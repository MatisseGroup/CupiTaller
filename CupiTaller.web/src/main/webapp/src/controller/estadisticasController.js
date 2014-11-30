$(document).ready(function() {  
	$('#pickerSencillo').datepick({
		renderer: $.datepick.weekOfYearRenderer, 
		firstDay: 1, 
		showOtherMonths: true, 
		rangeSelect: true, 
		onShow: $.datepick.selectWeek,
		onDate: $.datepick.noWeekends
	});
	$('#pickerMultiple').datepick({
		renderer: $.datepick.weekOfYearRenderer, 
		firstDay: 1, 
		showOtherMonths: true, 
		rangeSelect: true, 
		onShow: $.datepick.selectWeek,
		onDate: $.datepick.noWeekends,
		onClose: agregarAComparar
	});
	$('#pickerMonitor').datepick({
		renderer: $.datepick.weekOfYearRenderer, 
		firstDay: 1, 
		showOtherMonths: true, 
		rangeSelect: true, 
		onShow: $.datepick.selectWeek,
		onDate: $.datepick.noWeekends
	});
	$('#formSemana').submit(function(e){
		e.preventDefault();
		var formData=$("#formSemana").serializeFormJSON();
		var split=formData["fechas"].split("-");
		formData["inicial"] = jQuery.trim(split[0]);
		formData["final"] = jQuery.trim(split[1]);
		$.ajax({
			url: '/CupiTaller.web/webresources/Sesion/estadistica',
			data: formData,
			type: 'GET',
			contentType: 'application/json',
			success: function(data){
				var myDiv = $('#grafica');
				var titulo = 'Resultados de última fecha';
				if(formData["fechas"]!=='' && data.name !==null){
					titulo = formData["inicial"]!==formData["final"]?'Resultados de ' + formData["inicial"] + ' a ' + formData["final"]:'Resultados de ' + formData["inicial"];
				}
				var labs = data.label;
				var series=[];
				series[0] = {name: data.name, data:data.value};
				drawTable(myDiv, titulo, 'Estado', 'Cantidad',labs,series);
			}
		});
	});

	$('#formComparar').submit(function(e){
		e.preventDefault();
		var semanas = {"semanas":serializarOpciones()};
		$.ajax({
  			url: '/CupiTaller.web/webresources/Sesion/compararFechas',
  			data: semanas,
  			type: 'GET',
  			contentType: 'application/json',
  			success: function(data){
				var myDiv = $('#graficaComparar');
	  			var titulo = 'Resultado de fechas seleccionadas';
				var labs = data[0].label;;
				var series=[];
				for(var i=0;i<data.length;i++){
	  				if(data[i].label.length > labs.length){
						labs = data[i].label;          
					}
					series[i] = {name: data[i].name, data:data[i].value}
				}
				drawTable(myDiv, titulo, 'Estado', 'Cantidad',labs,series);
			}
		});
	});

	$('#formMonitor').submit(function(e){
		e.preventDefault();
		var formData = $("#formMonitor").serializeFormJSON();
		var split=formData["fechas"].split("-");
		formData["inicial"] = jQuery.trim(split[0]);
		formData["final"] = jQuery.trim(split[1]);
		var nombre = $('#opcionesMonitor option:selected').text();
		$.ajax({
			url: '/CupiTaller.web/webresources/Sesion/sesionesMonitor',
			data: formData,
			type: 'GET',
			contentType: 'application/json',
			success: function(data){
				var myDiv = $('#graficaMonitor');
				var titulo = 'Sesiones de ' + nombre + ' desde ' + formData["inicial"] +' a ' + formData["final"];
				var labs = data.label;;
				var series=[];
				series[0] = {name: data.name, data:data.value};
				drawTable(myDiv, titulo, 'Estado', 'Cantidad',labs,series);
			}
		});
	});

	$.fn.serializeFormJSON = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} 
			else {
				o[this.name] = this.value || '';
			 }
		});
		return o;
	};

	document.getElementById('compararOpciones').ondblclick = function(){
		this.remove(this.selectedIndex);
	};

	$('#formSemana').submit();
	configurarMonitores();
});


function agregarAComparar(){
	$('#pickerMultiple').blur();
	var formData = $("#formComparar").serializeFormJSON();
	var split=formData["fechas"].split("-");
	var inic = jQuery.trim(split[0]);
	var fin = jQuery.trim(split[1]);
	var select = document.getElementById("compararOpciones");
	var agregar = formData["fechas"]!=='';
	for (i = 0; i < select.options.length && agregar; i++) {
  		agregar = select.options[i].value !== formData["fechas"];
	}
	if(agregar){
		var option = document.createElement("option");
		option.text = inic===fin?inic:formData["fechas"];
		option.value = formData["fechas"];
		select.appendChild(option); 
	}
}

function configurarMonitores(){
 	$.ajax({
  		url: '/CupiTaller.web/webresources/Monitor',
  		type: 'GET',
  		data: {"page":1,"maxRecords":100},
  		contentType: 'application/json',
  		success: function(data){
			for(var i = 0; i<data["records"].length;i++){
		 		var option = document.createElement("option");
		  		option.text = data["records"][i]["name"];
		  		option.value = data["records"][i]["id"];
		  		var select = document.getElementById("opcionesMonitor");
		  		select.appendChild(option); 
  			}
		}
	});
}

function serializarOpciones(){
	var data=[];
	var select = document.getElementById('compararOpciones');
	for (i = 0; i < select.options.length; i++){
		var split=select.options[i].value.split("-");
		var inic = jQuery.trim(split[0]);
		var fin = jQuery.trim(split[1]);
		data[i] = {"inicial":inic,"fin":fin};
	}
	return data;
}

function drawTable(div,titulo,x,y,labels,series){
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