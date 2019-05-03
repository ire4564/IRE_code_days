package Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import Stock.Stock;

public class Account implements Serializable{
	private int accountType;
	/*
	 * int 0 ���¿��ݰ��� 1 ���ݰ��� 2 ������� 3 ���ǰ���
	 */
	private Customer owner;
	private String accountNumber;
	/*
	 * 13�ڸ��� �������� ������ �������� ���¹�ȣ ex) 3333-00-0594277 (������ : ��ÿ�) 3333������ �ٸ� ������ ���¿�
	 * �򰥸��� �ʵ��� �ϴ� �ѹ����̰� 00 : ���¿���, 01: ���࿹�� 02:���� ������ ���¸� ������ �� �ֵ��� �Ѵ�
	 */
	private int balance;
	private int disposableBalance;
	/*
	 * ���¿��� ���´� �ܾ׸�θ� ��� ���� ���ݰ��´� ������ ����Ǳ����� ����ϸ� �ܾ��� ���ڸ� ����ŭ ����� �� ���� ������´� ���� �� �ִ�
	 * �ݾ׸�ŭ ��� �����ϰ�, ��� �ϴ� ���� ���� �þ���� ���̵�
	 */
	private String openDate;
	/*
	 * 2018�� 12�� 03�� 18�� 46�� 36�� �̷��� ������ String���� �����Ѵ�
	 */
	private ArrayList<Stock> stocks;
	/*
	 * ���� ������ ���� Stock�� ������ ���� �� �ִ�. �ֽ��� ������ ���¿��� ���¿����� ������� �̷������
	 */
	private double interest;
	/*
	 * ���� ������ ��쿡�� ���ڰ� ������ ������������ �������� �ٸ��� ���� ������ ���´� ��� �������� ����.
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
		// �ʱ� �ܰ�� ���¿��� ���°� ���ݰ��°� 0�̴�
		// ���� ������ ��쿡�� ���Ŀ� �Ա��Ѵ�
	}

	// getter�� setter ����
	public void setAccountType(int type) {// accountType�� �������ش�.
		accountType = type;
	}

	public int getAccountType() {// account type�� ��ȯ�Ѵ�.
		return accountType;
	}

	public void setOwner(Customer customerName) {// ���� ������ �̸��� ���Ѵ�.
		owner = customerName;
	}

	public Customer getOwner() {// ���� ������ �̸��� ��ȯ�Ѵ�.
		return owner;
	}

	public void setAccountNumber(int accountType) {// ���¹�ȣ�� ������ ��Ģ�� ���� �����Ѵ�.
		Random r = new Random();
		String tempString = "" + r.nextInt(10000000);
		String str = "";
		for (int i = 0; i < 7 - tempString.length(); i++) {// 0�� �ʿ��� ��ŭ �߰��Ѵ�.
			str += "0";
		}
		tempString = str + tempString;
		/*
		 * ������ ���������� 1�� ������ ���� ������ �ڸ����� 7�ڸ��� �Ǵ°��� �ƴ϶� 1�ڸ��� �ȴ� �װ��� �ذ��ϱ� ���� �ڵ�
		 */
		if (("" + accountType).length() == 2) {
			accountNumber = "3333-" + getAccountType() + "-" + tempString;
		}
		// ���� Ÿ���� �������� 2�ڸ��� �� ��������
		else {
			accountNumber = "3333-0" + getAccountType() + "-" + tempString;
		}
		/*
		 * ���¹�ȣ�� �������� �������ִ� �޼ҵ� ���߿� ������ ������ 9������ �Ѿ�� ��쿡 ���� ����ؼ� ���¹�ȣ �߰��� 2�ڸ� ��������
		 * ���������� �޾ƿԴ�. �̿��� ȣȯ�� ���� ����Ѵ�
		 */
	}

	public String getAccountNumber() {// ���¹�ȣ�� String ������ ��ȯ�Ѵ�
		return accountNumber;
	}

	public void setBalance(int deposit) {// ���� �ܾ��� �ش� �ݾ����� �����Ѵ�.
		balance = deposit;
	}

	public int addBalance(int deposit) {// ���¿� �ش� �ݾ׸�ŭ ���Ѵ�(-�ٿ��� ������ ����)
		setDisposableBalance(balance += deposit);
		return (balance);
	}

	public int getBalance() {// ���� �ܾ��� ��ȯ�Ѵ�, ���� Ÿ���� 3.���� ���¶�� �ֽĵ��� ������ market���� Ȯ���Ѵ�
		//���ǰ��´� �������� ��ȯ�Ѵ�.
		return balance;
	}

	public void setDisposableBalance(int disposable) {
		disposableBalance = disposable;
		/*
		 * ������ ��� ������ �ܾ��� ��쿡 ���� �ٸ��� �� �ݾ��� BankManager���� �����Ѵ�.
		 */
	}

	public int getDisposableBalance() {// ���� ��밡�� �ݾ��� ��ȯ�Ѵ�.
		if (getAccountType() == 0) {// ���¿��ݰ��´� �ܾ� �״�� ��ȯ
			return balance;
		} else if (getAccountType() == 1) {
			return disposableBalance;
		} else {
			return disposableBalance;
		}
	}

	public void setOpenDate() {// ������ ������ ��¥�� �����Ѵ�, ���°� �����Ǹ鼭 ȣ���Ѵ�
		Calendar openDate = Calendar.getInstance();
		// �޷� ��ü�� �����Ǹ鼭 �׶��� �ð��� ���¿� ��ϵȴ�
		this.openDate = "" + openDate.get(Calendar.YEAR) + "�� " + (openDate.get(Calendar.MONTH) + 1) + "�� "
				+ openDate.get(Calendar.DATE) + "�� " + openDate.get(Calendar.HOUR) + "�� "
				+ openDate.get(Calendar.MINUTE) + "�� " + openDate.get(Calendar.SECOND) + "��";
	}

	public String getOpenDate() {// ������ ���� ��¥�� ��ȯ�Ѵ�.
		return openDate;
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
		/*
		 * ArrayList�� �����ϰ� ������ ��ȯ���ش�
		 */
	}

	public void setInterest(double i) {
		interest = i;
	}

	public double getInterest() {
		return interest;
	}

	// getter setter ��
	public String toString() {// ����� to String
		String str = "";
		if (getAccountType() == 0) {
			str += "���� ���� : ���¿��ݰ���\n";
		} else if (getAccountType() == 1) {
			str += "���� ���� : ���ݰ���\n";
		} else if (getAccountType() == 2) {
			str += "���� ���� : �������\n";
		} else if (getAccountType()==3){
			str += "���� ���� : ���ǰ���\n";
		}
		else {
			str += "���� ���� : "+getAccountType()+"\n";
		}

		str += "���� ������ : " + owner.getName() + "\n";
		str += "���¹�ȣ : " + accountNumber + "\n";
		str += "���� �ܾ� : " + balanceToString(balance) + "��" + "\n";
		str += "���� ��� ���� �ݾ� : " + balanceToString(disposableBalance) + "��" + "\n";
		str += "���� ������ : " + openDate + "\n";
		str += "���� ������ : " + interest;
		return str;

	}

	public String balanceToString(int balance) {// 10000000��(1000����)�� 10,000,000 ���� ǥ�����ִ� �޼ҵ�
		String balanceString = "" + balance;
		if (balanceString.length() > 3) {
			String returnString = "";
			for (int i = 0; i < balanceString.length() % 3; i++) {
				returnString += balanceString.charAt(i);
			}
			if (balanceString.length() % 3 != 0)
				returnString += ",";// 10, ���� �������
			for (int i = 0; i < balanceString.length() - balanceString.length() % 3; i++) {// 8-2�ϱ� 6�� �ݺ���
				returnString += balanceString.charAt(i + balanceString.length() % 3);// 2��° �ε������� (0,1,2)����ϹǷ� 2�� ���Ѵ�

				if ((i % 3) == 2 && i != (balanceString.length() - balanceString.length() % 3) - 1) {
					// 10,000,000 (012345) 2,5,8��° �ε������� �ĸ��� ��������
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
