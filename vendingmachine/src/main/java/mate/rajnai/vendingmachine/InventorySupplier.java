package mate.rajnai.vendingmachine;

public interface InventorySupplier<T> {

	 void fillUp(Inventory<T> inventory);

}
