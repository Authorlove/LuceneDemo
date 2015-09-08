package Config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Config {
	public final static String INDEX_FILE_PATH = "D:\\java_workspace\\LuceneDemo\\IRlab2015S"; //�������ļ��Ĵ��·�� ����ʱ�����ڱ�Ŀ¼�����н�һЩ�ĵ�,�������б༭����
    public final static String INDEX_STORE_PATH = "D:\\java_workspace\\LuceneDemo\\Index"; //�����Ĵ��λ��
//	 public static Analyzer analyzer=new IKAnalyzer(true);
	 public static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//	public static Analyzer analyzer = new CJKAnalyzer(Version.LUCENE_46);
	public static String[] queryStr = {"��������","��������","��˹��","�й��ͻ�","�й�����","������Ȩ��","������װ","ҽ�Ʊ���"};
}
