package org.jinn.gm.jdk.nio.libevent;


import java.io.IOException;

/**
 * Created by gumingcn on 2014/10/21.
 */
public interface EventOp {

    public void event_base_init() throws IOException;

    public void event_base_dispatch(EventBase eventBase, long timeout);

    public void event_base_new(Event event);

    public void event_base_delete(Event event);

}
