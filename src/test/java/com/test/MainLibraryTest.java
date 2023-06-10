package com.test;

import com.test.controller.MainLibrary;
import com.test.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static com.test.LibraryManagementApplication.NAME_OF_FILE_WITH_BOOKS;
import static org.junit.jupiter.api.Assertions.*;

public class MainLibraryTest {
    MainLibrary library;
    static List<Book> validBooks;
    static List<Book> invalidBooks;
    static Map<String, List<Book>> validBooksByAuthorMap;
    static Map<String, List<Book>> invalidBooksByAuthorMap;
    static Map<String, List<Book>> validBooksByTitleMap;
    static Map<String, List<Book>> invalidBooksByTitleMap;

    @BeforeAll
    static void beforeAllSetup() {
        try {
            List<Book> books = LibraryManagementApplication.getBooksFromFile(NAME_OF_FILE_WITH_BOOKS);
            validBooks = books;
            invalidBooks = new ArrayList<>();
            Random random = new Random();
            for (Book book : books.subList(0, 5)) {

                Book invalidBook = new Book(
                        book.getTitle().substring(random.nextInt(book.getTitle().length())) + " sdfsdfs",
                        book.getAuthor().substring(random.nextInt(book.getAuthor().length())) + " sd213123fd",
                        book.getPublicationYear() + random.nextInt()
                );
                invalidBooks.add(invalidBook);
            }
            validBooksByAuthorMap = validBooks.stream().collect(Collectors.groupingBy(Book::getAuthor));
            invalidBooksByAuthorMap = invalidBooks.stream().collect(Collectors.groupingBy(Book::getAuthor));
            validBooksByTitleMap = validBooks.stream().collect(Collectors.groupingBy(Book::getTitle));
            invalidBooksByTitleMap = invalidBooks.stream().collect(Collectors.groupingBy(Book::getTitle));
        } catch (IOException e) {
            Assertions.fail("Failed to retrieve books from file: " + e.getMessage());
        }
    }

    @BeforeEach
    void beforeEachSetup() {
        library = new MainLibrary();
    }

    @Test
    void testInsertingOneBook() {
        assertTrue(library.addBook(validBooks.get(0)), "Book was not inserted into library.");
        assertFalse(library.isEmpty(), "Library don not have to be empty.");
        assertEquals(1, library.countBooks(), "Library have to contain one book.");
    }

    @Test
    void testInsertingAllBooks() {
        assertTrue(library.addAllBooks(validBooks), "Not all books were inserted into library.");
        assertFalse(library.isEmpty(), "Library don not have to be empty.");
        assertEquals(validBooks.size(), library.countBooks(), "Library have to contain " + validBooks.size() + " books.");
    }

    @Test
    void testRemoveOneBook() {
        library.addAllBooks(validBooks);
        assertTrue(library.removeBook(validBooks.get(0)), "Book was not removed from the library.");
        assertFalse(library.isEmpty(), "Library don not have to be empty.");
        assertEquals(validBooks.size() - 1, library.countBooks(), "Library have to contain " + (validBooks.size() - 1) + " books.");
    }

    @Test
    void testRemoveAllBooks() {
        library.addAllBooks(validBooks);
        assertTrue(library.removeAllBooks(validBooks), "Not all books were removed from the library.");
        assertTrue(library.isEmpty(), "Library have to be empty.");
        assertEquals(0, library.countBooks(), "Library have to be empty.");
    }

    @Test
    void testAddNullBookThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            library.addBook(null)
        , "addBook have to throw NullPointerException if null value was passed");
    }

    @Test
    void testAddNullBooksListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                        library.addAllBooks(null)
                , "addAllBooks have to throw NullPointerException if null value was passed");
    }

    @Test
    void testRemoveNullBookThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                        library.removeBook(null)
                , "removeBook have to throw NullPointerException if null value was passed");
    }

    @Test
    void testRemoveNullBooksListThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                        library.removeAllBooks(null)
                , "removeAllBooks have to throw NullPointerException if null value was passed");
    }
    
    @Test
    void testSearchByValidAuthor() {
        library.addAllBooks(validBooks);
        String xAuthor = validBooksByAuthorMap.keySet().iterator().next();
        List<Book> booksOfXAuthor = validBooksByAuthorMap.get(xAuthor);
        List<Book> booksOfXAuthorFromLibrary = library.searchByAuthor(xAuthor);
        assertEquals(booksOfXAuthor.size(), booksOfXAuthorFromLibrary.size(), "The same number of books have to be retrieved.");
        for (Book book : booksOfXAuthor) {
            assertTrue(booksOfXAuthorFromLibrary.contains(book), "Library have to contain " + book.toString());
        }
    }

    @Test
    void testSearchByInvalidAuthor() {
        library.addAllBooks(validBooks);
        String xAuthor = invalidBooksByAuthorMap.keySet().iterator().next();
        List<Book> booksOfXAuthorFromLibrary = library.searchByAuthor(xAuthor);
        assertEquals(0, booksOfXAuthorFromLibrary.size(), "Library do not have to contain books written by " + xAuthor);
    }

    @Test
    void testSearchByValidTitle() {
        library.addAllBooks(validBooks);
        String xTitle = validBooksByTitleMap.keySet().iterator().next();
        List<Book> booksOfXTitle = validBooksByTitleMap.get(xTitle);
        List<Book> booksOfXTitleFromLibrary = library.searchByTitle(xTitle);
        assertEquals(booksOfXTitle.size(), booksOfXTitleFromLibrary.size(), "The same number of books have to be retrieved.");
        for (Book book : booksOfXTitle) {
            assertTrue(booksOfXTitleFromLibrary.contains(book), "Library have to contain " + book.toString());
        }
    }

    @Test
    void testSearchByInvalidTitle() {
        library.addAllBooks(validBooks);
        String xTitle = validBooksByTitleMap.keySet().iterator().next();
        List<Book> booksOfXTitleFromLibrary = library.searchByAuthor(xTitle);
        assertEquals(0, booksOfXTitleFromLibrary.size(), "Library do not have to contain books with title " + xTitle);
    }
}
