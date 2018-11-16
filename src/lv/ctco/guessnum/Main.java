package lv.ctco.guessnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();
    public static final File RESULTS_FILE = new File("results.txt");


    public static void main(String[] args) {
        loadResults();
        do {
            System.out.println("What it is your name?");
            String name = scan.next();
            System.out.println("Hello " + name + "!");
            long t1;
            long t2;

            int myNum = rand.nextInt(100) + 1;

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

        saveResults();
        showResults();

    }

    private static void showResults() {
       // results.sort(Comparator.comparingInt(r -> r.triesCount));
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                                  .<GameResult>thenComparingLong(r -> r.duration))
                .limit(3)
                .forEach(r -> System.out.printf("%s %d %d \n", r.name , r.triesCount, r.duration));
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

    private static void saveResults(){
        try (PrintWriter fileOut = new PrintWriter(RESULTS_FILE)) {

           int skipCount = results.size() - 5;

            results.stream()
                    .skip(skipCount)
                    .forEach(r -> fileOut.printf("%s %d %d \n", r.name , r.triesCount, r.duration));

/*           for (GameResult r: results){
               if (skipCount <= 0) {
                   fileOut.println(r.name + " " + r.triesCount + " " + r.duration);
               }
               skipCount--;
            }*/

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    private static void loadResults(){
     try(Scanner in = new Scanner(RESULTS_FILE)){
         while(in.hasNext()){

             GameResult gr = new GameResult();
             gr.name = in.next();
             gr.triesCount = in.nextInt();
             gr.duration = in.nextLong();

           results.add(gr);
         }
     } catch (FileNotFoundException e){
         e.printStackTrace();
     }
    }
}