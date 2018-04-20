/**
 *
 * @author Sengxay Xayachack
 * @version Beta
 * Date : 14/04/2018
 * Blog: https://medium.com/@frankxayachack && http://www.frankkung.com
 * ref: http://www.panl10n.net/english/outputs/Working%20Papers/Laos/Microsoft%20Word%20-%206_E_N_296.pdf
 *
 */
package com.sengxay;

import com.sengxay.SegmentationAlgorithm.Segmentation;

public class Main {

    public static String input = "";
    public static String output = "";

    public static void main(String[] args) {
        try{

            if(args.length <2){
                System.out.println("input file is required");
                if (output.isEmpty()) output = "output.txt";
                System.out.println("e.g: java LaoWordSegmentaion.jar input.txt output.txt");
                System.exit(0);
            }

            input = args[0];
            output = args[1];

            // write your code here
            Segmentation laoWordSegmentation = new Segmentation();
            laoWordSegmentation.LoadWordList();
            laoWordSegmentation.ReadAndWrite();
        }catch(Exception e){
            System.out.println(e);
        }


    }
}
