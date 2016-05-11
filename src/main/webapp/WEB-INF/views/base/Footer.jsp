<%-- 
    Document   : Footer
    Created on : 24/03/2016, 14:29:18
    Author     : Mariano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="typography" value="thin"/>
<!DOCTYPE html>
<!--<div class="fixed-action-btn horizontal click-to-toggle" style="bottom: 45px; right: 24px;">
    <a class="btn-floating btn-large red">
      <i class="material-icons">menu</i>
    </a>
    <ul>
      <li><a class="btn-floating blue darken-1" id="btn_list"><i class="material-icons">insert_chart</i></a></li>
      <li><a class="btn-floating green darken-1" id="btn_insert"><i class="material-icons">attach_file</i></a></li>
    </ul>
  </div>-->
</main>
</body>
<footer class="page-footer indigo">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text ${typography}">
                    </h5>
                    <p class="grey-text text-lighten-4 ${typography}"></p>
                </div>
                <div class="col l3 s12">
                    <h5 class="white-text ${typography}"></h5>
                <ul>
                    <li><a class="white-text ${typography}" href="#!"></a></li>
                        </br>
                        
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright" style="height: 40px;"><p class="brown-text text-lighten-5 right-align ${typography}" style="margin-right: 1%;">© 2016 Ledesma, Lopez, Meza, Nuñez, Silva</p></div>
</footer>
</html>
<style>

    body {
        display: flex;
        min-height: 100vh;
        flex-direction: column;
    }

    main {
        flex: 1 0 auto;
    }
</style>