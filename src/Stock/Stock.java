package Stock;

/*
 * 주식 클래스 주식 자체로는 아무런 가치도 가지지 않는다
 * 주식에는 가격이 기입된 것이 아니라 몇 주에 해당하는지 개수만이 적혀 있고
 * 가격은 StockMarket의 StockPrice클래스 에서 정해진다
 */
public class Stock {
	private String name;// 주식이 상장된 이름
	private int number;// 주식의 주

	public Stock(String n, int num) {
		setName(n);
		setNumber(num);
	}

	/*
	 * 주식 클래스 생성자
	 */
	// getter Setter 시작
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
	// getter Setter 끝

	public String toString() {
		String str = "";
		str += "주식 이름 : " + name + "\n";
		str += "주식 개수 : " + number;
		return str;
	}

	/*
	 * test를 위한 toString()
	 */
	public static void main(String args[]) {
		Stock samsung = new Stock("삼성전자", 100);
		System.out.println(samsung);
	}
}
