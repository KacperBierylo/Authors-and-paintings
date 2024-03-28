import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


export function addPaintingAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/paintings', true);

    const request = {
        'title': document.getElementById('title').value,
        'year': parseInt(document.getElementById('year').value),
        'author': parseInt(getParameterByName('author'))
    };
    console.log(request);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    //console.log("test")
    //console.log(JSON.stringify(request))
    xhttp.send(JSON.stringify(request));
    location.reload()
}
