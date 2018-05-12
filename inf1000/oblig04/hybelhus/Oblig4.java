import java.util.*;
import java.io.*;

class Oblig4 {
    // Scanner for reading user input
    Scanner input = new Scanner(System.in);
    // String to hold the file name, will not be changed
    final String HOUSEDATA = "hybeldata.txt";
    // String that represent a vacant room
    static final String VACANT = "TOM HYBEL";

    public static void main(String[] args) {
        // Declare a new Oblig4 to access functions
        Oblig4 o = new Oblig4();

        // Declare a new House and feed the program information from file
        House utsyn = new House(o.HOUSEDATA);

        // Start programloop
        o.programloop(utsyn);        
    }

    void programloop(House house) {
        // On startup, write a welcome message
        System.out.println("\nWelcome to the appartment manager!\n");

        int choice = 0;
        while (choice != 8) {
            // Print options
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            writeMenu();
            // Promt user for choice
            System.out.print("\nWhat would you like to do? ");
            choice = input.nextInt();
            input.nextLine();
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            // Send user to appropriate function
            switch (choice) {
                case 1: printOverview(house); break;
                case 2: regNew(house); break;
                case 3: regPayment(house); break;
                case 4: regEmigration(house); break;
                case 5: monthlyPayment(house); break;
                case 6: regEviction(house); break;
                case 7: increaseRent(house); break;
                case 8: break;
                default: System.out.println("Not a valid option!");
            }
        }
        // When option is '8', exit program
        exitProgram(house);
    }

    void printOverview(House house) {
        // Print the top row of the overview
        System.out.println("\nRoom  Tenant               Balance");
        System.out.println("----- -------------------- -----------");
        
        // For every appartment in every floor, print info
        for (Floor floor : house.floors) {
            for (Appartment appartment : floor.appartments) {
                System.out.printf("%-5s %-20s %-3s%8d\n", appartment.appName, 
                                 (appartment.vacant) ? "( VACANT )" : appartment.tenant.name, 
                                  "kr ", appartment.tenant.accBalance);
            }
        }

        // Print bottom line of the overview
        System.out.printf("\nMonth/Year:\t%d/%d, %d Months in business\n", house.currMonth, house.currYear, house.totalMonths);
        System.out.printf("Total profit:\t%d kr\n\n",house.totalProfit);
    } // End printOverview

    void regNew(House house) {
        // False until vacant room found
        boolean anyVacant = false;

        System.out.println("\nVacant appartments:");
        // Print all vacant appartments with monthly rent
        for (Floor floor : house.floors) {
            for (Appartment appartment : floor.appartments) {
                if(appartment.vacant) {
                    System.out.printf("%-5s kr %5d/month \n", appartment.appName, getRent(house, appartment));
                    // If a vacant appartment is found, make true
                    anyVacant = true;
                }
            }
        }

        // If no vacant appartments where found, go back to programloop
        if(!anyVacant) {
            System.out.println("--- NONE ---");
        } else {
            // Prompt user for appartment name
            System.out.print("\nSelect appartment: ");
            // Find the pointer to the requested appartment, returns null if not found
            Appartment newApp = findAppartment(house);
            if (newApp == null || !newApp.vacant) {
                System.out.println("Appartment does not match options!\n");
            } else {
                // Promt user for name of tenant
                System.out.print("Name of tenant: ");
                String name = input.nextLine();
                // The appartment is no longer vacant
                newApp.vacant = false;
                // Create a Student, add to currIncome
                newApp.tenant = new Student(name, 15000 - getRent(house, newApp));
                house.currIncome += getRent(house, newApp);
                // Print a pretty success message
                System.out.println("--------------------------------------");
                System.out.printf("%-5s %-20s %-3s%8d\n", newApp.appName, newApp.tenant.name, "kr ", newApp.tenant.accBalance);
                System.out.println("--------------------------------------\n");
            }
        }
    } // End regNew

    void regPayment(House house) {
        // Promt user for appartment and amount
        System.out.print("\nSelect appartment: ");
        Appartment currApp = findAppartment(house);
        // Check if appartment has a tenant
        if(currApp == null || currApp.vacant) {
            System.out.println("--- Selected appartment is unavailible! ---\n");
        } else {
            System.out.print("Deposit amount: ");
            int depAmount = input.nextInt();
            input.nextLine();
            // Add amount to accBalance
            currApp.tenant.accBalance += depAmount;

            // Print a message with student info and new account balance
            System.out.println("--------------------------------------");
            System.out.printf("%-5s %-20s %-3s%8d\n", currApp.appName, currApp.tenant.name, "kr ", currApp.tenant.accBalance);
            System.out.println("--------------------------------------\n");
        }
    } // End regPayment

