package my.lucene.index;

import java.io.File;
import java.io.IOException;
import java.util.List;

import my.config.Config;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexFactory {
	
	private File dataFile ;
	public IndexFactory(File dataFile){
		this.dataFile = dataFile ;
	}

	public <T> void bulidIndex(List<T> datas){
        IndexWriter writer = null;
        try {

        	writer = this.getIndexWriter() ;
            for(T t:datas){
            	Document doc = this.getDocument(t) ;
            	writer.addDocument(doc) ;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
            	writer.optimize() ;
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 按条件更新索引
	 * @param obj
	 * @param name
	 * @param value
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
    public boolean update(Object obj,String name,String value)
    		throws IllegalArgumentException, IOException, IllegalAccessException {
        Term term = new Term(name, value);
        return this.update(term, obj) ;
    }
    public boolean update(Term term, Object obj) 
    		throws IOException, IllegalArgumentException, IllegalAccessException {
    	IndexWriter writer = this.getIndexWriter() ;
    	Document doc = this.getDocument(obj) ;
    	writer.updateDocument(term, doc) ;
    	writer.close() ;
    	return true ;
    }
    
    /**
     * 删除索引
     * @param id
     * @throws IOException 
     */
    public boolean delete(Integer id) throws IOException {
    	Term term = new Term("id", String.valueOf(id));
        return this.delete(term) ;
    }
    public boolean delete(Term term) throws IOException{
    	IndexWriter writer = this.getIndexWriter() ;
        writer.deleteDocuments(term);
        writer.close();
        return true ;
    }
    
    /**
     * 获取indexwriter
     * @return
     * @throws IOException
     */
    private IndexWriter getIndexWriter() throws IOException{
        //创建IndexWriter对象,第一个参数是Directory,第二个是分词器,第三个表示是否是创建,如果为false为在此基础上面修改,第四表示表示分词的最大值，比如说new MaxFieldLength(2)，就表示两个字一分，一般用IndexWriter.MaxFieldLength.LIMITED 
        Directory directory = FSDirectory.open(dataFile) ;
        IndexWriter writer = new IndexWriter(directory, Config.analyzer,true,IndexWriter.MaxFieldLength.UNLIMITED) ;
        return writer ;
    }
    
    /**
     * 对象转document 
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
	protected <T> Document getDocument(T obj) throws IllegalArgumentException, IllegalAccessException{

        Document doc = new Document();
        //Field.Index.NO 表示不索引
        //Field.Index.ANALYZED 表示分词且索引
        //Field.Index.NOT_ANALYZED 表示不分词且索引
        Class<? extends Object> clas = obj.getClass() ;
        java.lang.reflect.Field[] fs = clas.getDeclaredFields();	//获取私有属性。
        StringBuffer buf = new StringBuffer() ;
		for(java.lang.reflect.Field f:fs){
			f.setAccessible(true);
			String name = f.getName() ;
			Object value = f.get(obj) ;
			if(value!=null){
				Fieldable field;
				if(value instanceof String){
					buf.append(value.toString()).append("|") ;
					field = new Field(name, value.toString(), Field.Store.YES, Field.Index.ANALYZED) ;
				} else {
					field = new Field(name, value.toString(), Field.Store.YES, Field.Index.NOT_ANALYZED) ;
				}
				doc.add(field) ;
			}
		}
		//将所有字段拼接成一个全字段搜索字段
		doc.add(new Field(Config.querykey, buf.toString(), Field.Store.YES, Field.Index.ANALYZED)) ;
		return doc ;
	}
	
}
