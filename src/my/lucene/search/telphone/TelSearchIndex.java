package my.lucene.search.telphone;

import java.io.File;
import java.io.IOException;
import java.util.List;

import my.lucene.search.SearchFactory;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;


/** 
 * by dyong 
 */
public class TelSearchIndex {

	private SearchFactory factory ;
	private String searchKey ;
	public TelSearchIndex(String indexDir,String searchKey){
		File file = new File(indexDir) ;
		factory = SearchFactory.getinstens(file) ;
		this.searchKey = searchKey ;
	}
	
	public List<Tel> queryIndex(String key){
		try {
			return factory.search(key, searchKey, Tel.class, 10) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Tel> queryTelpho(String tel){
		try {
			return factory.search(tel, searchKey, Tel.class, 10) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
