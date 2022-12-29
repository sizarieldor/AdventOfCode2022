package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = readInput("src\\main\\resources\\input1.txt");
        int countSuccesses = 0;
        for (String inputLine : inputLines) {
            if (checkIfAnyOverlap(inputLine)) { //use either of the two methods below
                countSuccesses++;
            }
        }
        System.out.println(countSuccesses);
    }

    public static List<String> readInput(String path) {
        List<String> inputLines = new ArrayList<>();
        Scanner scanner = null;

        try {
            File inputFile = new File(path);
            scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return inputLines;
    }

    public static boolean checkIfContainment(String line) { //for part 1 use this method on line 15
        String[] halvesOfLine = line.split(",");
        int[] firstHalfInts = Arrays.stream(halvesOfLine[0].split("-"))
                .mapToInt(Integer::valueOf)
                .toArray();
        int[] secondHalfInts = Arrays.stream(halvesOfLine[1].split("-"))
                .mapToInt(Integer::valueOf)
                .toArray();

        //overlap logic
        boolean oneContainsTheOther = false;
        boolean firstBiggerThanSecond = false;
        boolean secondBiggerThanFirst = false;
        if (firstHalfInts[0] <= secondHalfInts[0] && firstHalfInts[1] >= secondHalfInts[1]) {
            firstBiggerThanSecond = true;
        }
        if (secondHalfInts[0] <= firstHalfInts[0] && secondHalfInts[1] >= firstHalfInts[1]) {
            secondBiggerThanFirst = true;
        }
        if (firstBiggerThanSecond || secondBiggerThanFirst) {
            oneContainsTheOther = true;
        }
        return oneContainsTheOther;
    }

    public static boolean checkIfAnyOverlap(String line){ //for part 2 use this method on line 15
        String[] halvesOfLine = line.split(",");
        int[] firstHalfInts = Arrays.stream(halvesOfLine[0].split("-"))
                .mapToInt(Integer::valueOf)
                .toArray();
        int[] secondHalfInts = Arrays.stream(halvesOfLine[1].split("-"))
                .mapToInt(Integer::valueOf)
                .toArray();

        //overlap logic
        int firstHalfMax = firstHalfInts[1];
        int secondHalfMin = secondHalfInts[0];
        int firstHalfMin = firstHalfInts[0];
        int secondHalfMax = secondHalfInts[1];
        boolean arraysOverlap = false;
        boolean firstOverlapsToTheLeft = false;
        boolean firstOverlapsToTheRight  = false;
        if(firstHalfMax >= secondHalfMin && firstHalfMin <= secondHalfMax){
            firstOverlapsToTheLeft = true;
        }
        if(firstHalfMin <= secondHalfMax && firstHalfMax >= secondHalfMin){
            firstOverlapsToTheRight = true;
        }
        if(firstOverlapsToTheLeft || firstOverlapsToTheRight){
            arraysOverlap = true;
        }
       return arraysOverlap;
    }
}
