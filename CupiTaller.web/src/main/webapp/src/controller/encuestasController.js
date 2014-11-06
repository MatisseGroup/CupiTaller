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
    console.log(data);
    var preg = data.pregunta;
    var pieData = [];
     var labs = ['muy de acuerdo','de acuerdo', 'en desacuerdo', 'muy en desacuerdo'];
        var vals = [60.0, 40.0, 0.0, 0.0];
//        for(var i = 0; i<data.length;i++){
//            labs[i] = data[i].label;
//            vals[i] = data[i].value;
//        }
        for(var i = 0; i <5;i++){
            pieData[i] = [labs[i],vals[i]];
        }
        div.highcharts({
        chart: {
            plotBackGroudCollor : null,
            plotBorderWidth : 1,
            plotShadow : false
        },
        title: {
            text: preg
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
            name: 'resultados',
            data: [
                ['muy de acuerdo',50.0],
                ['de acuerdo', 30.0],
                ['en desacuerdo',10.0],
                ['muy en desacuerdo', 10.0]
            ]
        }]
    });  
}




