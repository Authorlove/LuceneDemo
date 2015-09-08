package Config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Config {
	public final static String INDEX_FILE_PATH = "D:\\java_workspace\\LuceneDemo\\IRlab2015S"; //索引的文件的存放路径 测试时可以在本目录下自行建一些文档,内容自行编辑即可
    public final static String INDEX_STORE_PATH = "D:\\java_workspace\\LuceneDemo\\Index"; //索引的存放位置
//	 public static Analyzer analyzer=new IKAnalyzer(true);
	 public static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//	public static Analyzer analyzer = new CJKAnalyzer(Version.LUCENE_46);
	public static String[] queryStr = {"建筑艺术","电子政务","奥斯卡","中国油画","中国武术","消费者权益","汽车改装","医疗保险"};
}
