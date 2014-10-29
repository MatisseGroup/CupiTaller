define(['model/_resultadoMasterModel'], function() { 
    App.Model.ResultadoMasterModel = App.Model._ResultadoMasterModel.extend({

    });

    App.Model.ResultadoMasterList = App.Model._ResultadoMasterList.extend({
        model: App.Model.ResultadoMasterModel
    });

    return  App.Model.ResultadoMasterModel;

});