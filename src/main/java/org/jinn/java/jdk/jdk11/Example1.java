package org.jinn.java.jdk.jdk11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example1 {

    public static void main(String[] args) {
        var name="so sad";
        System.out.println(name);
        var list = List.of("a", "b", "c");
        list.forEach(item-> System.out.println(item));
        System.out.println("+++++++++++++");
        list.forEach(System.out::println);
        System.out.println("+++++++++++++");
        list.stream().filter(s -> s.contains("b")).forEach(System.out::println);
        System.out.println("+++++++++++++");
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        items.forEach((k,v) ->{

        } );
    }
    
}
