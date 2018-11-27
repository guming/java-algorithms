package org.jinn.gm.interview.jcode;

import java.util.HashMap;

/**
 * Created by gumingcn on 2014/10/17.
 */
public class Solution {

    public static int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, Integer> coverMap = new HashMap<Integer, Integer>();
        int[] result=new int[2];
        for (int i = 0; i <numbers.length ; i++) {
            int temp=numbers[i];
            int sub=target-temp;
            if (coverMap.containsKey(sub)){
                result[0]=coverMap.get(sub)+1;
                result[1]=i+1;
                break;
            }
            coverMap.put(numbers[i],i);
        }
        return result;

    }

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int maxLen = 0;
        int tempLen=0;
        int start=0;
        HashMap<Character, Integer> cmap = new HashMap<Character, Integer>();
        for (int i = 0; i < len; i++) {
            Integer offset=cmap.get(s.charAt(i));
            if (offset==null||offset<start){
                tempLen++;
            }else{
                tempLen=i-offset;
                start=offset+1;
            }
            cmap.put(s.charAt(i), i);
            if(tempLen>maxLen) {
                maxLen = tempLen;
            }
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int len=s.length();
        String[] str=new String[len];
        for (int i = 0; i <len ; i++) {
            str[i]=s.substring(i,len);
        }

        int max=0;
        int temp=0;
        int half=(len+1)/2;
        String old_sub_str=null;
        for (int i = 1; i <half ; i++) {
            for (int  j= 0; j+i <len-1 ; j+=i) {
                if (str[j+i].length()>=i&&str[j].length()>=i) {
                    String s1 = str[0].substring(0, i);
                    if (old_sub_str == null) {
                        old_sub_str=s1;
                    }

                    String s2 = str[j + i].substring(0, i);
                    if (s2.equals(old_sub_str+old_sub_str)) {
                        temp=0;
                        break;
                    }
                    if (s1.equals(s2)) {
                        temp += 1;
                    }else{
//                        temp=0;
                        break;
                    }
                }
            }
            if(temp>0) {
                max = i;
            }else {
                temp=0;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        int[] numbers={2, 7, 11, 15};
        int target=18;
//        int[] result=twoSum(numbers, target);
//        System.out.println(result[0]+","+result[1]);
        System.out.println(lengthOfLongestSubstring("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco"));
    }
}
