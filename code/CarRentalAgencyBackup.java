package code;

import code.vehicles.Car;
import simpleIO.Console;
import java.io.*;


public class CarRentalAgencyBackup {

  // Class data field = this will be accessible to all methods within this file
  private static Car[] carVehicle;

  public static void main(String[] args) {
    
    // Menu options
    final int QUIT = 0, DISPLAY = 1, RENT_OUT = 2, RETURN = 3, SORT_BY_MAKE = 4, SORT_BY_MODEL = 5, SEARCH_BY_MAKE = 6, SEARCH_BY_MODEL = 7, SAVE_INFO = 8;

    int userChoice, numCars;

    // Input: How many Cars are in the Library?
    fillCars();

    do {
      Console.print("Welcome to Ace Car Rental Agency\n");
      userChoice = Console.readInt("Menu options:\n" + DISPLAY + " - Display the list of Cars\n" + RENT_OUT
          + " - Rent out a car\n" + RETURN + " - Return a car\n" + SORT_BY_MAKE + " - Sort list by make\n" + SORT_BY_MODEL + " - Sort list by model\n" + SEARCH_BY_MAKE + " - Search list by make\n" + SEARCH_BY_MODEL + " - Search list by model\n" + SAVE_INFO + " - Save current data to a file\n" + QUIT + " - Quit\nEnter choice:");

      if (userChoice == DISPLAY) {
        displayCars();
      } else if (userChoice == RENT_OUT) {
        rentOutCar();
      } else if (userChoice == RETURN) {
        returnCar();
      } else if (userChoice == SORT_BY_MAKE) {
        // Call the merge string sort method to sort the list by name
        SortByMake.mergeSortString(carVehicle, 0, carVehicle.length - 1);
        // Print a message to user informing them that their list has been sorted
        Console.print("Your list has been sorted by make. Please choose the display option to view the sorted list");
      } else if (userChoice == SORT_BY_MODEL) {
        // Call the merge string sort method to sort the list by name
        SortByModel.mergeSortString(carVehicle, 0, carVehicle.length - 1);
        // Print a message to user informing them that their list has been sorted
        Console.print("Your list has been sorted by model. Please choose the display option to view the sorted list");
      } else if (userChoice == SEARCH_BY_MAKE) {
        int location;
        // Temp variable to hold the name user wants to search for
        String makeToFind;
        // Get user input for name they want to search for
        makeToFind = Console.readString("What car make would you like to search for?\n");
        // Call the binary search method, save result in location variable
        location = SearchByMake.linear(carVehicle, makeToFind);
        // If statements for whether the name is found or not.
        if (location == -1) {
          System.out.println("Sorry, car make not found in list.");
        } else {
          // Add 1 to location value so it starts at 1 (more user friendly)
          System.out.println(makeToFind + " is car # " + (location + 1));
        }
      } else if (userChoice == SEARCH_BY_MODEL) {
        int location;
        // Temp variable to hold the name user wants to search for
        String modelToFind;
        // Get user input for name they want to search for
        modelToFind = Console.readString("What car model would you like to search for?\n");
        // Call the binary search method, save result in location variable
        location = SearchByModel.linear(carVehicle, modelToFind);
        // If statements for whether the name is found or not.
        if (location == -1) {
          System.out.println("Sorry, car model not found in list.");
        } else {
          // Add 1 to location value so it starts at 1 (more user friendly)
          System.out.println(modelToFind + " is car # " + (location + 1) + "\nThe make of the " + modelToFind + " is " + carVehicle[location].getMake());
        }
      } else if (userChoice == SAVE_INFO) {
        // File handling objects
        FileWriter studentFile;
        PrintWriter studentPrinter;

        // Try catch block to catch any errors while saving to the file
        try {
          // Print a user friendly message informing user their file is being saved
          Console.print("\nSaving file ...");

          // Local variable to hold the name of the file that the user wants to save their
          // info to
          String fileName;
          // Get user input for file name
          fileName = Console.readString("What would you like to name the file which will hold this information?\n");
          // Instantiate the file handling objects (concatenate the user input to name the
          // file)
          studentFile = new FileWriter("data/" + fileName + ".txt");
          studentPrinter = new PrintWriter(studentFile);

          // The first line of the file will be the number of students that the file
          // contains
          studentPrinter.println(carVehicle.length);

          // Save contents of the array to the file
          for (int i = 0; i < carVehicle.length; i++) {
            studentPrinter.println(carVehicle[i]);
          }

          // Close the file
          studentFile.close();

          // If successful, success message will print
          Console.print("File saved successfully");
          // Catch blocks to catch any possible errors that occur while saving to file
        } catch (IOException e) {
          Console.print("Error writing to file: " + e.getMessage());
        }
      } else if (userChoice == QUIT) {
        Console.print("Thank you for using the Car Agency Application!");
      }


    } while (userChoice != QUIT);

  }

