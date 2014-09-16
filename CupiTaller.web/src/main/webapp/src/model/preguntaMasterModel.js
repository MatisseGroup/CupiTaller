define(['model/_preguntaMasterModel'], function() { 
    App.Model.PreguntaMasterModel = App.Model._PreguntaMasterModel.extend({

    });

    App.Model.PreguntaMasterList = App.Model._PreguntaMasterList.extend({
        model: App.Model.PreguntaMasterModel
    });

    return  App.Model.PreguntaMasterModel;

});