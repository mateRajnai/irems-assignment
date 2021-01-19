package mate.rajnai.vendingmachine;

public class VendingMachineHasNotEnoughChangeException extends RuntimeException {
	VendingMachineHasNotEnoughChangeException(String message) {
		super(message);
	}

}
