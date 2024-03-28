import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayPainting();
});

function fetchAndDisplayPainting() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/paintings/' + getParameterByName('painting'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayPainting();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/paintings/' + getParameterByName('painting'), true);

    const request = {
        'title': document.getElementById('title').value,
        'year': parseInt(document.getElementById('year').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.replace('../author_view/author_view.html?author='+getParameterByName('author'))
}

