package my.applet.swing.postcode;

import java.util.List;

import org.apache.log4j.Logger;


/** 
 * by dyong 2010-7-19
 */
public class PostMain {
	private static Logger log = Logger.getLogger(PostMain.class.getName());
	private static String indexDir = System.getProperty("user.dir")+"/chinaPost" ;
	
	private boolean INDEX_IN_MEMORY = false ;
	private String INDEX_SEARCH_KEY = "key";
	private String INDEX_DIR ;
	private String sp = "\t" ;
	
	private static PostMain postcode ;
	private PostMain(String path){
		INDEX_DIR = path ;
	}
	
	public static PostMain getInstance(){
		if(postcode==null){
			postcode = new PostMain(indexDir) ;
		}
		return postcode ;
	}
	
	public List searchList(String key){
		PostSearchIndex read = new PostSearchIndex(INDEX_DIR,INDEX_SEARCH_KEY) ;
		List l = read.queryIndex(key) ;
		return l ;
	}
	public String searchToString(String key){
		PostSearchIndex read = new PostSearchIndex(INDEX_DIR,INDEX_SEARCH_KEY) ;
		List l = read.queryIndex(key) ;
		List<Post> list = l ;
		StringBuffer buf = new StringBuffer() ;
		for(Post p:list){
			buf
//			.append(p.getProvince()).append(sp)
			.append(p.getCity()).append(sp)
			.append(p.getArea()).append(sp)
			.append(p.getAddress()).append(sp)
			.append(p.getPostcode()).append(sp)
			.append(p.getDnumber())
			.append("\r\n");
		}
		return buf.toString() ;
	}

	public boolean isINDEX_IN_MEMORY() {
		return INDEX_IN_MEMORY;
	}

	public String getINDEX_SEARCH_KEY() {
		return INDEX_SEARCH_KEY;
	}

	public String getINDEX_DIR() {
		return INDEX_DIR;
	}
	
	public static void main(String[] args) {
		List<Post> list = PostMain.getInstance().searchList("保定") ;
		log.info(list.size()) ;
	}
}
