package org.jinn.gm.jdk.nio.libevent;

/**
 * Created by gumingcn on 2014/10/21.
 */
public interface EventConfig {
            //#define EV_TIMEOUT      0x01
            //#define EV_READ         0x02
           // #define EV_WRITE        0x04
           // #define EV_SIGNAL       0x08
           // #define EV_PERSIST      0x10
           // #define EV_ET           0x20
    public int EV_TIMEOUT = 0x01;
    public int EV_READ = 0x02;
    public int EV_WRITE = 0x04;
    public int EV_ACCEPT = 0x08;
    public int EV_CONNECT = 0x10;
    public int EV_PERSIST = 0x20;

    public int EVLIST_TIMEOUT = 0x01;
    public int EVLIST_INSERTED = 0x02;
    public int EVLIST_ACTIVE = 0x04;
    public int EVLIST_INTERNAL = 0x08;
    public int EVLIST_INIT = 0x10;
}
