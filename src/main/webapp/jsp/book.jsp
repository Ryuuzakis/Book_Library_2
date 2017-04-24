<%@page import="car.tp4.entity.OrderEntry"%>
<%@page import="car.tp4.entity.BookOrder"%>
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h2>Ajouter un livre</h2>
        <form action="create" method="GET">
            <input type="submit" value="Créer un nouveau livre"/>
        </form>

        <h2>Existing books</h2>
        <%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");

            for (Book book: books) {
            	%>
            	<p>
                	Author: <%= book.getAuthor() %><br />
                	Title: 	<%= book.getTitle() %><br />
                	Year: 	<%= book.getYear() %><br />
                	<form action="basket" method="POST">
                		<input type="hidden" value="<%= book.getId() %>" name="bookId" />
                		<input type="hidden" value="1" name="quantity" />
                		<input type="submit" value="Ajouter au panier" />
                	</form>
                </p>
                <%
            }
        %>

        <a href="/books?order=asc">Trier par année de parution croissante</a> <br />
        <a href="/books?order=desc">Trier par année de parution décroissante</a>

        <h2>Basket</h2>
        <%
            BookOrder basket = (BookOrder) request.getAttribute("basket");

            for (OrderEntry entry: basket.getOrderEntries()) {
            	%>
            	<p>
                	Title: 	<%= entry.getBook().getTitle() %>
                	Quantity: <%= entry.getQuantity() %>
                	<form action="basket" method="POST">
                		<input type="hidden" value="<%= entry.getBook().getId() %>" name="bookId" />
                		<label></label><input type="number" value="<%= entry.getQuantity() %>" name="quantity" />
                		<input type="submit" value="valider" />
                	</form>
                </p>
                <%
            }
        %>
        <form action="order" method="POST">
       		<input type="submit" value="Valider le panier" />
        </form>
    </body>
</html>
