package ru.ncedu.java.tasks;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.Character.isDigit;

/**
 * Created by ultra on 12.08.2017.
 */
public class StringFilterImpl implements StringFilter{
    public Set<String> setOfStrings = new HashSet<String>();
    @Override
    public void add(String s) {
        if (s != null)
            setOfStrings.add(s.toLowerCase());
        else
            setOfStrings.add(null);
    }

    @Override
    public boolean remove(String s) {
        if (s != null)
            return setOfStrings.remove(s.toLowerCase());
        else
            return setOfStrings.remove(null);
    }

    @Override
    public void removeAll() {
        setOfStrings.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return setOfStrings;
    }

    private interface Filter {
        boolean checkString(String checkedString);
    }

    public Iterator<String> getIterator(Filter filter) {
        Set<String> temp = new HashSet<String>();
        for (Iterator<String> i = setOfStrings.iterator(); i.hasNext();) {
            String str = i.next();
            if (filter.checkString(str))
                temp.add(str);
        }
        return temp.iterator();
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        final String charsFinal = chars == null ? null : chars.toLowerCase();
        Filter filter = new Filter() {
            @Override
            public boolean checkString(String s) {
                if ( charsFinal == null || charsFinal.equals("")) {
                    return true;
                } else
                if (s != null){
                    return s.contains(charsFinal);
                }else {
                    return false;
                }
            }
        };

        return getIterator(filter);
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        final String beginFinal = begin == null ? null : begin.toLowerCase();
        Filter filter = new Filter() {
            @Override
            public boolean checkString(String s) {
                if (beginFinal == null || beginFinal.equals("")) {
                    return true;
                } else
                if (s != null){
                    return s.startsWith(beginFinal);
                } else {
                    return false;
                }
            }
        };

        return getIterator(filter);
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        final String formatFinal = format == null ? null : format.toLowerCase();
        Filter filter = new Filter() {
            @Override
            public boolean checkString(String s) {

                if (formatFinal == null || formatFinal.equals("")) {
                    return true;
                } else
                if (s != null && s.length() == formatFinal.length()) {
                    char[] temp = s.toCharArray();
                    char[] form = formatFinal.toCharArray();
                    boolean wrongFormat = false;
                    for (int i = 0; i < form.length; i++) {
                        if (form[i] == '#') {
                            if (!isDigit(temp[i])) {
                                wrongFormat = true;
                                break;
                            }
                        } else {
                            if (temp[i] != form[i]) {
                                wrongFormat = true;
                                break;
                            }
                        }
                    }
                    return !wrongFormat;
                }
                return false;
            }
        };

        return getIterator(filter);
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        final String patternFinal = pattern == null ? null : pattern.toLowerCase();
        Filter filter = new Filter() {
            @Override
            public boolean checkString(String s) {
                if (patternFinal == null || patternFinal.equals("")) {
                    return true;
                } else
                if (s != null) {
                    char[] temp = s.toCharArray();
                    char[] patt = patternFinal.toCharArray();
                    boolean wrongFormat = false;
                    boolean lastWasStar = false;
                    boolean starExisted = false;
                    int lastStarIndex = 0;

                    if (temp.length < patt.length)
                        return false;

                    for (int i = 0, j = 0; i < temp.length || j < patt.length; i++, j++) {
                        if ((j == patt.length && i < temp.length) ||
                            (i == temp.length && j < patt.length)) {
                            wrongFormat = true;
                            break;
                        } else
                        if (patt[j] == '*') {
                            //star was last in pattern -> skip all cycle
                            if (j == patt.length - 1)
                                break;
                            lastWasStar = true;
                            lastStarIndex = j;
                            starExisted = true;
                            i--;

                        } else {
                            if (lastWasStar && patt[j] == temp[i]) {
                                lastWasStar = false;
                                continue;
                            }

                            if (lastWasStar && patt[j] != temp[i]) {
                                j--;
                                continue;
                            }

                            if (!lastWasStar && patt[j] != temp[i]) {
                                if (starExisted) {
                                    j = lastStarIndex - 1;
                                    continue;
                                } else {
                                    wrongFormat = true;
                                    break;
                                }

                            }

                            if (!lastWasStar && patt[j] == temp[i]) {
                                continue;
                            }
                        }
                    }
                    return !wrongFormat;
                }
                return false;
            }
        };

        return getIterator(filter);
    }
}
