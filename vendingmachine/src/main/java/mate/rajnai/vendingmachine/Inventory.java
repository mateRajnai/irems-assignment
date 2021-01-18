package mate.rajnai.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
	
	private List<T> items = new ArrayList<T>();

	public List<T> getItems() {
		return items;
	}

	public void addItem(T t) {
		items.add(t);
	}

	public boolean removeItem(T t) {
		return items.remove(t);
	}
	
}
