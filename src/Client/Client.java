package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Stock.StockMarket.StockPrice;

public class Client {
	private ArrayList<Customer> customerList; // customerŬ������ ������ ��� �ִ� ArrayList
	private ArrayList<Account> accountList; // accountŬ������ ������ ��� �ִ� ArrayList
	private ArrayList<StockPrice> clientStockPriece;// *�ӽ������� �߰�
	private double[] interest; // �� ���� ������ ���� �������� �����ϰ� �ִ� �迭. ���������� ������ �����Ƿ� array�� ����
	private int portNum = 5050;
	private Customer User;
	private boolean again;

	private Socket socket = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream output = null;

	public Client() {
		try {
			System.out.println("�������� ����� �õ��մϴ�...");
			socket = new Socket("127.0.0.1", 5050);
			System.out.println("��Ĺ ���� �Ϸ�!");
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			System.out.println("outputStream!");
			// �ֽ� �׽�Ʈ
			while (true) {
				try {
					output.writeObject(0);
					output.writeObject("asdf01");
					output.writeObject("qwerty");
					int i = (Integer) input.readObject(); // 0�̸� ����,
					System.out.println("�α��� ��� : " + i);
					if (i == 0) {
						Customer customer = (Customer) input.readObject();
						System.out.println(customer.toString());
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Ŭ������ ����");
					e.printStackTrace();
				} // input���� ���ο� ArrayList�� read

			}
		} catch (UnknownHostException e) {
			System.out.println("ȣ��Ʈ�� �������� �ʽ��ϴ�.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ŭ���̾�Ʈ ���� ����!");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Client();

	}
}
