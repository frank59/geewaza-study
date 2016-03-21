package com.geewaza.web.test.img;

import com.geewaza.study.test.web.img.ImgTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

/**
 * Created by WangHeng on 2016/3/4.
 */
public class ImgTester {

	public static void main(String[] args) throws IOException {
		test07();
	}

	private static void test07() throws IOException {

		String fromFile = "D:\\tmp\\img\\img.png";
		String toFile = "D:\\tmp\\img\\img.jpg";
		RenderedImage img = ImageIO.read(new File(fromFile));
		ImageIO.write(img, "jpg", new File(toFile));
	}

	private static void test06() throws IOException {
		String bgPic = "D:\\tmp\\img\\back.png";
		String iconPath = "D:\\tmp\\img\\miss_mini.png";
		String targerPath = "D:\\tmp\\img\\img.png";

		BufferedImage backBuffer = ImageIO.read(new File(bgPic));
		BufferedImage iconBuffer = ImageIO.read(new File(iconPath));
		BufferedImage targetBuffer = new BufferedImage(backBuffer.getWidth(), backBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = targetBuffer.createGraphics();
		g2d.drawImage(backBuffer, 0, 0, null);
		g2d.drawImage(iconBuffer, 0, backBuffer.getHeight() - iconBuffer.getHeight(), null);
		System.out.println(ImageIO.write(targetBuffer, "PNG", new FileOutputStream(targerPath)));
	}

	private static void test05() throws IOException {

		String bgPic = "D:\\tmp\\img\\back.jpg";
		String targerPath = "D:\\tmp\\img\\back.png";
		String format = "png";
		System.out.println(imageTransfer(bgPic, targerPath, format));
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
			ImageIO.write(backBuffer, "png", os);
		} finally {
			os.close();
		}
	}

	private static void test03() {
		String fromFile = "D:\\tmp\\img\\miss.png";
		String toFile = "D:\\tmp\\img\\miss_mini.png";
		int width = 150;
		int height = 200;
		resizePNG(fromFile, toFile, width, height);

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

	public static void resizePNG(String fromFile, String toFile, int outputWidth, int outputHeight) {
		try {
			File f2 = new File(fromFile);
			BufferedImage bi2 = ImageIO.read(f2);
			int newWidth;
			int newHeight;
			newWidth = outputWidth; // 输出的图片宽度
			newHeight = outputHeight; // 输出的图片高度

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

	public static boolean imageTransfer(String fromFile, String toFile, String format) throws IOException {
		boolean flag = false;
		BufferedImage src = ImageIO.read(new File(fromFile));

		Image image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = dest.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		flag = ImageIO.write(dest, format, new FileOutputStream(toFile));
		return flag;
	}


}
