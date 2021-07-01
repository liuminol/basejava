package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {
    private static int counter = 0;
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        //____________________________________________

        File dir = new File("C:/java/basejava/src");
        printDirectoryDeeply(dir);

        //____________________________________________
        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printDirectoryDeeply(File dir) {


        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    printMargin(counter);
                    System.out.println("F: " + file.getName());
                } else if (file.isDirectory()){
                    printMargin(counter);
                    System.out.println("D: " + file.getName());
                    counter++;
                    printDirectoryDeeply(file);
                }
            }
            counter--;
        }
    }

    private static void printMargin(int counter) {
        String margin = "   ";

        for (int i = 0; i < counter; i++) {
            System.out.print(margin);
        }
    }
}
