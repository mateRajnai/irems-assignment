package mate.rajnai.vendingmachine;

public class ProductIsOutOfRunException extends RuntimeException {

	public ProductIsOutOfRunException(String message) {
		super(message);
	}

}
