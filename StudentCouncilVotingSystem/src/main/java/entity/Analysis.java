/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.LinkedListInterface;
import adt.SortedLinkedList;
import adt.SortedListInterface;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Dextor
 */
public class Analysis {

    private static ArrayList<Candidate> oriCandidate = Admin.getCandidate();
    public static SortedListInterface<AnalysisCandidate> aCandidate = new SortedLinkedList<>();
    private String voteFileName = "vote.txt";
    private LinkedListInterface<Voter> aVoter;

    public static SortedListInterface<AnalysisCandidate> getAnalysisCandidateList() {
        return aCandidate;
    }

    public void setAnalysisCandidateList(SortedListInterface<AnalysisCandidate> aCandidate) {
        this.aCandidate = aCandidate;
    }

    public void analysis() {
        aVoter = FileHandler.readVoteFile(voteFileName);
        Scanner sc = new Scanner(System.in);
        if (!aCandidate.isEmpty()) {
            aCandidate.clear();
            System.out.println(oriCandidate.getNumberOfEntries());
        }
        for (int i = 1; i <= oriCandidate.getNumberOfEntries(); i++) {
            AnalysisCandidate ac = new AnalysisCandidate(oriCandidate.getEntry(i).getName(), oriCandidate.getEntry(i).getId(), oriCandidate.getEntry(i).getClassName(), oriCandidate.getEntry(i).getVoteCount());
            aCandidate.add(ac);

        }

        boolean exit = false;
        String buffer;
        do {
            basicReport();
            menu();

            System.out.print(
                    "\nPlease enter your selection: ");
            String select = sc.nextLine();

            switch (select) {
                case "1":
                    daReport();
                    System.out.println("\nPress enter to continue......");
                    buffer = sc.nextLine();
                    break;

                case "2":
                    gaReport();
                    System.out.println("\nPress enter to continue......");
                    buffer = sc.nextLine();
                    break;

                case "3":
                    caReport();
                    System.out.println("\nPress enter to continue......");
                    buffer = sc.nextLine();
                    break;

                case "4":
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid option! Please try again!");
                    System.out.println("Press enter to continue......");
                    buffer = sc.nextLine();
            }
        } while (exit == false);

    }

public int getVotedVoters() {
        int count = 0;

        for (int i = 1; i <= aVoter.getNumberOfEntries(); i++) {
            Voter v = aVoter.getEntry(i);
            if (v.getStatus().equals("VOTED")) {
                count++;
            }
        }

        return count;
    }

    public int getStudentInClass(String className) {
        int count = 0;

        for (int i = 1; i <= aVoter.getNumberOfEntries(); i++) {
            Voter v = aVoter.getEntry(i);
            if (v.getStudClass().equals(className)) {
                count++;
            }
        }

        return count;
    }

    public int getVotedClassMember(String className) {
        int count = 0;
        for (int i = 1; i <= aVoter.getNumberOfEntries(); i++) {
            Voter v = aVoter.getEntry(i);
            if ((v.getStatus().equals("VOTED")) && (v.getStudClass().compareTo(className) == 0)) {
                count++;
            }
        }
        return count;
    }

    public int getMaleVoters() {
        int count = 0;
        for (int i = 1; i <= aVoter.getNumberOfEntries(); i++) {
            Voter v = aVoter.getEntry(i);
            if (v.getGender().compareTo("M") == 0) {
                count++;
            }
        }
        return count;
    }

    public int getFemaleVoters() {
        return aVoter.getNumberOfEntries() - getMaleVoters();
    }

    public int getVotedMaleVoters() {
        int count = 0;
        for (int i = 1; i <= aVoter.getNumberOfEntries(); i++) {
            Voter v = aVoter.getEntry(i);
            if ((v.getStatus().equals("VOTED")) && (v.getGender().compareTo("M") == 0)) {
                count++;
            }
        }
        return count;
    }

    public int getVotedFemaleVoters() {
        return getVotedVoters() - getVotedMaleVoters();
    }

