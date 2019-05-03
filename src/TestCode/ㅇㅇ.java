package TestCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Client.BankC_Menu;

public class ㅇㅇ {
	//각 버튼들의 이벤트 리스너 작
	Send_b.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			BankC_Menu.setVisible(false);
			BankC_SendAccount.setVisible(true);	
		}	
	});

		//각 버튼에 대한 다음 패널 전환
		ect_b.addActionListener(new ActionListener() {	@Override
			public void actionPerformed(ActionEvent e) {
				BankC_ViewAccount.setVisible(true);
				BankC_Menu.setVisible(false);
			}
		});
		

		Stock_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BankC_Stock.setVisible(true);
				BankC_Menu.setVisible(false);
			}	
		});

Ord_button.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		Ord_button.setVisible(false);
		Order_Panel.setVisible(true);	
	}	
});

//다시 메인 화면으로 돌아가기(작업 종료)
BackToStart_btn.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_Finish.setVisible(false);
		BankC_Menu.setVisible(true);
	}	
});

//계좌 이체로 이동하는 버튼
Send_Button.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_SendAccount.setVisible(true);
		BankC_ViewAccount2.setVisible(false);
	}	
});
BankC_ViewAccount2.setVisible(false);
Another_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
Send_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

Select_Acc.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_ViewAccount2.setVisible(true);
		BankC_ViewAccount.setVisible(false);
	}	
});


finb.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_SendAccount4.setVisible(true);
		BankC_SendAccount3.setVisible(false);
	}	
});

//계좌이체4페이지에서 종료를 눌렀을 경우
fin_btn.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_Finish.setVisible(true);
		BankC_SendAccount4.setVisible(false);
	}	
});



//계좌 이체 중 송금 계좌번호 입력 패널에서 다음 패널 -> 계좌번호 확인 패널로 넘어가는 이벤트
next2_button.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_SendAccount3.setVisible(true);
		BankC_SendAccount2.setVisible(false);
	}	
});

back2_button.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_SendAccount2.setVisible(false);
		BankC_SendAccount.setVisible(true);
	}	
});


DtmStorage.addColumn(new String[] {"주식명","가격","금일 변동","최근 변동", "보유 주식 갯수"}); //이 코드가 왜 안먹히지..?
DtmStorage.addRow(new String[] {(String)"시온주식", "10000000,0000","2.26", "3.23", "5"});

DefaultTableModel model2 = new DefaultTableModel() {
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
			return Boolean.class;
		default:
			return String.class;
		}
	}
};

model2.addColumn("주식명");
model2.addColumn("가격");
model2.addColumn("금일 변동");
model2.addColumn("최근 변동");
model2.addColumn("주문");

for(int i = 0; i <= 10; i++) {
	model2.addRow(new Object[0]);
	model2.setValueAt("Data Col0", i, 0);
	model2.setValueAt("Row" + (i+1), i, 1);
	model2.setValueAt("Data Col2", i, 2);
	model2.setValueAt("Data Col3", i, 3);

	}

final JTable Account_table = new JTable();
scrollPane_2.setViewportView(Account_table);
Account_table.setModel(model);


JScrollPane scrollPane1 = new JScrollPane();
scrollPane_2.setColumnHeaderView(scrollPane1);

}
