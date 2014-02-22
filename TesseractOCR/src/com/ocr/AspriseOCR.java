package com.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;







import com.asprise.util.ocr.OCR;
import com.image.filter1.ImageFilter;
import com.image.filter1.ImageIOHelper;

/**
* @ClassName: AspriseOCR
* @Description: method 1 :use the aspriseOCR library to recongnize the image string 
*               STEPS:
*               1,move the cracked four libraries  into java library path ,like C:\jdk1.6.0_43\bin,
*                   32bit into this path :C:\Windows\System32 64 bit into this path:C:\Windows\SysWOW64
*                  or it's better to use in this path:C:\Windows this can be used in different enviroments
*               2,load the AspriseOCR.jar into the java class path;
*               3, use the blow code to recongize the image;
*               
*               
*                [DllImport("OCRDLL\\AspriseOCR.dll", EntryPoint = "OCR")]
62	        public static extern IntPtr OCR(string file, int type);
63	
64	        [DllImport("OCRDLL\\AspriseOCR.dll", EntryPoint = "OCRpart")]
65	        static extern IntPtr OCRpart(string file, int type, int startX, int startY, int width, int height);
66	
67	        [DllImport("OCRDLL\\AspriseOCR.dll", EntryPoint = "OCRBarCodes")]
68	        static extern IntPtr OCRBarCodes(string file, int type);
69	
70	        [DllImport("OCRDLL\\AspriseOCR.dll", EntryPoint = "OCRpartBarCodes")]
71	        static extern IntPtr OCRpartBarCodes(string file, int type, int startX, int startY, int width, int height);
* @author alterhu2020@gmail.com
* @date Feb 17, 2014 1:15:28 PM
* 
* it supported hand write very well than Tesseract OCR
* 
*/

public class AspriseOCR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadLibraryIntoJavaPath("aspriseocr");	
			
	    String codepath="testedimages//cutter//filter.png";			
		recognizeEverything(codepath,false);
		
	}

	public static void loadLibraryIntoJavaPath(String nativelibrarypath){
		try {			
			//private String tessPath = new File("aspriseocr").getAbsolutePath();
			FileUtils.copyDirectory( new File(nativelibrarypath), new File("C:\\Windows"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String recognizeEverything(String filepath,boolean filter){
	   BufferedImage image;
	   String imagestr = null;
	   File parsedfile=null;		
       long starter=System.currentTimeMillis();	
       if(filter){
	    	ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(new File(filepath)));
	   		ImageFilter imf2 = new ImageFilter(imf.changeGrey());
	   		//ImageFilter imf3 = new ImageFilter(imf2.median());
	   		BufferedImage img = imf2.lineGrey();
	   		parsedfile = ImageIOHelper.createImage(img);
	   		System.out.println("Parsed Filter File path is:"+parsedfile.getAbsolutePath());
       }else{
    	    parsedfile=new File(filepath);
       }
  
		try {
		/*	System.out.println(System.getProperty("java.library.path")); 
			System.setProperty("java.library.path","/lib");
				//	+ "//com/resources/aspriseocr") ;
			System.out.println("path is:"+System.getProperty("java.library.path")); 
                    
           System.loadLibrary("AspriseOCR");
           System.loadLibrary("AspriseJTwain");
           System.loadLibrary("DevIL");
           System.loadLibrary("ILU");*/
           // System.out.println(new File("").getAbsolutePath());
			//FileUtils.copyDirectory(new File(new File("").getAbsolutePath()+"\\aspriseocr"), new File("C:\\Windows"));
			image = ImageIO.read(parsedfile);
			imagestr=new OCR().recognizeEverything(image);
			//System.out.println("cnfig is :"+imagestr);
			imagestr=imagestr.replaceAll("[.|?|:|(|)|=|,|\r\n|%|'|&|$|@|#|/|!|-|\\[|\\]]|\\*", "").replaceAll(" ","").trim();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end=System.currentTimeMillis();
		System.out.println("Cracked Code:"+imagestr+",AspriseOCR took time is:"+(end-starter));
		return imagestr;
	}
}
