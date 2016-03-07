package org.jinn.java.jdk.nio.libevent;

import java.nio.channels.SelectableChannel;

/**
 * Created by gumingcn on 2014/10/21.
 */
public interface EventCallBackHandler {

    public void callback(SelectableChannel channel, int interest, Object... args);

}
