<#import "layout.ftlh" as layout>
<@layout.page title="Character Gallery">
    <section class="page-heading">
        <h1>The Characters of SpongeBob SquarePants</h1>
    </section>

    <form class="search-form" action="/" method="get">
        <label>
            Search...
            <input type="search" name="name" value="${name!}" placeholder="Search...">
        </label>
        <label>
            Universe
            <input type="search" name="universe" value="${universe!}" placeholder="Filter by universe">
        </label>
        <button type="submit">Search</button>
        <a class="button button-secondary" href="/">Clear</a>
    </form>

    <section aria-label="Characters">
        <#if characters?has_content>
            <div class="character-grid">
                <#list characters as character>
                    <article class="character-card">
                        <#if character.imageUrl?? && character.imageUrl?has_content>
                            <#assign cardImage = character.imageUrl>
                        <#else>
                            <#assign cardImage = "/images/placeholder.svg">
                        </#if>
                        <a href="/characters/${character.characterId?c}">
                            <img class="character-image" src="${cardImage}" alt="${character.name}">
                        </a>
                        <div class="character-card-heading">
                            <span class="character-species">${character.species}</span>
                            <h2><a href="/characters/${character.characterId?c}">${character.name}</a></h2>
                        </div>
                        <p class="character-universe">${character.universe}</p>
                        <p>${character.description?truncate(160)}</p>
                        <a class="text-link" href="/characters/${character.characterId?c}">View details &rarr;</a>
                    </article>
                </#list>
            </div>
        <#else>
            <div class="empty-state">
                <h2>No characters found</h2>
                <p>Try a different search, or add a new character to the gallery.</p>
                <a class="button" href="/characters/new">Add a character</a>
            </div>
        </#if>
    </section>
</@layout.page>
