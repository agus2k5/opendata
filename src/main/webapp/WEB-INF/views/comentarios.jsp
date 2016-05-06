<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="base/Header.jsp"/>
<c:set var="base_url" value="${pageContext.request.contextPath}"/>
<spring:url value="/resources/img/" var="img" />
<spring:url value="/resources/js/Facebook.js" var="FacebookJs" />
<script src="${FacebookJs}"></script>
 <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.6&appId=1052935038119722";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div>
<button id="botonlogin" onclick="Login()" >Logearse con Facebook</button>
<button id="botonlogin" onclick="Logout()" >Cerrar sesion</button>
</div>








<!--//comentarios-->
<div class="fb-comments"  data-numposts="5"></div>

<jsp:include page="base/Footer.jsp"/>
