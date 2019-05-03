package Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import Stock.Stock;

public class Account implements Serializable{
	private int accountType;
	/*
	 * int 0 당좌예금계좌 1 적금계좌 2 대출계좌 3 증권계좌
	 */
	private Customer owner;
	private String accountNumber;
	/*
	 * 13자리의 하이픈을 포함한 정수형의 계좌번호 ex) 3333-00-0594277 (예금주 : 김시온) 3333까지는 다른 은행의 계좌와
	 * 헷갈리지 않도록 하는 넘버링이고 00 : 당좌예금, 01: 저축예금 02:대출 등으로 계좌를 구분할 수 있도록 한다
	 */
	private int balance;
	private int disposableBalance;
	/*
	 * 당좌예금 계좌는 잔액모두를 출금 가능 적금계좌는 적금이 만기되기전에 출금하면 잔액중 이자를 뺀만큼 출금할 수 있음 대출계좌는 빌릴 수 있는
	 * 금액만큼 출금 가능하고, 출금 하는 순간 돈은 늘어나지만 빚이됨
	 */
	private String openDate;
	/*
	 * 2018년 12월 03일 18시 46분 36초 이러한 형식의 String으로 저장한다
	 */
	private ArrayList<Stock> stocks;
	/*
	 * 증권 계좌인 경우는 Stock을 가지고 있을 수 있다. 주식의 구입은 당좌예금 계좌에서의 출금으로 이루어진다
	 */
	private double interest;
	/*
	 * 증권 계좌의 경우에는 이자가 없으며 계좌종류마다 이자율이 다르다 같은 종류의 계좌는 모두 이자율이 같다.
	 */

	public Account(int accountType, Customer customer) {
		setAccountType(accountType);
		setOwner(customer);
		setAccountNumber(accountType);
		setBalance(0);
		setDisposableBalance(balance);
		setOpenDate();
		if(accountType ==3) {
			stocks = new ArrayList<>();
		}
		// 초기 잔고는 당좌예금 계좌건 적금계좌건 0이다
		// 대출 계좌의 경우에는 추후에 입금한다
	}

	// getter와 setter 시작
	public void setAccountType(int type) {// accountType을 설정해준다.
		accountType = type;
	}

	public int getAccountType() {// account type을 반환한다.
		return accountType;
	}

	public void setOwner(Customer customerName) {// 계좌 주인의 이름을 정한다.
		owner = customerName;
	}

	public Customer getOwner() {// 계좌 주인의 이름을 반환한다.
		return owner;
	}

	public void setAccountNumber(int accountType) {// 계좌번호를 일정한 규칙에 따라 생성한다.
		Random r = new Random();
		String tempString = "" + r.nextInt(10000000);
		String str = "";
		for (int i = 0; i < 7 - tempString.length(); i++) {// 0을 필요한 만큼 추가한다.
			str += "0";
		}
		tempString = str + tempString;
		/*
		 * 난수로 생성했을때 1이 나오는 경우는 마지막 자리수가 7자리가 되는것이 아니라 1자리가 된다 그것을 해결하기 위한 코드
		 */
		if (("" + accountType).length() == 2) {
			accountNumber = "3333-" + getAccountType() + "-" + tempString;
		}
		// 계좌 타입이 언젠가는 2자리가 될 수도있음
		else {
			accountNumber = "3333-0" + getAccountType() + "-" + tempString;
		}
		/*
		 * 계좌번호를 랜덤으로 생성해주는 메소드 나중에 계좌의 종류가 9가지를 넘어가는 경우에 대해 대비해서 계좌번호 중간에 2자리 정수형의
		 * 계좌종류를 받아왔다. 이와의 호환성 또한 고려한다
		 */
	}

	public String getAccountNumber() {// 계좌번호를 String 형으로 반환한다
		return accountNumber;
	}

	public void setBalance(int deposit) {// 계좌 잔액을 해당 금액으로 설정한다.
		balance = deposit;
	}

