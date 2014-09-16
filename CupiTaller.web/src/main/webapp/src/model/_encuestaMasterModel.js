define([], function() {
    App.Model._EncuestaMasterModel = Backbone.Model.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('encuesta-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.EncuestaModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.encuestaEntity,options);
            }
        }
    });

    App.Model._EncuestaMasterList = Backbone.Collection.extend({
        model: App.Model._EncuestaMasterModel,
        initialize: function() {
        }

    });
    return App.Model._EncuestaMasterModel;
    
});