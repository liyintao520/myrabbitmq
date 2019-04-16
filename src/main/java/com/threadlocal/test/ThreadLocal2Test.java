package com.threadlocal.test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 参考文档：https://github.com/love-somnus/SpringBoot/blob/master/SpringBoot-00-J2SE/src/test/java/com/somnus/thread/threadlocal/ThreadLocal2Test.java
 */
public class ThreadLocal2Test {
    /**
     * 必须实例化重写内部方法initialValue()  不重写该方法的话，默认返回的事null，请看源码。
     */
    // 创建一个Integer型的线程本地变量
    public static final ThreadLocal<Integer> container = new ThreadLocal<Integer>();

    public void execute() {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                // 获取当前线程的本地变量，然后累加1000次
                int num = container.get();
                //
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
    public Map<Long, String> getIdNameMap(List<ThreadLocal2Test> accounts) {
        return accounts.stream().collect(Collectors.toMap(ThreadLocal2Test::1, Account::getUsername));
    }
    public static void main(String[] args) {
        try {
            ThreadLocal2Test test = new ThreadLocal2Test();
            test.execute();
        } catch (Exception e) {

            System.out.println("定义的时候这个值是container = " + container);
        } finally {
            System.out.println("必须实例化重写内部方法initialValue()  不重写该方法的话，默认返回的是null，请看源码!!!");
        }
    }
}
