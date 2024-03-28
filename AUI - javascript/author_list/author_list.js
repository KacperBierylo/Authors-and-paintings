import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';
import {addAuthorAction} from "../author_add/author_add.js";
window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => addAuthorAction(event));
    infoForm.addEventListener('submit', eventt => fetchAndDisplayAuthors(eventt));
    fetchAndDisplayAuthors();
});

/**
 * Fetches all authors and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayAuthors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            const obj = JSON.parse(this.response);
            let authorsNames = [];
            obj.authors.forEach(element => authorsNames.push([element.id, element.nameAndSurname]))
            displayAuthors(authorsNames)
            console.log(authorsNames)


            // console.log(this.responseText)
            // const obj = JSON.parse(this.response);
            // console.log(obj.authors[0].nameAndSurname)
            // let authorsNames = [];
            // obj.authors.forEach(element => authorsNames.push(element.nameAndSurname))
            // console.log(authorsNames)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/authors', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display authors.
 *
 * @param {string[]} authors
 */
function displayAuthors(authors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    authors.forEach(author => {
        tableBody.appendChild(createTableRow(author));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} author
 * @returns {HTMLTableRowElement}
 */
function createTableRow(author) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(author[1]));
    tr.appendChild(createLinkCell('view', '../author_view/author_view.html?author=' + author[0]));
    tr.appendChild(createLinkCell('edit', '../author_edit/author_edit.html?author=' + author[0]));
    tr.appendChild(createButtonCell('delete', () => deleteAuthor(author[0])));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } author to be deleted
 */
function deleteAuthor(author) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAuthors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/authors/' + author, true);
    xhttp.send();
}
