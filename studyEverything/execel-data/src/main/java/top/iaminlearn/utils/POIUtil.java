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
 * excel��д������ */
@Component
public class POIUtil {
    private static Logger logger  = Logger.getLogger(POIUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    //ͼƬ��λ�û�ȡ
    public static List<Map<String,Object>> readExcel(MultipartFile file) throws Exception{
        //�������ض��󣬰�ÿ��sheet�е�ֵ��Ϊһ��Map������sheet��Ϊһ�����Ϸ���
        List<Map<String,Object>> list = new ArrayList<>();

        // ����Workbook
        Workbook wb = getWorkBook(file);
        // ����sheet
        Sheet sheet = null;

        //��ȡexcel sheet����
        int sheetNumbers = wb.getNumberOfSheets();
        // sheet list
        List<Map<String, PictureData>> sheetList = new ArrayList<Map<String, PictureData>>();

        // ѭ��sheet
        for (int i = 0; i < sheetNumbers; i++) {
            // ��õ�ǰsheet������
            sheet = wb.getSheetAt(i);
            if(sheet == null){
                continue;
            }
            // map�ȴ��洢excelͼƬ
            Map<String, PictureData> sheetIndexPicMap;
            /*
             *	1����ȡexcel��ͼƬ
             */
            // ��ȡ�ļ���׺��
            String fileExt =  file.getName().substring(file.getName().lastIndexOf(".") + 1);
            // �ж���07����03�ķ�����ȡͼƬ
            if (fileExt.equals("xls")) {
                sheetIndexPicMap = getSheetPictrues03(i, (HSSFSheet) sheet, (HSSFWorkbook) wb);
            } else {
                sheetIndexPicMap = getSheetPictrues07(i, (XSSFSheet) sheet, (XSSFWorkbook) wb);
            }
            // ����ǰsheetͼƬmap����list
            sheetList.add(sheetIndexPicMap);
            /*
             *	2����ȡexcel������
             */
            // ��ÿ���е�ֵ��Ϊһ�����飬��������Ϊһ�����Ϸ���
            List<String[]> data = new ArrayList<String[]>();
            //��õ�ǰsheet�Ŀ�ʼ��
            int firstRowNum = sheet.getFirstRowNum();
            //��õ�ǰsheet�Ľ�����
            int lastRowNum = sheet.getLastRowNum();
            //ѭ�����˵�һ�е�������
            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                //��õ�ǰ��
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                //��õ�ǰ�еĿ�ʼ��
                int firstCellNum = row.getFirstCellNum();
                //��õ�ǰ�е�����
                //int lastCellNum = row.getPhysicalNumberOfCells();
                //��ȡ��һ�е�����
                int lastCellNum = sheet.getRow(0).getPhysicalNumberOfCells();
                String[] cells = new String[lastCellNum];
                //ѭ����ǰ��
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                data.add(cells);
            }

            Map map = new HashMap();
            map.put("file",sheetIndexPicMap);  //ͼƬ��
            map.put("data", data);  //���ݼ�
            list.add(map);
        }
        //��ͼƬ���浽ָ��λ��
        printImg(sheetList);
        return list;
    }

    //���Workbook����������
    public static Workbook getWorkBook(MultipartFile file) {
        //����ļ���
        String fileName = file.getOriginalFilename();
        //����Workbook���������󣬱�ʾ����excel
        Workbook workbook = null;
        try {
            //��ȡexcel�ļ���io��
            InputStream is = file.getInputStream();
            //�����ļ���׺����ͬ(xls��xlsx)��ò�ͬ��Workbookʵ�������
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
     * ��ȡExcel2003ͼƬ
     * @param sheetNum ��ǰsheet���
     * @param sheet ��ǰsheet����
     * @param workbook ����������
     * @return Map key:ͼƬ��Ԫ��������0_1_1��String��value:ͼƬ��PictureData
     * @throws IOException
     */
    public static Map<String, PictureData> getSheetPictrues03(int sheetNum,
                                                              HSSFSheet sheet, HSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
        //����һ��Excel���е�����ͼƬ�ļ���
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                int i=0;
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = pictures.get(pictureIndex);
                    String picIndex = System.currentTimeMillis() +"_"  //ʱ���
                            + String.valueOf(sheetNum) + "_"	//�ڼ���sheet
                            + String.valueOf(anchor.getRow1()) + "_"	//�к�
                            + String.valueOf(anchor.getCol1()) + "_"	//�к�
                            + i++;	//�ڼ���ͼƬ��
                    sheetIndexPicMap.put(picIndex, picData);
                }
            }
            return sheetIndexPicMap;
        } else {
            return null;
        }
    }

    /**
     * 07��ʽexcel��ȡͼƬ��
     * @param sheetNum ��ǰsheet���
     * @param sheet ��ǰsheet����
     * @param workbook ����������
     * @return Map key:ͼƬ��Ԫ��������0_1_1��String��value:ͼƬ��PictureData
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
                    String picIndex =System.currentTimeMillis() +"_"	//ʱ���
                            + String.valueOf(sheetNum) + "_"	//�ڼ��� sheet
                            + ctMarker.getRow() + "_"	//�к�
                            + ctMarker.getCol() + "_"	//�к�
                            + i++;	//�ڼ���ͼƬ
                    sheetIndexPicMap.put(picIndex, pic.getPictureData());
                }
            }
        }

        return sheetIndexPicMap;
    }

    // ��ȡ��ǰ�е�ǰ�е�����
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //�����ֵ���String�������������1����1.0�����
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //�ж����ݵ�����
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //����
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //�ַ���
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //��ʽ
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //��ֵ
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //����
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    //��ͼƬ���浽ָ��λ��
    public static void printImg(List<Map<String, PictureData>> sheetList) throws IOException {

        for (Map<String, PictureData> map : sheetList) {
            Object key[] = map.keySet().toArray();
            for (int i = 0; i < map.size(); i++) {
                // ��ȡͼƬ��
                PictureData pic = map.get(key[i]);
                // ��ȡͼƬ����
                String picName = key[i].toString();
                // ��ȡͼƬ��ʽ
                String ext = pic.suggestFileExtension();

                byte[] data = pic.getData();

                FileOutputStream out = new FileOutputStream("D:\\pic\\img" + picName + "." + ext);
                out.write(data);
                out.close();
            }
        }
    }
}

