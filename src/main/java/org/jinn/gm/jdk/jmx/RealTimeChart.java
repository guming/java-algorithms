package org.jinn.gm.jdk.jmx;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by cyrus.gu on 2014/12/24.
 */

public class RealTimeChart extends ChartPanel implements Runnable
{
    JMXConsole jmxConsole;
    private static TimeSeries timeSeries;
    private long value=0;

    public JMXConsole getJmxConsole() {
        return jmxConsole;
    }

    public void setJmxConsole(JMXConsole jmxConsole) {
        this.jmxConsole = jmxConsole;
    }

    public RealTimeChart(String chartContent,String title,String yaxisName)
    {
        super(createChart(chartContent,title,yaxisName));
    }

    private static JFreeChart createChart(String chartContent,String title,String yaxisName){

        timeSeries = new TimeSeries(chartContent,Millisecond.class);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"time(s)",yaxisName,timeseriescollection,true,true,false);
        XYPlot xyplot = jfreechart.getXYPlot();
        ValueAxis valueaxis = xyplot.getDomainAxis();
        valueaxis.setAutoRange(true);
        // 30s
        valueaxis.setFixedAutoRange(30000D);
        valueaxis = xyplot.getRangeAxis();

        return jfreechart;
    }
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                timeSeries.add(new Millisecond(), jmxConsole.getUsedHeap()/1024);
                Thread.sleep(1000);
            }
            catch (InterruptedException e)  {   }
        }
    }

    private long randomNum()
    {
        System.out.println((Math.random()*20+80));
        return (long)(Math.random()*20+80);
    }

    public static void main(String[] args)
    {
        JFrame frame=new JFrame("Test data");
        RealTimeChart rtcp=new RealTimeChart("Random Data","Random","Data");
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
    }
}
