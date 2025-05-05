package model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Funciones {
    

    public static void createFolder(String folderName) {
        try {
            Path path = Paths.get(folderName);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }
    }
    
   
    public static void createFile(String path, String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path + File.separator + fileName, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al crear/escribir el archivo: " + e.getMessage());
        }
    }
    
   
    public static String[] showListFiles(String path) {
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            return folder.list();
        }
        return new String[0];
    }
    
   
    public static String showFile(String path, String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path + File.separator + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }
        return content.toString();
    }
    
   
    public static boolean overWriteFile(String path, String fileName, String newContent) {
        File file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path + File.separator + fileName))) {
            writer.write(newContent);
            return true;
        } catch (IOException e) {
            System.err.println("Error al sobrescribir el archivo: " + e.getMessage());
            return false;
        }
    }
    
  
    public static void deleteFile(String path, String fileName) {
        File file = new File(path + File.separator + fileName);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("No se pudo eliminar el archivo");
            }
        } else {
            System.err.println("El archivo no existe");
        }
    }
    
    
    public static int countChars(String path, String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path + File.separator + fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar caracteres: " + e.getMessage());
            return -1;
        }
        return count;
    }
    
   
    public static int countWords(String path, String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path + File.separator + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                count += words.length;
            }
        } catch (IOException e) {
            System.err.println("Error al contar palabras: " + e.getMessage());
            return -1;
        }
        return count;
    }
    
   
    public static String swapWords(String path, String fileName, String oldWord, String newWord) {
        StringBuilder newContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path + File.separator + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                newContent.append(line.replaceAll(oldWord, newWord)).append("\n");
            }
        } catch (IOException e) {
            return "Error al reemplazar palabras: " + e.getMessage();
        }
        
        
        overWriteFile(path, fileName, newContent.toString());
        return newContent.toString();
    }
}