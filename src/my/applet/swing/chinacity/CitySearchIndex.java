package my.applet.swing.chinacity;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Sort;

import my.applet.swing.readIndex.IndexFather;

/** 
 * by dyong 2010-7-23
 */
public class CitySearchIndex extends IndexFather {
	
	public CitySearchIndex(String indexDir, String searchKey, boolean inMemory) {
		super(indexDir, searchKey, inMemory);
	}
	public CitySearchIndex(String indexDir, String searchKey) {
		super(indexDir, searchKey, false);
	}
	
	@Override
	protected Object DocumentToObject(Document doc) {
		// TODO Auto-generated method stub
		City c = new City() ;
		c.setId(doc.get("id")) ;
		c.setCname(doc.get("cname")) ;
		c.setAlias(doc.get("alias")) ;
		c.setS_spell(doc.get("s_spell")) ;
		c.setA_spell(doc.get("a_spell")) ;
		c.setParent_id(doc.get("parent_id")) ;
		
		c.setInstact_name(doc.get("instact_name")) ;
		c.setInstact_spell(doc.get("instact_spell")) ;
		return c;
	}

	@Override
	protected Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

}
