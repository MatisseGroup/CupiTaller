define(['controller/selectionController', 'model/cacheModel', 'model/preguntaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/preguntaComponent',
 'component/respuestaComponent'
 
 ],function(SelectionController, CacheModel, PreguntaMasterModel, CRUDComponent, TabController, PreguntaComponent,
 respuestaComponent
 ) {
    App.Component.PreguntaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('preguntaMaster');
            var uComponent = new PreguntaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-pregunta-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-pregunta-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-pregunta-list', function() {
                self.hideChilds();
            });
            Backbone.on('pregunta-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'pregunta-master-save', view: self, message: error});
            });
            Backbone.on(uComponent.componentId + '-instead-pregunta-save', function(params) {
                self.model.set('preguntaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var respuestaModels = self.respuestaComponent.componentController.respuestaModelList;
                self.model.set('listrespuesta', []);
                self.model.set('createrespuesta', []);
                self.model.set('updaterespuesta', []);
                self.model.set('deleterespuesta', []);
                for (var i = 0; i < respuestaModels.models.length; i++) {
                    var m =respuestaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createrespuesta').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updaterespuesta').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < respuestaModels.deletedModels.length; i++) {
                    var m = respuestaModels.deletedModels[i];
                    self.model.get('deleterespuesta').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(uComponent.componentId + '-post-pregunta-save', self);
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'pregunta-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Respuesta", name: "respuesta", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.PreguntaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.respuestaComponent = new respuestaComponent();
                    self.respuestaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.RespuestaModel), self.model.get('listrespuesta'));
                    self.respuestaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.RespuestaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.RespuestaModel, App.Model.RespuestaList, self.respuestaModels)
                    });
                    self.respuestaComponent.render(self.tabs.getTabHtmlId('respuesta'));
                    Backbone.on(self.respuestaComponent.componentId + '-post-respuesta-create', function(params) {
                        params.view.currentRespuestaModel.setCacheList(params.view.respuestaModelList);
                    });
                    self.respuestaToolbarModel = self.respuestaComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.respuestaComponent.setToolbarModel(self.respuestaToolbarModel);
                	
                     
                
                    Backbone.on(self.respuestaComponent.componentId + '-toolbar-add', function() {
                        var selection = new App.Controller.SelectionController({"componentId":"respuestaComponent"});
                        App.Utils.getComponentList('respuestaComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no Respuestas to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'Respuesta List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('respuestaComponent-post-selection', function(models) {
                        var cacherespuestaModel = App.Utils.createCacheModel(App.Model.RespuestaModel);
                        models = App.Utils.convertToModel(cacherespuestaModel, models);
                        for (var i = 0; i < models.length; i++) {
                        	var model = models[i];
                        	model.setCacheList(self.respuestaComponent.componentController.respuestaModelList);
                        	model.save('',{});
                        }
                        self.respuestaComponent.componentController.showEdit=false;
                        self.respuestaComponent.componentController.list();
                        
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'pregunta-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.PreguntaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.PreguntaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.PreguntaMasterComponent;
});