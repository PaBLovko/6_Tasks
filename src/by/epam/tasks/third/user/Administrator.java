package by.epam.tasks.third.user;

/*
    Класс для представления администратора
    Доп возможности:
    1) Модернизация каталога
    (добавление/удаление/изменение описания)
    2) Добавление рекомендованных книг
    3) Изменение пароля к админ панели
 */

import by.epam.tasks.third.deed.Deed;

public class Administrator extends User {

    protected Administrator(String login,String password){
        super(login,password);
    }

    public void changePassword(String oldPassword,String newPassword){
        UserFactory.changePassword(oldPassword, newPassword);
    }

    public void addDeed(Deed deed){
        archive.addDeed(deed);
    }

    public void addDeed(String name,String faculty,int course,int yearOfEnrolling){
        archive.addDeed(name, faculty, course, yearOfEnrolling);
    }

    public void removeDeed(Deed deed){
        archive.removeDeed(deed);
    }

    public void removeDeed(int numOfDeed){
        archive.removeDeed(numOfDeed);
    }

    public void removeIfNotEnrolled(){
        archive.removeIfNotEnrolled();
    }

    public boolean chooseDeed(int numOfDeed){
        return archive.chooseDeed(numOfDeed);
    }

    public void changeFaculty(String faculty){
        archive.changeFaculty(faculty);
    }

    public void setCharacteristic(String characteristic){
       archive.setCharacteristic(characteristic);
    }

    public boolean incCourse(){
        return archive.incCourse();
    }

    public void changeCourse(int course){
        archive.changeCourse(course);
    }

    public void sort(){
        archive.sort();
    }

    @Override
    public boolean isAdmin(){
        return true;
    }
}
