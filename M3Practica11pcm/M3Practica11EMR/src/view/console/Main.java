package view.console;

import java.text.ParseException;
import java.util.Scanner;
import model.validations.UserDataValidations;
import model.validations.exceptions.InvalidIdException;
import model.validations.exceptions.InvalidEmailException;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        String option = "";
        do {
            System.out.println("\n\n");
            System.out.println("1.-Tester checkId");
            System.out.println("2.-Tester checkFormatDate");
            System.out.println("3.-Tester checkCalculateAge");
            System.out.println("4.-Tester checkPostalCode");
            System.out.println("5.-Tester checkNumeric");
            System.out.println("6.-Tester checkAlplhabetic");
            System.out.println("7.-Tester checkEmail");
            System.out.println("8.-Tester checkName");
            System.out.println("z.-Salir");
            System.out.print("Option: ");
            option = scan.next();
            System.out.println("");

            switch (option) {
                case "1":
                    testCheckId();
                    break;
                case "2":
                    testCheckFormatDate();
                    break;
                case "3":
                    testCheckCalculateAge();
                    break;
                case "4":
                    testCheckPostalCode();
                    break;
                case "5":
                    testCheckNumeric();
                    break;
                case "6":
                    testCheckAlphabetic();
                    break;
                case "7":
                    testCheckEmail();
                    break;
                case "8":
                    testCheckName();
                    break;
                case "z":
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Incorrect Option");
            }
        } while (!option.equals("z"));
    }

    static void testCheckId() {
        System.out.println("-NIF VALIDATOR-");
        System.out.print("Enter your ID: ");
        String ID = scan.next();
        try {
            UserDataValidations.checkId(1, ID);
            System.out.println("Correct ID");
        } catch (InvalidIdException e) {
            System.out.println("Wrong ID: " + e.getMessage());
        }
    }

    static void testCheckFormatDate() {
        System.out.println("Format Date Validator");
        System.out.print("Insert any date (format: dd/mm/yyyy): ");
        String date = scan.next();
        boolean dateOK = UserDataValidations.checkFormatDate(date);
        if (dateOK) {
            System.out.println("Correct format date");
        } else {
            System.out.println("Incorrect format date");
        }
    }

    static void testCheckCalculateAge() throws ParseException {
        System.out.println("Age calculator");
        System.out.print("Insert your birth date (format: dd/mm/yyyy): ");
        String date = scan.next();
        double ageOK = UserDataValidations.checkAge(date);
        if (ageOK >= 0) {
            System.out.println("Your age is " + ageOK);
        } else {
            System.out.println("Error, your birth date is incorrect");
        }
    }

    static void testCheckPostalCode() {
        System.out.println("Postal Code Validator");
        System.out.print("Insert the postal code: ");
        String zip = scan.next();
        boolean cpOK = UserDataValidations.checkPostalCode(zip);
        if (cpOK) {
            System.out.println("Your postal code is right.");
        } else {
            System.out.println("Wrong postal code, try again.");
        }
    }

    static void testCheckNumeric() {
        System.out.println("Numeric Validator");
        System.out.print("Insert something: ");
        String num = scan.next();
        boolean numOK = UserDataValidations.isNumeric(num);
        if (numOK) {
            System.out.println("They're all nums");
        } else {
            System.out.println("There are some letters >:C");
        }
    }

    static void testCheckAlphabetic() {
        System.out.println("Alphabetic Validator");
        System.out.print("Insert something: ");
        String character = scan.next();
        boolean charOK = UserDataValidations.isAlphabetic(character);
        if (charOK) {
            System.out.println("They're all letters");
        } else {
            System.out.println("There are some numbers >:C");
        }
    }

    static void testCheckEmail() {
        System.out.println("Email Validator");
        System.out.print("Insert your Email: ");
        String email = scan.next();
        try {
            UserDataValidations.checkEmail(email);
            System.out.println("Your Email is correct");
        } catch (InvalidEmailException e) {
            System.out.println("Wrong Email: " + e.getMessage());
        }
    }

    static void testCheckName() {
        System.out.println("Name Validator");
        System.out.print("Insert your name: ");
        String name = scan.next();
        boolean nameOK = UserDataValidations.checkName(name);
        if (nameOK) {
            System.out.println("Nice name ;)");
        } else {
            System.out.println("I don't like that name, change it");
        }
    }
}
