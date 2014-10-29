define([], function() {
    App.Model._ResultadoMasterModel = Backbone.Model.extend({
     	initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('resultado-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ResultadoModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.resultadoEntity,options);
            }
        }
    });

    App.Model._ResultadoMasterList = Backbone.Collection.extend({
        model: App.Model._ResultadoMasterModel,
        initialize: function() {
        }

    });
    return App.Model._ResultadoMasterModel;
    
});