package org.jinn.java.jdk.jmx;

/**
 * Created by cyrus.gu on 2014/12/24.
 */
public interface ServerMonitorMBean{
    public long getUpTime();
    public void setUpTime(long upTime);
}
