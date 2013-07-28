package my.applet.swing;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import my.applet.swing.chinacity.City;
import my.applet.swing.chinacity.CityMain;
import my.applet.swing.pinyin.Pinyin4j;
import my.applet.swing.postcode.Post;
import my.applet.swing.postcode.PostMain;
import my.applet.swing.telphone.Tel;
import my.applet.swing.telphone.TelMain;

/** 
 * by dyong 2010-7-19
 */
public class Config {

	private String type = "city" ;
	
	public String out(CreateJFrame frame){
		String input = frame.inputText.getText() ;
		String out = input ;
		
		try {
			if(type.equals("pinyin")){
				out = Pinyin4j.getPinYin(input)+"\r\n"+input;
			} else if(type.equals("post")){
				out = PostMain.getInstance().searchToString(input) ;
			} else if(type.equals("city")){
				out = CityMain.getInstance().searchCity(input) ;
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out ;
	}
	
	public void getTableData(List<String> title,List data,CreateJFrame frame){
		String type =  frame.box.getSelectedItem().toString() ;
		String input = frame.inputText.getText() ;
		try {
			if(type.equals("拼音")){
				title.add("类型") ;
				title.add("数据") ;
				
				List list = new ArrayList() ;
				list.add("输入") ;
				list.add(input) ;
				data.add(list) ;
				
				list = new ArrayList() ;
				list.add("输出") ;
				list.add(Pinyin4j.getPinYin(input)) ;
				data.add(list) ;
			} else if(type.equals("邮编")){
				List list = PostMain.getInstance().searchList(input) ;
				title.add("省") ;
				title.add("市") ;
				title.add("县") ;
				title.add("邮编") ;
				title.add("地址") ;
				title.add("区号") ;
				for(int i=0;i<list.size();i++){
					List l = new ArrayList() ;
					Post post = (Post) list.get(i) ;
					l.add(post.getProvince()) ;
					l.add(post.getCity()) ;
					l.add(post.getArea()) ;
					l.add(post.getAddress()) ;
					l.add(post.getPostcode()) ;
					l.add(post.getDnumber()) ;
					data.add(l) ;
				}
			} else if(type.equals("城市")){
				List list = CityMain.getInstance().queryByKey(input) ;
				title.add("ID") ;
				title.add("全称") ;
				title.add("简称") ;
				title.add("全拼") ;
				title.add("简拼") ;
				title.add("上级ID") ;
				title.add("全路径") ;
				for(int i=0;i<list.size();i++){
					List l = new ArrayList() ;
					City city = (City) list.get(i) ;
					l.add(city.getId()) ;
					l.add(city.getCname()) ;
					l.add(city.getAlias()) ;
					l.add(city.getA_spell()) ;
					l.add(city.getS_spell()) ;
					l.add(city.getParent_id()) ;
					l.add(city.getInstact_name()) ;
					data.add(l) ;
				}
			} else if(type.equals("手机号")){
				List<Tel> list = TelMain.getInstance().searchList(input) ;
				title.add("ID") ;
				title.add("所属省") ;
				title.add("所属地区") ;
				title.add("所属号段") ;
				title.add("所属服务商") ;
				for(Tel tel:list){
					List<String> l = new ArrayList<String>() ;
					l.add(tel.getId()) ;
					l.add(tel.getProv()) ;
					l.add(tel.getCity()) ;
					l.add(tel.getTel()) ;
					l.add(tel.getType()) ;
					data.add(l) ;
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
