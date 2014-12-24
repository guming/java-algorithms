package org.jinn.java.jdk.jmx;

import com.sun.management.OperatingSystemMXBean;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

/**
 * Created by cyrus.gu on 2014/12/24.
 */
public class JMXConsole {
    MemoryMXBean memBean;
    OperatingSystemMXBean opMXbean;
    MemoryUsage heap;
    MemoryUsage nonHeap;
    public JMXConsole() {
        try{
            JMXServiceURL serviceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:5050/jmxrmi");
            JMXConnector conn = JMXConnectorFactory.connect(serviceURL);
            MBeanServerConnection mbs=conn.getMBeanServerConnection();
            //ThreadMXBean
             memBean= ManagementFactory.newPlatformMXBeanProxy
                    (mbs, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);

             opMXbean =
                    ManagementFactory.newPlatformMXBeanProxy(mbs,
                            ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
            heap = memBean
                    .getHeapMemoryUsage();
            nonHeap = memBean
                    .getNonHeapMemoryUsage();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public long getUsedHeap(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long heapSizeUsed = heap.getUsed();
//        long nonHeapSizeUsed = nonHeap.getUsed();
//        long heapCommitedSize = heap.getCommitted();
//        long nonHeapCommitedSize = nonHeap.getCommitted();

        return heapSizeUsed;
    }
    public long getNonUsedHeap(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nonHeap.getUsed();
    }
    public double getProcessCpuRaio(){
        Long start = System.currentTimeMillis();
        long startT = opMXbean.getProcessCpuTime();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        long endT = opMXbean.getProcessCpuTime();
        double ratio = (endT-startT)/1000000.0/(end-start)/opMXbean.getAvailableProcessors();
        System.out.println(ratio);
        return ratio;
    }
    public static void main(String[] args) {
        try{
            JMXConsole jmxConsole=new JMXConsole();
            JFrame frame=new JFrame("Test data");
            RealTimeChart rtcp=new RealTimeChart("Random Data","Random","Data");
            rtcp.setJmxConsole(jmxConsole);
            frame.getContentPane().add(rtcp,new BorderLayout().CENTER);
            frame.pack();
            frame.setVisible(true);
            (new Thread(rtcp)).start();
            frame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent windowevent)
                {
                    System.exit(0);
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
