package com.test.fileio;

import com.test.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 The BookFileReader class is responsible for reading book information from a file.
 It parses the file content and creates Book objects based on the specified format.
 Each line in the file is expected to be in the format: "book title, author, publication year".
 Invalid lines or lines that do not match the expected format are ignored.
 The class provides a method to read the file and return a list of Book objects.
 */
public class BookFileReader {

    /**
     * Reads book information from a file and returns a list of Book objects.
     *
     * @param fileName the path of the file to read from, which is looked up in resources
     * @return a list of Book objects read from the file
     * @throws IOException if an error occurs while reading the file
     */
    public List<Book> getBooksFromFile(String fileName) throws IOException {
        List<Book> booksReadFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    try {
                        String title = parts[0].trim();
                        String author = parts[1].trim();
                        int publicationYear = Integer.parseInt(parts[2].trim());

                        Book book = new Book(title, author, publicationYear);
                        booksReadFromFile.add(book);
                    } catch (NumberFormatException e) {
                        System.out.printf("Number argument in %d line is not parsable. Whole line be ignored%n", lineNumber);
                    }
                } else {
                    System.out.printf("%d line does not match the format for parsing a book. Whole line be ignored%n",lineNumber);
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.printf("Error occurred while reading from file %s. Message: %s%n", fileName, e.getMessage());
            throw e;
        }
        return booksReadFromFile;
    }
}
