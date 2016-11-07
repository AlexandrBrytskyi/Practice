package brytskyi.week5._1blockingQueue;

import java.util.concurrent.*;

/**
 * Created by alexandr on 01.11.16.
 */
public class TestMyBlockingQueue {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(40);
        BlockingQueue<Integer> queue = new MyBlockingQueue<Integer>(10, false);
//        LinkedBlockingQueue;
        for (int i = 0; i < 20; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5000; j++) {
                        queue.add(j);
                        System.out.println("added " +j);
                    }
                }
            });
            service.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5000; j++) {
                        queue.remove();
                        System.out.println("removed " +j);
                    }
                }
            });
        }
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
