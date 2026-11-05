// Lógica del Detector de humo: funciones puras, sin DOM.
(function (root) {
  const PESO_VERDAD = 0.4;
  const PESO_UTILIDAD = 0.6;

  function nota(x) {
    return typeof x === 'number' && Number.isFinite(x) && x >= 0 && x <= 10 ? x : null;
  }

  function media(video) {
    const ver = nota(video && video.notaVerdad);
    const uti = nota(video && video.notaUtilidad);
    if (ver === null || uti === null) return null;
    return PESO_VERDAD * ver + PESO_UTILIDAD * uti;
  }

  function semaforo(n) {
    if (n === null || n === undefined) return null;
    if (n >= 7) return 'verde';
    if (n >= 4) return 'amarillo';
    return 'rojo';
  }

  function claveCanal(canal) {
    return String(canal || '').trim().toLowerCase();
  }

  function promedio(nums) {
    const ok = nums.filter(n => n !== null);
    return ok.length ? ok.reduce((a, b) => a + b, 0) / ok.length : null;
  }

  function ranking(videos) {
    const grupos = new Map();
    for (const v of videos || []) {
      const k = claveCanal(v.canal);
      if (!grupos.has(k)) grupos.set(k, { canal: String(v.canal || '').trim() || '(sin canal)', lista: [] });
      grupos.get(k).lista.push(v);
    }
    const filas = [...grupos.values()].map(g => ({
      canal: g.canal,
      n: g.lista.length,
      verdad: promedio(g.lista.map(v => nota(v.notaVerdad))),
      utilidad: promedio(g.lista.map(v => nota(v.notaUtilidad))),
      media: promedio(g.lista.map(media)),
    }));
    return filas.sort((a, b) => {
      if (a.media === null) return b.media === null ? 0 : 1;
      if (b.media === null) return -1;
      return b.media - a.media;
    });
  }

  function ordenarVideos(videos) {
    return (videos || []).slice().sort((a, b) => {
      const fa = typeof a.fecha === 'string' ? a.fecha : '';
      const fb = typeof b.fecha === 'string' ? b.fecha : '';
      if (!fa) return fb ? 1 : 0;
      if (!fb) return -1;
      if (fa !== fb) return fb.localeCompare(fa);
      const ca = typeof a.creado === 'string' ? a.creado : '';
      const cb = typeof b.creado === 'string' ? b.creado : '';
      if (!ca) return cb ? 1 : 0;
      if (!cb) return -1;
      return cb.localeCompare(ca);
    });
  }

  function filtrar(videos, clave) {
    if (!clave) return (videos || []).slice();
    return (videos || []).filter(v => claveCanal(v.canal) === clave);
  }

  function fmt(n) {
    if (n === null || n === undefined) return '—';
    const r = Math.round(n * 10) / 10;
    return Number.isInteger(r) ? String(r) : r.toFixed(1).replace('.', ',');
  }

  function enlaceSeguro(url) {
    if (typeof url !== 'string') return null;
    const u = url.trim();
    return /^https:\/\/[^\s]+$/i.test(u) ? u : null;
  }

  const api = { nota, media, semaforo, claveCanal, ranking, ordenarVideos, filtrar, fmt, enlaceSeguro };
  if (typeof module !== 'undefined' && module.exports) module.exports = api;
  else root.Humo = api;
})(typeof globalThis !== 'undefined' ? globalThis : this);
