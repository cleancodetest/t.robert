
public class CannotReturnMoneyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int ticketNumber;

	public CannotReturnMoneyException() {
		super( "Cannot return money. Ticket number: " + TicketHandler.instance.getCurrentTicketNumber() + 
				", total price: " + TicketHandler.instance.getCurrentPrice() + "\nAvailable coins: \n" + CoinProcessor.instance );
		this.ticketNumber = TicketHandler.instance.getCurrentTicketNumber();
	}
	
	int getPaymentId()
	{
		return ticketNumber;
	}
}
