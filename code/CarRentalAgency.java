package code;

import code.vehicles.Car;
import simpleIO.Console;
import java.io.*;

/*
* @author Engy Elsayed 
* Date: May 13th 2021 
* Course: ICS4U 
* CarRentalAgency.java 
* A car rental agency application that allows users to: display the list of cars available at ace car rental agency, search the list of cars (and find the number that corresponds with the car they would like to rent). Users can search the list by either make or model. Users can also sort the list of cars by make or model, to make it easier for them to find their choices. The user can also save the current information (i.e. a sorted list, names of customers who have rented out cars, whether or not a car is available for rent, etc...) to a file, and they can also name the file themselves.
*/

public class CarRentalAgency {

  // Class data field = this will be accessible to all methods within this file
  private static Car[] carVehicle;

  public static void main(String[] args) {

    // Menu options (final ints)
    final int QUIT = 0, DISPLAY = 1, RENT_OUT = 2, RETURN = 3, SORT_BY_MAKE = 4, SORT_BY_MODEL = 5, SEARCH_BY_MAKE = 6,
        SEARCH_BY_MODEL = 7, SAVE_INFO = 8;

    // Variable for user's choice on the menu
    int userChoice;

    // Call the method that reads in the file that saves available car list found in
    // the Ace Car Rental Agency
    fillCars();
    // Print a welcome message with the name of the rental agency
    Console.print("Welcome to Ace Car Rental Agency\n");

    // Enter a do while loop and stay in it as long as the user does not choose the
    // exit option, as well as display a message asking for valid input if they give
    // bad input
    do {
      // Get user input for which menu option the user would like to choose, save in
      // userChoice variable
      userChoice = Console.readInt("Menu options:\n" + DISPLAY + " - Display the list of Cars\n" + RENT_OUT
          + " - Rent out a car\n" + RETURN + " - Return a car\n" + SORT_BY_MAKE + " - Sort list by make\n"
          + SORT_BY_MODEL + " - Sort list by model\n" + SEARCH_BY_MAKE + " - Search list by make\n" + SEARCH_BY_MODEL
          + " - Search list by model\n" + SAVE_INFO + " - Save current data to a file\n" + QUIT
          + " - Quit\nEnter choice:\n");

      // if the user chooses the DISPLAY option
      if (userChoice == DISPLAY) {
        // Call the displayCars() method to display the list of cars
        displayCars();

        // else if the user chooses RENT_OUT
      } else if (userChoice == RENT_OUT) {
        // Call the rentOutCar() method to rent out a car
        rentOutCar();

        // else if the user chooses RETURN
      } else if (userChoice == RETURN) {
        // Call the rentOutCar() method to rent out a car
        returnCar();

        // else if the user chooses SORT_BY_MAKE
      } else if (userChoice == SORT_BY_MAKE) {
        // Call the mergeSortMake() method to sort the list by make
        SortByMake.mergeSortMake(carVehicle, 0, carVehicle.length - 1);
        // Print a message to user informing them that their list has been sorted
        Console.print("Your list has been sorted by make. Please choose the display option to view the sorted list\n");

        // else if the user chooses SORT_BY_MODEL
      } else if (userChoice == SORT_BY_MODEL) {
        // Call the mergeSortModel() method to sort the list by model
        SortByModel.mergeSortModel(carVehicle, 0, carVehicle.length - 1);
        // Print a message to user informing them that their list has been sorted
        Console.print("Your list has been sorted by model. Please choose the display option to view the sorted list\n");

        // else if the user chooses SEARCH_BY_MAKE
      } else if (userChoice == SEARCH_BY_MAKE) {
        int location;
        // Temp variable to hold the make the user wants to search for
        String makeToFind;
        // Get user input for the make they want to search for
        makeToFind = Console.readString("What car make would you like to search for?\n");
        // Call the linear search method, save result in location variable
        location = SearchByMake.linear(carVehicle, makeToFind);
        // If statements for whether the make is found or not.
        if (location == -1) {
          System.out.println("Sorry, car make not found in list.\n");
        } else {
          // Add 1 to location value so it starts at 1 (more user friendly) and print the
          // model of the make they searched for, so they know the car they will
          // potentially be renting
          System.out.println(makeToFind + " is car # " + (location + 1) + "\nThe model of the " + makeToFind + " is "
              + carVehicle[location].getModel());
        }

        // else if the user chooses SEARCH_BY_MODEL
      } else if (userChoice == SEARCH_BY_MODEL) {
        int location;
        // Temp variable to hold the model the user wants to search for
        String modelToFind;
        // Get user input for the model they want to search for
        modelToFind = Console.readString("What car model would you like to search for?\n");
        // Call the linear search method, save result in location variable
        location = SearchByModel.linear(carVehicle, modelToFind);
        // If statements for whether the model is found or not.
        if (location == -1) {
          System.out.println("Sorry, car model not found in list.\n");
        } else {
          // Add 1 to location value so it starts at 1 (more user friendly) and print the
          // make of the model they searched for, so they know the car they will
          // potentially be renting
          System.out.println(modelToFind + " is car # " + (location + 1) + "\nThe make of the " + modelToFind + " is "
              + carVehicle[location].getMake());
        }

        // else if the user chooses SAVE_INFO (saving current data to a file)
      } else if (userChoice == SAVE_INFO) {

        // File handling objects
        FileWriter carFile;
        PrintWriter carPrinter;

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
          carFile = new FileWriter("data/" + fileName + ".txt");
          carPrinter = new PrintWriter(carFile);

          // The first line of the file will be the number of cars that the file
          // contains
          carPrinter.println(carVehicle.length);

          // Save contents of the array to the file
          for (int i = 0; i < carVehicle.length; i++) {
            carPrinter.println(carVehicle[i]);
          }

          // Close the file
          carFile.close();

          // If successful, success message will print
          Console.print("File saved successfully\n");
          // Catch blocks to catch any possible errors that occur while saving to file
        } catch (IOException e) {
          Console.print("Error writing to file: " + e.getMessage());
        }

        // else if the user chooses QUIT
      } else if (userChoice == QUIT) {
        // Print a message thanking the user for using the program
        Console.print("Thank you for using the Car Agency Application!");

        // else if the user chooses anything that isn't one of the options on the menu
      } else {
        // Ask user to input a valid menu item
        Console.print("Please enter a valid menu item!\n");
      }

      // Keep the loop going until the user decided they want to quit
    } while (userChoice != QUIT);

  }

  /**
   * Reads from file the list of available cars in the rental agency. 
   * pre: none
   * post: the file has been read and stored in an array, with all the available
   * cars in the rental agency
   */
  public static void fillCars() {

    // Local variables
    int numberOfCars;
    String make, model;
    String line = "";

    // File handling objects
    FileReader carFile;
    BufferedReader carStream;

    // Go into try catch block to validate info coming in
    try {
      // Instantiate the file handling objects
      carFile = new FileReader("data/cars.txt");
      carStream = new BufferedReader(carFile);

      // First line of the file contains the number of cars in the file
      // Read the line as a String, then convert to an int
      line = carStream.readLine();
      numberOfCars = Integer.parseInt(line);

      // Create the array with the given number of cars
      carVehicle = new Car[numberOfCars];

      // Read the rest of the file and fill the array
      for (int i = 0; i < carVehicle.length; i++) {

        // Get the make of vehicle from the file (make is first) and store it in the
        // make variable
        make = carStream.readLine();

        // Read the next line and store in the model variable
        model = carStream.readLine();

        // store in array
        carVehicle[i] = new Car(make, model);
      }

      // close the file
      carFile.close();

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
   * Display a list of all Cars in the rental agency 
   * pre: none 
   * post: The Cars in the rental agency are displayed
   */
  public static void displayCars() {

    // print to console all available cars in the agency
    Console.print("\n---------Ace Rental Car Agency Car List---------\n");
    for (int i = 0; i < carVehicle.length; i++) {
      // Include the car number for user entry
      Console.print("Car # " + (i + 1));
      // Display the available cars to the Console
      Console.print(carVehicle[i]);
    }

  }

  /**
   * Rent out a car. A message will be printed to the user if the car is already
   * rented out
   * 
   * @param num  the index of the item to be rented out
   * @param name the name of the customer renting out the car
   */
  public static void rentOutCar() {
    // Print a user friendly message to the user that the program is renting their
    // car
    Console.print("Renting the car ...");
    // Get user's name (or the name of the customer renting it)
    String name = Console.readString("What is the name of the customer who is renting this car?\n");
    // Get user's choice of which car number they want to rent
    int num = Console.readInt("Enter car #: ");

    // Check if the car # is actually valid
    if (num >= 0 && num < carVehicle.length) {
      // Make sure the car is available!
      if (carVehicle[num].isCarRented()) {
        // Print this message if car is not available for rent
        Console.print("This car is already out!\n");
      } else {
        // Check out the car using the name provided
        carVehicle[num].rentOut(name);

      }
      // Tell the user that the input they entered is not valid
    } else {
      Console.print("Invalid car number!\n");
    }
  }

  /**
   * Return a car. A message will be printed to the user if the car is already
   * returned.
   * 
   * @param num the index of the car to be returned
   */
  public static void returnCar() {

    // Print a user friendly message to the user that the program is returning their
    // car
    Console.print("Returning a car ...");
    // Get user's choice of which car number they want to return
    int num = Console.readInt("Enter car #: ");

    // Check if the car # is actually valid
    if (num >= 0 && num < carVehicle.length) {
      // Check that the car # is actually out
      if (carVehicle[num].isCarRented()) {
        // Return the car
        carVehicle[num].returnCar();
        // Print a success message (to give user insight on what is happening)
        Console.print("The car is successfully returned!");

        // Print this message if car is not already returned
      } else {
        Console.print("This car is already returned!\n");
      }

      // Tell the user that the input they entered is not valid
    } else {
      Console.print("Invalid car number!");
    }
  }

}
