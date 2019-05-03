package Client;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Stock.StockMarket.StockPrice;

public class Login {

	private JFrame frame;
	private JLabel ID;
	private JLabel PW;
	private JTextField IDField;
	private JTextField PwField;
	private Button Login;
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
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
	Font Origin = new Font("a드림고딕2", Font.PLAIN, 15);
	Font Title = new Font("a드림고딕2", Font.BOLD, 15);
	private JPanel LoginLayout;
	private JPanel textLayout;
	
	//Font Origin2 = new Font("a드림고딕1", Font.PLAIN, 15);
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		System.out.println("서버와의 통신을 시도합니다...");
		try {
			socket = new Socket("127.0.0.1", 5050);
			System.out.println("소캣 연결 완료!");
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
		System.out.println("outputStream!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LoginLayout = new JPanel();
		LoginLayout.setBounds(14, 134, 257, 68);
		frame.getContentPane().add(LoginLayout);
		LoginLayout.setLayout(new GridLayout(0, 2, 0, 0));
		Login = new Button("LOGIN");
				ID = new JLabel("ID");
				ID.setFont(Origin);
				ID.setHorizontalAlignment(SwingConstants.RIGHT);
				LoginLayout.add(ID);
		
				IDField = new JTextField();
				LoginLayout.add(IDField);
				IDField.setColumns(10);
		
		PW = new JLabel("PW");
		LoginLayout.add(PW);
		
		PW.setFont(Origin);
		PW.setHorizontalAlignment(SwingConstants.RIGHT);
		
		PwField = new JTextField();
		LoginLayout.add(PwField);
		PwField.setColumns(10);

		Login.setBounds(288, 134, 106, 68);
		frame.getContentPane().add(Login);
		
		textLayout = new JPanel();
		textLayout.setBounds(34, 26, 353, 96);
		frame.getContentPane().add(textLayout);
		
		text2 = new JLabel("CNU BANK");
		text1 = new JLabel("어서오세요, CUN BANK에 오신것을 환영합니다.");
		text3 = new JLabel("작업을 진행하시려면 로그인을 해 주세요.");
		
		textLayout.add(text2);
		text2.setFont(Title);text3.setFont(Origin);text1.setFont(Origin);
		
		textLayout.add(text2);
		textLayout.add(text1);
		textLayout.add(text3);
		
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						try {
							output.writeObject(0);
							output.writeObject(IDField.getText());
							output.writeObject(PwField.getText());
							if((Integer)input.readObject()==0) {
								frame.setVisible(false);
							User = (Customer) input.readObject();
							BankC_Menu window = new BankC_Menu();
							window.frame.setVisible(true);
							}
							else {}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
	class BankC_Menu {

		JFrame frame;
		private JTextField S_num;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField WantM;
		private String user0,user1;
		private String Account2;
		private String name2;
		
		//폰트 지정을 위한 코드
		Font Origin = new Font("a드림고딕2", Font.PLAIN, 20);
		Font Origin2 = new Font("a드림고딕1", Font.PLAIN, 18);
		Font Title = new Font("a드림고딕3", Font.PLAIN, 24);
		Font Title2 = new Font("a드림고딕3", Font.PLAIN, 30);
		private JTextField textField_2;
		

		//기본 동작을 위한 코드

		
		public BankC_Menu() {
			initialize();
		} //여기까지 기본 동작 코드
	 
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 600, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			//대출 첫 패널
			JPanel BankC_Loan = new JPanel();
			BankC_Loan.setBounds(0, 0, 584, 461);
			frame.getContentPane().add(BankC_Loan);
			BankC_Loan.setLayout(null);
			
			JPanel text10Layout = new JPanel();
			text10Layout.setBounds(101, 97, 356, 78);
			BankC_Loan.add(text10Layout);
			text10Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text20 = new JLabel("대출");
			JLabel text021 = new JLabel("원하시는 금액을 입력해주세요.");
			JLabel text022 = new JLabel("연이율은");
			JLabel LoanR = new JLabel("3.5");
			JLabel text023 = new JLabel("% 입니다.");
			
			text10Layout.add(text20);
			text10Layout.add(text021);
			text10Layout.add(text022);
			text10Layout.add(LoanR);
			text10Layout.add(text023);
			text20.setFont(Title);
			text021.setFont(Origin2);
			text022.setFont(Origin);
			LoanR.setFont(Title);
			text023.setFont(Origin);
			
			//입력 칸 패널
			JPanel btn02Layout = new JPanel();
			btn02Layout.setBounds(173, 203, 397, 68);
			BankC_Loan.add(btn02Layout);
			btn02Layout.setLayout(new GridLayout(1, 0, 0, 0));
		
			
			WantM = new JTextField();
			btn02Layout.add(WantM);
			WantM.setColumns(10);
			JLabel Want = new JLabel("원");
			btn02Layout.add(Want); 
			Want.setFont(Title);
		
			
			//이전 다음 버튼
			JPanel btn10Layout = new JPanel();
			btn10Layout.setBounds(79, 316, 431, 77);
			BankC_Loan.add(btn10Layout);
			btn10Layout.setLayout(new GridLayout(1, 0, 0, 0));
			
			JButton Back4 = new JButton("이전(취소)");
			JButton Next4 = new JButton("다음 단계로");
			Back4.setFont(Origin); 
			Next4.setFont(Origin); 
			
			btn10Layout.add(Back4);
			btn10Layout.add(Next4);
			
			
			
			//대출 완료(두번째) 패널
			JPanel BankC_Loan2 = new JPanel();
			BankC_Loan2.setBounds(0, 0, 584, 453);
			frame.getContentPane().add(BankC_Loan2);
			BankC_Loan2.setLayout(null);
			
			JPanel text30Layout = new JPanel();
			text30Layout.setBounds(48, 88, 487, 132);
			BankC_Loan2.add(text30Layout);
			text30Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text30 = new JLabel("선택 계좌로");
			JLabel LoanNum = new JLabel();
			JLabel text32 = new JLabel("원이 대출 완료되었습니다.");
			JLabel text33 = new JLabel("통장 잔고를 확인해주세요.");
			text30.setFont(Origin);text32.setFont(Origin);text33.setFont(Origin);LoanNum.setFont(Title2);
			
			text30Layout.add(text30);
			text30Layout.add(LoanNum);
			text30Layout.add(text32);
			text30Layout.add(text33);
			
			JPanel btn30Layout = new JPanel();
			btn30Layout.setBounds(176, 243, 230, 125);
			BankC_Loan2.add(btn30Layout);
			btn30Layout.setLayout(new GridLayout(0, 1, 0, 0));
			
			JButton ectAcc2 = new JButton("계좌 잔액 확인");
			JButton Back5 = new JButton("돌아가기");
			ectAcc2.setFont(Origin);Back5.setFont(Origin);
			
			btn30Layout.add(ectAcc2);
			btn30Layout.add(Back5);
			
			
			//고객 계좌 정보 선택 패널
			JPanel BankC_AccountSelect2 = new JPanel();
			BankC_AccountSelect2.setBounds(0, 0, 584, 472);
			frame.getContentPane().add(BankC_AccountSelect2);
			BankC_AccountSelect2.setLayout(null);
			
			//계좌 잔액 조회 시 계좌 선택 Panel
			JPanel text28Layout = new JPanel();
			text28Layout.setBounds(134, 25, 315, 77);
			BankC_AccountSelect2.add(text28Layout);
			text28Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));	
			
			JLabel text38 = new JLabel("대출계좌");
			JLabel text39 = new JLabel("고객님의 계좌를 선택해주세요.");
			text38.setFont(Title); 
			text39.setFont(Origin2);
			
			text28Layout.add(text38);
			text28Layout.add(text39);
			
			//계좌 목록 생성
			JScrollPane scrollPane01 = new JScrollPane();
			scrollPane01.setBounds(42, 117, 494, 173);
			BankC_AccountSelect2.add(scrollPane01);
			
			DefaultTableModel model01 = new DefaultTableModel() {
					public boolean isCellEditable( int row, int column) {
				return false;
			}
		};
			
			model01.addColumn("계좌명");
			model01.addColumn("계좌번호");
			System.out.println(User.toString());
			System.out.println(User.getAccountList().size());
			for(int i = 0; i < User.getAccountList().size(); i++) {
				System.out.println("반복문 들어옴 : "+i);
				model01.addRow(new Object[0]);
				model01.setValueAt("계좌명"+i+1, i, 0);
				model01.setValueAt(User.getAccountList().get(i), i, 1);
				
			}
		
			final JTable ViewAccount01 = new JTable();
			scrollPane01.setViewportView(ViewAccount01);
			ViewAccount01.setModel(model01);
			
			//계좌 선택 버튼
			JButton SelectAcc03 = new JButton("계좌 선택");
			SelectAcc03.setBounds(212, 310, 164, 58);
			BankC_AccountSelect2.add(SelectAcc03);
			
			
			/****************************SendAccount(계좌이체 페이지 코드)************************************/
			
			//고객 정보 열람 패널
			JPanel BankC_SendSelect = new JPanel();
			BankC_SendSelect.setBounds(0, 0, 584, 472);
			frame.getContentPane().add(BankC_SendSelect);
			BankC_SendSelect.setLayout(null);
			
			//계좌 잔액 조회 시 계좌 선택 Panel
			JPanel text04Layout = new JPanel();
			text04Layout.setBounds(134, 25, 315, 77);
			BankC_SendSelect.add(text04Layout);
			text04Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));	
			
