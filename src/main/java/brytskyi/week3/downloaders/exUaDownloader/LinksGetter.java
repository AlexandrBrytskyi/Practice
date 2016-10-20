package brytskyi.week3.downloaders.exUaDownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexandr on 16.10.16.
 */
public class LinksGetter {

    public Set<UrlTittleWrapper> getURLs(URL page) throws IOException {
        Document doc = Jsoup.parse(page, 10000);
        Set<UrlTittleWrapper> urls = new HashSet<>();
        for (Element o : doc.getElementsByTag("a")) {
            if (o.attr("href") != null && o.attr("href").contains("/get/")) {
                URL url = URI.create("http://www.ex.ua" + o.attr("href")).toURL();
                String name = o.attr("title");
                urls.add(new UrlTittleWrapper(url, name));
            }
        }
        return urls;
    }
}
