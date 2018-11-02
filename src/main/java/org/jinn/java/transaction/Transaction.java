package org.jinn.java.transaction;

public interface Transaction {

    public static long timeElape = 3000;

    public void start();

    public void commit();

    public void rollback();

    public Lock getLock();

    public long getStartTime();

}
