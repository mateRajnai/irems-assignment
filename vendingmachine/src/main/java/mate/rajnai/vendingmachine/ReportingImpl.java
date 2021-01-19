package mate.rajnai.vendingmachine;

import java.util.List;

public class ReportingImpl implements Reporting {

	private VendingMachine vendingMachine;
	
	ReportingImpl(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	
	@Override
	public String reportConsumptionByProduct() {
		String report = "";
		List<Product> consumedProducts = vendingMachine.getConsumedProducts();
		for (Product product: Product.values()) {
			int consumedQuantity = (int) consumedProducts.stream().filter(item -> item.equals(product)).count();
			report += product.getName() + ": " + consumedQuantity + "\n";
		}
		return report;
	}
	

}
