package my.lucene.search;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import my.config.Config;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class SearchIndex {
	private static Logger log = Logger.getLogger(SearchIndex.class.getName());

	private static Hashtable<String,IndexReader> readertable = new Hashtable<String,IndexReader>() ;	//redertables
	private String key ;	//readertable  key
	private File indexdir ;	//index file path
	
	protected Analyzer analyzer = Config.analyzer ;	//分词器
	public SearchIndex(File indexdir, boolean ram){
		this.key = indexdir.getName() ;
		this.indexdir = indexdir ;
		if(readertable==null || !readertable.keySet().contains(key)){
			if(ram){
				//内存索引
				this.getMemoryIndexReader() ;
			} else {
				try {
					//文件索引
					this.getIndexReader() ;
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public SearchIndex(File indexdir){
		this.key = indexdir.getName() ;
		this.indexdir = indexdir ;
		if(readertable==null || !readertable.keySet().contains(key)){
			try {
				//文件索引
				this.getIndexReader() ;
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    /**
     * 做内存索引
     */
	private IndexReader getMemoryIndexReader() {
        try {
//        	long start = System.currentTimeMillis() ;
        	
            RAMDirectory _RAM_DIR = new RAMDirectory();
            FSDirectory directory = FSDirectory.open(indexdir);
            IndexReader reader = IndexReader.open(directory);
            IndexWriter writer = new IndexWriter(_RAM_DIR ,analyzer ,IndexWriter.MaxFieldLength.UNLIMITED);
            writer.addIndexes(reader);
            writer.close();
            
            reader = IndexReader.open(_RAM_DIR);
    		readertable.put(key, reader) ;
    	    return reader ;
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
		return null ;
    }
	
	/**
	 * index reader
	 * @return
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	private IndexReader getIndexReader() 
			throws CorruptIndexException, IOException{
		FSDirectory directory = FSDirectory.open(indexdir);
		IndexReader reader = IndexReader.open(directory);
		readertable.put(key, reader) ;
	    return reader ;
	}
	
	/**
	 * get indexsearcher
	 * @return
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	protected IndexSearcher getIndexSearcher() 
			throws CorruptIndexException, IOException {
		long start = System.currentTimeMillis() ;
		StringBuffer logs = new StringBuffer(this.getClass().getName()).append("|getIndexSearcher|") ;
		logs.append(key).append("|") ;
		
		if(!readertable.keySet().contains(key)){
			logs.append(1) ;
			this.getIndexReader() ;
		} else {
			logs.append(0) ;
		}

		IndexReader reader = readertable.get(key) ;
	    IndexSearcher searcher = new IndexSearcher(reader);
	    
	    logs.append("|").append(System.currentTimeMillis()-start) ;
	    log.debug(logs) ;
	    return searcher ;
	}

	public File getIndexdir() {
		return indexdir;
	}

	public void setIndexdir(File indexdir) {
		this.indexdir = indexdir;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
}
