package org.jinn.java.interview;

/**
 * Created by gumingcn on 14-8-14.
 */
public class PalindromeTest {
    public static void main(String args[]){

//        Scanner scanner = new Scanner(System.in);
        //int number = scanner.nextInt();
//
        reverse(1221);
        Boolean.valueOf(true);

    }

    private static boolean isPalindrome(int number) {
        if(number == reverse(number)){
            return true;
        }
        return false;
    }


    private static int reverse(int number){
        int reverse = 0;

        while(number != 0){
            reverse = reverse*10 + number%10;
            number = number/10;
        }

        return reverse;
    }


}
