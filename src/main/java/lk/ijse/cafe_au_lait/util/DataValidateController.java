package lk.ijse.cafe_au_lait.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidateController {
    static boolean emailCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
