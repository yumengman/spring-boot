package com.example.demoTestThread;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/9/4.
 */
public class TestThreadPool extends ThreadPoolExecutor {
    /**
     * corePoolSize         主线程数
     * maximumPoolSize      最大线程数
     * keepAliveTime        空闲时保持多久停掉
     * unit                 时间单位
     * workQueue            一个阻塞队列，用来存储等待执行的任务
     * */
    public TestThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                          TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        TreeSet set = new TreeSet<>();
        ArrayList list = new ArrayList<>();
        LinkedList linkedList = new LinkedList();
        LinkedHashSet linkHashSet = new LinkedHashSet<>();
        HashMap hashMap = new HashMap();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("abcdefg" + (1<<4));
            }
        });

        executorService.shutdown();
    }
}
