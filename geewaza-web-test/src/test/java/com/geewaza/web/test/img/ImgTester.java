package com.geewaza.web.test.img;

import com.geewaza.study.test.web.img.ImgTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by WangHeng on 2016/3/4.
 */
public class ImgTester {

	public static void main(String[] args) throws IOException {
		test01();
	}

	private static void test04() throws IOException {

		String bgPic = "D:\\tmp\\img\\back.jpg";
		String iconPath = "D:\\tmp\\img\\miss.png";
		String targerPath = "D:\\tmp\\img\\img.jpg";
		OutputStream os = null;
		try {
			BufferedImage srcImg = ImageIO.read(new File(bgPic));
			BufferedImage backBuffer = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = backBuffer.createGraphics();
			backBuffer = g.getDeviceConfiguration().createCompatibleImage(srcImg.getWidth(null), srcImg.getHeight(null), Transparency.TRANSLUCENT);
			g.dispose();
			g = backBuffer.createGraphics();
			Image backImg = srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), srcImg.SCALE_AREA_AVERAGING);
			g.drawImage(backImg, 0, 0, null);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(backBuffer, "JPG", os);
		} finally {
			os.close();
		}
	}

	private static void test03() {
		String fromFile = "D:\\tmp\\img\\miss.png";
		String toFile = "D:\\tmp\\img\\miss_mini.png";
		int width = 150;
		int height = 200;
		resizePNG(fromFile, toFile, width, height, false);

	}

	private static void test02() throws IOException {
		String iconPath = "D:\\tmp\\img\\miss.png";
		ImgTools.resizeImg(iconPath,"D:\\tmp\\img\\minimiss.png", 150, 199, "png");
		System.out.println("OK");
		System.exit(0);
	}

	private static void test01() throws IOException {

		String bgPic = "D:\\tmp\\img\\back.jpg";
		String iconPath = "D:\\tmp\\img\\miss.png";
		String targerPath = "D:\\tmp\\img\\img.jpg";
		OutputStream os = null;



		BufferedImage iconBuffer = ImageIO.read(new File(iconPath));
		int iconHeight = 200;
		int iconWidth = 150;
		BufferedImage to = new BufferedImage(iconWidth, iconHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = to.createGraphics();
		to = g2d.getDeviceConfiguration().createCompatibleImage(iconWidth, iconHeight, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = to.createGraphics();
		Image iconImg = iconBuffer.getScaledInstance(iconWidth, iconHeight, iconBuffer.SCALE_AREA_AVERAGING);
		g2d.drawImage(iconImg, 0, 0, null);
		g2d.dispose();


		try {
			BufferedImage srcImg = ImageIO.read(new File(bgPic));
			BufferedImage backBuffer = null;
//			if (srcImg.isAlphaPremultiplied()) {
//				backBuffer = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TRANSLUCENT);
//			} else {
//				backBuffer = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
//			}
			backBuffer = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = backBuffer.createGraphics();
			backBuffer = g.getDeviceConfiguration().createCompatibleImage(srcImg.getWidth(null), srcImg.getHeight(null), Transparency.TRANSLUCENT);
			g.dispose();
			g = backBuffer.createGraphics();
			Image backImg = srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), backBuffer.SCALE_AREA_AVERAGING);
			g.drawImage(backImg, 0, 0, null);
			g.drawImage(iconImg, 0, srcImg.getHeight(null) - iconHeight, null);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(backBuffer, "JPG", os);
//			ImageIO.write(iconBuffer, "JPG", os);
		} finally {
			os.close();
		}

	}

	public static void resizePNG(String fromFile, String toFile, int outputWidth, int outputHeight,boolean proportion) {
		try {
			File f2 = new File(fromFile);
			BufferedImage bi2 = ImageIO.read(f2);
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			if (proportion == true) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
				double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 < rate2 ? rate1 : rate2;
				newWidth = (int) (((double) bi2.getWidth(null)) / rate);
				newHeight = (int) (((double) bi2.getHeight(null)) / rate);
			} else {
				newWidth = outputWidth; // 输出的图片宽度
				newHeight = outputHeight; // 输出的图片高度
			}

			BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = to.createGraphics();
			to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth,newHeight, Transparency.TRANSLUCENT);
			g2d.dispose();
			g2d = to.createGraphics();
			Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
			g2d.drawImage(from, 0, 0, null);
			g2d.dispose();
			ImageIO.write(to, "png", new File(toFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
