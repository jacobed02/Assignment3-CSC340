<#import "layout.ftlh" as layout>
<@layout.page title=(editing?then("Edit Character", "Character Request"))>
    <section class="form-panel">
        <h1>${editing?then("Edit Character", "Request a New Character")}</h1>
        <p>Enter the character's name, universe, species, image URL, and short description.</p>

        <#if errors?has_content>
            <div class="form-errors" role="alert">
                <p>Please correct the following:</p>
                <ul>
                    <#list errors as error>
                        <li>${error!}</li>
                    </#list>
                </ul>
            </div>
        </#if>

        <form class="character-form" action="${formAction}" method="post">
            <label for="name">Name</label>
            <input id="name" name="name" type="text" value="${character.name!}" required maxlength="255">

            <label for="universe">Universe</label>
            <input id="universe" name="universe" type="text" value="${character.universe!}" required maxlength="255">

            <label for="species">Species</label>
            <input id="species" name="species" type="text" value="${character.species!}" required maxlength="255">

            <label for="imageUrl">Image URL</label>
            <input id="imageUrl" name="imageUrl" type="url" value="${character.imageUrl!}" maxlength="2048"
                   placeholder="https://example.com/character.jpg">

            <label for="description">Short Description</label>
            <textarea id="description" name="description" rows="6" maxlength="2000" required>${character.description!}</textarea>

            <div class="page-actions">
                <button class="button" type="submit">${editing?then("Save changes", "Submit Character")}</button>
                <a class="button button-secondary" href="/">Cancel</a>
            </div>
        </form>
    </section>
</@layout.page>