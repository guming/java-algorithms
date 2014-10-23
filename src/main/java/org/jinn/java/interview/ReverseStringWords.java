package org.jinn.java.interview;

/**
 * Created by guming on 2014/10/20.
 */
public class ReverseStringWords {
    public static void main(String[] args) {
        System.out.println(reverse("you are a better man"));
    }
    public static String reverse(String s){
        String[] temp=s.split(" ");
        int count = s.length();
        int len = temp.length;
        StringBuilder sb=new StringBuilder();
        while(len>0){
            if(!temp[len-1].equals("")) {
                sb.append(temp[len - 1]);
                if (len != 1) {
                    sb.append(" ");
                }
            }
            len--;
        }
        return sb.length()>0?sb.toString().trim():"";
    }
}
