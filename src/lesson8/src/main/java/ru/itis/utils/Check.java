package ru.itis.utils;

import java.util.regex.Pattern;

public class Check {

    public static boolean checkAll(String name, String email, String password, String passwordRepeat, String checkboxOn) {
        if (name != null && email != null && password != null && passwordRepeat != null && checkboxOn != null)
            return Pattern.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", email) &&
                    Pattern.matches(password, passwordRepeat) &&
                    Pattern.matches(checkboxOn, "true");
        return false;
    }
}
