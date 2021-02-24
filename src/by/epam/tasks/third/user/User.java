package by.epam.tasks.third.user;


import by.epam.tasks.third.deed.Archive;
import by.epam.tasks.third.deed.Deed;

import java.util.List;
import java.util.Objects;

/*
    Класс для представления пользователя
    Возможности:
    1) Поиск книги
    2) Поиск всех книг автора
    3) Просмотр каталога
    4) Рекоммендация книги на добавление
    5) Проверка почты на изменения в каталоге
    6) Получение описания книг
 */

public class User {

    protected static Archive archive=new Archive();
    private String login="New user";
    private String password;

    protected User(String login,String password){
        setLogin(login);
        if(password!=null && !password.isEmpty()){
            this.password=password;
        }
    }

    public void setLogin(String login){
        if(login!=null && !login.isEmpty()){
            this.login=login;
        }
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public List<Deed> findDeedByCourse(int course){
        return archive.findDeedByCourse(course);
    }

    public List<Deed> findDeedByFaculty(String faculty){
        return archive.findDeedByFaculty(faculty);
    }

    public List<Deed> findDeedByYear(int year){
        return archive.findDeedByYear(year);
    }

    public boolean isAdmin(){
        return false;
    }

    public Archive getArchive(){
        return archive;
    }

    private String encryptPassword(){
        StringBuilder string= new StringBuilder();
        for(int i=0;i<password.length();i++){
            string.append("*");
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString(){
        return String.format("%20s %15s %15s",login,encryptPassword(),(isAdmin())?"Аdmin":"User");
    }
}
