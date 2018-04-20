package com.sengxay.SegmentationAlgorithm;

import com.sengxay.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Segmentation {

    ArrayList<String> finalResult = new ArrayList<>();

    // Initialize Word list
    final public String FILENAME = "LaoWord.csv";
    HashSet<String> WORDLIST = new HashSet<>();


    // I/O
    String input = Main.input;
    String output = Main.output;

    String lineStr = "";
    static String orderedStr = "";

    public void ReadAndWrite(){
        try {
            File file = new File(input);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            PrintWriter writer = new PrintWriter(output, "UTF-8");

            String line;
            while((line = bufferedReader.readLine()) != null){
                if(line.isEmpty()) continue;
                // Remove all "Zero Width Space using "Unicode Regex"
                lineStr = line.replaceAll("[\\p{Cf}]", "");
                orderedStr = OrderSort(lineStr);
                TestWord();
                Verify();
                VerifyWithMixConsonant();
                writer.print(ArrangeWord());

            }

            fileReader.close();
            writer.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void TestWord(){

        char[] test_char = orderedStr.toCharArray();

        ArrayList<Character> result = new ArrayList<>();

        for(int i=0;i<test_char.length;){

            try {

                if(XsType.isALPHABETS(test_char[i])){
                    while(XsType.isALPHABETS(test_char[i])){
                        result.add(test_char[i++]);
                    }
                    finalResult.add(getStringRepresentation(result));
                    result.clear();
                }

                if(XsType.isSPECIALCHARS(test_char[i])){
                    while(XsType.isSPECIALCHARS(test_char[i])){
                        result.add(test_char[i++]);
                    }
                    finalResult.add(getStringRepresentation(result));
                    result.clear();
                }


                if(XsType.isX0(test_char[i])) {
                    result.add(test_char[i++]);
                }

                if(XsType.isX1(test_char[i])) result.add(test_char[i++]);

                if(XsType.isX(test_char[i])) result.add(test_char[i++]);

                if(XsType.isX2(test_char[i])) result.add(test_char[i++]);

                if(XsType.isX3(test_char[i])) result.add(test_char[i++]);


                // Avoid to add X4 vowels to the array in case the syllable started with ໄ or ໃ
                if((result.size() == 0 || (result.get(0) != 'ໄ' && result.get(0) != 'ໃ')) && XsType.isX4(test_char[i])){
                    result.add(test_char[i++]);
                }

                if(XsType.isX5(test_char[i])) result.add(test_char[i++]);

                if(XsType.isX6(test_char[i])) result.add(test_char[i++]);

                // In case syllable started with ໄ or ໃ there is no need to put more X6 X7 X8 to the syllable
                if(result.size() == 0 || (result.get(0) != 'ໄ' && result.get(0) != 'ໃ') ){

                    if(XsType.isX7(test_char[i])) result.add(test_char[i++]);

                    if(XsType.isX7_2(test_char[i])) result.add(test_char[i++]);

                    if(XsType.isX8(test_char[i])) result.add(test_char[i++]);

                }

                if(XsType.isX9(test_char[i])) result.add(test_char[i++]);

                if(XsType.isX10(test_char[i])) result.add(test_char[i++]);

                //Check if finish word
                if(test_char.length == 0 || result.size() == 0) continue;
                finalResult.add(getStringRepresentation(result));
                result.clear();
            }catch (ArrayIndexOutOfBoundsException ae){

            }catch (Exception e){
                System.out.println(e);
            }

        }

        finalResult.add(getStringRepresentation(result));
        result.clear();

    }

    /*

    ======================================

    Reorder the character

    ======================================
    Desc: Some people will type X5 before X3 OR X4
    So we have to rearrange it and put it in the right order

     */

    public String OrderSort(String str){
        char[] StrChar = str.toCharArray();
        char tmp = 0;
        try {
            for (int i = 0; i < StrChar.length; i++) {
                if (XsType.isX(StrChar[i])) {
                    if ((XsType.isX5(StrChar[i + 1]) && XsType.isX3(StrChar[i + 2])) || (XsType.isX5(StrChar[i + 1]) && XsType.isX4(StrChar[i + 2]))) {
                        tmp = StrChar[i + 2];
                        StrChar[i + 2] = StrChar[i + 1];
                        StrChar[i + 1] = tmp;
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException ae){

        }catch (Exception e){
            System.out.println(e);
        }

        return new String(StrChar);
    }




    /*

    ======================================

    CUT BY WORD LIST

    ======================================


     */

    // Using dictionary-based technique
    public String ArrangeWord() {

        ArrayList<String> FinalArray = new ArrayList<>();
        ArrayList<String> tmpArray = new ArrayList<>();
        String result = "";

        try {
            while (finalResult.size() != 0) {
                int i = 0;
                String tmp = finalResult.get(0);
                tmpArray.add(finalResult.get(0));
                while (true) {
                    if ((i + 1 < finalResult.size()) && CheckWordList(tmp + finalResult.get(i + 1))) {
                        tmp += finalResult.get(++i);
                        tmpArray.add(finalResult.get(i));
                    } else {
                        while (!WORDLIST.contains(tmp) && tmpArray.size() > 1) {
                            tmpArray.remove(tmpArray.size() - 1);
                            tmp = "";
                            for (String k : tmpArray) {
                                tmp += k;
                            }
                        }
                        break;
                    }
                }
                if(!tmp.equals(" ")){
                    FinalArray.add(tmp.trim());
                }

                for (int j = 0; j < tmpArray.size(); j++) {
                    finalResult.remove(0);
                }
                tmpArray.clear();
            }

            for(String str : FinalArray){
                result += str + "|";
            }

            System.out.println(result);

        }catch(Exception e){
            System.out.println(e);
        }

        return result;

    }

    //Check word if starting with arg str
    public Boolean CheckWordList(String str){

        boolean o = WORDLIST.stream()
                .anyMatch(n-> n.startsWith(str));

        return o;

    }

    //Load Word list
    public void LoadWordList(){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(FILENAME));
            String line = null;
            while((line = reader.readLine()) != null){
                if(!line.equals("")){
                    WORDLIST.add(line);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*

    ======================================

    END CUT BY WORD LIST

    ======================================

     */

    //Verify the form of the word
    public void Verify(){

        int i = 0;
        if(finalResult.get(finalResult.size()-1).equals("")) finalResult.remove(finalResult.size()-1);
        try {
            for (String str : finalResult) {
                char[] x = str.toCharArray();
                if(XsType.isSPECIALCHARS(x[0])){
                    i++;
                    continue;
                }
                char tmp = 0;
                //if not vowels, consonants, special char, alphabet, number then take Consonants from prev array
                if ((!XsType.isX0(x[0]) && !XsType.isX(x[0]) && !XsType.isSPECIALCHARS(x[0]) && !XsType.isALPHABETS(x[0])) ) {
                    tmp = TakeLastCharFromPrevWord(i, str, finalResult);
                }
                //if first char is not X (main consonants) then take the consonants from the prev array of word
                if ((XsType.isX6(x[0]) && XsType.isX8(x[1]) && !XsType.isX(tmp))) {
                    TakeLastCharFromPrevWord(i, str, finalResult);
                }
                i++;
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void VerifyWithMixConsonant(){

        int i = 0;
        if(finalResult.get(finalResult.size()-1).equals("")) finalResult.remove(finalResult.size()-1);
        try {
            for (String str : finalResult) {
                char[] x = str.toCharArray();
                if(XsType.isSPECIALCHARS(x[0])){
                    i++;
                    continue;
                }
                char tmp = 0;
                //if not vowels, consonants, special char, alphabet, number then take Consonants from prev array
                if ((!XsType.isX0(x[0]) && !XsType.isX(x[0]) && !XsType.isSPECIALCHARS(x[0]) && !XsType.isALPHABETS(x[0])) ) {
                    tmp = TakeLastCharFromPrevWord(i, str, finalResult);
                }
                //if first char is not X (main consonants) then take the consonants from the prev array of word
                if ((XsType.isX6(x[0]) && XsType.isX8(x[1]) && !XsType.isX(tmp)) || (x[0] == 'ວ' && !WORDLIST.contains(str)) && XsType.isMixConsonants(finalResult.get(finalResult.size()-1))) {
                    TakeLastCharFromPrevWord(i, str, finalResult);
                }
                i++;
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    /*

    This function will reformat a current word
    The previous word could take the consonant of the current word because the consonant of current word is in X8, X9 array
    Which can be the last character for the previous word as well

    This function will take the last character from the previous word which is in X array and take it back to put in the current word

     */
    public char TakeLastCharFromPrevWord(int i, String str, ArrayList<String> Set){
        char returnChar = 0;
        String tmp = Set.get(i-1);
        char[] tmpChar = tmp.toCharArray();
        StringBuilder newStr = new StringBuilder(str);
        newStr.insert(0, tmpChar[tmpChar.length-1]);
        returnChar = tmpChar[tmpChar.length-1];
        tmpChar = Arrays.copyOfRange(tmpChar,0,tmpChar.length-1);
        Set.set(i-1, new String(tmpChar));
        Set.set(i, newStr.toString());

        return returnChar;
    }


    /*
    ======================================

    CREATE THE STRING FROM CHAR

    ======================================


     */
    public String getStringRepresentation(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }



}
