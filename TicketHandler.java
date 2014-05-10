import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class TicketHandler {
	
	public static final TicketHandler instance = new TicketHandler();
	private Set<Ticket> ticketList = new HashSet<>();
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
		for (Ticket t: ticketList)
		{
			if (t.number == number)
			{
				curr = t;
				if (t.paid)
				{
					throw new TicketAlreadyPaidException( number);
				}
				break;
			}
		}
		
		if ( curr != null)
			return curr.price;
		else
		{
			Ticket newTicket = new Ticket( number, Math.abs( 200 + (rand.nextInt() % 100) * 20));
			ticketList.add( newTicket);
			return newTicket.price;
		}
			 
	}
	
	public void setTicketPaid(int number)
	{
		for (Ticket t: ticketList)
		{
			if (t.number == number)
			{
				t.paid = true;
				return;
			}
		}
	}
}
