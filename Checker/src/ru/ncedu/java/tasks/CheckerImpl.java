package ru.ncedu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ultra on 15.08.2017.
 */
public class CheckerImpl implements Checker {
    @Override
    public Pattern getPLSQLNamesPattern() {
        return Pattern.compile("[a-zA-Z]+[a-zA-Z_$0-9]{0,29}");
    }

    @Override
    public Pattern getHrefURLPattern() {
        return Pattern.compile("<\\s*(a|A)\\s*[hH][rR][eE][fF]\\s*=" +
                               "\\s*((\"(https?://)?(www\\.)?[A-Za-z0-9 /@_?#:.&%\\\\]*\")|" +
                               "([A-Za-z0-9/:._?&\\%#@\\\\]*))\\s*/?>");
    }

    @Override
    public Pattern getEMailPattern() {
        return Pattern.compile("[A-Za-z0-9][A-Za-z0-9_.-]{0,20}[A-Za-z0-9]@" +
                               "([a-zA-Z0-9]+[a-zA-Z0-9-]*[a-zA-Z0-9]+\\.)+" +
                               "((ru)|(com)|(net)|(org))");
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if (inputString == null && pattern == null) {
            return true;
        } else if (inputString == null || pattern == null) {
            throw new IllegalArgumentException("One of the arguments is null");
        } else {
            Matcher matcher = pattern.matcher(inputString);
            return matcher.matches();
        }
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        if (inputString == null || pattern == null) {
            throw new IllegalArgumentException("One of the arguments is null");
        } else {
            Matcher matcher = pattern.matcher(inputString);
            List<String> temp = new ArrayList<>();
            while (matcher.find()) {
                temp.add(matcher.group());
            }
            return temp;
        }
    }
}
