package brytskyi.week5._2fileSearchWithQueue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**/
public class FileSearcher {

    /*blocking!!*/
    private volatile BlockingQueue<File> folderQueue;
    private Lock searchingLock;

    public FileSearcher() {
        searchingLock = new ReentrantLock();
        folderQueue = new LinkedBlockingQueue<>();
    }

    public List<File> search(String destination, File beginPoint, int threads) {
        searchingLock.lock();
        List<File> result = new LinkedList<>();
        List<Future<List<File>>> threadsRes = new LinkedList<>();
        try {
            if (beginPoint.isDirectory()) {
                folderQueue.add(beginPoint);
            } else {
                if (beginPoint.getName().contains(destination)) result.add(beginPoint);
            }


            ExecutorService service = Executors.newFixedThreadPool(threads);
            if (!folderQueue.isEmpty()) {
                while (!service.isShutdown()) {
                    File file = folderQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (null != file) threadsRes.add(service.submit(new FolderWatcher(file, destination)));
                    if (lookWeatherDone(threadsRes, folderQueue)) {
                        service.shutdown();
                        while (!service.isTerminated()) {
                            service.awaitTermination(1, TimeUnit.DAYS);
                        }
                    }
                }

                for (Future<List<File>> threadsRe : threadsRes) {
                    result.addAll(threadsRe.get());
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            searchingLock.unlock();
        }
        return result;
    }

    private boolean lookWeatherDone(List<Future<List<File>>> threadsRes, BlockingQueue<File> folderQueue) {
        for (Future<List<File>> threadsRe : threadsRes) {
            if (!threadsRe.isDone()) return false;
        }
//        System.out.println("going to return true check if empty " + folderQueue.isEmpty());
        return folderQueue.isEmpty();
    }

    private class FolderWatcher implements Callable<List<File>> {

        private File beginPoint;
        private String destination;

        public FolderWatcher(File beginPoint, String destination) {
            this.beginPoint = beginPoint;
            this.destination = destination;
        }

        @Override
        public List<File> call() throws Exception {
//            System.out.println(beginPoint);
            List<File> res = new LinkedList<>();
            if (beginPoint.isDirectory()) {
                File[] children = beginPoint.listFiles();
                for (File child : children) {
                    if (child.isDirectory()) {
                        folderQueue.add(child);
//                        System.out.println("added directory "+ child);
                    } else {
                        if (child.getName().contains(destination)) res.add(child);
                    }
                }
            }
            if (beginPoint.getName().contains(destination)) res.add(beginPoint);
//            for (File re : res) {
//                System.out.println(re.getPath());
//            }
            return res;
        }
    }

}
