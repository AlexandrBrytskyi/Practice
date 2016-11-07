package brytskyi.week5._04multithreadDownloader.ex_ua_parser.controller;


import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class UrlDownloader implements Runnable {

    private int persents = 0;
    private long allreaded = 0;
    String url;
    String filePath;
    private JProgressBar bar;

    public UrlDownloader(String url, String filePath, JProgressBar bar) {
        this.url = url;
        this.filePath = filePath;
        this.bar = bar;
    }

    /*downloads the data from url in file*/
    public boolean download() throws IOException {
        URL urla = URI.create(url).toURL();
        long available = 0;
        URLConnection connection = urla.openConnection();
        available = connection.getContentLengthLong();
        try (InputStream is = new BufferedInputStream(connection.getInputStream());
             OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buf = new byte[8196];
            int readed;
            long passed = System.currentTimeMillis();
            while ((readed = is.read(buf)) > 0) {
                os.write(buf, 0, readed);
                allreaded += readed;
                persents = (int) (((double) allreaded / (double) available) * 100);
                if (null != bar) {
                    passed = System.currentTimeMillis() - passed;
                    if (passed >= 1000) {
                        bar.setValue(getPersents());
                    }
                }
            }
        }
        return true;
    }

    public int getPersents() {
        return persents;
    }

    @Override
    public void run() {
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
