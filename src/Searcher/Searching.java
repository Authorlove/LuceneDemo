package Searcher;
import java.io.File;
import java.util.Scanner;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import Config.Config;

public class Searching {
	/**
	 * 搜索
	 * @param query
	 * @throws Exception
	 */
	private static void search(Query query,String str) throws Exception {
		Directory dire=FSDirectory.open(new File(Config.INDEX_STORE_PATH));
		IndexReader ir=DirectoryReader.open(dire);
		IndexSearcher is=new IndexSearcher(ir);
		TopDocs td=is.search(query, 10);
		System.out.println("共为您查找到"+td.totalHits+"条结果\n以下为前十条结果");
		ScoreDoc[] sds =td.scoreDocs;
		for (ScoreDoc sd : sds) { 
			Document d = is.doc(sd.doc); 
			System.out.println("<"+str+"     "+d.get("name") + "    "+sd.score+">"); 
		}
		System.out.println("");
	}
	public static void main(String[] args) throws Exception, Exception {
		QueryParser parser = new QueryParser(Version.LUCENE_46, "content",Config.analyzer); 
		for(String qStr:Config.queryStr){
			Query query = parser.parse(qStr);
            search(query,qStr);
		}
	}
}
