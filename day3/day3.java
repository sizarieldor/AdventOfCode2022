package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> inputs = readInput("src\\main\\resources\\input1.txt");
        int totalValue = 0;

        for (String line : inputs) {
            //split into halves
            char[] firstHalf = getHalfOfArray(line, "first");
            char[] secondHalf = getHalfOfArray(line, "second");

            //find matching chars
            Set<Character> setFirstHalf = new HashSet<>();
            Set<Character> setSecondHalf = new HashSet<>();
            for (char c : firstHalf) {
                setFirstHalf.add(c);
            }
            for (char c : secondHalf) {
                setSecondHalf.add(c);
            }
            setFirstHalf.retainAll(setSecondHalf); //the set that originally contained the first half now contains only 1 element - the common one

            //calculate value of element
            Map<Character,Integer> mapCharsToValues = mapCharsToValues();
            int commonCharValue = mapCharsToValues.get(setFirstHalf.toArray()[0]);
            totalValue+=commonCharValue;
        }

        System.out.println(totalValue);
    }

    public static List<String> readInput(String path) {
        Scanner scan = null;
        List<String> inputLines = new ArrayList<>();

        try {
            File file = new File(path);
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                inputLines.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return inputLines;
    }

    public static char[] getHalfOfArray(String line, String firstSecondToken) {

        char[] lineToChars = line.toCharArray();
        int midIndex = lineToChars.length / 2;
        if (firstSecondToken.equals("first")) {
            char[] firstHalf = Arrays.copyOfRange(lineToChars, 0, midIndex);
            return firstHalf;
        } else {
            int lastIndex = lineToChars.length;
            char[] secondHalf = Arrays.copyOfRange(lineToChars, midIndex, lastIndex);
            return secondHalf;
        }
    }

    public static Map<Character, Integer> mapCharsToValues() {
        Map<Character, Integer> mapCharsToValues = new HashMap<>();

        for (int i = 97; i <= 122; i++) {
            mapCharsToValues.put((char) i, i - 96);
        }

        for (int i = 65; i <= 90; i++) {
            mapCharsToValues.put((char) i, i - 38);
        }

        return mapCharsToValues;
    }  //slightly easier than directly referring to the ASCII table
}

//==========================================================================
//==============================part2=======================================
//==========================================================================

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> listInputs = readInput("src\\main\\resources\\input1.txt");
        int totalValue = 0;
        List<String> listInputTriple = new ArrayList<>();
        Map<Character, Integer> mapCharsToValues = mapCharsToValues();

        for (int i = 0; i < listInputs.size(); i++) {
            String currentLine = listInputs.get(i);
            listInputTriple.add(currentLine);

            if ((i + 1) % 3 == 0) { //every third line
                Character tripletBadge = findCharThreeArrays(listInputTriple.get(0), listInputTriple.get(1), listInputTriple.get(2));
                int badgeValue = mapCharsToValues.get(tripletBadge);
                totalValue += badgeValue;
                listInputTriple.clear();
            }
        }
        System.out.println(totalValue);
    }


    public static List<String> readInput(String path) {
        Scanner scan = null;
        List<String> inputLines = new ArrayList<>();

        try {
            File file = new File(path);
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                inputLines.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return inputLines;
    }

    public static char findCharThreeArrays(String firstLine, String secondLine, String thirdLine) {
        char[] firstLineChars = firstLine.toCharArray();
        char[] secondLineChars = secondLine.toCharArray();
        char[] thirdLineChars = thirdLine.toCharArray();
        Set<Character> setFirstHalf = new HashSet<>();
        Set<Character> setSecondHalf = new HashSet<>();
        Set<Character> setThirdHalf = new HashSet<>();
        for (char c : firstLineChars) {
            setFirstHalf.add(c);
        }
        for (char c : secondLineChars) {
            setSecondHalf.add(c);
        }
        for (char c : thirdLineChars) {
            setThirdHalf.add(c);
        }
        setFirstHalf.retainAll(setSecondHalf);
        setThirdHalf.retainAll(setFirstHalf);
        return setThirdHalf.toArray(new Character[0])[0];
    }

    public static char[] getHalfOfArray(String line, String firstSecondToken) {

        char[] lineToChars = line.toCharArray();
        int midIndex = lineToChars.length / 2;
        if (firstSecondToken.equals("first")) {
            char[] firstHalf = Arrays.copyOfRange(lineToChars, 0, midIndex);
            return firstHalf;
        } else {
            int lastIndex = lineToChars.length;
            char[] secondHalf = Arrays.copyOfRange(lineToChars, midIndex, lastIndex);
            return secondHalf;
        }
    }

    public static Map<Character, Integer> mapCharsToValues() {
        Map<Character, Integer> mapCharsToValues = new HashMap<>();

        for (int i = 97; i <= 122; i++) {
            mapCharsToValues.put((char) i, i - 96);
        }

        for (int i = 65; i <= 90; i++) {
            mapCharsToValues.put((char) i, i - 38);
        }

        return mapCharsToValues;
    }  //slightly easier than directly referring to the ASCII table
}
