package mate.rajnai.vendingmachine.exception;

public class NotEnoughCoinIsInsertedException extends RuntimeException {
	public NotEnoughCoinIsInsertedException(String message) {
		super(message);
	}
}
