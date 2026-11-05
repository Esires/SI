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

test('ordenarVideos: fecha desc, sin fecha al final, no muta', () => {
  const lista = [v('@a', '2026-11-01', 1, 1), v('@b', undefined, 1, 1), v('@c', '2026-11-05', 1, 1)];
  const copia = lista.slice();
  const o = H.ordenarVideos(lista);
  assert.deepEqual(o.map(x => x.canal), ['@c', '@a', '@b']);
  assert.deepEqual(lista, copia);
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
