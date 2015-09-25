package com.deeroid.translator;

import java.util.HashMap;

/**
 * Created by Deepak on 11-07-2015.
 */
public class Hexadecimal {

    //Hex to Binary Start
    public String hexToBinary(String s){
        if(s.length() == 0)
            return "";

        HashMap<Character, String> hexVals = new HashMap<Character, String>();
        hexVals.put( '0',"0000");
        hexVals.put('1', "0001");
        hexVals.put('2', "0010");
        hexVals.put('3', "0011");
        hexVals.put('4', "0100");
        hexVals.put('5', "0101");
        hexVals.put('6', "0110");
        hexVals.put('7', "0111");
        hexVals.put('8', "1000");
        hexVals.put('9', "1001");
        hexVals.put('A', "1010");
        hexVals.put('B', "1011");
        hexVals.put('C', "1100");
        hexVals.put('D', "1101");
        hexVals.put('E', "1110");
        hexVals.put('F', "1111");
        s = s.toUpperCase();
        String arr[] = s.split(" ");

        String ans = "";

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length(); j++){
                ans = ans + hexVals.get(arr[i].charAt(j));
            }
            ans += " ";
        }

        return ans;
    }
    //Hex to Binary End
}
