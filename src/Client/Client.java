package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Stock.StockMarket.StockPrice;

public class Client {
	private ArrayList<Customer> customerList; // customer클래스의 정보를 담고 있는 ArrayList
	private ArrayList<Account> accountList; // account클래스의 정보를 담고 있는 ArrayList
	private ArrayList<StockPrice> clientStockPriece;// *임시적으로 추가
	private double[] interest; // 각 통장 유형에 따른 이자율을 저장하고 있는 배열. 통장유형이 정해져 있으므로 array로 만듦
	private int portNum = 5050;
	private Customer User;
	private boolean again;

	private Socket socket = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;

	public Client() {
		try {
			System.out.println("서버와의 통신을 시도합니다...");
			socket = new Socket("127.0.0.1", 5050);
			System.out.println("소캣 연결 완료!");
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			System.out.println("outputStream!");
			// 주식 테스트
			while (true) {
				try {
					output.writeObject(0);
					output.writeObject("asdf01");
					output.writeObject("qwerty");
					int i = (Integer) input.readObject(); // 0이면 존재,
					System.out.println("로그인 결과 : " + i);
					if (i == 0) {
						Customer customer = (Customer) input.readObject();
						System.out.println(customer.toString());
					}
				} catch (ClassNotFoundException e) {
					System.out.println("클래스가 없음");
					e.printStackTrace();
				} // input에서 새로운 ArrayList를 read

			}
		} catch (UnknownHostException e) {
			System.out.println("호스트가 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("클라이언트 연결 실패!");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Client();

	}
}
