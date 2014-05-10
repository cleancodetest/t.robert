import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TicketHandler {
	
	public static final TicketHandler instance = new TicketHandler();
	private Map<Integer, Ticket> tickets = new HashMap<>();
	private Random rand = new Random();
	private int currTicketNumber = 0;
	
	private TicketHandler()
	{
		
	}
	
	public void setCurrentTicketNumber(int number)
	{
		currTicketNumber = number;
	}
	
	public int getCurrentTicketNumber()
	{
		return currTicketNumber;
	}
	
	public int getCurrentPrice()
	{
		return getPriceForTicket( currTicketNumber);
	}
	
	
	public int getPriceForTicket(int number)
	{
		Ticket curr = null;
		
		if (tickets.containsKey( number))
		{
			curr = tickets.get( number);
			if (curr.paid) {
				throw new TicketAlreadyPaidException( number);
			}
			return curr.price;
		}
		else
		{
			Ticket newTicket = new Ticket( number, Math.abs( 200 + (rand.nextInt() % 100) * 20));
			tickets.put( number, newTicket);
			return newTicket.price;
		}
			 
	}
	
	public void setTicketPaid(int number)
	{
		if (tickets.containsKey( number))
		{
			tickets.get( number).paid = true;
		}
	}
}
