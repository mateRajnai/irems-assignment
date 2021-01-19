package mate.rajnai.vendingmachine.exception;

public class VendingMachineHasNotEnoughChangeException extends RuntimeException {
	public VendingMachineHasNotEnoughChangeException(String message) {
		super(message);
	}
}
