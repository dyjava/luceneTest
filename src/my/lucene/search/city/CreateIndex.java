package my.lucene.search.city;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import my.lucene.index.IndexFactory;

public class CreateIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateIndex c = new CreateIndex() ;
		c.createTelIndex() ;
	}

	public void createTelIndex(){
		File dataFile = new File(CityConfig.indexDir) ;
		// TODO Auto-generated method stub
		IndexFactory factory = new IndexFactory(dataFile) ;
		List<City> datas = this.getDatas(new File(CityConfig.souceDir)) ;
		factory.bulidIndex(datas) ;
	}
	
	private List<City> getDatas(File file){
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
