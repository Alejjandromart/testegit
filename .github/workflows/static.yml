
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Tech Stack Map</title>
  <style>
    body {
      font-family: 'Segoe UI', Arial, sans-serif;
      margin: 0;
      background: #f7fafc;
      color: #1a202c;
    }
    .container {
      max-width: 900px;
      margin: 40px auto;
      background: #fff;
      border-radius: 18px;
      box-shadow: 0 8px 32px rgba(44, 62, 80, 0.10);
      padding: 30px 36px 36px 36px;
    }
    h1 {
      letter-spacing: 0.8px;
      font-weight: 700;
      font-size: 2.3rem;
      margin-bottom: 24px;
      color: #005099;
      text-align: center;
    }
    .stack-diagram {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .layer {
      margin: 18px 0;
      display: flex;
      align-items: center;
      position: relative;
    }
    .layer-label {
      min-width: 126px;
      font-weight: 600;
      padding: 9px 18px;
      background: #eaf3fc;
      border-radius: 14px;
      box-shadow: 0 1px 8px rgba(76, 144, 242, 0.05);
      margin-right: 22px;
      text-align: right;
      font-size: 1.15rem;
      color: #2260a4;
    }
    .tech-list {
      display: flex;
      gap: 14px;
      flex-wrap: wrap;
    }
    .tech-item {
      background: #fff;
      color: #006887;
      border: 1.6px solid #c8e1fa;
      border-radius: 12px;
      padding: 8px 18px;
      font-weight: 500;
      font-size: 1rem;
      letter-spacing: 0.05em;
      box-shadow: 0 2px 7px rgba(44, 62, 80, 0.08);
      display: flex;
      align-items: center;
      transition: background 0.18s, box-shadow 0.18s;
      cursor: pointer;
      position: relative;
    }
    .tech-item:hover {
      background: #f3fbff;
      box-shadow: 0 4px 18px rgba(1, 90, 161, 0.16);
      z-index: 5;
    }
    .diagram-arrow {
      width: 18px;
      height: 18px;
      margin: 0 12px;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      color: #2560a6;
      font-size: 1.4rem;
    }
    /* Tooltip styles */
    .tech-item[data-desc]:hover::after {
      content: attr(data-desc);
      position: absolute;
      left: 50%;
      top: 125%;
      transform: translate(-50%, 0);
      background: #1a202c;
      color: #fff;
      padding: 6px 15px;
      border-radius: 7px;
      font-size: 0.96rem;
      white-space: pre-line;
      box-shadow: 0 2px 12px rgba(44,62,80,0.20);
      min-width: 120px;
      z-index: 20;
      pointer-events: none;
    }
    @media (max-width: 700px) {
      .container { padding: 10px 1vw 18px 1vw; }
      .stack-diagram {font-size: 14px;}
      .layer-label {min-width: 96px; font-size: 0.99rem;}
      .tech-list, .layer {flex-wrap: wrap;}
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>Tech Stack Map</h1>
    <div class="stack-diagram">
      <div class="layer">
        <span class="layer-label">Frontend</span>
        <div class="tech-list">
          <span class="tech-item" data-desc="Biblioteca para interfaces reativas e SPAs.">React</span>
          <span class="tech-item" data-desc="Superset do JavaScript tipado.">TypeScript</span>
          <span class="tech-item" data-desc="Bundler ultrarrápido e moderno.">Vite</span>
          <span class="tech-item" data-desc="CSS-in-JS para estilização modular.">Styled Components</span>
        </div>
      </div>
      <div class="diagram-arrow">⬇</div>
      <div class="layer">
        <span class="layer-label">Backend</span>
        <div class="tech-list">
          <span class="tech-item" data-desc="Ambiente JavaScript para servidores.">Node.js</span>
          <span class="tech-item" data-desc="Framework minimalista para APIs HTTP.">Express</span>
          <span class="tech-item" data-desc="ORM moderno para bancos de dados.">Prisma</span>
        </div>
      </div>
      <div class="diagram-arrow">⬇</div>
      <div class="layer">
        <span class="layer-label">Banco de Dados</span>
        <div class="tech-list">
          <span class="tech-item" data-desc="Banco relacional, robusto e open source.">PostgreSQL</span>
        </div>
      </div>
      <div class="diagram-arrow">⬇</div>
      <div class="layer">
        <span class="layer-label">Infraestrutura</span>
        <div class="tech-list">
          <span class="tech-item" data-desc="Containerização e ambiente isolado.">Docker</span>
          <span class="tech-item" data-desc="Proxy reverso, load balancer e cache estático.">Nginx</span>
          <span class="tech-item" data-desc="Plataforma de nuvem e serviços escaláveis.">AWS</span>
        </div>
      </div>
      <div class="diagram-arrow">⬇</div>
      <div class="layer">
        <span class="layer-label">DevOps / CI</span>
        <div class="tech-list">
          <span class="tech-item" data-desc="CI/CD pipelines automatizados.">GitHub Actions</span>
          <span class="tech-item" data-desc="Teste de unidade para JavaScript/TypeScript.">Jest</span>
        </div>
      </div>
    </div>
    <p style="margin-top:36px; color:#7992a7; text-align:center;font-size:1.03rem;">
      Passe o mouse nos itens para ver descrições das tecnologias.
    </p>
  </div>
</body>
</html>

