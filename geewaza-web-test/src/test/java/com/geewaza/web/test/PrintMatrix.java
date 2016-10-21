package com.geewaza.web.test;

import java.io.*;
import java.util.Arrays;

/**
 * Created by WangHeng on 2016/10/13.
 */
public class PrintMatrix {
    private static final String fileName = "/opt/file/matrix_input.txt";

    /**
     * 读取文件中的矩阵
     * @param fileName
     * @return
     * @throws IOException
     */
    private static int[][] readMatrixFromFile(String fileName) throws IOException {
        int matrix_x = 0;
        int matrix_y = Integer.MAX_VALUE;

        int[][] result = null;
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        String line = null;
        int lineNum = 0;
        while ((line = reader.readLine()) != null && lineNum - 2 < matrix_y) {
            lineNum++;
            if (lineNum == 1) {
                matrix_y = Integer.parseInt(line);
                continue;
            } else if (lineNum == 2) {
                matrix_x = Integer.parseInt(line);
                result = new int[matrix_y][matrix_x];
                continue;
            }
            String[] nums = line.split(" ");
            int x = 0;
            for (String num : nums) {
                result[lineNum - 3][x++] = Integer.parseInt(num);
            }
        }
        return result;
    }

    /**
     * 顺时针打印
     * @param matrix
     */
    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int startX = 0;
        int startY = 0;
        int endX = matrix[0].length - 1;
        int endY = matrix.length - 1;
        while ((startX <= endX) && (startY <= endY)) {
            int x = startX;
            int y = startY;
            if (startX == endX) {//只剩一列
                for (;y <= endY; y++) {
                    System.out.print(matrix[y][x] + " ");
                }
                return ;
            }
            if (startY == endY) {//只剩一行
                for (;x <= endX; x++) {
                    System.out.print(matrix[y][x] + " ");
                }
                return ;
            }

            //向右
            for (; x <= endX; x++) {
                System.out.print(matrix[startY][x] + " ");
            }

            //向下
            for (y = startY + 1;y <= endY; y++) {
                System.out.print(matrix[y][endX] + " ");
            }
            //向左
            for (x = endX - 1; x >= startX; x--) {
                System.out.print(matrix[endY][x] + " ");
            }
            //向上
            for (y = endY - 1;y > startY; y--) {
                System.out.print(matrix[y][startX] + " ");
            }

            startX++;
            startY++;
            endX--;
            endY--;
        }


    }

    public static void main(String[] args) throws IOException {
        int[][] matrix = readMatrixFromFile(fileName);
        printMatrix(matrix);
    }

}
