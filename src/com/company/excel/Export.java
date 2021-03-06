package com.company.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Export {

    public void export(LinkedHashMap lhm, String path) throws IOException {

        String surname = "Левадний";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(surname);

        sheet.setColumnWidth(0, 7500);

        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(1, CellType.STRING).setCellValue("X");
        row0.createCell(2, CellType.STRING).setCellValue("Y");
        row0.createCell(3, CellType.STRING).setCellValue("Z");

        Set set = lhm.entrySet();
        Iterator iterator = set.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            XSSFRow row = sheet.createRow(i);
            Map.Entry data = (Map.Entry) iterator.next();
            row.createCell(0, CellType.STRING).setCellValue((String) data.getKey());
            for (int j = 0; j < 3; j++) {
                row.createCell(j + 1, CellType.NUMERIC).setCellValue(((Double[]) data.getValue())[j]);
            }
            i++;
        }

        //String path = "C:\\fileExcelExport.xlsx";
        File file = new File(path);
        workbook.write(new FileOutputStream(file));
        workbook.close();

    }

}
