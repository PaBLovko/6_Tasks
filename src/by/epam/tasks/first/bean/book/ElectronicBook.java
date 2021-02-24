package by.epam.tasks.first.bean.book;

import java.util.Objects;

public class ElectronicBook extends Book{

    private String resource;

    public ElectronicBook(String name, String author, int pages) {
        super(name, author, pages);
        this.resource = "http://elib.bsu.by/";
    }

    public ElectronicBook(String name, String author, String resource,  int pages) {
        super(name, author, pages);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectronicBook that = (ElectronicBook) o;
        return Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), resource);
    }

    @Override
    public String toString() {
        return "ElectronicBook{" +
                "resource='" + resource + '\'' +
                '}';
    }
}

