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
        Map<String, String> gameRequiredOutcomes = getMapRequiredOutcomes();

        for (String line : inputLines) {
            String[] lineToArr = line.split(" ");
            String enemyPick = lineToArr[0];
            String requiredOutcome = gameRequiredOutcomes.get(lineToArr[1]);
            String requiredPick = chooseNecessaryPick(enemyPick,requiredOutcome);
            int resultThisRound = gameOutcomeValues.get(requiredOutcome) + playerChoiceValues.get(requiredPick);
            totalResult += resultThisRound;
        }

        System.out.println(totalResult);
    }

    private static Map<String, String> getMapRequiredOutcomes() {
        Map<String, String> requiredOutcomesMap = new HashMap<>();
        requiredOutcomesMap.put("X", "loss");
        requiredOutcomesMap.put("Y", "draw");
        requiredOutcomesMap.put("Z", "win");
        return requiredOutcomesMap;
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
    
    public static String chooseNecessaryPick(String enemyPick, String outcome){
        switch (enemyPick) {
            case "A": //enemy picks rock
                switch (outcome) {
                    case "loss": //player picks scissors
                        return "Z";
                    case "draw": //player picks rock
                        return "X";
                    case "win": //player picks paper
                        return "Y";
                }
                break;
            case "B": //enemy picks paper
                switch (outcome) {
                    case "loss": //player picks rock
                        return "X";
                    case "draw": //player picks paper
                        return "Y";
                    case "win": //player picks scissors
                        return "Z";
                }
                break;
            case "C": //enemy picks scissors
                switch (outcome) {
                    case "loss": //player picks paper
                        return "Y";
                    case "draw": //player picks scissors
                        return "Z";
                    case "win": //player picks rock
                        return "X";
                }
                break;
        }
        return "";
    }
}
