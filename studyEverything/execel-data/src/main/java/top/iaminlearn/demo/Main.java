package top.iaminlearn.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Date: 2022/2/25 0:53
 */
public class Main{
    public static void main(String[] args){
        ExcelImportUtil importUtil;
        try {
            importUtil = new ExcelImportUtil("D:\\data.xls",0, new HxlsInterfaceImpl());
            importUtil.process();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
