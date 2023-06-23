package client;

import entity.Admin;
import entity.Voter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Admin ad = new Admin();
        Voter v = new Voter();
        //Voter vo = new Voter();
        Scanner input = new Scanner(System.in);
        int choice = 0, counter = 0;
        do {
            ad.readCandidate();
            ad.readPollStatus();
            System.out.println("The current poll status is : " + ad.getPollStatus());
            System.out.println("\n                          Welcome to Student Council Voting System!!! \n");
            System.out.println("**************************************");
            System.out.println("                  Main Menu         ");
            System.out.println("**************************************");
            System.out.println("      1)Admin                       ");
            System.out.println("      2)Voter                       ");
            System.out.println("      3)Exit                        ");
            System.out.println("**************************************");

            while (counter == 0) {
                try {
                    System.out.print("\nPlease Enter Your Choice :");
                    choice = input.nextInt();
                    if (choice < 1 || choice > 3) {
                        System.out.print("Invalid, Please enter again!! : \n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Type of Input, Please Try Again.");
                    input.next(); // to consume the input token (prevent infinite loops) 

                }
            }

            switch (choice) {
                case 1:
                    ad.login();
                    counter = 0;
                    break;
                case 2:         
                    v.voterMenu();
                    counter = 0;
                    break;
                case 3:
                    System.out.println("Thank you for using the system. See you again.");
                    break;
                default:
                    System.out.println("");
            }

        } while (choice != 3);

    }
}


