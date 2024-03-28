import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


export function addAuthorAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/authors/' , true);

    const request = {
        'nameAndSurname': document.getElementById('nameAndSurname').value,
        'yearOfBirth': parseInt(document.getElementById('yearOfBirth').value),
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log("test")
    console.log(JSON.stringify(request))
    xhttp.send(JSON.stringify(request));
    location.reload()
}


