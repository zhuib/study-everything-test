package top.iaminlearn.demo;

/**
 * Date: 2022/2/25 0:52
 */

import java.util.List;

public class HxlsInterfaceImpl implements HxlsOptRowsInterface {

    @Override
    public String optRows(int sheetIndex, int curRow, List<String> datalist)
            throws Exception {
        //������ִ�����ݵĲ���
        System.out.println(datalist);
        //saveData(datalist);
        return "";
    }
}