<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
<script>
$(function(){
            $.get("http://localhost:8080/openData/establecimientos/getByCueAnexo/"+${cuanexo},function(data){
                //alert(JSON.stringify(data));
                $("#datoescuela").html("<div>\n\
        <h4>" + data.localidad + "  "+ data.departamento +"</h4>\n\
                      <h5> Regimen: " + data.regimen + "</h5>\n\
                <h5> Jurisdiccion:  " + data.jurisdiccion + "</h5></div>");
                    
                  $("#nombre").html("<h3>" + data.nombre+ "</h3>");
                 
                
            });
});


      




</script>

<div id="asd"><h1> </div>


<!--<<div>
<button id="botonlogin" onclick="Login()" >Logearse con Facebook</button>
<button id="botonlogin" onclick="Logout()" >Cerrar sesion</button>
</div>-->


<div class="row">
        <div class="col s12 m6">
          <div class="card blue-grey darken-1">
            <div class="card-content white-text">
              <span class="card-title" id="nombre"></span>
              <p id="datoescuela"></p>
            </div>            
          </div>
        </div>
      </div>






<!--//comentarios-->
<div class="fb-comments"  data-numposts="5"></div>

<jsp:include page="base/Footer.jsp"/>
