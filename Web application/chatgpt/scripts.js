document.getElementById('bookForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const bookTitle = document.getElementById('bookTitle').value;
    const bookAuthor = document.getElementById('bookAuthor').value;

    addBook(bookTitle, bookAuthor);
    clearForm();
});

function addBook(title, author) {
    const bookList = document.getElementById('bookList');

    const listItem = document.createElement('li');
    listItem.textContent = `${title} by ${author}`;
    
    const removeButton = document.createElement('button');
    removeButton.textContent = 'Remove';
    removeButton.addEventListener('click', function() {
        bookList.removeChild(listItem);
    });

    listItem.appendChild(removeButton);
    bookList.appendChild(listItem);
}

function clearForm() {
    document.getElementById('bookTitle').value = '';
    document.getElementById('bookAuthor').value = '';
}
