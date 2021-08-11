package com.example.screen3;

import java.util.ArrayList;

public class Mergearraylist {

        // Merge arr1[0..n1-1] and arr2[0..n2-1]
        // into arr3[0..n1+n2-1]
        public ArrayList mergeArrays(ArrayList x, ArrayList y) {
            int i = 0, j = 0, k = 0;
            ArrayList<Integer> arr3 = new ArrayList();

            // Traverse both array
            while (i < x.size() && j < y.size()) {
                // Check if current element of first
                // array is smaller than current element
                // of second array. If yes, store first
                // array element and increment first array
                // index. Otherwise do same with second array
                if (Integer.parseInt(x.get(i).toString()) < Integer.parseInt(y.get(j).toString()))
                    arr3.add(Integer.parseInt(x.get(i).toString()));
                else
                    arr3.add(Integer.parseInt(y.get(j).toString()));
            }

            // Store remaining elements of first array
            while (i < x.size())
                arr3.add(Integer.parseInt(x.get(i).toString()));

            // Store remaining elements of second array
            while (j < y.size())
                arr3.add(Integer.parseInt(y.get(j).toString()));

            return arr3;
        }

    }

