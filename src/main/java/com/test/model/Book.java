package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a book with its title, author, and publication year.
 *
 * <p>
 * This class provides a convenient way to encapsulate the data of a book.
 * It automatically generates getter and setter methods for the fields,
 * as well as constructors for creating instances of the class.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 *     // Create a new book instance
 *     Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
 *
 *     // Get the title of the book
 *     String title = book.getTitle();
 *
 *     // Set the author of the book
 *     book.setAuthor("Jane Austen");
 * </pre>
 * </p>
 *
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int publicationYear;
}
