package org.jinn.gm.algorithms.Tree;

/**
 * Created by guming on 14-3-24.
 */
public class User implements Comparable<User>{
    private int age;
    private String name;

    public User(String name,int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        if (this.age>o.age)
        return 1;
        if (this.age==o.age)
            return 0;
        else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
