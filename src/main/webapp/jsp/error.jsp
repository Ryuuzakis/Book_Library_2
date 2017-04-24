<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    <h1>Erreur</h1>

    L'erreur suivante s'est produite lors de l'une de vos actions :<br />

        <%
            String error = (String) request.getAttribute("error");
            %><%= error %><%
        %><br />

    <a href="/books">Retour à la liste des livres </a>
    </body>
</html>