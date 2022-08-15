package code.vehicles;

/*
* @author Engy Elsayed 
* Date: May 13th 2021 
* Course: ICS4U 
* Vehicles.java 
* 
*/

public class Vehicles {

  // Data fields 
  protected String make;
  protected String rentersName;
  protected boolean isRented;

  /**
   * Creates a Vehicle with a given make. By default the vehicle is available for check out.
   * 
   * @param create the make of the Vehicle
   */
  public Vehicles(String make) {
    this.make = make;
    isRented = false;
    rentersName = "";
  }

  /**
   * @return the make of the Vehicle
   */
  public String getMake() {
    return make;
  }

  /**
   * @return <code>true</code> if this car is rented out, <code>false</code> if it is available
   */
  public boolean isCarRented() {
    return isRented;
  }

  /**
   * @return the name of the person that rented out the car. Returns an empty String "" if the car is not rented out.
   */
  public String getRentersName() {
    return rentersName;
  }

  /**
   * Changes the rent out status to false. Clears the name of the person that rentd out the car.
   */
  public void returnCar() {
    isRented = false;
    rentersName = "";
  }

  /**
   * Changes the rent out status to true. Saves the name of the customer renting the car.
   * 
   * @param save the name of the person renting the book
   */
  public void rentOut(String name) {
    isRented = true;
    rentersName = name;
  }

  /**
   * Gives the make and a String indicating if the vehicle is available or rented out. 
   * If the vehicle is rented out, gives the renter's name.
   */
  @Override
  public String toString() {
    String output = "Make: " + make + "\n";

    if (isRented) {
      output += "Rented Out\nCustomer Name: " + rentersName + "\n";
    } else {
      output += "Available for Rent\n";
    }

    return output;
  }

  /**
   * Determines if two vehicles are the same by checking the make and rent out name
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Vehicles) {
      Vehicles test = (Vehicles) obj;

      boolean makeMatches = make.equals(test.getMake());
      boolean borrowerMatches = rentersName.equals(test.getRentersName());

      return makeMatches && borrowerMatches;

    }

    return false;

  }

}