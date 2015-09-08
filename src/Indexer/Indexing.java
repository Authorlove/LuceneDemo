package Indexer;
import Config.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Indexing {
	/**
	 * 创建索引
	 * 
	 * @param analyzer
	 * @throws Exception
	 */
	public static void createIndex(Analyzer analyzer) throws Exception {
		Directory dire = FSDirectory.open(new File(Config.INDEX_STORE_PATH));
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46,
				analyzer);
		IndexWriter iw = new IndexWriter(dire, iwc);
		Indexing.addDoc(iw);
		iw.close();
	}

	/**
	 * 动态添加Document
	 * 
	 * @param iw
	 * @throws Exception
	 */
	public static void addDoc(IndexWriter iw) throws Exception {
		long start = System.currentTimeMillis();
		File[] files = new File(Config.INDEX_FILE_PATH).listFiles();
		for (File file : files) {
			Document doc = new Document();
			String content = Indexing.getContent(file);
			String name = file.getName();
			String path = file.getAbsolutePath();
			doc.add(new TextField("content", content, Store.YES));
			doc.add(new TextField("name", name, Store.YES));
			doc.add(new TextField("path", path, Store.YES));
			System.out.println("Indexing "+name);
			iw.addDocument(doc);
			iw.commit();
		}
		long end = System.currentTimeMillis();
		System.out.printf("共检索了%d个文件，用时%fs\n",files.length,(end-start)/1000.0);
	}

	/**
	 * 获取文本内容
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String getContent(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			sb.append(line + "\n");
			line = null;
		}
		return sb.toString();
	}
	public static void main(String[] args) throws Exception, Exception {
		createIndex(Config.analyzer);
	}
}
