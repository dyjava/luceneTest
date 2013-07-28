package my.applet.swing.chinacity;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;


/** 
 * by dyong 2010-7-23
 */
public class CityMain {
	private static Logger log = Logger.getLogger(CityMain.class.getName());
	
	private static String indexDir = System.getProperty("user.dir")+"/chinaCity" ;
	private String INDEX_SEARCH_KEY = "key";
	private String INDEX_DIR ;
	
	private static CityMain instance ;
	private CityMain(){
		INDEX_DIR = indexDir ;
	}
	public static CityMain getInstance(){
		if(instance==null){
			instance = new CityMain() ;
		}
		return instance ;
	}

	public String searchCity(String key){
		StringBuffer buf = new StringBuffer() ;
		List<City> list = queryByKey(key) ;
		for(City c:list){
			buf.append(c.getInstact_name()).append("\t").append(c.getA_spell()).append("\r\n") ;
		}
		return buf.toString() ;
	}
	public List<City> queryByKey(String key){
		List list = queryByName(key) ;
		if(list==null || list.size()==0){
			list = queryBySpell(key);
		}
		return list ;
	}
	
	public List<City> queryByName(String key){
		CitySearchIndex si = new CitySearchIndex(INDEX_DIR, "instact_name") ;
		si.setAnalyzer(CitySearchIndex.standardAnalyzer) ;
		
		List l = si.queryIndex(key) ;
		return l ;
	}
	
	public List<City> queryBySpell(String key){
		CitySearchIndex si = new CitySearchIndex(INDEX_DIR, "instact_spell") ;
		si.setAnalyzer(CitySearchIndex.standardAnalyzer) ;
		
		List l = si.queryIndex(key) ;
		return l ;
	}
	
	public List<City> queryListByParentId(String pid){
		CitySearchIndex si = new CitySearchIndex(INDEX_DIR, "parent_id") ;
		si.setAnalyzer(CitySearchIndex.keywordAnalyzer) ;
		if(pid==null || pid.length()!=8){
			pid = "0" ;
		}
		List l = si.queryIndex(pid) ;
		return l ;
	}
	
	public City queryById(String id){
		CitySearchIndex si = new CitySearchIndex(INDEX_DIR, "id") ;
		si.setAnalyzer(CitySearchIndex.keywordAnalyzer) ;
		
		List l = si.queryIndex(id) ;
		if(l!=null && l.size()==1){
			return (City) l.get(0) ;
		}
		return null ;
	}
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, IOException {
		CityMain city = CityMain.getInstance() ;
		List list = city.queryByName("石家庄") ;
		log.info(list.size()) ;
		
		list = city.queryBySpell("baoding") ;
		log.info(list.size()) ;
		
		list = city.queryListByParentId("22020000") ;
		log.info(list.size()) ;
		
		City c = city.queryById("22020000") ;
		log.info(c.getCname()) ;
	}
	
	
}
