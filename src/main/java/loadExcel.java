/**
 * Created by wujindong on 2018/4/12.
 */

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class loadExcel {
    public static void main(String []args){
        try {
            InputStream in = new FileInputStream("/Users/wujindong/Downloads/input.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

            XSSFCell cf = null;
            XSSFCell c = null;
            String stu = null;
            int length = sheetAt.getLastRowNum();
            for (int rowNum = 0; rowNum <= length; rowNum++) {
                XSSFRow r = sheetAt.getRow(rowNum);
                cf = r.getCell(0);
                c = r.getCell(1);
                System.out.println(cf.getStringCellValue() + ":" + c.getStringCellValue().trim());
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}