    void regEmigration(House house) {
        System.out.print("\nName of tenant: ");
        Appartment currApp = findStudent(house);
        // Check if appartment has a tenant
        if(currApp == null || currApp.vacant) {
            System.out.println("--- Appartment is vacant or unavailible! ---\n");
        } else if (currApp.tenant.accBalance < 0) {
            System.out.println("--- Your account balance is negative! ---\n");
        } else {
            // Print out remaining account balance, and set the appartment to vacant
            System.out.println(currApp.tenant.name + ", kr " + currApp.tenant.accBalance + " account balance refunded! \n");
            currApp.tenant = new Student(VACANT, 0);
            currApp.vacant = true;
        }
    }

    void monthlyPayment(House house) {
        // Figure out next month/year
        int nextMonth = (house.currMonth + 1 > 12) ? 1 : house.currMonth + 1; 
        int nextYear = (nextMonth == 1) ? house.currYear + 1 : house.currYear;
        
        // Promt user
        System.out.printf("\nCollect payment for month %d/%d (y/n)? ", nextMonth, nextYear);
        char answer = input.nextLine().charAt(0);

        // If answer is not 'y' or 'Y', abort
        if (answer != 'y' && answer != 'Y') {
            System.out.println("--- Aborting monthly payment! ---\n");
        } else {
            // Update month and year, and total months in business
            house.currMonth = nextMonth;
            house.currYear = nextYear;
            house.totalMonths++;

            // Loop through all appartments and 'pay'
            for (Floor floor : house.floors) {
                for (Appartment appartment : floor.appartments) {
                    if(!appartment.vacant) {
                        // Variables to make code readable
                        int appRent = getRent(house, appartment);
                        int appBalance = appartment.tenant.accBalance;
                        
                        // If tenant has enough account balance, full profit, else, get the remaining account balance if it is above 0
                        house.currIncome += (appBalance >= appRent ? appRent : (appBalance <= 0 ? 0 : appBalance));
                        // Subtract rent from account balance
                        appartment.tenant.accBalance -= appRent;
                    }
                    // For every appartment, vacant aswell, owner pays Vedlikehold A/S
                    house.currExpense += 1200;
                }
                // For every floor, owner pays Vedlikehold A/S for the joint rooms
                house.currExpense += 1700;
            }
            // Calculate monthly profit
            int monthlyProfit = (house.currIncome - house.currExpense);
            house.totalProfit += monthlyProfit;

            // Write a message with info on the monthly payment
            System.out.printf("\nMonth/Year:\t%d/%d, %d Months in business\n", house.currMonth, house.currYear, house.totalMonths);
            System.out.printf("Top floor rent:     kr %d \nNormal floor rent:  kr %d \n", house.maxRent, house.minRent);
            System.out.printf("Monthly profit:     kr %d \nTotal profit:       kr %d \n", monthlyProfit, house.totalProfit);
            System.out.printf("Average profit:     kr %d/month\n\n", (house.totalProfit/house.totalMonths));
            house.currIncome = 0;
            house.currExpense = 0;
        }
    }

    void regEviction(House house) {
        System.out.println("\nEvicted students:");
        boolean anyEvicted = false;
        // Search every appartment for students that are too far behind on rent
        for (Floor floor : house.floors) {
            for (Appartment appartment : floor.appartments) {
                if(!appartment.vacant && appartment.tenant.accBalance < getRent(house, appartment)*(-1)) {
                    // Calculate what the student owes
                    int amount = (appartment.tenant.accBalance * (-1)) + 3000;
                    // Kick out tenant, and write info to file and terminal
                    callTorpedo(appartment, amount);
                    // Add to profit, minus the torpedos cut
                    house.totalProfit += amount - 1500;
                    anyEvicted = true;
                }
            }
        }
        if (!anyEvicted) {
            System.out.println("--- NONE ---");    
        }
        System.out.println();
    }

