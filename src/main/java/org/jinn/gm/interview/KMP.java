package org.jinn.gm.interview;

/**
 * Created by Yao on 2014/10/20.
 */
public class KMP {

    private static int[] generateNext(String str) {
        int[] next = new int[str.length()];
        for (int i = 1, j = 0; i < str.length(); i ++) {
            while(j > 0 && str.charAt(i) != str.charAt(j))
                j = next[j - 1];
            if (str.charAt(i) == str.charAt(j))
                j ++;
            next[i] = j;
        }
        return next;
    }
    public static boolean kmp(String str, String dest) {
        int[] next =  generateNext(dest);
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j))
                j = next[j - 1];
            if (str.charAt(i) == dest.charAt(j))
                j++;
            if (j == dest.length())
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(KMP.kmp("ABCDAB ABCDABDEEF AB","ABCDABD"));
    }
}
