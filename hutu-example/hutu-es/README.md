### 注解说明
```java
    public @interface Document {
     
    String indexName(); //索引库的名称，个人建议以项目的名称命名
     
    String type() default ""; //类型，个人建议以实体的名称命名
     
    short shards() default 5; //默认分区数
     
    short replicas() default 1; //每个分区默认的备份数
     
    String refreshInterval() default "1s"; //刷新间隔
     
    String indexStoreType() default "fs"; //索引文件存储类型
    }
    
    public @interface Field {
     
    FieldType type() default FieldType.Auto; //自动检测属性的类型，可以根据实际情况自己设置
     
    FieldIndex index() default FieldIndex.analyzed; //默认情况下分词，一般默认分词就好，除非这个字段你确定查询时不会用到
     
    DateFormat format() default DateFormat.none; //时间类型的格式化
     
    String pattern() default ""; 
     
    boolean store() default false; //默认情况下不存储原文
     
    String searchAnalyzer() default ""; //指定字段搜索时使用的分词器
     
    String indexAnalyzer() default ""; //指定字段建立索引时指定的分词器
     
    String[] ignoreFields() default {}; //如果某个字段需要被忽略
     
    boolean includeInParent() default false;
    }

    // 多个属性情况实例
    @MultiField(mainField = @Field(type = FieldType.Text),
            otherFields = {
            @InnerField(suffix = ElasticConstant.KEYWORD, type = FieldType.Text, analyzer = AnalyzerConstant.IK_SMART),
            @InnerField(suffix = "saleName", type = FieldType.Text, analyzer = AnalyzerConstant.IK_SMART),
            @InnerField(suffix = "prefixName", type = FieldType.Text, analyzer = AnalyzerConstant.PINYIN),
            }
    )
```

### 总结(摘自网络，部分待验证)
分析器主要有两种情况会被使用，一种是插入文档时，将text类型的字段做分词然后插入倒排索引，第二种就是在查询时，先对要查询的text类型的输入做分词，再去倒排索引搜索
如果想要让 索引 和 查询 时使用不同的分词器，ElasticSearch也是能支持的，只需要在字段上加上search_analyzer参数
在索引时，只会去看字段有没有定义analyzer，有定义的话就用定义的，没定义就用ES预设的
在查询时，会先去看字段有没有定义search_analyzer，如果没有定义，就去看有没有analyzer，再没有定义，才会去使用ES预设的
