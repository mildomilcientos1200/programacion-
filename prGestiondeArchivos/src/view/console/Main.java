package view.console;

import model.Funciones;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        String currentFolder = "";
        int option;
        
        do {
            showMenu();
            option = getIntInput("Seleccione una opcion: ");
            
            try {
                switch (option) {
                    case 1:
                        currentFolder = handleCreateFolder();
                        break;
                    case 2:
                        handleCreateFile(currentFolder);
                        break;
                    case 3:
                        handleShowListFiles(currentFolder);
                        break;
                    case 4:
                        handleShowFile(currentFolder);
                        break;
                    case 5:
                        handleOverWriteFile(currentFolder);
                        break;
                    case 6:
                        handleDeleteFile(currentFolder);
                        break;
                    case 7:
                        handleCountChars(currentFolder);
                        break;
                    case 8:
                        handleCountWords(currentFolder);
                        break;
                    case 9:
                        handleSwapWords(currentFolder);
                        break;
                    case 10:
                        handlePrintPDF(currentFolder);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            
        } while (option != 0);
        
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n--- MENU GESTION DE ARCHIVOS ---");
        System.out.println("1. Crear carpeta");
        System.out.println("2. Crear/agregar a archivo");
        System.out.println("3. Listar archivos");
        System.out.println("4. Mostrar contenido de archivo");
        System.out.println("5. Sobrescribir archivo");
        System.out.println("6. Eliminar archivo");
        System.out.println("7. Contar caracteres en archivo");
        System.out.println("8. Contar palabras en archivo");
        System.out.println("9. Reemplazar palabras en archivo");
        System.out.println("10. Crear PDF de archivo");
        System.out.println("0. Salir");
    }
    
    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada no valida. " + message);
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static String getStringInput(String message) {
        System.out.print(message);
        scanner.nextLine(); 
        return scanner.nextLine();
    }
    
    private static String handleCreateFolder() {
        String folderName = getStringInput("Ingrese el nombre de la carpeta: ");
        Funciones.createFolder(folderName);
        System.out.println("Carpeta '" + folderName + "' creada o verificada");
        return folderName;
    }
    
    private static void handleCreateFile(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        String content = getStringInput("Ingrese el contenido: ");
        Funciones.createFile(currentFolder, fileName, content);
        System.out.println("Archivo '" + fileName + "' creado/modificado");
    }
    
    private static void handleShowListFiles(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String[] files = Funciones.showListFiles(currentFolder);
        if (files.length == 0) {
            System.out.println("No hay archivos en la carpeta");
        } else {
            System.out.println("Archivos en la carpeta:");
            for (String file : files) {
                System.out.println("- " + file);
            }
        }
    }
    
    private static void handleShowFile(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        String content = Funciones.showFile(currentFolder, fileName);
        System.out.println("\nContenido de '" + fileName + "':\n" + content);
    }
    
    private static void handleOverWriteFile(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        String newContent = getStringInput("Ingrese el nuevo contenido: ");
        boolean success = Funciones.overWriteFile(currentFolder, fileName, newContent);
        if (success) {
            System.out.println("Archivo '" + fileName + "' sobrescrito");
        } else {
            System.out.println("El archivo no existe");
        }
    }
    
    private static void handleDeleteFile(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo a eliminar: ");
        Funciones.deleteFile(currentFolder, fileName);
        System.out.println("Archivo '" + fileName + "' eliminado (si existia)");
    }
    
    private static void handleCountChars(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        int count = Funciones.countChars(currentFolder, fileName);
        if (count >= 0) {
            System.out.println("El archivo '" + fileName + "' tiene " + count + " caracteres");
        } else {
            System.out.println("Error al contar caracteres");
        }
    }
    
    private static void handleCountWords(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        int count = Funciones.countWords(currentFolder, fileName);
        if (count >= 0) {
            System.out.println("El archivo '" + fileName + "' tiene " + count + " palabras");
        } else {
            System.out.println("Error al contar palabras");
        }
    }
    
    private static void handleSwapWords(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
        String fileName = getStringInput("Ingrese el nombre del archivo: ");
        String oldWord = getStringInput("Ingrese la palabra a reemplazar: ");
        String newWord = getStringInput("Ingrese la nueva palabra: ");
        String result = Funciones.swapWords(currentFolder, fileName, oldWord, newWord);
        System.out.println("\nContenido modificado:\n" + result);
    }
    
    private static void handlePrintPDF(String currentFolder) {
        if (currentFolder.isEmpty()) {
            System.out.println("Primero cree o seleccione una carpeta");
            return;
        }
    }
}


        