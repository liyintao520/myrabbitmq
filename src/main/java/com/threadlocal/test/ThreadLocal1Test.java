package com.threadlocal.test;

/**
 * 参考文档：
 *     https://github.com/love-somnus/SpringBoot/blob/master/SpringBoot-00-J2SE/src/test/java/com/somnus/thread/threadlocal/ThreadLocal1Test.java
 */
public class ThreadLocal1Test {
//    程序员希望线程局部变量具有初始值必须为此方法被重写。通常情况下，将使用匿名内部类。
    // 创建一个Integer型的线程本地变量，并重写该方法initialValue() 设置初始化值
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
        try {
            ThreadLocal1Test test = new ThreadLocal1Test();
            test.execute();
        } catch (Exception e) {
            System.out.println("定义的时候这个值是container = " + container);
        } finally {
            System.out.println("必须实例化重写内部方法initialValue()  不重写该方法的话，默认返回的是null，请看源码!!!");
        }
    }
}
