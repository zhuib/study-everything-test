package top.iaminlearn.demo;

/**
 * Date: 2022/2/25 0:52
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * excel���빤��
 * Created by charlin on 2017/9/7.
 */
public class ExcelImportUtil extends HxlsAbstract{

    //���ݴ���bean
    private  HxlsOptRowsInterface hxlsOptRowsInterface;
    //������������
    private int optRows_sum = 0;
    //�������ݳɹ�����
    private int optRows_success = 0;
    //��������ʧ������
    private int optRows_failure = 0;
    //excel���ÿ�б���
    private List<String> rowtitle ;
    //ʧ������
    private List<List<String>> failrows;
    //ʧ��ԭ��
    private List<String> failmsgs ;
    //Ҫ�����������ڵ�sheet����,��0��ʼ
    private int sheetIndex;

    public ExcelImportUtil(String filename, int sheetIndex, HxlsOptRowsInterface hxlsOptRowsInterface) throws IOException,
            FileNotFoundException, SQLException {
        super(filename);
        this.sheetIndex = sheetIndex;
        this.hxlsOptRowsInterface = hxlsOptRowsInterface;
        this.rowtitle = new ArrayList<String>();
        this.failrows = new ArrayList<List<String>>();
        this.failmsgs = new ArrayList<String>();
    }

    @Override
    public void optRows(int sheetIndex,int curRow, List<String> rowlist) throws Exception {
        /*for (int i = 0 ;i< rowlist.size();i++){
            System.out.print("'"+rowlist.get(i)+"',");
        }
        System.out.println();*/
        //��rowlist�ĳ��Ȳ���ͱ���һ��
        int k=rowtitle.size()-rowlist.size();
        for(int i=0;i<k;i++){
            rowlist.add(null);
        }
        if(sheetIndex == this.sheetIndex){
            optRows_sum++;
            if(curRow == 0){//��¼����
                rowtitle.addAll(rowlist);
            }else{
                String result = hxlsOptRowsInterface.optRows(sheetIndex, curRow, rowlist);
                if(!result.equals(hxlsOptRowsInterface.SUCCESS)){
                    optRows_failure++;
                    //ʧ������
                    failrows.add(new ArrayList<String>(rowlist));
                    failmsgs.add(result);
                }else{
                    optRows_success++;
                }
            }

        }
    }

    public long getOptRows_sum() {
        return optRows_sum;
    }

    public void setOptRows_sum(int optRows_sum) {
        this.optRows_sum = optRows_sum;
    }

    public long getOptRows_success() {
        return optRows_success;
    }

    public void setOptRows_success(int optRows_success) {
        this.optRows_success = optRows_success;
    }

    public long getOptRows_failure() {
        return optRows_failure;
    }

    public void setOptRows_failure(int optRows_failure) {
        this.optRows_failure = optRows_failure;
    }

    public List<String> getRowtitle() {
        return rowtitle;
    }

    public List<List<String>> getFailrows() {
        return failrows;
    }

    public List<String> getFailmsgs() {
        return failmsgs;
    }

    public void setFailmsgs(List<String> failmsgs) {
        this.failmsgs = failmsgs;
    }
}