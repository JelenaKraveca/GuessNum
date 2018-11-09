package lv.ctco.guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        int myNum = rand.nextInt(100) + 1;

        for (int i = 1; i<=10; i++) {
            System.out.println("What is my number?");
            int num;
            try {
                num = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater!");
                return;
            }
            if (myNum > num) {
                System.out.println(i+") My number is bigger!");
            } else if (myNum < num){
                System.out.println(i+")My number is less!");
            } else if  (myNum == num){
                System.out.println("Your guess!");
                break;
            }
            System.out.println("Try again!");

        }
        System.out.println("My num is : " + myNum);
    }
}
