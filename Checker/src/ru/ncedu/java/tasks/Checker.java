/**
 * Created by ultra on 15.08.2017.
 */
package ru.ncedu.java.tasks;

import java.util.List;
import java.util.regex.Pattern;

/**
 * TASK AIM is to understand basic classes of package java.util.regex.<br/>
 * Task:
 * Implement name check for pattern of PL/SQL,
 * valid check for URL and e-mails,
 *
 * @author Alexey Vasiliev
 */
public interface Checker {

    public Pattern getPLSQLNamesPattern();

    public Pattern getHrefURLPattern();

    public Pattern getEMailPattern();

    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException;

    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException;
}

