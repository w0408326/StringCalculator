package com.inft4001;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public static int add(final String numbers) {
        String delimiter = ",|n";
        String numbersWithoutDelimiter = numbers;
        int count = 0;
        int x = 0;

        if (numbers.startsWith("//[")){
            int delimiterIndex = numbers.indexOf("//[") + 3;

            if(numbers.contains("\\n")){
                 x = numbers.indexOf("n") + 1;
            }else{
                x = numbers.indexOf("\n");
            }


            for (int i = 3; i < numbers.length(); i++){

                char c = numbers.charAt(i);

                if(c == ']'){
                    count = i - 3;
                    break;
                }
            }

            delimiter = numbers.substring(delimiterIndex, delimiterIndex + count);

            if(numbers.charAt(5) == '[') {
                delimiter += '|';
                delimiter += numbers.charAt(6);
            }

            numbersWithoutDelimiter = numbers.substring(x);
        }
        else if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        String[] numbersArray = numbers.split(delimiter);
        List negativeNumbers = new ArrayList();
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                int numberInt = Integer.parseInt(number.trim());
                if (numberInt < 0) {
                    negativeNumbers.add(numberInt);
                } else if (numberInt <= 1000) {
                    returnValue += numberInt;
                }
            }
        }
        if (negativeNumbers.size() > 0) {
            throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
        }
        return returnValue;
    }
}
