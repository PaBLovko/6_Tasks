package by.epam.tasks.first.bean;

import by.epam.tasks.first.bean.book.DatabaseBooks;
import by.epam.tasks.first.bean.user.DatabaseUsers;
import by.epam.tasks.first.logic.book.DatabaseBooksLogic;
import by.epam.tasks.first.logic.user.DatabaseUsersLogic;

import java.util.Scanner;

public class ConsoleMenu {
    private final DatabaseBooks databaseBooks;
    private final DatabaseUsers databaseUsers;
    private final Scanner scanner;

    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.databaseBooks = new DatabaseBooksLogic().createDatabaseBooks();
        this.databaseUsers = new DatabaseUsersLogic().createDatabaseUsers();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public DatabaseBooks getDatabaseBooks() {
        return databaseBooks;
    }

    public DatabaseUsers getDatabaseUsers() {
        return databaseUsers;
    }
}
