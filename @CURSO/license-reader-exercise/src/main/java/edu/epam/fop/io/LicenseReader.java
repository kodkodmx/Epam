package edu.epam.fop.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LicenseReader {

    public void collectLicenses(File root, File outputFile) {
        if (root == null || outputFile == null) {
            throw new IllegalArgumentException("root and outputFile cannot be null.");
        }
        if (!root.exists() || !root.canRead() || (root.isDirectory() && !root.canExecute())) {
            throw new IllegalArgumentException("Invalid root directory.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            processDirectory(root, writer);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error processing files.", e);
        }
    }

    private void processDirectory(File directory, BufferedWriter writer) throws IOException {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        processDirectory(file, writer);
                    } else {
                        processFileWithReader(file, writer);
                    }
                }
            }
        } else {
            processFileWithReader(directory, writer);
        }
    }

    private void processFileWithReader(File file, BufferedWriter writer) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();

            if (firstLine == null || !firstLine.trim().equals("---")) {
                return; // If it doesn't start with a license header, we consider it a regular file
            }

            processLicenseFileContent(reader, file, writer);
        }
    }

    private void processLicenseFileContent(BufferedReader reader, File file, BufferedWriter writer) throws IOException {
        String license = null;
        String issuedBy = null;
        String issuedOn = null;
        String expiresOn = null;

        String line;
        while ((line = reader.readLine()) != null && !line.trim().equals("---")) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                switch (parts[0].trim()) {
                    case "License" -> license = parts[1].trim();
                    case "Issued by" -> issuedBy = parts[1].trim();
                    case "Issued on" -> issuedOn = parts[1].trim();
                    case "Expires on" -> expiresOn = parts[1].trim();
                }
            }
        }

        if (line == null || !line.trim().equals("---")) {
            throw new IllegalArgumentException("File does not end with license footer: " + file.getPath());
        }

        if (license == null || issuedBy == null || issuedOn == null) {
            throw new IllegalArgumentException("Missing required license fields in " + file.getPath());
        }

        if (!isValidDate(issuedOn)) {
            throw new IllegalArgumentException("Invalid date format for 'Issued on' in " + file.getPath());
        }

        if (expiresOn != null && !expiresOn.equals("unlimited") && !isValidDate(expiresOn)) {
            throw new IllegalArgumentException("Invalid date format for 'Expires on' in " + file.getPath());
        }

        String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");

        String output = String.format(
                "License for %s is %s issued by %s [%s - %s]",
                fileNameWithoutExtension,
                license,
                issuedBy,
                issuedOn,
                expiresOn != null ? expiresOn : "unlimited"
        );

        writer.write(output);
        writer.newLine();
    }

    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}