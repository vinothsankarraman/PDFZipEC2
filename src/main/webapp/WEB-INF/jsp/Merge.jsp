<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PDF to Word </title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1 class="header">PDF Merge</h1>
<a href="/" class="home home1" role="button">Back to Home</a>
<hr>
<form method="POST" action="pdfmergenew" enctype="multipart/form-data">

<div class="container">

	<label for="choosefile" class="lbl" >Select the PDF File 1: </label>
	<input type="file"  name="file1" accept=".pdf" required><br><br>
	
	<label for="choosefile" class="lbl" >Select the PDF File 2: </label>
	<input type="file"  name="file2" accept=".pdf" required><br><br>
	
	<!-- <label for="savefile" class="lbl" >Destination Path with Filename: </label>
	<input type="text" class="txt" placeholder="Eg: S:\Workspace_D\Common\PDFMerge\{yourFolder}\filename.pdf" name="savefile" required> -->
			
		
</div> 
<br><br>
		 
		<div class="buttondiv"> <button type="submit" class="button button1">Merge</button></div>
 </form>
 
 
</body>
</html>