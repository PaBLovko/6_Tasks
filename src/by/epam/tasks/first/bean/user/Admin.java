package by.epam.tasks.first.bean.user;

public class Admin extends User{

    public Admin(String login, String password, String email) {
        super(login, password);
        adminEmail = email;
    }

    public Admin(String login, String parole) {
        super(login, parole);
    }

    @Override
    public String getEmail() {
        return adminEmail;
    }

    @Override
    public void setEmail(String email) {
        adminEmail = email;
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
