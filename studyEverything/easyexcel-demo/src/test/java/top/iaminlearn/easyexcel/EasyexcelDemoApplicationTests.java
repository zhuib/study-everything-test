package top.iaminlearn.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.iaminlearn.easyexcel.pojo.MallGoods;
import top.iaminlearn.easyexcel.pojo.Student_Read;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EasyexcelDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void readExcel() throws Exception {

        List<MallGoods> list =  new ArrayList<>();

        /*
         * EasyExcel 读取 是基于SAX方式
         * 因此在解析时需要传入监听器
         */
        // 第一个参数 为 excel文件路径
        // 读取时的数据类型
        // 监听器
        EasyExcel.read("D:\\tb_mall_goods_info" + ExcelTypeEnum.XLSX.getValue(), MallGoods.class, new AnalysisEventListener<MallGoods>() {

            // 每读取一行就调用该方法
            @Override
            public void invoke(MallGoods data, AnalysisContext context) {
                list.add(data);
            }

            // 全部读取完成就调用该方法
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("读取完成");
            }
        }).sheet().doRead();

        list.forEach(System.out::println);
    }
}
