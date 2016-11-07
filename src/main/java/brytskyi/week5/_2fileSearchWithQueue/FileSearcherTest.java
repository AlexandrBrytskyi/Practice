package brytskyi.week5._2fileSearchWithQueue;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by alexandr on 02.11.16.
 */
public class FileSearcherTest {

    public static void main(String[] args) {
        File root = new File("/media/alexandr/4A322674322664E3");
//        File root = File.listRoots()[0];
        FileSearcher searcher = new FileSearcher();
        int threads = 8;
        long time = System.currentTimeMillis();
        List<File> res = searcher.search(".java", root, threads);
        time = System.currentTimeMillis() - time;
        System.out.println("results");
        for (File file : res) {
            System.out.println(file.getPath());
        }
        System.out.println("time " + time + " for " + threads + " threads");
    }
}
