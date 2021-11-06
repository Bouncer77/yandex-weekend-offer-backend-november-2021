package com.bouncer77.games;

import java.util.Scanner;

public class TaskA {

    public static void main(String[] args) {

        int res = 0;
        Scanner scanner = new Scanner(System.in);
        String line0 = scanner.nextLine(); // ab
        String line1 = scanner.nextLine(); // aabbccvvff

        for (int i = 0; i < line0.length(); i++) {
            for (int j = 0; j < line1.length(); j++) {
                if (line0.charAt(i) == line1.charAt(j)) {
                    res++;
                }
            }
        }

        /*for (int i = 0; i < line0.length(); ++i) {
            String s = Character.toString(line0.charAt(i));
            // res += count(args[1], s);
            res += (line1.length() - line1.replace(s, "").length()) / s.length();
        }*/
        
        /*for (int i = 0; i < args[0].length(); ++i) {
            String s = Character.toString(args[0].charAt(i));
            // res += count(args[1], s);
            res += (args[1].length() - args[1].replace(s, "").length()) / s.length();
        }*/

        System.out.println(res);
    }

    public static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }
}
