<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>General Project</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1>Hello ${time}</h1>
	<form:form action="${pageContext.request.contextPath}/login" method="post" commandName="loginForm">
		<p>Username : <form:input type="text" path="username" /></p>
		<p>Password : <form:input type="password" path="password" /></p>
		<button>Gönder</button>
	</form:form>
</body>
</html>