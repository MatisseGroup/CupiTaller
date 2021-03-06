/* ========================================================================
   * Copyright 2014 Matisse
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
define(['model/_monitorModel'], function() {
    App.Model.MonitorModel = App.Model._MonitorModel.extend({

 		validate: function(attrs,options){
            var validationMessage = "";
            var regExp ="^[0-9]+$"; 
            if(!attrs.name){
                validationMessage = "El nombre no puede ser vacio";
            }
            else if(attrs.puntosNegativos<0){
              validationMessage="El valor de puntos negativos debe ser positivo";
            }
            else if(!attrs.puntosNegativos){
              validationMessage="El campo de puntos negativos no puede ser vacio";
            }           
            else if(!attrs.puntosNegativos.match(regExp)){
              validationMessage="Los puntos negativos deben ser numéricos y sin decimales";
            }

            else if(attrs.llegadasTarde<0){
              validationMessage="El valor de llegadas tarde debe ser positivo";
            }
            else if(!attrs.llegadasTarde){
              validationMessage="El campo de llegadas tarde no puede ser vacio";
            }           
            else if(!attrs.llegadasTarde.match(regExp)){
              validationMessage="Las llegadas tarde deben ser numéricas y sin decimales";
            }
            
            if(validationMessage.length>0){
               return validationMessage;
            }
        }

    });

    App.Model.MonitorList = App.Model._MonitorList.extend({
        model: App.Model.MonitorModel
    });

    return  App.Model.MonitorModel;

});