  /**
   * Fills the Cars in the Library.
   */
  public static void fillCars() {

    int numberOfCars = 0;
    // Local variables for user input
    String make, model;
    String line = "";

    // File handling objects
    FileReader studentFile;
    BufferedReader studentStream;

    // Go into try catch block to validate info coming in
    try {
      // Instantiate the file handling objects
      studentFile = new FileReader("data/cars.txt");
      studentStream = new BufferedReader(studentFile);

      // First line of the file contains the number of students in the file
      // Read the line as a String, then convert to an int
      line = studentStream.readLine();
      numberOfCars = Integer.parseInt(line);

      // Create the array with the given number of students
      carVehicle = new Car[numberOfCars];

      // Read the rest of the file and fill the array
      for (int i = 0; i < carVehicle.length; i++) {

        // Get the name from the file (Name is first)
        make = studentStream.readLine();

        // Read the next line as a String, then convert to a double (Grade is next)
        model = studentStream.readLine();
        // store in array
        carVehicle[i] = new Car(make, model);
      }

      // close the file
      studentFile.close();

      // Deal with all the things that could go wrong using try catch blocks
    } catch (FileNotFoundException e) {
      Console.print("No file was found: " + e.getMessage());
    } catch (IOException e) {
      Console.print("Problems reading the file: " + e.getMessage());
    } catch (NumberFormatException e) {
      Console.print("File not formatted properly: " + e.getMessage());
    }
  }


  /**
   * Display a list of all Cars in the library 
   * pre: none 
   * post: The Cars in the library are displayed
   */
  public static void displayCars() {
    Console.print("---------Agency Cars---------");

    for (int i = 0; i < carVehicle.length; i++) {
      //Include the car number for user entry
      Console.print("Car # " + (i + 1));
      //Display the current car to the Console
      Console.print(carVehicle[i]);
    }    

  }

  /**
   * Check a car out of the library. A message will be printed to the user if the
   * car is already checked out
   * 
   * @param num  the index of the item to be checked out
   * @param name the name of the person checking out the car
   */
  public static void rentOutCar() {
    Console.print("Renting the car ...");
    String name = Console.readString("What is the customer's name?");
    int num = Console.readInt("Enter car #: ");

    if (num >= 0 && num < carVehicle.length) { //check for valid car #
      //make sure the car is available!
      if ( carVehicle[num].isCarRented() ) {
        Console.print("This car is already out!");
      } else {
        //Check out the car using the name provided
        carVehicle[num].rentOut(name);

      }
    } else {
      Console.print("Invalid car number!");
    }
  }

  /**
   * Return a car to the library. A message will be printed to the user if the
   * car is already returned.
   * 
   * @param num the index of the item to be returned
   */
  public static void returnCar() {

    Console.print("Returning a car ...");
    int num = Console.readInt("Enter car #: ");

    if (num >= 0 && num < carVehicle.length) {  //check for valid car #
      // check that the car is actually out
      if ( carVehicle[num].isCarRented() ) {
        //return the car
        carVehicle[num].returnCar();

      } else {
        Console.print("This car is already returned!");
      }
    } else {
      Console.print("Invalid car number!");
    }
  }

}
