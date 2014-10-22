define(['component/_preguntaMasterComponent'],function(_PreguntaMasterComponent) {
    App.Component.PreguntaMasterComponent = _PreguntaMasterComponent.extend({
		postInit: function(){
			//Escribir en este servicio las instrucciones que desea ejecutar al inicializar el componente
		}
    });

    return App.Component.PreguntaMasterComponent;
});