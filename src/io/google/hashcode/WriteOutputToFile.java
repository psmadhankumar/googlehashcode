package io.google.hashcode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WriteOutputToFile {


    String filePath;

    public WriteOutputToFile(String filePath) {
        this.filePath = filePath;
    }

    public void write(List<LibraryOutput> librarySubmissions) throws IOException {
        String firstLine = String.valueOf(librarySubmissions.size());

        StringBuilder sb = new StringBuilder();
        for(LibraryOutput lo : librarySubmissions) {
            sb.append(lo.getLibraryIndex()).append(" ").append(lo.getBooksToSend().size()).append("\n");
            for(Integer x : lo.getBooksToSend()) {
                sb.append(x).append(" ");
            }
            sb.append("\n");
        }

        try {
            PrintWriter printWriter = new PrintWriter(filePath);
            printWriter.println(firstLine);
            printWriter.println(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
