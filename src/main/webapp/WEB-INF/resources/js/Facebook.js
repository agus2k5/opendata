var estado;
var nombre;

window.fbAsyncInit = function() {
    FB.init({
      appId      : '1052935038119722', // Set YOUR APP ID
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });
 
    FB.Event.subscribe('auth.authResponseChange', function(response) 
    {
     if (response.status === 'connected') 
    {
       // document.getElementById("message").innerHTML +=  "<br>Connected to Facebook";
        console.log(response.status);
        //SUCCESS
 
    }    
    else if (response.status === 'not_authorized') 
    {console.log(response.status);
        //document.getElementById("message").innerHTML +=  "<br>Failed to Connect";
 
        //FAILED
    } else 
    {
        //document.getElementById("message").innerHTML +=  "<br>Logged Out";
 console.log(response.status);
        //UNKNOWN ERROR
    }
    }); 
 
    };

 
 var log=Login;
    function Login()
    {
 
        FB.login(function(response) {
            console.log(response);
           if (response.authResponse) 
           {
                getUserInfo();
            } else 
            {
             console.log('User cancelled login or did not fully authorize.');
            }
         },{scope: 'email,user_photos,user_videos'});
 
    }
 
 function checklogin(){
     //alert('assd');
      FB.getLoginStatus(function(response) {
        if (response.status === 'connected') {
            console.log(nombre);
          document.getElementById("cerrarSesion").disabled="false";
        }});
//     var str="<b>Name</b> : "+response.name+"<br>";
//           str +="<b>id: </b>"+response.id+"<br>";
//           str +="<input type='button' value='Get Photo' onclick='getPhoto();'/>";
//          str +="<input type='button' value='Logout' onclick='Logout();'/>";
//          document.getElementById("status").innerHTML=str;
//     
 }
 
  function getUserInfo() {
        FB.api('/me', function(response) {
 
      var str="<b>Name</b> : "+response.name+"<br>";
      nombre=response.name;
          str +="<b>Link: </b>"+response.link+"<br>";
          str +="<b>Username:</b> "+response.username+"<br>";
          str +="<b>id: </b>"+response.id+"<br>";
          str +="<b>Email:</b> "+response.email+"<br>";
          str +="<input type='button' value='Get Photo' onclick='getPhoto();'/>";
          str +="<input type='button' value='Logout' onclick='Logout();'/>";
          document.getElementById("status").innerHTML=str;
          //console.log(str);
    });
    }
    function getPhoto()
    {
      FB.api('/me/picture?type=normal', function(response) {
 
          var str="<br/><b>Pic</b> : <img src='"+response.data.url+"'/>";
          document.getElementById("status").innerHTML+=str;
 
    });
 
    }
    
        
    function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      estado =
        'Thanks for logging in, ' + response.name + '!';
    });
    FB.getLoginStatus(function(response) {
        if (response.status === 'connected') {
          console.log(response);
          console.log('conectado');
        } else if (response.status === 'not_authorized') {
          console.log('not connected to app');
        } else {
          console.log('not logged in to fb');
        }});
    return function(){
        alert(estado);
    };
    
  }
    
    function Logout()
    {
        FB.logout(function(){document.location.reload();});
    }
 
  // Load the SDK asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
   
