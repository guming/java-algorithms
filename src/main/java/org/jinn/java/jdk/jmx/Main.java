package org.jinn.java.jdk.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by cyrus.gu on 2014/12/24.
 */
public class Main {
    private static ObjectName objectName ;
    private static MBeanServer mBeanServer;
    public static void main(String[] args) throws Exception{
        init();
        manage();
    }
    private static void init() throws Exception{
        ServerImpl serverImpl = new ServerImpl();
        ServerMonitor serverMonitor = new ServerMonitor(serverImpl);
        mBeanServer =  ManagementFactory.getPlatformMBeanServer();
        objectName = new ObjectName("org.jinn.java.jdk.jmx:type=ServerMonitor1");
        mBeanServer.registerMBean(serverMonitor,objectName);
        System.out.println(serverMonitor.getUpTime());
    }
    private static void manage() throws Exception{
        Thread.sleep(1000*120);
    }
}
