package by.epam.tasks.first.logic.user;

import by.epam.tasks.first.bean.FileForData;
import by.epam.tasks.first.bean.user.DatabaseUsers;
import by.epam.tasks.first.bean.user.User;
import by.epam.tasks.first.logic.FileForDataLogic;

public class DatabaseUsersLogic {

    public DatabaseUsers createDatabaseUsers() {
        new FileForDataLogic().createFile(new FileForData(FileForData.pathUsers));
        DatabaseUsers databaseUsers = new DatabaseUsers();
        loadData(databaseUsers);
        return databaseUsers;
    }

    public User getUser(DatabaseUsers databaseUsers){
        return new UserLogic().getUser(databaseUsers.getUsers());
    }

    public void addUser(DatabaseUsers databaseUsers, String login, String password){
        if (new UserLogic().isValidLogin(login, databaseUsers.getUsers()))
            new FileForDataLogic().addToFile(databaseUsers.getFileForDatabase(), new UserLogic().parseUser(
                    new UserLogic().addUser(databaseUsers.getUsers(), login, password)) +"\n");
    }

    public void adviceAdmin(String advice){
        new UserLogic().adviceAdmin(advice);
    }

    public void messageAllUsers(DatabaseUsers databaseUsers, String message){
        new UserLogic().messageAllUsers(databaseUsers.getUsers(), message);
    }

    public void changePassword(String oldPassword,String newPassword){
        UserLogic.changePassword(oldPassword, newPassword);
    }

    public boolean isConnectAdmin(DatabaseUsers databaseUsers){
        return new UserLogic().isConnectAdmin(databaseUsers.getUsers());
    }

    public String checkMail(DatabaseUsers databaseUsers) {
        return new UserLogic().getMail(getUser(databaseUsers));
    }

    public void loadData(DatabaseUsers databaseUsers){
        String stringFile = new FileForDataLogic().readFile(databaseUsers.getFileForDatabase());
        new FileForDataLogic().cleanFile(databaseUsers.getFileForDatabase());
        String[] lines = stringFile.split("\n\\s*");
        for (String line : lines){
            String[] words = line.split(" {2}");
            addUser(databaseUsers, words[0], new UserLogic().decryptPassword(words[1]));
        }
    }
}

