package brytskyi.week5._1blockingQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by alexandr on 01.11.16.
 */
public class MyBlockingQueue<T> implements BlockingQueue<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private volatile Queue<T> queue;
    private int capacity = DEFAULT_CAPACITY;
    private Lock writeLock;
    private Lock removeLock;
    private Condition addCondition;
    private Condition removeCondition;
    private AtomicInteger size = new AtomicInteger();

    public MyBlockingQueue(boolean fair) {
        queue = new LinkedList<T>();
        writeLock = new ReentrantLock(fair);
        removeLock = new ReentrantLock(fair);
        addCondition = writeLock.newCondition();
        removeCondition = removeLock.newCondition();
    }

    public MyBlockingQueue(int capacity, boolean fair) {
        this.capacity = capacity;
        queue = new LinkedList<T>();
        writeLock = new ReentrantLock(fair);
        removeLock = new ReentrantLock(fair);
        addCondition = writeLock.newCondition();
        removeCondition = removeLock.newCondition();
    }

    @Override
    public boolean add(T t) {
        if (t == null) throw new NullPointerException();
        boolean res = false;
        try {
            writeLock.lockInterruptibly();
            System.out.println("addLockLockedInterruptidly");
            while (size.get() == capacity) {
                if (queue.size() == capacity) {
                    System.out.println(Thread.currentThread().getId() + " thread is going to wait, want to add, size is " + queue.size());
                    addCondition.await();
                    System.out.println(Thread.currentThread().getId() + " thread is await, want to add, size is " + queue.size());

                }
            }
            res = queue.add(t);
            size.incrementAndGet();
            System.out.println(Thread.currentThread().getId() + " thread has added, size is " + queue.size());

//            if (size.get() + 1 < capacity) addCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println("addLockUnLocked");
        }

        if (size.get() == capacity) {
            removeLock.lock();
            System.out.println("removeLock locked");
            try {
                removeCondition.signal();
                System.out.println("signal to removers");
            } finally {
                removeLock.unlock();
            }
        }
        return res;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        T res = null;
        try {
            removeLock.lockInterruptibly();
            System.out.println("removeLockLockedInterruptidly");
            while (size.get() == 0) {
                System.out.println(Thread.currentThread().getId() + " thread is going to wait, want to remove, size is " + queue.size());
                removeCondition.await();
                System.out.println(Thread.currentThread().getId() + " thread is await, want to remove, size is " + queue.size());
            }
            res = queue.remove();
            size.decrementAndGet();
            System.out.println(Thread.currentThread().getId() + " thread has removed, size is " + queue.size());

//            if (size.get() - 1 > 0) removeCondition.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            removeLock.unlock();
            System.out.println("removeLockUnlocked");
        }

        if (size.get() == 0) {
            writeLock.lock();
            System.out.println("writeLock locked");
            try {
                addCondition.signal();
                System.out.println("signal to adders");
            } finally {
                writeLock.unlock();
            }
        }

        return res;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void put(T t) throws InterruptedException {

    }

    @Override
    public boolean offer(T t, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public T take() throws InterruptedException {
        return null;
    }

    @Override
    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public int drainTo(Collection<? super T> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super T> c, int maxElements) {
        return 0;
    }

    @Override
    public String toString() {
        return "MyBlockingQueue{" +
                "queue=" + queue +
                '}';
    }
}
