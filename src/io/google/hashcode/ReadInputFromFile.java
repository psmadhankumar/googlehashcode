package io.google.hashcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ReadInputFromFile {

    private String filePath;

    private static String SPACE = " ";

    public ReadInputFromFile(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public InputData parse() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String[] firstLineData = br.readLine().split(SPACE);
        int totalBooks = Integer.parseInt(firstLineData[0]);
        int totalLibaries = Integer.parseInt(firstLineData[1]);
        int totalDaysForScanning = Integer.parseInt(firstLineData[2]);

        String secondLine = br.readLine();
        List<Integer> books = Arrays.stream(secondLine.split(SPACE)).map(Integer::parseInt).collect(toList());

        List<Library> libraries = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null && !line.equals("")) {
            String[] libraryFirstLine = line.split(SPACE);
            int booksCount = Integer.parseInt(libraryFirstLine[0]);
            int signupDays = Integer.parseInt(libraryFirstLine[1]);
            int shipsDay = Integer.parseInt(libraryFirstLine[2]);
            String booksInLibraryLine = br.readLine();
            List<Integer> booksInLibrary = Arrays.stream(booksInLibraryLine.split(SPACE)).map(Integer::parseInt).collect(Collectors.toList());
            libraries.add(new Library(booksCount, signupDays, shipsDay, booksInLibrary, libraries.size()));
        }

        return new InputData(totalBooks, totalLibaries, totalDaysForScanning, books, libraries);
    }
}
