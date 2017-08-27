package ru.ncedu.java.tasks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ultra on 16.08.2017.
 */
public class WordProcessorImpl implements WordProcessor {
    String text;
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setSource(String src) {
        if (src == null)
            throw new IllegalArgumentException("Source text is null");

        text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        if (srcFile == null)
            throw new IllegalArgumentException("File name is null");

        setSource(new FileInputStream(srcFile));
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if (fis == null)
            throw new IllegalArgumentException("File input stream is null");

        byte[] result = new byte[fis.available()];
        fis.read(result);
        text = new String(result);
        fis.close();
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if (text == null)
            throw new IllegalStateException("No text to work with");

        Set<String> stringSet = new HashSet<>();

        for(String st : text.toLowerCase().split("\\s+")) {
            if(begin == null || begin.length() == 0 || st.startsWith(begin.toLowerCase())){
                stringSet.add(st);
            }
        }

        return stringSet;
    }
}
