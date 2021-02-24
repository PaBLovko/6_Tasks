package by.epam.tasks.first.bean.user;

import java.util.Objects;

public class User {
    private String login;
    private String password;
    private String email;
    protected static String adminEmail;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.email = " ";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getAdminEmail() {
        return adminEmail;
    }

    public static void setAdminEmail(String adminEmail) {
        User.adminEmail = adminEmail;
    }

    public boolean isAdmin(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
