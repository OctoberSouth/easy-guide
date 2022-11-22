package com.lp.easy.basics.thread;


import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        completableFuture();
    }

    /**
     * 线程测试
     */
    public static void thread() {
        System.out.println("方法开始执行");
        // 第一种方式，继承Thread
        //Thread thread = new Thread01();
        // thread.start();
        //第二种方式，实现Runnable
        //Runnable runnable = new RunnableO1();
        //new Thread(runnable).start();
        //第三种方式，Callable ->FutureTask 带返回结果
//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
//        new Thread(futureTask).start();
//        try {
//            Integer i = futureTask.get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        //第四种方式，用线程池
        /**
         * 1，核心线程数 线程池创建就存在
         * 2，最大线程数
         * 3，过期时间，线程空闲超过一定时间后就会销毁
         * 4，过期时间单位
         * 5，线程队列，只要有空闲线程就会执行
         * 6，线程创建工厂
         * 7，拒绝策略 常见的ThreadPoolExecutor 丢弃老的，DiscardPolicy 丢弃新的 ,AbortPolicy 拒绝执行并抛出异常，CallerRunsPolicy 会以普通方法执行
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 20L,
                TimeUnit.SECONDS, new LinkedBlockingQueue(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Thread01());
        System.out.println("方法执行结束");
    }

    /**
     * CompletableFuture
     */
    public static void completableFuture() {
        System.out.println("方法开始执行");
        //无返回值
//        CompletableFuture.runAsync(new RunnableO1(), executorService);
        //有返回值
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier01(), executorService);
        try {
            Integer i = future.get();
            System.out.println(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("方法执行结束");
    }


}

class Thread01 extends Thread {

    @Override
    public void run() {
        System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
        int i = 10 / 5;
        System.out.println("线程运行结果：" + i);
    }
}

class RunnableO1 implements Runnable {

    @Override
    public void run() {
        System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
        int i = 10 / 5;
        System.out.println("线程运行结果：" + i);
    }
}

class Callable01 implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
        int i = 10 / 5;
        System.out.println("线程运行结果：" + i);
        return i;
    }
}

class Supplier01 implements Supplier<Integer> {

    @Override
    public Integer get() {
        System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
        int i = 10 / 5;
        System.out.println("线程运行结果：" + i);
        return i;
    }
}
