function consultarEstadisticasSemana(){
        var formData = $("#formSemana").serializeFormJSON();
        $.ajax({
          url: '/CupiTaller.web/webresources/Sesion/estadistica',
          data: formData,
          type: 'GET',
          contentType: 'application/json',
          success: function(data){
            var myDiv = $('#grafica');
            drawTable(myDiv,'Resultados Semana ' + formData.semanaAnual, 'Estado', 'Cantidad', 1 , data);
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

function drawTable(div,titulo,x,y,series,data){
    console.log("entro drawtable");
     var labs = [];
        var vals = [];
        for(var i = 0; i<data.length;i++){
            labs[i] = data[i].label;
            vals[i] = data[i].value;
        }
        div.highcharts({
            chart: {
            type: 'column'
        },
        title: {
            text: titulo
        },
        xAxis: {
            categories: labs,
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
        series: [{
            name: 'Cantidad',
            data: vals
        }]
       });  
}


