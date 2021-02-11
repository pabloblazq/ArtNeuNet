package com.blame.artneunet.problemarena.common;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ColorMap {

	private static final Logger logger = LogManager.getLogger(ColorMap.class);

	protected Color[][] colorMap;

	public static ColorMap loadColorMap(String filePath) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = ColorMap.class.getResourceAsStream(filePath);
			BufferedImage image = ImageIO.read(is);
			ImageIO.write(image, "bmp", baos);
			byte[] bytearray = baos.toByteArray();
			bytearray = Arrays.copyOfRange(bytearray, 54, bytearray.length);
			return new ColorMap(bytearray, image.getWidth());
		} 
		catch (IOException e) {
			logger.catching(e);
			return null;
		}
	}

	private ColorMap(byte[] rgbData, int width) {
		
		int numPixels = rgbData.length / 3;
		int height = numPixels / width;
		
		colorMap = new Color[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int rgbDataIndex = 3 * (i * width + j);
				int red = unsign(rgbData[rgbDataIndex]);
				int green = unsign(rgbData[rgbDataIndex +1]);
				int blue = unsign(rgbData[rgbDataIndex +2]);
				colorMap[i][j] = new Color(red, green, blue);
			}
		}
	}

	private int unsign(byte b) {
		if(b < 0) {
			return b + 256;
		} else {
			return b;
		}
	}
	
	public Color getColor(int rowIndex, int columnIndex) {
		return colorMap[rowIndex][columnIndex];
	}
	
	public int getNumColumns() {
		return colorMap[0].length;
	}
	
	public int getNumRows() {
		return colorMap.length;
	}
}
