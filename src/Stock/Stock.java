package Stock;

/*
 * �ֽ� Ŭ���� �ֽ� ��ü�δ� �ƹ��� ��ġ�� ������ �ʴ´�
 * �ֽĿ��� ������ ���Ե� ���� �ƴ϶� �� �ֿ� �ش��ϴ��� �������� ���� �ְ�
 * ������ StockMarket�� StockPriceŬ���� ���� ��������
 */
public class Stock {
	private String name;// �ֽ��� ����� �̸�
	private int number;// �ֽ��� ��

	public Stock(String n, int num) {
		setName(n);
		setNumber(num);
	}

	/*
	 * �ֽ� Ŭ���� ������
	 */
	// getter Setter ����
	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setNumber(int n) {
		number = n;
	}

	public int getNumber() {
		return number;
	}
	// getter Setter ��

	public String toString() {
		String str = "";
		str += "�ֽ� �̸� : " + name + "\n";
		str += "�ֽ� ���� : " + number;
		return str;
	}

	/*
	 * test�� ���� toString()
	 */
	public static void main(String args[]) {
		Stock samsung = new Stock("�Ｚ����", 100);
		System.out.println(samsung);
	}
}
