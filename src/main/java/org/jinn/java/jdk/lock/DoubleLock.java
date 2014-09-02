package org.jinn.java.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by gumingcn on 14-9-2.
 */
public class DoubleLock implements Lock {
    public final Sync sync=new Sync(2);
    class Sync extends AbstractQueuedSynchronizer{

        public Sync(int acquired) {
            if(acquired<=0){
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(acquired);
        }

        @Override
        protected int tryAcquireShared(int arg) {
//            return super.tryAcquireShared(arg);
            for(;;){
                int current=getState();
                int result=current-arg;
                if (result<0||compareAndSetState(current,result)){
                    return result;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
//            return super.tryReleaseShared(arg);
            for (;;) {
                int current = getState();
                int result = current + arg;
                if (compareAndSetState(current,result)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
