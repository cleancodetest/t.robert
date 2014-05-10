import java.util.List;


public class PaymentFlow {

	private UserInput userInput = new UserInput( CoinProcessor.instance);
	private int currTicketNumber = 0;
	private Payment currentPayment;
	
	
	public boolean doFlow()
	{	
		currTicketNumber = userInput.readTicketNumber();
		if (currTicketNumber == 0)
			return false;
		TicketHandler.instance.setCurrentTicketNumber(currTicketNumber);
		
		try
		{
			currentPayment = new Payment( TicketHandler.instance.getPriceForTicket( currTicketNumber));
			System.out.println("  Price of the ticket: " + currentPayment.getPrice());
		}
		catch (TicketAlreadyPaidException  ex)
		{
			System.out.println("Ticket has already been paid, please try again");
			return true;
		}		
		
					
		
		while (true)
		{
			int value = userInput.askForCoin();
			currentPayment.coinInserted( value);
			
			if (value > 0  &&  ! needMoreCoins( value))
			{
				break;
			}
		}
		
		return true;
	}
	
	
	
	private boolean needMoreCoins( int value)
	{
		if (currentPayment.paymentDone())
		{
			System.out.println( "  Payment done");

			try
			{
				List<CoinInfo> returnCoins = CoinProcessor.instance.calculateReturnCoins( currentPayment.getReturnMoney());
				
				if (returnCoins.size() > 0)
				{
					CoinProcessor.instance.removeReturnedCoins(returnCoins);
					System.out.println( "Returning money: ");
					for ( CoinInfo ci: returnCoins)
					{
						System.out.println( ci);
					}
				}
				else
				{
					System.out.println( "Not returning coins, exact amount was inserted");
				}
				TicketHandler.instance.setTicketPaid( TicketHandler.instance.getCurrentTicketNumber());
				CoinProcessor.instance.addInsertedCoins( currentPayment.getInsertedCoins());
			}
			catch (CannotReturnMoneyException ex)
			{
				System.out.println("Cannot return money, not enough coins");
			}

			CoinProcessor.instance.printStoredCoins();
			return false;
		}
		return true;
	}
	
	
}
