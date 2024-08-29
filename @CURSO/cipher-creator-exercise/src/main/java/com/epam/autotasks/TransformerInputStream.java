// TransformerInputStream.java
package com.epam.autotasks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TransformerInputStream extends FilterInputStream {

    protected TransformerInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b == -1) {
            return -1;
        }
        if (Character.isLetter(b)) {
            if (Character.isUpperCase(b)) {
                return 'A' + (b - 'A' + 2) % 26;
            } else {
                return 'a' + (b - 'a' + 2) % 26;
            }
        }
        return b;
    }

    @Override
    public void close() throws IOException {
        super.close();
        System.out.println("Stream closed.");
    }
}

