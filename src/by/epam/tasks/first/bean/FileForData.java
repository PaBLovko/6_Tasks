package by.epam.tasks.first.bean;

import java.io.File;
import java.util.Objects;

public class FileForData {
    private final String pathFromContentRoot;
    private final File file;
    public static final String pathData = "src\\by\\epam\\tasks\\first\\resources\\DatabaseBooks.txt";
    public static final String pathUsers = "src\\by\\epam\\tasks\\first\\resources\\Users.txt";

    public FileForData(String pathFromContentRoot) {
        this.pathFromContentRoot = pathFromContentRoot;
        this.file = new File(pathFromContentRoot);
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileForData that = (FileForData) o;
        return Objects.equals(pathFromContentRoot, that.pathFromContentRoot) &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromContentRoot, file);
    }

    @Override
    public String toString() {
        return "FileForData{" +
                "pathFromContentRoot='" + pathFromContentRoot + '\'' +
                ", file=" + file +
                '}';
    }
}

