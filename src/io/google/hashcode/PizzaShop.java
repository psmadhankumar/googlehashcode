package io.google.hashcode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PizzaShop {

    private static String SPACE = " ";
    private static String[] INPUT_FILES = {"a_example.in" ,"b_small.in" ,"c_medium.in" ,"d_quite_big.in" ,"e_also_big.in"};
    private static String[] OUTPUT_FILES= {"a_example.out","b_small.out","c_medium.out","d_quite_big.out","e_also_big.out"};

    /**
     * entry point with in and out files
     * @param inFileName
     * @param outFileName
     */
    public void execute(String inFileName, String outFileName) {
        String[] inputs = readFromFile(inFileName);

        if(inputs[0] == null || inputs[1] == null) {
            throw new IllegalStateException("either firstline or secondline is empty, cannot proceed");
        }

        String targetSlices = inputs[0].split(SPACE)[0];
        String totalSliceOptions = inputs[0].split(SPACE)[1];

        String slicesString = inputs[1];
        List<Integer> slices = stringToIntegers(slicesString);

        int target = Integer.parseInt(targetSlices);
        int givenSlicesCount = Integer.parseInt(totalSliceOptions);

        if(givenSlicesCount != slices.size()) {
            throw new IllegalArgumentException("Given slices count in firstLine doesn't match slices size from secondLine");
        }

        //calculate the sum of all slices
        long currentSum = 0;
        for(Integer x : slices) { currentSum += x; }
        //System.out.println("Total  sum: " + currentSum);

        Set<Integer> skippingIndexes = indexesToSkip(currentSum,target, new ArrayList<>(slices));

        writeToFile(outFileName,slices, skippingIndexes);
    }

    /**
     * util method to get the string into list of integers
     * @param input
     * @return
     */
    public List<Integer> stringToIntegers(String input) {
        String[] split = splitOnSpaceCharacter(input);

        //parse the string to Integer
        List<Integer> in = new ArrayList<>();
        for(String s : split) { in.add(Integer.parseInt(s)); }
        return in;
    }

    public String[] splitOnSpaceCharacter(String string) {
        return string.split(SPACE);
    }

    /**
     * Removes the indexes (pizza slice type(s)) one by one by getting closest biggest slice type. This is done until
     * total-sum is less than or equal to target-sum. Logic uses closest biggest value using binary search.
     *
     * @param totalSum totalsum of all slices
     * @param target target max slices requested from file
     * @param slices list of each slice type
     * @return
     */
    public Set<Integer> indexesToSkip(long totalSum, int target, List<Integer> slices) {
        Set<Integer> indexToSkip = new HashSet<>();
        if(totalSum == target) { return indexToSkip; }
        if(totalSum < target) { throw new IllegalStateException("Total Sum of slices cannot be less then requested max slices"); }

        //Dealing with sum larger than target
        while(totalSum > target) {
            int index = findClosetIndex(slices,  totalSum - target);
            totalSum = totalSum - slices.get(index);
            indexToSkip.add(index);
            slices.remove(index);
        }

        //System.out.println("Closet sum: " + currentSum);
        //System.out.println("indexes to remove: " + indexToSkip);

        return indexToSkip;
    }

    /**
     * Finds closest biggest value using binary search
     * @param array
     * @param target
     * @return
     */
    public int findClosetIndex(List<Integer> array, long target) {
        int lo = 0;
        int hi = array.size()-1;

        while(lo < hi) {
            int mid = lo + (hi - lo)/2;
            if(target == array.get(mid)) return mid;
            else if(target < array.get(mid)) {hi = mid;}
            else {lo = mid + 1;}
        }

        return hi == -1 ? 0 : hi;
    }

    /**
     * reads the file and returns first line in String[0] and second line in String[1]
     * @param fileName
     * @return
     */
    public String[] readFromFile(String fileName) {
        String[] inputs = new String[2];

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            inputs[0] = bufferedReader.readLine();
            inputs[1] = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return inputs;
    }

    /**
     * Writes the first line with size of indexes that are supposed to be in result
     * And second line with actual slices
     *
     * @param fileName
     * @param slices
     * @param indexesToSkip
     */
    public void writeToFile(String fileName, List<Integer> slices, Set<Integer> indexesToSkip) {

        try {
            //System.out.println("Writing to the file:" + fileName);
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.println(slices.size() - indexesToSkip.size());
            //System.out.println("total slices:" +slices.size());
            //System.out.println("slices to skip:" + indexesToSkip.size());

            for(int idx = 0; idx < slices.size(); idx++) {
                if(indexesToSkip.contains(idx)) continue;
                printWriter.print(idx + " ");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        for(int i = 0; i < INPUT_FILES.length; i++) {
            PizzaShop pizzaShop = new PizzaShop();
            pizzaShop.execute("input//" + INPUT_FILES[i], "output//" + OUTPUT_FILES[i]);
        }
    }
}


