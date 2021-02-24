package by.epam.tasks.first.bean.user;

import by.epam.tasks.first.bean.FileForData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseUsers {
    private List<User> users;
    private FileForData fileForDatabase;

    public DatabaseUsers() {
        this.users = new ArrayList<>();
        this.fileForDatabase = new FileForData(FileForData.pathUsers);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public FileForData getFileForDatabase() {
        return fileForDatabase;
    }

    public void setFileForDatabase(FileForData fileForDatabase) {
        this.fileForDatabase = fileForDatabase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseUsers that = (DatabaseUsers) o;
        return Objects.equals(users, that.users) &&
                Objects.equals(fileForDatabase, that.fileForDatabase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, fileForDatabase);
    }

    @Override
    public String toString() {
        return "DatabaseUsers{" +
                "users=" + users +
                ", fileForDataBase=" + fileForDatabase +
                '}';
    }
}