			JLabel text08 = new JLabel("계좌이체");
			JLabel text09 = new JLabel("고객님의 계좌를 선택해주세요.");
			text08.setFont(Title); 
			text09.setFont(Origin2);
			
			text04Layout.add(text08);
			text04Layout.add(text09);
			
			//계좌 목록 생성
			JScrollPane scrollPane3 = new JScrollPane();
			scrollPane3.setBounds(42, 117, 494, 173);
			BankC_SendSelect.add(scrollPane3);
			
			DefaultTableModel model3 = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			model3.addColumn("계좌명");
			model3.addColumn("계좌번호");
			System.out.println(User.toString());
			System.out.println(User.getAccountList().size());
			for(int i = 0; i < User.getAccountList().size(); i++) {
				model01.addRow(new Object[0]);
				model01.setValueAt("계좌명"+i+1, i, 0);
				model01.setValueAt(User.getAccountList().get(i), i, 1);
				
			}
		
			
			final JTable ViewAccount3 = new JTable();
			scrollPane3.setViewportView(ViewAccount3);
			ViewAccount3.setModel(model3);
			
			//계좌 선택 버튼
			JButton SelectAcc4 = new JButton("계좌 선택");
			SelectAcc4.setBounds(212, 310, 164, 58);
			BankC_SendSelect.add(SelectAcc4);
			
			
			//계좌이체 다음 패널
			JPanel BankC_SendAccount = new JPanel();
			BankC_SendAccount.setBounds(0, 0, 584, 461);
			frame.getContentPane().add(BankC_SendAccount);
			BankC_SendAccount.setLayout(null);
			
