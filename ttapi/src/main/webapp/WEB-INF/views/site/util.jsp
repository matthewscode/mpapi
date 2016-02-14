<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.16/angular.min.js"></script>
<script src="<c:url value="/resources/js/ttapp.js" />"></script> 


</head>
<body ng-app="ttApp">
<div ng-controller="utilController">
	<input type='text' ng-model="checksum"/><br/>
		<button ng-click="submitImageChecksum('<c:url value="/api/ic/create/" />' + checksum)" > submit </button>
		<br/><br/>
		file translation<br/>
		url: <input type='text' ng-model="originUrl" />
		checksum:<input type='text' ng-model="ftChecksum"/><br/>
		<button ng-click="submitFileTranslation('<c:url value="/api/ft/create/" />' + ftChecksum + '/', originUrl)" > derp </button>
</div>
</body>
</html>