    public void basicReport() {
        System.out.println(
                "\n\n\n\tVoting Analysis Report");
        System.out.println(
                "\t**********************");
        System.out.println(
                "Registered Voters\t\t" + aVoter.getNumberOfEntries());
        System.out.println(
                "Male Voters\t\t\t" + getMaleVoters() + getPercentage(getMaleVoters(), aVoter.getNumberOfEntries()));
        System.out.println(
                "Female Voters\t\t\t" + getFemaleVoters() + getPercentage(getFemaleVoters(), aVoter.getNumberOfEntries()));
        System.out.println(
                "Voters From RSW\t\t\t" + getStudentInClass("RSW") + getPercentage(getStudentInClass("RSW"), aVoter.getNumberOfEntries()));
        System.out.println(
                "Voters From RSD\t\t\t" + getStudentInClass("RDS") + getPercentage(getStudentInClass("RDS"), aVoter.getNumberOfEntries()));
        System.out.println(
                "Voters From RIT\t\t\t" + getStudentInClass("RIT") + getPercentage(getStudentInClass("RIT"), aVoter.getNumberOfEntries()));

        System.out.println(
                "\nVotes Casted\t\t\t" + getVotedVoters() + getPercentage(getVotedVoters(), aVoter.getNumberOfEntries()));
        System.out.println(
                "Absence\t\t\t\t" + (aVoter.getNumberOfEntries() - getVotedVoters()) + getPercentage((aVoter.getNumberOfEntries() - getVotedVoters()), aVoter.getNumberOfEntries()));
        System.out.println(
                "Voted Male Voters\t\t" + getVotedMaleVoters() + getPercentage(getVotedMaleVoters(), getVotedVoters()));
        System.out.println(
                "Voted Female Voters\t\t" + getVotedFemaleVoters() + getPercentage(getVotedFemaleVoters(), getVotedVoters()));
        System.out.println(
                "Votes From RSW\t\t\t" + getVotedClassMember("RSW") + getPercentage(getVotedClassMember("RSW"), getVotedVoters()));
        System.out.println(
                "Votes From RSD\t\t\t" + getVotedClassMember("RDS") + getPercentage(getVotedClassMember("RDS"), getVotedVoters()));
        System.out.println(
                "Votes From RIT\t\t\t" + getVotedClassMember("RIT") + getPercentage(getVotedClassMember("RIT"), getVotedVoters()));
    }

    public void menu() {
        System.out.println(
                "\n\n======================================");
        System.out.println(
                "|          Report Selection          |");
        System.out.println(
                "======================================");
        System.out.println(
                "|     1. Detail Analysis Report      |");
        System.out.println(
                "|     2. Gender Analsysis Report     |");
        System.out.println(
                "|     3. Class Analysis Report       |");
        System.out.println(
                "|     4. Exit                        |");
        System.out.println(
                "======================================");
    }

    public void daReport() {
        Iterator<AnalysisCandidate> iterator = aCandidate.getIterator();

        System.out.println(
                "\n\n\t   Detail Analysis Report");
        System.out.println(
                "\t   **********************");
        System.out.printf("%-5s%-12s%-10s%-15s%-6s\n", "No.", "ID", "Name", "Votes", "Class");

        int i = 1;
        while (iterator.hasNext() && i <= aCandidate.getNumberOfEntries()) {
            System.out.printf("%2d%-3s%-25s%-12s%-6s\n", i, ".", aCandidate.getEntry(i).toString(), getPercentage(aCandidate.getEntry(i).getTotalVote(), getVotedVoters()), aCandidate.getEntry(i).getClassName());
            i++;
        }
    }

    public void gaReport() {
        Iterator<AnalysisCandidate> iterator = aCandidate.getIterator();

        System.out.println(
                "\n\n\t\t\tGender Analysis Report");
        System.out.println(
                "\t\t\t**********************");
        System.out.printf("%-5s%-12s%-10s%-15s%-15s%-13s\n", "No.", "ID", "Name", "Votes", "Male Votes", "Female Votes");

        int i = 1;
        while (iterator.hasNext() && i <= aCandidate.getNumberOfEntries()) {
            System.out.printf("%2d%-3s%-25s%-12s%-3s%-12s%-3s%-12s\n", i, ".", aCandidate.getEntry(i).toString(), getPercentage(aCandidate.getEntry(i).getTotalVote(), getVotedVoters()), aCandidate.getEntry(i).calculateMaleVotes(), getPercentage(aCandidate.getEntry(i).calculateMaleVotes(), getVotedMaleVoters()), aCandidate.getEntry(i).calculateFemaleVotes(), getPercentage(aCandidate.getEntry(i).calculateFemaleVotes(), getVotedFemaleVoters()));
            i++;
        }
    }

    public void caReport() {
        Iterator<AnalysisCandidate> iterator = aCandidate.getIterator();

        System.out.println(
                "\n\n\t\t\tClass Analysis Report");
        System.out.println(
                "\t\t\t*********************");
        System.out.printf("%-5s%-12s%-10s%-15s%-15s%-15s%-15s\n", "No.", "ID", "Name", "Votes", "RSW", "RSD", "RIT");

        int i = 1;
        while (iterator.hasNext() && i <= aCandidate.getNumberOfEntries()) {
            System.out.printf("%2d%-3s%-25s%-12s%-3s%-12s%-3s%-12s%-3s%-12s\n", i, ".", aCandidate.getEntry(i).toString(), getPercentage(aCandidate.getEntry(i).getTotalVote(), getVotedVoters()), aCandidate.getEntry(i).calculateClassVotes("RSW"), getPercentage(aCandidate.getEntry(i).calculateClassVotes("RSW"), getVotedClassMember("RSW")), aCandidate.getEntry(i).calculateClassVotes("RDS"), getPercentage(aCandidate.getEntry(i).calculateClassVotes("RDS"), getVotedClassMember("RDS")), aCandidate.getEntry(i).calculateClassVotes("RIT"), getPercentage(aCandidate.getEntry(i).calculateClassVotes("RIT"), getVotedClassMember("RIT")));
            i++;
        }
    }

    public static String getPercentage(int numerator, int denominator) {
        return String.format("(%.2f%2S", ((double) numerator / denominator) * 100, "%)");
    }
}
