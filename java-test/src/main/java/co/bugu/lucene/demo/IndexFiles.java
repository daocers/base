package co.bugu.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by user on 2017/3/1.
 */
public class IndexFiles {
    public static void main(String[] args) {
        String usage = "java org.apache.lucene.demo.IndexFiles\n" +
                " [-index INDEX_PATH] [-docs DOCS_PATH] [-update]\n\n" +
                "This indexes the documents in DOCS_PATH, creating a Lucene index" +
                "in INDEX_PATH that can be searched with SearchFiles";
        String indexPath = "/lucene/index";
        String docsPath = "/lucene/data";
        boolean create = false;

        final Path docDir = Paths.get(docsPath);
        if (!Files.isReadable(docDir)) {
            System.out.println("document directory　" + docDir.toAbsolutePath()
                    + " does not exist or is not readable, please check the path");
            System.exit(1);
        }
        long begin = System.currentTimeMillis();
        try {
            System.out.println("indexing to directory " + indexPath + " ...");
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            if (create) {
                config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            } else {
                config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }
            IndexWriter writer = new IndexWriter(directory, config);
            indexDocs(writer, docDir);

            writer.close();
            long end = System.currentTimeMillis();
            System.out.println("耗费： " + (end - begin) + "毫秒");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    indexDoc(writer, file, attrs.lastAccessTime().toMillis());
                    return FileVisitResult.CONTINUE;
                }

            });
        } else {
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }

    static void indexDoc(IndexWriter writer, Path file, long lastModified) {
        try (InputStream stream = Files.newInputStream(file)) {
            Document document = new Document();
            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            document.add(pathField);
            document.add(new LongPoint("modified", lastModified));
            document.add(new TextField("content", new BufferedReader(new InputStreamReader(stream, "gbk"))));

            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                System.out.println("adding " + file);
                writer.addDocument(document);
            } else {
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