			//송금할 금액 입력
			JPanel text6Layout = new JPanel();
			text6Layout.setBounds(79, 71, 426, 116);
			BankC_SendAccount.add(text6Layout);
			text6Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text13 = new JLabel("계좌이체");
			JLabel text12 = new JLabel("송금하실 금액을 입력해주세요.");
			text13.setFont(Title);text12.setFont(Title2);
			
			text6Layout.add(text13);
			text6Layout.add(text12);
			
			//이전 다음 버튼
			JPanel btn9Layout = new JPanel();
			btn9Layout.setBounds(79, 316, 431, 77);
			BankC_SendAccount.add(btn9Layout);
			btn9Layout.setLayout(new GridLayout(1, 0, 0, 0));
			
			JButton Back = new JButton("이전(취소)");
			JButton Next = new JButton("다음 단계로");
			Back.setFont(Origin); Next.setFont(Origin); 
			
			btn9Layout.add(Back);
			btn9Layout.add(Next);
			//금액 입력 필드 패널
			JPanel btn7Layout = new JPanel();
			btn7Layout.setBounds(179, 205, 391, 77);
			BankC_SendAccount.add(btn7Layout);
			btn7Layout.setLayout(new GridLayout(0, 2, 0, 0));
			
			textField = new JTextField();
			textField.setColumns(10);textField.setFont(Title); 
			btn7Layout.add(textField); 	
			JLabel text14 = new JLabel("원");
			text14.setFont(Title); 
			
			btn7Layout.add(text14);
		

			//계좌이체 두 번째 패널(송금 계좌 번호 입력)
			JPanel BankC_SendAccount2 = new JPanel();
			BankC_SendAccount2.setBounds(0, 0, 584, 461);
			frame.getContentPane().add(BankC_SendAccount2);
			BankC_SendAccount2.setLayout(null);
			
			//송금 안내 문구와 입력 필드 패널
			JPanel btn5Layout = new JPanel();
			btn5Layout.setBounds(14, 66, 556, 148);
			BankC_SendAccount2.add(btn5Layout);
			btn5Layout.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel text_15 = new JLabel("송금하실 계좌 번호를 정확히 입력해주세요.");
			textField_1 = new JTextField(); 
			textField_1.setColumns(10);
			
			text_15.setFont(Title);
			textField_1.setFont(Title);
			
			btn5Layout.add(text_15);
			btn5Layout.add(textField_1);
			
			//버튼 이전 다음
			JPanel NextBack = new JPanel();
			NextBack.setBounds(95, 298, 383, 82);
			BankC_SendAccount2.add(NextBack);
			NextBack.setLayout(new GridLayout(0, 2, 0, 0));
			
			JButton back2 = new JButton("이전(취소)");
			JButton next2= new JButton("다음 단계로");
			back2.setFont(Origin2); 
			next2.setFont(Origin2);
			
			NextBack.add(back2);
			NextBack.add(next2);
			

			//계좌이체 3번쨰 패널(계좌 정보 확인 패널)
			JPanel BankC_SendAccount3 = new JPanel();
			BankC_SendAccount3.setBounds(0, 0, 584, 453);
			frame.getContentPane().add(BankC_SendAccount3);
			BankC_SendAccount3.setLayout(null);
			
			JPanel text5Layout = new JPanel();
			text5Layout.setBounds(57, 119, 458, 109);
			BankC_SendAccount3.add(text5Layout);
			text5Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text_16 = new JLabel("계좌번호:");
			JLabel Account_num = new JLabel(Account2);//게좌번호
			JLabel text_20 = new JLabel("예금주:");
			JLabel Account_name = new JLabel("한유경");
			JLabel text_18 = new JLabel("님");
			JLabel text_19 = new JLabel("송금 정보가 정확한지 다시 한 번 확인해주세요.");
			
			text5Layout.add(text_16);
			text_16.setFont(Title); Account_num.setFont(Origin);text_20.setFont(Title);text_20.setFont(Title);
			Account_name.setFont(Origin);text_18.setFont(Origin);text_19.setFont(Origin2);
		
