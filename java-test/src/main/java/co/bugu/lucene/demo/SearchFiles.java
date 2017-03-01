package co.bugu.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by user on 2017/3/1.
 */
public class SearchFiles {
    public static void main(String[] args) throws IOException, ParseException {
        String index = "/lucene/index";
        String field = "content";
        String queries = null;
        int repeat = 0;
        boolean raw = false;
        String queryStr = "红";
        int hitsPerPage = 10;

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
        IndexSearcher searcher = new IndexSearcher(reader)；
        Analyzer analyzer = new StandardAnalyzer();
        BufferedReader in = null;
        if(queries != null){

            in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
        }else{
            in = new BufferedReader(new InputStreamReader(System.in,  StandardCharsets.UTF_8));
        }

        QueryParser parser = new QueryParser(field, analyzer);
        while(true){
            if(queries == null && queryStr == null){
                System.out.println("enter query;　");
            }

            Query query = parser.parse(queryStr);
            System.out.println("searching for :" + query.toString(field));

        }
    }
}
