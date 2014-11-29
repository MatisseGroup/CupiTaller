/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function consultarResultadoPregunta() {
    var formData;
    formData = $("#formPregunta").serializeFormJSON();
    $.ajax({
        url: '/CupiTaller.web/webresources/Resultado/resultadoPregunta',
        data: formData,
        type: 'GET',
        contentType: 'application/json',
        success: function(data) {
            var myDiv = $('#grafica');
            drawTable(myDiv, 'Resultados de pregunta ' + formData.pregunta, 'Estado', 'Cantidad', 1, data);
        }
    });
}

function configurarPreguntas(){
    $.ajax({
       url : '/CupiTaller.web/webresources/Resultado/ListarPreguntas',
       type : 'GET',
       contentType : 'application/json',
       success : function(data){
           console.log(data);
            for (var i = 0; i<data.length;i++){
                var option = document.createElement("option");
                option.innerHTML = data[i].pregunta;
//                option.text = data[i].pregunta;
                option.value = data[i].qid;
                var dropdown = document.getElementById("pregunta");
                dropdown.appendChild(option);
            }
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

function drawTable(div, titulo, x, y, series, data) {
    var preg = data.pregunta;
    var pieData = [];
    var labs = data["opciones"];
    var vals = data["valores"];
    var suma = 0;
    for (var i = 0; i < vals.length; i++){
        suma += vals[i];
    }
    for(var i = 0;i<vals.length;i++){
        pieData[i] = [labs[i], vals[i]/suma * 100];
    }
    div.highcharts({
        chart: {
            plotBackGroudCollor: null,
            plotBorderWidth: 1,
            plotShadow: false
        },
        title: {
            text: preg
        },
        tooltip: {
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
                data: pieData
            }]
    });
}




