package mate.rajnai.vendingmachine.inventory;

public interface InventorySupplier<T> {

	 void fillUp(Inventory<T> inventory);

}
