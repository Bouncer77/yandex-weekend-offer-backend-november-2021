package com.bouncer77.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaskG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        List<Integer> ratingList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ratingList.add(scanner.nextInt());
        }

        // System.out.println(ratingList);

        Collections.sort(ratingList);

        // System.out.println(ratingList);

        int sum = 0;
        int money = 500;
        for (int i = 0; i < ratingList.size(); i++) {

            if (i == 0) {
                sum += 500;
                continue;
            }

            if (ratingList.get(i) > ratingList.get(i - 1)) {
                money += 500;
                sum += money;
            } else if (ratingList.get(i) == ratingList.get(i - 1)) {
                sum += money;
            }
        }

        // System.out.println("SUM = " + sum);
        System.out.println(sum);
    }
}
