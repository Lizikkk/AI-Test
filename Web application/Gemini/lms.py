from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

books = []  # List to store book information (title, author)

# Function to add a book
def add_book(title, author):
  books.append({"title": title, "author": author})

# Function to remove a book by title
def remove_book(title):
  for i, book in enumerate(books):
    if book["title"] == title:
      books.pop(i)
      return True
  return False

# Route for adding a book (redirects to avoid form resubmission)
@app.route("/add_book", methods=["POST"])
def handle_add_book():
  title = request.form["title"]
  author = request.form["author"]
  add_book(title, author)
  return redirect(url_for("index"))  # Redirect to main page

# Route for removing a book (redirects to avoid form resubmission)
@app.route("/remove_book/<title>", methods=["POST"])
def handle_remove_book(title):
  if remove_book(title):
    message = f"Book '{title}' removed successfully!"
  else:
    message = f"Book '{title}' not found!"
  return redirect(url_for("index", message=message))  # Redirect with message

# Route for the main page
@app.route("/")
def index():
  return render_template("index.html", books=books)

if __name__ == "__main__":
  app.run(debug=True)
