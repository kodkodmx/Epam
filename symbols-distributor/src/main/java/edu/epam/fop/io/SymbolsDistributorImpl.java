package edu.epam.fop.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

class SymbolsDistributorImpl implements SymbolsDistributor {

    @Override
    public void distribute(File inputFile, Map<Predicate<Integer>, File> outputMapping) {
        if (!inputFile.exists() || !inputFile.canRead() || !inputFile.isFile()) {
            throw new IllegalArgumentException("Input file is not valid.");
        }

        Map<File, FileOutputStream> outputStreams = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            // Open output streams for all files and store them in the map
            for (Map.Entry<Predicate<Integer>, File> entry : outputMapping.entrySet()) {
                File file = entry.getValue();
                if (!file.exists()) {
                    file.createNewFile();
                }
                outputStreams.put(file, new FileOutputStream(file, false));
            }

            int character;
            while ((character = fis.read()) != -1) {
                for (Map.Entry<Predicate<Integer>, File> entry : outputMapping.entrySet()) {
                    if (entry.getKey().test(character)) {
                        FileOutputStream fos = outputStreams.get(entry.getValue());
                        if (fos != null) {
                            fos.write(character);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during file processing", e);
        } finally {
            // Close all output streams
            for (FileOutputStream fos : outputStreams.values()) {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // Ignore exceptions during closing streams
                    }
                }
            }
        }
    }
}
