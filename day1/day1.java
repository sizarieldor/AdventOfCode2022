package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = readInputLines("src\\main\\resources\\input1.txt");
        List<Long> elvesCarryingCalories = new ArrayList<>();
        long currentElf = 0;

        for (String line : inputLines) {
            if (!line.equals("")) {
                long newCalorieValue = Long.parseLong(line);
                currentElf += newCalorieValue;

                //edge case last row of input
                int currIndex = inputLines.indexOf(line);
                if (currIndex == inputLines.size() - 1) {
                    elvesCarryingCalories.add(currentElf);
                }
            } else { //line is empty, switch to a new elf
                elvesCarryingCalories.add(currentElf);
                currentElf = 0;
            }
        }

        long top3ElvesCalories = 0;

        //====================================================
        //=====================part 2=========================
        //====================================================

        long maxCaloriesElf = Collections.max(elvesCarryingCalories);
        System.out.println("Elf with most calories is " + maxCaloriesElf);
        top3ElvesCalories+=maxCaloriesElf;

        elvesCarryingCalories.remove(maxCaloriesElf);
        maxCaloriesElf = Collections.max(elvesCarryingCalories);
        System.out.println("Elf with second most calories is "+ maxCaloriesElf);
        top3ElvesCalories+=maxCaloriesElf;

        elvesCarryingCalories.remove(maxCaloriesElf);
        maxCaloriesElf = Collections.max(elvesCarryingCalories);
        System.out.println("Elf with third most calories is "+ maxCaloriesElf);
        top3ElvesCalories+=maxCaloriesElf;

        System.out.println(top3ElvesCalories);


    }

    public static List<String> readInputLines(String filePath) {
        List<String> inputLines = new ArrayList<>();
        Scanner scanner = null;

        try {
            File inputFile = new File(filePath);
            scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return inputLines;
    }
}
