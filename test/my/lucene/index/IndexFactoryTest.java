package my.lucene.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import my.lucene.search.city.City;
import my.lucene.search.city.CityConfig;
import my.lucene.search.telphone.Tel;
import my.lucene.search.telphone.TelConfig;

public class IndexFactoryTest extends TestCase{
	
	private IndexFactory factory ;
	public IndexFactoryTest(){
		String path = "e:/data/index" ;
		File index = new File(path) ;
		this.factory = new IndexFactory(index) ;
	}
	
	public void testBulidIndexTel(){
		File dataFile = new File(TelConfig.indexDir) ;
		List<Tel> datas = this.getTelDatas(new File(TelConfig.souceDir)) ;
		this.factory.setIndexfile(dataFile) ;
		this.factory.bulidIndex(datas) ;
	}

	public void testBulidIndexCity(){
		File dataFile = new File(CityConfig.indexDir) ;
		List<City> datas = this.getCityDatas(new File(CityConfig.souceDir)) ;
		this.factory.setIndexfile(dataFile) ;
		this.factory.bulidIndex(datas) ;
	}
	
	private List<Tel> getTelDatas(File file){
		List<Tel> list = new ArrayList<Tel>() ;

		try {
			FileReader fr = new FileReader(file);

			String record = "";
			BufferedReader br = new BufferedReader(fr);

			while ((record = br.readLine()) != null) {
				String[] items = record.trim().split("\t") ;
				if(items.length!=4){
					continue ;
				}
				Tel t = new Tel() ;
				t.setId(Integer.parseInt(items[0])) ;
				t.setProv(items[1]) ;
				t.setCity(items[2]) ;
				t.setTel(items[3]) ;
				list.add(t) ;
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list ;
	}

	private List<City> getCityDatas(File file){
		List<City> list = new ArrayList<City>() ;
		try {
			FileReader fr = new FileReader(file);

			String record = "";
			BufferedReader br = new BufferedReader(fr);

			while ((record = br.readLine()) != null) {
				String[] items = record.trim().split("\t") ;
				if(items.length<=5){
					continue ;
				}
				City t = new City() ;
				t.setId(items[0]) ;
				t.setCname(items[1]) ;
				t.setAlias(items[2]) ;
				t.setA_spell(items[3]) ;
				t.setS_spell(items[3]) ;
				t.setInstact_name(items[1]) ;
				t.setParent_id(items[4]) ;
				list.add(t) ;
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list ;
	}
}
