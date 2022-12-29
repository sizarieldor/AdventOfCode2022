package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = readInput("src\\main\\resources\\input1.txt");
        int totalResult = 0;
        Map<String, Integer> playerChoiceValues = getMapPlayer();
        Map<String, Integer> gameOutcomeValues = getMapOutcomes();

        for (String line : inputLines) {
            String[] lineToArr = line.split(" ");
            String enemyPick = lineToArr[0];
            String playerPick = lineToArr[1];
            String roundOutcome = calculateRound(enemyPick,playerPick);
            int resultThisRound = gameOutcomeValues.get(roundOutcome) + playerChoiceValues.get(playerPick);
            totalResult += resultThisRound;
        }

        System.out.println(totalResult);
    }

    public static List<String> readInput(String path) {
        List<String> inputLines = new ArrayList<>();
        Scanner scan = null;

        try {
            File inputFile = new File(path);
            scan = new Scanner(inputFile);
            while (scan.hasNextLine()) {
                inputLines.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return inputLines;
    }

    public static Map<String, Integer> getMapPlayer() {
        Map<String, Integer> playerScoring = new HashMap<>();
        playerScoring.put("X", 1); //player rock
        playerScoring.put("Y", 2); //player paper
        playerScoring.put("Z", 3); //player scissors
        return playerScoring;
    }


    public static Map<String, Integer> getMapOutcomes() {
        Map<String, Integer> outcomes = new HashMap<>();
        outcomes.put("win", 6);
        outcomes.put("loss", 0);
        outcomes.put("draw", 3);
        return outcomes;
    }

    public static String calculateRound(String enemyPick, String playerPick) {
        switch (enemyPick) {
            case "A": //enemy picks rock
                switch (playerPick) {
                    case "X": //player picks rock
                        return "draw";
                    case "Y": //player picks paper
                        return "win";
                    case "Z": //player picks scissors
                        return "loss";
                }
                break;
            case "B": //enemy picks paper
                switch (playerPick) {
                    case "X": //player picks rock
                        return "loss";
                    case "Y": //player picks paper
                        return "draw";
                    case "Z": //player picks scissors
                        return "win";
                }
                break;
            case "C": //enemy picks scissors
                switch (playerPick) {
                    case "X": //player picks rock
                        return "win";
                    case "Y": //player picks paper
                        return "loss";
                    case "Z": //player picks scissors
                        return "draw";
                }
                break;
        }
        return "";
    }
}