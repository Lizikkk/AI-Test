const addBookForm = document.getElementById('addBookForm');
const removeBookForm = document.getElementById('removeBookForm');
const bookList = document.getElementById('bookList');

addBookForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;
    addBook(title, author);
    document.getElementById('title').value = '';
    document.getElementById('author').value = '';
});

removeBookForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const title = document.getElementById('removeTitle').value;
    const author = document.getElementById('removeAuthor').value;
    removeBook(title, author);
    document.getElementById('removeTitle').value = '';
    document.getElementById('removeAuthor').value = '';
});

function addBook(title, author) {
    const bookElement = document.createElement('li');
    bookElement.textContent = `${title} by ${author}`;
    bookList.appendChild(bookElement);
}

function removeBook(title, author) {
    const bookElements = bookList.getElementsByTagName('li');
    for (let i = 0; i < bookElements.length; i++) {
        const bookElement = bookElements[i];
        const bookText = bookElement.textContent;
        if (bookText.includes(title) && bookText.includes(author)) {
            bookList.removeChild(bookElement);
            break;
        }
    }
}