package com.example.common;

public class Recursive {


        // Recursive function to convert string to integer
        public static int stringToInt(String str)
        {

            // If the number represented as a string
            // contains only a single digit
            // then returns its value
            if (str.length() == 1)
                return (str.charAt(0) - '0');

            // Recursive call for the sub-string
            // starting at the second character
            double y = stringToInt(str.substring(1));

            // First digit of the number
            double x = str.charAt(0) - '0';

            // First digit multiplied by the
            // appropriate power of 10 and then
            // add the recursive result
            // For example, xy = ((x * 10) + y)
            x = x * Math.pow(10, str.length() - 1) + y;
            return (int)(x);
        }

        // Driver code
    /*
        public static void main(String[] args)
        {
            String str = "1235";
            System.out.print(stringToInt(str));
        }
        */
    }
