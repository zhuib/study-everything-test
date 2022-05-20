package top.iaminlearn.crawler.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Date: 2021/5/6 20:51
 */
// 这里indexName不小写会报错
@Data
@Document(indexName = "jobinfo"/*, type = "jobInfoField"*/)
public class JobInfoField {

    @org.springframework.data.annotation.Id
    @Field(index = true, store = true, type = FieldType.Long)
    private Long id;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyName;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyAddr;
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyInfo;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String jobName;
    @Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String jobAddr;
    @Field(index = true, store = false, analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String jobInfo;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMin;
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMax;
    private String url;
    @Field(index = true, store = true, type = FieldType.Text)
    private String time;
    // 生成 get/set
    // 生成toString()
}