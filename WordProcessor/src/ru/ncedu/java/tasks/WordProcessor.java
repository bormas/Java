package ru.ncedu.java.tasks;

/**
 * Created by ultra on 16.08.2017.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public interface WordProcessor
{
    /**
     * @return current text or null
     */
    String getText ();
    /**
     * obtains text to work with
     * @param src text for processing
     * @throws IllegalArgumentException if <code>src == null</code>
     */
    void setSource (String src);
    /**
     * reads the text from file with name srcFile
     * @param srcFile file path
     * @throws IOException in case of reading errors
     * @throws IllegalArgumentException if <code>srcFile == null</code>
     */
    void setSourceFile (String srcFile) throws IOException;
    /**
     * reads the text from current stream
     * @param fis input stream
     * @throws IOException in case of reading errors
     * @throws IllegalArgumentException if <code>fis == null</code>
     */
    void setSource (FileInputStream fis) throws IOException;
    /**
     * Looks for words which begin with following beginning
     * all symbols are low-cased <br/>
     * if <code>begin</code> - "" or <code>null</code>,
     * then result contains all words<br/>
     * @param begin first symbols
     * @return words which begin with following beginning
     * @throws IllegalStateException if text == null
     */
    Set<String> wordsStartWith (String begin);
}
