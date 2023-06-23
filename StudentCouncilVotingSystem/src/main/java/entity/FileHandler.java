package entity;

import adt.ArrayList;
import adt.LinkedList;
import adt.LinkedListInterface;
import java.io.*;

public class FileHandler {

//Method to check existence of the file
    public static boolean chkfile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File Not Found. Creating new file...");
            try {
                if (file.createNewFile()) {
                    System.out.println("File Created");
                    return false;
                } else {
                    return true;
                }

            } catch (IOException ex) {
                System.out.println("An error occurred while creating the file. Continue to the next process without saving file...");
                return false;
            }
        }
        return true;
    }

    // method to write ArrayList of Candidate objects into a file
    public static void writeFile(ArrayList<Candidate> list, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
            System.out.println("Candidate information is saved to " + fileName + " successfully!");
        } catch (IOException e) {
            System.out.println("Error writing ArrayList of Candidate objects to " + fileName + "!");
        }
    }

    // method to read file and store contents into an ArrayList of Candidate objects
    public static ArrayList<Candidate> readFile(String fileName) {
        ArrayList<Candidate> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.length() == 0) {
            System.out.println("The candidate file is empty.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (ArrayList<Candidate>) ois.readObject();
                ois.close();
                fis.close();
                System.out.println("Candidate data read from " + fileName + " successfully!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading Candidate data from " + fileName + "!");
                System.out.println("The data of the candidate will be overwritten.");
            }

        }
        return list;
    }
// method to write contents of LinkedList of the Voter objects in to file   

    public static void writeVote(LinkedList<Voter> list, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
            System.out.println("Vote infomaion is saved to " + fileName + " successfully!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error writing vote to " + fileName + "!");
        }
    }

// method to read file and store contents into an LinkedList of Voter objects
    public static LinkedListInterface<Voter> readVoteFile(String fileName) {
        FileHandler.chkfile(fileName);
        LinkedList<Voter> list = new LinkedList<>();
        File file = new File(fileName);
        if (file.length() == 0) {
            System.out.println("The vote file is empty.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (LinkedList<Voter>) ois.readObject();
                ois.close();
                fis.close();
                System.out.println("Vote data read from " + fileName + " successfully!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading Vote data from " + fileName + "!");
                System.out.println("The data of the vote will be overwritten.");
            }

        }
        return list;
    }

    public static Admin readAdmin(String fileName) {
        Admin admin = new Admin();
        File file = new File(fileName);
        if (file.length() == 0) {
            System.out.println("The Admin file is empty.");
        } else {
            try {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                admin = (Admin) ois.readObject();
                ois.close();
                fis.close();
                System.out.println("Admin data read from " + fileName + " successfully!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading Admin data from " + fileName + "!");
            }
        }
        return admin;
    }

    public static void writeStatus(Admin admin, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(admin);
            oos.close();
            fos.close();
            System.out.println("Poll status saved to " + fileName + " successfully!");
        } catch (IOException e) {
            System.out.println("Error writing Poll status to " + fileName + "!");
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
