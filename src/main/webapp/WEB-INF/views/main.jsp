<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="base/Header.jsp"/>
<c:set var="base_url" value="${pageContext.request.contextPath}"/>
<spring:url value="/resources/img/" var="img" />
<spring:url value="/resources/js/Gmap.js" var="GmapJs" />
<script type="text/javascript">
    var base_url = '${base_url}';
    var img_url = '${img}';
    var distanciaKM = '${distanciaKM}';
    var regimen = 'Todos';
</script><!--global para Gmap.js-->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script src="${GmapJs}"></script>
<script type="text/javascript">
    $(function () {
        $('select').material_select();
    });
    function submitChange() {
        distanciaKM = $("#radio_search").val();
        regimen =$( "#regimen option:selected" ).text();
        deleteAllMarksAndCircle();
        getLocationAndLoadMarks();
    }
</script>

<div class="main">
    <div class="row">
        <div class="input-field col s3">
            <select id="regimen" onchange="submitChange();">
                <option value="" selected>Todos</option>
                <option value="">Publico</option>
                <option value="">Privado Subvencionado</option>
                <option value="">Privado No Subvencionado</option>
            </select>
            <label>Regimen</label>
        </div>
    </div>
    <div class="col s6">
        <div class="col s3" style="position: absolute;top: 160px;margin-left: 30px;z-index:5;">
            <form action="#">
                <p class="range-field">
                    <input type="range" name="points" min="0.1" max="4.0" step="0.10" value="1.0" id="radio_search" onchange="submitChange();">
                </p>
            </form>
        </div>
        <div class="col s12">
            <div class="table-responsive" style="overflow:auto; overflow-y:hidden; margin: 0 auto; withe-space: nowrap">
                <div id="googleMap" class="col s12"style="width:1300px;height:550px;margin-top: 10px;margin-bottom: 10px;margin-left: 15px;margin-right: 25px;"></div>
            </div>  
        </div>
    </div>
</div>
<jsp:include page="base/Footer.jsp"/>