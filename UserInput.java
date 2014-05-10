import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UserInput {
	
	private CoinProcessor coinProcessor;
	
	
	public UserInput(CoinProcessor coinProcessor) {
		this.coinProcessor = coinProcessor;
	}


	public int askForCoin()
	{
		int value = 0;
		boolean invalid = false;
		System.out.println("Please insert coin: ");
		
		do
		{
			invalid = false;
			try
			{
				String valueStr = new BufferedReader( new InputStreamReader( System.in)).readLine();
				value = Integer.parseInt( valueStr);
			}
			catch (NumberFormatException | IOException e)
			{
				invalid = true; 
			}
			if ( invalid || ! coinProcessor.isValidCoin(value))
			{
				System.out.println("  Invalid coin!");
				System.out.print("  Accepted coins:");
				for (CoinProcessor.Coin coin: coinProcessor.getAcceptedCoins())
					System.out.print( " " + coin.getValue());
				System.out.println();
				continue;
			}
			return value;
		} while (true);
	}
	
	
	
	public int readTicketNumber()
	{
		System.out.print( "Please enter ticket number: " );
		
		while (true)
		{
			try
			{
				String numberStr = new BufferedReader( new InputStreamReader( System.in)).readLine();
				return Integer.parseInt( numberStr);
			}
			catch (NumberFormatException | IOException e)
			{
				System.out.println("Invalid ticket number, please try again");
			}
		}
	}
}
