/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.ArrayStack;
import adt.LinkedList;
import adt.LinkedListInterface;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author User
 */
// CASTING VOTE CLASS  ///
public class Voting {

    private static ArrayStack<Candidate> voteStack2 = new ArrayStack<>();
    private static ArrayStack<Candidate> voteStack3 = new ArrayStack<>();
    private static ArrayStack<Voter> stack = new ArrayStack<>();
    private static ArrayList<Candidate> candiList2 = Admin.getCandidate();

    private static String fileName = "candidate.txt";
    private static String voteFileName = "vote.txt";
    private static LinkedListInterface<Voter> voterList2 = Voter.getVoterList();
    static FileHandler f = new FileHandler();

    public Voting() {
    }

    public void voting(Voter voter) {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        Voter voterSub = new Voter();
        voterList2 = FileHandler.readVoteFile(voteFileName);
        if (stack.isEmpty()) {
            for (int i = 0; i <= voterList2.getNumberOfEntries(); i++) {  
                stack.push(voterList2.getEntry(i));
            }

        } else if (voterList2.getNumberOfEntries() != stack.topIndex()) {// if equal ,it will straightly go to go=votemenu()

            for (int i = (stack.topIndex() + 1); i <= voterList2.getNumberOfEntries(); i++) { // to make sure the same voter object
                stack.push(voterList2.getEntry(i));                                           // does not push in the stack again

            }

        }
        int go = voteMenu();

        do {
            admin.readPollStatus();
            switch (go) {
                case 1:
                    System.out.println("\n");
                    System.out.println("===========================");
                    System.out.println("         Voting            ");
                    System.out.println("===========================");
                    if (admin.getPollStatus().equals("Close")) { // see admin closepoll function 
                        System.out.println("\nThe Voting has been closed!!");

                    } else if (candiList2.isEmpty()) {
                        System.out.println("There are no candidate available yet.");
                    } else {

                        System.out.println("Your ID: " + voter.getId());
                        

                        Iterator<Voter> iterator = stack.getiterator();  //stack iterator  voter

                        boolean found = false;
                        int i = 0;
                        while (iterator.hasNext()) {
                            Voter checkVoter = iterator.next();
                            i++;
                            if (checkVoter != null && checkVoter.getId().equals(voter.getId())) {
                                System.out.println("\nYour ID in the database.\n");
                                System.out.println("Your details: ");
                                System.out.println("ID: " + checkVoter.getId());
                                System.out.println("Name: " + checkVoter.getName());
                                System.out.println("Gender: " + checkVoter.getGender());
                                System.out.println("Class: " + checkVoter.getStudClass());
                                System.out.println("Voting Status: " + checkVoter.getStatus());

                                String checkStatus = checkVoter.getStatus();

                                if (checkStatus.equals("NOT VOTED")) {
                                    voteHere(checkVoter);

                                    voterList2.getEntry(voterList2.indexOf(checkVoter)).setStatus("VOTED");
                                    checkVoter.setStatus("VOTED");
                                    voter.setStatus("VOTED");
                                    FileHandler.writeVote((LinkedList<Voter>) voterList2, voteFileName);  //for file handling

                                    found = true;
                                    break;

                                } else {
                                    System.out.println("\n");
                                    System.out.println("You already voted. Can't vote anymore!");
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            System.out.println("\nYour ID is not in the database. Please try again!");
                        }

                    }
                    break;
                case 2:
                    System.out.println(admin.getPollStatus());
                    if (admin.getPollStatus().equals("Close")) {

                        String msg = checkWin();
                        System.out.println(msg);
                        //break;
                    } else {
                        System.out.println("\nVote has not been closed yet!!");
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nInvalid input! Chosee between 1 -3 !!");
                    break;
            }

            go = voteMenu();

        } while (go == 1 || go == 2);

        if (go == 3) {

            voterSub.loggedInMenu(voter); // back to voter mneu
        }

    }

    public static int voteMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("===========================");
        System.out.println("       Voting Menu         ");
        System.out.println("===========================");
        System.out.println("1. Cast Vote");
        System.out.println("2. See Winner");
        System.out.println("3. Return to Login Menu");
        System.out.print("\nEnter your choice: ");

        int choice;

        choice = sc.nextInt();
        return choice;

    }

    public static void voteHere(Voter currentVoter) {
        Admin admin = new Admin();
        Iterator<Candidate> iterator = candiList2.iterator();
        Scanner sc = new Scanner(System.in);

        System.out.print("\nCandidates Competing: ");

        if (!candiList2.isEmpty()) {
            for (Candidate c : candiList2) {
                System.out.print("\n" + ("* ") + c.getId() + " " + c.getName() + " " + c.getClassName());
            }
        }

        System.out.print("\nPlease type your selected Candidate ID to vote: ");
        String votes = sc.nextLine().toUpperCase();

        boolean search2 = false;
        int index = 0;

        while (iterator.hasNext()) {
            Candidate checkCandidate = iterator.next();
            index++;
            if (checkCandidate != null && checkCandidate.getId().equals(votes)) {
                if (!voteStack2.contains(checkCandidate)) {
                    voteStack2.push(checkCandidate);
                }
                currentVoter.setVotedId(votes);
                candiList2.getEntry(index).setVoteCount(candiList2.getEntry(index).getVoteCount() + 1);
                FileHandler.writeFile(candiList2, fileName);  //for file handling

                search2 = true;
                System.out.println("\nYou have successfully voted. Thank you!!");
                break;

            }

        }

        for (int i = 1; i <= voterList2.getNumberOfEntries(); i++) {
            if (voterList2.getEntry(i).getId().equals(currentVoter.getId())) {
                voterList2.getEntry(i).setVotedId(votes);
                FileHandler.writeVote((LinkedList<Voter>) voterList2, voteFileName);  //for file handling
                break;
            }
        }
        if (!search2) {
            System.out.println("\nPlease enter the correct Candidate name. Try again!");
            voteHere(currentVoter);
        }

    }

    public String checkWin() {

        System.out.println("\n");
        System.out.println("===========================");
        System.out.println("         Results           ");
        System.out.println("===========================");
        String candidateManageOneVote = anounceWinner();
        System.out.println(candidateManageOneVote);
        
        candiList2 = FileHandler.readFile(fileName);
        for (Candidate c : candiList2) { //loop all the current candidates including file so 
            if (!voteStack2.contains(c)) { // that it won't just compare the voted candidate in single run
                voteStack2.push(c);
            }
        }

        int eachVote = voteStack2.peek().getVoteCount();
        boolean checkTie = false;

        Candidate topCandidate = null;

        while (!voteStack2.isEmpty()) {

            Candidate currentCandidate = voteStack2.pop();
            if (currentCandidate.getVoteCount() > eachVote) {
                topCandidate = currentCandidate;
                eachVote = currentCandidate.getVoteCount();
                checkTie = false;

            } else if (currentCandidate.getVoteCount() == eachVote) {
                topCandidate = null;
                checkTie = true;

            }

        }

        if (topCandidate != null) {
            return ("\n The Winner for the Student Council is : " + topCandidate.getId() + " " + topCandidate.getName());

        } else {
            return ("The winner is not declared because the vote count is tied.");
        }

    }

    public static String anounceWinner() {

        ArrayList<Candidate> candidate = candiList2;
        int i = 0;
        voteStack3.clear();

        for (Candidate c : candidate) { //loop all the candidate including those in file
            if (c.getVoteCount() >= 1) {
                voteStack3.push(c);
            }
        }

        int chosenCandidateSize = voteStack3.size();
        int actualCandidateSize = candiList2.getNumberOfEntries();

        return ("Out of " + actualCandidateSize + " candidates ,there are " + chosenCandidateSize + "  candidates manage to get atleast 1 vote.");

    }

    public static ArrayStack<Voter> getStack() {
        return stack;
    }

    public static ArrayStack<Candidate> getVoteStack2() {
        return voteStack2;
    }

    public static void setVoteStack2(ArrayStack<Candidate> voteStack2) {
        Voting.voteStack2 = voteStack2;
    }

    public static ArrayStack<Candidate> getVoteStack3() {
        return voteStack3;
    }

    public static void setVoteStack3(ArrayStack<Candidate> voteStack3) {
        Voting.voteStack3 = voteStack3;
    }

}
