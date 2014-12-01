/* ========================================================================
 * Copyright 014 Matisse
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 Matisse
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 * ========================================================================
 
 
 Source generated by CrudMaker version 1.0.0.201410152247*/
define(['controller/_sesionController', 'delegate/sesionDelegate'], function () {
    App.Controller.SesionController = App.Controller._SesionController.extend({
        cargarGrafica : function(data){
            var max = 0;
            for (var i = 0; i < data.datasets[0].data.length; i++) {
                max = max < data.datasets[0].data[i]?data.datasets[0].data[i]:max;
            };
            console.log(data);
            if(window.myBar == undefined){
                 var ctx = document.getElementById("canvas").getContext("2d");
                 var steps = 5;
                 window.myBar = new Chart(ctx).Bar(data, {
                       scaleOverride: true,
                       scaleSteps: steps,
                       scaleStepWidth: Math.ceil(max/steps),
                       scaleStartValue: 0,
                       showTooltips: true,
                       tooltipEvents: ["mousemove", "touchstart", "touchmove"],
                       barDatasetSpacing : 5,
                       barValueSpacing : 30,
                       scaleLineWidth: 5,
                       responsive: true
                 });
            }
            else{
              window.myBar.removeData();
              window.myBar.update();  
              window.myBar.addData(data.datasets[0].data,data.labels);
            }
        },
        buscarSesionesPorSemana: function(callback,context){
            var self = this;
            var model = $('#' + this.componentId + '-sesionForm').serializeObject();
            this.currentModel.set(model);
            var delegate = new App.Delegate.SesionDelegate();
            delegate.darSesionesPorSemana(self.currentModel,function (data){
                self.currentList.reset(data.records);
                callback.call(context, {data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.length});
            },function (data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'sesion-search', view: self, id: '', data: data, error: 'Error in sesion search'});
            });
        },
        buscarSesionesPorMonitor: function(callback,context){
            var self = this;
            var model = $('#' + this.componentId + '-sesionForm').serializeObject();
            this.currentModel.set(model);
            this.currentModel.validate();
            var delegate = new App.Delegate.SesionDelegate();
            delegate.darSesionesPorMonitor(self.currentModel,function (data){
                self.currentList.reset(data.records);
                callback.call(context, {data: self.currentList, page: 1, pages: 1, totalRecords: self.currentList.length});
            },function (data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'sesion-search', view: self, id: '', data: data, error: 'Error in sesion search'});
            });
        },

        postInit: function(){
            var self=this;
            Backbone.on(this.componentId + '-toolbar-print', function(params) {
                self.print();
            });
        },
        print: function(){
            window.open("/CupiTaller.web/webresources/Sesion/report","_blank")
        }
        
    });
    return App.Controller.SesionController;
}); 