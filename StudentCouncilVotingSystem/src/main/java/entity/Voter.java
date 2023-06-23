/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.LinkedList;
import adt.LinkedListInterface;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Human
 */
public class Voter implements Serializable {

    private String name;
    private String id;
    private String password;
    private String status;
    private String gender;
    private String studClass;
    private String votedId;

    private static LinkedListInterface<Voter> voterList = new LinkedList<>();
    String voteFileName = "vote.txt";

    public Voter() {
    }

    public Voter(String id) {
        this.id = id;
    }

    public Voter(String name, String id, String password, String gender, String studClass) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.gender = gender;
        this.status = "NOT VOTED";
        this.studClass = studClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVotedId() {
        return votedId;
    }

    public void setVotedId(String votedId) {
        this.votedId = votedId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getStudClass() {
        return studClass;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    public static LinkedListInterface<Voter> getVoterList() {
        return voterList;
    }

    public static void setVoterList(LinkedListInterface<Voter> voterList) {
        Voter.voterList = voterList;
    }

    public void voterMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        Admin ad = new Admin();
        do {
            ad.readPollStatus();

            int choice = 0, counter = 0;
            System.out.println("\t\t\t\t==========");
            System.out.println("\t\t\t\tVoter Menu");
            System.out.println("\t\t\t\t==========");

            System.out.println("\nAvailable options: ");
            System.out.println("1. Search Registration Status ");
            System.out.println("2. Register ");
            System.out.println("3. Login");
            System.out.println("4. Main Menu");

            while (counter == 0) {
                try {
                    System.out.print("\nEnter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 1 || choice > 4) {
                        System.out.print("Invalid input, please try again.\n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid type of input, please try again.");
                    // to consume the input token (prevent infinite loops) 
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    checkRegistered();
                    break;
                case 2:
                    if (!ad.getPollStatus().equals("Open")) {
                        System.out.println("The poll is not opened yet.\n");
                    } else {
                        registerVoter();
                    }
                    break;
                case 3:
                    loginVoter();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("");
            }
        } while (!exit);
    }

    public void checkRegistered() {
        Iterator<Voter> it = voterList.getIterator();
        Scanner scanner = new Scanner(System.in);
        int i = 1;

        System.out.print("Enter your StudentID to checking registration status: ");
        String userInputId = scanner.nextLine();

        if (voterList.isEmpty()) {
            System.out.println("No voter record in the system.");
        }

        while (!voterList.isEmpty()) {
            while (it.hasNext() && i <= voterList.getNumberOfEntries()) {
                if (voterList.getEntry(i).getId().equals(userInputId)) {        //1st node
                    System.out.println("You have already registered.");
                    i = voterList.getNumberOfEntries() + 1;
                } else {
                    do {
                        i += 1;

                        if (voterList.getEntry(i) == null) {
                            System.out.println("No such voter record.");
                            break;
                        }
                        if (voterList.getEntry(i).getId().equals(userInputId)) {        //next node
                            System.out.println("You have already registered.");
                            i = voterList.getNumberOfEntries() + 1;
                            break;
                        }
                    } while (i <= voterList.getNumberOfEntries());
                }
            }
            break;
        }
    }

    public void registerVoter() {
        String voteFileName = "vote.txt";
        voterList = FileHandler.readVoteFile(voteFileName);

        Scanner scanner = new Scanner(System.in);
        String userInputId;
        int userInputGender = 0;
        int userInputClass = 0;

        System.out.println("\t\t\t\t=================");
        System.out.println("\t\t\t\tRegistration Form");
        System.out.println("\t\t\t\t=================");

        System.out.print("Enter your name: ");
        String userInputName = scanner.nextLine();

        boolean isValid = true;
        do {
            System.out.print("\nEnter your ID: ");
            userInputId = scanner.nextLine();
            for (int i = 1; i <= voterList.getNumberOfEntries(); i++) {
                if (userInputId.equals(voterList.getEntry(i).getId())) {
                    System.out.println("The ID is already taken. Unable to register with same ID.");
                    System.out.println("If you input wrong name, you can change your name after the registration.");
                    isValid = false;
                    break;
                } else {
                    isValid = true;
                }
            }
        } while (!isValid);

        System.out.print("\nEnter your password: ");
        String userInputPw = scanner.nextLine();

        String userGenderSelection = null;

        int counter = 0;

        while (counter == 0) {
            try {
                System.out.println("\nGender available: ");
                System.out.println("1. Male");
                System.out.println("2. Female");
                System.out.print("\nSelect your gender: ");
                userInputGender = scanner.nextInt();
                scanner.nextLine();

                if (userInputGender < 1 || userInputGender > 2) {
                    System.out.print("Invalid input, please try again.\n");
                } else {
                    counter++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid type of input, please try again.");
                scanner.nextLine(); // to consume the input token (prevent infinite loops) 
            }
        }

        switch (userInputGender) {
            case 1:
                userGenderSelection = "M";
                break;
            case 2:
                userGenderSelection = "F";
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        counter = 0;
        while (counter == 0) {
            try {
                System.out.println("\nClasses available: ");
                System.out.println("1. RSW");
                System.out.println("2. RDS");
                System.out.println("3. RIT ");
                System.out.print("\nSelect your class: ");
                userInputClass = scanner.nextInt();
                scanner.nextLine();

                if (userInputClass < 1 || userInputClass > 3) {
                    System.out.print("Invalid input, please try again.\n");
                } else {
                    counter++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid type of input, please try again.");
                scanner.nextLine(); // to consume the input token (prevent infinite loops) 
            }
        }

        String userClassSelection = null;

        switch (userInputClass) {
            case 1:
                userClassSelection = "RSW";
                break;
            case 2:
                userClassSelection = "RDS";
                break;
            case 3:
                userClassSelection = "RIT";
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        Voter voter = new Voter(userInputName, userInputId, userInputPw, userGenderSelection, userClassSelection);

        voterList.add(voter);
        FileHandler.writeVote((LinkedList<Voter>) voterList, voteFileName);
    }

    public void loginVoter() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;
        String voteFileName = "vote.txt";
        voterList = FileHandler.readVoteFile(voteFileName);

        String userInputPw;
        String userInputId;

        System.out.println("\t\t\t\t==========");
        System.out.println("\t\t\t\tLogin Form");
        System.out.println("\t\t\t\t==========");
        do {

            System.out.print("\nEnter your ID: ");
            userInputId = scanner.nextLine();

            System.out.print("Enter Password: ");
            userInputPw = scanner.nextLine();

            Voter v = new Voter();
            if (!voterList.isEmpty()) {

                for (int i = 1; i <= voterList.getNumberOfEntries(); i++) {

                    if (!voterList.getEntry(i).getId().equals(userInputId) || !voterList.getEntry(i).getPassword().equals(userInputPw)) {
                        isValid = false;

                        //break;
                    } else {
                        v = voterList.getEntry(i);
                        isValid = true;
                        break;
                    }
                }

                if (isValid == true) {
                    System.out.println("\nSuccessful login!");
                    loggedInMenu(v);
                    break;
                } else {
                    System.out.println("\nWrong ID or password, please try again.");
                }
            } else {
                System.out.println("No data in system yet.");
            }

        } while (!isValid);

    }

    public void viewDetails(Voter i) {
        System.out.println("Name: " + i.getName());
        System.out.println("Student ID: " + i.getId());
        System.out.println("Class: " + i.getStudClass());
        System.out.println("Gender: " + i.getGender());
        System.out.println("Voting Status: " + i.getStatus());
        loggedInMenu(i);
    }

    public void updateName(Voter i) {
        Scanner scanner = new Scanner(System.in);
        if (!checkVoted(i)) {
            System.out.println("Old Name: " + i.getName());
            System.out.print("Enter new name: ");

            String userInputName = scanner.nextLine();

            i.setName(userInputName);

            System.out.println("Successfully updated!");
            System.out.println("New Name: " + i.getName());
            loggedInMenu(i);
        } else {
            System.out.println("Unable to update name since you have already voted.");
        }
    }

    public void updatePassword(Voter i) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Old Password: " + i.getPassword());
        System.out.print("Enter new password: ");

        scanner.nextLine();
        String userInputPw = scanner.nextLine();

        i.setPassword(userInputPw);

        System.out.println("Successfully updated!");
        System.out.println("New Password: " + i.getPassword());
        loggedInMenu(i);
    }

    public boolean checkVoted(Voter i) {
        return i.getStatus().equals("VOTED");
    }

    public void loggedInMenu(Voter i) {
        Scanner scanner = new Scanner(System.in);
        Voting voting = new Voting();
        int userInputClass = 0;
        int counter = 0;
        while (counter == 0) {
            try {
                System.out.println("\t\t\t\t==============");
                System.out.println("\t\t\t\tLogged In Menu");
                System.out.println("\t\t\t\t==============");

                System.out.println("\nAvailable options:");
                System.out.println("1. Vote ");
                System.out.println("2. View User Details");
                System.out.println("3. Update Name");
                System.out.println("4. Update Password");
                System.out.println("5. Exit");

                System.out.print("\nSelect an option: ");
                userInputClass = scanner.nextInt();
                scanner.nextLine();

                if (userInputClass < 1 || userInputClass > 5) {
                    System.out.print("Invalid input, please try again.\n");
                } else {
                    counter++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid type of input, please try again.");
                scanner.nextLine(); // to consume the input token (prevent infinite loops) 
            }
        }

        switch (userInputClass) {
            case 1:
                voting.voting(i);
                break;
            case 2:
                viewDetails(i);
                break;
            case 3:
                updateName(i);
                break;
            case 4:
                updatePassword(i);
                break;
            case 5:
                System.out.println("Exit to main menu.");
                break;
            default:
                System.out.println("");
                break;
        }
    }

    @Override
    public String toString() {
        return "Voter{" + "status=" + status + ", gender=" + gender + ", studClass=" + studClass + ", votedId=" + votedId + '}';
    }

}
