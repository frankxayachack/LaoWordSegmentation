package com.sengxay.SegmentationAlgorithm;

public class XsType {

    static final public char[] X0 = {'ເ','ແ','ໂ','ໄ','ໃ'};
    static final public char X1 = 'ຫ';
    static final public char[] X = {'ກ', 'ຂ', 'ຄ', 'ງ', 'ຈ', 'ສ', 'ຊ', 'ຍ', 'ດ', 'ຕ', 'ຖ', 'ທ', 'ນ', 'ບ', 'ປ', 'ຜ', 'ຝ', 'ພ', 'ຟ', 'ມ', 'ຢ', 'ຣ', 'ລ', 'ວ', 'ຫ', 'ອ', 'ຮ', 'ໜ', 'ໝ'};
    static final public char[] X2 = {'ຼ', 'ຣ', 'ວ'};
    static final public char[] X3 = {'ຸ','ູ'};
    static final public char[] X4 = {'ິ', 'ີ', 'ຶ', 'ື', 'ໍ', 'ົ', 'ັ','ຳ'};
    static final public char[] X5 = {'່','້','໊','໋'};
    static final public char[] X6 = {'ວ','ອ','ຽ'};
    static final public char[] X7 = {'ະ','າ','ຳ'};
    static final public char X7_2 = 'ະ';
    static final public char[] X8 = {'ກ', 'ງ', 'ຍ', 'ດ', 'ນ', 'ມ', 'ບ', 'ວ'};
    static final public char[] X9 = {'ຈ', 'ສ', 'ຊ', 'ພ', 'ຟ', 'ລ', 'ຣ'};
    static final public char[] X10 = {'ຯ', 'ໆ', '໌'};
    static final public char[] MIX_CONSONANTS = {'ກ', 'ຂ', 'ຄ', 'ງ', 'ຈ'};
    static final public char[] SPECIAL_CHARS = " ,./;:'\"!@#$%^&*()_+-=?~“`0123456789".toCharArray();
    static final public char[] ALPHABETS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    static public boolean isX0(char x){
        for(char i : X0){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX1(char x){
        if(x == X1) return true;
        return false;
    }

    static public boolean isX(char x){
        for(char i : X){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX2(char x){
        for(char i : X2){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX3(char x){
        for(char i : X3){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX4(char x){
        for(char i : X4){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX5(char x){
        for(char i : X5){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX6(char x){
        for(char i : X6){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX7(char x){
        for(char i : X7){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX7_2(char x){
        if(x == X7_2) return true;
        return false;
    }

    static public boolean isX8(char x){
        for(char i : X8){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX9(char x){
        for(char i : X9){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isX10(char x){
        for(char i : X10){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isSPECIALCHARS(char x){
        for(char i : SPECIAL_CHARS){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isALPHABETS(char x){
        for(char i : ALPHABETS){
            if(i == x) return true;
        }
        return false;
    }

    static public boolean isMixConsonants(String str){
        char[] chars = str.toCharArray();
        for(char x : MIX_CONSONANTS){
            if(x == chars[chars.length-1]);
            return true;
        }
        return false;
    }


    // put at X8
    // is ເxຍ, ເxຽ, ເxຍ, ເxວ, ເxົາ, ເxືອ...
//    public boolean isCombinationVowels(ArrayList<Character> arr){
//        int i =1;
//        if(XsType.isX0(arr.get(0))){
//            while(i<arr.size()){
//                if(((XsType.isX6(arr.get(i)) || XsType.isX7(arr.get(i)))  && arr.get(i) != 'ອ')){
//                    return true;
//                }
//                i++;
//            }
//
//        }
//        return false;
//    }

}
