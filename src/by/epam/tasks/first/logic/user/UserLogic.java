package by.epam.tasks.first.logic.user;

import by.epam.tasks.first.bean.user.Admin;
import by.epam.tasks.first.bean.user.User;

import java.util.List;

public class UserLogic {
    private static String password = "876543210";

    public User getUser(String password, String login){
        if(UserLogic.password.equals(password)) return new Admin(login,password);
        else return new User(login,password);
    }

    protected static void changePassword(String oldPassword,String newPassword){
        if(oldPassword.equals(password) && newPassword!=null && !newPassword.isEmpty())
            password=newPassword;
    }

    public boolean isValidLogin(String login, List<User> users){
        boolean isInvalid=true;
        for(User user:users){
            if(user.getLogin().equals(login)){
                isInvalid=false;
                break;
            }
        }
        return isInvalid;
    }

    public boolean login(String login,String password, List<User> users){
        int index=0;
        for(int i=0;i<users.size();i++)
            if(users.get(i).getLogin().equals(login)){
                index=i;
                break;
            }
        if(password.equals(users.get(index).getPassword())) {
            users.add(users.size(), users.get(index));
            users.remove(index);
            return true;
        }else return false;
    }

    protected void addMessage(User user, String message){
        if(message!=null && !message.isEmpty()){
            user.setEmail(user.getEmail()+message);
        }
    }

    public String getMail(User user){
        String email = user.getEmail();
        if(email!=null && !email.isEmpty() && !email.equals(" ")) {
            user.setEmail(" ");
            return email;
        }else return null;
    }

    public User getUser(List<User> users) {
        return users.get(users.size() - 1);
    }

    public User addUser(List<User> users, String login, String password) {
        User toAdd = getUser(password, login);
        users.add(toAdd);
        return toAdd;
    }

    public void adviceAdmin(String advice){
        if(advice!=null && !advice.isEmpty())
            User.setAdminEmail(User.getAdminEmail() + advice);
    }

    public void messageAllUsers(List<User> users, String message) {
        for (User user : users)
            if (!user.isAdmin())
                addMessage(user, message);
    }

    public boolean isConnectAdmin(List<User> users) {
        return users.get(users.size() - 1).isAdmin();
    }

    public String parseUser(User user){
        return String.format(
                "%s  %s  %s",user.getLogin(),encryptPassword(user.getPassword()),(user.isAdmin())?"Admin":"User");
    }

    private String encryptPassword(String password){
        StringBuilder string= new StringBuilder();
        int parseInt = Integer.parseInt(password);
        if (parseInt == 0) string.append(1);
        while (parseInt!=0){
            int number = parseInt % 10;
            string.append(number == 9 ? 0: ++number);
            parseInt/=10;
        }
        return string.toString();
    }

    protected String decryptPassword(String password){
        StringBuilder string = new StringBuilder();
        int parseInt = Integer.parseInt(password);
        if (parseInt == 0) return password;
        while (parseInt!=0){
            int number = parseInt % 10;
            string.append(number == 0 ? 9: --number);
            parseInt/=10;
        }
        return string.toString();
    }
}
