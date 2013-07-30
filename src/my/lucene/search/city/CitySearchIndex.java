package my.lucene.search.city;

import java.io.File;
import java.io.IOException;
import java.util.List;

import my.lucene.search.SearchFactory;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

/** 
 * by dyong 2010-7-23
 */
public class CitySearchIndex {

	private SearchFactory factory ;
	private String searchKey ;
	public CitySearchIndex(String indexDir,String searchKey){
		File file = new File(indexDir) ;
		factory = SearchFactory.getinstens(file) ;
		this.searchKey = searchKey ;
	}
	
	public List<City> queryIndex(String key){
		try {
			return factory.search(key, searchKey, City.class, 10) ;
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

	public List<City> queryAll(String key){
		try {
			return factory.search(key, City.class) ;
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
