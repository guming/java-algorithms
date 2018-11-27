package org.jinn.gm.jdk.nio.libevent;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by gumingcn on 2014/10/21.
 */
public class SelectOp implements EventOp {

    private Selector selector;
    private EventHandler eventHandler;

    public SelectOp(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void event_base_init() throws IOException {
        this.selector = Selector.open();
    }

    public void event_base_dispatch(EventBase eventBase, long timeout) {

    }

    public void event_base_new(Event event) {

    }

    public void event_base_delete(Event event) {

    }


}
