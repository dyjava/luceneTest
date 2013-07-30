package my.lucene.search;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.config.Config;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

public class SearchFactory extends SearchIndex{
	private static Logger log = Logger.getLogger(SearchFactory.class.getName());

	private static SearchFactory factory ;
	private boolean hightline ;
	private Highlighter highlighter ;
	private SearchFactory(File indexdir, boolean ram){
		super(indexdir, ram) ;
	}
	private SearchFactory(File indexdir){
		super(indexdir) ;
	}

	public static SearchFactory getinstens(File indexdir){
		if(factory==null){
			factory = new SearchFactory(indexdir) ;
		}
		return factory ;
	}
	
	/**
	 * search
	 * @param keyword
	 * @param T
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> search(String keyword,Class<?> T)
			throws ParseException, IOException, InvalidTokenOffsetsException, InstantiationException, IllegalAccessException{
		long start = System.currentTimeMillis() ;
		StringBuffer logs = new StringBuffer(this.getClass().getName()).append("|search|") ;
		logs.append(keyword).append("|") ;
		
		QueryParser parser = new QueryParser(Config.VERSION, Config.querykey, analyzer);
		Query query = parser.parse(keyword);
		this.setHightlighter(query) ;
		
		IndexSearcher isearcher = this.getIndexSearcher() ;
		TopDocs topDocs = isearcher.search(query, 50) ;
//		TopDocs topDocs2 = this.getIndexSearcher(name).searchAfter(after, query, 10);
		ScoreDoc[] hits = topDocs.scoreDocs;
//		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
//		Scorer scorer = new QueryScorer(query);
//		Highlighter highlighter = new Highlighter(formatter, scorer);
		
		List<T> list = new ArrayList<T>() ;
		for(ScoreDoc scoreDoc : hits){
			Document hitDoc = isearcher.doc(scoreDoc.doc);
			list.add((T) this.doc2Object(hitDoc, T)) ;
//			String id = hitDoc.get("id");
//			String title = hitDoc.get("title");
//			String content = hitDoc.get("content");
//			float score = scoreDoc.score;
//			title = highlighter.getBestFragment(analyzer, "title", title);
//			content = highlighter.getBestFragment(analyzer, "content", content);
//			if(title == null){
//				title = hitDoc.get("title");
//			}
//			if(content == null){
//				content = hitDoc.get("content");
//			}
//			System.out.println("doc:" + scoreDoc.doc + "    score:" + score + "   id:" + id + "   title:" + title + "    content:" + content);
		}
		logs.append(list.size()).append("|").append(System.currentTimeMillis()-start) ;
	    log.debug(logs) ;
		return list ;
	}

	/**
	 * 
	 * @param keyword
	 * @param trem
	 * @param T
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> search(String keyword,String trem,Class<?> T,int limit)
			throws ParseException, IOException, InvalidTokenOffsetsException, InstantiationException, IllegalAccessException{
		long start = System.currentTimeMillis() ;
		StringBuffer logs = new StringBuffer(this.getClass().getName()).append("|search|") ;
		logs.append(keyword).append(",").append(trem).append(",").append(limit).append("|") ;
		
		QueryParser parser = new QueryParser(Config.VERSION, trem, analyzer);
		Query query = parser.parse(keyword);
		this.setHightlighter(query) ;
		
		IndexSearcher isearcher = this.getIndexSearcher() ;
		TopDocs topDocs = isearcher.search(query, limit) ;
		ScoreDoc[] hits = topDocs.scoreDocs;
		
		List<T> list = new ArrayList<T>() ;
		for(ScoreDoc scoreDoc : hits){
			Document hitDoc = isearcher.doc(scoreDoc.doc);
			list.add((T) this.doc2Object(hitDoc, T)) ;
		}
		
		logs.append(list.size()).append("|").append(System.currentTimeMillis()-start) ;
	    log.debug(logs) ;
		return list ;
		
	}
	
	/**
	 * 
	 * @param query
	 * @param T
	 * @param limit
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> search(Query query,Class<?> T,int limit)
			throws ParseException, IOException, InvalidTokenOffsetsException, InstantiationException, IllegalAccessException{
		long start = System.currentTimeMillis() ;
		StringBuffer logs = new StringBuffer(this.getClass().getName()).append("|search|") ;
		logs.append(query.toString()).append("|") ;

		this.setHightlighter(query) ;
		
		IndexSearcher isearcher = this.getIndexSearcher() ;
		TopDocs topDocs = isearcher.search(query, limit) ;
		ScoreDoc[] hits = topDocs.scoreDocs;
		
		List<T> list = new ArrayList<T>() ;
		for(ScoreDoc scoreDoc : hits){
			Document hitDoc = isearcher.doc(scoreDoc.doc);
			list.add((T) this.doc2Object(hitDoc, T)) ;
		}
		
		logs.append(list.size()).append("|").append(System.currentTimeMillis()-start) ;
	    log.debug(logs) ;
		return list ;
	}
	
	/**
	 * document to object
	 * @param doc
	 * @param T
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	protected <T> T doc2Object(Document doc, Class<?> T)
			throws InstantiationException, IllegalAccessException{
		T o = (T) T.newInstance();
		this.doc2Object2(doc, o, T) ;
		return o ;
	}
	
	/**
	 * document to object
	 * @param doc
	 * @param bean
	 * @param clas
	 */
	@SuppressWarnings("unused")
	private void doc2Object(Document doc,Object bean,Class<?> clas){
		if(!clas.getSuperclass().equals(Object.class)){
			this.doc2Object(doc, bean, clas.getSuperclass()) ;
		}
		Field[] fs = clas.getDeclaredFields();	//获取私有属性。
		for(Field f:fs){
			if(doc.get(f.getName())!=null){
				f.setAccessible(true);//设置私有、保护变量的可以访问权限。
				try {
					
					f.set(bean, doc.get(f.getName())) ;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void doc2Object2(Document doc,Object bean,Class<?> clas){
		if(!clas.getSuperclass().equals(Object.class)){
			this.doc2Object2(doc, bean, clas.getSuperclass()) ;
		}

		HashMap<String,Field> map = new HashMap<String,Field>() ;
		Field[] fs = clas.getDeclaredFields();	//获取私有属性。
		for(Field f:fs){
			String name = f.getName() ;
			f.setAccessible(true);//设置私有、保护变量的可以访问权限。
			map.put(name, f) ;
		}
		List<Fieldable> fields = doc.getFields() ;
		for(Fieldable fd:fields){
			String name = fd.name() ;
			if(!map.containsKey(name)){
				continue ;
			}
			Field f = map.get(name) ;
			try {
				String value = doc.get(name) ;
				Class<?> type = f.getType() ;
				//类型匹配
				if(type.toString().equals("int")){
					f.set(bean, Integer.parseInt(value)) ;
				} else if(type.toString().equals("class java.lang.String")){
					//针对字符串字段，高亮显示
					if(hightline){
						try {
							String hvalue = highlighter.getBestFragment(analyzer, name, value);
							if(hvalue!=null && hvalue.trim().length()>0){
								value = hvalue ;
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InvalidTokenOffsetsException e) {
							e.printStackTrace();
						}
					}
					f.set(bean, value) ;
				} else {
					f.set(bean, value) ;//对象赋值
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public boolean isHightline() {
		return hightline;
	}
	public void setHightline(boolean hightline) {
		this.hightline = hightline;
	}
	private void setHightlighter(Query query){
		if(!hightline){
			return ;
		}
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		Scorer scorer = new QueryScorer(query);
		highlighter = new Highlighter(formatter, scorer);
	}
	
	
	
}
