package lv.ctco.guessnum;

import java.util.*;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();




    public static void main(String[] args) {
        do {
            System.out.println("What it is your name?");
            String name = scan.next();
            System.out.println("Hello " + name + "!");
            long t1;
            long t2;

            int myNum = rand.nextInt(10) + 1;

            for (int i = 1; i <= 100; i++) {
                t1 = System.currentTimeMillis();
                System.out.println("What is my number?");
                int num = readUserNum();
                if (myNum > num) {
                    System.out.println(i + ") My number is bigger!");
                } else if (myNum < num) {
                    System.out.println(i + ")My number is less!");
                } else if (myNum == num) {
                    System.out.println("Your guess!");
                    t2 = System.currentTimeMillis();
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount  = i;
                    r.duration = t2-t1;
                    results.add(r);
                    System.out.println("Your time:" + r.duration);
                    break;
                }
                System.out.println("Try again!");

            }
            System.out.println("My num is : " + myNum);
            System.out.println("Do you want play again? Enter Y or N");

        } while ("Y".equals(scan.next()));

        for (GameResult r: results){
            System.out.printf("Player %s done %d treies and it took %.2f sec\n",
                    r.name ,
                    r.triesCount,
                    r.duration/1000.00);
        }
    }

    private static int readUserNum() {
        while (true) {
            try {
                int num = scan.nextInt();
                if (num < 1 || num > 100) {
                    System.out.println("Please enter in range 1..100!");
                    continue;
                }
                return num;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("You are cheater!");
            }
        }
    }
}