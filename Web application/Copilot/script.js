// script.js
const bookList = document.getElementById('book-list');
const addBookButton = document.getElementById('add-book');
const removeBookButton = document.getElementById('remove-book');

const books = []; // Array to store book data (you can use an object for more details)

// Function to display books in the list
function displayBooks() {
    bookList.innerHTML = '';
    books.forEach((book) => {
        const bookItem = document.createElement('div');
        bookItem.textContent = book.title; // Display book title (customize as needed)
        bookList.appendChild(bookItem);
    });
}

// Event listeners for buttons
addBookButton.addEventListener('click', () => {
    const title = prompt('Enter book title:');
    if (title) {
        books.push({ title }); // Add book to the list
        displayBooks();
    }
});

removeBookButton.addEventListener('click', () => {
    const titleToRemove = prompt('Enter book title to remove:');
    const indexToRemove = books.findIndex((book) => book.title === titleToRemove);
    if (indexToRemove !== -1) {
        books.splice(indexToRemove, 1); // Remove book from the list
        displayBooks();
    }
});

// Initialize book list
displayBooks();
