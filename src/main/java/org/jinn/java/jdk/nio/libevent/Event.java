package org.jinn.java.jdk.nio.libevent;

import java.nio.channels.SelectableChannel;

/**
 * Created by gumingcn on 2014/10/21.
 */
public class Event implements Comparable<Event>, EventConfig{
    EventBase eventBase;
    SelectableChannel channel;
    EventCallBackHandler eventCallBackHandler;
    long timeoutstamp;
    int op;
    int status=EVLIST_INIT;;
    int priority;
    public int compareTo(Event o) {
        if(this.timeoutstamp>o.timeoutstamp){
            return 1;
        }else if(timeoutstamp==o.timeoutstamp) {
            return 0;
        }else{
            return -1;
        }
    }

    public EventBase getEventBase() {
        return eventBase;
    }

    public SelectableChannel getChannel() {
        return channel;
    }

    public EventCallBackHandler getEventCallBackHandler() {
        return eventCallBackHandler;
    }

    public long getTimeoutstamp() {
        return timeoutstamp;
    }

    public int getOp() {
        return op;
    }

    public int getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
