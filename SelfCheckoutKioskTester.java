/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Self-Checkout Kiosk Tester
// Course: CS 300 Spring 2021
//
// Author: Khyati Gupta
// Email: kgupta44@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/*
 * The application given below checks the individual methods from SelfCheckoutKiosk java file to
 * verify their correct functionality.
 * 
 * @author khyat
 *
 */

public class SelfCheckoutKioskTester {

	/*
	 * Checks whether SelfCheckoutKisok.getItemName() and
	 * SelfCheckoutKisok.getItemPrice() method work as expected.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testItemNameAndPriceGetterMethods() {
		// consider all identifiers values as input arguments
		// GROCERY_ITEMS array is a perfect size array. So, its elements are stored
		// in the range of indezes from 0 .. GROCERY_ITEMS.length -1
		for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
			// check first for the correctness of the getItemName(i) method
			if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
				System.out.println("Problem detected: Called your getItemName() method with " + "input value " + i
						+ ". But it did not return the expected output.");
				return false;
			}

			// Now, let's check for the correctness of the getItemPrice(i) method
			double expectedPriceOutput = Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1).trim());
			// We do not use == to compare floating-point numbers (double and float)
			// in java. Two variables a and b of type double are equal if the absolute
			// value of their difference is less or equal to a small threshold epsilon.
			// For instance, if Math.abs(a - b) <= 0.001, then a equals b
			if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
				// you can print a descriptive error message before returning false
				return false;
			}
		}
		System.out.println("testItemNameAndPriceGetterMethods() passed successfully!");
		return true; // No defect detected -> The implementation passes this test
	}

	/*
	 * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testAddItemToBaggingArea() {
		// Create an empty bagging area
		String[] items = new String[10];
		int size = 0;

		// Define the test scenarios:

		// (1) Add one item to an empty bagging area
		// try to add an apple (id: 0) to the bagging area
		size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);

		if (size != 1) {
			System.out.println("Problem detected: Tried to add one item to an empty, "
					+ "bagging area. The returned size must be 1. But your addItemToBaggingArea "
					+ "method returned a different output.");

			return false;

		}

		if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
			// notice here the importance of checking for the correctness of your
			// getItemName()
			// method before calling it above
			System.out.println("Problem detected: Tried to add only one item to an empty, "
					+ "bagging area. But that item was not appropriately added to the contents "
					+ "of the items array.");
		}

		// (2) Consider a non-empty bagging area
		items = new String[] { "Milk", "Chocolate", "Onion", null, null, null, null };
		size = 3;
		size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
		if (size != 4) {
			System.out.println("Problem detected: Tried to add only one item to an non-empty, "
					+ "bagging area. The size must be incremented after the method returns. But "
					+ "it was not the case");
			return false;
		}

		if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
			System.out.println("Problem detected: Tried to add one item to an non-empty, "
					+ "bagging area. But that item was not appropriately added to the contents "
					+ "of the items array.");
		}

		// (3) Consider adding an item to a full bagging are
		items = new String[] { "Pizza", "Eggs", "Apples" };
		size = 3;
		size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);

		if (size != 3) {
			System.out.println("Problem detected: Tried to add one item to a non-empty, "
					+ "bagging area. The size must not be incremented after the method returns. But "
					+ "it was not the case");
			return false;
		}

		if (!items[0].equals("Pizza") || !items[1].equals("Eggs") || !items[2].equals("Apples")) {
			System.out.println("Problem detected: Tried to add one item to a non-empty, "
					+ "bagging area. No changes should have been made to the content of the items array."
					+ " But it was not the case");
			return false;
		}

		System.out.println("testAddItemToBaggingArea() passed successfully!");
		return true; // No defects detected by this unit test
	}

	/*
	 * Checks the correctness of SelfCheckoutKiosk.count() method Try to consider
	 * different test scenarios: (1) a bagging area (defined by the items array and
	 * its size) which contains 0 occurrences of the item, (2) a bagging area which
	 * contains at least 4 items and only one occurrence of the item to count, and
	 * (3) a bagging area which contains at least 5 items and 2 occurrences of the
	 * item to count.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testCount() {

		// Create an empty bagging area
		String[] items = new String[10];
		int size = 0;
		int count = 0;

		// Case 1: a bagging area (defined by the items array and its size) which
		// contains 0 occurrences of the item
		items = new String[] { "Pizza", "Eggs", "Apples" };
		size = 3;

		count = SelfCheckoutKiosk.count("Cookie", items, size);

		if (count != 0) {
			System.out.println("Tried to count occurences of an item not present in the given list of items. "
					+ "The return value should be 0, but that was not the case");

			return false;
		}
		// Case 2: a bagging area which contains at least 4 items and only one
		// occurrence of the item to
		// count
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples" };
		size = 4;

		count = SelfCheckoutKiosk.count("Cereal", items, size);

		if (count != 1) {
			System.out.println("Tried to count occurences of an item present only once in the given list of items. "
					+ "The return value should be 1, but that was not the case");

			return false;
		}

		// Case 3: a bagging area which contains at least 5 items and only 2 occurrences
		// of the item to
		// count
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples", "Pizza" };
		size = 5;

		count = SelfCheckoutKiosk.count("Pizza", items, size);

		if (count != 2) {
			System.out.println("Tried to count occurences of an item present only twice in the given list of items. "
					+ "The return value should be 2, but that was not the case");

			return false;
		}

		System.out.println("testCount() passed successfully!");
		return true;
	}

	/*
	 * Checks the correctness of SelfCheckoutKiosk.indexOf() method Consider the
	 * cases where the items array contains at least one match with the item to
	 * find, and the case when the item was not stored in the array and the expected
	 * output is -1.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testIndexOf() {

		// Create an empty bagging area
		String[] items = new String[10];
		int size = 0;
		int index = 0;

		// Case 1: where the items array contains one match with the item to find
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples" };
		size = 4;

		index = SelfCheckoutKiosk.indexOf("Cereal", items, size);

		if (index != 2) {
			System.out
					.println("Tried to count index of an item present only once (index 2) in the given list of items. "
							+ "The return value should be 2, but that was not the case");

			return false;
		}

		// Case 2: where the items array contains two matches with the item to find
		items = new String[] { "Pizza", "Eggs", "Cereal", "Eggs" };
		size = 4;

		index = SelfCheckoutKiosk.indexOf("Eggs", items, size);

		if (index != 1) {
			System.out.println("Tried to count index of an item present twice (index 1, 3) in the given list of items. "
					+ "The return value should be 1, but that was not the case");

			return false;
		}

		// Case 3: where the items array contains no matches with the item to find
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples" };
		size = 4;

		index = SelfCheckoutKiosk.indexOf("Cookie", items, size);

		if (index != -1) {
			System.out.println("Tried to count index of an item not present in the given list of items. "
					+ "The return value should be -1, but that was not the case");

			return false;
		}

		System.out.println("testIndexOf() passed successfully!");
		return true;
	}

	/*
	 * Checks that when only one attempt to remove an item stored in the bagging
	 * area is made, only one occurrence of that item is removed from the array of
	 * items, that the returned size is correct, and that the items array contains
	 * all the other items.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testRemove() {

		String[] items = new String[] { "Pizza", "Eggs", "Cereal", "Apples" };
		int size = 4;
		String toBeRemoved = "Eggs";

		size = SelfCheckoutKiosk.remove(toBeRemoved, items, size);

		// check that Eggs are not present in list of items anymore
		for (int i = 0; i < size; i++) {
			if (items[i].equals(toBeRemoved)) {
				System.out.println(
						"Eggs should not have been present in the list of items after the remove method was executed. "
								+ "However, at least one instance was found");
				return false;
			}
		}
		// checks that the size of remaining list is 3
		if (size != 3) {
			System.out.println("Size should have been 3, but has another value");
			return false;
		}
		// checks that other elements of the list are present and in the right order

		if (!items[0].equals("Pizza") || !items[1].equals("Cereal") || !items[2].equals("Apples") || items[3] != null) {
			System.out.println("Items not present in the right order, or are missing");
			return false;
		}

		System.out.println("testRemove() passed successfully!");
		return true;
	}

	/*
	 * Checks whether getUniqueCheckedOutput functioning is correct.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testGetUniqueCheckedOutItems() {

		// Create an empty bagging area
		String[] items = new String[10];
		int size = 0;

		// Case 1: All unique items in cart
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples" };
		size = 4;

		String[] itemsSet = new String[size];

		int setSize = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet);

		// checks for set size
		if (setSize != 4) {
			System.out.println("setSize should have been 4, but has another value");
			return false;
		}

		// checks that other elements of the list are present and in the right order
		if (!itemsSet[0].equals("Pizza") || !itemsSet[1].equals("Eggs") || !itemsSet[2].equals("Cereal")
				|| itemsSet[3] != "Apples") {
			System.out.println("Items not present in the right order, or are missing from the set");
			return false;
		}

		// Case 2: Two duplicate items in cart
		items = new String[] { "Pizza", "Eggs", "Cereal", "Apples", "Cookie", "Apples", "Eggs", "Pineapple" };
		size = 8;

		itemsSet = new String[size];

		setSize = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet);

		// checks for set size
		if (setSize != 6) {
			System.out.println("setSize should have been 6, but has another value");
			return false;
		}

		// checks that other elements of the list are present and in the right order
		if (!itemsSet[0].equals("Pizza") || !itemsSet[1].equals("Eggs") || !itemsSet[2].equals("Cereal")
				|| itemsSet[3] != "Apples" || itemsSet[4] != "Cookie" || itemsSet[5] != "Pineapple") {
			System.out.println("Items not present in the right order, or are missing from the set");
			return false;
		}

		System.out.println("testGetUniqueCheckedOutItems() passed successfully!");
		return true;
	}

	/*
	 * Checks whether getSubTotalPrice method returns the correct output.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 * otherwise
	 */
	public static boolean testGetSubTotalPrice() {

		// Create an empty bagging area
		String[] items = new String[10];
		int size = 0;

		// Case 1: All unique items in cart
		items = new String[] { "Blueberry", "Eggs", "Cereal", "Apple" };
		size = 4;

		double subTotal = SelfCheckoutKiosk.getSubTotalPrice(items, size);

		if (subTotal != 15.26) {
			System.out.println("Sub-total price should have been $15.26, but is otherwise");
			return false;
		}

		// Case 2: Some duplicate items in cart
		items = new String[] { "Blueberry", "Eggs", "Cereal", "Apple", "Eggs" };
		size = 5;

		subTotal = SelfCheckoutKiosk.getSubTotalPrice(items, size);

		if (subTotal != 18.35) {
			System.out.println("Sub-total price should have been $18.35, but is otherwise");
			return false;
		}
		System.out.println("testGetSubTotalPrice() passed successfully!");
		return true;
	}

	/*
	 * main method used to call the unit tests
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println("tesItemNameAndPriceGetterMethods(): " + testItemNameAndPriceGetterMethods());
		SelfCheckoutKiosk.printCatalog();
		testAddItemToBaggingArea();
		testCount();
		testIndexOf();
		testRemove();
		testGetUniqueCheckedOutItems();
		testGetSubTotalPrice();
	}
}