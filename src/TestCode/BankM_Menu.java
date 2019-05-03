package TestCode;


import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BankM_Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankM_Menu window = new BankM_Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankM_Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel BankM_NewCustomer = new JPanel();
		BankM_NewCustomer.setBounds(0, 0, 582, 453);
		frame.getContentPane().add(BankM_NewCustomer);
		BankM_NewCustomer.setLayout(null);
		
		JLabel text_6 = new JLabel("※ 신규 가입을 원하는 고객의 정보를 정확히 입력해주세요.");
		text_6.setFont(new Font("a드림고딕1", Font.PLAIN, 16));
		text_6.setBounds(38, 86, 449, 18);
		BankM_NewCustomer.add(text_6);
		
		JLabel text_7 = new JLabel("성명: ");
		text_7.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_7.setBounds(57, 136, 52, 21);
		BankM_NewCustomer.add(text_7);
		
		JLabel text_8 = new JLabel("ID: ");
		text_8.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_8.setBounds(57, 176, 52, 21);
		BankM_NewCustomer.add(text_8);
		
		JLabel text_9 = new JLabel("PW: ");
		text_9.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_9.setBounds(57, 216, 40, 21);
		BankM_NewCustomer.add(text_9);
		
		JLabel text_10 = new JLabel("주소: ");
		text_10.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_10.setBounds(57, 267, 52, 21);
		BankM_NewCustomer.add(text_10);
		
		JLabel text_11 = new JLabel("연락처: ");
		text_11.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_11.setBounds(57, 311, 74, 21);
		BankM_NewCustomer.add(text_11);
		
		JLabel text_12 = new JLabel("이전 계좌 소유 여부: ");
		text_12.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		text_12.setBounds(56, 357, 182, 21);
		BankM_NewCustomer.add(text_12);
		
		JPanel BankM_ViewCustomer = new JPanel();
		BankM_ViewCustomer.setBounds(0, 0, 582, 453);
		frame.getContentPane().add(BankM_ViewCustomer);
		BankM_ViewCustomer.setLayout(null);
		
		JLabel text_4 = new JLabel("※ 전체 고객 정보를 열람합니다. 메뉴에서 바로 고객 정보 관리가 가능합니다.");
		text_4.setFont(new Font("a드림고딕1", Font.PLAIN, 16));
		text_4.setBounds(14, 43, 564, 18);
		BankM_ViewCustomer.add(text_4);
		
		//고객 정보 관리
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 86, 508, 192);
		BankM_ViewCustomer.add(scrollPane);
		
		final JTable View_Stable = new JTable();
		scrollPane.setViewportView(View_Stable);
		
		DefaultTableModel model3 = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch(column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		View_Stable.setModel(model3);
		
		model3.addColumn("성명");
		model3.addColumn("ID");
		model3.addColumn("주소");
		model3.addColumn("연락처");
		model3.addColumn("계좌번호");
		
		for(int i = 0; i <= 10; i++) {
			model3.addRow(new Object[0]);
			model3.setValueAt("익명", i, 0);
			model3.setValueAt("ID" + (i+1), i, 1);
			model3.setValueAt("대한민국" +(i+2) + "번지", i, 2);
			model3.setValueAt("010-1234-567" +(i+1), i, 3);
			model3.setValueAt("1234-5675" + (i+2), i, 4);
			
		
			}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setColumnHeaderView(scrollPane_1);
		 
		//주문 버튼
		JButton DeleteCustomer_btn = new JButton("고객 정보 관리");
		DeleteCustomer_btn.setFont(new Font("a드림고딕1", Font.PLAIN, 18));
		DeleteCustomer_btn.setBounds(189, 311, 188, 50);
		BankM_ViewCustomer.add(DeleteCustomer_btn);
		
		
		//은행 관리자 계좌 관리 메뉴
		JPanel BankM_AccountMenu = new JPanel();
		BankM_AccountMenu.setBounds(0, 0, 582, 453);
		frame.getContentPane().add(BankM_AccountMenu);
		BankM_AccountMenu.setLayout(null);
		
		JButton CustomerInfo_btn = new JButton("고객 정보 관리");
		CustomerInfo_btn.setBounds(198, 326, 169, 65);
		BankM_AccountMenu.add(CustomerInfo_btn);
		CustomerInfo_btn.setFont(new Font("a드림고딕2", Font.PLAIN, 15));
		
		JButton CustomerView_btn = new JButton("고객 정보 열람");
		CustomerView_btn.setBounds(201, 178, 166, 59);
		BankM_AccountMenu.add(CustomerView_btn);
		CustomerView_btn.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		
		JButton CustomerNew_btn = new JButton("신규 고객 가입");
		CustomerNew_btn.setBounds(198, 249, 169, 65);
		BankM_AccountMenu.add(CustomerNew_btn);
		CustomerNew_btn.setFont(new Font("a드림고딕2", Font.PLAIN, 18));
		
		JLabel text_2 = new JLabel("어떤 작업을 하시겠습니까?");
		text_2.setBounds(170, 120, 274, 31);
		BankM_AccountMenu.add(text_2);
		text_2.setFont(new Font("a드림고딕2", Font.PLAIN, 20));
		
		JLabel text_1 = new JLabel("계좌관리");
		text_1.setBounds(226, 77, 122, 31);
		BankM_AccountMenu.add(text_1);
		text_1.setFont(new Font("a드림고딕3", Font.BOLD, 30));
		
		JPanel BankM_FirstMenu = new JPanel();
		BankM_FirstMenu.setBounds(0, 0, 582, 453);
		frame.getContentPane().add(BankM_FirstMenu);
		BankM_FirstMenu.setLayout(null);
		
		JLabel text_3 = new JLabel("BankManager Menu");
		text_3.setFont(new Font("a드림고딕3", Font.BOLD, 35));
		text_3.setBounds(96, 155, 430, 41);
		BankM_FirstMenu.add(text_3);
		
		JButton AccountManager_btn = new JButton("계좌 관리");
		AccountManager_btn.setFont(new Font("a드림고딕2", Font.PLAIN, 20));
		AccountManager_btn.setBounds(85, 222, 184, 65);
		BankM_FirstMenu.add(AccountManager_btn);
		
		JButton CustomerManager_btn = new JButton("고객 관리");
		CustomerManager_btn.setFont(new Font("a드림고딕2", Font.PLAIN, 20));
		CustomerManager_btn.setBounds(301, 222, 184, 65);
		BankM_FirstMenu.add(CustomerManager_btn);
		
		BankM_FirstMenu.setVisible(false);
		//BankM_ViewCustomer.setVisible(false);
		BankM_AccountMenu.setVisible(false);
	}
}
