<#import "layout.ftlh" as layout>
<@layout.page title="Character not found | Character Gallery">
    <section class="empty-state">
        <p class="eyebrow">404 - Not found</p>
        <h1>That character wandered off!</h1>
        <p>${errorMessage!"The character you requested could not be found."}</p>
        <a class="button" href="/">Return to the gallery</a>
    </section>
</@layout.page>
