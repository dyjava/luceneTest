package my.lucene.search.telphone;

import java.util.List;

import org.apache.log4j.Logger;

/** 
 * by dyong 2010-11-16
 */
public class TelMain {
	private static Logger log = Logger.getLogger(TelMain.class.getName());
	private static String indexDir = TelConfig.indexDir ;
	
	private String INDEX_SEARCH_KEY = "key";
	private String INDEX_DIR ;
//	private String sp = "\t" ;
	
	private static TelMain telphone ;
	private TelMain(String path){
		INDEX_DIR = path ;
	}
	
	public static TelMain getInstance(){
		if(telphone==null){
			telphone = new TelMain(indexDir) ;
		}
		return telphone ;
	}
	
	public List<Tel> searchList(String key){
		List<Tel> l = searchListByKey(key) ;
		if(l==null || l.size()==0){
			l = searchByTel(key) ;
		}
		return l ;
	}
	public List<Tel> searchListByKey(String key){
		TelSearchIndex read = new TelSearchIndex(INDEX_DIR,INDEX_SEARCH_KEY) ;
		List<Tel> l = read.queryIndex(key) ;
		return l ;
	}
	public List<Tel> searchByTel(String tel){
		TelSearchIndex search = new TelSearchIndex(INDEX_DIR, "tel") ;
		tel = tel.substring(0,7) ;
		List<Tel> l = search.queryTelpho(tel) ;
		return l ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Tel> list = TelMain.getInstance().searchList("保定") ;
		log.info(list.size()) ;
	}

}