	public int addBalance(int deposit) {// 계좌에 해당 금액만큼 더한다(-붙여서 뺄수도 있음)
		setDisposableBalance(balance += deposit);
		return (balance);
	}

	public int getBalance() {// 계좌 잔액을 반환한다, 계좌 타입이 3.증권 계좌라면 주식들의 가격은 market에서 확인한다
		//증권계좌는 예수금을 반환한다.
		return balance;
	}

	public void setDisposableBalance(int disposable) {
		disposableBalance = disposable;
		/*
		 * 계좌의 사용 가능한 잔액은 경우에 따라 다르다 그 금액은 BankManager에서 구현한다.
		 */
	}

	public int getDisposableBalance() {// 계좌 사용가능 금액을 반환한다.
		if (getAccountType() == 0) {// 당좌예금계좌는 잔액 그대로 반환
			return balance;
		} else if (getAccountType() == 1) {
			return disposableBalance;
		} else {
			return disposableBalance;
		}
	}

	public void setOpenDate() {// 계좌의 개설된 날짜를 설정한다, 계좌가 생성되면서 호출한다
		Calendar openDate = Calendar.getInstance();
		// 달력 객체가 생성되면서 그때의 시간이 계좌에 기록된다
		this.openDate = "" + openDate.get(Calendar.YEAR) + "년 " + (openDate.get(Calendar.MONTH) + 1) + "월 "
				+ openDate.get(Calendar.DATE) + "일 " + openDate.get(Calendar.HOUR) + "시 "
				+ openDate.get(Calendar.MINUTE) + "분 " + openDate.get(Calendar.SECOND) + "초";
	}

	public String getOpenDate() {// 계좌의 개설 날짜를 반환한다.
		return openDate;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
		/*
		 * ArrayList에 접근하고 싶을때 반환해준다
		 */
	}

	public void setInterest(double i) {
		interest = i;
	}

	public double getInterest() {
		return interest;
	}

	// getter setter 끝
	public String toString() {// 시험용 to String
		String str = "";
		if (getAccountType() == 0) {
			str += "계좌 종류 : 당좌예금계좌\n";
		} else if (getAccountType() == 1) {
			str += "계좌 종류 : 적금계좌\n";
		} else if (getAccountType() == 2) {
			str += "계좌 종류 : 대출계좌\n";
		} else if (getAccountType()==3){
			str += "계좌 종류 : 증권계좌\n";
		}
		else {
			str += "계좌 종류 : "+getAccountType()+"\n";
		}

		str += "계좌 소유자 : " + owner.getName() + "\n";
		str += "계좌번호 : " + accountNumber + "\n";
		str += "계좌 잔액 : " + balanceToString(balance) + "원" + "\n";
		str += "계좌 출금 가능 금액 : " + balanceToString(disposableBalance) + "원" + "\n";
		str += "계좌 개설일 : " + openDate + "\n";
		str += "계좌 이자율 : " + interest;
		return str;

	}

	public String balanceToString(int balance) {// 10000000원(1000만원)을 10,000,000 으로 표시해주는 메소드
		String balanceString = "" + balance;
		if (balanceString.length() > 3) {
			String returnString = "";
			for (int i = 0; i < balanceString.length() % 3; i++) {
				returnString += balanceString.charAt(i);
			}
			if (balanceString.length() % 3 != 0)
				returnString += ",";// 10, 까지 만들어짐
			for (int i = 0; i < balanceString.length() - balanceString.length() % 3; i++) {// 8-2니까 6번 반복함
				returnString += balanceString.charAt(i + balanceString.length() % 3);// 2번째 인덱스부터 (0,1,2)써야하므로 2를 더한다

				if ((i % 3) == 2 && i != (balanceString.length() - balanceString.length() % 3) - 1) {
					// 10,000,000 (012345) 2,5,8번째 인덱스에서 컴마를 찍어줘야함
					returnString += ",";
				}
			}

			return returnString;
		} else {
			return balanceString;
		}
	}

	public static void main(String[] args) {
		
	}
}
