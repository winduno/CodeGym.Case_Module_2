package sample.business;

import sample.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static ArrayList<User> readUserFromFile(String fileName){
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream   objectInputStream = new ObjectInputStream(fileInputStream);
            Object userList = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return (ArrayList<User>) userList;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void writeUserToFile(List<User> usersList, String fileName){
        try{
            File file = new File(fileName);
            if (file.exists() == false){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(usersList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
