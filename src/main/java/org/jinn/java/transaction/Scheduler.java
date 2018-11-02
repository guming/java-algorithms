package org.jinn.java.transaction;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Scheduler {

    private AtomicLong txSeq = new AtomicLong(10000000);

    private AtomicInteger txIdGenerater = new AtomicInteger(0);

    BlockingQueue waitQueue = new ArrayBlockingQueue(1000);
    //element.id
    ConcurrentHashMap<Integer, ConcurrentLinkedQueue> lockWaitTable = new ConcurrentHashMap<>();
    //element.id
    ConcurrentHashMap<Integer, Lock> lockTable = new ConcurrentHashMap<>();

    private static Scheduler scheduler = new Scheduler();

    private static ConcurrentLinkedQueue requestQueue = new ConcurrentLinkedQueue();

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
        if(!acquirelock(eid,lock)){
            //put into the wait queue and retry
            ConcurrentLinkedQueue newLockList = new ConcurrentLinkedQueue();
            newLockList.offer(lock);
            System.out.println("put que:"+lock);
            ConcurrentLinkedQueue oldLockList = lockWaitTable.putIfAbsent(eid, newLockList);
            if (oldLockList != null) {
                System.out.println("put que:"+lock);
                oldLockList.offer(lock);
            }

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
        }else {
            System.out.println("acquired lock true:"+lock+"," +
                    "tid:"+Thread.currentThread().getId()+",time:"+System.currentTimeMillis());

            requestQueue.offer(lock.getTxId());
        }
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
        boolean flag = lockTable.remove(eid, lock);
        if(flag) {
            notifyExchangeLock(eid, startTime);
            System.out.println("release:"+flag+",lock:"+lock);
        }else {
            System.out.println("release:"+flag+",lock:"+lock);
        }
        return flag;
    }

    public void notifyExchangeLock(Integer eid,long startTime){
        ConcurrentLinkedQueue lockList = lockWaitTable.get(eid);
        if(!lockList.isEmpty()){
            Lock new_lock=(Lock)lockList.poll();
            if(new_lock!=null) {
                System.out.println("ex new lock:" + acquirelock(eid, new_lock) + ",poll lock:" + new_lock.toString());
            }
        }else {
            System.out.println("lockList is empty");
        }
    }

    public static ConcurrentLinkedQueue getRequestQueue() {
        return requestQueue;
    }
}
