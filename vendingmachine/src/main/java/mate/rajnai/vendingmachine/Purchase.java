package mate.rajnai.vendingmachine;

import java.util.List;

public class Purchase {
	
	private final Product purchasedProduct;
	private final List<Coin> change;
	
	Purchase(Product product, List<Coin> change) {
		this.purchasedProduct = product;
		this.change = change;
	}

	public Product getProduct() {
		return this.purchasedProduct;
	}

	public List<Coin> getChange() {
		return this.change;
	}

}
