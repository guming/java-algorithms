package org.jinn.gm.jdk.jmx;

/**
 * Created by cyrus.gu on 2014/12/24.
 */
public class ServerMonitor implements ServerMonitorMBean {
    public long upTime;
    private final ServerImpl target;
    public ServerMonitor(ServerImpl target){
        this.target = target;
    }
    public long getUpTime(){
        return System.currentTimeMillis() - target.startTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }
}
