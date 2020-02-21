package io.google.hashcode;

import java.util.List;

public class LibraryOutput {
    int libraryIndex;
    List<Integer> booksToSend;

    public LibraryOutput(int libraryIndex, List<Integer> booksToSend) {
        this.libraryIndex = libraryIndex;
        this.booksToSend = booksToSend;
    }

    public int getLibraryIndex() {
        return libraryIndex;
    }

    public List<Integer> getBooksToSend() {
        return booksToSend;
    }
}
