<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
	    <% String title = request.getParameter("title"); %>
	    <% String author = request.getParameter("author"); %>
	    <% String year = request.getParameter("year"); %>
	    <h2>Récapitulatif des informations </h2>
        	
        <b>Titre: </b> <%= title %>
        <br/>
        <b>Auteur: </b> <%= author %>
        <br/>
        <b>Année de parution: </b> <%= year %>
        <br/>
    </body>
</html>