package me.romkit.string;

import java.util.Scanner;

public class Step8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        reverseString(words);

    }

    static void reverseString(String words) {
        char[] arr = words.toUpperCase().toCharArray();
        int lt = 0;
        int rt = arr.length - 1;
        String result = "YES";
        while (lt < rt) {
            if (Character.isAlphabetic(words.charAt(lt)) && Character.isAlphabetic(words.charAt(rt))) {

                if (arr[lt] != arr[rt]) {
                    result = "NO";
                    break;
                }
            }
            lt++;
            rt--;
        }
        System.out.println(result);

    }
}