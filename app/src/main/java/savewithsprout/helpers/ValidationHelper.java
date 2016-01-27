package savewithsprout.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by menushkaweeratunga on 2015-12-01.
 */
public class ValidationHelper {

    public static final int PASSWORD_VALID = 0;
    public static final int PASSWORD_INVALID_LENGTH = 1;
    public static final int PASSWORD_INVALID_CASE = 2;
    public static final int PASSWORD_INVALID_SYMBOL = 3;
    public static final int PASSWORD_INVALID_DIGIT = 4;

    public static final String SYMBOLS = "`~!@#$%^&*()_-+={}[]\\|:;\"'<>,.?/";
    public static final String DIGITS = "1234567890";

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static int validateLogin(String email, String phone, String password){
        if (phone.length() == 0){
            //return 1;
        }
        if (password.length() == 0){
            return 2;
        }
        if (!isEmailValid(email)){
            return 3;
        }
        if (!isPhoneValid(phone)){
            //return 4;
        }

        return 0;
    }

    public static int validateCreateAccount(String first, String last, String email, String phone, String password, String passwordConfirm){
        if (first.length() == 0){
            return 1;
        }
        if (last.length() == 0){
            return 2;
        }
        if (email.length() == 0){
            return 3;
        }
        if (phone.length() == 0){
            return 4;
        }
        if (password.length() == 0){
            return 5;
        }
        if (passwordConfirm.length() == 0){
            return 6;
        }
        if (!isEmailValid(email)){
            return 7;
        }
        if (!isPhoneValid(phone)){
            return 8;
        }
        if (isPasswordValid(password) != 0){
            return 9;
        }
        if (!password.equals(passwordConfirm)){
            return 10;
        }
        return 0;
    }

    private static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isPhoneValid(String phone){
        return phone.length() == 10;
    }

    public static int isPasswordValid(String password){
        if (password.length() < 6){
            return PASSWORD_INVALID_LENGTH;
        }

        if (!checkForLowerCase(password) || !checkForUpperCase(password)){
            return PASSWORD_INVALID_CASE;
        }

        if (!checkForSymbol(password)){
            return PASSWORD_INVALID_SYMBOL;
        }

        if (!checkForDigit(password)){
            return PASSWORD_INVALID_DIGIT;
        }

        return PASSWORD_VALID;
    }

    private static boolean checkForUpperCase(String password){
        String[] characters = "abcdefghijklmnopqrstuvwxyz".split("");
        for (String c: characters){
            if (password.contains(c)){
                return true;
            }
        }
        return false;
    }

    private static boolean checkForLowerCase(String password){
        String[] characters = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
        for (String c: characters){
            if (password.contains(c)){
                return true;
            }
        }
        return false;
    }

    private static boolean checkForSymbol(String password){
        String[] characters = SYMBOLS.split("");
        for (String c: characters){
            if (password.contains(c)){
                return true;
            }
        }
        return false;
    }

    private static boolean checkForDigit(String password){
        String[] characters = DIGITS.split("");
        for (String c: characters){
            if (password.contains(c)){
                return true;
            }
        }
        return false;
    }
}
