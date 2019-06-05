import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * corePoolSize:没有任务时，线程池的基本大小，并且只有在工作队列满了的情况下才会创建超出这个数量的线程
     * maximumPoolSize：线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。
     * 提交一个任务时的处理流程：
     * 1、如果线程池的当前大小还没有达到基本大小(poolSize < corePoolSize)，那么就新增加一个线程处理新提交的任务
     * 2、如果当前大小已经达到了基本大小，就将新提交的任务提交到阻塞队列排队，等候处理workQueue.offer(command)
     * 3、如果队列容量已达上限，并且当前大小poolSize没有达到maximumPoolSize，那么就新增线程来处理任务
     * 4、如果队列已满，并且当前线程数目也已经达到上限，那么意味着线程池的处理能力已经达到了极限，此时需要拒绝新增加的任务。至于如何拒绝处理新增的任务，取决于线程池的饱和策略RejectedExecutionHandler。
     */
    private static ThreadPoolExecutor poolExecutor = new MyThreadPool(
            2, 5, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory());

    public static void main(String[] args) {
        MyTask task = new MyTask();
        for (int i = 0; i < 20; i++) {
            poolExecutor.execute(task);
        }
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}