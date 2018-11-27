package org.jinn.gm.jdk.nio.libevent;

import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by gumingcn on 2014/10/21.
 */
public class EventBase {

    public LinkedBlockingDeque<Event> eventsQueue;

    public LinkedBlockingDeque<Event>[] activeEventQueues;

    public PriorityQueue<Event> time;

    public boolean eventBreak;

    public int activeEventsCount=0;

    public EventOp eventOp;

    public EventBase(){
        this.eventsQueue=new LinkedBlockingDeque<Event>();
        this.activeEventQueues=new LinkedBlockingDeque[5];
        this.time = new PriorityQueue<Event>();
        for (int i = 0; i < 5; i++) {
            this.activeEventQueues[i] = new LinkedBlockingDeque<Event>();
        }
    }

}
