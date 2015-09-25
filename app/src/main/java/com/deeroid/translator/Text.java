package com.deeroid.translator;

/**
 * Created by Deepak on 11-07-2015.
 */
public class Text {

    //Text to Binary Start
    public String textToBinary(String s){
        if(s.length() == 0)
            return "";

        String bin = "";

        int intArr[] = new int[s.length()];

        for(int i = 0; i < s.length(); i++){
            intArr[i] = (int)s.charAt(i);
            String t = Integer.toBinaryString(intArr[i]);
            bin = bin + t + " ";
        }
        return bin;
    }
    //Text to Binary End
}
