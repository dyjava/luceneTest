package my.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class Config {

	public static final Version VERSION = Version.LUCENE_30 ;
	public static Analyzer analyzer = new StandardAnalyzer(Config.VERSION) ;
	public static final String querykey = "keyword" ;
}
