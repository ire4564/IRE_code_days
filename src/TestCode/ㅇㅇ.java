package TestCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Client.BankC_Menu;

public class ���� {
	//�� ��ư���� �̺�Ʈ ������ ��
	Send_b.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			BankC_Menu.setVisible(false);
			BankC_SendAccount.setVisible(true);	
		}	
	});

		//�� ��ư�� ���� ���� �г� ��ȯ
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

//�ٽ� ���� ȭ������ ���ư���(�۾� ����)
BackToStart_btn.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_Finish.setVisible(false);
		BankC_Menu.setVisible(true);
	}	
});

//���� ��ü�� �̵��ϴ� ��ư
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

//������ü4���������� ���Ḧ ������ ���
fin_btn.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		BankC_Finish.setVisible(true);
		BankC_SendAccount4.setVisible(false);
	}	
});



//���� ��ü �� �۱� ���¹�ȣ �Է� �гο��� ���� �г� -> ���¹�ȣ Ȯ�� �гη� �Ѿ�� �̺�Ʈ
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


DtmStorage.addColumn(new String[] {"�ֽĸ�","����","���� ����","�ֱ� ����", "���� �ֽ� ����"}); //�� �ڵ尡 �� �ȸ�����..?
DtmStorage.addRow(new String[] {(String)"�ÿ��ֽ�", "10000000,0000","2.26", "3.23", "5"});

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

model2.addColumn("�ֽĸ�");
model2.addColumn("����");
model2.addColumn("���� ����");
model2.addColumn("�ֱ� ����");
model2.addColumn("�ֹ�");

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
