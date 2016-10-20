package brytskyi.week3.downloaders.urlDownloader;


import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UrlDownloader {

    private int persents = 0;
    private long allreaded = 0;

    /*downloads the data from url in file*/
    public boolean download(String url, String filePath) throws IOException {
        URL urla = URI.create(url).toURL();
        long available = 0;
        URLConnection connection = urla.openConnection();
        available = connection.getContentLengthLong();
        try (InputStream is = new BufferedInputStream(connection.getInputStream());
             OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buf = new byte[8196];
            int readed;
            while ((readed = is.read(buf)) > 0) {
                os.write(buf, 0, readed);
                allreaded += readed;
                persents = (int) (((double) allreaded / (double) available) * 100);
            }
        }
        return true;
    }

    public int getPersents() {
        return persents;
    }

}
