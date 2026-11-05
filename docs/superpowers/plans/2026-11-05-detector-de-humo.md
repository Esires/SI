# Detector de humo Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Página privada de claude.ai que muestra los vídeos analizados, sus notas y el ranking de canales.

**Architecture:** Un Artifact (`index.html`) que lee la colección `videos` de su `db` y la pinta. Toda la lógica de cálculo vive en `logic.js` (funciones puras, sin DOM), que la página carga como fichero adjunto y que Node prueba directamente. Claude escribe los datos con `ArtifactData`.

**Tech Stack:** HTML/CSS/JS sin dependencias; pruebas con `node --test`.

**Spec:** `docs/superpowers/specs/2026-11-05-detector-de-humo-design.md`

## Global Constraints

- Media = `0,4 × notaVerdad + 0,6 × notaUtilidad`.
- Semáforo solo sobre la Media: 🟢 ≥ 7, 🟡 ≥ 4 y < 7, 🔴 < 4.
- Nota válida = número finito entre 0 y 10; si no, se muestra "—" y no entra en medias.
- Números con coma decimal y como mucho un decimal (`6,4`, `6`).
- Ningún dato de usuario va al repositorio (es público): solo código.
- Textos de la base de datos se pintan con `textContent`, nunca como HTML.

## Review Focus

- Nota guardada como texto (`"7"`) o fuera de rango → se trata como ausente, sin `NaN` en pantalla.
- Mismo canal escrito distinto (`@Sebintel` / `@sebintel `) → un solo canal en el ranking.
- Vídeo sin `fecha` → va al final de la lista, no rompe el orden.
- `verdad`/`humo` ausentes o no lista → ficha sin esa sección, sin error.
- Texto con `<script>` en un campo → se ve como texto.

---

### Task 1: Lógica de cálculo

**Files:**
- Create: `detector-de-humo/logic.js`
- Test: `detector-de-humo/logic.test.js`

**Interfaces:**
- Produces (en `globalThis.Humo` en el navegador y `module.exports` en Node):
  - `nota(x): number|null`
  - `media(video): number|null`
  - `semaforo(n: number|null): 'verde'|'amarillo'|'rojo'|null`
  - `claveCanal(canal: string): string` (trim + minúsculas)
  - `ranking(videos): Array<{canal, n, verdad, utilidad, media}>` ordenado por `media` desc, `null` al final; `media` del canal = media de las Medias válidas.
  - `ordenarVideos(videos): videos[]` por `fecha` desc, sin fecha al final; no muta la entrada.
  - `filtrar(videos, clave: string|null): videos[]`
  - `fmt(n: number|null): string`

- [ ] **Step 1: Write failing tests** — casos: `media({notaVerdad:6,notaUtilidad:2})` ≈ 3.6; `media` con `"7"` → `null`; `semaforo(7)` `'verde'`, `semaforo(6.9)` `'amarillo'`, `semaforo(3.9)` `'rojo'`, `semaforo(null)` `null`; `ranking` agrupa `@Sebintel` y `@sebintel ` en uno con `n: 2`; `ranking` ordena por media desc; `ordenarVideos` pone sin fecha al final y no muta; `filtrar(v, null)` devuelve todos; `fmt(6.4)` `'6,4'`, `fmt(6)` `'6'`, `fmt(6.25)` `'6,3'`, `fmt(null)` `'—'`.
- [ ] **Step 2: Run** `node --test detector-de-humo/logic.test.js` → FAIL (módulo no existe).
- [ ] **Step 3: Implement** `logic.js` con las firmas de arriba.
- [ ] **Step 4: Run** `node --test detector-de-humo/logic.test.js` → todos PASS.
- [ ] **Step 5: Commit.**

### Task 2: Página

**Files:**
- Create: `detector-de-humo/index.html`

**Interfaces:**
- Consumes: `globalThis.Humo` de Task 1 (cargado con `<script src="logic.js">`).
- `db` con `rules: [{path:"videos", read:"view", write:"owner"}]`; lectura con `onSnapshot` de `db.collection("videos")`.

- [ ] **Step 1: Write** la página según la sección "Pantalla" del spec: ranking (tocar = filtrar, "Ver todos" quita filtro), lista, ficha desplegable; estados de carga (10 s), vacío y sin `db`.
- [ ] **Step 2: Verify locally** con Playwright a 390 px de ancho y datos falsos inyectados (incluidos los casos de Review Focus): sin scroll horizontal, sin `NaN`, `<script>` visible como texto.
- [ ] **Step 3: Commit.**

### Task 3: Publicar y cargar datos

- [ ] **Step 1: Publish** el Artifact (`index.html` + `logic.js`) con capacidad `db`.
- [ ] **Step 2: Write** los 5 vídeos acordados con `ArtifactData batch`.
- [ ] **Step 3: Verify** con `ArtifactData list` de `videos` → 5 documentos.
