package my.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class Config {

	public static final Set<String> set = new HashSet<String>() ;
	public static final Version VERSION = Version.LUCENE_30 ;
	public static Analyzer analyzer = new StandardAnalyzer(Config.VERSION,Config.set) ;
	public static final String querykey = "keyword" ;
	
	//停留词
	static {
		String[] list = {"a","an","and","are","as","at","be","but","by","for","if","in","into","is","it","yes","not","of","on","or","s","such","t","that","the","their","then","there","these","they","this","to","was","will","with"};
		for(String s:list){
			set.add(s) ;
		}
	}
}
