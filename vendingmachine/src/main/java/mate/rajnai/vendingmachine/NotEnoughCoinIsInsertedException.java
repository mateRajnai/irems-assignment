package mate.rajnai.vendingmachine;

public class NotEnoughCoinIsInsertedException extends RuntimeException {
	NotEnoughCoinIsInsertedException(String message) {
		super(message);
	}
}
