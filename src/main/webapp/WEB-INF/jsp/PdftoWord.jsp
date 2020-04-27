<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PDF to Word </title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1 class="header">PDF to Word Conversion tool</h1>
<a href="/" class="home home1" role="button">Back to Home</a>
<hr>
<form method="POST" action="pdftowordupload" enctype="multipart/form-data">

<div class="container">

	<label for="choosefile" class="lbl" >Select the PDF File: </label>
	<input type="file" id="file" name="file" accept=".pdf" required><br><br>
	


			<!-- <label for="savefile" class="lbl" >Destination Path with Filename: </label>
			 <input type="text" class="txt" placeholder="Eg: S:\Workspace_D\Common\PDF to Word\{yourFolder}\filename.docx" name="savefile" required> -->
		
</div> 
<br><br>

		 
		<div class="buttondiv"> <button type="submit" id="clear" class="button button1">Convert</button></div>
 </form>
 
 
 
</body>
</html>