    void callTorpedo(Appartment currApp, int amount) {
        // Make a string with info on evicted student
        String evicted = String.format("%-5s %-20s %-3s%8d", currApp.appName, currApp.tenant.name, "kr ", amount);
        // Print info to terminal
        System.out.println(evicted);
        try {
            FileWriter torpedo = new FileWriter(new File("torpedo.txt"), true);
            // Print info to file
            torpedo.write(evicted + "\n");
            torpedo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set appartment to be vacant
        currApp.vacant = true;
        currApp.tenant = new Student(VACANT, 0);
    }

    void increaseRent(House house) {
        // Print current rent
        System.out.println("\nTop floor rent:    " + house.maxRent);
        System.out.println("Normal floor rent: " + house.minRent);
        
        // Promt user for new rent
        System.out.print("\nNew top floor rent:     ");
        house.maxRent = input.nextInt();
        input.nextLine();
        System.out.print("New normal floor rent:  ");
        house.minRent = input.nextInt();
        input.nextLine();
        
        System.out.println("\nThe rent has been changed!\n");
    }

    void exitProgram(House house) {
        try {
            // Open "hybeldata.txt" to write new data to
            FileWriter save = new FileWriter(new File(HOUSEDATA));
            // Generate the first line of the file
            String firstLine = String.format("%d;%d;%d;%d;%d;%d;", 
                                             house.currMonth, house.currYear,
                                             house.totalProfit, house.totalMonths,
                                             house.minRent, house.maxRent);
            // Write the first line to file
            save.write(firstLine + "\n");
        
            // Loop through all appartments
            for (Floor floor : house.floors) {
                for (Appartment appartment : floor.appartments) {
                    // Generate a string with appartment info
                    String appLine = String.format("%c;%c;%d;%s", 
                                                   appartment.appName.charAt(0), appartment.appName.charAt(1),
                                                   appartment.tenant.accBalance, appartment.tenant.name);
                    // Write info to datafile
                    save.write(appLine + "\n");
                }
            }
            // Save the datafile
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeMenu() {
        // Print out the menu options
        System.out.println("1. Print overview");
        System.out.println("2. Register new tenant");
        System.out.println("3. Register payment from tenant");
        System.out.println("4. Register emigration");
        System.out.println("5. Get monthly payment");
        System.out.println("6. Evict tenant");
        System.out.println("7. Increase rent");
        System.out.println("8. Exit program");
    }

    // Function to find an appartment based on appartment name
    Appartment findAppartment(House house) {
        String searchName = input.nextLine();
        for (Floor floor : house.floors) {
            for (Appartment appartment : floor.appartments) {
                if(appartment.appName.equalsIgnoreCase(searchName)) {
                    return appartment;
                }
            }
        }
        return null;
    }

    // Function to find an appartment based on student name
    Appartment findStudent(House house) {
        String searchName = input.nextLine();
        for (Floor floor : house.floors) {
            for (Appartment appartment : floor.appartments) {
                if(appartment.tenant.name.equalsIgnoreCase(searchName)) {
                    return appartment;
                }
            }
        }
        return null;
    }

    int getRent(House house, Appartment appartment) {
        return (appartment.topRent ? house.maxRent : house.minRent);
    }
} // End Oblig4

class House {
    // House has an array of floors
    Floor[] floors;
    // Number of floors in the House
    int numFloors = 3;
    // Number of appartments in each floor
    int numAppartments = 6;

    // Variables to track from file
    int currMonth;
    int currYear;
    int totalProfit;
    int totalMonths;
    int minRent;
    int maxRent;

    // Other usefull variables
    int currIncome;
    int currExpense;

    House (String filename) {
        try {
            // Read the first lines of the file into the apropriate variables
            Scanner file = new Scanner(new File(filename));
            String[] firstLine = file.nextLine().split(";");
            currMonth = Integer.parseInt(firstLine[0]);
            currYear = Integer.parseInt(firstLine[1]);
            currIncome = 0;
            currExpense = 0;
            totalProfit = Integer.parseInt(firstLine[2]);
            totalMonths = Integer.parseInt(firstLine[3]);
            minRent = Integer.parseInt(firstLine[4]);
            maxRent = Integer.parseInt(firstLine[5]);

            // Make array based on building size
            floors = new Floor[numFloors];
            
            // Fill array with floors
            for (int i = 0; i < numFloors; i++) {
                    floors[i] = new Floor(numAppartments, (i == numFloors-1) ? true : false, i+1);
            }

            // Create new student objects from text file
            while (file.hasNextLine()) {
                String[] currLine = file.nextLine().split(";");
                int floor = Integer.parseInt(currLine[0])-1;
                int room = (int)currLine[1].charAt(0)-65;
                int accBalance = Integer.parseInt(currLine[2]);
                String name = currLine[3];
                floors[floor].appartments[room].tenant = new Student(name, accBalance);
                if(!name.equals(Oblig4.VACANT)) {
                    floors[floor].appartments[room].vacant = false;   
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    } // End constructor
} // End House

class Floor {
    // A Floor has a number of appartments
    Appartment[] appartments;

    Floor (int numAppartments, boolean topRent, int floorNr) {
        // Make array based on floor size
        appartments = new Appartment[numAppartments];

        // Fill array with appartments
        for (int i = 0; i < numAppartments; i++) {
            appartments[i] = new Appartment(topRent, floorNr, i);
        }
    } // End constructor
} // End Floor

class Appartment {
    // An Appartment has a tenant, rent and a name
    Student tenant;
    boolean topRent;
    String appName;
    boolean vacant = true;

    Appartment (boolean topRent, int floorNr, int roomNr) {
        this.topRent = topRent;
        appName = "" + floorNr + (char)(roomNr+65);
    } // End constructor
} // End Appartment

class Student {
    String name;
    int accBalance;

    Student (String name, int accBalance) {
        this.name = name;
        this.accBalance = accBalance;
    } // End constructor
} // End Student