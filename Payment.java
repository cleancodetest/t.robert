import java.util.SortedMap;
import java.util.TreeMap;


public class Payment {

	private SortedMap<Integer, CoinInfo> insertedCoins;
	private int totalPriceToPay;
	private int priceLeft;
	private int moneyInserted;
	
	
	public Payment(int totalPriceToPay) {
		this.totalPriceToPay = totalPriceToPay;
		insertedCoins = new TreeMap<>();
		priceLeft = 0;
		moneyInserted = 0;
	}
	
	
	
	public void coinInserted( int value)
	{
		moneyInserted += value;
		if ( value > priceLeft)
		{
			priceLeft = 0;
		}
		else
		{
			priceLeft -= value;
		}
		
		if (insertedCoins.containsKey( value))
		{
			insertedCoins.get( value).count++;
		}
		else
			insertedCoins.put( value, new CoinInfo(value, 1));
	}
	
	
	public boolean paymentDone()
	{
		return (moneyInserted >= totalPriceToPay);
	}
	
	
	public boolean moneyShallReturned()
	{
		return (getReturnMoney() > 0);
	}
	
	
	public int getReturnMoney()
	{
		if (moneyInserted > totalPriceToPay)
			return moneyInserted - totalPriceToPay;
		else
			return 0;
	}
	
	
	public SortedMap<Integer, CoinInfo> getInsertedCoins()
	{
		return insertedCoins;
	}
	
	
	public int getPrice()
	{
		return totalPriceToPay;
	}
}
