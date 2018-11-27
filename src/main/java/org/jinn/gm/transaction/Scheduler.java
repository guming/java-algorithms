package org.jinn.gm.transaction;


import org.jinn.gm.algorithms.queue.LinkedQueue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Scheduler {

    private AtomicLong txSeq = new AtomicLong(10000000);

    private AtomicInteger txIdGenerater = new AtomicInteger(0);

    //element.id
//    ConcurrentHashMap<Integer, LinkedQueue> lockWaitTable = new ConcurrentHashMap<>();
    //element.id
    ConcurrentHashMap<Integer, Lock> lockTable = new ConcurrentHashMap<>();

    private static Scheduler scheduler = new Scheduler();

    private static LinkedQueue requestQueue = new LinkedQueue();

    private Scheduler() {
    }

    public static Scheduler getScheduler(){
        return scheduler;
    }

    public Long getNextSeq(){
        return txSeq.addAndGet(1);
    }

    public Integer generateTxId(){
        return txIdGenerater.addAndGet(1);
    }

    public boolean lock(Integer eid,Lock lock,long startTime){
        //try lock, and failed
        long start = System.currentTimeMillis();
        if(!acquirelock(eid,lock)){
            //put into the wait queue and retry
            for(;;) {
                long currentTime = System.currentTimeMillis();
                //timeout and break
                if ((currentTime - startTime) > Transaction.timeElape) {
                    System.out.println("transaction timeout." + lock);
                    return false;
                }
                if(acquirelock(eid, lock)){
                    System.out.println("acquired lock true:"+lock+"," +
                            "tid:"+Thread.currentThread().getId()+",time:"+System.currentTimeMillis());
                    requestQueue.offer(lock.getTxId());
                    return true;
                }
            }
//            LinkedQueue newLockList = new LinkedQueue();
//            newLockList.offer(lock);
//            System.out.println("put que:"+lock);
//            LinkedQueue oldLockList = lockWaitTable.putIfAbsent(eid, newLockList);
//            if (oldLockList != null) {
//                System.out.println("put que:"+lock);
//                oldLockList.offer(lock);
//            }
//            return false;
        } else {
            System.out.println("acquired lock true:"+lock+"," +
                    "tid:"+Thread.currentThread().getId()+",time:"+System.currentTimeMillis());

            requestQueue.offer(lock.getTxId());
        }
        long end = System.currentTimeMillis();
        System.out.println("lock take time:"+(end-start)+","+lock.getTxId());
        return true;
    }

    private boolean acquirelock(Integer eid,Lock lock){
        Lock return_lock=lockTable.putIfAbsent(eid, lock);
        if(return_lock==null||return_lock.getTxId()==lock.getTxId()){
            return true;
        } else {
            return false;
        }
    }

    public boolean release(Integer eid,Lock lock,long startTime){
        boolean flag = false;
//        synchronized (lockTable) {
            flag = lockTable.remove(eid, lock);
            if (flag) {
//                notifyExchangeLock(eid, startTime);
                System.out.println("release:" + flag + ",lock:" + lock);
            } else {
                System.out.println("release:" + flag + ",lock:" + lock);
            }
//        }
        return flag;
    }

    public static LinkedQueue getRequestQueue() {
        return requestQueue;
    }
}
