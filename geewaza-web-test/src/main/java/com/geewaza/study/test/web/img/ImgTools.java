package com.geewaza.study.test.web.img;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by WangHeng on 2016/3/3.
 */
public class ImgTools {

	public static void resizeImg(String inputFile, String outputFile, int width, int height, String format) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(inputFile));
		BufferedImage inputImage = ImageIO.read(inputStream);
		BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
		try {
			Graphics graphics = outputImage.createGraphics();
			graphics.drawImage(inputImage, 0, 0, width, height, null);
			ImageIO.write(outputImage, format, outputStream);
		} finally {
			outputStream.flush();
			inputStream.close();
			outputStream.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String inputFile = "/tmp/img/XinZhao.jpg";
		String outputFile = "/tmp/img/back.jpg";
		int width = 448;
		int height = 252;
		resizeImg(inputFile, outputFile, width, height, "JPG");
		System.out.println("OK!");
	}

}
