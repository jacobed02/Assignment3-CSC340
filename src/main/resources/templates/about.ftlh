<#import "layout.ftlh" as layout>
<@layout.page title="About Us">
    <section class="content-panel about-page">
        <h1>About This Webpage</h1>
        <p>This webpage is a character guide for SpongeBob SquarePants. It displays character information stored in the database, including each character's description, universe, and species. Open a character's details page by visiting its URL with its database ID, such as <code>/characters/2</code>.</p>
        <img class="about-image" src="/images/bikini-bottom.svg" alt="An illustration of an underwater town">
        <p>Each page request is handled by a Spring MVC controller. The controller asks the service layer for character data, then passes that data to a FreeMarker template to render the page. The shared layout keeps the navigation consistent.</p>
        <p>The service layer contains the application logic and uses a repository to read and write characters in PostgreSQL. The REST API and the HTML pages both call that same service, so the browser interface works alongside the Assignment 3 API without making HTTP calls to it.</p>
    </section>
</@layout.page>