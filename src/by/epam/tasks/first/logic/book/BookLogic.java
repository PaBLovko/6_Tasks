package by.epam.tasks.first.logic.book;

import by.epam.tasks.first.bean.FileForData;
import by.epam.tasks.first.bean.book.Book;
import by.epam.tasks.first.bean.book.ElectronicBook;
import by.epam.tasks.first.logic.FileForDataLogic;

import java.util.ArrayList;
import java.util.Map;

public class BookLogic {

    public int addBook(Map<Integer, ArrayList<Book>> catalog, int page, Book book) {
        if (!catalog.containsKey(page)) {
            catalog.put(page, new ArrayList<>());
        } else if (catalog.get(page).size() > 9) {
            page++;
            catalog.put(page, new ArrayList<>());
        }
        catalog.get(page).add(book);
        return page;
    }

    public ArrayList<Book> findBook(Map<Integer, ArrayList<Book>> catalog, String author){
        ArrayList<Book> authorsBooks=new ArrayList<>();
        if(catalog.size()>0)
            for (Integer i : catalog.keySet())
                for (Book book : catalog.get(i))
                    if (book.getAuthor().equals(author))
                        authorsBooks.add(book);
        return authorsBooks;
    }

    public ArrayList<Book> findBook(Map<Integer, ArrayList<Book>> catalog, Book bookSearch) {
        ArrayList<Book> books = new ArrayList<>();
        if(catalog.size()>0)
            for (Integer i : catalog.keySet())
                for (Book book : catalog.get(i))
                    if (book.equals(bookSearch))
                        books.add(book);
        return books;
    }

    public int removeBook(Map<Integer, ArrayList<Book>> catalog, Book book, int currPage, FileForData fileForDatabase){
        int removePage = -1;
        for(Integer i:catalog.keySet()){
            if(catalog.get(i).contains(book)){
                removePage=i;
                break;
            }
        }
        if(removePage!=-1) {
            currPage=removePage;
            catalog.get(removePage).remove(book);
            while (catalog.containsKey(removePage + 1)) {
                catalog.get(removePage).add(catalog.get(removePage + 1).get(0));
                catalog.get(removePage + 1).remove(0);
                removePage++;
            }
            new FileForDataLogic().writeToFile(fileForDatabase, catalog);
        }
        return currPage;
    }

    public void print(int page, Map<Integer, ArrayList<Book>> catalog) {
        if(catalog.containsKey(page)){
            System.out.println("--------------------------"+page+"---------------------------");
            for(int i=0;i<catalog.get(page).size();i++)
                System.out.println((i+1)+"\t\t"+catalog.get(page).get(i).toString());
            System.out.println("--------------------------"+page+"---------------------------");
        }
    }

    public String getDescription(Map<Integer, ArrayList<Book>> catalog,int currPage, int bookNumber) {
        if(catalog.size()>0 && bookNumber>0 && bookNumber<=catalog.get(currPage).size())
            return catalog.get(currPage).get(bookNumber-1).getDescription();
        else return "Wrong book number!";
    }

    public void setDescription(Map<Integer, ArrayList<Book>> catalog, int currPage, int bookNumber, String description){
        if(catalog.size()>0 && bookNumber>0 && bookNumber<=catalog.get(currPage).size())
            addDescription(catalog.get(currPage).get(bookNumber-1), description);
    }

    public void addDescription(Book book, String description){
        if(description!=null && !description.isEmpty())
            book.setDescription(book.getDescription() + description+"\n");
    }

    public Book getNthBook(Map<Integer, ArrayList<Book>> catalog, int currPage, int bookNumber) {
        if(catalog.size()>0 && bookNumber>0 && bookNumber<=catalog.get(currPage).size())
            return catalog.get(currPage).get(bookNumber-1);
        else return null;
    }

    public void removeAll(Map<Integer, ArrayList<Book>> catalog, Book book, int currPage, FileForData fileForDatabase) {
        for(Integer i:catalog.keySet())
            while(catalog.get(i).contains(book))
                currPage = removeBook(catalog, book, currPage, fileForDatabase);
    }

    public String parseBook(Book book){
        return String.format("%s  %s  %s",book.getAuthor(),book.getName(),book.getPages());
    }

    public String parseBook(ElectronicBook electronicBook){
        return parseBook((Book) electronicBook)+"\t"+electronicBook.getResource();
    }
}
