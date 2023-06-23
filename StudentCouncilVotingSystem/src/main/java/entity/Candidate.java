package entity;

import java.io.Serializable;

public class Candidate implements Serializable, Comparable<Candidate> {

    private String id;
    private String name;
    private String className;
    private int voteCount = 0;

    public Candidate() {
    }

    public Candidate(String id, String name, String className) {
        this.id = id;
        this.name = name;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + "\nClass: " + className + "\nVote Count: " + voteCount + "\n\n";
    }

    @Override
    public int compareTo(Candidate otherCandidate) {
        int result = id.compareTo(otherCandidate.id);
        return result;
    }

    @Override
    public boolean equals(Object obj) { //comparing the candidate id, name and classname
        if (!(obj instanceof Candidate)) {
            return false;
        }
        Candidate c = (Candidate) obj;
        return this.id.equals(c.getId()) && this.name.equals(c.getName()) && this.className.equals(c.getClassName());
    }

}
