package my.applet.swing.readIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class SearchIdx {
	private static final Logger log = Logger.getLogger(SearchIdx.class);
	private static RAMDirectory _RAM_DIR = null;
	
	private IndexFather index ;
	public SearchIdx(IndexFather conf){
		index = conf ;
	}
//	搜索
	public List<Object> queryIndex(String key){
		List<Object> list = new ArrayList<Object>();
		Hits hits = null ;
		Searcher searcher = null ;
		try {
			Sort sort = index.getSort() ;
			searcher = getIndexSearcher() ;
			Query query = getQuery(key);
			log.info("Query:"+query.toString());
			if(sort!=null){
				hits = searcher.search(query,sort);
			} else {
				hits = searcher.search(query);
			}
			
			// 提取结果并封装
		    for (int i = 0; i < hits.length(); i++) {
		    	Document doc = hits.doc(i);
		    	list.add(index.DocumentToObject(doc)) ;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list ;
	}
	
    /**
     * 返回检索对象
     * @throws IOException 
     */
	private Searcher getIndexSearcher() throws IOException {
        Searcher searcher = null;
        if (index.INDEX_IN_MEMORY){	// 从内存索引检索
        	if(_RAM_DIR==null){
        		indexMemoryAll() ;
        	}
        	searcher = new IndexSearcher(_RAM_DIR);
        } else {	// 从硬盘索引检索
            searcher = new IndexSearcher(index.INDEX_DIR);
        }
        return searcher;
    }
    
    /**
     * 做内存索引
     */
	public void indexMemoryAll() {
        try {
            Date start = new Date();
            _RAM_DIR = new RAMDirectory();
            FSDirectory fsdir = FSDirectory.getDirectory(index.INDEX_DIR, false);
            Directory dirs[] = {fsdir};
            IndexWriter writer = new IndexWriter(_RAM_DIR,index.getAnalyzer(),true);
            writer.addIndexes(dirs);
            writer.close();
            Date end = new Date();
            log.error(end.getTime() - start.getTime()+" total milliseconds");
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
    }
    
	/**
	 * 生成检索语句
	 * @param keyword
	 * @return
	 * @throws ParseException
	 */
	private Query getQuery(String keyword)
    throws ParseException {
        StringTokenizer st = new StringTokenizer(keyword);
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens()){
             String token = st.nextToken().toLowerCase();
             sb.append("+");
             sb.append(token);
             sb.append(" ");
         }
//      搜索屏蔽词
//		String[] s = {"a","an","and","are","as","at","be","but","by","for","if","in","into","is","it","no","not","of","on","or","s","such","t","that","the","their","then","there","these","they","this","to","was","will","with"};
		String[] s = {"a","an","and","are","as","at","be","but","by","for","if","in","into","is","it","yes","not","of","on","or","s","such","t","that","the","their","then","there","these","they","this","to","was","will","with"};
//		StopAnalyzer sd = new StopAnalyzer(s);
//        return QueryParser.parse(sb.toString(), Const.INDEX_SEARCH_KEY,
//        		new StandardAnalyzer(s));
        return QueryParser.parse(sb.toString(), index.INDEX_SEARCH_KEY,
        		index.getAnalyzer());
     }
}
