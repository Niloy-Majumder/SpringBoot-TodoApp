<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>
<div class="container">
	<p>${ errorMessage }</p>
	<form action="/login" method="post">
		Name: <input type="text" name="name" /> Password: <input
			type="password" name="password" /> <input type="submit">
	</form>
</div>

<%@ include file="common/footer.jspf"%>