<%@page import="car.tp4.entity.OrderEntry"%>
<%@page import="car.tp4.entity.BookOrder"%>
<%@page import="car.tp4.entity.Stock"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h2>Books stock</h2>
        <% BookOrder order = (BookOrder) request.getAttribute("order"); %>
		<div>
			<% for(OrderEntry entry : order.getOrderEntries()) { %>
				<p>
					<%= entry.getBook().getTitle() %> (<%= entry.getQuantity() %>)
				</p>
			<% } %>
		</div>
    </body>
</html>
