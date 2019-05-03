package BankServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Client.Account;
import Client.Customer;
import Stock.StockMarket;

public class BankServer implements Serializable{
	// �⺻ ���� ������ �ʵ�
	private LinkedList<Customer> customerList = new LinkedList<>(); // customerŬ������ ������ ��� �ִ� LinkedList
	private LinkedList<Account> accountList = new LinkedList<>(); // accountŬ������ ������ ��� �ִ� LinkedList
	private double[] interest; // �� ���� ������ ���� �������� �����ϰ� �ִ� �迭. ���������� ������ �����Ƿ� Linked�� ����
	private StockServer stockServer = new StockServer(new StockMarket());
	private int portNum = 5050;
	public static final int MAXCLIENT = 1000;
	private boolean serverRun = true;

	// ���� ������
	public BankServer() {
		interest = new double[3];
		interest[0] = 0.5;
		interest[1] = 2.5;
		interest[2] = 3.5;
		getAccountData();
		getCustomerData();
		getStockServerData();
		System.out.println("���� ������ Ȯ��");
		System.out.println("�� ����Ʈ ũ�� : "+customerList.size());
		System.out.println("���� ����Ʈ ũ�� : "+accountList.size());
	}

	// ������ �޼ҵ�
	public void setServer(boolean b) { // �޴��� ���� �޼ҵ�. ���� �ݴ� �޼ҵ�
		serverRun = b;
	}

	public void setStockServer(StockServer stockServer) {
		this.stockServer = stockServer;
	}

	public Task makeTask(Socket socket) { // ������ ����
		return new Task(socket);
	}

	// Ž�� �޼ҵ�
	public int CustomerListNum(Customer customer) { // �� Ž�� �� ��ġ Ȯ��
		return customerList.indexOf(customer);
	}

	public int AccountListNum(Account account) { // ���� Ž�� �� ��ġ Ȯ��
		return accountList.indexOf(account);
	}

	public Customer searchID(String ID) { // ���̵� ���� ���� Ž��
		System.out.println("���̵� ���翩�� �ľ���...");
		for (int i = 0; i < customerList.size(); i++) {
			System.out.println(i + "��° �˻���");
			if (customerList.get(i).getID().equals(ID))
				return customerList.get(i);
		}
		System.out.println("���̵� ����x");
		return null;
	}

	public Customer Login(String ID, String PW) { // �α��� �޼ҵ�
		Customer customer = searchID(ID);
		System.out.println("��й�ȣ Ȯ���� ...");
		if (customer != null)
			if (customer.getPW().equals(PW))
				return customer;
		return null;
	}

	// ������ ���� �� �߰�, ����
	/*
	 * public synchronized void setCustomerList(int index, Customer customer) {
	 * customerList.set(index, customer); }
	 */

	public synchronized void newCustomer(Customer customer) {
		customerList.add(customer);
	}

	public synchronized void removeCustomer(Customer customer) {
		customerList.remove(CustomerListNum(customer));
	}

	public LinkedList<Customer> printCustomerList() {
		return customerList;
	}

	/*
	 * public synchronized void setAccountList(int index, Account account) {
	 * accountList.set(index, account); }
	 */

	public synchronized void newAccount(Account account) {
		accountList.add(account);
		account.getOwner().addAccount(account);
	}

	public synchronized void removeAccount(Account account) {
		accountList.remove(AccountListNum(account));
	}

	public LinkedList<Account> printAccountList() {
		return accountList;
	}

	// ������ �޼ҵ�
	public int getPortNum() {
		return portNum;
	}

	public boolean getServer() {
		return serverRun;
	}

	public LinkedList<Customer> getCustomerList() {
		return customerList;
	}

	public LinkedList<Account> getAccountList() {
		return accountList;
	}

	public StockServer getStockServer() {
		return stockServer;
	}

	// �� ���Ͽ��� ������ �ҷ����� �� �����ϱ� �޼ҵ�

