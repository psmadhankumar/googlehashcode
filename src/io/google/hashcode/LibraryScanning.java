package io.google.hashcode;

import java.io.IOException;
import java.util.*;

public class LibraryScanning {

    private List<Integer> books;
    private Map<Integer, Integer> libraryScore;
    private InputData inputData;
    private Integer remainingDays;

    private static String[] INPUT_FILES = {"a_example.txt" ,"b_read_on.txt" ,"c_incunabula.txt" ,"d_tough_choices.txt" ,"e_so_many_books.txt","f_libraries_of_the_world.txt"};
    private static String[] OUTPUT_FILES= {"a_example_out4.txt" ,"b_read_on_out4.txt" ,"c_incunabula_out4.txt" ,"d_tough_choices_out4.txt" ,"e_so_many_books_out4.txt","f_libraries_of_the_world_out4.txt"};

    public LibraryScanning(InputData input) {
        libraryScore = new HashMap<>();
        this.inputData = input;
        remainingDays = this.inputData.getTotalScanningDays();
        books = inputData.books;
    }
    public static void main(String[] args) throws IOException {

        for(int i = 0; i < INPUT_FILES.length; i++) {
            ReadInputFromFile inputFromFile = new ReadInputFromFile("input//" + INPUT_FILES[i]);
            InputData inputData = inputFromFile.parse();

            LibraryScanning libraryScanning = new LibraryScanning(inputData);
            List<LibraryOutput> libs = libraryScanning.solve(inputData);

            WriteOutputToFile writeOutputToFile = new WriteOutputToFile("output//" + OUTPUT_FILES[i]);
            writeOutputToFile.write(libs);
        }
    }


    public List<LibraryOutput> solve(InputData inputData) {

        List<LibraryOutput> chosenLibs = new ArrayList<>();
        Set<Integer> booksScanned = new HashSet<>();
        while (remainingDays > 0 && !inputData.libraries.isEmpty()) {

            inputData.libraries.forEach(l -> l.booksIds.sort(Comparator.comparingInt(o -> books.get(o))));

            for(Library library : inputData.libraries) {
                library.getBooksIds().removeAll(booksScanned);
                libraryScore.put(library.getLibraryId(), getLibraryScore(library, remainingDays));
            }

            inputData.libraries.sort(Comparator.comparingInt(this::nextLibraryScore).reversed());
            Library chosenLib = inputData.libraries.remove(0);
            booksScanned.addAll(chosenLib.getBooksIds());
            chosenLibs.add(new LibraryOutput(chosenLib.getLibraryId(), chosenLib.getBooksIds()));
            remainingDays -= chosenLib.getSignupDays();
        }
        return chosenLibs;
    }
    private int nextLibraryScore(Library lib) {
        return libraryScore.get(lib.getLibraryId());
    }

    private int getLibraryScore(Library lib1, int dRemaining) {
        long daysRemaining = Math.max(inputData.getTotalScanningDays() - lib1.getSignupDays(), 0);
        /*
        * for each library                                                      L0                      L1
        *   get all books (sorted by score)                                     6 5 3 2 1               6 4 3 1
        *       sum-up only for the books that can be shipped within D days     D days - signup=>5      4
        * */
        return lib1.getBooksIds().stream()
                .limit(daysRemaining * lib1.getShipDay())
                .mapToInt(x -> books.get(x))
                .sum();
    }

}
