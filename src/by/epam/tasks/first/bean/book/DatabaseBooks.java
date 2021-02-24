package by.epam.tasks.first.bean.book;

import by.epam.tasks.first.bean.FileForData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DatabaseBooks {
    private Map<Integer, ArrayList<Book>> catalog;
    private int page;
    private FileForData fileForDatabase;
    private int currPage;


    public DatabaseBooks() {
        this.catalog = new HashMap<Integer, ArrayList<Book>>();
        this.fileForDatabase = new FileForData(FileForData.pathData);
        this.page = 1;
        this.currPage = 1;
    }

    public Map<Integer, ArrayList<Book>> getCatalog() {
        return catalog;
    }

    public void setCatalog(Map<Integer, ArrayList<Book>> catalog) {
        this.catalog = catalog;
    }

    public FileForData getFileForDatabase() {
        return fileForDatabase;
    }

    public void setFileForDatabase(FileForData fileForDatabase) {
        this.fileForDatabase = fileForDatabase;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseBooks that = (DatabaseBooks) o;
        return page == that.page &&
                currPage == that.currPage &&
                Objects.equals(catalog, that.catalog) &&
                Objects.equals(fileForDatabase, that.fileForDatabase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog, page, fileForDatabase, currPage);
    }

    @Override
    public String toString() {
        return "DatabaseBooks{" +
                "catalog=" + catalog +
                ", page=" + page +
                ", fileForDatabase=" + fileForDatabase +
                ", currPage=" + currPage +
                '}';
    }
}

