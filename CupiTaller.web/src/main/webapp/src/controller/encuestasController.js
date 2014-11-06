/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function consultarResultadoPregunta(){
        var formData;
        
        
            formData = $("#formPregunta").serializeFormJSON();
        $.ajax({
          url: '/CupiTaller.web/webresources/Resultado/resultadoPregunta',
          data: formData,
          type: 'GET',
          contentType: 'application/json',
          success: function(data){
            var myDiv = $('#grafica');
            drawTable(myDiv,'Resultados de pregunta ' + formData.pregunta, 'Estado', 'Cantidad', 1 , data);
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
//    console.log(data);
     var labs = [];
        var vals = [];
        for(var i = 0; i<data.length;i++){
            labs[i] = data[i].label;
            vals[i] = data[i].value;
        }
        div.highcharts({
        chart: {
            plotBackGroudCollor : null,
            plotBorderWidth : 1,
            plotShadow : false
        },
        title: {
            text: titulo
        },
        tooltip:{
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['Firefox',   45.0],
                ['IE',       26.8],
                {
                    name: 'Chrome',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['Safari',    8.5],
                ['Opera',     6.2],
                ['Others',   0.7]
            ]
        }]
    });  
}




