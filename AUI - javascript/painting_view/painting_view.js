import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';
import {addPaintingAction} from "../painting_add/painting_add.js";
window.addEventListener('load', () => {
    fetchAndDisplayAuthor();
});


function fetchAndDisplayAuthor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthor(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/paintings/' + getParameterByName('painting'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{Id:int, title: string, authorId: int, year:int}} painting
 */
function displayAuthor(painting) {
    setTextNode('Id', getParameterByName('painting'));
    setTextNode('authorId', getParameterByName('author'));
    setTextNode('title', painting.title);
    setTextNode('year', painting.year.toString());
}
