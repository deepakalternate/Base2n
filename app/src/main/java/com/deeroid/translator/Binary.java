package com.deeroid.translator;

import java.util.HashMap;

/**
 * Created by Deepak on 11-07-2015.
 */
public class Binary {

    //Binary to Hexadecimal Start
    public String binaryToHex(String s){

        if(s.length() == 0)
            return "";

        HashMap<String, String> hexVals = new HashMap<String, String>();
        hexVals.put("0000", "0");
        hexVals.put("0001", "1");
        hexVals.put("0010", "2");
        hexVals.put("0011", "3");
        hexVals.put("0100", "4");
        hexVals.put("0101", "5");
        hexVals.put("0110", "6");
        hexVals.put("0111", "7");
        hexVals.put("1000", "8");
        hexVals.put("1001", "9");
        hexVals.put("1010", "A");
        hexVals.put("1011", "B");
        hexVals.put("1100", "C");
        hexVals.put("1101", "D");
        hexVals.put("1110", "E");
        hexVals.put("1111", "F");

        String ans = "";

        String arr[] = s.split(" ");

        for(int i = 0; i < arr.length; i++){

            String hexString = "";
            int ind = 0;

            if(arr[i].length() % 4 != 0){
                while(arr[i].length() % 4 != 0){
                    arr[i] = "0" + arr[i];
                }
            }

            while(ind+4 <= arr[i].length()){
                hexString += hexVals.get(arr[i].substring(ind, ind+4));
                ind = ind + 4;
            }

            ans = ans + hexString + " ";

        }

        return ans;

    }
    //Binary to Hexadecimal End

    //Binary to Octal Start
    public String binaryToOctal(String s){

        if(s.length() == 0)
            return "";

        HashMap<String, String> octVals = new HashMap<String, String>();
        octVals.put("000", "0");
        octVals.put("001", "1");
        octVals.put("010", "2");
        octVals.put("011", "3");
        octVals.put("100", "4");
        octVals.put("101", "5");
        octVals.put("110", "6");
        octVals.put("111", "7");

        String ans = "";
        String arr[] = s.split(" ");

        for(int i =0;i<arr.length;i++){

            String octString = "";

            int ind = 0;

            while(arr[i].length() % 3 != 0){
                arr[i] = "0" + arr[i];
            }

            while(ind + 3 <= arr[i].length()){
                octString += octVals.get(arr[i].substring(ind, ind+3));
                ind +=  3;
            }

            ans = ans + octString + " ";

        }

        return ans;
    }
    //Binary to Octal End

    //Binary to Text Start
    public String binaryToText(String s){
        if(s.length() == 0)
            return "";

        String arr[] = s.split(" ");

        String ans = "";

        for(int i = 0; i < arr.length; i++){

            if(arr[i].length() > 31){

                int len = arr[i].length();

                while(len > 31){
                    String temp = arr[i].substring(len - 31, len);
                    int foo = Integer.parseInt(temp, 2);
                    ans = ans + (char)foo;
                    len = len - 31;
                }

                if(len > 0){
                    String temp = arr[i].substring(0, len);
                    int foo = Integer.parseInt(temp, 2);
                    ans = ans + (char)foo;
                }

            }

            else{
                int foo = Integer.parseInt(arr[i], 2);
                ans = ans + (char)foo;
            }

        }

        return ans;
    }
    //Binary to Text End
}
