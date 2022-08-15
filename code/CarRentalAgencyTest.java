package code;

import code.vehicles.Car;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;


public class CarRentalAgencyTest {

  @Test
  public void testSortByMake() {

    Car [] originalCars = new Car [5];
    originalCars [0] = new Car("Mazda", "CX-5");
    originalCars [1] = new Car("Honda", "CR-V");
    originalCars [2] = new Car("Toyota", "RAV4");
    originalCars [3] = new Car("Land Rover", "Range Rover");
    originalCars [4] = new Car("Ford", "Escape");

    Car [] sortedByMakeCars = new Car [5];
    sortedByMakeCars [0] = new Car("Ford", "Escape");
    sortedByMakeCars [1] = new Car("Honda", "CR-V");
    sortedByMakeCars [2] = new Car("Land Rover", "Range Rover");
    sortedByMakeCars [3] = new Car("Mazda", "CX-5");
    sortedByMakeCars [4] = new Car("Toyota", "RAV4");

    SortByMake.mergeSortMake(originalCars, 0, originalCars.length - 1);

    assertArrayEquals(sortedByMakeCars, originalCars);

  }

  @Test
  public void testSortByModel() {

    Car [] originalCars = new Car [5];
    originalCars [0] = new Car("Mazda", "CX-5");
    originalCars [1] = new Car("Honda", "CR-V");
    originalCars [2] = new Car("Toyota", "RAV4");
    originalCars [3] = new Car("Land Rover", "Range Rover");
    originalCars [4] = new Car("Ford", "Escape");

    Car [] sortedByModelCars = new Car [5];
    sortedByModelCars [0] = new Car("Honda", "CR-V");
    sortedByModelCars [1] = new Car("Mazda", "CX-5");
    sortedByModelCars [2] = new Car("Ford", "Escape");
    sortedByModelCars [3] = new Car("Toyota", "RAV4");
    sortedByModelCars [4] = new Car("Land Rover", "Range Rover");

    SortByModel.mergeSortModel(originalCars, 0, originalCars.length - 1);

    assertArrayEquals(sortedByModelCars, originalCars);

  }

}