	void setCustomerData() { // Customer �����͸� �����ϴ� �޼ҵ�
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Coustomer.db"))) {
			// Customer.db ���� �� ����⸦ ���� outputStream ����
			out.writeObject(customerList); // �׵��� ����� �����͸� ������Ѵ�.
		} catch (IOException e) {
			System.out.println("Customer.db�� ����� ����!");
			e.printStackTrace();
		}
	}

	private void getCustomerData() { // Customer �����͸� �������� �޼ҵ�
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Coustomer.db"))) {
			try {
				customerList = (LinkedList<Customer>) in.readObject();
			} catch (ClassNotFoundException e) { // Customer.db�� �ڷ� ����°� �������� ��(���� ������X)
				setCustomerData(); // setCustomerData()�޼ҵ带 �̿��Ͽ� customerList�� �����ϰ� ����
			}
		} catch (FileNotFoundException e) { // Customer.db�� �����Ǿ� ���� ���� ���
			setCustomerData(); // setCustomerData()�޼ҵ带 �̿��Ͽ� �������ش�.
		} catch (IOException e) {
			System.out.println("Customer.db���� ���� �ҷ����� ����!");
			e.printStackTrace();
		}
	}

	void setAccountData() { // Account �����͸� �����ϴ� �޼ҵ�
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Account.db"))) {
			// Account.db ���� �� ����⸦ ���� outputStream ����
			out.writeObject(accountList); // �׵��� ����� �����͸� ������Ѵ�.
		} catch (IOException e) {
			System.out.println("Account.db�� ����� ����!");
			e.printStackTrace();
		}
	}

	private void getAccountData() { // Account �����͸� �������� �޼ҵ�
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Account.db"))) {
			try {
				accountList = (LinkedList<Account>) in.readObject();
			} catch (ClassNotFoundException e) { // Account.db�� �ڷ� ����°� �������� ��(���� ������X)
				setAccountData(); // setAccountData()�޼ҵ带 �̿��Ͽ� accountList�� �����ϰ� ����
			}
		} catch (FileNotFoundException e) { // Account.db�� �����Ǿ� ���� ���� ���
			setAccountData(); // setAccountData()�޼ҵ带 �̿��Ͽ� �������ش�.
		} catch (IOException e) {
			System.out.println("Account.db���� �ҷ����� ����!");
			e.printStackTrace();
		}
	}

	private void setStockServerData() { // stockServer �����͸� �����ϴ� �޼ҵ�
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("StockServer.dat"))) {
			// StockServer.db ���� �� ����⸦ ���� outputStream ����
			out.writeObject(stockServer); // �׵��� ����� �����͸� ������Ѵ�.
		} catch (IOException e) {
			System.out.println("StockServer.db�� ����� ����!");
			e.printStackTrace();
		}
	}

	private void getStockServerData() { // StockServer �����͸� �������� �޼ҵ�
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("StockServer.dat"))) {
			try {
				stockServer = (StockServer) in.readObject();
			} catch (ClassNotFoundException e) { // StockMarket.db�� �ڷ� ����°� �������� ��(���� ������X)
				setStockServerData(); // setStockMarketData()�޼ҵ带 �̿��Ͽ� StockMarket�� �����ϰ� ����
			}
		} catch (FileNotFoundException e) { // StockServer.db�� �����Ǿ� ���� ���� ���
			setStockServerData(); // setStockServerData()�޼ҵ带 �̿��Ͽ� �������ش�.
		} catch (IOException e) {
			System.out.println("StockServer.db���� ���� �ҷ����� ����!");
			e.printStackTrace();
		}
	}

	/*
	 * �ֽ� ���� Ŭ���� �ֽ� ���忡���� �ֽĵ��� �ü��� �����Ͽ� ���Ӿ��� ������Ų��
	 */

	class Task implements Runnable {
		private Socket socket = null;
		private ObjectInputStream input = null;
		private ObjectOutputStream output = null;
		private boolean end = false;
		private Customer User;

		public Task(Socket socket) {
			System.out.println("Task������ ����");
			this.socket = socket;
		}

		public void run() {
			System.out.println("run()�޼ҵ� ����");
			       try {
					input = new ObjectInputStream(socket.getInputStream());
					System.out.println("input �غ� �Ϸ�");
					output = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("output �غ� �Ϸ�");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			while (!end) {
				try {
					// ���� �����κ��� �����͸� �޴´�.
					
					int c = (Integer) input.readObject();
					System.out.println("��� : " + c);
					switch (c) {
					case 0: // �α��� ��û
						System.out.println("�α��� ����ġ ��");
						Object ID, PW;
						ID = input.readObject();
						System.out.println("ID ������ ���� : " + ID.toString());
						PW = input.readObject();
						System.out.println("PW ������ ����");
						User = Login(ID.toString(), PW.toString()); // �α��� ���� ������ ���� ����ǹǷ� �����ȴ�.
						System.out.println("��� ����!");
						if (User != null) {
							output.writeObject(0);
							output.writeObject(User);
						} else
							output.writeObject(1);
						break;

					case 1: // ������ü
						Account account1, account2 = null; // ������� �������, �Ա�����
						int money = 0; // ���� ��
						account1 = (Account) input.readObject();
						money = (Integer) input.readObject();
						if (account1.getBalance() < money) {
							output.writeObject(1); // ���� �������� �˷���
							break;
						}
						output.writeObject(0);
						output.writeObject(account1); // ���� ���� �� �۾� ���
						account2 = (Account) input.readObject();
						if (AccountListNum(account2) == -1) {
							output.writeObject(1); // ���� ���°� ����
							break;
						}
						output.writeObject(0);
						output.writeObject(account1);
						if ((Integer) input.readObject() == 1) {
							break;
						}
						removeAccount(account1);
						account1.addBalance(-1 * money);
						newAccount(account1);
						removeAccount(account2);
						account2.addBalance(money);
						newAccount(account2);
						output.writeObject(account1);
						break;
					case 2: // �ֽĽü� ��û

						while ((boolean) input.readObject()) {
							try {
								output.writeObject(stockServer.getMarket().getStockPrice());
								Thread.sleep(1000);
								output.reset();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					case 3: // ����
						output.writeObject(interest[2]); // ���� ���� ����
						if ((Integer) input.readObject() == -1) // ���� ���
							break;
						else {
							int loan = (Integer) input.readObject();
							if ((Integer) input.readObject() == -1) // ���� ���
								break;
							else {
								Account account = (Account) input.readObject();
								User.getAccount(0).addBalance(loan);
								removeAccount(account);
								account.addBalance(loan);
								newAccount(account);
								break;
							}
						}
					case 4: // �� ��ȯ
						Account account3, loan = null; // ������� �������, ��������
						int debt = 0; // ���� ��
						account3 = (Account) input.readObject();
						debt = (Integer) input.readObject();
						if (account3.getBalance() < debt) {
							output.writeObject(1); // ���� �������� �˷���
							break;
						}
						output.writeObject(0);
						output.writeObject(account3); // ���� ���� �� �۾� ���
						loan = User.getAccount(0);
						if (loan.getBalance() <= 0) {
							output.writeObject(1); // ���� ���� ����
							break;
						}
						output.writeObject(0);
						output.writeObject(loan);
						if ((Integer) input.readObject() == 1) {
							break;
						}
						removeAccount(account3);
						if (loan.getBalance() < debt) {
							account3.addBalance(-1 * debt);
							newAccount(account3);
							User.getAccount(0).addBalance(-1 * debt);
						} else {
							account3.addBalance(-1 * loan.getBalance());
							newAccount(account3);
							User.getAccount(0).addBalance(-1 * loan.getBalance());
						}
						output.writeObject(User.getAccount(0));
						break;

					case 5: // �� ����
						Customer customer = (Customer) input.readObject();
						if (CustomerListNum(customer) != -1) {
							newCustomer(customer);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 6: // ���� ����
						Account account = (Account) input.readObject();
						if (AccountListNum(account) != -1) {
							newAccount(account);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 7: // �� ����
						Customer removeCustomer = (Customer) input.readObject();
						if (CustomerListNum(removeCustomer) != -1) {
							removeCustomer(removeCustomer);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 8: // ���� ����
						Account removeAccount = (Account) input.readObject();
						if (AccountListNum(removeAccount) != -1) {
							removeAccount(removeAccount);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 9: // ���� ����
						serverRun = false;
						break;
					case 10: // �� ��� ���� / ���� ���� ��� ����� ���� �� ��� ����
						output.writeObject(customerList);
						break;
					case 11: // ���� ��� ����
						output.writeObject(accountList);
						break;
					case 12: // ������ ����
						output.writeObject(interest);
						interest = (double[]) input.readObject();
						break;
					case 13: // �����
						break;
					}
					//end = true;
					// ���� ������ �����͸� �����Ѵ�.
					//input = null;
					//output = null;

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// ���� main �޼ҵ�
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("���� �����͸� �ҷ��ɴϴ�...");
		BankServer server = new BankServer();
		System.out.println("���� ������ �ҷ����� �Ϸ�!");
		System.out.println("Ŭ���̾�Ʈ���� ����� ���� ������Ĺ�� �����մϴ�...\n���� port��ȣ : " + server.getPortNum());
		ServerSocket serverSocket = new ServerSocket(server.getPortNum(), MAXCLIENT);
		System.out.println("���� ��Ĺ ���� �Ϸ�!");
		ExecutorService es = Executors.newCachedThreadPool(); // Ŭ���̾�Ʈ���� ����� ���� ������Ǯ ����
		Thread stockServer = new Thread(server.getStockServer());
		System.out.println("StockServer ����");
		stockServer.start();
		Socket clientSocket = null;
		/*
		Customer c = new Customer("��ÿ�", "201802070", "990510", "��õ��", "010-5402-0806");
		server.getCustomerList().add(c);
		server.newAccount(new Account(0, c));
		c = new Customer("������", "201802172", "990312", "��ȭ��", "010-2748-9933");
		server.getCustomerList().add(c);
		server.newAccount(new Account(0, c));
		server.getAccountList().get(1).addBalance(1000000000);
		System.out.println(server.getAccountList().get(0).getAccountNumber());
		*/
		while (server.getServer()) { // ������ Ŭ���̾�Ʈ���� ���

			try {
				System.out.println("Ŭ���̾�Ʈ���� ���� �õ���...");
				clientSocket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�!\n�۾��� �ǽ��մϴ�.");
				es.submit(server.makeTask(clientSocket));
			} catch (IOException e) {
			}finally{
				server.setAccountData();
				server.setCustomerData();
				server.setStockServerData();
			}
		}
		

		System.out.println("���� �ݴ���...");
		es.shutdown();

		System.out.println("Ŭ���̾�Ʈ�� �۾��� ��� �������ϴ�.\n������Ǯ�� �ݽ��ϴ�.");
		System.out.println("������Ĺ�� �ݽ��ϴ�.");
		server.getStockServer().getMarket().setOpen(false);
		System.out.println("�ֽ� ������ �ݽ��ϴ�.");
																																							stockServer.join();
		serverSocket.close();
		server.setAccountData();
		server.setCustomerData();
		server.setStockServerData();
		System.out.println("������ ���� �Ϸ�. ���� ������ �����մϴ�.");

	}
}
