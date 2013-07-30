package my.lucene.search;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

public class SearchIndex {

	private static Hashtable<String,IndexReader> readertable = new Hashtable<String,IndexReader>() ;	//redertables
	private String key ;	//readertable  key
	private File indexdir ;	//index file path
	
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
//        try {
//        	long start = System.currentTimeMillis() ;
//        	
//            _RAM_DIR = new RAMDirectory();
//            FSDirectory fsdir = FSDirectory.open(this.index_dir);
//            Directory dirs[] = {fsdir};
//            IndexWriter writer = new IndexWriter(_RAM_DIR ,Config.analyzer ,true);
//            writer.addIndexes(dirs);
//            writer.close();
//            Date end = new Date();
//            log.error(end.getTime() - start.getTime()+" total milliseconds");
//        } catch (Exception e) {
//        	log.error(e.getMessage());
//        }
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
	 * @param name
	 * @return
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 */
	protected IndexSearcher getIndexSearcher() 
			throws CorruptIndexException, IOException {
		if(!readertable.keySet().contains(key)){
			this.getIndexReader() ;
		}

		IndexReader reader = readertable.get(key) ;
	    IndexSearcher searcher = new IndexSearcher(reader);
	    return searcher ;
	}
	
}
