package VendingMachine.state;

import VendingMachine.Product;
import VendingMachine.VendingMachine;
import VendingMachine.enums.Note;

public interface State {
    // need to keep in track of the inserted money and we need to check if somehow we can use it to buy.
   void insertMoney(VendingMachine machine, Note note);
   
   // add product to the bag
   void selectProduct(VendingMachine machine, Product product, int quantity);

   void cancelTransaction(VendingMachine machine);

   // Should dispence from the bag
   void dispenceProduct(VendingMachine machine);
    
} 
