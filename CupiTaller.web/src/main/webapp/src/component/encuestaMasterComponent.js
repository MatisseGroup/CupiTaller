define(['controller/selectionController', 'model/cacheModel', 'model/encuestaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/encuestaComponent',
 'component/preguntaComponent'
 
 ],function(SelectionController, CacheModel, EncuestaMasterModel, CRUDComponent, TabController, EncuestaComponent,
 preguntasComponent
 ) {
    App.Component.EncuestaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('encuestaMaster');
            var uComponent = new EncuestaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-encuesta-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-encuesta-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-encuesta-list', function() {
                self.hideChilds();
            });
            Backbone.on('encuesta-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'encuesta-master-save', view: self, message: error});
            });
            Backbone.on(uComponent.componentId + '-instead-encuesta-save', function(params) {
                self.model.set('encuestaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var preguntasModels = self.preguntasComponent.componentController.preguntaModelList;
                self.model.set('listpreguntas', []);
                self.model.set('createpreguntas', []);
                self.model.set('updatepreguntas', []);
                self.model.set('deletepreguntas', []);
                for (var i = 0; i < preguntasModels.models.length; i++) {
                    var m =preguntasModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createpreguntas').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updatepreguntas').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < preguntasModels.deletedModels.length; i++) {
                    var m = preguntasModels.deletedModels[i];
                    self.model.get('deletepreguntas').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(uComponent.componentId + '-post-encuesta-save', self);
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'encuesta-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Preguntas", name: "preguntas", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.EncuestaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.preguntasComponent = new preguntasComponent();
                    self.preguntasModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.PreguntaModel), self.model.get('listpreguntas'));
                    self.preguntasComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.PreguntaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.PreguntaModel, App.Model.PreguntaList, self.preguntasModels)
                    });
                    self.preguntasComponent.render(self.tabs.getTabHtmlId('preguntas'));
                    Backbone.on(self.preguntasComponent.componentId + '-post-pregunta-create', function(params) {
                        params.view.currentPreguntaModel.setCacheList(params.view.preguntaModelList);
                    });
                    self.preguntasToolbarModel = self.preguntasComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.preguntasComponent.setToolbarModel(self.preguntasToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'encuesta-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.EncuestaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.EncuestaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.EncuestaMasterComponent;
});