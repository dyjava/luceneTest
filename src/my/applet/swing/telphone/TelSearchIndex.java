package my.applet.swing.telphone;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Sort;

import my.applet.swing.readIndex.IndexFather;

/** 
 * by dyong 2010-11-16
 */
public class TelSearchIndex extends IndexFather {

	public TelSearchIndex(String indexDir, String searchKey, boolean inMemory) {
		super(indexDir, searchKey, inMemory);
	}

	public TelSearchIndex(String indexDir,String searchKey){
		super(indexDir, searchKey, false);
	}
	
	@Override
	protected Object DocumentToObject(Document doc) {
		Tel tel = new Tel() ;
		tel.setId(doc.get("id")) ;
		tel.setTel(doc.get("tel")) ;
		tel.setProv(doc.get("prov")) ;
		tel.setCity(doc.get("city")) ;
		tel.setType(doc.get("type")) ;
		return tel ;
	}

	@Override
	protected Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

}
