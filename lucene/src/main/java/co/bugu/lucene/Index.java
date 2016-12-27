package co.bugu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;


/**
 * Created by daocers on 2016/8/23.
 */
public class Index {
    public void index() throws IOException {
        IndexWriter indexWriter = null;
        try{
            //1 创建Directory
            Directory directory = FSDirectory.open(FileSystems.getDefault()
                    .getPath("C:/Users/daocers/Desktop/LuceneDemo/data/index"));

            // 2 创建indexWriter
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteAll();//清除以前的index

            //要搜索的file路径
            File dFile = new File("c:/users/daocers/Desktop/LuceneDemo/data/data");
            File[] files = dFile.listFiles();
            for(File file: files){
                //3 创建document对象
                Document document = new Document();
                //4 为document添加field
                //第三个参数是FieldType，但是定义在TextField中做为静态变量。
//                document.add(new Field("content", new FileReader(file), TextField.TYPE_NOT_STORED));
                BufferedReader reader = new BufferedReader(new FileReader(file));
                document.add(new Field("content", reader.readLine(), TextField.TYPE_STORED));
                document.add(new Field("filename", file.getName(), TextField.TYPE_STORED));
                document.add(new Field("filepath", file.getAbsolutePath(), TextField.TYPE_STORED));

                //5 通过indexwriter 添加文档到索引中
                indexWriter.addDocument(document);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(indexWriter != null){
                    indexWriter.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
