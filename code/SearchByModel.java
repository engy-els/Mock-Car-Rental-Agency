package code;

import code.vehicles.Car;

/*
* @author Engy Elsayed 
* Date: May 13th 2021 
* Course: ICS4U 
* SearchByModel.java 
* A class that implements a String searching algorithm, using the linear search method of searching.
*/

public class SearchByModel {

  /**
  * Returns the index of the element searchModel in the array.
  * -1 returned if element not found.
  * pre: none
  * post: index of searchModel has been returned. -1 has been
  * returned if element not found.
  */
  public static int linear(Car [] model, String searchModel) {

    for (int index = 0; index < model.length; index++) {
      if (model[index].getModel().equals(searchModel)) { // the element was found
        // return the index of where the model was found
        return index;
      } 

    }

    // if we get here, no element was found
    return -1;

  }
}