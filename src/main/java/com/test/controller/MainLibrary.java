package com.test.controller;

import com.test.utils.StringUtils;
import com.test.model.Book;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 The MainLibrary class represents a library implementation that stores and manages books.
 It implements the ILibrary interface.
 */
public class MainLibrary implements ILibrary {
    private static final String LIBRARY_IS_EMPTY_MESSAGE = "Library is empty.";
    private static final String LIBRARY_CONTAINS_THESE_BOOKS_MESSAGE = "Library contains these books:";
    private final Set<Book> books = new HashSet<>();

    /**
     Adds a book to the library.
     @param book the book to add
     @return true if the book was successfully added, false otherwise
     */
    @Override
    public boolean addBook(Book book) {
        Objects.requireNonNull(book);
        return books.add(book);
    }

    /**
     Adds multiple books to the library.
     @param books the list of books to add
     @return true if all the books were successfully added, false otherwise
     */
    @Override
    public boolean addAllBooks(List<Book> books) {
        Objects.requireNonNull(books);
        return this.books.addAll(books);
    }

    /**
     Removes a book from the library.
     @param book the book to remove
     @return true if the book was successfully removed, false otherwise
     */
    @Override
    public boolean removeBook(Book book) {
        Objects.requireNonNull(book);
        return books.remove(book);
    }

    /**
     Removes multiple books from the library.
     @param books the list of books to remove
     @return true if all the books were successfully removed, false otherwise
     */
    @Override
    public boolean removeAllBooks(List<Book> books) {
        Objects.requireNonNull(books);
        return this.books.removeAll(books);
    }

    /**
     Searches for books written by a specific author.
     @param author the author's name to search for
     @return a list of books written by the specified author
     */
    @Override
    public List<Book> searchByAuthor(String author) {
        Objects.requireNonNull(author);
        return searchUsingPredicate(book -> StringUtils.containsIgnoreCase(book.getAuthor(), author));
    }

    /**
     Searches for books with a specific title.
     @param title the title to search for
     @return a list of books with the specified title
     */
    @Override
    public List<Book> searchByTitle(String title) {
        Objects.requireNonNull(title);
        return searchUsingPredicate(book -> StringUtils.containsIgnoreCase(book.getTitle(), title));
    }

    /**
     Searches for books based on a custom predicate.
     @param bookPredicate the predicate to filter the books
     @return a list of books that satisfy the specified predicate
     */
    public List<Book> searchUsingPredicate(Predicate<Book> bookPredicate) {
        return books.stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());
    }

    /**
     Displays all the books in the library.
     If the library is empty, it prints a corresponding message.
     */
    @Override
    public void displayBooks() {
        if (isEmpty()) {
            System.out.println(LIBRARY_IS_EMPTY_MESSAGE);
        } else {
            System.out.println(LIBRARY_CONTAINS_THESE_BOOKS_MESSAGE);
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    /**
     Counts the number of books in the library.
     @return the total number of books in the library
     */
    @Override
    public int countBooks() {
        return books.size();
    }

    /**
     Checks if the library is empty.
     @return true if the library has no books, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return books.isEmpty();
    }
}
