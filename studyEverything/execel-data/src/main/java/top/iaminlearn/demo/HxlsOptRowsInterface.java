package top.iaminlearn.demo;

/**
 * Date: 2022/2/25 0:52
 */

import java.util.List;

public interface HxlsOptRowsInterface {

    public static final String SUCCESS="success";
    /**
     * ����excel�ļ�ÿ�����ݷ���
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     * @return success���ɹ�������Ϊʧ��ԭ��
     * @throws Exception
     */
    public String optRows(int sheetIndex, int curRow, List<String> rowlist) throws Exception;
}