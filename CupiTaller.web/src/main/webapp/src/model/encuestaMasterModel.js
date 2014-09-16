define(['model/_encuestaMasterModel'], function() { 
    App.Model.EncuestaMasterModel = App.Model._EncuestaMasterModel.extend({

    });

    App.Model.EncuestaMasterList = App.Model._EncuestaMasterList.extend({
        model: App.Model.EncuestaMasterModel
    });

    return  App.Model.EncuestaMasterModel;

});