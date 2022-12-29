package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//quite awful, overall
public class Main {
    public static void main(String[] args) {
        List<String> inputAsLines = readInput("src\\main\\resources\\input1.txt");
        List<String> initialStacking = new ArrayList<>(); //rows of boxes as entries in a list

        for (String inputLine : inputAsLines) {
            if (inputLine.equals("")) {
                break;
            }
            initialStacking.add(inputLine.replace(" ", "+")); //this just makes it easier to see during debugging
        }

        List<String> semiFinalStacking = new ArrayList<>(); //rows of boxes as entries in a list, stripped of '[', ']', and ' '
        for (String stackingLine : initialStacking) {
            String[] lineBrokenUpArr = splitTextIntoQuadruples(stackingLine, 4).toArray(new String[0]); //split into 4 chars
            StringBuilder lineOfStrippedBoxes = new StringBuilder();
            for (String charQuadruplet : lineBrokenUpArr) {
                String quadrupletStripped = charQuadruplet.substring(1, 2);
                lineOfStrippedBoxes.append(quadrupletStripped);
            }
            semiFinalStacking.add(lineOfStrippedBoxes.toString());
        }

        //fix padding
        int longestStringLen = Integer.MIN_VALUE;
        for (String s : semiFinalStacking) {
            if (s.length() > longestStringLen) {
                longestStringLen = s.length();
            }
        }

        //pad them, because IntelliJ removes trailing spaces at the end of lines of .txt files, 
        for (String s : semiFinalStacking) {
            if (s.length() < longestStringLen) {
                int spacesToPad = longestStringLen - s.length();
                StringBuilder properlyPaddedLine = new StringBuilder();
                properlyPaddedLine.append(s);
                for (int i = 0; i < spacesToPad; i++) {
                    properlyPaddedLine.append('+');
                }
                semiFinalStacking.indexOf(s);
                semiFinalStacking.set(semiFinalStacking.indexOf(s), properlyPaddedLine.toString());
            }
        }

        //convert this to a proper matrix
        int rowCount = semiFinalStacking.size() - 1;
        int columnCount = semiFinalStacking.get(0).length();
        char[][] inputsMatrix = new char[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            inputsMatrix[i] = semiFinalStacking.get(i).toCharArray();
        }

        //transpose matrix
        char[][] finalStacking = new char[columnCount][rowCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                finalStacking[j][i] = inputsMatrix[i][j];
            }
        }
        
        //adjust matrix positions
        for (int i = 0; i < finalStacking.length; i++) {
            finalStacking[i] = reverseArray(finalStacking[i]);
        }

        //put the transformed data into stacks
        ArrayDeque<Character>[] stacks = new ArrayDeque[9];
        for (int i = 0; i < 9; i++) {
            ArrayDeque<Character> oneOfTheStacks = new ArrayDeque<>();
            for (int j = 0; j < finalStacking[i].length; j++) {
                char topChar = finalStacking[i][j];
                if (topChar != '+') {
                    oneOfTheStacks.push(finalStacking[i][j]);
                }
            }
            stacks[i] = oneOfTheStacks;
        }

        //FINALLY can do commands
        int indexStartCommands = inputAsLines.indexOf("") + 1;
        for (int i = indexStartCommands; i < inputAsLines.size(); i++) {
            String[] command = inputAsLines.get(i).split(" ");
            int quantity = Integer.parseInt(command[1]);
            int sourceStackIndex = Integer.parseInt(command[3]) - 1;
            int destinationStackIndex = Integer.parseInt(command[5]) - 1;


            /* part 1 solution */
            for (int j = 0; j < quantity; j++) {
                ArrayDeque<Character> sourceStack = stacks[sourceStackIndex];
                ArrayDeque<Character> destinationStack = stacks[destinationStackIndex];
                destinationStack.push(sourceStack.pop());
            }

            /* part 2 solution
            
             for (int j = 0; j < quantity; j++) {
                ArrayDeque<Character> sourceStack = stacks[sourceStackIndex];
                crateBundle.push(sourceStack.pop());
            }
            for (int j = 0; j < quantity; j++) {
                ArrayDeque<Character> destinationStack = stacks[destinationStackIndex];
                destinationStack.push(crateBundle.pop());
            }

            */
        }

        //get final result
        StringBuilder crateOnTopOfEach = new StringBuilder();
        for (int i = 0; i < stacks.length; i++) {
            char topChar = stacks[i].pop();
            crateOnTopOfEach.append(topChar);
        }
        System.out.println(crateOnTopOfEach);
    }

    public static List<String> readInput(String path) {
        List<String> inputAsLines = new ArrayList<>();
        Scanner scanner = null;
        try {
            File inputFile = new File(path);
            scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                inputAsLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputAsLines;
    }

    public static List<String> splitTextIntoQuadruples(String text, int n) {
        List<String> results = new ArrayList<>();
        int length = text.length();

        for (int i = 0; i < length; i += n) {
            results.add(text.substring(i, Math.min(length, i + n)));
        }

        return results;
    }

    public static char[] reverseArray(char[] inputArr) {
        int len = inputArr.length;
        char[] inputReversed = new char[len];
        for (int i = 0; i < len; i++) {
            inputReversed[i] = inputArr[len - 1 - i];
        }
        return inputReversed;
    }
}
