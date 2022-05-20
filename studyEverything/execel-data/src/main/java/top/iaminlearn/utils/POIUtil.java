package top.iaminlearn.utils;

/**
 * Date: 2022/2/25 0:30
 */

import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel读写工具类 */
@Component
public class POIUtil {
    private static Logger logger  = Logger.getLogger(POIUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    //图片及位置获取
    public static List<Map<String,Object>> readExcel(MultipartFile file) throws Exception{
        //创建返回对象，把每个sheet中的值作为一个Map，所有sheet作为一个集合返回
        List<Map<String,Object>> list = new ArrayList<>();

        // 创建Workbook
        Workbook wb = getWorkBook(file);
        // 创建sheet
        Sheet sheet = null;

        //获取excel sheet总数
        int sheetNumbers = wb.getNumberOfSheets();
        // sheet list
        List<Map<String, PictureData>> sheetList = new ArrayList<Map<String, PictureData>>();

        // 循环sheet
        for (int i = 0; i < sheetNumbers; i++) {
            // 获得当前sheet工作表
            sheet = wb.getSheetAt(i);
            if(sheet == null){
                continue;
            }
            // map等待存储excel图片
            Map<String, PictureData> sheetIndexPicMap;
            /*
             *	1、获取excel中图片
             */
            // 获取文件后缀名
            String fileExt =  file.getName().substring(file.getName().lastIndexOf(".") + 1);
            // 判断用07还是03的方法获取图片
            if (fileExt.equals("xls")) {
                sheetIndexPicMap = getSheetPictrues03(i, (HSSFSheet) sheet, (HSSFWorkbook) wb);
            } else {
                sheetIndexPicMap = getSheetPictrues07(i, (XSSFSheet) sheet, (XSSFWorkbook) wb);
            }
            // 将当前sheet图片map存入list
            sheetList.add(sheetIndexPicMap);
            /*
             *	2、获取excel中数据
             */
            // 把每行中的值作为一个数组，所有行作为一个集合返回
            List<String[]> data = new ArrayList<String[]>();
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            //循环除了第一行的所有行
            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                //获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数
                //int lastCellNum = row.getPhysicalNumberOfCells();
                //获取第一行的列数
                int lastCellNum = sheet.getRow(0).getPhysicalNumberOfCells();
                String[] cells = new String[lastCellNum];
                //循环当前行
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                data.add(cells);
            }

            Map map = new HashMap();
            map.put("file",sheetIndexPicMap);  //图片集
            map.put("data", data);  //数据集
            list.add(map);
        }
        //将图片保存到指定位置
        printImg(sheetList);
        return list;
    }

    //获得Workbook工作薄对象
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    /**
     * 获取Excel2003图片
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     * @throws IOException
     */
    public static Map<String, PictureData> getSheetPictrues03(int sheetNum,
                                                              HSSFSheet sheet, HSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        //返回一个Excel表中的所有图片的集合
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                int i=0;
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);
                    String picIndex = System.currentTimeMillis() +"_"  //时间戳
                            + String.valueOf(sheetNum) + "_"	//第几个sheet
                            + String.valueOf(anchor.getRow1()) + "_"	//行号
                            + String.valueOf(anchor.getCol1()) + "_"	//列号
                            + i++;	//第几张图片号
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }
            return sheetIndexPicMap;
        } else {
            return null;
        }
    }

    /**
     * 07格式excel获取图片。
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getSheetPictrues07(int sheetNum,
                                                              XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        int i =0;
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picIndex =System.currentTimeMillis() +"_"	//时间戳
                            + String.valueOf(sheetNum) + "_"	//第几个 sheet
                            + ctMarker.getRow() + "_"	//行号
                            + ctMarker.getCol() + "_"	//列号
                            + i++;	//第几张图片
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }

        return sheetIndexPicMap;
    }

    // 获取当前行当前列的数据
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    //将图片保存到指定位置
    public static void printImg(List<Map<String, PictureData>> sheetList) throws IOException {

        for (Map<String, PictureData> map : sheetList) {
            Object key[] = map.keySet().toArray();
            for (int i = 0; i < map.size(); i++) {
                // 获取图片流
                PictureData pic = map.get(key[i]);
                // 获取图片索引
                String picName = key[i].toString();
                // 获取图片格式
                String ext = pic.suggestFileExtension();

                byte[] data = pic.getData();

                FileOutputStream out = new FileOutputStream("D:\\pic\\img" + picName + "." + ext);
                out.write(data);
                out.close();
            }
        }
    }
}

