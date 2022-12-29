package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Read the signal
        String encodedSignal = readFile(); 

        //Find matching characters
        char[] signalAsChars = encodedSignal.toCharArray();
        int firstPosition = findFirstSequence(signalAsChars);

        //Get result
        System.out.println("First position is " + firstPosition);
    }

    //Read the signal
    public static String readFile(){
        try {
            File inputFile = new File("src\\main\\resources\\input1.txt");
            Scanner scan = new Scanner(inputFile);
            String encodedSignal = scan.nextLine();
            return encodedSignal;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Find matching characters
    public static int findFirstSequence(char[] signalAsChars){
        int firstPosition = 0;
        char firstLetter;
        char secondLetter;
        char thirdLetter;
        char fourthLetter; //get the value of all these and compare to each other

        for (int i = 3; i < signalAsChars.length; i++) {
            firstLetter = signalAsChars[i-3];
            secondLetter = signalAsChars[i-2];
            thirdLetter = signalAsChars[i-1];
            fourthLetter = signalAsChars[i];

            if(fourCharsDifferenceChecker(firstLetter,secondLetter,thirdLetter,fourthLetter)){
                firstPosition = i + 1;
                break;
            }
        }

        return firstPosition;
    }

    //Compare four chars to each other and return TRUE if there are no matches
    public static boolean fourCharsDifferenceChecker(char firstC, char secondC, char thirdC, char fourthC){
        return (firstC!=secondC && firstC != thirdC && firstC != fourthC
                && secondC != thirdC && secondC != fourthC
                && thirdC != fourthC);
    }

    //===========================================================================================
    //=====part 2 can be solved by manually adding ten more variables to the last two methods====
    //===========================================================================================
}
