<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Anslutningsverktyg</title>
    <r:require modules="jquery,bootstrap,main-style"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <h1>Tjänsteproducent</h1>
                <!-- Insert a link here that generates a server trip that which renders a partial angular page" -->
                <li> <a href="/anslutningsverktyg/anslut">Anslut ny tjänst</a></li>
                <shiro:hasRole name="ADMINISTRATÖR">
                    <li><g:link controller="user" action="index">Användare</g:link></li>
                </shiro:hasRole>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
