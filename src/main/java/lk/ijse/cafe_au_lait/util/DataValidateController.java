package lk.ijse.cafe_au_lait.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidateController {
    public static boolean emailCheck(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean contactCheck(String contact) {
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    public static boolean customerIdValidate(String custId) {
        String customerRegex = "^(C)[0-9]{3}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custId);
        return matcher.matches();
    }

    public static boolean customerNameValidate(String custName) {
        String customerRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(customerRegex);
        Matcher matcher = pattern.matcher(custName);
        return matcher.matches();
    }
}
