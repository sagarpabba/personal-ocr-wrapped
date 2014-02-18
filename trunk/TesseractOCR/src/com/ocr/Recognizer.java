package com.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public abstract class Recognizer {
	public static String getLoginValidateCode(String fileName) throws Exception {
		OCR ocr = new OCR();
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
	
	public static void test() throws Exception{
		File file = new File("img");
		if(file.isDirectory()){
			OCR ocr = new OCR();
			String[] imgs = file.list();
			File[] imgFiles = file.listFiles();
			String[] answer = new String[imgs.length];
			List<String> right = new ArrayList<String>();
			for(int i=0;i<imgs.length;i++){
				if(!imgFiles[i].exists()){
					continue;
				}
				answer[i]=imgs[i].substring(0,4);
				ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(imgFiles[i]));
				ImageFilter imf2 = new ImageFilter(imf.median());
				BufferedImage img = imf2.changeGrey();
				String code = ocr.recognizeText(ImageIOHelper.createImage(img), "tiff").trim();
				if(answer.equals(code)){
					right.add(code);
				}
			}
			System.out.println("总个数："+imgs.length+" 识别正确个数："+right.size());
			System.out.println("识别正确的："+right);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(test1());
	}

	private static String test1() throws Exception {
		long starter=System.currentTimeMillis();
		OCR ocr = new OCR();
		ImageFilter imf = new ImageFilter(ImageIOHelper.getImage(new File("testedimages//login_passcode.png")));
		ImageFilter imf2 = new ImageFilter(imf.changeGrey());
		ImageFilter imf3 = new ImageFilter(imf2.median());
		BufferedImage img = imf3.lineGrey();
		File file = ImageIOHelper.createImage(img);
		//Common.exec(file.getAbsolutePath());
		//Common.getUserInputOnConsole("");
		 long end=System.currentTimeMillis();
		 String imagestr=ocr.recognizeText(file, "tiff").trim();
		System.out.println("Cracked Code:"+imagestr+",TesseractOCR time is:"+(end-starter));
		return null;
		
	}
}
