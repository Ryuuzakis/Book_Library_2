<%@page import="car.tp4.entity.OrderEntry"%>
<%@page import="car.tp4.entity.BookOrder"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    <%     	
    	
    	BookOrder order = (BookOrder) request.getAttribute("order");
    	String error = (String) request.getAttribute("error");

    	if(error == null || error.isEmpty()) { 
    %>
    		<%= error %>
	        <h2>Commande validée !</h2>
			<div>
				<% for(OrderEntry entry : order.getOrderEntries()) { %>
					<p>
						<%= entry.getBook().getTitle() %> (<%= entry.getQuantity() %>)
					</p>
				<% } %>
			</div>
		<% 
		} else {
		%>
			<h2>Oups, une erreur est survenue...</h2>
			<div>
				<%= error %>
			</div>
		<%
		}
		%>
    </body>
</html>
