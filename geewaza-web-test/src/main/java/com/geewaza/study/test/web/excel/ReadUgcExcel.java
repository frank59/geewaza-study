package com.geewaza.study.test.web.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * Created by WangHeng on 2016/10/12.
 */
public class ReadUgcExcel {

    private static String fileName = "/opt/file/ugc.xlsx";


    public static void main(String[] args) throws IOException, InvalidFormatException {

        File file = new File(fileName);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        System.out.println(xssfSheet);

        int rowstart = xssfSheet.getFirstRowNum();
        int rowEnd = xssfSheet.getLastRowNum();
        for (int i = rowstart; i <= rowEnd; i++) {
            XSSFRow row = xssfSheet.getRow(i);
            if (null == row) continue;
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();
            for (int k = cellStart; k <= cellEnd; k++) {
                XSSFCell cell = row.getCell(k);
                if (null == cell) continue;
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        System.out.print(cell.getNumericCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        System.out.print(cell.getStringCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        System.out.println(cell.getBooleanCellValue()
                                + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        System.out.print(cell.getCellFormula() + "\t");
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        System.out.println("\t");
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        System.out.println("\t");
                        break;
                    default:
                        System.out.print("未知类型\t");
                        break;
                }
            }
            System.out.print("\n");
        }


    }

}
