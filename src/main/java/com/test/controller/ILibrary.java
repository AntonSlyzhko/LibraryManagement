package com.test.controller;

import com.test.model.Book;

import java.util.List;

/**
 * The ILibrary interface represents a library and provides operations for managing books.
 */
public interface ILibrary {

    /**
     * Adds a single book to the library.
     *
     * @param book the book to be added
     * @return true if the book was successfully added, false otherwise
     */
    boolean addBook(Book book);

    /**
     * Adds a list of books to the library.
     *
     * @param books the list of books to be added
     * @return true if all books were successfully added, false otherwise
     */
    boolean addAllBooks(List<Book> books);

    /**
     * Removes a single book from the library.
     *
     * @param book the book to be removed
     * @return true if the book was successfully removed, false otherwise
     */
    boolean removeBook(Book book);

    /**
     * Removes a list of books from the library.
     *
     * @param books the list of books to be removed
     * @return true if all books were successfully removed, false otherwise
     */
    boolean removeAllBooks(List<Book> books);

    /**
     * Searches for books written by a specific author.
     *
     * @param author the author's name to search for
     * @return a list of books written by the specified author
     */
    List<Book> searchByAuthor(String author);

    /**
     * Searches for books with a specific title.
     *
     * @param title the title to search for
     * @return a list of books with the specified title
     */
    List<Book> searchByTitle(String title);

    /**
     * Displays all the books in the library.
     */
    void displayBooks();

    /**
     * Returns the total count of books in the library.
     *
     * @return the count of books in the library
     */
    int countBooks();

    /**
     * Checks if the library is empty.
     *
     * @return true if the library has no books, false otherwise
     */
    boolean isEmpty();
}



