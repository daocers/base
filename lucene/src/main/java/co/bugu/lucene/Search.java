package co.bugu.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.file.FileSystems;

/**
 * *  创建Directory
 创建IndexReader
 根据IndexReader创建IndexSearch
 创建搜索的Query
 根据searcher搜索并且返回TopDocs
 根据TopDocs获取ScoreDoc对象
 根据searcher和ScoreDoc对象获取具体的Document对象
 根据Document对象获取需要的值
 * Created by daocers on 2016/8/23.
 */
public class Search {

    public void search(String keyword){
        DirectoryReader directoryReader = null;
        try{
            //1 创建directory
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("C:/Users/daocers/Desktop/LuceneDemo/data/index"));
            //2 创建indexReader
            directoryReader = DirectoryReader.open(directory);
            // 3根据indexReader创建indexSearch
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            // 4 创建搜索的query
            Analyzer analyzer = new StandardAnalyzer();
            //创建parser来确定要搜索的文件的内容，第一个参数为搜索的域
            QueryParser queryParser = new QueryParser("content", analyzer);
            //创建query表示搜索域为content包含uima 的文档
            Query query = queryParser.parse(keyword);

            //5 根据searcher搜索并返回topdocs
            TopDocs topDocs = indexSearcher.search(query, 10);
            System.out.println("查找到的文档总共有：" + topDocs.totalHits);

            //6 根据topdocs获取scoredoc对象
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for(ScoreDoc scoreDoc: scoreDocs){
                //7 根据search和scoredoc对象获取具体的document对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                System.out.println(document.get("filename") + "  " + document.get("filepath"));
                System.out.println(document.get("content"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(directoryReader != null){
                    directoryReader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}

