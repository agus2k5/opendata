<%-- 
    Document   : Header
    Created on : 24/03/2016, 14:15:13
    Author     : Mariano
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base_url" value="${pageContext.request.contextPath}"/><!--path base-->
<c:set var="typography" value="thin"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <title>Asdqwezxc</title>
        <!-- CSS  -->
        <spring:url value="/resources/font-awesome/css/font-awesome.css" var="fontAwesomeCss"/>
        <link rel="stylesheet" type="text/css" href="${fontAwesomeCss}" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <spring:url value="/resources/materialize/css/materialize.css" var="materialize" />
        <link rel="stylesheet" type="text/css" href="${materialize}" media="screen,projection">
        <spring:url value="/resources/materialize/css/style.css" var="materializeExtra" />
        <link rel="stylesheet" type="text/css" href="${materializeExtra}">
        <!--JScripts-->
        <spring:url value="/resources/js/jquery-2.1.4.js" var="jqueryJs" />
        <script src="${jqueryJs}"></script>
        <spring:url value="/resources/materialize/js/materialize.js" var="materializeJs" />
        <script src="${materializeJs}"></script>
        <!-- DATA TABLE SCRIPTS -->
        <spring:url value="/resources/js/jquery.dataTables.js" var="dataTablesJs" />
        <spring:url value="/resources/css/jquery.dataTables.css" var="dataTablesCss" />
        <spring:url value="/resources/css/dataTables.bootstrap.css" var="dataTablesCssB" />
        <script src="${dataTablesJs}"></script>
        <link rel="stylesheet" type="text/css" href="${dataTablesCss}">
        <link rel="stylesheet" type="text/css" href="${dataTablesCssB}">
        <style type="text/css">nav ul a, nav .brand-logo {color: #FFFFFF;}</style>
    <nav class="indigo" role="navigation">
        <div class="nav-wrapper">
            <a href="${base_url}"><img id="logo-container" class="brand-logo" style="height: 64px;" src=""></img>Logo</a>
            <ul class="right hide-on-med-and-down">
                <li><a class="white-text ${typography}" href="">1</a></li>
                <li><a class="white-text ${typography}" href="">2</a></li>
                <li><a class="white-text ${typography}" href="">3</a></li>
                <li><a class="white-text ${typography}" href="">4</a></li>
            </ul>
            <ul id="nav-mobile" class="side-nav indigo">
                <li><a class="white-text ${typography}" href="">1</a></li>
                <li><a class="white-text ${typography}" href="">2</a></li>
                <li><a class="white-text ${typography}" href="">3</a></li>
                <li><a class="white-text ${typography}" href="">4</a></li>
            </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
        </div>
    </nav>
</head>
<main>
    <body>

