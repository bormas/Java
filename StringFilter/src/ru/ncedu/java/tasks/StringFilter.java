/**
 * Created by ultra on 12.08.2017.
 */
package ru.ncedu.java.tasks;

import java.util.Collection;
import java.util.Iterator;

public interface StringFilter {
    /**
     * Adds string to our set
     * If string is already exists, then do nothing
     * @param s can be null
     */
    void add(String s);
    /**
     * Deletes one string from our set
     * @param s can be null
     * @return true if string was deleted, false if string was not in set
     */
    boolean remove(String s);
    /**
     * clears set
     */
    void removeAll();
    /**
     * returns collection
     * collection can not contain same objects, but it can contain null
     */
    Collection<String> getCollection();

    /**
     * looks for all strings which contains chars
     * @param chars chars which are included in sought strings
     *  all symbols are in lower case
     * @return strings which contain chars
     */
    Iterator<String> getStringsContaining(String chars);
    /**
     * looks for strings in set which begin with param begin
     * @param begin first symbols of sought strings
     * begin with low case
     * if begin == null or == "" then return all strings
     * @return strings, which begin with param
     */
    Iterator<String> getStringsStartingWith(String begin);
    /**
     * looks for strings in set according to format
     * Format can contain # (place for a digit from 0 to 9) and other symbols.
     * Format examples: "(###)###-####" (phone), "# ###" (integer),
     *  "-#.##" (negative float).
     *  inplementation of this method does not imply usage of regex
     * @param format number format
     * @return strings, which fit our format
     */
    Iterator<String> getStringsByNumberFormat(String format);
    /**
     * looks for strings which satisfy pattern
     * pattern can contain *
     * * means that there can be none or more other symbols
     * <a href="http://en.wikipedia.org/wiki/Wildcard_character#File_and_directory_patterns">About * wildcard</a>.<br/>
     * Pattern examples, which this string satisfy "distribute": "distr*", "*str*", "di*bute*".<br/>
     * @param pattern given patters (all letters are in low case)
     * @return strings, which fit our pattern
     */
    Iterator<String> getStringsByPattern(String pattern);
}
