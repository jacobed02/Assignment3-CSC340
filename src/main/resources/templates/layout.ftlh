<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <header class="site-header">
        <a href="/" class="site-title">Spongebob Squarepants Character Gallery</a>
        <nav class="site-nav" aria-label="Main navigation">
            <a href="/">Home</a>
            <a href="/about">About</a>
            <a href="/characters/new">Add a Character</a>
        </nav>
    </header>
    <#if message??>
        <p class="flash-message" role="status">${message}</p>
    </#if>
    <main class="page-content">
        <#nested>
    </main>
</body>
</html>
</#macro>
