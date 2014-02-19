package com.ocr;

import java.awt.image.BufferedImage;
import java.io.File;




public abstract class TesseractOCR {
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		 String codepath="testedimages//passcode38.jpg";
		 recognizeEverything(codepath,false);
	}
	
	
	
	public static String getLoginValidateCode(String fileName) throws Exception {
		HPOCR ocr = new HPOCR();
		ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(new File(fileName)));
		ImageFilter imf2 = new ImageFilter(imf.scale(1.65f));
		BufferedImage img = imf2.changeGrey();
		return ocr.recognizeText(ImageIOHelper.createImage(img), "tiff").trim();
	}
	
	/*public static String getM18ValidateCode(DefaultHttpClient client,OCR ocr,String html) throws Exception{
		
		String url = HtmlParserUtils.getM18ValidateCodeImgSrc(html);
		HttpClientUtils.doGetRequest(client, url, null);
		String fileName = HtmlParserUtils.getResource(client,url, "jpg");
		System.out.println(fileName);
		File imgFile = new File(fileName);
		ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(imgFile));
		imgFile.delete();
		ImageFilter imf1 = new ImageFilter(imf.changeGrey());
		ImageFilter imf2 = new ImageFilter(imf1.grayFilter());
		BufferedImage img = imf2.median();
		return ocr.recognizeText(ImageIOHelper.createImage(img), "tiff");
	}

	public static String getMail163ComplainCode(DefaultHttpClient client) throws MalformedURLException, IOException{
		String url="http://feedback.mail.126.com/antispam/captcha/gdimg.php?"+new Random().nextFloat();
		String fileName = HtmlParserUtils.getResource(client,url,"png");
		Common.exec(fileName);
		return Common.getUserInput("请输入验证码：");
	}	*/
	/**
	 * 获取麦网注册验证码
	 * @param client
	 * @param oldGuid
	 * @param isByHand 是否手动输入验证码
	 * @return
	 * @throws Exception
	 */
/*	public static String getM18RegisterCode(DefaultHttpClient client,String oldGuid,boolean isByHand) throws Exception{
		String url = "http://verifycode.m18.com/getimg.aspx?guid="+oldGuid;
		String fileName = HtmlParserUtils.getResource(client,url,"gif");
		if(isByHand){
			Common.exec(fileName);
			return Common.getUserInput("请输入验证码：");
		}else{
			return getLoginValidateCode(fileName);
		}
	}*/
	
	
	private static String recognizeEverything(String filepath,boolean filter) throws Exception {
		long starter=System.currentTimeMillis();
		HPOCR ocr = new HPOCR();
		File parsedfile=null;
		if(filter){			
			ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(new File(filepath)));
			ImageFilter imf2 = new ImageFilter(imf.changeGrey());  //
		//	ImageFilter imf3 = new ImageFilter(imf2.median());
			BufferedImage img = imf2.lineGrey();
			parsedfile = ImageIOHelper.createImage(img);
			//System.out.println("parsed image path is:"+parsedfile.getAbsolutePath());
		}else{
			parsedfile=new File(filepath);
		}
		
		String imagestr=ocr.recognizeText(parsedfile, "tiff").trim();
		long end=System.currentTimeMillis();
		System.out.println("Cracked Code:"+imagestr+",TesseractOCR took time is:"+(end-starter));
		return imagestr;
		
	}
}
