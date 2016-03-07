package org.jinn.java.jdk.nio.libevent;

import java.io.IOException;
import java.nio.channels.SelectableChannel;

/**
 * Created by gumingcn on 2014/10/21.
 */
public class EventHandler implements EventConfig{
    EventBase eventBase;
    EventOp eventOp;
    public void init()throws IOException{
        eventBase=new EventBase();
        eventOp=new SelectOp(this);
        eventOp.event_base_init();
        eventBase.eventOp=eventOp;
    }

    public void eventLoop()throws IOException{

    }

    public void addEvent(Event event,long timeout)throws IOException{
        EventBase eb=event.eventBase;
        EventOp op=eb.eventOp;
        if (isValidEvent(event)&&!isActiveEvent(event)){
            op.event_base_init();
        }
    }

    public void delEvent(Event event)throws IOException{

    }

    public Event newEvent(SelectableChannel channel,int events,EventCallBackHandler eventCallBackHandler,Object...args) {
        return null;
    }

    private boolean isActiveEvent(Event event){
        return (event.getStatus()&(EVLIST_ACTIVE|EVLIST_INSERTED))>0;
    }

    private boolean isValidEvent(Event event){
        return (event.getOp()&(EV_ACCEPT|EV_CONNECT|EV_READ|EV_WRITE))>0;
    }
}
