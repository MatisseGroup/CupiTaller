define([], function() {
    App.Model._PreguntaMasterModel = Backbone.Model.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('pregunta-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.PreguntaModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.preguntaEntity,options);
            }
        }
    });

    App.Model._PreguntaMasterList = Backbone.Collection.extend({
        model: App.Model._PreguntaMasterModel,
        initialize: function() {
        }

    });
    return App.Model._PreguntaMasterModel;
    
});