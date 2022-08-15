package code.vehicles;

/*
* @author Engy Elsayed 
* Date: May 13th 2021 
* Course: ICS4U 
* Car.java 
* 
*/


public class Car extends Vehicles {

  // data field to hold the model name
  private String model;

  /**
   * @param carMake the Car's make
   * @param modelName the Car's model
   */
  public Car(String carMake, String carModel) {
    super(carMake);
    model = carModel;
  }

  /**
   * @return the model of the Car
   */
  public String getModel() {
    return model;
  }

  /**
   * Identifies the vehicle as a Car.
   * {@inheritDoc}
   * Also displays the model name.
   * 
   */
  @Override
  public String toString() {
    String output = new String("Car ");

    output += super.toString();
    output += "Model: " + model + "\n";

    return output;
  }

  /**
   * Determines if obj is an instance of Car
   * 
   */
  @Override
  public boolean equals(Object obj) {

    if (obj instanceof Car) {
      Car test = (Car) obj;

      boolean modelMatches = model.equals(test.getModel());

      return modelMatches && super.equals(obj);

    }

    return false;
  }
}