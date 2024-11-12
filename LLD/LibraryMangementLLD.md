## Que 1. Design a simple library management system that allows users to borrow and retur books Entities/Relationships Functions/classes/edge cases database schema Rest API endpoint.

To design a **Library Management System** that allows users to **borrow and return books**, we will define entities, relationships, functions, edge cases, and database schema, followed by REST API endpoints.

### Entities & Relationships

1. **User**: Represents a library user.
   - A **user** can borrow multiple **books**.
   
2. **Book**: Represents a book in the library.
   - A **book** can be borrowed by many **users** over time but only by one user at a time.
   
3. **Transaction (Borrow/Return)**: Represents a record of borrowing or returning a book.
   - A **transaction** is created when a **user** borrows or returns a **book**.

### Key Functions:
- **Borrow a book**: A user can borrow a book if it is available.
- **Return a book**: A user can return a borrowed book.
- **View borrowed books**: View the books currently borrowed by a user.
- **View book availability**: Check if a book is available for borrowing.

### Edge Cases:
- A user tries to borrow a book that is not available (already borrowed by another user).
- A user tries to return a book they haven't borrowed.
- A user reaches the maximum limit of books they can borrow.
- Trying to return a book that is not recorded in the system.

### Class Design (Java)

#### 1. **User.java**
```java
public class User {
    private int id;
    private String name;
    private String email;
    private int maxBooksAllowed = 5; // Maximum books a user can borrow

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }
}
```

#### 2. **Book.java**
```java
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Available by default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
```

#### 3. **Transaction.java**
```java
import java.util.Date;

public class Transaction {
    private int id;
    private User user;
    private Book book;
    private Date borrowDate;
    private Date returnDate;

    public Transaction(int id, User user, Book book, Date borrowDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    // Set return date when a book is returned
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    // Getters
    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
```

#### 4. **LibraryService.java**
```java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryService {
    private List<Book> books;
    private List<Transaction> transactions;

    public LibraryService() {
        books = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    // Method to add books to library
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to borrow a book
    public boolean borrowBook(User user, Book book) {
        if (book.isAvailable() && getUserBorrowedBooksCount(user) < user.getMaxBooksAllowed()) {
            Transaction transaction = new Transaction(transactions.size() + 1, user, book, new Date());
            transactions.add(transaction);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    // Method to return a book
    public boolean returnBook(User user, Book book) {
        for (Transaction transaction : transactions) {
            if (transaction.getUser().getId() == user.getId() && transaction.getBook().getId() == book.getId() && transaction.getReturnDate() == null) {
                transaction.setReturnDate(new Date());
                book.setAvailable(true);
                return true;
            }
        }
        return false; // Book not found in user's borrowed list
    }

    // View user's borrowed books
    public List<Book> getUserBorrowedBooks(User user) {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getUser().getId() == user.getId() && transaction.getReturnDate() == null) {
                borrowedBooks.add(transaction.getBook());
            }
        }
        return borrowedBooks;
    }

    // Count borrowed books by user
    private int getUserBorrowedBooksCount(User user) {
        return getUserBorrowedBooks(user).size();
    }

    // Check book availability
    public boolean isBookAvailable(Book book) {
        return book.isAvailable();
    }
}
```

### Database Schema:

#### 1. **User Table**
```sql
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100)
);
```

#### 2. **Book Table**
```sql
CREATE TABLE Book (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    is_available BOOLEAN
);
```

#### 3. **Transaction Table**
```sql
CREATE TABLE Transaction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (book_id) REFERENCES Book(id)
);
```

### REST API Endpoints

1. **POST /api/books**: Add a new book to the library.
   - **Request Body**: `{ "title": "Book Title", "author": "Author Name" }`
   - **Response**: `201 Created`

2. **POST /api/users**: Register a new user.
   - **Request Body**: `{ "name": "User Name", "email": "user@example.com" }`
   - **Response**: `201 Created`

3. **POST /api/borrow**: Borrow a book.
   - **Request Body**: `{ "userId": 1, "bookId": 1 }`
   - **Response**: `200 OK` or `400 Bad Request` (if book unavailable or user exceeds borrow limit)

4. **POST /api/return**: Return a book.
   - **Request Body**: `{ "userId": 1, "bookId": 1 }`
   - **Response**: `200 OK` or `400 Bad Request` (if book was not borrowed by this user)

5. **GET /api/users/{userId}/borrowed**: Get the list of books borrowed by a user.
   - **Response**: `200 OK`, e.g., `[{ "id": 1, "title": "Book Title", "author": "Author Name" }]`

6. **GET /api/books/{bookId}/availability**: Check if a book is available for borrowing.
   - **Response**: `200 OK`, e.g., `{ "available": true }`

### Example Flow:

1. A user registers using the **POST /api/users** endpoint.
2. The user borrows a book using **POST /api/borrow**.
3. The user returns a book using **POST /api/return**.
4. The user can view the list of currently borrowed books using **GET /api/users/{userId}/borrowed**.

This implementation captures the basic functionalities of a library system. You can expand it with features like book reservations, overdue penalties, and more complex user roles (e.g., librarians).