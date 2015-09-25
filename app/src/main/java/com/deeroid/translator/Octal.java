package com.deeroid.translator;

import java.util.HashMap;

/**
 * Created by Deepak on 11-07-2015.
 */
public class Octal {

    //Octal to Binary Start
    public String octToBinary(String s){

        if(s.length() == 0)
            return "";

        HashMap<Character, String> octVals = new HashMap<Character, String>();
        octVals.put('0',"000");
        octVals.put('1',"001");
        octVals.put('2',"010");
        octVals.put('3',"011");
        octVals.put('4',"100");
        octVals.put('5',"101");
        octVals.put('6',"110");
        octVals.put('7',"111");

        String arr[] = s.split(" ");
        String ans = "";

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length(); j++){
                ans = ans + octVals.get(arr[i].charAt(j));
            }
            ans += " ";
        }

        return ans;

    }
    //Octal to Binary End
}
