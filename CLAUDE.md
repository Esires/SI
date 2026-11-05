# Detector de humo IA

Este repo guarda el código de **Detector de humo IA**: una página privada de
claude.ai donde el usuario apunta los vídeos de TikTok/Reels sobre Claude e IA
que ve, con lo que prometen, lo que es verdad y lo que es humo.

- Diseño: `docs/superpowers/specs/2026-11-05-detector-de-humo-design.md`
- Código: `detector-de-humo/` (`index.html`, `logic.js` + `logic.test.js`)
- **Nunca** guardes en este repo las notas ni la URL de la página: el repo es
  público. La URL te la da el usuario al empezar el chat.

## Cuando el usuario te pase un vídeo (enlace o archivo)

Habla en español, de tú, claro y sin rollo. El usuario no programa.

1. **Consíguelo** en una carpeta nueva del scratchpad:
   - Enlace: `yt-dlp -q --no-warnings --write-info-json -o "video.%(ext)s" <enlace>`
     (el `.info.json` trae el canal y la descripción). Los carruseles de fotos
     (`/photo/`) no se pueden bajar: pide capturas.
   - Si falta algún programa: `pip install -q faster-whisper yt-dlp`.
   - Si la red bloquea un servidor de TikTok o Hugging Face, mira cuál en
     `curl -sS "$HTTPS_PROXY/__agentproxy/status"` y pide añadirlo con las
     herramientas del entorno (`get_environment` → `update_environment`).
2. **Míralo y escúchalo:**
   - Fotogramas: `ffmpeg -v error -i video.mp4 -vf "fps=1/2,scale=480:-1,tile=5x2" sheet_%02d.jpg`
     y lee las imágenes.
   - Audio (pasa el audio ya extraído; abrir el vídeo directamente falla):
     ```python
     import subprocess, numpy as np
     from faster_whisper import WhisperModel
     raw = subprocess.run(['ffmpeg','-v','error','-i','video.mp4','-ac','1','-ar','16000','-f','f32le','-'],
                          capture_output=True, check=True).stdout
     m = WhisperModel('small', device='cpu', compute_type='int8')
     segs, info = m.transcribe(np.frombuffer(raw, np.float32))
     print(' '.join(s.text.strip() for s in segs))
     ```
3. **Compruébalo.** Si nombra una herramienta, skill, plugin o conector,
   verifica que existe (búsqueda web, catálogo de plugins o de conectores).
   No te fíes del vídeo.
4. **Pon las notas (0–10, enteros):**
   - **Verdad:** ¿lo que cuenta es cierto o exagera?
   - **Utilidad:** ¿le sirve a este usuario? Hace apps con Claude Code, paga
     Claude Pro y ya tiene los plugins claude-code-setup, Superpowers y
     frontend-design.
   - **Media** = 0,4 × Verdad + 0,6 × Utilidad. Semáforo: 🟢 ≥ 7, 🟡 ≥ 4, 🔴 < 4.
5. **Guárdalo directamente, sin pedir permiso**, con `ArtifactData` `set` en la
   colección `videos` de la página. `doc_id` en minúsculas con guiones
   (`canal-tema`, sin `@`). Campos:

   | Campo | Contenido |
   |---|---|
   | `canal` | `@usuario` |
   | `titulo` | de qué va, corto |
   | `fecha` | hoy, `AAAA-MM-DD` |
   | `creado` | ahora, ISO UTC (`2026-11-05T22:00:00Z`) |
   | `enlace` | el enlace del vídeo, si lo hay (solo `https://`) |
   | `promete` | resumen de lo que vende |
   | `verdad` | lista de frases: lo que es cierto |
   | `humo` | lista de frases: lo que exagera o es falso |
   | `veredicto` | qué hacer / qué se instaló |
   | `instalado` | `true` si se instala algo, si no `false` |
   | `notaVerdad`, `notaUtilidad` | números 0–10 |

   La Media no se guarda: la calcula la página.
6. **Respóndele** con: qué dice el vídeo, ✅ lo que es verdad, 💨 lo que es humo,
   si le sirve, una tabla `Canal | Verdad | Utilidad | Media` y **debajo el
   enlace del vídeo** (🔗). Si recomiendas instalar algo, búscalo con
   `SearchPlugins` y sácale la tarjeta con `SuggestPluginInstall`.

Si el usuario no está de acuerdo con una nota, cámbiala con `ArtifactData`
`update` (con el `if_version` que leíste).
