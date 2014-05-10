
public class Ticket
{
	int number;
	int price;
	boolean paid;
	
	public Ticket(int number, int price) {
		this.number = number;
		this.price = price;
		paid = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (number != other.number)
			return false;
		return true;
	}
}