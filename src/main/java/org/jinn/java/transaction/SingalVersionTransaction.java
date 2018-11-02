package org.jinn.java.transaction;

public class SingalVersionTransaction implements Transaction{
    private long startTime = System.currentTimeMillis();
    private Lock lock;
    private Element element;
    private Integer txId;

    public SingalVersionTransaction(Element element) {
        this.element = element;
    }

    @Override
    public void start() {
        Integer txId=Scheduler.getScheduler().generateTxId();
        this.txId = txId;
        lock = new Lock(txId, txId);
        System.out.println("transaction start:"+txId);
    }

    @Override
    public void commit() {
        System.out.println("transaction commit:"+element.toString()+",lock:"+lock.toString());
        Scheduler.getScheduler().release(element.getId(),lock,startTime);
    }

    @Override
    public void rollback() {
        Scheduler.getScheduler().release(element.getId(), lock,startTime);
    }

    public Lock getLock() {
        return lock;
    }

    public long getStartTime() {
        return startTime;
    }
}
