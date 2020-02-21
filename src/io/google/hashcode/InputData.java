package io.google.hashcode;

import java.util.List;

public class InputData {
    private int totalBooks;
    private int totalLibraries;
    private int totalScanningDays;

    List<Integer> books;
    List<Library> libraries;

    public InputData(int totalBooks, int totalLibraries, int totalScanningDays, List<Integer> books, List<Library> libraries) {
        this.totalBooks = totalBooks;
        this.totalLibraries = totalLibraries;
        this.totalScanningDays = totalScanningDays;
        this.books = books;
        this.libraries = libraries;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getTotalLibraries() {
        return totalLibraries;
    }

    public int getTotalScanningDays() {
        return totalScanningDays;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "totalBooks=" + totalBooks +
                ", totalLibraries=" + totalLibraries +
                ", totalScanningDays=" + totalScanningDays +
                ", books=" + books +
                ", libraries=" + libraries +
                '}';
    }
}
