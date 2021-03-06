package 多线程.s03;


public class Main {
    public static int m = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i ++){
            threads[i] = new Thread(()-> {
                synchronized (Main.class) {
                    for (int j = 0; j < 1000; j++) {
                        m++;
                    }
                }
            });
        }

        for(Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join(); // 等待所有线程运行结束
        System.out.println(m);
    }
}
