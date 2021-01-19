package mate.rajnai.vendingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.InventorySupplier;

public class VendingMachineImpl implements VendingMachine {
	
	private Inventory<Product> availableProducts = new Inventory<Product>();
	private Inventory<Coin> availableCoins = new Inventory<Coin>();
	private Inventory<Coin> insertedCoinsOfCurrentPurchase = new Inventory<Coin>();
	private List<Product> consumedProducts = new ArrayList<>();
	
	VendingMachineImpl(InventorySupplier<Product> productInventorySupplier, InventorySupplier<Coin> coinInventorySupplier) {
		productInventorySupplier.fillUp(this.availableProducts);
		coinInventorySupplier.fillUp(this.availableCoins);
	}


	@Override
	public void insertCoin(Coin coin) {
		this.insertedCoinsOfCurrentPurchase.addItem(coin);
	}
	
	@Override
	public int getAmountOfInsertedMoneyOfCurrentPurchase() {
		 return Math.toIntExact(this.insertedCoinsOfCurrentPurchase
				 .getItems()
				 .stream()
				 .map(item -> item.getValue())
				 .collect(Collectors.summingInt(Integer::intValue)));
	}

	@Override
	public Purchase buyProductAndReturnChangesIfAny(Product product) {
		if (this.getAmountOfInsertedMoneyOfCurrentPurchase() < product.getPrice()) {
			throw new NotEnoughCoinIsInsertedException("Inserted coin is less than product's price!");
		}
		if(this.availableProducts.removeItem(product)) {
			int changeAmount = this.getAmountOfInsertedMoneyOfCurrentPurchase() - product.getPrice();
			List<Coin> coinsToBeAddedToAvailableCoins = this.insertedCoinsOfCurrentPurchase.clearItems();
			this.availableCoins.addItems(coinsToBeAddedToAvailableCoins);
			
			List<Coin> coinsAsChange = new ArrayList<Coin>();
			while (changeAmount > 0) {
				if (changeAmount >= Coin.QUARTER.getValue() && this.availableCoins.hasItem(Coin.QUARTER)) {
					this.availableCoins.removeItem(Coin.QUARTER);
					coinsAsChange.add(Coin.QUARTER);
					changeAmount -= Coin.QUARTER.getValue();
					continue;
				} else if (changeAmount >= Coin.DIME.getValue() && this.availableCoins.hasItem(Coin.DIME)) {
					this.availableCoins.removeItem(Coin.DIME);
					coinsAsChange.add(Coin.DIME);
					changeAmount -= Coin.DIME.getValue();
					continue;
				} else if (changeAmount >= Coin.NICKEL.getValue() && this.availableCoins.hasItem(Coin.NICKEL)) {
					this.availableCoins.removeItem(Coin.NICKEL);
					coinsAsChange.add(Coin.NICKEL);
					changeAmount -= Coin.NICKEL.getValue();
					continue;
				} else if (changeAmount >= Coin.PENNY.getValue() && this.availableCoins.hasItem(Coin.PENNY)) {
					this.availableCoins.removeItem(Coin.PENNY);
					coinsAsChange.add(Coin.PENNY);
					changeAmount -= Coin.PENNY.getValue();
					continue;
				} else {
					this.availableProducts.addItem(product);
					this.takeRefund();
					throw new VendingMachineHasNotEnoughChangeException("Not enough change. You've got back your money. Please insert the exact amount of money and select your product again.");
				}
				
			}
			this.consumedProducts.add(product);
			return new Purchase(product, coinsAsChange);
		}
		else
			throw new ProductIsOutOfRunException(product.getName() + " is out of run!");
	}

	@Override
	public List<Coin> takeRefund() {
		return this.insertedCoinsOfCurrentPurchase.clearItems();
	}

	@Override
	public InventoriesOfVendingMachine reset(Inventory<Product> productInventory, Inventory<Coin> coinInventory) {
		productInventory.addItems(this.availableProducts.clearItems());
		coinInventory.addItems(this.availableCoins.clearItems());
		coinInventory.addItems(this.insertedCoinsOfCurrentPurchase.clearItems());
		return new InventoriesOfVendingMachine(productInventory, coinInventory);
	}


	@Override
	public List<Product> getConsumedProducts() {
		return this.consumedProducts ;
		
	}
	
}
