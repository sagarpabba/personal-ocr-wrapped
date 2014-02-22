package com.image.filter1;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.media.imageio.plugins.tiff.TIFFTag;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
import com.sun.media.jai.codecimpl.TIFFImageEncoder;

public class CovertTifFormat {

	
	  public static void TiffOutputTester(RenderedImage image, String outputFilePath, int dpi) {
	        try {
	            if (image != null) {
	                TIFFEncodeParam param = new TIFFEncodeParam();
	                param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);
	                TIFFField[] extras = new TIFFField[2];
	                extras[0] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][]{{(long) dpi, 1}, {0, 0}});
	                extras[1] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][]{{(long) dpi, 1}, {0, 0}});
	                param.setExtraFields(extras);
	                File outputFile = new File(outputFilePath);
	                outputFile.createNewFile();
	                FileOutputStream outputStream = new FileOutputStream(outputFile);
	                TIFFImageEncoder encoder = new TIFFImageEncoder(outputStream, param);
	                encoder.encode(image);
	                outputStream.close();
	            }
	        } catch (IOException ex) {
	                ex.printStackTrace();
	        }
	    }
	  
	  public static void TiffOutputCommand(RenderedImage image, String outputFilePath, int dpi) {
	        try {
	            if (image != null) {
	                TIFFEncodeParam param = new TIFFEncodeParam();
	                param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);
	                TIFFField[] extras = new TIFFField[2];
	                extras[0] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][]{{(long) dpi, 1}, {0, 0}});
	                extras[1] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, (Object) new long[][]{{(long) dpi, 1}, {0, 0}});
	                param.setExtraFields(extras);
	                File outputFile = new File(outputFilePath);
	                outputFile.createNewFile();
	                FileOutputStream outputStream = new FileOutputStream(outputFile);
	                TIFFImageEncoder encoder = new TIFFImageEncoder(outputStream, param);
	                encoder.encode(image);
	                outputStream.close();
	            }
	        } catch (IOException ex) {
	                ex.printStackTrace();
	        }
	    }
}
