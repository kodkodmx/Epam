// CipherCreator.java
package com.epam.autotasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CipherCreator {

    private CipherCreator() {
    }

    public static StringBuilder cipherText(File input) {
        if (input.isDirectory()) {
            throw new IllegalArgumentException("Input file is a directory");
        }

        if (!input.exists()) {
            throw new IllegalArgumentException("Input file does not exist");
        }

        StringBuilder result = new StringBuilder();
        try (TransformerInputStream tis = new TransformerInputStream(new FileInputStream(input))) {
            int b;
            while ((b = tis.read()) != -1) {
                if (Character.isLetter(b) || b == '\n') {
                    result.append((char) b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}