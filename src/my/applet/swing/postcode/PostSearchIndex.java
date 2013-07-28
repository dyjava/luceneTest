package my.applet.swing.postcode;

import my.applet.swing.readIndex.IndexFather;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Sort;

public class PostSearchIndex extends IndexFather{
	
	public PostSearchIndex(String indexDir, String searchKey, boolean inMemory) {
		super(indexDir, searchKey, inMemory);
		// TODO Auto-generated constructor stub
	}

	public PostSearchIndex(String indexDir,String searchKey){
		super(indexDir, searchKey, false);
	}

	@Override
	protected Object DocumentToObject(Document doc) {
		// TODO Auto-generated method stub
		Post post = new Post() ;
		post.setId(doc.get("id"));
		post.setProvince(doc.get("prov"));
    	post.setCity(doc.get("city")) ;
    	post.setArea(doc.get("area")) ;
    	post.setAddress(doc.get("addr")) ;
    	post.setDnumber(doc.get("dnum")) ;
    	post.setPostcode(doc.get("post")) ;
		return post;
	}

	@Override
	protected Sort getSort() {
		// TODO Auto-generated method stub
		
		return null;
	}
	

}
