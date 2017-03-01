package co.bugu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;

/**
 * Created by user on 2017/3/1.
 */
public class Search {
    //索引的存放地址，文件的存放文件夹
    public static String indexDir = "/lucene/index";

    public static String dataDir = "/lucene/data";
    public static void main(String[] atgs) throws IOException {
        Search search = new Search();
        //索引文件
        search.writeIndex();
        //搜索文件名字里面有”红“的文件，并打印出文件名
        search.search("红", "content");
        search.search("2", "name");
    }

    public void writeIndex() throws IOException {
        File indexFile = new File(indexDir);
        File dataFile = new File(dataDir);
        if(!indexFile.exists()){
            indexFile.mkdirs();
        }
        if(!dataFile.exists()){
            dataFile.mkdirs();
        }

        //使用自带的二元分词
        Analyzer analyzer = new CJKAnalyzer();
        //创建放在磁盘上的索引文件
        Directory directory = FSDirectory.open(indexFile.toPath());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        File[] fileList = dataFile.listFiles();
        if(fileList == null){
            return ;
        }
        int fileNum = fileList.length;
        //把每个文件都转化为document
        for(int i = 0; i< fileNum; i++){
            if(fileList[i].getName().startsWith(".")){
                continue;
            }
            Document document = new Document();
            document.add(new Field("name", fileList[i].getName(), TextField.TYPE_STORED));
            document.add(new Field("content", getFileContent(fileList[i]), TextField.TYPE_STORED));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }

    /**
     * 搜索文章里面的内容
     * para key[关键字] target[可以指定要查询的字段]
     * @param key
     * @param target
     */
    public void search(String key, String target) throws IOException {
        File indexFile = new File(indexDir);
        //获取索引存放的文件夹
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(indexFile.toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        //封装查询的内容
        Term term =  new Term(target, key);
        Query termQuery = new TermQuery(term);
        TopDocs topDocs = indexSearcher.search(termQuery, 1000);
        for(ScoreDoc scoreDoc: topDocs.scoreDocs){
            //输出文件的名字
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get(target));
        }
    }

    /**
     * 获取文档内容
     * @param file
     * @return
     */
    private String getFileContent(File file) throws IOException {
        BufferedReader reader = null;
        String result = "";
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
        String tempString = null;
        while((tempString = reader.readLine()) != null) {
            result += (tempString + "\n");
        }
        reader.close();
        return result;
    }

}
