package code;

import code.vehicles.Car;

/*
* @author Engy Elsayed 
* Date: May 13th 2021 
* Course: ICS4U 
* SearchByMake.java 
* A class that implements a String searching algorithm, using the linear search method of searching.
*/

public class SearchByMake {

  /**
  * Returns the index of the element searchMake in the array.
  * -1 returned if element not found.
  * pre: none
  * post: index of searchMake has been returned. -1 has been
  * returned if element not found.
  */
  public static int linear(Car[] make, String searchMake) {

    for (int index = 0; index < make.length; index++) {
      if (make[index].getMake().equals(searchMake)) { // the element was found
        // return the index of where the make was found
        return index;
      } 

    }

    // No element was found, return -1
    return -1;

  }
}