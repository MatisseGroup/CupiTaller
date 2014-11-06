function consultarEstadisticasSemana(realizar){
    var formData;
    if(realizar)
      formData = $("#formSemana").serializeFormJSON();

    $.ajax({
      url: '/CupiTaller.web/webresources/Sesion/estadistica',
      data: formData,
      type: 'GET',
      contentType: 'application/json',
      success: function(data){
        var myDiv = $('#grafica');
        var titulo = 'Resultados última semana';
        if(realizar){
          titulo = 'Resultados Semana ' + formData.semanaAnual;
        }
        var labs = data.label;;
        var series=[];
        series[0] = {name: data.name, data:data.value};
        drawTable(myDiv, titulo, 'Estado', 'Cantidad',labs,series);
      }
    });
}

function compararSemanas(realizar){
   var formData = $("#formComparar").serializeFormJSON();
   $.ajax({
      url: '/CupiTaller.web/webresources/Sesion/compararSemanas',
      data: formData,
      type: 'GET',
      contentType: 'application/json',
      success: function(data){
        var myDiv = $('#graficaComparar');
        var titulo = 'Resultados último mes';
        if(realizar){
          titulo = 'Resultados Semanas ' + formData.semanas[0];
          for(var i=1;i<formData.semanas.length;i++){
            titulo+=', ' + formData.semanas[i];
          }
        }
        var labs = data[0].label;;
        var series=[];
        for(var i=0;i<data.length;i++){
          if(data[i].label.length > labs.length){
            labs = data[i].label;          }
          series[i] = {name: data[i].name, data:data[i].value}
        }
        drawTable(myDiv, titulo, 'Estado', 'Cantidad',labs,series);
      }
    });
}

$.fn.serializeFormJSON = function() {
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

function drawTable(div,titulo,x,y,labels,series){
    console.log("entro drawtable");
        div.highcharts({
            chart: {
            type: 'column'
        },
        title: {
            text: titulo
        },
        xAxis: {
            categories: labels,
            title: {
                text: x
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: y,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' citas'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: series
       });  
}


