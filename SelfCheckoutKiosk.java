/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Self-Checkout Kiosk
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
 * This class implements different methods for a self-checkout kiosk such as monitoring the
 * different items available, their given prices and keeping track of the grocery cart (oversize
 * array).
 * 
 * @author khyat
 *
 */
public class SelfCheckoutKiosk {

	public static final double TAX_RATE = 0.05; // sales tax
	// a perfect-size two-dimensional array that stores the available items in the
	// grocery store
	// GROCERY_ITEMS[i][0] refers to a String that represents the name of the item
	// identified by index i
	// GROCERY_ITEMS[i][1] refers to a String that represents the unit price of the
	// item
	// identified by index i in dollars.

	public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

	/*
	 * Returns the items name given its index
	 *
	 * @param index - unique identifier of an item
	 * 
	 * @return the item name
	 */
	public static String getItemName(int index) {
    String itemName = GROCERY_ITEMS[index][0];
    return itemName;
  }

	/*
	 * Returns the price of an item given its index (unique identifier)
	 *
	 * @param index - unique identifier of an item
	 * 
	 * @return the price of an item
	 */
	public static double getItemPrice(int index) {
    String price = GROCERY_ITEMS[index][1].substring(1);
    double itemPrice = Double.parseDouble(price);
    return itemPrice;
  }

	/*
	 * Prints the Catalog of the grocery store (item identifiers, names, and prices)
	 */
	public static void printCatalog() {
    // Complete the missing code /* */ in the following implementation

    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \t Name \t Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + getItemName(i) + " \t " + getItemPrice(i));
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

	/*
	 * Adds the name of a grocery item given its identifier at the end of (the
	 * bagging area) the oversize array defined by the items array and its size. If
	 * the items array reaches its capacity, the following message:"Error! No
	 * additional item can be scanned. Please wait for assistance." will be
	 * displayed and the method returns without making any change to the contents of
	 * the items array. Returns the number of elements stored in bagging area after
	 * the item with the provided identifier was added to the bagging area
	 *
	 * @param id - identifier of the item to be added to the bagging area (index of
	 * the item in the GROCERY_ITEMS array) items - array storing the names of the
	 * items checked out and placed in the bagging area size - number of elements
	 * stored in items before trying to add a new item
	 * 
	 * @return the number of elements
	 */
	public static int addItemToBaggingArea(int id, String[] items, int size) {


    if (size < items.length) {

      items[size] = getItemName(id);
      size++;
      return size;

    } else {
      System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
    }
    return size;
  }

	/*
	 * Returns the number of occurrences of a given item in an oversize array of
	 * strings. The comparison to find the occurrences of item is case insensitive.
	 *
	 * @param item - item to count its occurrences items - a bag of string items
	 * size - number of items stored in items
	 * 
	 * @return the number of occurrences
	 */
	public static int count(String item, String[] items, int size) {

    int count = 0;

    for (int i = 0; i < size; i++) {
      // if match is found, increase count
      if (items[i].equals(item))
        count++;
    }
    return count;
  }

	/*
	 * Returns the index of the first occurrence of item in items if found, and -1
	 * if the item not found
	 *
	 * @param item - element to search for items - an array of string elements size
	 * - number of elements stored in items
	 * 
	 * @return the index
	 */
	public static int indexOf(String item, String[] items, int size) {

    // in the case that item is present
    for (int i = 0; i < size; i++) {
      if (item.equals(items[i])) {
        return i;
      }

    }
    // when item is not found
    return -1;
  }

	/*
	 * Removes the first occurrence of itemToRemove from the bagging area defined by
	 * the array items and its size. If no match with itemToRemove is found, the
	 * method displays the following error message "WARNING: item not found."
	 * without making any change to the items array. This method compacts the
	 * contents of the items array after removing the itemToRemove so there are no
	 * empty spaces in the middle of the array.
	 *
	 * @param itemToRemove - item to remove from the bagging area items - a bag of
	 * items size - number of elements stored in the bag of items
	 * 
	 * @return the number of items present in the cart after the itemToRemove is
	 * removed from the cart
	 */
	public static int remove(String itemToRemove, String[] items, int size) {

    // traversing item list for item to remove
    for (int i = 0; i < size; i++) {
      if (itemToRemove.equals(items[i])) {

        // shift the items one place to the left, to cover for the item that is being removed
        for (int j = i; j < size - 1; j++) {
          items[j] = items[j + 1];
        }

        items[size - 1] = null;
        // decrease size of the list after successful removal and shift
        size = size - 1;
        return size;
      }

    }

    System.out.println("WARNING: item not found.");
    return 0;
  }

	/*
	 * Gets a copy of the items array without duplicates. Adds every unique item
	 * stored within the items array to the itemsSet array.The itemsSet array is
	 * initially empty. Recall that a set is a collection which does not contain
	 * duplicate items). On the other hand, this method does not make any change to
	 * the contents of the items array.
	 *
	 * @param items - list of items added to the bagging area size - number of
	 * elements stored in items itemsSet - reference to an empty array which is
	 * going to contain the set of items checked out (it does not contain
	 * duplicates)
	 * 
	 * @return the number of elements in items without accounting duplicates. In
	 * other words, this method returns the new size of the itemsSet array
	 */
	public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {

    int setSize = 0;

    // traversing initial item list
    for (int i = 0; i < size; i++) {

      String toBeChecked = items[i];

      // for initial case when there are no items in the itemsSet
      if (setSize == 0) {
        itemsSet[0] = toBeChecked;
        setSize++;
      }

      // otherwise, search for the given item in the itemsSet to check for duplicates
      else {

        int flag = 0;

        for (int j = 0; j < setSize; j++) {
          if (toBeChecked.equals(itemsSet[j]))
            flag++;
        }

        // if the item is not a duplicate, add to itemsSet
        if (flag == 0) {
          itemsSet[setSize] = toBeChecked;
          setSize++;
        }

      }

    }

    // Note that we assume that the length of itemsSet equals
    // at least the size of items. This means that itemsSet array
    // can store the set of scanned items at checkout
    return setSize;
  }

	/*
	 * Returns the total value (price) of the scanned items at checkout without tax
	 * in $ (double)
	 *
	 * @param items - an array which stores the items checked out size - number of
	 * elements stored in the items array
	 * 
	 * @return the total value
	 */
	public static double getSubTotalPrice(String[] items, int size) {

    double subTotal = 0;
    String itemToSearch = null;

    // traversing item list
    for (int i = 0; i < size; i++) {

      itemToSearch = items[i];

      // searching for the item in grocery list
      for (int index = 0; index < 25; index++) {
        if (itemToSearch.equals(GROCERY_ITEMS[index][0])) {
          // if item is found, add price of the item to subtotal
          subTotal += getItemPrice(index);
          break;
        }

      }

    }
    return subTotal;
  }

}
