package by.epam.tasks.first.logic;

import by.epam.tasks.first.bean.ConsoleMenu;
import by.epam.tasks.first.bean.book.Book;
import by.epam.tasks.first.bean.book.DatabaseBooks;
import by.epam.tasks.first.bean.book.ElectronicBook;
import by.epam.tasks.first.bean.user.DatabaseUsers;
import by.epam.tasks.first.bean.user.User;
import by.epam.tasks.first.logic.book.DatabaseBooksLogic;
import by.epam.tasks.first.logic.user.DatabaseUsersLogic;
import by.epam.tasks.first.logic.user.UserLogic;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleMenuLogic {

    public void menu(ConsoleMenu consoleMenu){
        int choice=1;
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for exit from application");
                System.out.println("Enter 1, for login");
                System.out.println("Enter 2, for sign up in database");
                choice = consoleMenu.getScanner().nextInt();
                if(choice==1 || choice==2) {
                    boolean isLogged = false;
                    while (!isLogged) {
                        switch (choice) {
                            case 1: {
                                System.out.println("\nEnter login and password:");
                                isLogged = login(consoleMenu.getScanner().next(), consoleMenu.getScanner().next(),
                                        consoleMenu.getDatabaseUsers().getUsers());
                                break;
                            }

                            case 2: {
                                System.out.println("\nEnter login and password:");
                                new DatabaseUsersLogic().addUser(consoleMenu.getDatabaseUsers(),
                                        consoleMenu.getScanner().next(), consoleMenu.getScanner().next());
                                isLogged = true;
                                break;
                            }
                        }

                    }
                    if (new DatabaseUsersLogic().isConnectAdmin(consoleMenu.getDatabaseUsers())) {
                        adminMenu(consoleMenu.getScanner(),
                                consoleMenu.getDatabaseBooks(),consoleMenu.getDatabaseUsers());
                    } else {
                        defaultUserMenu(consoleMenu.getScanner(),
                                consoleMenu.getDatabaseBooks(),consoleMenu.getDatabaseUsers());
                    }
                }
            }
        }catch (InputMismatchException ex){
            System.out.println("Error ! Wrong operation!");
            menu(consoleMenu);
        }
    }

    private void adminMenu(Scanner scanner, DatabaseBooks databaseBooks, DatabaseUsers databaseUsers){
        DatabaseBooksLogic databaseBooksLogic = new DatabaseBooksLogic();
        DatabaseUsersLogic databaseUsersLogic = new DatabaseUsersLogic();
        int choice=1;
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for out");
                System.out.println("Enter 3, for get list of books this page ");
                System.out.println("Enter 4, for get list of books page on certain page");
                System.out.println("Enter 5, for find a book");
                System.out.println("Enter 6, for find all books of authors");
                System.out.println("Enter 7, for get description of book");
                System.out.println("Enter 8, for add the book");
                System.out.println("Enter 9, for add electronic book");
                System.out.println("Enter 10, for delete the book");
                System.out.println("Enter 11, for answer on recommendation of user");
                System.out.println("Enter 12, for change the password like admin");
                System.out.println("Enter 13, for change the description");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 3: {
                        databaseBooksLogic.print(databaseBooks);
                        break;
                    }

                    case 4: {
                        System.out.println("\nEnter number of page:");
                        databaseBooksLogic.print(databaseBooks, scanner.nextInt());
                        break;
                    }

                    case 5: {
                        System.out.println("\nEnter author, book name, count of page, new address");
                        databaseBooksLogic.print(databaseBooksLogic.findBook(databaseBooks,
                                new Book(scanner.nextLine(), scanner.nextLine(), scanner.nextInt())));
                        break;
                    }

                    case 6: {
                        System.out.println("\nEnter author");
                        databaseBooksLogic.print(databaseBooksLogic.findBook(databaseBooks, scanner.next()));
                        break;
                    }

                    case 7: {
                        System.out.println("\nEnter serial number of book and description on this page ");
                        System.out.println(databaseBooksLogic.getDescription(databaseBooks, scanner.nextInt()));
                        break;
                    }

                    case 8: {
                        System.out.println("\nEnter author, book name, count of page");
                        Book book = new Book(scanner.nextLine(), scanner.nextLine(), scanner.nextInt());
                        databaseBooksLogic.addBook(databaseBooks, book);
                        databaseUsersLogic.messageAllUsers(databaseUsers, book.toString()+"\t\tadd catalog\n");
                        break;
                    }

                    case 9: {
                        System.out.println("\nEnter author, book name, count of page, new address ");
                        ElectronicBook ebook = new ElectronicBook(scanner.nextLine(),
                                scanner.nextLine(),parseStringWeb(scanner.nextLine()),scanner.nextInt());
                        databaseBooksLogic.addBook(databaseBooks, ebook);
                        databaseUsersLogic.messageAllUsers(databaseUsers,ebook.toString()+"\t\tadd catalog\n");
                        break;
                    }

                    case 10: {
                        System.out.println("\nEnter author, book name, amount of pages");
                        databaseBooksLogic.deleteBookFromAnywhere(databaseBooks,
                                new Book(scanner.nextLine(), scanner.nextLine(), scanner.nextInt()));
                        break;
                    }

                    case 11: {
                        checkMail(databaseUsersLogic.checkMail(databaseUsers));
                        break;
                    }

                    case 12: {
                        System.out.println("\nEnter password, the enter new password");
                        databaseUsersLogic.changePassword(scanner.next(), scanner.next());
                        break;
                    }

                    case 13: {
                        System.out.println("\nEnter serial number of book and description on this page ");
                        int num=scanner.nextInt();
                        databaseBooksLogic.setDescription(databaseBooks, num,
                                scanner.next()+scanner.nextLine());
                        Book book = databaseBooksLogic.getNthBook(databaseBooks, num);
                        if (book != null)
                            databaseUsersLogic.messageAllUsers(databaseUsers,
                                    book.toString()+"\t\tChange description \n");
                        break;
                    }
                }
            }
        } catch (InputMismatchException ex){
            System.out.println("Error! Wrong operation!");
            scanner.reset();
            adminMenu(scanner, databaseBooks, databaseUsers);
        }
    }

    private void defaultUserMenu(Scanner scanner, DatabaseBooks databaseBooks, DatabaseUsers databaseUsers){
        int choice=1;
        DatabaseBooksLogic databaseBooksLogic = new DatabaseBooksLogic();
        DatabaseUsersLogic databaseUsersLogic = new DatabaseUsersLogic();
        try {
            while (choice != 0) {
                System.out.println("Enter 0, for out");
                System.out.println("Enter 3, for get list of book on this page");
                System.out.println("Enter 4, for get of list on certain page");
                System.out.println("Enter 5, for find a book");
                System.out.println("Enter 6, for find all books");
                System.out.println("Enter 7, for check post");
                System.out.println("Enter 8, for recommend book");
                System.out.println("Enter 9, for get description of book");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 3: {
                        databaseBooksLogic.print(databaseBooks);
                        break;
                    }

                    case 4: {
                        System.out.println("\nEnter number of page :");
                        databaseBooksLogic.print(databaseBooks, scanner.nextInt());
                        break;
                    }

                    case 5: {
                        System.out.println("\nEnter author, book name, number of page");
                        databaseBooksLogic.print(databaseBooksLogic.findBook(databaseBooks,
                                new Book(scanner.nextLine(), scanner.nextLine(), scanner.nextInt())));
                        break;
                    }

                    case 6: {
                        System.out.println("\nEnter author");
                        databaseBooksLogic.print(databaseBooksLogic.findBook(databaseBooks, scanner.nextLine()));
                        break;
                    }

                    case 7: {
                        checkMail(databaseUsersLogic.checkMail(databaseUsers));
                        break;
                    }

                    case 8: {
                        System.out.println("\nEnter author, book name, number of page");
                        databaseUsersLogic.adviceAdmin(
                                scanner.nextLine() + " " + scanner.nextLine() + " " + scanner.nextInt());
                        break;
                    }

                    case 9: {
                        System.out.println("\nEnter serial number of book and description on this page");
                        System.out.println(databaseBooksLogic.getDescription(databaseBooks, scanner.nextInt()));
                        break;
                    }
                }
            }
        } catch (InputMismatchException ex){
            System.out.println("Error! wrong operation!");
            defaultUserMenu(scanner, databaseBooks, databaseUsers);
        }
    }

    public boolean login(String login,String password, List<User> users){
        UserLogic userLogic = new UserLogic();
        if(!userLogic.isValidLogin(login, users)){
            if (!userLogic.login(login, password, users)){
                System.out.println("Entered wrong password!");
                return false;
            }
            return true;
        }else{
            System.out.println("User "+login+" not found!");
            return false;
        }
    }

    public void checkMail(String email){
        if(email == null)
            System.out.println("\nThere is not new massages!");
        else System.out.println(email);
    }

    public String parseStringWeb(String resource){
        Pattern urlPattern=Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher urlMatcher=urlPattern.matcher(resource);
        if(urlMatcher.find())
            return resource;
        return " ";
    }
}
