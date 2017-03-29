package com.staticarr;

import java.util.ArrayList;

public class Arr {
    public static String toString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++) {
            if(i + 1 == a.length) {
                sb.append(a[i]);
            } else {
                sb.append(a[i] + ",");
            }
        }
        return sb.toString();
    }   

    public static void sort(int[] a) {
        //Bubble sort
        boolean changeMade = true;
        while(changeMade) {
            changeMade = false;
            for(int i = 0; i < a.length - 1; i++){
                if(a[i] > a[i + 1]) {
                    changeMade = true;
                    int tempValHolder = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tempValHolder;
                }
            }
        }
    }

    public static int[] reverse(int[] a) {
        int[] reversed = new int[a.length];
        for(int i = a.length - 1; i >= 0; i--) {
            int index = reversed.length - i - 1;
            reversed[index] = a[i];
        }
        return reversed;
    }

    public static String join(int[] a, String glue) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++) {
            if(i + 1 == a.length) {
                sb.append(a[i]);
            } else {
                sb.append(a[i] + glue);
            }
        }
        return sb.toString();
    }

    public static int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static int[] range(int a, int b) {
        int[] rangeArr =  new int[Math.abs(b - a)];
        for (int i = 0; i < rangeArr.length; i++) {
            rangeArr[i] = a + i;
        }
        return rangeArr;
    }

    public static Integer[] filterOdd(int[] a) {
        ArrayList<Integer> listOfOdds = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if(a[i] % 2 == 1) {
                listOfOdds.add(a[i]);
            }
        }
        Integer[] odds = new Integer[listOfOdds.size()];
        odds = listOfOdds.toArray(odds);
        return odds;
    }

    public static void main(String[] args) {
        int[] a = {10, 20, -50, 80, 70, 66, -365};

        System.out.println("Print the array to string:");
        System.out.println(Arr.toString(a));

        System.out.println("Sort the array a itself and print it sorted:");
        Arr.sort(a);
        System.out.println(Arr.toString(a));


        System.out.println("Print the reverse of the sorted array");
        System.out.println(Arr.reverse(a));

        System.out.println("Output each element in a with -> between them");
        System.out.println(Arr.join(a, "->"));

        System.out.println("Output the sum");
        System.out.println(Arr.sum(a));

        System.out.println("Output array with elements from 1 to 10");
        System.out.println(Arr.toString(Arr.range(1, 10)));

        System.out.println("Print only the odd numbers");
        System.out.println(Arr.filterOdd(a));
    }
}