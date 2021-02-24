package by.epam.tasks.first.logic;

import by.epam.tasks.first.bean.FileForData;
import by.epam.tasks.first.bean.book.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FileForDataLogic {

    public void addToFile(FileForData fileForDatabase, String content){
        try {
            FileWriter writer = new FileWriter(fileForDatabase.getFile(), true);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void writeToFile(FileForData fileForDatabase, Map<Integer, ArrayList<Book>> catalog){
        try {
            FileWriter writer = new FileWriter(fileForDatabase.getFile());
            for(Integer i:catalog.keySet())
                for(Book book:catalog.get(i))
                    writer.write(book.toString()+"\n");
            writer.flush();
            writer.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public boolean createFile(FileForData fileForDatabase){
        if(!fileForDatabase.getFile().exists()){
            try {
                return fileForDatabase.getFile().createNewFile();
            }catch (IOException ex){
                return false;
            }
        }else{
//            cleanFile(fileForDatabase.getFile());
            return false;
        }
    }

    public void cleanFile(FileForData fileForData){
        try {
            FileWriter cleaner = new FileWriter(fileForData.getFile());
            cleaner.close();
        } catch (IOException ex){
            System.out.println(ex);
        }
    }

    public String readFile(FileForData fileForData) {
        try {
            Scanner scanner = new Scanner(Paths.get(fileForData.getFile().getPath()), StandardCharsets.UTF_8.name());
            String data = scanner.useDelimiter("\\A").next();
            scanner.close();
            return data;
        } catch (IOException e) {
            return null;
        }
    }
}
