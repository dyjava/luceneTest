package my.applet.swing.chinacity;

/** 
 * by dyong 2010-7-22
 */
public class City {

	private String id ;				//ID
	private String cname ;			//城市名
	private String alias ;			//城市简称
	private String s_spell ;		//简拼
	private String a_spell ;		//全拼
	private String parent_id ;		//上级ID
	
	private String instact_name ;	//完整名称 省、地区、市
	private String instact_spell ;	//完整拼写
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getS_spell() {
		return s_spell;
	}
	public void setS_spell(String sSpell) {
		s_spell = sSpell;
	}
	public String getA_spell() {
		return a_spell;
	}
	public void setA_spell(String aSpell) {
		a_spell = aSpell;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public String getInstact_name() {
		return instact_name;
	}
	public void setInstact_name(String instactName) {
		instact_name = instactName;
	}
	public String getInstact_spell() {
		return instact_spell;
	}
	public void setInstact_spell(String instactSpell) {
		instact_spell = instactSpell;
	}
	
}
