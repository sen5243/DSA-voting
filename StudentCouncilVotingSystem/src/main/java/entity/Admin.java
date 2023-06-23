package entity;

import adt.ArrayList;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin implements Serializable {

    private String id = "admin"; //admin login id
    private String password = "123"; //admin login password
    private String pollStatus = "Pending";

//ArrayList for storing candidates
    private static ArrayList<Candidate> candidate = new ArrayList();

//Files for storing information
    String fileName = "candidate.txt";
    String adminFileName = "admin.txt";
    String voterFileName = "vote.txt";

    public static ArrayList<Candidate> getCandidate() {
        return candidate;
    }

    public static void setCandidate(ArrayList<Candidate> candidate) {
        Admin.candidate = candidate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPollStatus() {
        return pollStatus;
    }

    public void setPollStatus(String pollStatus) {
        this.pollStatus = pollStatus;
    }

    public void readCandidate() {
        FileHandler.chkfile(fileName);
        candidate = FileHandler.readFile(fileName);

    }

//write the pollStatus from admin file to the admin
    public void readPollStatus() {
        FileHandler.chkfile(adminFileName);
        pollStatus = FileHandler.readAdmin(adminFileName).getPollStatus();
    }

//change id of a specific candidate and update it into the candidate file using replace method of the arrayList
    public void changeId(Candidate c, int index) {
        Scanner sc = new Scanner(System.in);
        FileHandler f = new FileHandler();
        boolean isValid = true;
        String id = "";
        do {
            System.out.print("Please Enter Candidate ID: ");
            id = sc.nextLine().trim();

            if (id.length() != 7) {
                System.out.println("The ID should be 7 characters long.\n");
                isValid = false;

            } else {
                for (int i = 0; i < id.length(); i++) {

                    if (!Character.isDigit(id.charAt(i))) {
                        System.out.println("The ID should consists only digit.\n");
                        isValid = false;
                        break;
                    }
                    isValid = true;
                }

                for (Candidate can : candidate) {
                    if (id.equals(can.getId())) {
                        System.out.println("The entered ID already exists.\n");
                        isValid = false;
                        break;
                    }
                }
            }

        } while (!isValid);

        if (isValid) {
            c.setId(id);
            candidate.replace(index, c);
            f.writeFile(candidate, fileName);
        }
    }

//change name of a specific candidate and update it into the candidate file using replace method of the arrayList
    public void changeName(Candidate c, int index) {
        Scanner sc = new Scanner(System.in);
        FileHandler f = new FileHandler();

        System.out.print("Please Enter Candidate Name: ");
        String name = sc.nextLine().trim().toUpperCase();

        c.setName(name);
        candidate.replace(index, c);
        f.writeFile(candidate, fileName);

    }

//change class name of a specific candidate and update it into the candidate file using replace method of the arrayList
    public void changeClassName(Candidate c, int index) {
        Scanner sc = new Scanner(System.in);
        FileHandler f = new FileHandler();
        boolean isValid = true;
        int classOption = 0;
        String className = "";
        do {
            System.out.println("Please Select Your Class\n");
            System.out.println("1.RSW");
            System.out.println("2.RSD");
            System.out.println("3.RIT");
            System.out.print(">>");
            try {
                classOption = sc.nextInt();

            } catch (Exception e) {
                System.out.println("The option should be in digit.\n");
                isValid = false;
                sc.next(); // clear buffer

            }

            switch (classOption) {
                case 1:
                    className = "RSW";
                    isValid = true;
                    break;
                case 2:
                    className = "RSD";
                    isValid = true;
                    break;
                case 3:
                    className = "RIT";
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input. Please Try Again.\n");
                    isValid = false;
            }

        } while (classOption < 1 || classOption > 3 || !isValid);

        if (isValid) {
            c.setClassName(className);
            candidate.replace(index, c);
            f.writeFile(candidate, fileName);
        }
    }

//Menu for the update operations
    public void updateMenu(Candidate c, int index) {
        FileHandler f = new FileHandler();
        Scanner sc = new Scanner(System.in);
        String id = null, name = null, className = null;
        boolean exit = false;
        int classOption = 0;
        do {
            int counter = 0, option = 0;
            System.out.println("======================================");
            System.out.println("|         Candidate Info              |");
            System.out.println("======================================");
            System.out.println("ID: " + c.getId());
            System.out.println("Name: " + c.getName());
            System.out.println("Class: " + c.getClassName());
            System.out.println("======================================");
            System.out.println("");
            System.out.println("======================================");
            System.out.println("|         Update Option               |");
            System.out.println("======================================");
            System.out.println("|      1)Change ID                    |");
            System.out.println("|      2)Change Name                  |");
            System.out.println("|      3)Change Class                 |");
            System.out.println("|      4)Back to Main Menu            |");
            System.out.println("======================================");

            while (counter == 0) {
                try {
                    System.out.print("Enter your option : ");
                    option = sc.nextInt();
                    if (option < 1 || option > 4) {
                        System.out.println("Invalid Input, Please enter again.\n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input Type. Please Try Again.\n");
                    sc.next(); //consume the input token to prevent infinite loop
                }
            }

            switch (option) {
                case 1:
                    changeId(c, index);
                    break;
                case 2:
                    changeName(c, index);
                    break;
                case 3:
                    changeClassName(c, index);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
    }

//Method to call the updateMenu, search and identify the candidate using candidate id
    public void updateCandidate() {
        Scanner sc = new Scanner(System.in);
        Candidate c = new Candidate();
        int foundCounter = 0;

        if (!pollStatus.equals("Pending")) {
            if (pollStatus.equals("Open")) {
                System.out.println("The poll is stated, unable to update candidates.\n");
            } else if (pollStatus.equals("Close")) {
                System.out.println("The poll is ended, unable to update candidates.\n");
            }
        } else {
            boolean isValid = true;
            do {
                System.out.print("Please Enter Candidate ID: ");
                id = sc.nextLine().trim();

                if (id.length() != 7) {
                    System.out.println("The ID should be 7 characters long.\n");
                    isValid = false;

                } else {
                    for (int i = 0; i < id.length(); i++) {

                        if (!Character.isDigit(id.charAt(i))) {
                            System.out.println("The ID should consists only digit.\n");
                            isValid = false;
                            break;
                        }
                        isValid = true;
                    }

                    for (int i = 1; i <= candidate.getNumberOfEntries(); i++) {
                        if (id.equals(candidate.getEntry(i).getId())) {
                            System.out.println("Candidate Found.\n");
                            isValid = true;
                            foundCounter++;
                            c = candidate.getEntry(i);
                            updateMenu(c, i);
                            break;
                        }
                    }
                    if (foundCounter == 0) {
                        System.out.println("Candidate not found.\n");
                    }
                }

            } while (!isValid);

        }

    }

//Method to delete a candidate from the list using candidate id, the list will be updated in the file
    public void deleteCandidate() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        int foundCount = 0, index = 0;
        FileHandler f = new FileHandler();
        do {
            System.out.print("Please Enter Candidate ID: ");
            String id = sc.nextLine().trim();

            if (id.length() != 7) {
                System.out.println("The ID should be 7 characters long.\n");
                isValid = false;

            } else {
                for (int i = 0; i < id.length(); i++) {

                    if (!Character.isDigit(id.charAt(i))) {
                        System.out.println("The ID should consists only digit.\n");
                        isValid = false;
                        break;
                    }
                    isValid = true;
                }

                for (int i = 1; i <= candidate.getNumberOfEntries(); i++) {
                    if (id.equals(candidate.getEntry(i).getId())) {
                        System.out.println("======================================");
                        System.out.println("|        Candidate Infomation         |");
                        System.out.println("======================================\n");
                        System.out.println(candidate.getEntry(i).toString());
                        isValid = true;
                        index = i;
                        foundCount++;
                        break;
                    }
                }
                if (foundCount == 0) {
                    System.out.println("Candidate not found.\n");
                }
                if (foundCount != 0) {
                    System.out.print("Do you really want to delete this candidate? (Y/N) :");
                    char confirm = sc.next().toUpperCase().charAt(0);

                    if (confirm == 'Y') {
                        candidate.remove(index);
                        System.out.println("Candidate removed successfully...\n");
                        f.writeFile(candidate, fileName);
                        isValid = true;

                    } else if (confirm == 'N') {
                        System.out.println("Returning to menu...\n");
                        isValid = true;
                    } else {
                        System.out.println("Invalid input. Returning to id input session.\n");
                        isValid = false;
                    }
                }

            }

        } while (!isValid);
    }

//Main menu of the admin module
    public void adminMenu() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        FileHandler.writeStatus(this, adminFileName);
        do {
            readCandidate();
            readPollStatus();
            int counter = 0, option = 0;
            System.out.println("======================================");
            System.out.println("|         Admin Menu                  |");
            System.out.println("======================================");
            System.out.println("|      1)Add Candidate                |");
            System.out.println("|      2)Search Candidate             |");
            System.out.println("|      3)View all candidate           |");
            System.out.println("|      4)Update candidate             |");
            System.out.println("|      5)Open/Close Poll              |");
            System.out.println("|      6)View poll report             |");
            System.out.println("|      7)Delete Candidate             |");
            System.out.println("|      8)Back to Main Menu            |");
            System.out.println("======================================");

            while (counter == 0) {
                try {
                    System.out.print("Enter your option : ");
                    option = sc.nextInt();
                    if (option < 1 || option > 8) {
                        System.out.println("Invalid Input, Please enter again.\n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input Type. Please Try Again.\n");
                    sc.next(); //consume the input token to prevent infinite loop
                }
            }

            switch (option) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    searchCandidate();
                    break;
                case 3:
                    viewCandidate();
                    break;
                case 4:
                    updateCandidate();
                    break;
                case 5:
                    openClosePoll();
                    break;
                case 6:
                    if (pollStatus.equals("Pending")) {
                        System.out.println("The poll is not started yet.\n");
                    } else if (pollStatus.equals("Open")) {
                        System.out.println("The poll is not ended yet.\n");
                    } else {
                        Analysis a = new Analysis();
                        a.analysis();
                    }
                    break;
                case 7:
                    if (!candidate.isEmpty() && pollStatus.equals("Pending")) {
                        deleteCandidate();
                    } else if (pollStatus.equals("Open")) {
                        System.out.println("The poll is started, unable to delete candidate.\n");
                    } else if (pollStatus.equals("Close")) {
                        System.out.println("The poll is ended, unable to delete candidate.\n");
                    } else {
                        System.out.println("There are no candidates yet.\n");
                    }
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);

    }

//Login method for the admin menu, the admin must be logged in before accessing the admin menu
    public boolean login() {
        Scanner sc = new Scanner(System.in);
        String input, pass;
        System.out.print("Please Enter Your ID: ");
        input = sc.nextLine();
        System.out.print("Please Enter Your Password: ");
        pass = sc.nextLine();

        if (input.equals(id)) {
            if (pass.equals(password)) {
                System.out.println("Login Successfully.\n");
                adminMenu();
                return true;
            } else {
                System.out.println("User Not Found.\n");

            }

        } else {
            System.out.println("User Not Found.\n");

        }
        return false;
    }

//Operation for adding candidate, the candidates will be added in the arrayList and updated to the file
    public void addCandidate() {

        Scanner sc = new Scanner(System.in);
        String id = null, name = null, className = null;
        Candidate c = new Candidate();
        int classOption = 0;
        if (!pollStatus.equals("Pending")) {
            if (pollStatus.equals("Open")) {
                System.out.println("The poll is started, unable to add new candidates.\n");
            } else if (pollStatus.equals("Close")) {
                System.out.println("The poll is ended, unable to add new candidates.\n");
            }
        } else {
            boolean isValid = true;
            System.out.printf("\n\n================================================================================================================================\n");
            System.out.printf("                                                    ACCOUNT REGISTRATION                                                        \n");
            System.out.printf("================================================================================================================================\n");
            System.out.println("-------------------PLEASE FILL IN YOUR PERSONAL INFORMATION-------------------");

            do {
                System.out.print("Please Enter Candidate ID: ");
                id = sc.nextLine().trim();

                if (id.length() != 7) {
                    System.out.println("The ID should be 7 characters long.\n");
                    isValid = false;

                } else {
                    for (int i = 0; i < id.length(); i++) {

                        if (!Character.isDigit(id.charAt(i))) {
                            System.out.println("The ID should consists only digit.\n");
                            isValid = false;
                            break;
                        }
                        isValid = true;
                    }

                    for (Candidate can : candidate) {
                        if (id.equals(can.getId())) {
                            System.out.println("The entered ID already exists.\n");
                            isValid = false;
                            break;
                        }
                    }
                }

            } while (!isValid);

            System.out.print("Please Enter Candidate Name: ");
            name = sc.nextLine().trim().toUpperCase();

            do {
                System.out.println("Please Select Your Class");
                System.out.println("1.RSW");
                System.out.println("2.RSD");
                System.out.println("3.RIT");
                System.out.print(">>");
                try {
                    classOption = sc.nextInt();

                } catch (Exception e) {
                    System.out.println("The option should be in digit.\n");
                    isValid = false;
                    sc.next(); // clear buffer

                }

                switch (classOption) {
                    case 1:
                        className = "RSW";
                        isValid = true;
                        break;
                    case 2:
                        className = "RSD";
                        isValid = true;
                        break;
                    case 3:
                        className = "RIT";
                        isValid = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please Try Again.\n");
                        isValid = false;
                }

            } while (classOption < 1 || classOption > 3 || !isValid);

            c.setId(id);
            c.setName(name);
            c.setClassName(className);

            candidate.add(new Candidate(id, name, className));
            FileHandler.writeFile(candidate, fileName);

        }
    }

//Search a candidate in the list using its id by looping through the list
    public void idSearch() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        int foundCount = 0;
        do {
            System.out.print("Please Enter Candidate ID: ");
            String id = sc.nextLine().trim();

            if (id.length() != 7) {
                System.out.println("The ID should be 7 characters long.\n");
                isValid = false;

            } else {
                for (int i = 0; i < id.length(); i++) {

                    if (!Character.isDigit(id.charAt(i))) {
                        System.out.println("The ID should consists only digit.\n");
                        isValid = false;
                        break;
                    }
                    isValid = true;
                }

                for (Candidate c : candidate) {
                    if (id.equals(c.getId())) {
                        System.out.println("======================================");
                        System.out.println("|        Candidate Infomation         |");
                        System.out.println("======================================\n");
                        System.out.println(c.toString());
                        isValid = true;
                        foundCount++;
                        break;
                    }
                }
                if (foundCount == 0) {
                    System.out.println("Candidate not found.\n");
                }

            }

        } while (!isValid);
    }

//Search for a candidate in the list by using the complete information of the candidate using contains() method (sequential search) of the arrayList, and the equals method overwritten in the candidate class.
    public void detailSearch() {
        Scanner sc = new Scanner(System.in);
        String id = null, name = null, className = null;
        Candidate c = new Candidate();
        FileHandler f = new FileHandler();
        int classOption = 0;
        boolean isValid = true;

        do {
            System.out.print("Please Enter Candidate ID: ");
            id = sc.nextLine().trim();

            if (id.length() != 7) {
                System.out.println("The ID should be 7 characters long.\n");
                isValid = false;

            } else {
                for (int i = 0; i < id.length(); i++) {

                    if (!Character.isDigit(id.charAt(i))) {
                        System.out.println("The ID should consists only digit.\n");
                        isValid = false;
                        break;
                    }
                    isValid = true;
                }
            }

        } while (!isValid);

        System.out.print("Please Enter Candidate Name: ");
        name = sc.nextLine().trim().toUpperCase();

        do {
            System.out.println("Please Select Your Class");
            System.out.println("1.RSW");
            System.out.println("2.RSD");
            System.out.println("3.RIT");
            System.out.print(">>");
            try {
                classOption = sc.nextInt();

            } catch (Exception e) {
                System.out.println("The option should be in digit.\n");
                isValid = false;
                sc.next(); // clear buffer

            }

            switch (classOption) {
                case 1:
                    className = "RSW";
                    isValid = true;
                    break;
                case 2:
                    className = "RSD";
                    isValid = true;
                    break;
                case 3:
                    className = "RIT";
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input. Please Try Again.\n");
                    isValid = false;
            }

        } while (classOption < 1 || classOption > 3 || !isValid);

        c.setId(id);
        c.setName(name);
        c.setClassName(className);

        if (candidate.contains(c)) {
            System.out.println("Candidate Found.");
            System.out.println("======================================");
            System.out.println("|        Candidate Infomation         |");
            System.out.println("======================================\n");
            System.out.println(c.toString());
            System.out.println("======================================\n");
        } else {
            System.out.println("Candidate Not Found.\n");
        }
    }

//List down the candidates in the given className, the array is looped using for each loop which is allowed by using the iterator
    public void classSearch() {
        Scanner sc = new Scanner(System.in);
        FileHandler f = new FileHandler();
        int classOption = 0, foundCount = 0;
        ArrayList<Candidate> found = new ArrayList<>();
        String className = null;
        boolean isValid = true;

        do {
            System.out.println("Please Select Your Class");
            System.out.println("1.RSW");
            System.out.println("2.RSD");
            System.out.println("3.RIT");
            System.out.print(">>");
            try {
                classOption = sc.nextInt();

            } catch (Exception e) {
                System.out.println("The option should be in digit.\n");
                isValid = false;
                sc.next(); // clear buffer

            }

            switch (classOption) {
                case 1:
                    className = "RSW";
                    isValid = true;
                    break;
                case 2:
                    className = "RSD";
                    isValid = true;
                    break;
                case 3:
                    className = "RIT";
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input. Please Try Again.\n");
                    isValid = false;
            }

        } while (classOption < 1 || classOption > 3 || !isValid);

        for (Candidate c : candidate) {
            if (className.equals(c.getClassName())) {
                found.add(c);
                foundCount++;
            }
        }

        if (foundCount == 0) {
            System.out.println("Result not found.\n");
        } else {
            System.out.println("======================================");
            System.out.println("|        Candidate Infomation         |");
            System.out.println("======================================\n");
            for (Candidate c : found) {
                System.out.println(c.toString());
            }
        }
    }

//Search menu 
    public void searchCandidate() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        do {
            int counter = 0, option = 0;
            System.out.println("======================================");
            System.out.println("|         Search Candidate           |");
            System.out.println("======================================");
            System.out.println("|      1)Search by ID                |");
            System.out.println("|      2)Detail Search               |");
            System.out.println("|      3)Search By Class             |");
            System.out.println("|      4)Return To Menu              |");
            System.out.println("======================================");

            while (counter == 0) {
                try {
                    System.out.print("Enter your option : ");
                    option = sc.nextInt();
                    if (option < 1 || option > 4) {
                        System.out.println("Invalid Input, Please enter again.\n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input Type. Please Try Again.\n");
                    sc.next(); //consume the input token to prevent infinite loop
                }
            }

            switch (option) {
                case 1:
                    idSearch();
                    break;
                case 2:
                    detailSearch();
                    break;
                case 3:
                    classSearch();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
    }

    public void openClosePoll() {

        readPollStatus();
        switch (pollStatus) {
            case "Pending":
                pollStatus = "Open";
                FileHandler.writeStatus(this, adminFileName);
                System.out.println("The poll is started.\n");
                break;
            case "Open":
                pollStatus = "Close";
                FileHandler.writeStatus(this, adminFileName);
                System.out.println("The poll is ended.\n");
                break;
            case "Close":
                pollStatus = "Pending";
                FileHandler.writeStatus(this, adminFileName);
                candidate.clear(); // the candidate list reset after new poll started
                System.out.println("A new poll is pending. The old candidate and voter file will be cleared\n");
                FileHandler.deleteFile(fileName);
                FileHandler.deleteFile(voterFileName);
                break;
            default:
                break;
        }
    }

//View all the candidates in the list sorted by candidate ID in either Ascending or Descending order using bubble sort
    public void viewCandidate() {

        Scanner sc = new Scanner(System.in);
        Candidate[] cArray = new Candidate[candidate.size()];
        int counter = 0, option = 0;
        ArrayList<String> id = new ArrayList();
        System.out.println("======================================");
        System.out.println("|         View  Candidate             |");
        System.out.println("|      1)Sorted by ID (Ascending)     |");
        System.out.println("|      2)Sorted by ID (Descending)    |");
        System.out.println("======================================");

        for (Candidate c : candidate) {
            id.add(c.getId());
        }
        while (counter == 0) {
            try {
                System.out.print("Enter your option : ");
                option = sc.nextInt();
                if (option < 1 || option > 3) {
                    System.out.print("Invalid Input, Please enter again.\n");
                } else {
                    counter++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input Type. Please Try Again.\n");
                sc.next(); //consume the input token to prevent infinite loop
            }
        }

        switch (option) {
            case 1:
                if (!candidate.isEmpty()) {
                    candidate.bubbleSort(candidate.toArray(cArray), candidate.size()); //sort the array
                    for (int i = 1; i <= candidate.getNumberOfEntries(); i++) {
                        candidate.replace(i, cArray[i - 1]);
                    } //sort the list by replacing the value of sorted array
                    System.out.println("======================================");
                    System.out.println("|         Candidate Info              |");
                    System.out.println("======================================");
                    for (Candidate c : candidate) {
                        System.out.println(c.toString()); //display candidate info from list
                    }
                } else {
                    System.out.println("There are no candidates available yet.\n");
                }

                break;

            case 2:
                if (!candidate.isEmpty()) {
                    candidate.reversedBubbleSort(candidate.toArray(cArray), candidate.size()); //sort the array
                    for (int i = 1; i <= candidate.getNumberOfEntries(); i++) {
                        candidate.replace(i, cArray[i - 1]);
                    } //sort the list by replacing the value of sorted array
                    System.out.println("======================================");
                    System.out.println("|         Candidate Info              |");
                    System.out.println("======================================");
                    for (Candidate c : candidate) {
                        System.out.println(c.toString()); //display candidate info from list
                    }
                } else {
                    System.out.println("There are no candidates available yet.\n");
                }
                break;
            default:
                break;
        }

    }
}
