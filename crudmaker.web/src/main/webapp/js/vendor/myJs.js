function mostrarTemplate(view) {
    console.log("entro");
    $.ajax({
        url: 'src/view/' + view + '.html',
        method: 'GET',
        async: true,
        contentType: 'text/html',
        dataType: 'html',
        success: function(data) {
            console.log(data);
            var mydiv = $('#main');
            while ( mydiv.firstChild ) mydiv.removeChild( mydiv.firstChild );
            mydiv.append($(data));
            var arr = document.getElementById('main').getElementsByTagName('script');
            for (var n = 0; n < arr.length; n++){
                 eval(arr[n].innerHTML);
            }
        }
    });
}

function cargarComponent(view){
     loadComponents = [
            {
                    name: 'component/sesionComponent', 
                    render: true,
                    domElementId: 'main'
            }
            ];
    //mydiv = $('#main');
    //while ( mydiv.firstChild ) mydiv.removeChild( mydiv.firstChild );
    //var script = "loadComponents = [{name: 'component/" + view + "Component', render: true,domElementId: 'main'}];";
    //$('head').append(script);
    // mydiv = $('#main');
    // var myHTMLRequest = new Request.HTML(
    //     {
    //         update: mydiv,
    //         method:'get'
    //     }).get('src/view/testTemplate.html');

    // var script = document.createElement('script');
    // var id = view+'Script';
    // script.setAttribute('id',id);
    // script.appendChild(document.createTextNode("loadComponents = [{name: component/sesionComponent, render: true, domElementId: 'main'}];"));
    // document.head.appendChild(script);
    // var ex = document.getElementById(id);
    // eval(ex.innerHTML);
}

