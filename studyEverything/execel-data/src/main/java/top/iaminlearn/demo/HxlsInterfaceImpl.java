package top.iaminlearn.demo;

/**
 * Date: 2022/2/25 0:52
 */

import java.util.List;

public class HxlsInterfaceImpl implements HxlsOptRowsInterface {

    @Override
    public String optRows(int sheetIndex, int curRow, List<String> datalist)
            throws Exception {
        //在这里执行数据的插入
        System.out.println(datalist);
        //saveData(datalist);
        return "";
    }
}