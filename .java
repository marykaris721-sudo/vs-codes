<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Number System Converter</title>
<style>
  /* ============================================================
     1. THEME TOKENS
     ============================================================ */
  :root {
    --bg: #f4f6fb;
    --bg-2: #ffffff;
    --panel: #ffffff;
    --panel-2: #f7f9fc;
    --border: #e2e8f0;
    --text: #1a202c;
    --text-2: #64748b;
    --primary: #4f46e5;
    --primary-hover: #4338ca;
    --primary-soft: #eef2ff;
    --success: #16a34a;
    --success-soft: #dcfce7;
    --error: #dc2626;
    --error-soft: #fee2e2;
    --warn: #d97706;
    --shadow: 0 10px 30px rgba(15, 23, 42, 0.08);
    --shadow-lg: 0 20px 60px rgba(15, 23, 42, 0.15);
    --radius: 16px;
    --radius-sm: 10px;
    --font: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
    --mono: 'SF Mono', 'Menlo', 'Consolas', monospace;
  }

  [data-theme="dark"] {
    --bg: #0b0f1a;
    --bg-2: #0f1524;
    --panel: #151b2e;
    --panel-2: #1a2138;
    --border: #232b45;
    --text: #e6ecff;
    --text-2: #8b95b8;
    --primary: #7c7cff;
    --primary-hover: #9494ff;
    --primary-soft: #1e2144;
    --success: #4ade80;
    --success-soft: #052e16;
    --error: #ff6b6b;
    --error-soft: #3b0a0a;
    --warn: #fbbf24;
    --shadow: 0 10px 30px rgba(0, 0, 0, 0.4);
    --shadow-lg: 0 20px 60px rgba(0, 0, 0, 0.6);
  }

  /* ============================================================
     2. BASE
     ============================================================ */
  * { box-sizing: border-box; margin: 0; padding: 0; }

  html, body {
    min-height: 100%;
    font-family: var(--font);
    background: var(--bg);
    color: var(--text);
    transition: background 0.35s ease, color 0.35s ease;
  }

  body {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 24px 16px 60px;
    min-height: 100vh;
    background:
      radial-gradient(circle at 15% 0%, rgba(124, 124, 255, 0.12), transparent 45%),
      radial-gradient(circle at 85% 100%, rgba(255, 100, 150, 0.10), transparent 45%),
      var(--bg);
    background-attachment: fixed;
  }

  /* ============================================================
     3. HEADER
     ============================================================ */
  .header {
    width: 100%;
    max-width: 720px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }

  .header .brand {
    display: flex;
    align-items: center;
    gap: 12px;
  }

  .header .logo {
    width: 44px;
    height: 44px;
    border-radius: 12px;
    background: linear-gradient(135deg, var(--primary), #a855f7);
    display: grid;
    place-items: center;
    color: #fff;
    font-size: 22px;
    font-weight: bold;
    box-shadow: 0 8px 20px rgba(124, 124, 255, 0.35);
  }

  .header h1 {
    font-size: 1.3rem;
    font-weight: 700;
    letter-spacing: -0.5px;
  }

  .header .sub {
    font-size: 0.78rem;
    color: var(--text-2);
    margin-top: 2px;
  }

  .theme-toggle {
    width: 46px;
    height: 46px;
    border: 1px solid var(--border);
    background: var(--panel);
    border-radius: 12px;
    cursor: pointer;
    display: grid;
    place-items: center;
    font-size: 20px;
    color: var(--text);
    transition: all 0.25s ease;
    box-shadow: var(--shadow);
  }

  .theme-toggle:hover {
    transform: translateY(-2px);
    border-color: var(--primary);
    color: var(--primary);
  }

  .theme-toggle:active { transform: scale(0.94); }

  /* ============================================================
     4. CARD / PANEL
     ============================================================ */
  .card {
    width: 100%;
    max-width: 720px;
    background: var(--panel);
    border: 1px solid var(--border);
    border-radius: var(--radius);
    padding: 22px;
    box-shadow: var(--shadow);
    transition: all 0.35s ease;
  }

  .card + .card { margin-top: 18px; }

  /* ============================================================
     5. BASE SELECTORS
     ============================================================ */
  .field-label {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 0.8rem;
    font-weight: 600;
    color: var(--text-2);
    letter-spacing: 0.6px;
    text-transform: uppercase;
    margin-bottom: 10px;
  }

  .field-label span.info {
    font-size: 0.72rem;
    font-weight: 500;
    text-transform: none;
    letter-spacing: 0;
    color: var(--text-2);
  }

  .base-chips {
    display: flex;
    gap: 8px;
    flex-wrap: wrap;
    margin-bottom: 18px;
  }

  .chip {
    padding: 9px 16px;
    border-radius: 30px;
    border: 1px solid var(--border);
    background: var(--panel-2);
    color: var(--text);
    font-size: 0.85rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s ease;
    display: flex;
    align-items: center;
    gap: 6px;
    user-select: none;
  }

  .chip:hover {
    border-color: var(--primary);
    color: var(--primary);
    transform: translateY(-1px);
  }

  .chip.active {
    background: var(--primary);
    color: #fff;
    border-color: var(--primary);
    box-shadow: 0 6px 18px rgba(79, 70, 229, 0.35);
  }

  .chip .base-num {
    font-size: 0.7rem;
    padding: 1px 6px;
    border-radius: 6px;
    background: rgba(0, 0, 0, 0.08);
  }

  .chip.active .base-num {
    background: rgba(255, 255, 255, 0.25);
  }

  /* Custom base row */
  .custom-base-row {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-top: -6px;
    margin-bottom: 16px;
    font-size: 0.82rem;
    color: var(--text-2);
    flex-wrap: wrap;
  }

  .custom-base-row input[type="number"] {
    width: 80px;
    padding: 8px 10px;
    border-radius: var(--radius-sm);
    border: 1px solid var(--border);
    background: var(--panel-2);
    color: var(--text);
    font-family: var(--mono);
    font-size: 0.9rem;
    outline: none;
    transition: border 0.2s ease;
  }

  .custom-base-row input:focus {
    border-color: var(--primary);
    box-shadow: 0 0 0 3px var(--primary-soft);
  }

  /* ============================================================
     6. INPUT FIELD
     ============================================================ */
  .input-wrap {
    position: relative;
    margin-bottom: 6px;
  }

  .input-wrap input {
    width: 100%;
    padding: 16px 100px 16px 18px;
    border-radius: var(--radius-sm);
    border: 1.5px solid var(--border);
    background: var(--panel-2);
    color: var(--text);
    font-size: 1.15rem;
    font-family: var(--mono);
    letter-spacing: 1px;
    outline: none;
    transition: all 0.2s ease;
  }

  .input-wrap input:focus {
    border-color: var(--primary);
    box-shadow: 0 0 0 4px var(--primary-soft);
  }

  .input-wrap input.invalid {
    border-color: var(--error);
    box-shadow: 0 0 0 4px var(--error-soft);
    animation: shake 0.4s ease;
  }

  @keyframes shake {
    0%, 100% { transform: translateX(0); }
    25%      { transform: translateX(-6px); }
    75%      { transform: translateX(6px); }
  }

  .input-actions {
    position: absolute;
    right: 8px;
    top: 50%;
    transform: translateY(-50%);
    display: flex;
    gap: 4px;
  }

  .icon-btn {
    width: 38px;
    height: 38px;
    border: none;
    border-radius: 8px;
    background: transparent;
    color: var(--text-2);
    cursor: pointer;
    display: grid;
    place-items: center;
    font-size: 16px;
    transition: all 0.2s ease;
  }

  .icon-btn:hover {
    background: var(--primary-soft);
    color: var(--primary);
  }

  /* ============================================================
     7. MESSAGES
     ============================================================ */
  .msg {
    min-height: 20px;
    font-size: 0.82rem;
    padding: 6px 4px;
    display: flex;
    align-items: center;
    gap: 6px;
    transition: opacity 0.2s ease;
  }

  .msg.error   { color: var(--error);   }
  .msg.success { color: var(--success); }
  .msg.hint    { color: var(--text-2);  }

  /* ============================================================
     8. ACTIONS (swap, convert)
     ============================================================ */
  .actions {
    display: flex;
    gap: 10px;
    margin-top: 10px;
  }

  .btn {
    flex: 1;
    padding: 14px 18px;
    border: none;
    border-radius: var(--radius-sm);
    font-family: var(--font);
    font-size: 0.95rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s ease;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
  }

  .btn-primary {
    background: linear-gradient(135deg, var(--primary), #6366f1);
    color: #fff;
    box-shadow: 0 8px 22px rgba(79, 70, 229, 0.35);
  }
  .btn-primary:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 28px rgba(79, 70, 229, 0.5);
  }
  .btn-primary:active { transform: translateY(0); }

  .btn-secondary {
    background: var(--panel-2);
    color: var(--text);
    border: 1px solid var(--border);
  }
  .btn-secondary:hover {
    border-color: var(--primary);
    color: var(--primary);
    transform: translateY(-2px);
  }

  /* ============================================================
     9. RESULT
     ============================================================ */
  .result {
    margin-top: 20px;
    padding: 20px;
    border-radius: var(--radius-sm);
    background: linear-gradient(135deg, var(--primary-soft), transparent);
    border: 1px solid var(--border);
    opacity: 0;
    transform: translateY(8px);
    transition: all 0.35s ease;
  }

  .result.show {
    opacity: 1;
    transform: translateY(0);
  }

  .result .label {
    font-size: 0.75rem;
    font-weight: 700;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: var(--primary);
    margin-bottom: 8px;
  }

  .result .value {
    font-family: var(--mono);
    font-size: 1.6rem;
    font-weight: 700;
    word-break: break-all;
    color: var(--text);
    margin-bottom: 12px;
    letter-spacing: 1px;
  }

  .result .meta {
    display: flex;
    gap: 16px;
    flex-wrap: wrap;
    font-size: 0.78rem;
    color: var(--text-2);
  }

  .result .meta b { color: var(--text); font-weight: 600; }

  .result-actions {
    display: flex;
    gap: 8px;
    margin-top: 14px;
  }

  .mini-btn {
    padding: 8px 14px;
    border-radius: 8px;
    border: 1px solid var(--border);
    background: var(--panel);
    color: var(--text);
    font-size: 0.78rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s ease;
    display: flex;
    align-items: center;
    gap: 6px;
  }

  .mini-btn:hover {
    border-color: var(--primary);
    color: var(--primary);
    transform: translateY(-1px);
  }

  /* ============================================================
     10. HISTORY
     ============================================================ */
  .history-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 14px;
  }

  .history-header h2 {
    font-size: 1rem;
    font-weight: 700;
    display: flex;
    align-items: center;
    gap: 8px;
  }

  .history-header h2 .count {
    font-size: 0.72rem;
    padding: 2px 8px;
    border-radius: 20px;
    background: var(--primary-soft);
    color: var(--primary);
    font-weight: 700;
  }

  .clear-history {
    font-size: 0.78rem;
    color: var(--error);
    background: transparent;
    border: none;
    cursor: pointer;
    padding: 6px 10px;
    border-radius: 6px;
    font-weight: 600;
    transition: background 0.2s ease;
  }

  .clear-history:hover { background: var(--error-soft); }

  .history-empty {
    text-align: center;
    padding: 30px 10px;
    color: var(--text-2);
    font-size: 0.9rem;
  }

  .history-empty .big {
    font-size: 40px;
    display: block;
    margin-bottom: 8px;
    opacity: 0.5;
  }

  .history-list {
    list-style: none;
    display: flex;
    flex-direction: column;
    gap: 8px;
    max-height: 320px;
    overflow-y: auto;
    padding-right: 4px;
  }

  .history-list::-webkit-scrollbar { width: 6px; }
  .history-list::-webkit-scrollbar-thumb {
    background: var(--border);
    border-radius: 3px;
  }

  .history-item {
    background: var(--panel-2);
    border: 1px solid var(--border);
    border-radius: var(--radius-sm);
    padding: 12px 14px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 12px;
    transition: all 0.2s ease;
    animation: slideIn 0.3s ease;
  }

  @keyframes slideIn {
    from { opacity: 0; transform: translateY(-6px); }
    to   { opacity: 1; transform: translateY(0); }
  }

  .history-item:hover {
    border-color: var(--primary);
    transform: translateX(2px);
  }

  .history-item .info {
    flex: 1;
    min-width: 0;
  }

  .history-item .conversion {
    font-family: var(--mono);
    font-size: 0.88rem;
    color: var(--text);
    margin-bottom: 4px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .history-item .conversion .arrow {
    color: var(--primary);
    margin: 0 6px;
    font-weight: bold;
  }

  .history-item .timestamp {
    font-size: 0.7rem;
    color: var(--text-2);
  }

  .history-item .item-actions {
    display: flex;
    gap: 4px;
    flex-shrink: 0;
  }

  .history-item .item-actions button {
    width: 32px;
    height: 32px;
    border-radius: 8px;
    border: none;
    background: transparent;
    color: var(--text-2);
    cursor: pointer;
    font-size: 14px;
    display: grid;
    place-items: center;
    transition: all 0.2s ease;
  }

  .history-item .item-actions button:hover {
    background: var(--primary-soft);
    color: var(--primary);
  }

  .history-item .item-actions button.delete:hover {
    background: var(--error-soft);
    color: var(--error);
  }

  /* ============================================================
     11. TOAST
     ============================================================ */
  .toast {
    position: fixed;
    bottom: 30px;
    left: 50%;
    transform: translateX(-50%) translateY(20px);
    background: var(--panel);
    border: 1px solid var(--border);
    color: var(--text);
    padding: 12px 20px;
    border-radius: 12px;
    box-shadow: var(--shadow-lg);
    font-size: 0.88rem;
    font-weight: 600;
    display: flex;
    align-items: center;
    gap: 10px;
    opacity: 0;
    pointer-events: none;
    transition: all 0.3s ease;
    z-index: 999;
  }

  .toast.show {
    opacity: 1;
    transform: translateX(-50%) translateY(0);
  }

  .toast.success { border-color: var(--success); color: var(--success); }
  .toast.error   { border-color: var(--error);   color: var(--error);   }

  /* ============================================================
     12. RESPONSIVE
     ============================================================ */
  @media (max-width: 520px) {
    body { padding: 16px 12px 50px; }
    .header h1 { font-size: 1.1rem; }
    .header .logo { width: 38px; height: 38px; font-size: 18px; }
    .card { padding: 16px; border-radius: 14px; }
    .result .value { font-size: 1.25rem; }
    .chip { padding: 8px 12px; font-size: 0.8rem; }
    .actions { flex-direction: column; }
    .actions .btn { width: 100%; }
  }
</style>
</head>
<body>

  <!-- ============================================================
       HEADER
       ============================================================ -->
  <header class="header">
    <div class="brand">
      <div class="logo">#</div>
      <div>
        <h1>Number Converter</h1>
        <div class="sub">Binary · Octal · Decimal · Hex</div>
      </div>
    </div>
    <button class="theme-toggle" id="themeToggle" title="Toggle theme">🌙</button>
  </header>

  <!-- ============================================================
       CONVERTER CARD
       ============================================================ -->
  <main class="card">

    <!-- FROM base -->
    <div class="field-label">
      <span>From Base</span>
      <span class="info" id="fromInfo">Decimal (10)</span>
    </div>
    <div class="base-chips" id="fromChips">
      <div class="chip" data-base="2">Binary <span class="base-num">2</span></div>
      <div class="chip" data-base="8">Octal <span class="base-num">8</span></div>
      <div class="chip active" data-base="10">Decimal <span class="base-num">10</span></div>
      <div class="chip" data-base="16">Hex <span class="base-num">16</span></div>
    </div>
    <div class="custom-base-row">
      <label for="customFrom">Custom base (2–36):</label>
      <input type="number" id="customFrom" min="2" max="36" placeholder="e.g. 5" />
    </div>

    <!-- Input -->
    <div class="field-label">
      <span>Enter Number</span>
      <span class="info" id="validChars">Allowed: 0–9</span>
    </div>
    <div class="input-wrap">
      <input type="text" id="inputField" placeholder="e.g. 42" autocomplete="off" spellcheck="false" />
      <div class="input-actions">
        <button class="icon-btn" id="pasteBtn" title="Paste">📋</button>
        <button class="icon-btn" id="clearBtn" title="Clear">✕</button>
      </div>
    </div>
    <div class="msg hint" id="message">Type a number to convert</div>

    <!-- Actions -->
    <div class="actions">
      <button class="btn btn-secondary" id="swapBtn">🔄 Swap Bases</button>
      <button class="btn btn-primary" id="convertBtn">Convert →</button>
    </div>

    <!-- Result -->
    <div class="result" id="result">
      <div class="label" id="resultLabel">Result</div>
      <div class="value" id="resultValue">—</div>
      <div class="meta" id="resultMeta"></div>
      <div class="result-actions">
        <button class="mini-btn" id="copyResult">📄 Copy</button>
        <button class="mini-btn" id="saveResult">💾 Save to History</button>
      </div>
    </div>

  </main>

  <!-- ============================================================
       HISTORY CARD
       ============================================================ -->
  <section class="card">
    <div class="history-header">
      <h2>📜 History <span class="count" id="historyCount">0</span></h2>
      <button class="clear-history" id="clearHistory">Clear All</button>
    </div>
    <div class="history-empty" id="historyEmpty">
      <span class="big">🗂️</span>
      No conversions yet. Your history will appear here.
    </div>
    <ul class="history-list" id="historyList"></ul>
  </section>

  <!-- Toast -->
  <div class="toast" id="toast">Copied!</div>

<script>
  /* ============================================================
     STATE
     ============================================================ */
  const state = {
    fromBase: 10,
    input: '',
    result: null,
    lastError: null,
    history: [],
  };

  const BASE_NAMES = {
    2: 'Binary', 8: 'Octal', 10: 'Decimal', 16: 'Hexadecimal',
  };

  const STORAGE = {
    theme: 'nsc_theme',
    history: 'nsc_history',
    fromBase: 'nsc_fromBase',
  };

  /* ============================================================
     DOM
     ============================================================ */
  const $ = (id) => document.getElementById(id);
  const inputField   = $('inputField');
  const messageEl    = $('message');
  const resultEl     = $('result');
  const resultValue  = $('resultValue');
  const resultLabel  = $('resultLabel');
  const resultMeta   = $('resultMeta');
  const fromChips    = $('fromChips');
  const fromInfo     = $('fromInfo');
  const validChars   = $('validChars');
  const customFrom   = $('customFrom');
  const historyList  = $('historyList');
  const historyEmpty = $('historyEmpty');
  const historyCount = $('historyCount');
  const toastEl      = $('toast');

  /* ============================================================
     THEME
     ============================================================ */
  function applyTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    $('themeToggle').textContent = theme === 'dark' ? '☀️' : '🌙';
    try { localStorage.setItem(STORAGE.theme, theme); } catch (e) {}
  }

  function toggleTheme() {
    const current = document.documentElement.getAttribute('data-theme') || 'light';
    applyTheme(current === 'dark' ? 'light' : 'dark');
  }

  function initTheme() {
    let saved = null;
    try { saved = localStorage.getItem(STORAGE.theme); } catch (e) {}
    if (saved) return applyTheme(saved);
    const prefersDark = window.matchMedia &&
      window.matchMedia('(prefers-color-scheme: dark)').matches;
    applyTheme(prefersDark ? 'dark' : 'light');
  }

  /* ============================================================
     BASE HELPERS
     ============================================================ */
  function getValidChars(base) {
    if (base <= 10) return '0–' + (base - 1);
    return '0–9, A–' + String.fromCharCode(55 + base);
  }

  function getBaseName(base) {
    return BASE_NAMES[base] || `Base ${base}`;
  }

  /* ============================================================
     INPUT VALIDATION
     ============================================================ */
  function validateInput(raw, base) {
    const value = raw.trim();

    if (value === '') {
      return { ok: false, code: 'empty', msg: 'Please enter a number.' };
    }

    // Optional single leading minus
    let body = value;
    if (body.startsWith('-')) body = body.slice(1);
    if (body.startsWith('+')) body = body.slice(1);

    if (body === '') {
      return { ok: false, code: 'sign', msg: 'Enter digits after the sign.' };
    }

    const upper = body.toUpperCase();
    const maxCharCode = base <= 10 ? 47 + base : 54 + base;

    for (const ch of upper) {
      const code = ch.charCodeAt(0);
      const isDigit = code >= 48 && code <= 57;
      const isHexLetter = code >= 65 && code <= 90;

      if (isDigit) {
        if (code - 48 >= base) {
          return {
            ok: false,
            code: 'digit',
            msg: `Digit "${ch}" is not valid in ${getBaseName(base)} (base ${base}). Allowed: ${getValidChars(base)}.`,
          };
        }
      } else if (isHexLetter) {
        const val = code - 55; // 'A' → 10
        if (val >= base) {
          return {
            ok: false,
            code: 'digit',
            msg: `Character "${ch}" is not valid in ${getBaseName(base)} (base ${base}). Allowed: ${getValidChars(base)}.`,
          };
        }
      } else {
        return {
          ok: false,
          code: 'char',
          msg: `Character "${ch}" is not allowed. Use only ${getValidChars(base)}${base > 10 ? '' : ''}.`,
        };
      }
    }

    return { ok: true, value: upper, negative: value.startsWith('-') };
  }

  /* ============================================================
     CONVERSION (manual, base 2–36)
     ============================================================ */
  function toDecimal(str, base) {
    let result = 0n;
    const bigBase = BigInt(base);
    for (const ch of str) {
      const code = ch.charCodeAt(0);
      const digit = code >= 48 && code <= 57 ? code - 48 : code - 55;
      result = result * bigBase + BigInt(digit);
    }
    return result;
  }

  function fromDecimal(num, base) {
    if (num === 0n) return '0';
    const digits = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const bigBase = BigInt(base);
    let out = '';
    while (num > 0n) {
      out = digits[Number(num % bigBase)] + out;
      num = num / bigBase;
    }
    return out;
  }

  function convert(raw, fromBase, toBase) {
    const check = validateInput(raw, fromBase);
    if (!check.ok) return { ok: false, ...check };

    const dec = toDecimal(check.value, fromBase);
    const out = fromDecimal(dec, toBase);
    const finalOut = check.negative ? '-' + out : out;

    return {
      ok: true,
      input: check.value,
      negative: check.negative,
      fromBase,
      toBase,
      decimal: check.negative ? -dec : dec,
      output: finalOut,
    };
  }

  /* ============================================================
     UI UPDATES
     ============================================================ */
  function updateBaseUI() {
    fromInfo.textContent = `${getBaseName(state.fromBase)} (${state.fromBase})`;
    validChars.textContent = 'Allowed: ' + getValidChars(state.fromBase);
    inputField.placeholder = state.fromBase === 16
      ? 'e.g. 2A, FF, 1B4'
      : state.fromBase === 2
      ? 'e.g. 1010'
      : `e.g. ${state.fromBase === 10 ? '42' : '17'}`;

    document.querySelectorAll('#fromChips .chip').forEach(chip => {
      chip.classList.toggle('active', parseInt(chip.dataset.base) === state.fromBase);
    });

    if ([2, 8, 10, 16].includes(state.fromBase)) {
      customFrom.value = '';
    } else {
      customFrom.value = state.fromBase;
    }
  }

  function setMessage(text, type = 'hint') {
    messageEl.textContent = text;
    messageEl.className = 'msg ' + type;
  }

  function showResult(data) {
    resultEl.classList.add('show');
    resultLabel.textContent = `Result in ${getBaseName(data.toBase)} (base ${data.toBase})`;
    resultValue.textContent = data.output;
    resultMeta.innerHTML = `
      <div>From: <b>${getBaseName(data.fromBase)}</b></div>
      <div>To: <b>${getBaseName(data.toBase)}</b></div>
      <div>Decimal: <b>${data.decimal.toString()}</b></div>
      <div>Digits: <b>${data.output.replace('-', '').length}</b></div>
    `;
  }

  function hideResult() {
    resultEl.classList.remove('show');
  }

  /* ============================================================
     TOAST
     ============================================================ */
  let toastTimer = null;
  function showToast(text, type = 'success') {
    toastEl.textContent = text;
    toastEl.className = 'toast show ' + type;
    clearTimeout(toastTimer);
    toastTimer = setTimeout(() => {
      toastEl.className = 'toast ' + type;
    }, 1800);
  }

  /* ============================================================
     MAIN CONVERT ACTION
     ============================================================ */
  function doConvert(silent = false) {
    const raw = inputField.value;
    const fromBase = state.fromBase;
    // Default convert to decimal unless user picks "toBase"
    const toBase = state.toBase || (fromBase === 10 ? 2 : 10);

    const res = convert(raw, fromBase, toBase);

    if (!res.ok) {
      inputField.classList.add('invalid');
      setMessage(res.msg, 'error');
      state.lastError = res.msg;
      hideResult();
      if (!silent) showToast(res.msg, 'error');
      return;
    }

    inputField.classList.remove('invalid');
    setMessage(`✓ Valid ${getBaseName(fromBase)} number`, 'success');
    state.result = res;
    state.lastError = null;
    showResult(res);

    if (!silent) showToast('Converted successfully!', 'success');
  }

  /* ============================================================
     HISTORY
     ============================================================ */
  function loadHistory() {
    try {
      const raw = localStorage.getItem(STORAGE.history);
      state.history = raw ? JSON.parse(raw) : [];
    } catch (e) {
      state.history = [];
    }
    renderHistory();
  }

  function saveHistory() {
    try {
      localStorage.setItem(STORAGE.history, JSON.stringify(state.history.slice(0, 50)));
    } catch (e) {}
  }

  function addToHistory(entry) {
    state.history.unshift(entry);
    state.history = state.history.slice(0, 50);
    saveHistory();
    renderHistory();
  }

  function renderHistory() {
    historyCount.textContent = state.history.length;
    historyList.innerHTML = '';

    if (state.history.length === 0) {
      historyEmpty.style.display = 'block';
      historyList.style.display = 'none';
      return;
    }

    historyEmpty.style.display = 'none';
    historyList.style.display = 'flex';

    state.history.forEach((item, index) => {
      const li = document.createElement('li');
      li.className = 'history-item';
      li.innerHTML = `
        <div class="info">
          <div class="conversion">
            ${escapeHtml(item.input)} <span class="arrow">→</span> ${escapeHtml(item.output)}
          </div>
          <div class="timestamp">
            ${getBaseName(item.fromBase)} → ${getBaseName(item.toBase)} ·
            ${formatTime(item.time