package my.lucene.search.telphone;

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
		File dataFile = new File(TelConfig.indexDir) ;
		// TODO Auto-generated method stub
		IndexFactory factory = new IndexFactory(dataFile) ;
		List<Tel> datas = this.getDatas(new File(TelConfig.souceDir)) ;
		factory.bulidIndex(datas) ;
	}
	
	private List<Tel> getDatas(File file){
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
	
}
