package co.bugu.lucene;

import java.io.IOException;

/**
 * Created by daocers on 2016/8/23.
 */
public class HighLightSearch{
    public static void main(String[] args) throws IOException {
        Index newIndex = new Index();
        newIndex.index();
        Search newSearch = new Search();
        newSearch.search("uima");
    }
}
