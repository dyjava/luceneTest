package my.applet.swing.readIndex;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Sort;

/** 
 * by dyong 2010-7-8
 */
public abstract class IndexFather {

	protected boolean INDEX_IN_MEMORY = false ;
	protected String INDEX_SEARCH_KEY = "key";
	protected String INDEX_DIR ;		//索引存放的位置
	
	public static final String standardAnalyzer = "standard" ;
	public static final String keywordAnalyzer = "keyword" ;
	private Analyzer analyzer = new StandardAnalyzer();
	
	public IndexFather(String indexDir,String searchKey,boolean in_memory){
		INDEX_IN_MEMORY = in_memory ;
		INDEX_SEARCH_KEY = searchKey ;
		INDEX_DIR = indexDir ;
	}
	
//	Document to object
	protected abstract Object DocumentToObject(Document doc) ;
	
//	查询排序
	protected abstract Sort getSort() ;
	
	public List<Object> queryIndex(String key){
		if(key==null || key.trim().length()==0){
			return null ;
		}
		return new SearchIdx(this).queryIndex(key) ;
	}
	
//	分词器
	protected Analyzer getAnalyzer(){
		return analyzer ;
	}
	public void setAnalyzer(String type){
		if(type.equals(standardAnalyzer)){
			analyzer = new StandardAnalyzer() ;
		}
		if(type.equals(keywordAnalyzer)){
			analyzer = new KeywordAnalyzer() ;
		}
	}

}
