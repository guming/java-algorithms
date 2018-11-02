package org.jinn.java.transaction;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {
    private Integer id;
    private Integer txId;
    private AtomicBoolean isMutex=new AtomicBoolean(false);
    private AtomicBoolean waitStatus=new AtomicBoolean(false);

    public Lock(Integer id, Integer txId) {
        this.id = id;
        this.txId = txId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
    }

    public AtomicBoolean getIsMutex() {
        return isMutex;
    }

    public void setIsMutex(AtomicBoolean isMutex) {
        this.isMutex = isMutex;
    }

    public AtomicBoolean getWaitStatus() {
        return waitStatus;
    }

    public void setWaitStatus(AtomicBoolean waitStatus) {
        this.waitStatus = waitStatus;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", txId=" + txId +
                '}';
    }
}
