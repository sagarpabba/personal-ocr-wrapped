/**
 * created since 2010-8-18
 */
package com.image.filter2;

import java.awt.image.BufferedImage;

/**
 * @author yuezhen
 * @version $Id: Test.java,v 0.1 2010-8-18 обнГ04:00:38 yuezhen Exp $
 */
public class Test {
    public static void main(String[] args) {
            for(int i=0;i<300;i++){
            	
         
            BufferedImage image = Tools.getImage("C:\\Users\\huchan\\Desktop\\OCR\\Trainingdata12306\\passcode"+i+".png");

            ImageFilter.blackAndWhiteFilter(image);
            Tools.writeImageToFile("C:\\Users\\huchan\\Desktop\\OCR\\blacked\\passcode"+i+".tiff", image);
            }
         /*   
            ImageFilter.dotFilter(image);
            Tools.writeImageToFile("testedimages/cutter/filter.png", image);*/
       
    }
}
