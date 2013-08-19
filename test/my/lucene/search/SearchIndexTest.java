package my.lucene.search;

import java.io.File;
import java.util.List;

import my.lucene.search.city.City;
import my.lucene.search.city.CityConfig;

import junit.framework.TestCase;

public class SearchIndexTest extends TestCase{

	private SearchFactory factory ;
	public SearchIndexTest(){
		File indexdir = new File(CityConfig.indexDir) ;
		factory = SearchFactory.getinstens(indexdir) ;
	}
	public void testSearch(){
		String keyword = "北京" ;
		try {
			List<City> list = factory.search(keyword , City.class) ;
			assertNotNull(list) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSearch1(){
		String keyword = "北京" ;
		try {
			List<City> list = factory.search(keyword , City.class) ;
			assertTrue(list.size()>0) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testSearch2(){
		String keyword = "北京" ;
		try {
			List<City> list = factory.search(keyword , City.class) ;
			assertTrue(list.get(0).getCname().equals("北京市")) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
