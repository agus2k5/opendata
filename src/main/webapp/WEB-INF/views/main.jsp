<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="base/Header.jsp"/>
<c:set var="base_url" value="${pageContext.request.contextPath}"/>
<spring:url value="/resources/img/" var="img" />
<spring:url value="/resources/js/Gmap.js" var="GmapJs" />
<script type="text/javascript">var base_url = '${base_url}';
    var img_url = '${img}';
    var distanciaKM = '${distanciaKM}';</script><!--global para Gmap.js-->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script src="${GmapJs}"></script>
<script type="text/javascript">
    function changeRadio() {
        distanciaKM = $("#radio_search").val();
        deleteAllMarksAndCircle();
        getLocationAndLoadMarks();
    }
</script>

<div class="main">
    <div class="col s6">
        <div class="col s3" style="position: absolute;top: 80px;margin-left: 30px;z-index:5;">
            <form action="#">
                <p class="range-field">
                    <input type="range" name="points" min="0.1" max="4.0" step="0.10" value="1.0" id="radio_search" onchange="changeRadio()">
                </p>
            </form>
        </div>
        <!--<div class="col s3" style="position: absolute;top: 140px;left: 30%;z-index:5;">
            <button style="margin-top: 30px;" class="btn waves-effect waves-light indigo" id="btn_enviar">
                <i class="material-icons right">search</i>
            </button>
        </div>-->
        <div class="col s12">
            <div class="table-responsive" style="overflow:auto; overflow-y:hidden; margin: 0 auto; withe-space: nowrap">
                <div id="googleMap" class="col s12"style="width:1300px;height:550px;margin-top: 10px;margin-bottom: 10px;margin-left: 15px;margin-right: 25px;"></div>
            </div>  
        </div>
    </div>
</div>
<jsp:include page="base/Footer.jsp"/>