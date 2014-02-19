/*
 * OCR.java
 *
 * Created on December 24, 2007, 12:38 AM
 */

package com.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdesktop.swingx.util.OS;


public class OCR {
	//protected transient final Logger logger = Logger.getLogger(this.getClass());
	private final String LANG_OPTION = "-l";
	private final String EOL = System.getProperty("line.separator");
	private String tessPath = new File("tessdata").getAbsolutePath();

	public String recognizeText(File imageFile, String imageFormat) throws Exception {
		//File tempImage = imageFile;
		//ImageIOHelper.createImage(imageFile, imageFormat);
		File outputFile =new File(imageFile.getParentFile(), "output");
		//System.out.println("path is:"+outputFile.getAbsolutePath());
		StringBuffer strB = new StringBuffer();

		List<String> cmd = new ArrayList<String>();
		if (OS.isWindowsXP()) {
			cmd.add(tessPath+ "\\tesseract");
		} else if (OS.isLinux()) {
			cmd.add("tesseract");
		} else {
			cmd.add(tessPath+ "\\tesseract");
		}
		cmd.add("");
		cmd.add(outputFile.getName());
		cmd.add(LANG_OPTION);
		cmd.add("eng");
		ProcessBuilder pb = new ProcessBuilder();
		pb.directory(imageFile.getParentFile());

		cmd.set(1, imageFile.getName());
		pb.command(cmd);
		pb.redirectErrorStream(true);
		Process process = pb.start();

		int w = process.waitFor();
		System.out.println("Exit value ="+w+",if the code is 0 ,means it's success");

		// delete temp working files
		//imageFile.delete();

		if (w == 0) {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile
					.getAbsolutePath()
					+ ".txt"), "UTF-8"));

			String str;

			while ((str = in.readLine()) != null) {
				strB.append(str).append(EOL);
			}
			in.close();
		} else {
			String msg;
			switch (w) {
			case 1:
				msg = "Errors accessing files. There may be spaces in your image's filename.";
				break;
			case 29:
				msg = "Cannot recognize the image or its selected region.";
				break;
			case 31:
				msg = "Unsupported image format.";
				break;
			default:
				msg = "Errors occurred.";
			}
			//imageFile.delete();
			throw new RuntimeException(msg);
		}

		new File(outputFile.getAbsolutePath() + ".txt").delete();
	//	System.out.println("code is:"+ strB);
		return strB.toString();
	}
}
