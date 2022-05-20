package top.iaminlearn.demo;

/**
 * Date: 2022/2/25 0:52
 */

import java.util.List;

public interface HxlsOptRowsInterface {

    public static final String SUCCESS="success";
    /**
     * 处理excel文件每行数据方法
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     * @return success：成功，否则为失败原因
     * @throws Exception
     */
    public String optRows(int sheetIndex, int curRow, List<String> rowlist) throws Exception;
}