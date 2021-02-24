package by.epam.tasks.first.logic.book;

import by.epam.tasks.first.bean.FileForData;
import by.epam.tasks.first.bean.book.Book;
import by.epam.tasks.first.bean.book.DatabaseBooks;
import by.epam.tasks.first.bean.book.ElectronicBook;
import by.epam.tasks.first.logic.FileForDataLogic;

import java.util.ArrayList;

public class DatabaseBooksLogic {

    public DatabaseBooks createDatabaseBooks() {
        new FileForDataLogic().createFile(new FileForData(FileForData.pathData));
        DatabaseBooks databaseBooks = new DatabaseBooks();
        loadData(databaseBooks);
        return databaseBooks;
    }

    public void addBook(DatabaseBooks databaseBooks, Book book) {
        if (book != null) {
            databaseBooks.setCurrPage(new BookLogic().addBook(
                    databaseBooks.getCatalog(),databaseBooks.getPage(),book));
            new FileForDataLogic().addToFile(databaseBooks.getFileForDatabase(),
                    new BookLogic().parseBook(book) + "\n");
        }
    }

    public void addBook(DatabaseBooks databaseBooks, ElectronicBook electronicBook) {
        if (electronicBook != null) {
            databaseBooks.setCurrPage(new BookLogic().addBook(
                    databaseBooks.getCatalog(),databaseBooks.getPage(),electronicBook));
            new FileForDataLogic().addToFile(databaseBooks.getFileForDatabase(),
                    new BookLogic().parseBook(electronicBook) + "\n");
        }
    }

    public void addElectronicBook(DatabaseBooks databaseBooks, String author, String name, int pages, String recourse) {
        addBook(databaseBooks, new ElectronicBook(author, name,recourse, pages));
    }

    public void addBook(DatabaseBooks databaseBooks, String author, String name, int pages) {
        addBook(databaseBooks, new Book(author, name, pages));
    }

    public ArrayList<Book> findBook(DatabaseBooks databaseBooks, Book bookSearch) {
        return new BookLogic().findBook(databaseBooks.getCatalog(), bookSearch);
    }

    public ArrayList<Book> findBook(DatabaseBooks databaseBooks, String author) {
        return new BookLogic().findBook(databaseBooks.getCatalog(), author);
    }

    public void print(DatabaseBooks databaseBooks) {
        new BookLogic().print(databaseBooks.getCurrPage(), databaseBooks.getCatalog());
    }

    public void print(DatabaseBooks databaseBooks, int page) {
        new BookLogic().print(page, databaseBooks.getCatalog());
    }

    public String getDescription(DatabaseBooks databaseBooks, int bookNumber) {
        return new BookLogic().getDescription(databaseBooks.getCatalog(), databaseBooks.getCurrPage(), bookNumber);
    }

    public void setDescription(DatabaseBooks databaseBooks, int bookNumber, String description) {
        new BookLogic().setDescription(databaseBooks.getCatalog(), databaseBooks.getCurrPage(), bookNumber, description);
    }

    public Book getNthBook(DatabaseBooks databaseBooks, int bookNumber) {
        return new BookLogic().getNthBook(databaseBooks.getCatalog(), databaseBooks.getCurrPage(), bookNumber);
    }

    public void deleteBookFromAnywhere(DatabaseBooks databaseBooks, Book book) {
        new BookLogic().removeAll(databaseBooks.getCatalog(), book,
                databaseBooks.getCurrPage(), databaseBooks.getFileForDatabase());
    }

    public void print(ArrayList<Book> books) {
        for(Book book: books)
            System.out.println(new BookLogic().parseBook(book));
    }

    public void loadData(DatabaseBooks databaseBooks){
        String stringFile = new FileForDataLogic().readFile(databaseBooks.getFileForDatabase());
        new FileForDataLogic().cleanFile(databaseBooks.getFileForDatabase());
        String[] lines = stringFile.split("\n\\s*");
        for (String line : lines){
            String[] words = line.split(" {2}");
            if (words.length == 3) addBook(databaseBooks, words[0], words[1], Integer.parseInt(words[2]));
            else addElectronicBook(databaseBooks, words[0], words[1], Integer.parseInt(words[2]), words[3]);
        }
    }
}