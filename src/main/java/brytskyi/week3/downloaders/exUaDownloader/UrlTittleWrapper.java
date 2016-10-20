package brytskyi.week3.downloaders.exUaDownloader;

import java.net.URL;

/**
 * Created by alexandr on 16.10.16.
 */
public class UrlTittleWrapper {
    private URL url;
    private String tittle;

    public UrlTittleWrapper(URL url, String tittle) {
        this.url = url;
        this.tittle = tittle;
    }

    public URL getUrl() {
        return url;
    }

    public String getTittle() {
        return tittle;
    }

    @Override
    public String toString() {
        return "url=" + url +
                ", tittle='" + tittle + "";
    }
}
