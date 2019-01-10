package com.threadlocal.test;

/**
 * 参考文档：
 *     https://github.com/love-somnus/SpringBoot/blob/master/SpringBoot-00-J2SE/src/test/java/com/somnus/thread/threadlocal/ThreadLocal1Test.java
 */
public class ThreadLocal1Test {
    // 创建一个Integer型的线程本地变量
    public static final ThreadLocal<Integer> container = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public void execute() {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                // 获取当前线程的本地变量，然后累加1000次
                int num = container.get();
                for (int i = 0; i < 1000; i++) {
                    num++;
                }
                // 重新设置累加后的本地变量
                container.set(num);
                System.out.println(Thread.currentThread().getName() + " : " + container.get());
                container.remove();
            }, "Thread-" + j).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal1Test test = new ThreadLocal1Test();
        test.execute();
    }
}
