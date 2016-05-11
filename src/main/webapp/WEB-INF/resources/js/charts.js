
// Load the Visualization API and the piechart package.
    google.charts.load('current', {'packages':['corechart']});
      
    // Set a callback to run when the Google Visualization API is loaded.
    
      //localidad, departamento, latitud,  longitud, distanciaKM, regimen
    function drawChart(localidad, departamento, latitud,  longitud, distanciaKM, regimen) {
      $.get(base_url + '/establecimientos/Count/'+ localidad +'/'+ departamento +'/'+ latitud +'/'+ longitud +'/'+ distanciaKM +'/'+ regimen,function(data){
          // Create our data table out of JSON data loaded from server.
        var jdata = new google.visualization.DataTable();
      
        jdata.addColumn('string', 'Regimen');
        jdata.addColumn('number', 'Cantidad');
       $.each(data, function(i, item) {
             console.log(i+'/'+data[i]);
             if(i!=="Total"){
                 jdata.addRow(JSON.parse( '["'+i+'",'+data[i]+']'));
             }
        });
        
        var options = {
            title:'Distribución de establecimientos según Régimen',
            width:700,
            height:500,
            is3D: true};

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(jdata, options);
          });
            
    }
    
 