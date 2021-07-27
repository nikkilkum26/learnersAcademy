<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<style>
.container{
width: 30%;
    height: 30%;
    border-color: antiquewhite;
    border-style: solid;
    padding: 50px;
}
    
</style>
<body>
<div align ="center">
<br/>
<h1>Learners Academy </h1>
<br/><br/><br/><br/><br/>
<h2>Admin Login</h2>

<div class="container">
<form action ="verifylogin" method="post">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">User Name</label>
    <input  type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
  </div>
  <div class="mb-3 form-check">
    <button type="submit" class="btn btn-primary">Submit</button>
  </div>
  
</form>
</div>

 

</div>
</body>
</html>