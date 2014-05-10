import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;


public class CoinProcessor {

	public class Coin
	{
		private int value;
		
		public Coin( int value) {
			this.value = value;
		}
		
		public int getValue() { 
			return value;
		}
	}
	
	
	public static final CoinProcessor instance = new CoinProcessor();
	private Coin[] acceptedCoins = { new Coin(5), new Coin(10), new Coin(20), new Coin(50), 
			new Coin(100), new Coin(200), new Coin(500), new Coin(1000), new Coin(2000), new Coin(5000) };
	private SortedMap<Integer, CoinInfo> storedCoins;
	
	
	private CoinProcessor()
	{
		storedCoins = new TreeMap<>( Collections.reverseOrder());
		storedCoins.put( 5, new CoinInfo( 5, 50));
		storedCoins.put( 10, new CoinInfo( 10, 50));
		storedCoins.put( 20, new CoinInfo( 20, 50));
		storedCoins.put( 50, new CoinInfo( 50, 50));
		storedCoins.put( 100, new CoinInfo( 100, 50));
		storedCoins.put( 200, new CoinInfo( 200, 50));
	}
	
	
	
	public boolean isValidCoin( int value)
	{
		for (Coin coin: acceptedCoins)
		{
			if (coin.getValue() == value)
				return true;
		}
		return false;
	}
	
	
	public Coin[] getAcceptedCoins()
	{
		return acceptedCoins;
	}
	
	
	public List<CoinInfo> calculateReturnCoins( int returnMoney)
	{
		SortedMap<Integer, CoinInfo> tempStoredCoins = new TreeMap<>( Collections.reverseOrder());
		for ( Entry<Integer, CoinInfo> entry: storedCoins.entrySet())
		{
			tempStoredCoins.put( entry.getKey(), new CoinInfo( entry.getValue().value, entry.getValue().count));
		}
		
		List<CoinInfo> returnCoins = new ArrayList<>();	
		boolean foundCoin = false;
		do
		{
			foundCoin = false;
			for (CoinInfo ci: tempStoredCoins.values())
			{
				if (ci.value <= returnMoney  &&  ci.count > 0)
				{
					foundCoin = true;
					ci.count--;
					returnMoney -= ci.value;
					
					boolean found = false;
					
					for (CoinInfo cRet: returnCoins)
					{
						if (cRet.value == ci.value)
						{
							cRet.count++;
							found = true;
							break;
						}
					}
					if ( ! found)
					{
						returnCoins.add( new CoinInfo( ci.value, 1));
					}
					break;
				}
			}
			
			if ( ! foundCoin  &&  returnMoney > 0)
			{
				throw new CannotReturnMoneyException();
			}
			
		} while (foundCoin  &&  returnMoney > 0);
		return returnCoins;
	}
	
	
	
	public void addInsertedCoins( Map<Integer, CoinInfo> insertedCoins)
	{
		// Store inserted coins in vault:
		for (CoinInfo ci: insertedCoins.values())
		{
			if (storedCoins.containsKey( ci.value))
			{
				storedCoins.get( ci.value).count += ci.count;
			}
			else
				storedCoins.put( ci.value, new CoinInfo( ci.value, ci.count));
		}
	}
	
	
	public void removeReturnedCoins( List<CoinInfo> returnedCoins)
	{
		for (CoinInfo ci: returnedCoins)
		{
			if (storedCoins.containsKey( ci.value))
			{
				storedCoins.get( ci.value).count -= ci.count;
			}
		}
	}
	
	
	/**
	 * This method is for debugging purpose, it should not be called from
	 * production environment.
	 */
	public void printStoredCoins()
	{
		System.out.println(" STORED COINS: ");
		System.out.println( toString());
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		for (CoinInfo ci: storedCoins.values())
		{
			ret.append( ci );
			ret.append( "\n");
		}
		return ret.toString();
	}
}
