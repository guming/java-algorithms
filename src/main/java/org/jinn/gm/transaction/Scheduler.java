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

    private static ConcurrentLinkedQueue requestQueue = new ConcurrentLinkedQueue();

    private Scheduler() {
    }

    public static Scheduler getScheduler(){
        return scheduler;
    }

//    public Long getNextSeq(){
//        return txSeq.addAndGet(1);
//    }

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
//                    requestQueue.offer(lock.getTxId());
                    return true;
                }
            }
        } else {
//            requestQueue.offer(lock.getTxId());
        }
        long end = System.currentTimeMillis();
        System.out.println("lock take time:"+(end-start)+","+lock.getTxId());
        return true;
    }

    private boolean acquirelock(Integer eid,Lock lock){
        long start = System.currentTimeMillis();
        Lock return_lock=lockTable.putIfAbsent(eid, lock);
        if(return_lock==null||return_lock.getTxId()==lock.getTxId()){
            System.out.println("acquire succ time:"+(System.currentTimeMillis()-start));
            return true;
        } else {
            return false;
        }
    }

    public boolean release(Integer eid,Lock lock,long startTime){
        boolean flag = lockTable.remove(eid, lock);
        return flag;
    }

    public static ConcurrentLinkedQueue getRequestQueue() {
        return requestQueue;
    }
}
