package mate.rajnai.vendingmachine.inventory;

import java.util.ArrayList;
import java.util.List;

import mate.rajnai.vendingmachine.Coin;

public class Inventory<T> {
	
	private List<T> items = new ArrayList<T>();

	public List<T> getItems() {
		return this.items;
	}

	public void addItem(T t) {
		this.items.add(t);
	}

	public boolean removeItem(T t) {
		return this.items.remove(t);
	}

	public List<T> clearItems() {
		List<T> itemsToBeReturned = new ArrayList<T>(this.items);
		this.items = new ArrayList<T>();
		return itemsToBeReturned;
	}

	public void addItems(List<T> itemsToBeAddedToOtherInventory) {
		this.items.addAll(itemsToBeAddedToOtherInventory);
		
	}

	public boolean hasItem(T t) {
		return this.items.parallelStream().filter(item -> item.equals(t)).findFirst().isPresent();
	}
	
	
	
}
