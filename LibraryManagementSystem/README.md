# Library Management System

A comprehensive Library Management System created using Java Swing. This application allows for efficient management of books in a library, including adding, issuing, returning, viewing, and deleting books.

## Features

- **Add Books**: Add new books to the library with details such as title, author, genre, and quantity.
- **Issue Books**: Issue books to users by specifying the title and optional author details, along with the issued by and expected return date.
- **Return Books**: Return issued books by specifying the title and the quantity being returned.
- **View Books**: Display all books in the library in a tabular form with details like title, author, genre, quantity, issued status, issued by, issued date, and expected return date.
- **Delete Books**: Remove books from the library by specifying the title.
- **Persistent Storage**: Save the state of the library, including all book details, so that data is retained between application restarts.
- **Stylish Interface**: A bright and user-friendly interface with vibrant colors and clear fonts.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed on your machine.

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Sourav459000/Java-Projects/LibraryManagementSystem.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd LibraryManagementSystem
   ```

3. **Compile the Java Files**:
   ```bash
   javac -d bin src/LibraryManagementSystem/App.java
   ```

4. **Run the Application**:
   ```bash
   java -cp bin LibraryManagementSystem.App
   ```

## Usage

1. **Add Books**: Enter the title, author, genre, and quantity in the respective fields and click the "Add Book" button.
2. **Issue Books**: Enter the title, issued by, issue quantity, and expected return date in the respective fields and click the "Issue Book" button.
3. **Return Books**: Enter the title and return quantity in the respective fields and click the "Return Book" button.
4. **View Books**: Click the "View Books" button to display the list of all books in a tabular form.
5. **Delete Books**: Enter the title of the book to be deleted in the respective field and click the "Delete Book" button.

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request with your changes.

## MIT License

Copyright (c) 2024 Sourav Toshniwal

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.