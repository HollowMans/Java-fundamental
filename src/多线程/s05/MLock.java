package 多线程.s05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MLock implements Lock {
    private volatile int i = 0;

    @Override
    public void lock() {
        synchronized (this){
            while(i != 0){ // 判断i 是否为0--否则有线程占用
                try{
                    this.wait(); // 阻塞CAS--让其他线程阻塞
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            i = 1; // 让当前线程占用这把锁
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this){
            i = 0; // 当前线程退出释放锁
            this.notifyAll(); // 唤醒阻塞的线程
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
