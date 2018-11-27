package org.jinn.gm.jdk.reflect;

import java.lang.instrument.Instrumentation;

/**
 * Created by guming on 2017/9/20.
 */
public class Agent {
    public static void premain(String[] args, Instrumentation ins) {
        System.out.println("start the agent.");
        ins.addTransformer(new MyTransformer());
    }
}
