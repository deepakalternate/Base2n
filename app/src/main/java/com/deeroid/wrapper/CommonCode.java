package com.deeroid.wrapper;

import com.deeroid.translator.Binary;
import com.deeroid.translator.Hexadecimal;
import com.deeroid.translator.Octal;
import com.deeroid.translator.Text;

/**
 * Created by Deepak on 04-07-2015.
 */
public class CommonCode {

    public static String BIN = "Binary";
    public static String OCT = "Octal";
    public static String HEX = "Hexadecimal";
    public static String TEX = "Text";

    private static Binary bin = new Binary();
    private static Octal oct = new Octal();
    private static Hexadecimal hex = new Hexadecimal();
    private static Text tex = new Text();


    public String callingFunction(int inp, int out, String inputString){

        String temp = "";
        String ans = "";

        //Text block start
        if(inp == 0){
            temp = tex.textToBinary(inputString);

            if(out == 0){
                ans = temp;
            }
            else if(out == 1){
                ans = bin.binaryToOctal(temp);
            }
            else if(out == 2){
                ans = bin.binaryToHex(temp);
            }
        }
        //Text block end

        //Binary block start
        else if(inp == 1){
            temp = inputString;

            if(out == 0){
                ans = bin.binaryToText(temp);
            }
            else if(out == 1){
                ans = bin.binaryToOctal(temp);
            }
            else if(out == 2){
                ans = bin.binaryToHex(temp);
            }
        }
        //Binary block end

        //Octal block start
        else if(inp == 2){
            temp = oct.octToBinary(inputString);

            if(out == 0){
                ans = bin.binaryToText(temp);
            }
            else if(out == 1){
                ans = temp;
            }
            else if(out == 2){
                ans = bin.binaryToHex(temp);
            }
        }
        //Octal block end

        //Hexadecimal block start
        else if(inp == 3){
            temp = hex.hexToBinary(inputString);

            if(out == 0){
                ans = bin.binaryToText(temp);
            }
            else if(out == 1){
                ans = temp;
            }
            else if(out == 2){
                ans = bin.binaryToOctal(temp);
            }
        }
        //Hexadecimal block end

        return ans;
    }

}
