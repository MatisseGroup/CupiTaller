define(['controller/selectionController', 'model/cacheModel', 'model/preguntaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/preguntaComponent',
 'component/respuestaComponent'],
 function(SelectionController, CacheModel, PreguntaMasterModel, CRUDComponent, TabController, PreguntaComponent,
 respuestaComponent) {
    App.Component._PreguntaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('preguntaMaster');
            App.Model.PreguntaMasterModel.prototype.urlRoot = this.configuration.context;
            this.componentId = App.Utils.randomInteger();
            
            this.masterComponent = new PreguntaComponent();
            this.masterComponent.initialize();
            
            this.childComponents = [];
			
			this.initializeChildComponents();
            
            Backbone.on(this.masterComponent.componentId + '-post-pregunta-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-post-pregunta-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(this.masterComponent.componentId + '-pre-pregunta-list', function() {
                self.hideChilds();
            });
            Backbone.on('pregunta-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'pregunta-master-save', view: self, message: error});
            });
            Backbone.on(this.masterComponent.componentId + '-instead-pregunta-save', function(params) {
                self.model.set('preguntaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }

				App.Utils.fillCacheList(
					'respuesta',
					self.model,
					self.respuestaComponent.getDeletedRecords(),
					self.respuestaComponent.getUpdatedRecords(),
					self.respuestaComponent.getCreatedRecords()
				);

                self.model.save({}, {
                    success: function() {
                        Backbone.trigger(self.masterComponent.componentId + '-' + 'post-pregunta-save', {view: self, model : self.model});
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'pregunta-master-save', view: self, error: error});
                    }
                });
			    if (this.postInit) {
					this.postInit();
				}
            });
        },
        render: function(domElementId){
			if (domElementId) {
				var rootElementId = $("#"+domElementId);
				this.masterElement = this.componentId + "-master";
				this.tabsElement = this.componentId + "-tabs";

				rootElementId.append("<div id='" + this.masterElement + "'></div>");
				rootElementId.append("<div id='" + this.tabsElement + "'></div>");
			}
			this.masterComponent.render(this.masterElement);
		},
		initializeChildComponents: function () {
			this.tabModel = new App.Model.TabModel({tabs: [
                {label: "Respuesta", name: "respuesta", enable: true}
			]});
			this.tabs = new TabController({model: this.tabModel});

			this.respuestaComponent = new respuestaComponent();
            this.respuestaComponent.initialize({cache: {data: [], mode: "memory"},pagination: false});
			this.childComponents.push(this.respuestaComponent);

            var self = this;
            
            this.configToolbar(this.respuestaComponent,false);
            this.respuestaComponent.disableEdit();

            Backbone.on(this.respuestaComponent.componentId + '-toolbar-add', function() {
                var selection = new SelectionController({"componentId":"respuestaComponent"});
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
            	self.respuestaComponent.addRecords(models);
            	self.respuestaComponent.render();
            });

		},
        renderChilds: function(params) {
            var self = this;
            
            var options = {
                success: function() {
                	self.tabs.render(self.tabsElement);

					self.respuestaComponent.clearCache();
					self.respuestaComponent.setRecords(self.model.get('listrespuesta'));
					self.respuestaComponent.render(self.tabs.getTabHtmlId('respuesta'));

                    $('#'+self.tabsElement).show();
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
        showMaster: function (flag) {
			if (typeof (flag) === "boolean") {
				if (flag) {
					$("#"+this.masterElement).show();
				} else {
					$("#"+this.masterElement).hide();
				}
			}
		},
        hideChilds: function() {
            $("#"+this.tabsElement).hide();
        },
		configToolbar: function(component, composite) {
		    component.removeGlobalAction('refresh');
			component.removeGlobalAction('print');
			component.removeGlobalAction('search');
			if (!composite) {
				component.removeGlobalAction('create');
				component.removeGlobalAction('save');
				component.removeGlobalAction('cancel');
				component.addGlobalAction({
					name: 'add',
					icon: 'glyphicon-send',
					displayName: 'Add',
					show: true
				}, function () {
					Backbone.trigger(component.componentId + '-toolbar-add');
				});
			}
        },
        getChilds: function(name){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === name) {
					return this.childComponents[idx].getRecords();
				}
			}
		},
		setChilds: function(childName,childData){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].setRecords(childData);
				}
			}
		},
		renderMaster: function(domElementId){
			this.masterComponent.render(domElementId);
		},
		renderChild: function(childName, domElementId){
			for (var idx in this.childComponents) {
				if (this.childComponents[idx].name === childName) {
					this.childComponents[idx].render(domElementId);
				}
			}
		}
    });

    return App.Component._PreguntaMasterComponent;
});