package com.example.denis.weather.model.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

/**
 * Allows the reading/writing of an object from/to a file
 * inside the application's /data directory. Allows for
 * more efficient storing of objects such as arrays instead
 * of within SharedPreferences
 *
 * @author Isaac Whitfield
 * @version 31/07/2013
 */
public class WriteObjectFile {


    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;
    private static ObjectInputStream objectIn;
    private static ObjectOutputStream objectOut;
    private static Object outputObject;
    private static String filePath;



    public static Object readObject(String fileName, Context parent){
        try {
            filePath = parent.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            fileIn = new FileInputStream(filePath);
            objectIn = new ObjectInputStream(fileIn);
            outputObject = objectIn.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputObject;
    }

    public static void writeObject(Object inputObject, String fileName, Context parent){
        try {
            filePath = parent.getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            fileOut = new FileOutputStream(filePath);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(inputObject);
            fileOut.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}