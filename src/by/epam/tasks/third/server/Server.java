package by.epam.tasks.third.server;

import by.epam.tasks.third.deed.Deed;
import by.epam.tasks.third.user.Administrator;
import by.epam.tasks.third.user.User;
import by.epam.tasks.third.user.UserFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@XmlRootElement(name="server")
public class Server {

    @XmlElement(name="user")
    @XmlElementWrapper(name="base")
    private List<User> users = new ArrayList<>();
    private static UserFactory factory=new UserFactory();
    private File file;//выходной файл для логов пользователей
    private final String defaultPath ="..\\resources\\Users.xml";

    public Server(){
        file=new File(defaultPath);
        createFile();
    }

    public Server(String pathToFile){
        if(pathToFile!=null && !pathToFile.isEmpty()){
            file=new File(pathToFile);
        }else{
            file=new File(defaultPath);
        }
        createFile();
    }

    //Регистрация пользователя
    public void addUser(String login,String password){
        if(isValidLogin(login)) {
            User toAdd=factory.getUser(password, login);
            users.add(toAdd);
            writeToFile();
        }
    }

    //Авторизация уже зарегестрированного пользователя
    public void login(String login,String password){
        if(!isValidLogin(login)){
            int index=0;
            for(int i=0;i<users.size();i++){
                if(users.get(i).getLogin().equals(login)){
                    index=i;
                    break;
                }
            }
            if(password.equals(users.get(index).getPassword())) {
                users.add(users.size(), users.get(index));
                users.remove(index);
            }else{
                System.out.println("Invalid password entered!");
            }
        }else{
            addUser(login, password);
        }
    }

    public void removeUser(User user){
        users.remove(user);
        writeToFile();
    }

    //Проверка на существования пользователя в системе
    private boolean isValidLogin(String login){
        boolean isInvalid=true;
        for(User user:users){
            if(user.getLogin().equals(login)){
                isInvalid=false;
                break;
            }
        }
        return isInvalid;
    }

    //Запись в файл (xml)
    private void writeToFile(){
        try {
            JAXBContext context = JAXBContext.newInstance(Server.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // сама сериализация
            marshaller.marshal(this, file);

        } catch (JAXBException e){
            throw new RuntimeException(e);
        }
    }

    //Создание файла по пути, если он отсутствует
    private boolean createFile(){
        if(!file.exists()){
            try {
                return file.createNewFile();
            }catch (IOException ex){
                return false;
            }
        }else{
            cleanFile();
            return false;
        }
    }

    //Очистка данных в файле по заданному пути
    private void cleanFile(){
        try {
            FileWriter cleaner = new FileWriter(file);
            cleaner.close();
        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    //Получить результат поиска в качестве строки
    private String getResult(List<Deed>founded){
        StringBuilder response= new StringBuilder();
        if(founded.size()>0){
            for(Deed deed:founded){
                response.append(deed.toString());
            }
        }else{
            response = new StringBuilder("not found!");
        }
        return response.toString();
    }

    //Обработчик запросов клиента
    private String handler(String request){
        String[] req = request.split(";");
        for(int i=0;i<req.length;i++){
            req[i]=req[i].trim();
        }
        String response;

        try {
            int choice = Integer.parseInt(req[0]);

            switch (choice) {
                case 1: {
                    login(req[1].trim(), req[2].trim());
                    response = "Operation completed successfully ";
                    break;
                }

                case 2: {
                    response = users.get(users.size() - 1).getArchive().toString();
                    break;
                }

                case 3: {
                    response = getResult(users.get(users.size() - 1).findDeedByFaculty(req[1]));
                    break;
                }

                case 4: {
                    response = getResult(users.get(users.size() - 1).findDeedByCourse(Integer.parseInt(req[1])));
                    break;
                }

                case 5: {
                    response = getResult(users.get(users.size() - 1).findDeedByYear(Integer.parseInt(req[1])));
                    break;
                }

                case 6: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).addDeed(req[1], req[2], Integer.parseInt(req[3]), Integer.parseInt(req[4]));
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied ";
                    }
                    break;
                }

                case 7: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).removeDeed(Integer.parseInt(req[1]));
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied ";
                    }
                    break;
                }

                case 8: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).removeIfNotEnrolled();
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied";
                    }
                    break;
                }

                case 9: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).chooseDeed(Integer.parseInt(req[1]));
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied";
                    }
                    break;
                }

                case 10: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).sort();
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied ";
                    }
                    break;
                }

                case 11: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).changeFaculty(req[1]);
                        response = "Operation completed successfully ";
                    } else {
                        response = "Access denied ";
                    }
                    break;
                }

                case 12: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).incCourse();
                        response = "Operation completed successfully";
                    } else {
                        response = "Access denied";
                    }
                    break;
                }

                case 13: {
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).changeCourse(Integer.parseInt(req[1]));
                        response = "Operation completed successfully";
                    } else {
                        response = "Access denied";
                    }
                    break;
                }

                case 14:{
                    if (users.get(users.size() - 1).isAdmin()) {
                        ((Administrator) users.get(users.size() - 1)).changePassword(req[1], req[2]);
                        response = "Operation completed successfully";
                    } else {
                        response = "Access denied";
                    }
                    break;
                }

                default: {
                    response = "Invalid opcode !";
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException | InputMismatchException e){
            throw new RuntimeException(e);
        }
        return response;
    }

    public static void main(String[] args) {
        try (ServerSocket server=new ServerSocket(8000)){
            Server serverObj=new Server();
            while (true){
                try (
                        Socket socket=server.accept();
                        BufferedWriter writer=
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                socket.getOutputStream()));
                        BufferedReader reader=
                                new BufferedReader(
                                        new InputStreamReader(
                                                socket.getInputStream()));
                ){
                    while(socket.isConnected()) {
                        String request = reader.readLine();
                        String response = serverObj.handler(request);

                        writer.write(response);
                        writer.newLine();
                        writer.flush();
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
