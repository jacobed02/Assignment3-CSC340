<#import "layout.ftlh" as layout>
<@layout.page title="${character.name} Details">
    <article class="details-card">
        <h1>${character.name} Details</h1>
        <aside class="character-sidebar">
            <h2>${character.name}</h2>
            <#if character.imageUrl?? && character.imageUrl?has_content>
                <#assign detailsImage = character.imageUrl>
            <#else>
                <#assign detailsImage = "/images/placeholder.svg">
            </#if>
            <img class="details-image" src="${detailsImage}" alt="${character.name}">
            <p>Character ID: ${character.characterId?c}</p>
        </aside>
        <section class="character-info">
            <h2>About ${character.name}</h2>
            <p class="character-description">${character.description}</p>
            <p>Universe: ${character.universe}</p>
            <p>Species: ${character.species}</p>
            <p>Image URL: <#if character.imageUrl?? && character.imageUrl?has_content><a href="${character.imageUrl}">${character.imageUrl}</a><#else>Not provided</#if></p>
        </section>
        <div class="page-actions">
            <a class="button" href="/characters/${character.characterId?c}/edit">Edit character</a>
            <form action="/characters/${character.characterId?c}/delete" method="post"
                  onsubmit="return confirm('Delete this character?');">
                <button class="button button-danger" type="submit">Delete character</button>
            </form>
            <a class="button button-secondary" href="/">Back to gallery</a>
        </div>
    </article>
</@layout.page>