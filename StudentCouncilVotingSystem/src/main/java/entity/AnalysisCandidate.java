/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.LinkedListInterface;
import adt.SortedLinkedList;
import adt.SortedListInterface;

/**
 *
 * @author Dextor
 */
public class AnalysisCandidate implements Comparable<AnalysisCandidate> {

    private String name;
    private String id;
    private String className;
    private int totalVote;
    private String voteFileName = "vote.txt";
    LinkedListInterface<Voter> v = FileHandler.readVoteFile(voteFileName);

    public AnalysisCandidate() {

    }

    public AnalysisCandidate(String name, String id, String className, int totalVote) {
        this.name = name;
        this.id = id;
        this.className = className;
        this.totalVote = totalVote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public double calculatePercentage(int grandTotal) {
        return (double) totalVote / grandTotal;
    }

@Override
    public String toString() {
        return String.format("%-12s%-10s%-3d", id, name, totalVote);
    }

    public int calculateMaleVotes() {
        int count = 0;
         
        for (int i = 1; i <= v.getNumberOfEntries(); i++) {
            if (v.getEntry(i).getStatus().equals("VOTED")) {
                if ((this.getId().compareTo(v.getEntry(i).getVotedId()) == 0) && (v.getEntry(i).getGender().compareTo("M") == 0)) {
                    count++;
                }
            }

        }
        return count;
    }

    public int calculateFemaleVotes() {
       int count = 0;
         
        for (int i = 1; i <= v.getNumberOfEntries(); i++) {
            if (v.getEntry(i).getStatus().equals("VOTED")) {
                if ((this.getId().compareTo(v.getEntry(i).getVotedId()) == 0) && (v.getEntry(i).getGender().compareTo("F") == 0)) {
                    count++;
                }
            }

        }
        return count;
    }

    public int calculateClassVotes(String className) {
        int count = 0;

        for (int i = 1; i <= v.getNumberOfEntries(); i++) {
            if (v.getEntry(i).getStatus().equals("VOTED")) {
                if ((this.getId().compareTo(v.getEntry(i).getVotedId()) == 0) && (v.getEntry(i).getStudClass().compareTo(className) == 0)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int compareTo(AnalysisCandidate o) {
        return (int) (this.totalVote - o.totalVote);
    }
}
