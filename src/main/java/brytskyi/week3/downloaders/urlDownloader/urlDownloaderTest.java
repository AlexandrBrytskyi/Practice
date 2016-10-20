package brytskyi.week3.downloaders.urlDownloader;

import java.io.IOException;

/**
 * Created by alexandr on 15.10.16.
 */
public class urlDownloaderTest {

    public static void main(String[] args) {
        UrlDownloader downloader = new UrlDownloader();

        String url = "http://www.hd-wallpapersdownload.com/script/bulk-upload/desktop-free-images-nature-dowload.jpg";

        String filePath = "temp/image.jpg";
        try {
            downloader.download(url, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
