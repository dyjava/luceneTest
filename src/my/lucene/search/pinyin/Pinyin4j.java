package my.lucene.search.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.log4j.Logger;

/** 
 * by dyong 
 */
public class Pinyin4j {
	private static Logger log = Logger.getLogger(Pinyin4j.class.getName());
	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		// TODO Auto-generated method stub
		
		String str = "尛	犇	骉	垚	壵	羴	猋	麤	鱻	赑	刕	掱	劦	毳	矗	飝	厽	叒	孨	歮	皛	畾	嚞	舙	灥	飍	馫	飝	厵	靐	龘" ;
		getPinYin(str +" 中国绿") ;
	}

	/** 
	 * 获取拼音 
	 *  
	 * @param zhongwen 
	 * @return 
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */  
	public static String getPinYin(String zhongwen)
	        throws BadHanyuPinyinOutputFormatCombination {
		log.info("Input ZhongWen=	" + zhongwen);
		
	    String zhongWenPinYin = "";
	    char[] chars = zhongwen.toCharArray();
	    
	    for (int i = 0; i < chars.length; i++) {
	        String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
	        		getDefaultOutputFormat());
	        // 当转换不是中文字符时,返回null
	        if (pinYin != null) {
//	        	zhongWenPinYin += capitalize(pinYin[0]);
	        	zhongWenPinYin += pinYin[0]+" ";
	        } else {
	        	zhongWenPinYin += chars[i];
	        }
	    }  
	
	    log.info("Output PinYin=	" + zhongWenPinYin);
	    return zhongWenPinYin;
	}
	
	/** 
	 * Default Format 默认输出格式 
	 *  
	 * @return 
	 */  
	private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
	    format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);// 没有音调数字
	    format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);// u显示格式：v u ü
	    return format;
	}
	
	/** 
	 * Capitalize 首字母大写 
	 *  
	 * @param s 
	 * @return 
	 */  
	@SuppressWarnings("unused")
	private static String capitalize(String s) {
		char ch[];
	    ch = s.toCharArray();
	    if (ch[0] >= 'a' && ch[0] <= 'z') {
	        ch[0] = (char) (ch[0] - 32);
	    }
	    String newString = new String(ch);
	    return newString;
	}  
}
