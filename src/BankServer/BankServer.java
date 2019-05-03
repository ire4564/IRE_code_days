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
	// 기본 저장 데이터 필드
	private LinkedList<Customer> customerList = new LinkedList<>(); // customer클래스의 정보를 담고 있는 LinkedList
	private LinkedList<Account> accountList = new LinkedList<>(); // account클래스의 정보를 담고 있는 LinkedList
	private double[] interest; // 각 통장 유형에 따른 이자율을 저장하고 있는 배열. 통장유형이 정해져 있으므로 Linked로 만듦
	private StockServer stockServer = new StockServer(new StockMarket());
	private int portNum = 5050;
	public static final int MAXCLIENT = 1000;
	private boolean serverRun = true;

	// 서버 생성자
	public BankServer() {
		interest = new double[3];
		interest[0] = 0.5;
		interest[1] = 2.5;
		interest[2] = 3.5;
		getAccountData();
		getCustomerData();
		getStockServerData();
		System.out.println("받은 데이터 확인");
		System.out.println("고객 리스트 크기 : "+customerList.size());
		System.out.println("계좌 리스트 크기 : "+accountList.size());
	}

	// 설정자 메소드
	public void setServer(boolean b) { // 메니저 전용 메소드. 서버 닫는 메소드
		serverRun = b;
	}

	public void setStockServer(StockServer stockServer) {
		this.stockServer = stockServer;
	}

	public Task makeTask(Socket socket) { // 스레드 생성
		return new Task(socket);
	}

	// 탐색 메소드
	public int CustomerListNum(Customer customer) { // 고객 탐색 및 위치 확인
		return customerList.indexOf(customer);
	}

	public int AccountListNum(Account account) { // 통장 탐색 및 위치 확인
		return accountList.indexOf(account);
	}

	public Customer searchID(String ID) { // 아이디 존재 여부 탐색
		System.out.println("아이디 존재여부 파악중...");
		for (int i = 0; i < customerList.size(); i++) {
			System.out.println(i + "번째 검색중");
			if (customerList.get(i).getID().equals(ID))
				return customerList.get(i);
		}
		System.out.println("아이디 존재x");
		return null;
	}

	public Customer Login(String ID, String PW) { // 로그인 메소드
		Customer customer = searchID(ID);
		System.out.println("비밀번호 확인중 ...");
		if (customer != null)
			if (customer.getPW().equals(PW))
				return customer;
		return null;
	}

	// 데이터 변경 및 추가, 삭제
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

	// 접근자 메소드
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

	// 각 파일에서 데이터 불러오기 및 저장하기 메소드

	void setCustomerData() { // Customer 데이터를 저장하는 메소드
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Coustomer.db"))) {
			// Customer.db 생성 및 덮어쓰기를 위해 outputStream 생성
			out.writeObject(customerList); // 그동안 저장된 데이터를 덮어쓰기한다.
		} catch (IOException e) {
			System.out.println("Customer.db에 덮어쓰기 실패!");
			e.printStackTrace();
		}
	}

	private void getCustomerData() { // Customer 데이터를 가져오는 메소드
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Coustomer.db"))) {
			try {
				customerList = (LinkedList<Customer>) in.readObject();
			} catch (ClassNotFoundException e) { // Customer.db에 자료 덮어쓰는게 실패했을 때(저장 데이터X)
				setCustomerData(); // setCustomerData()메소드를 이용하여 customerList를 저장하게 만듦
			}
		} catch (FileNotFoundException e) { // Customer.db가 생성되어 있지 않을 경우
			setCustomerData(); // setCustomerData()메소드를 이용하여 생성해준다.
		} catch (IOException e) {
			System.out.println("Customer.db에서 정보 불러오기 실패!");
			e.printStackTrace();
		}
	}

	void setAccountData() { // Account 데이터를 저장하는 메소드
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Account.db"))) {
			// Account.db 생성 및 덮어쓰기를 위해 outputStream 생성
			out.writeObject(accountList); // 그동안 저장된 데이터를 덮어쓰기한다.
		} catch (IOException e) {
			System.out.println("Account.db에 덮어쓰기 실패!");
			e.printStackTrace();
		}
	}

	private void getAccountData() { // Account 데이터를 가져오는 메소드
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Account.db"))) {
			try {
				accountList = (LinkedList<Account>) in.readObject();
			} catch (ClassNotFoundException e) { // Account.db에 자료 덮어쓰는게 실패했을 때(저장 데이터X)
				setAccountData(); // setAccountData()메소드를 이용하여 accountList를 저장하게 만듦
			}
		} catch (FileNotFoundException e) { // Account.db가 생성되어 있지 않을 경우
			setAccountData(); // setAccountData()메소드를 이용하여 생성해준다.
		} catch (IOException e) {
			System.out.println("Account.db에서 불러오기 실패!");
			e.printStackTrace();
		}
	}

	private void setStockServerData() { // stockServer 데이터를 저장하는 메소드
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("StockServer.dat"))) {
			// StockServer.db 생성 및 덮어쓰기를 위해 outputStream 생성
			out.writeObject(stockServer); // 그동안 저장된 데이터를 덮어쓰기한다.
		} catch (IOException e) {
			System.out.println("StockServer.db에 덮어쓰기 실패!");
			e.printStackTrace();
		}
	}

	private void getStockServerData() { // StockServer 데이터를 가져오는 메소드
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("StockServer.dat"))) {
			try {
				stockServer = (StockServer) in.readObject();
			} catch (ClassNotFoundException e) { // StockMarket.db에 자료 덮어쓰는게 실패했을 때(저장 데이터X)
				setStockServerData(); // setStockMarketData()메소드를 이용하여 StockMarket를 저장하게 만듦
			}
		} catch (FileNotFoundException e) { // StockServer.db가 생성되어 있지 않을 경우
			setStockServerData(); // setStockServerData()메소드를 이용하여 생성해준다.
		} catch (IOException e) {
			System.out.println("StockServer.db에서 정보 불러오기 실패!");
			e.printStackTrace();
		}
	}

	/*
	 * 주식 시장 클래스 주식 시장에서는 주식들의 시세를 생성하여 끊임없이 변동시킨다
	 */

	class Task implements Runnable {
		private Socket socket = null;
		private ObjectInputStream input = null;
		private ObjectOutputStream output = null;
		private boolean end = false;
		private Customer User;

		public Task(Socket socket) {
			System.out.println("Task생성자 생성");
			this.socket = socket;
		}

		public void run() {
			System.out.println("run()메소드 실행");
			       try {
					input = new ObjectInputStream(socket.getInputStream());
					System.out.println("input 준비 완료");
					output = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("output 준비 완료");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			while (!end) {
				try {
					// 먼저 서버로부터 데이터를 받는다.
					
					int c = (Integer) input.readObject();
					System.out.println("출력 : " + c);
					switch (c) {
					case 0: // 로그인 요청
						System.out.println("로그인 스위치 온");
						Object ID, PW;
						ID = input.readObject();
						System.out.println("ID 데이터 받음 : " + ID.toString());
						PW = input.readObject();
						System.out.println("PW 데이터 받음");
						User = Login(ID.toString(), PW.toString()); // 로그인 이후 스레드 내에 저장되므로 유지된다.
						System.out.println("결과 전송!");
						if (User != null) {
							output.writeObject(0);
							output.writeObject(User);
						} else
							output.writeObject(1);
						break;

					case 1: // 계좌이체
						Account account1, account2 = null; // 순서대로 출금통장, 입금통장
						int money = 0; // 보낼 돈
						account1 = (Account) input.readObject();
						money = (Integer) input.readObject();
						if (account1.getBalance() < money) {
							output.writeObject(1); // 돈이 부족함을 알려줌
							break;
						}
						output.writeObject(0);
						output.writeObject(account1); // 계좌 전송 후 작업 계속
						account2 = (Account) input.readObject();
						if (AccountListNum(account2) == -1) {
							output.writeObject(1); // 보낼 계좌가 없음
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
					case 2: // 주식시세 요청

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
					case 3: // 대출
						output.writeObject(interest[2]); // 대출 이자 전송
						if ((Integer) input.readObject() == -1) // 대출 취소
							break;
						else {
							int loan = (Integer) input.readObject();
							if ((Integer) input.readObject() == -1) // 대출 취소
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
					case 4: // 빚 상환
						Account account3, loan = null; // 순서대로 출금통장, 대출통장
						int debt = 0; // 보낼 돈
						account3 = (Account) input.readObject();
						debt = (Integer) input.readObject();
						if (account3.getBalance() < debt) {
							output.writeObject(1); // 돈이 부족함을 알려줌
							break;
						}
						output.writeObject(0);
						output.writeObject(account3); // 계좌 전송 후 작업 계속
						loan = User.getAccount(0);
						if (loan.getBalance() <= 0) {
							output.writeObject(1); // 갚을 돈이 없음
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

					case 5: // 고객 생성
						Customer customer = (Customer) input.readObject();
						if (CustomerListNum(customer) != -1) {
							newCustomer(customer);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 6: // 계좌 생성
						Account account = (Account) input.readObject();
						if (AccountListNum(account) != -1) {
							newAccount(account);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 7: // 고객 삭제
						Customer removeCustomer = (Customer) input.readObject();
						if (CustomerListNum(removeCustomer) != -1) {
							removeCustomer(removeCustomer);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 8: // 계좌 삭제
						Account removeAccount = (Account) input.readObject();
						if (AccountListNum(removeAccount) != -1) {
							removeAccount(removeAccount);
							output.writeObject(true);
						} else {
							output.writeObject(false);
						}
						break;
					case 9: // 서버 종료
						serverRun = false;
						break;
					case 10: // 고객 목록 전송 / 대출 계좌 목록 출력을 위한 고객 목록 전송
						output.writeObject(customerList);
						break;
					case 11: // 계좌 목록 전송
						output.writeObject(accountList);
						break;
					case 12: // 이자율 변경
						output.writeObject(interest);
						interest = (double[]) input.readObject();
						break;
					case 13: // 예비용
						break;
					}
					//end = true;
					// 이후 적절한 데이터를 송출한다.
					//input = null;
					//output = null;

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 서버 main 메소드
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("서버 데이터를 불러옵니다...");
		BankServer server = new BankServer();
		System.out.println("서버 데이터 불러오기 완료!");
		System.out.println("클라이언트와의 통신을 위한 서버소캣을 생성합니다...\n서버 port번호 : " + server.getPortNum());
		ServerSocket serverSocket = new ServerSocket(server.getPortNum(), MAXCLIENT);
		System.out.println("서버 소캣 생성 완료!");
		ExecutorService es = Executors.newCachedThreadPool(); // 클라이언트와의 통신을 위한 스레드풀 생성
		Thread stockServer = new Thread(server.getStockServer());
		System.out.println("StockServer 생성");
		stockServer.start();
		Socket clientSocket = null;
		/*
		Customer c = new Customer("김시온", "201802070", "990510", "순천시", "010-5402-0806");
		server.getCustomerList().add(c);
		server.newAccount(new Account(0, c));
		c = new Customer("한유경", "201802172", "990312", "강화도", "010-2748-9933");
		server.getCustomerList().add(c);
		server.newAccount(new Account(0, c));
		server.getAccountList().get(1).addBalance(1000000000);
		System.out.println(server.getAccountList().get(0).getAccountNumber());
		*/
		while (server.getServer()) { // 서버와 클라이언트와의 통신

			try {
				System.out.println("클라이언트와의 연결 시도중...");
				clientSocket = serverSocket.accept();
				System.out.println("클라이언트와 연결되었습니다!\n작업을 실시합니다.");
				es.submit(server.makeTask(clientSocket));
			} catch (IOException e) {
			}finally{
				server.setAccountData();
				server.setCustomerData();
				server.setStockServerData();
			}
		}
		

		System.out.println("서버 닫는중...");
		es.shutdown();

		System.out.println("클라이언트의 작업이 모두 끝났습니다.\n스레드풀을 닫습니다.");
		System.out.println("서버소캣을 닫습니다.");
		server.getStockServer().getMarket().setOpen(false);
		System.out.println("주식 시장을 닫습니다.");
																																							stockServer.join();
		serverSocket.close();
		server.setAccountData();
		server.setCustomerData();
		server.setStockServerData();
		System.out.println("데이터 저장 완료. 메인 스레드 종료합니다.");

	}
}
