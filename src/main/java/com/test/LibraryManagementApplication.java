package com.test;

import com.test.controller.ILibrary;
import com.test.controller.MainLibrary;
import com.test.fileio.BookFileReader;
import com.test.model.Book;

import java.io.IOException;
import java.util.List;

public class LibraryManagementApplication {
    static final String NAME_OF_FILE_WITH_BOOKS = "/books.txt";

    public static void main( String[] args ) {
        try {
            System.out.println("Instantiating library object.");
            ILibrary library = new MainLibrary();
            System.out.println("Printing books that library contains:");
            library.displayBooks();
            System.out.println("Reading list of books from the file.");
            List<Book> books = getBooksFromFile(NAME_OF_FILE_WITH_BOOKS);
            System.out.println("Adding these books to the library.");
            library.addAllBooks(books);
            System.out.println("Printing books that library contains:");
            library.displayBooks();
            System.out.println("Removing first 5 books from the library");
            library.removeAllBooks(books.subList(0, 5));
            System.out.println("Printing books that library contains:");
            library.displayBooks();
            String author = "Tolkien";
            System.out.println("Searching books by author: " + author);
            System.out.println(library.searchByAuthor(author));
            String title = "Gatsby";
            System.out.println("Searching books by title: " + title);
            System.out.println(library.searchByTitle(title));

        } catch (IOException e) {
            System.out.printf("Can not proceed with running a program. Cause: %s", e.getMessage());
        }
    }
    
    public static List<Book> getBooksFromFile(String filePath) throws IOException {
        BookFileReader fileReader = new BookFileReader();
        return fileReader.getBooksFromFile(filePath);
    }
}
