# Detector de humo — diseño

Fecha: 2026-11-05

## Para qué es

Una libreta personal donde se guardan los vídeos de TikTok/Reels sobre Claude
(skills, conectores, plugins…) que el usuario ve, con el análisis de cada uno:
qué promete, qué es verdad y qué es humo. Con el tiempo, la media de cada canal
dice de quién fiarse.

Lo usa una sola persona, sobre todo desde el móvil.

**Éxito:** abrir un enlace en el móvil y ver de un vistazo qué canales son
fiables y qué se dijo de cada vídeo, sin tener que escribir nada en la página.

## Fuera de alcance (por ahora)

- Que la app analice vídeos sola (necesitaría la API de Claude, de pago aparte).
  Queda como posible segunda fase.
- Editar datos desde la página. Todo cambio se pide a Claude en el chat.
- Varios usuarios, cuentas, compartir.

## Dónde vive

Una página privada (Artifact) en la cuenta de claude.ai, con base de datos
propia (capacidad `db`). No usa GitHub, Netlify ni nada de pago. Los datos
**no** van en el repositorio (es público).

## Flujo para añadir un vídeo

1. El usuario pasa el vídeo a Claude por el chat.
2. Claude lo analiza, le pone las notas y lo guarda directamente en la base de
   datos de la página (`ArtifactData`), sin pedir confirmación.
3. Si el usuario no está de acuerdo, se lo dice a Claude y lo corrige.

La página solo lee y muestra los datos.

## Datos

Colección `videos`, un documento por vídeo:

| Campo | Tipo | Notas |
|---|---|---|
| `canal` | texto | p. ej. `@sebintel` |
| `titulo` | texto | de qué va, en pocas palabras |
| `fecha` | texto `AAAA-MM-DD` | cuándo se analizó |
| `promete` | texto | resumen de lo que vende el vídeo |
| `verdad` | lista de textos | lo que es cierto |
| `humo` | lista de textos | lo que exagera o es falso |
| `veredicto` | texto | qué se decidió / qué se hizo |
| `instalado` | sí/no | si se instaló algo del vídeo |
| `notaVerdad` | número 0–10 | ¿lo que cuenta es cierto? |
| `notaUtilidad` | número 0–10 | ¿le sirve al usuario? |

La **Media** de un vídeo no se guarda: es una media ponderada que da más peso
a la utilidad, `0,4 × notaVerdad + 0,6 × notaUtilidad`.

Reglas de acceso: lectura para quien pueda ver la página, escritura solo el
propietario.

## Cálculos

- **Media del vídeo:** `0,4 × Verdad + 0,6 × Utilidad`. Si falta una de las
  dos notas, la Media se muestra como "—".
- **Por canal:** nº de vídeos, media de `notaVerdad`, media de `notaUtilidad`
  y media de las Medias. Se muestran con un decimal.
- **Semáforo** (solo sobre la Media, del vídeo o del canal):
  - 🟢 de 7 a 10
  - 🟡 de 4 a 6,9
  - 🔴 menos de 4

Verdad y Utilidad se muestran como números sin color.

## Pantalla

Una sola página, pensada primero para móvil.

1. **Ranking de canales** (arriba): ordenado por Media de mayor a menor. Cada
   canal muestra su nº de vídeos, Verdad, Utilidad y Media con semáforo.
   Tocar un canal filtra la lista de vídeos a ese canal; tocarlo otra vez (o
   "Ver todos") quita el filtro.
2. **Lista de vídeos** (debajo): de más nuevo a más viejo. Cada fila: canal,
   título, fecha, Verdad, Utilidad, Media con semáforo y ✅ si se instaló algo.
3. **Ficha del vídeo:** al tocar una fila se despliega con promete, verdad,
   humo y veredicto.

Diseño cuidado (skill frontend-design), modo claro y oscuro.

## Estados y errores

- **Cargando:** aviso de carga; si en 10 s no llegan datos, mensaje de que no
  se ha podido cargar.
- **Sin datos:** mensaje "Todavía no hay vídeos. Pásale uno a Claude."
- **Sin base de datos** (`claude.use("db")` devuelve `null`): mensaje de que la
  página debe abrirse desde claude.ai con la sesión iniciada.
- Datos raros (nota fuera de 0–10 o que falta): la fila se muestra igualmente y
  esa nota aparece como "—", sin romper las medias.

## Datos iniciales

Tras publicar, Claude carga los 5 vídeos analizados en el chat del 2026-11-05,
con las notas acordadas con el usuario.

## Pruebas

- Lógica de cálculo (medias, semáforo, orden, filtro, notas que faltan) en
  funciones puras, con pruebas antes del código (TDD), ejecutadas con Node.
- Tras publicar: una lectura `ArtifactData list` de `videos` para comprobar
  los 5 vídeos, y una revisión visual de la página a ancho de móvil.
