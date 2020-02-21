package io.google.hashcode;

import java.util.List;

public class Library {

    private int libraryId;
    private int booksCount;
    private int signupDays;
    private int shipDay;

    List<Integer> booksIds;

    public Library(int booksCount, int signupDays, int shipDay, List<Integer> booksIds, int libraryIndex) {
        this.booksCount = booksCount;
        this.signupDays = signupDays;
        this.shipDay = shipDay;
        this.booksIds = booksIds;
        this.libraryId = libraryIndex;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getSignupDays() {
        return signupDays;
    }

    public int getShipDay() {
        return shipDay;
    }

    public List<Integer> getBooksIds() {
        return booksIds;
    }

    public int getLibraryId() {
        return libraryId;
    }

    @Override
    public String toString() {
        return "Library {" +
                "booksCount=" + booksCount +
                ", signupDays=" + signupDays +
                ", shippingBooksperDay=" + shipDay +
                ", books=" + booksIds +
                '}';
    }
}
