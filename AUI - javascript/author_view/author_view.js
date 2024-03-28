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
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => addPaintingAction(event));
    infoForm.addEventListener('submit', eventt => fetchAndDisplayPaintings(eventt));
    fetchAndDisplayAuthor();
    fetchAndDisplayPaintings();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayPaintings() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPaintings(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author') + '/paintings', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display characters.
 *
 * @param {{paintings: {id: number, name:string}[]}} paintings
 */
function displayPaintings(paintings) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    paintings.paintings.forEach(painting => {
        tableBody.appendChild(createTableRow(painting));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} painting
 * @returns {HTMLTableRowElement}
 */
function createTableRow(painting) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(painting.title));
    tr.appendChild(createLinkCell('view', '../painting_view/painting_view.html?author='
        + getParameterByName('author') + '&painting=' + painting.id));
    tr.appendChild(createLinkCell('edit', '../painting_edit/painting_edit.html?author='
        + getParameterByName('author') + '&painting=' + painting.id));
    tr.appendChild(createButtonCell('delete', () => deletePainting(painting.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} painting to be deleted
 */
function deletePainting(painting) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPaintings();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + getParameterByName('author')
        + '/paintings/' + painting, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayAuthor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAuthor(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors/' + getParameterByName('author'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{id: int, nameAndSurname: string, yearOfBirth:int}} author
 */
function displayAuthor(author) {
    setTextNode('nameTitle', author.nameAndSurname.toString());
    setTextNode('id', author.id.toString());
    setTextNode('nameAndSurname', author.nameAndSurname);
    setTextNode('yearOfBirth', author.yearOfBirth.toString());
}
