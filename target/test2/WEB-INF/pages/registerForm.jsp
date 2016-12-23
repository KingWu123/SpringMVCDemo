<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet">
  </head>
  <body>
    <h1>Register</h1>

    <%--<form method="POST">--%>
      <%--First Name: <input type="text" name="firstName" /><br/>--%>
      <%--Last Name: <input type="text" name="lastName" /><br/>--%>
      <%--Email: <input type="email" name="email" /><br/>--%>
      <%--Username: <input type="text" name="username" /><br/>--%>
      <%--Password: <input type="password" name="password" /><br/>--%>
      <%--<input type="submit" value="Register" />--%>
    <%--</form>--%>
    <sf:form method="post" commandName="spitter" enctype="multipart/form-data">
      First Name: <sf:input path="firstName"/><br/>
        <sf:errors path="firstName"/><br/>
      Last Name: <sf:input path="lastName"/><br/>
      Email: <sf:input path="email" type="email"/><br/>
      UserName: <sf:input path="username"/><br/>
      Password: <sf:password path="password"/><br/>

      <label>profile picture</label>:<input type="file" name="profilePicture" accept="image/jpeg, image/png, image/gif"/><br/>

      <input type="submit" value="Register">
    </sf:form>
  </body>
</html>