			text5Layout.add(Account_num);
			text5Layout.add(text_20);
			text5Layout.add(Account_name);
			text5Layout.add(text_18);
			text5Layout.add(text_19);
			
			//확인 버튼
			JPanel btn6Layout = new JPanel();
			btn6Layout.setBounds(37, 283, 514, 72);
			BankC_SendAccount3.add(btn6Layout);
			btn6Layout.setLayout(new GridLayout(0, 2, 0, 0));
			
			JButton finb = new JButton("네,맞습니다.");
			JButton tryb = new JButton("아니오,다시 확인하겠습니다.");
			finb.setFont(Origin2); tryb.setFont(new Font("a드림고딕2", Font.PLAIN, 15));
			
			btn6Layout.add(finb);
			btn6Layout.add(tryb);
			
			
			//계좌이체 4번째 Panel(계좌이체 완료)
			JPanel BankC_SendAccount4 = new JPanel();
			BankC_SendAccount4.setBounds(0, 0, 584, 453);
			frame.getContentPane().add(BankC_SendAccount4);
			BankC_SendAccount4.setLayout(null);
			
			//계좌이체 완료 문구 Panel
			JPanel text1Layout = new JPanel();
			text1Layout.setBounds(51, 77, 483, 118);
			BankC_SendAccount4.add(text1Layout);
			text1Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel Account_name2 = new JLabel("한유경"); //고객 이름
			JLabel text21 = new JLabel("님께"); 
			JLabel Account_num2 = new JLabel(name2); //원하는 송금 금액
			JLabel text22 = new JLabel("원의");
			JLabel text24 = new JLabel("송금이 완료되었습니다.");
			JLabel text25 = new JLabel("고객님의 현 계좌 잔액은");
			JLabel AccAct = new JLabel(); 
			JLabel text27 = new JLabel("입니다.");
			
			Account_name2.setFont(Title); 
			Account_num2.setFont(Title);
			text21.setFont(Origin); 
			text22.setFont(Origin); 
			text24.setFont(Origin); 
			text25.setFont(Origin); 
			AccAct.setFont(Origin); 
			text27.setFont(Origin); 
			
			text1Layout.add(Account_name2);
			text1Layout.add(text21);
			text1Layout.add(Account_num2);
			text1Layout.add(text22);
			text1Layout.add(text24);
			text1Layout.add(text25);
			text1Layout.add(AccAct);
			text1Layout.add(text27);
		
			/*JLabel Account_num3 = new JLabel("10000,0000"); 
			Account_num3.setFont(Title); 
			text1Layout.add(Account_num3);
			text1Layout.add(text27);*/
			
			//계좌이체 완료의 버튼을 담은 패널
			JPanel btn1Layout = new JPanel();
			btn1Layout.setBounds(155, 217, 277, 174);
			BankC_SendAccount4.add(btn1Layout);
			btn1Layout.setLayout(new GridLayout(0, 1, 0, 0));
			
			JButton addAcc = new JButton("추가 계좌이체");
			JButton fin3 = new JButton("종료");
			
			addAcc.setFont(Origin); 
			fin3.setFont(Origin);
			
			btn1Layout.add(addAcc); 
			btn1Layout.add(fin3);
			
			
			
			/****************************ViewAccount(계좌 잔액 조회 페이지 코드)************************************/
			//고객 정보 열람 패널
			JPanel BankC_ViewAccount = new JPanel();
			BankC_ViewAccount.setBounds(0, 0, 584, 472);
			frame.getContentPane().add(BankC_ViewAccount);
			BankC_ViewAccount.setLayout(null);
			

