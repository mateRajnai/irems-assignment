package mate.rajnai.vendingmachine.inventory.supplier;

import mate.rajnai.vendingmachine.inventory.Inventory;

public interface InventorySupplier<T> {

	 void fillUp(Inventory<T> inventory);

}
