package co.bugu.framework.util.exception;

import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.conn.Wire;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/1/16.
 */
public class ExcelUtilNew {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtilNew.class);

    public static void main(String[] args) throws Exception {
        List<String> title = new ArrayList<>();
        title.add("项目");
        title.add("地址");
        title.add("电话");
        List<List<String>> content = new ArrayList<>();
        content.add(title);
        excelToFile(writeToExcel("", title, content), "d:/txt.xlsx");
    }

    public static void excelToFile(Workbook workbook, String path) throws IOException {
        File file = new File(path);
        OutputStream out = new FileOutputStream(file);
        workbook.write(out);

    }
    /**
     * 把数据写入表格
     * @param name
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    public static Workbook writeToExcel(String name, List<String> title, List<List<String>> content) throws Exception {
        Workbook workbook = createExcel("xlsx");
        Sheet sheet = createSheet(workbook, null);
        writeTitle(workbook, sheet, title);
        writeContent(workbook, sheet, content);
        return workbook;
    }

    public static Workbook createExcel(String type) throws Exception {
        Workbook workbook = null;
        if (type.equals("xls")) {
            workbook = new HSSFWorkbook();
        } else if (type.equals("xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            throw new Exception("需要创建的excel表格格式错误");
        }
        return workbook;
    }

    public static Sheet createSheet(Workbook workbook, String name) {
        if (StringUtils.isEmpty(name)) {
            name = "第一页";
        }
        Sheet sheet = workbook.createSheet();
        return sheet;
    }

    /**
     * 写入标题
     * 从第0行开始写入
     *
     * @param workbook
     * @param sheet
     * @param title
     */
    public static void writeTitle(Workbook workbook, Sheet sheet, List<String> title) {
        CellStyle cellStyle = createCellStyle(workbook, true);
        Row row = sheet.createRow(0);
        for (int i = 0; i < title.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title.get(i));
        }
    }

    /**
     * 写入数据，行索引需要加上1，标题占用一行
     *
     * @param workbook
     * @param sheet
     * @param content
     */
    public static void writeContent(Workbook workbook, Sheet sheet, List<List<String>> content) {
        CellStyle cellStyle = createCellStyle(workbook, false);
        for (int i = 0; i < content.size(); i++) {
            List<String> rowData = content.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(rowData.get(j));
            }
        }
    }

    /**
     * 创建样式
     *
     * @param workbook
     * @param isTitle
     * @return
     */
    public static CellStyle createCellStyle(Workbook workbook, boolean isTitle) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontName("微软雅黑");
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        if (isTitle) {
            cellStyle.setFillBackgroundColor(HSSFColor.BLUE.index2);
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        }
        return cellStyle;
    }
}
