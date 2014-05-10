
public class CoinInfo implements Comparable<CoinInfo>
{
	int value;
	int count;

	public CoinInfo(int value, int count) {
		this.value = value;
		this.count = count;
	}

	@Override
	public int compareTo(CoinInfo o) {
		if (o instanceof CoinInfo)
		{
			return o.value - this.value;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "CoinInfo [value=" + value + ", count=" + count + "]";
	}
	
}
