package mate.rajnai.vendingmachine;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendingMachineImplTest {
	
	private VendingMachine vendingMachine;
	
	@BeforeEach
	void init() {
		vendingMachine = new VendingMachineImpl();
	}
	
	@Test
	void insertCoin_Penny() {
		vendingMachine.insertCoin(Coin.PENNY);
		int insertedMoneyOfCurrentPurchase = vendingMachine.getInsertedMoneyOfCurrentPurchase();
		assertEquals(1, insertedMoneyOfCurrentPurchase);
		
	}

	


}
