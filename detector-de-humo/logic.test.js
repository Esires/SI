const test = require('node:test');
const assert = require('node:assert/strict');
const H = require('./logic.js');

const v = (canal, fecha, notaVerdad, notaUtilidad) => ({ canal, fecha, notaVerdad, notaUtilidad });

test('media ponderada 0,4 verdad + 0,6 utilidad', () => {
  assert.ok(Math.abs(H.media(v('@a', '2026-11-05', 6, 2)) - 3.6) < 1e-9);
  assert.ok(Math.abs(H.media(v('@a', '2026-11-05', 7, 6)) - 6.4) < 1e-9);
});

test('nota inválida (texto, fuera de rango, ausente) da media null', () => {
  assert.equal(H.media(v('@a', 'x', '7', 5)), null);
  assert.equal(H.media(v('@a', 'x', 11, 5)), null);
  assert.equal(H.media(v('@a', 'x', -1, 5)), null);
  assert.equal(H.media({ canal: '@a' }), null);
  assert.equal(H.nota(NaN), null);
  assert.equal(H.nota(0), 0);
  assert.equal(H.nota(10), 10);
});

test('semáforo', () => {
  assert.equal(H.semaforo(7), 'verde');
  assert.equal(H.semaforo(10), 'verde');
  assert.equal(H.semaforo(6.9), 'amarillo');
  assert.equal(H.semaforo(4), 'amarillo');
  assert.equal(H.semaforo(3.9), 'rojo');
  assert.equal(H.semaforo(null), null);
});

test('ranking agrupa canales escritos distinto y ordena por media desc', () => {
  const r = H.ranking([
    v('@Sebintel', '2026-11-05', 7, 6),
    v('@sebintel ', '2026-11-04', 9, 8),
    v('@malo', '2026-11-05', 2, 2),
    v('@roto', '2026-11-05', 'x', 2),
  ]);
  assert.equal(r.length, 3);
  assert.equal(r[0].n, 2);
  assert.equal(H.claveCanal(r[0].canal), '@sebintel');
  assert.ok(Math.abs(r[0].verdad - 8) < 1e-9);
  assert.ok(Math.abs(r[0].utilidad - 7) < 1e-9);
  assert.ok(Math.abs(r[0].media - 7.4) < 1e-9);
  assert.equal(r[1].canal, '@malo');
  assert.equal(r[2].canal, '@roto');
  assert.equal(r[2].media, null);
  assert.equal(r[2].verdad, null);
  assert.ok(Math.abs(r[2].utilidad - 2) < 1e-9);
});

test('ordenarVideos: por media de mayor a menor, sin nota al final, no muta', () => {
  const lista = [v('@a', '2026-11-05', 3, 2), v('@b', '2026-11-01', 8, 8), v('@sin', '2026-11-06'), v('@c', '2026-11-03', 6, 5)];
  const copia = lista.slice();
  const o = H.ordenarVideos(lista);
  assert.deepEqual(o.map(x => x.canal), ['@b', '@c', '@a', '@sin']);
  assert.deepEqual(lista, copia);
});

test('ordenarVideos: misma media, el más reciente primero (fecha y luego creado); sin fecha al final', () => {
  const lista = [
    { canal: '@sinfecha', notaVerdad: 5, notaUtilidad: 5 },
    { canal: '@viejo', fecha: '2026-11-05', notaVerdad: 5, notaUtilidad: 5 },
    { canal: '@a', fecha: '2026-11-05', creado: '2026-11-05T20:00:00Z', notaVerdad: 5, notaUtilidad: 5 },
    { canal: '@b', fecha: '2026-11-05', creado: '2026-11-05T21:00:00Z', notaVerdad: 5, notaUtilidad: 5 },
    { canal: '@nuevo', fecha: '2026-11-08', notaVerdad: 5, notaUtilidad: 5 },
  ];
  assert.deepEqual(H.ordenarVideos(lista).map(x => x.canal), ['@nuevo', '@b', '@a', '@viejo', '@sinfecha']);
});

test('ordenarVideos: medias que solo difieren por redondeo cuentan como empate', () => {
  // 0,4*6 + 0,6*4 = 4,8 y 0,4*3 + 0,6*6 = 4,8 (en coma flotante pueden salir distintas)
  const lista = [v('@viejo', '2026-11-01', 6, 4), v('@nuevo', '2026-11-08', 3, 6)];
  assert.deepEqual(H.ordenarVideos(lista).map(x => x.canal), ['@nuevo', '@viejo']);
});

test('enlaceSeguro solo acepta https', () => {
  assert.equal(H.enlaceSeguro('https://vm.tiktok.com/ZN8fjqCdd/'), 'https://vm.tiktok.com/ZN8fjqCdd/');
  assert.equal(H.enlaceSeguro(' https://www.tiktok.com/@a/video/1 '), 'https://www.tiktok.com/@a/video/1');
  assert.equal(H.enlaceSeguro('javascript:alert(1)'), null);
  assert.equal(H.enlaceSeguro('http://tiktok.com'), null);
  assert.equal(H.enlaceSeguro(undefined), null);
});

test('filtrar por canal', () => {
  const lista = [v('@A ', 'x', 1, 1), v('@b', 'x', 1, 1)];
  assert.equal(H.filtrar(lista, null).length, 2);
  assert.deepEqual(H.filtrar(lista, '@a').map(x => x.canal), ['@A ']);
});

test('fmt con coma y un decimal', () => {
  assert.equal(H.fmt(6.4), '6,4');
  assert.equal(H.fmt(6), '6');
  assert.equal(H.fmt(6.25), '6,3');
  assert.equal(H.fmt(6.04), '6');
  assert.equal(H.fmt(null), '—');
});

test('ordenarPorEnvio: el último enviado primero, sin mirar la nota; sin fecha al final; no muta', () => {
  const lista = [
    { canal: '@alta', fecha: '2026-11-05', creado: '2026-11-05T10:00:00Z', notaVerdad: 10, notaUtilidad: 10 },
    { canal: '@sinfecha', notaVerdad: 9, notaUtilidad: 9 },
    { canal: '@hoy-tarde', fecha: '2026-11-13', creado: '2026-11-13T18:00:00Z', notaVerdad: 1, notaUtilidad: 1 },
    { canal: '@hoy-pronto', fecha: '2026-11-13', creado: '2026-11-13T09:00:00Z', notaVerdad: 5, notaUtilidad: 5 },
  ];
  const copia = lista.map(x => x.canal);
  assert.deepEqual(H.ordenarPorEnvio(lista).map(x => x.canal), ['@hoy-tarde', '@hoy-pronto', '@alta', '@sinfecha']);
  assert.deepEqual(lista.map(x => x.canal), copia);
});
