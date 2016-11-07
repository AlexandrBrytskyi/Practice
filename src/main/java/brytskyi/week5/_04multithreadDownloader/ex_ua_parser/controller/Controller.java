package brytskyi.week5._04multithreadDownloader.ex_ua_parser.controller;


import brytskyi.week3.downloaders.exUaDownloader.UrlTittleWrapper;
import brytskyi.week5._04multithreadDownloader.ex_ua_parser.LinksFrame;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {

    public static final int THREADS_TO_PARSE_LINKS = 10;
    public static final int LINKS_TO_PARSE_QUEUE_CAPACITY = 1000;
    private volatile BlockingQueue<URL> linksToParse;
    private volatile ConcurrentSkipListSet<URL> alreadyVisitedLinks;
    private Set<Future<Set<UrlTittleWrapper>>> parsingFuture;
    public static final String PATH_FOR_DOWNLOADS = "temp/downloade/";
    private LinksFrame linksFrame;
    private ExecutorService parsingService;


    public Controller(String startPoint) throws MalformedURLException {
        linksToParse = new LinkedBlockingQueue<>(LINKS_TO_PARSE_QUEUE_CAPACITY);
        linksToParse.offer(URI.create(startPoint).toURL());
        alreadyVisitedLinks = new ConcurrentSkipListSet<>(new Comparator<URL>() {
            @Override
            public int compare(URL o1, URL o2) {
                return o1.getPath().compareTo(o2.getPath());
            }
        });
        parsingFuture = new HashSet<>();
        linksFrame = new LinksFrame(this);
        runLinksParser();
    }

    private void runLinksParser() {
        parsingService = Executors.newFixedThreadPool(THREADS_TO_PARSE_LINKS);

        try {
            while (!parsingService.isShutdown()) {
                URL toParse = linksToParse.poll(3000, TimeUnit.MILLISECONDS);
                if (null != toParse && !alreadyVisitedLinks.contains(toParse)) {
                    parsingFuture.add(parsingService.submit(new PageWatcher(toParse)));
                }
                if (watchWetherTasksCompletedAndSentLinksToUI()) {
                    parsingService.shutdown();
                    while (!parsingService.isTerminated()) {
                        parsingService.awaitTermination(1, TimeUnit.DAYS);
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean watchWetherTasksCompletedAndSentLinksToUI() {
        boolean res = false;
        Iterator<Future<Set<UrlTittleWrapper>>> iterator = parsingFuture.iterator();
        while (iterator.hasNext()) {
            Future<Set<UrlTittleWrapper>> fut = iterator.next();
            if (!fut.isDone()) {
                res = false;
            } else {
                try {
                    Set<UrlTittleWrapper> set = fut.get();
                    if (set != null)
                        linksFrame.addLinks(set.stream().filter(x -> x.getTittle() != null && !x.getTittle().isEmpty()).collect(Collectors.toList()));
                    iterator.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println("going to return true check if empty " + folderQueue.isEmpty());
        return res == true ? linksToParse.isEmpty() : res;
    }


    public Set<UrlTittleWrapper> getURLs(URL page) throws IOException {
//        System.out.println("parsing " + page);
        alreadyVisitedLinks.add(page);
        Document doc;
        try {
            doc = Jsoup.parse(page, 10000);
        } catch (Exception e) {
            return null;
        }
        Set<UrlTittleWrapper> urls = new HashSet<>();
        for (Element o : doc.getElementsByTag("a")) {
            String href = o.attr("href");
            if (href != null) {
                if (href.contains("/get/")) {
                    URL url = URI.create("http://www.ex.ua" + href).toURL();
                    String name = o.attr("title");
                    urls.add(new UrlTittleWrapper(url, name));
                } else {
                    URL link = null;
                    if (href.startsWith("http")) {
                        if (href.startsWith("http://www.ex.ua")) {
                            link = URI.create(href).toURL();
                        }
                    } else {
                        if (!href.contains("javascript") && !href.contains("search?s="))
                            link = URI.create("http://www.ex.ua" + href).toURL();
                    }
                    if (null != link) linksToParse.offer(link);
                }
            }
        }
        return urls;
    }


    private class PageWatcher implements Callable<Set<UrlTittleWrapper>> {

        private URL page;

        public PageWatcher(URL page) {
            this.page = page;
        }

        @Override
        public Set<UrlTittleWrapper> call() throws Exception {
            return getURLs(page);
        }

    }

    public void download(UrlTittleWrapper url, JProgressBar progressBar) {

        Thread downloadThread = new Thread(new UrlDownloader(url.getUrl().toString(), PATH_FOR_DOWNLOADS + url.getTittle(), progressBar));
        downloadThread.start();
    }


}
