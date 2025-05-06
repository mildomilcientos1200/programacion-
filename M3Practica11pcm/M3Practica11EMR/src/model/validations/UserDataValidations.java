package model.validations;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.validations.exceptions.InvalidIdException;
import model.validations.exceptions.InvalidEmailException;

public class UserDataValidations {

    public static void checkId(int typeDoc, String id) throws InvalidIdException {
        if (typeDoc == 1) {
            if (id.length() == 9) {
                String nums = id.substring(0, 8);
                char letra = id.charAt(8);
                if (nums.chars().allMatch(Character::isDigit)) {
                    char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
                    int numeros = Integer.parseInt(nums);
                    int resto = numeros % 23;
                    if (letra == letras[resto]) {
                        return;
                    }
                }
            }
        }
        throw new InvalidIdException("DNI inválido.");
    }

    public static boolean checkFormatDate(String Date) {
        if (Date.length() == 10 && Date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            String[] fecha = Date.split("/");
            int day = Integer.parseInt(fecha[0]);
            int month = Integer.parseInt(fecha[1]);
            int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (month >= 1 && month <= 12 && day >= 1 && day <= monthDays[month - 1]) {
                return true;
            }
        }
        return false;
    }

    public static double checkAge(String birth) throws ParseException {
        if (birth.length() == 10 && birth.matches("\\d{2}/\\d{2}/\\d{4}")) {
            String[] fecha = birth.split("/");
            int day = Integer.parseInt(fecha[0]);
            int month = Integer.parseInt(fecha[1]);
            int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (month >= 1 && month <= 12 && day >= 1 && day <= monthDays[month - 1]) {
                Date currentDate = new Date();
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                Date birthdate = date.parse(birth);

                long ageInMillis = currentDate.getTime() - birthdate.getTime();
                int days = (int) (ageInMillis / 86400000L);
                return days / 365.0;
            }
        }
        return -1;
    }

    public static boolean checkPostalCode(String zip) {
        return zip.matches("\\d{5}");
    }

    public static boolean isNumeric(String num) {
        return num.matches("\\d+");
    }

    public static boolean isAlphabetic(String character) {
        return character.matches("[a-zA-Z]+");
    }

    public static void checkEmail(String email) throws InvalidEmailException {
        String[] domains = {"gmail", "hotmail", "outlook", "monlau", "spacemail"};
        String[] types = {".org", ".cat", ".es", ".com", ".net"};

        if (email.contains("@")) {
            int atPosition = email.indexOf("@");
            int dotPosition = email.lastIndexOf(".");
            if (dotPosition > atPosition) {
                String domain = email.substring(atPosition + 1, dotPosition);
                for (String d : domains) {
                    if (domain.equals(d)) {
                        for (String t : types) {
                            if (email.endsWith(t)) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        throw new InvalidEmailException("Email inválido.");
    }

    public static boolean checkName(String name) {
        return name.length() <= 30 && name.matches("[a-zA-Z]+");
    }
}
