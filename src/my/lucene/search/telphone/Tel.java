package my.lucene.search.telphone;

/** 
 * by dyong 
 */
public class Tel {

	private int id ;
	private String tel ;	//手机号段
	private String prov ;	//归属省
	private String city ;	//归属市
	private String type="" ;	//运营商
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
