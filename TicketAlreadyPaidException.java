
public class TicketAlreadyPaidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int ticketNumber;

	
	public TicketAlreadyPaidException(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}


	public int getTicketNumber() {
		return ticketNumber;
	}
	
}
