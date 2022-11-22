package com.lp.easy.basics.thread;


import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws Exception {
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
     * CompletableFuture 用于线程编排
     */
    public static void completableFuture() throws ExecutionException, InterruptedException {
        System.out.println("方法开始执行");

        /**
         * 无返回值
         */
//        CompletableFuture.runAsync(new RunnableO1(), executorService);

        /**
         * 有返回值
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier01(), executorService);
//        try {
//            Integer i = future.get();
//            System.out.println(i);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        /**
         * 常用方法
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//                    System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
//                    int i = 10 / 0;
//                    System.out.println("线程运行结果：" + i);
//                    return i;
//                }, executorService)
//                .whenComplete((res, exception) -> {
//                    //能感知异常，但是无法修改返回数据
//                    System.out.println("方法执行结果：" + res);
//                }).exceptionally(throwable -> {
//                    // 异常处理 并返回值
//                    return 10;
//                });

        /**
         * 方法执行完成后的处理
         */
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
//            int i = 10 / 0;
//            System.out.println("线程运行结果：" + i);
//            return i;
//        }, executorService).handle((res, exception) -> {
//            if (res != null) {
//                return res * 2;
//            }
//            if (exception != null) {
//                return 0;
//            }
//            return 0;
//        });
//        try {
//            System.out.println("执行结果：" + future.get());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        /**
         * 串行化  不能获取到上一步的执行结果
         */
//        CompletableFuture.supplyAsync(() -> {
//                    System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
//                    int i = 10 / 5;
//                    System.out.println("线程运行结果：" + i);
//                    return i;
//                }, executorService)
//                .thenRunAsync(() -> {
//                    System.out.println("线程二");
//                }, executorService);

        /**
         * 获取上一步结果 但是无返回值
         */
//        CompletableFuture.supplyAsync(() -> {
//                    System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
//                    int i = 10 / 5;
//                    System.out.println("线程运行结果：" + i);
//                    return i;
//                }, executorService)
//                .thenAcceptAsync(res -> {
//                    System.out.println("线程二获取到上一个返回值：" + res);
//                }, executorService);

        /**
         *能接受上一步的结果，并且有返回值
         */
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//                    System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
//                    int i = 10 / 5;
//                    System.out.println("线程运行结果：" + i);
//                    return i;
//                }, executorService)
//                .thenApplyAsync(res -> {
//                    return "线程二获取到上一个返回值：" + res;
//                }, executorService);
//        try {
//            System.out.println("执行结果：" + future.get());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        /**
         * 两个任务都完成
         */
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名字：" + Thread.currentThread().getName() + "=======当前线程ID：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("线程运行结果：" + i);
            return i;
        }, executorService);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> "hello", executorService);
        //无返回值
//        future01.runAfterBothAsync(future02, () -> {
//            System.out.println("任务3执行");
//        }, executorService);
        // 可以得到两个返回值 没有返回值
//        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
//            System.out.println("线程3， 线程1执行结果：" + f1 + "============线程2执行结果：" + f2);
//        }, executorService);
        //可以得到两个返回值，并且有返回数据
//        CompletableFuture<String> future03 = future01.thenCombineAsync(future02, (f1, f2) -> f2 + f1, executorService);
//        System.out.println("线程3执行结果：" + future03.get());

        /**
         * 两个任务完成一个 就执行任务3
         */
        //无感知结果
//        future01.runAfterEitherAsync(future02, () -> System.out.println("任务三开始之前的结果"), executorService);
        //可以感知一个结果，没有返回值 注意：此方法需要两个返回值一样
//        future01.acceptEitherAsync(future02, (res) -> {
//            System.out.println("执行结果" + res);
//        }, executorService);
        //感知结果，并且有返回值
        CompletableFuture<Object> future03 = future01.applyToEitherAsync(future02, (res) -> res + "哈哈哈", executorService);
        System.out.println("线程3执行结果：" + future03.get());
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