			//계좌 잔액 조회 시 첫 화면 패널
			JPanel text4Layout = new JPanel();
			text4Layout.setBounds(134, 25, 315, 77);
			BankC_ViewAccount.add(text4Layout);
			text4Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));	
			
			JLabel text8 = new JLabel("계좌 잔액 조회");
			JLabel text9 = new JLabel("고객님의 계좌를 선택해주세요.");
			text8.setFont(Title); 
			text9.setFont(Origin2);
			
			text4Layout.add(text8);
			text4Layout.add(text9);
			
			//계좌 목록 생성
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(42, 117, 494, 173);
			BankC_ViewAccount.add(scrollPane_2);
			
			DefaultTableModel model = new DefaultTableModel() {
					public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
			
			model.addColumn("계좌명");
			model.addColumn("계좌번호");
			System.out.println(User.toString());
			System.out.println(User.getAccountList().size());
			for(int i = 0; i < User.getAccountList().size(); i++) {
				model01.addRow(new Object[0]);
				model01.setValueAt("계좌명"+i+1, i, 0);
				model01.setValueAt(User.getAccountList().get(i), i, 1);
				
			}
		
			String[] column2 = {"","","",""};
		
			final JTable ViewAccount = new JTable();
			scrollPane_2.setViewportView(ViewAccount);
			ViewAccount.setModel(model);
			
			//계좌 선택 버튼
			JButton SelectAcc = new JButton("계좌 선택");
			SelectAcc.setBounds(212, 310, 164, 58);
			BankC_ViewAccount.add(SelectAcc);
			
		
			
			//계좌 잔액 조회 결과 패널
			JPanel BankC_ViewAccount2 = new JPanel();
			BankC_ViewAccount2.setBounds(0, 0, 584, 461);
			frame.getContentPane().add(BankC_ViewAccount2);
			BankC_ViewAccount2.setLayout(null);
			
			//계좌 잔액 조회 패널 결과 안내 문구 패널
			JPanel text3Layout = new JPanel();
			text3Layout.setBounds(30, 108, 523, 86);
			BankC_ViewAccount2.add(text3Layout);
			
			JLabel text10 = new JLabel("고객님의");
			JLabel AccountM = new JLabel();
			JLabel text11 = new JLabel("계좌 총 잔액은");
			JLabel ectM = new JLabel();
			JLabel text23 = new JLabel("원입니다. 다른 작업을 하시겠습니까?");
			text10.setFont(Origin); text11.setFont(Origin2); text23.setFont(Origin2); AccountM.setFont(Origin2);ectM.setFont(Origin2);
			
			text3Layout.add(text10);
			text3Layout.add(AccountM);
			text3Layout.add(text11);
			text3Layout.add(ectM);
			text3Layout.add(text23);

			
			//계좌 잔액 조회 패널 결과 선택 버튼 패널
			JPanel btn2Layout = new JPanel();
			btn2Layout.setBounds(160, 191, 264, 180);
			BankC_ViewAccount2.add(btn2Layout);
			btn2Layout.setLayout(new GridLayout(0, 1, 0, 0));
			
			JButton SendA = new JButton("계좌이체");
			JButton AnotherA = new JButton("타계좌 잔액 조회");
			JButton FinishA = new JButton("돌아가기");
			SendA.setFont(Origin); AnotherA.setFont(Origin); FinishA.setFont(Origin);
			
			btn2Layout.add(SendA);
			btn2Layout.add(AnotherA);
			btn2Layout.add(FinishA);
		

			/****************************Finish(작업 종료 페이지)************************************/
			//BANK 작업을 종료 했을 때 Panel
			JPanel BankC_Finish = new JPanel();
			BankC_Finish.setBounds(0, 0, 584, 453);
			frame.getContentPane().add(BankC_Finish);
			BankC_Finish.setLayout(null);
			
			JPanel text2Layout = new JPanel();
			text2Layout.setBounds(39, 109, 502, 119);
			BankC_Finish.add(text2Layout);
			text2Layout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text_25 = new JLabel("CNU BANK를 이용해주셔서");
			JLabel text_26 = new JLabel("감사합니다.");
			text_25.setFont(Title2);
			text_26.setFont(Title2);
			
			JButton BackToStart = new JButton("돌아가기"); 
			BackToStart.setFont(Title);
			BackToStart.setBounds(185, 252, 208, 63);
			
			text2Layout.add(text_25);
			text2Layout.add(text_26);
			BankC_Finish.add(BackToStart);

		
			/****************************Stock(주식관리 페이지 코드)************************************/
			//주식 관리 화면 패널
			JPanel BankC_Stock = new JPanel();
			BankC_Stock.setBounds(0, 0, 582, 453);
			frame.getContentPane().add(BankC_Stock);
			BankC_Stock.setLayout(null);
			
			//주식 관리 안내 문구 패널
			JPanel text7Layout = new JPanel();
			text7Layout.setBounds(14, 12, 542, 50);
			BankC_Stock.add(text7Layout);
			
			JLabel text_5 = new JLabel("주식 관리");
			JLabel text_6 = new JLabel("CNU Bank에서 주식을 관리해보세요.");
			text_5.setFont(Title);
			text_6.setFont(Origin2);
			
			text7Layout.add(text_5);
			text7Layout.add(text_6);
			
			//주식 목록 테이블
			DefaultTableModel DtmStorage = new DefaultTableModel() {
				
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};	
			
			DtmStorage.addColumn("주식명");
			DtmStorage.addColumn("가격");
			DtmStorage.addColumn("금일 변동");
			DtmStorage.addColumn("최근 변동");
			DtmStorage.addColumn("주문");
			
			for(int i = 0; i <= 10; i++) {
				DtmStorage.addRow(new Object[0]);
				DtmStorage.setValueAt("Data Col0", i, 0);
				DtmStorage.setValueAt("Row" + (i+1), i, 1);
				DtmStorage.setValueAt("Data Col2", i, 2);
				DtmStorage.setValueAt("Data Col3", i, 3);
			
				}
			
			JScrollPane scrollPane8 = new JScrollPane();
			scrollPane8.setBounds(34, 69, 508, 167);
			BankC_Stock.add(scrollPane8);
			
			final JTable OrderStable = new JTable();
			OrderStable.setBounds(12, 56, 473, 38);
			scrollPane8.setViewportView(OrderStable);
			OrderStable.setModel(DtmStorage);
			
			JScrollPane scrollPane5 = new JScrollPane();
			scrollPane8.setColumnHeaderView(scrollPane5);
			
			
			DefaultTableModel model2 = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};	
			
			model2.addColumn("주식명");
			model2.addColumn("가격");
			model2.addColumn("금일 변동");
			model2.addColumn("최근 변동");
			model2.addColumn("주식 보유 갯수");
			
			
			model2.addRow(new Object[0]);
			model2.setValueAt("시온주식",0,0);
			model2.setValueAt("10000,000",0, 1);
			model2.setValueAt("0.5%",0, 2);
			model2.setValueAt("0.4%",0, 3);
			model2.setValueAt("6",0, 4);
				
			OrderStable.setBounds(12, 56, 473, 38);
			
			

			/****************************Menu(Client 접속 첫 번째 페이지 코드)************************************/
			//처음 Client의 은행 접속 화면 메뉴 패널
			JPanel BankC_Menu = new JPanel();
			BankC_Menu.setBounds(0, 0, 582, 453);
			frame.getContentPane().add(BankC_Menu);
			BankC_Menu.setLayout(null);
			
			//Client Menu 안의 text Panel(안내 문구 표출용)
			JPanel textLayout = new JPanel();
			textLayout.setBounds(14, 14, 554, 86);
			BankC_Menu.add(textLayout);
			textLayout.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel text0 = new JLabel("고객님의 CNU BANK에 접속하셨습니다.");
			JLabel text1 = new JLabel("무엇을 하시겠습니까?");
			text0.setFont(new Font("a드림고딕3", Font.BOLD, 24));
			text1.setFont(new Font("a드림고딕2", Font.PLAIN, 20));
			textLayout.add(text0);textLayout.add(text1);
			
			//Client Menu 안의 button Panel(메뉴 선택)
			JPanel BtnLayout = new JPanel();
			BtnLayout.setBounds(154, 112, 277, 310);
			BankC_Menu.add(BtnLayout);
			BtnLayout.setLayout(new GridLayout(0, 1, 0, 0));
			BtnLayout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//간격 설정 코드
			
			JButton Loanb = new JButton("대출");
			JButton Sendb = new JButton("계좌이체");
			JButton ectb = new JButton("계좌 잔액 조회");
			JButton Stockb = new JButton("주식 관리");
			
			BtnLayout.add(Loanb);BtnLayout.add(Sendb);BtnLayout.add(ectb); BtnLayout.add(Stockb);
			Loanb.setFont(Origin); Sendb.setFont(Origin); ectb.setFont(Origin); Stockb.setFont(Origin);		
			
			
			/****************************이벤트 리스너 처리************************************/
			
			//처음 시작 메뉴 화면에서 (대출)버튼을 눌렀을 때
			Loanb.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_Menu.setVisible(false);
					BankC_Loan.setVisible(true);
				}	
			});
			
			/****************************(Menu)이벤트 리스너 처리************************************/
			//처음 시작 메뉴 화면에서 (계좌이체)버튼을 눌렀을 때
			Sendb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BankC_Menu.setVisible(false);
					BankC_SendSelect.setVisible(true);
					try {
						output.writeObject(1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			});
				//처음 시작 메뉴 화면에서 (계좌 잔액 조회)버튼을 눌렀을 때
			ectb.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					BankC_Menu.setVisible(false);
					BankC_ViewAccount.setVisible(true);
				}	
			});
			//처음 시작 메뉴 화면에서 (주식관리)버튼을 눌렀을 때
			Stockb.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					BankC_Menu.setVisible(false);
					BankC_Stock.setVisible(true);
				}	
			});
			
			/****************************(SendAccount)계좌이체 이벤트 리스너 처리************************************/
			//계좌 선택
			SelectAcc4.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {					

						int SelectCol = ViewAccount3.getSelectedColumn();
						
						if(SelectCol==-1) {
							JOptionPane.showMessageDialog(frame, "선택된 계좌가 없습니다. 다시 확인해주세요.", "Message", JOptionPane.OK_OPTION  );
							return;
						
					}	
					BankC_SendSelect.setVisible(false);
					BankC_SendAccount.setVisible(true);
				}	
			});
			
			ViewAccount3.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent event) {
					
					int row = ViewAccount.rowAtPoint(event.getPoint());
					int col = ViewAccount.columnAtPoint(event.getPoint());
					
					if(row >= 0 && col >= 0) {
						
						
						if(event.getClickCount() ==1) {
						
							
							String Account = (String)model01.getValueAt(row, 1);
							user0 = Account;

			        		String Message=String.format("선택하신 계좌는%s입니다. 확인 후 계좌 선택을 눌러주세요.", Account);
			        		JOptionPane.showMessageDialog(frame, Message, "Message", JOptionPane.OK_OPTION  );
			        		try {
								output.writeObject(user0);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
						}
					}
				}
			});
			
			//계좌이체 다음 버튼
			Next.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//원하는  액수 저장
					int WantM2_Data = Integer.valueOf(textField.getText());
					Account_num2.setText(String.valueOf(WantM2_Data));
					
					try {
						output.writeObject(WantM2_Data);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						if((Integer)input.readObject()!=0) {
							System.out.println("돈 없음");
							System.exit(1);
						}
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BankC_SendAccount.setVisible(false);
					BankC_SendAccount2.setVisible(true);
				}	
			});
			next2.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//원하는  계좌 저장
					String Account2_Data = String.valueOf(textField_1.getText());
					Account_num.setText(String.valueOf(Account2_Data));
					
					try {
						output.writeObject(Account2_Data);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
							if((Integer)input.readObject()!=0) {
								System.out.println("돈 없음");
								System.exit(1);
							}
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					BankC_SendAccount2.setVisible(false);
					BankC_SendAccount3.setVisible(true);
				}	
			});
			
			
			


			//계좌이체 '송금하실 금액을 입력해주세요'의 이전(취소) 버튼을 눌렀을 때
			Back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BankC_SendAccount.setVisible(false);
					BankC_SendSelect.setVisible(true);
				}	
			});
			
			//계좌이체 '송금하실 정보가 정확합니까?'의 네,맞습니다 버튼을 눌렀을 때
			finb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						AccAct.setText(""+((Account)input.readObject()).getBalance());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BankC_SendAccount3.setVisible(false);
					BankC_SendAccount4.setVisible(true);
				}	
			});
			//계좌이체 '송금하실 정보가 정확합니까?'의 아니오,다시 확인하겠습니다 버튼을 눌렀을 때
			tryb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BankC_SendAccount3.setVisible(false);
					BankC_SendAccount.setVisible(true);
				}	
			});
			
			//계좌이체 '송금 완료, 다른 작업을 하시겠습니까'의  추가 계좌이체 버튼을 눌렀을 때
			addAcc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BankC_SendAccount4.setVisible(false);
					BankC_SendSelect.setVisible(true);
				}	
			});
			//계좌이체 '송금 완료, 다른 작업을 하시겠습니까'의  종료 버튼을 눌렀을 때
			fin3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BankC_SendAccount4.setVisible(false);
					BankC_Finish.setVisible(true);
				}	
			});
			
			//계좌이체 '송금하실 계좌번호를 정확히 입력해주세요'의 이전(취소) 버튼을 눌렀을 때
			back2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BankC_SendAccount2.setVisible(false);
					BankC_SendAccount.setVisible(true);
				}	
			});

			
			/****************************(ViewAccount)계좌조회 이벤트 리스너 처리************************************/
			
			
			
			ViewAccount.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent event) {
					
					int row = ViewAccount.rowAtPoint(event.getPoint());
					int col = ViewAccount.columnAtPoint(event.getPoint());
					
					if(row >= 0 && col >= 0) {
						
						
						if(event.getClickCount() ==1) {
						
							String name = (String)model01.getValueAt(row, 0);
							String Account = (String)model01.getValueAt(row, 1);
							user1 = Account; //계좌번호
							user0 = name;//계좌 이름
							AccountM.setText(String.valueOf(Account));
							ectM.setText(String.valueOf(name));

			        		String Message=String.format("선택하신 계좌는%s입니다. 확인 후 계좌 선택을 눌러주세요.", Account);
			        		JOptionPane.showMessageDialog(frame, Message, "Message", JOptionPane.OK_OPTION  );

							
						}
					}
				}
			});
			
			//계좌 조회 선택 버튼을 눌렀을 경우
			SelectAcc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {					

					int SelectCol = ViewAccount.getSelectedColumn();
					
					if(SelectCol==-1) {
						JOptionPane.showMessageDialog(frame, "선택된 계좌가 없습니다. 다시 확인해주세요.", "Message", JOptionPane.OK_OPTION  );
						return;
					
				}	
				BankC_ViewAccount.setVisible(false);
				BankC_ViewAccount2.setVisible(true);
				}
			});
			//계좌 조회2 계좌이체 버튼을 눌렀을 때
			SendA.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_ViewAccount2.setVisible(false);
					BankC_SendSelect.setVisible(true);
				}	
			});
			//계좌 조회2 타계좌 잔액 조회 눌렀을 때
			AnotherA.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_ViewAccount2.setVisible(false);
					BankC_ViewAccount.setVisible(true);
				}	
			});
			
			//계좌 조회2 돌아가기 눌렀을 때 
			BackToStart.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_Finish.setVisible(false);
					BankC_Menu.setVisible(true);
			
				}	
			});
			//계좌 완료 후 조회 돌아가기를 눌렀을 때
			FinishA.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_ViewAccount2.setVisible(false);
					BankC_Menu.setVisible(true);
			
				}	
			});
			
			/****************************(Stock)주식 이벤트 리스너 처리************************************/

			//주문 버튼
			JButton OrderS = new JButton("주문하기");
			OrderS.setBounds(226, 259, 130, 50);
			BankC_Stock.add(OrderS);
			
			//주식을 선택하고 넘어갔을 때 화면 패널
			JPanel Order_Panel = new JPanel();
			Order_Panel.setBounds(34, 253, 508, 178);
			BankC_Stock.add(Order_Panel);
			Order_Panel.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel text_7 = new JLabel("고객님이 선택하신 주식의 정보입니다.");
			Order_Panel.add(text_7);
			
			JScrollPane scrollPane = new JScrollPane();
			Order_Panel.add(scrollPane);
			
			final JTable View_Stable = new JTable();
			scrollPane.setViewportView(View_Stable);
			View_Stable.setModel(model2);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane.setColumnHeaderView(scrollPane_1);
			

			
			//주식 사고 팔기 버튼 패널
			JPanel btn8Layout = new JPanel();
			Order_Panel.add(btn8Layout);
			btn8Layout.setLayout(new GridLayout(0, 3, 0, 0));
			
			S_num = new JTextField();
			S_num.setColumns(10);
			
			S_num.setText("구매 희망 갯수");
			JButton S_Buy = new JButton("매수");
			JButton S_Sell = new JButton("매도");
			
			btn8Layout.add(S_num);
			btn8Layout.add(S_Buy);
			btn8Layout.add(S_Sell);
			Order_Panel.setVisible(false);
			
			//주식 선택 후 주문하기를 눌렀을 경우 나오는 패널
			OrderS.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					OrderS.setVisible(false);
					Order_Panel.setVisible(true);
				}	
			});
			
			/****************************(Loan)대출 이벤트 리스너 처리************************************/
			//대출 계좌 선택
			SelectAcc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {					

					int SelectCol = ViewAccount.getSelectedColumn();
					
					if(SelectCol==-1) {
						JOptionPane.showMessageDialog(frame, "선택된 계좌가 없습니다. 다시 확인해주세요.", "Message", JOptionPane.OK_OPTION  );
						return;
					
				}	
				BankC_ViewAccount.setVisible(false);
				BankC_ViewAccount2.setVisible(true);
				}
			});
			//대출 패널 표시
			ViewAccount01.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent event) {
					
					int row = ViewAccount.rowAtPoint(event.getPoint());
					int col = ViewAccount.columnAtPoint(event.getPoint());
					
					if(row >= 0 && col >= 0) {
						
						
						if(event.getClickCount() ==1) {
						
							String name = (String)model01.getValueAt(row, 0);
							String Account = (String)model01.getValueAt(row, 1);
							user1 = Account;

			        		String Message=String.format("선택하신 계좌는%s입니다. 확인 후 계좌 선택을 눌러주세요.", Account);
			        		JOptionPane.showMessageDialog(frame, Message, "Message", JOptionPane.OK_OPTION );

							
						}
					}
				}
			});
			//대출 패널에서 이전 버튼을 눌렀을 때
			Back4.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_Loan.setVisible(false);
					BankC_Menu.setVisible(true);
				}	
			});
			//대출 패널에서 다음 버튼을 눌렀을 때
			Next4.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//원하는 대출 액수 저장
					int WantM_Data = Integer.valueOf(WantM.getText());
					LoanNum.setText(String.valueOf(WantM_Data));
					
					BankC_Loan.setVisible(false);
					BankC_AccountSelect2.setVisible(true);
				}	
			});
			BankC_Loan.setVisible(false);
			
			//계좌 선택 버튼을 눌렀을 경우
			SelectAcc03.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//String Account = (String)model01.getValueAt(row, 1);
					int result = JOptionPane.showConfirmDialog(frame, "한유경 님" + WantM.getText() + "원을 계좌 " + user1 + "에 대출받으시겠습니까?", "대출 정보 확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(result == JOptionPane.CLOSED_OPTION) {
						//선택 없이 다이얼로그 창을 닫은 경우
					}
					else if(result == JOptionPane.YES_OPTION) {
						//사용자가 예를 선택한 경우
						
						
						BankC_AccountSelect2.setVisible(false);
						BankC_Loan2.setVisible(true);
					}
					else {
						//아니오를 선택한 경우
						JOptionPane.showConfirmDialog(frame, "처음부터 다시 시도 부탁드립니다.", "대출 정보 확인", JOptionPane.CLOSED_OPTION);
						
						BankC_AccountSelect2.setVisible(false);
						BankC_Menu.setVisible(true);
					}
				}	
			});
			//대출 후 잔액 계좌 조회
			ectAcc2.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_Loan2.setVisible(false);
					BankC_ViewAccount.setVisible(true);
				}	
			});
			//대출 후 끝내기 
			Back5.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					BankC_Loan2.setVisible(false);
					BankC_Finish.setVisible(true);
				}	
			});
			
			
			BankC_SendAccount.setVisible(false);
			BankC_SendAccount2.setVisible(false);
			BankC_SendAccount3.setVisible(false);
			BankC_SendAccount4.setVisible(false);
			BankC_ViewAccount.setVisible(false);
			BankC_ViewAccount2.setVisible(false);
			BankC_Finish.setVisible(false);
			BankC_Stock.setVisible(false);
			BankC_SendSelect.setVisible(false);
			BankC_AccountSelect2.setVisible(false);
			BankC_Loan2.setVisible(false);
			
			
		}
	}

}
