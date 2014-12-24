package org.jinn.java.jdk.jmx;

import com.sun.management.OperatingSystemMXBean;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
//import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.TimeUnit;

/**
 * Created by cyrus.gu on 2014/12/24.
 */
public class JMXConsole {
    public static void main(String[] args) {
        try{
            JMXServiceURL serviceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:5050/jmxrmi");
            JMXConnector conn = JMXConnectorFactory.connect(serviceURL);
            MBeanServerConnection mbs=conn.getMBeanServerConnection();

            MemoryMXBean memBean= ManagementFactory.newPlatformMXBeanProxy
                    (mbs, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);

            OperatingSystemMXBean opMXbean =
                    ManagementFactory.newPlatformMXBeanProxy(mbs,
                            ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MemoryUsage heap = memBean
                    .getHeapMemoryUsage();
            MemoryUsage nonHeap = memBean
                    .getNonHeapMemoryUsage();
            long heapSizeUsed = heap.getUsed();
            long nonHeapSizeUsed = nonHeap.getUsed();
            long heapCommitedSize = heap.getCommitted();
            long nonHeapCommitedSize = nonHeap.getCommitted();
            System.out.println(heapSizeUsed+","+nonHeapCommitedSize);
            Long start = System.currentTimeMillis();
            long startT = opMXbean.getProcessCpuTime();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
//                logger.error("InterruptedException occurred while MemoryCollector sleeping...");
            }
            Long end = System.currentTimeMillis();
            long endT = opMXbean.getProcessCpuTime();
//end - start 即为当前采集的时间单元，单位ms
//endT - startT 为当前时间单元内cpu使用的时间，单位为ns
            double ratio = (endT-startT)/1000000.0/(end-start)/opMXbean.getAvailableProcessors();
            System.out.println(ratio);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
