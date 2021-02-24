package by.epam.tasks.third.client;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {

    private static void write(BufferedWriter writer, String request){
        try {
            writer.write(request);
            writer.newLine();
            writer.flush();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //Меню запросов на сервер
    public static void menu(BufferedReader reader, BufferedWriter writer){
        Scanner scanner = new Scanner(System.in);
        String request;

        System.out.println("Enter login and password for authorization: ");
        request = 1 + ";" + scanner.next() + ";" + scanner.next();
        write(writer, request);

        try {
            int choice = 1;
            while (choice != 0) {

                do {
                    System.out.println(reader.readLine());
                }while (reader.ready());

                System.out.println("1-Login ");
                System.out.println("2-View archive ");
                System.out.println("3-Search for faculty archives ");
                System.out.println("4-Search archives by course ");
                System.out.println("5-Search archives by year of enrollment ");
                System.out.println("--------------------ADMIN MENU-----------------");
                System.out.println("6-Add Case ");
                System.out.println("7-Delete case ");
                System.out.println("8-Delete cases of expelled and graduated students ");
                System.out.println("9-Choose a case ");
                System.out.println("10-Sort cases ");
                System.out.println("11-Change faculty ");
                System.out.println("12-Enlarge student course ");
                System.out.println("13-Change course ");
                System.out.println("14-Change admin panel password ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.println("Enter login and password for authorization: ");
                        request = 1 + ";" + scanner.next() + ";" + scanner.next();
                        write(writer, request);
                        break;
                    }

                    case 2: {
                        request = 2 + ";";
                        write(writer, request);
                        break;
                    }

                    case 3: {
                        System.out.println("Enter the name of the faculty ");
                        request = 3 + ";" + scanner.next();
                        write(writer, request);
                        break;
                    }

                    case 4: {
                        System.out.println("Enter a course to search ");
                        request = 4 + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 5: {
                        System.out.println("Enter the year of receipt ");
                        request = 5 + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 6: {
                        System.out.println("Enter student name, faculty, course and year of enrollment ");
                        request = 6 + ";" + scanner.next() + ";" + scanner.next() + ";" + scanner.nextInt() + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 7: {
                        System.out.println("Enter case number to delete ");
                        request = 7 + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 8: {
                        request = 8 + ";";
                        write(writer, request);
                        break;
                    }

                    case 9: {
                        System.out.println("Enter case number to select ");
                        request = 8 + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 10: {
                        request = 10 + ";";
                        write(writer, request);
                        break;
                    }

                    case 11: {
                        System.out.println("Enter a new faculty name ");
                        request = 11 + ";" + scanner.next();
                        write(writer, request);
                        break;
                    }

                    case 12: {
                        request = 12 + ";";
                        write(writer, request);
                        break;
                    }

                    case 13: {
                        System.out.println("Enter course number ");
                        request = 13 + ";" + scanner.nextInt();
                        write(writer, request);
                        break;
                    }

                    case 14:{
                        System.out.println("Enter the old one, and then after the space, the new password ");
                        request=14+";"+scanner.next()+";"+scanner.next();
                        write(writer,request);
                        break;
                    }
                }
            }
        }catch (InputMismatchException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try(
                Socket socket=new Socket("127.0.0.1",8080);
                BufferedWriter writer=
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()));
                BufferedReader reader=
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()))
             ){
                while(socket.isConnected()) {
                    menu(reader, writer);
                }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
