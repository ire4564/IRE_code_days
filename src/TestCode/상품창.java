package TestCode;





import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JTextField;

import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class ��ǰâ   extends JFrame{

    JTextField	tfd��ȣ;
    JTextField	tfd�̸�;
    JTextField	tfd����;

	JButton btn�������;
	JButton btn���߰�;
	JButton btn�����;

	JTable ��ǰ���ǥ;
	
	��ǰâ myFrame;

	public ��ǰâ( ){

		myFrame = this;

		//

		tfd��ȣ=new JTextField();
		tfd��ȣ.setBounds(10, 50, 80, 30);
		this.add(tfd��ȣ);

		tfd�̸�=new JTextField();
		tfd�̸�.setBounds(105, 50, 80, 30);
		this.add(tfd�̸�);

		tfd����=new JTextField();
		tfd����.setBounds(200, 50, 80, 30);
		this.add(tfd����);

		//
		btn�������=new JButton("�������");
		btn�������.setBounds(10, 10, 90, 30);
		this.add(btn�������);

		btn���߰�=new JButton("���߰�");
		btn���߰�.setBounds(105, 10, 80, 30);
		this.add(btn���߰�);
		
		btn�����=new JButton("�����");
		btn�����.setBounds(200, 10, 80, 30);
		this.add(btn�����);		

		//JTable

		//======ǥ �� ����-���̺��� ���� ������ ����===

		//DefaultTableModel �⺻ǥ��=new DefaultTableModel();

		DefaultTableModel �⺻ǥ��=new NonEditableTableModel();

		

		//�� �߰� 3��

		�⺻ǥ��.addColumn("��ǰ��ȣ");
		�⺻ǥ��.addColumn("��ǰ��");
		�⺻ǥ��.addColumn("����");

		//======���̴� ���̺� �Ӽ� ����==============

		��ǰ���ǥ=new JTable(�⺻ǥ��);

		//ǥ���� ���� ��� ���� �����̳� ���� ��ŭ���� �ڵ� ���� Ȯ��

		��ǰ���ǥ.setFillsViewportHeight(true);

		//���྿�� ���õǵ���

		��ǰ���ǥ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);




		//===================================

		JScrollPane scrollPane = new JScrollPane(��ǰ���ǥ);

	    scrollPane.setBounds(10,120,450,300);    

	    this.add(scrollPane);




	    //=======Frame ��ü �Ӽ� ����=============

		this.setTitle("��ǰ ���");

		this.setLayout(null);

		this.setSize(500, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		//===�̺�Ʈ��============================

		btn�������.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel �⺻ǥ��=(DefaultTableModel) ��ǰ���ǥ.getModel();

				�⺻ǥ��.setRowCount(0);

			}

		});

		

		btn���߰�.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {

				//0.�Է°� �б�

				String ��ȣ��=tfd��ȣ.getText();

				String �̸���=tfd�̸�.getText();

				String ���ݰ�_���ڿ�=tfd����.getText();

				int ���ݰ�=Integer.valueOf(���ݰ�_���ڿ�);

				

				//==1.���߰�=============

				//���̺��� �� ���

				DefaultTableModel �⺻ǥ��=(DefaultTableModel) ��ǰ���ǥ.getModel();

				Object[] ��={��ȣ��,�̸���,new Integer(���ݰ�)};

				//�𵨿� �ֱ�(�̰��� ���̺��� �ð��� ��ȯ�� �ְԵ�.)

				�⺻ǥ��.addRow(��);

			}

		});	

		

		btn�����.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {					

				int ���õ���=myFrame.��ǰ���ǥ.getSelectedRow();

				if(���õ���==-1){

					JOptionPane.showMessageDialog(myFrame, "���õ� ���� �����", "�ٺ�!", JOptionPane.OK_OPTION  );
					return;

				}					

				DefaultTableModel �⺻ǥ��=(DefaultTableModel) myFrame.��ǰ���ǥ.getModel();

				�⺻ǥ��.removeRow(���õ���);				

			}

		});	

		

		��ǰ���ǥ.addMouseListener(new MouseAdapter() {

		    @Override

		    public void mouseClicked(MouseEvent �̺�Ʈ) {

		        int �� = ��ǰ���ǥ.rowAtPoint(�̺�Ʈ.getPoint());
		        int �� = ��ǰ���ǥ.columnAtPoint(�̺�Ʈ.getPoint());

		        if (�� >= 0 && �� >= 0) {

		        	JTable ǥ=(JTable)�̺�Ʈ.getSource();

		        	if(�̺�Ʈ.getClickCount()==1){

		        		DefaultTableModel �⺻ǥ��=(DefaultTableModel) ǥ.getModel();

		        		//���̺� ���б�

		        		String ��ȣ=(String)�⺻ǥ��.getValueAt(��, 0);
		        		String �̸�=(String)�⺻ǥ��.getValueAt(��, 1);
		        		Integer ����=(Integer)�⺻ǥ��.getValueAt(��, 2);

		        		//����

		        		myFrame.tfd��ȣ.setText(��ȣ);
		        		myFrame.tfd�̸�.setText(�̸�);
		        		myFrame.tfd����.setText(����.toString());

		        	}

		        	else if(�̺�Ʈ.getClickCount()==2){

		        		DefaultTableModel �⺻ǥ��=(DefaultTableModel) ǥ.getModel();

		        		//

		        		String ��ȣ=(String)�⺻ǥ��.getValueAt(��, 0);
		        		String �̸�=(String)�⺻ǥ��.getValueAt(��, 1);
		        		Integer ����=(Integer)�⺻ǥ��.getValueAt(��, 2);

		        		

		        		String �޼���=String.format("��ȣ:%s, �̸�:%s, ����:%d �Դϴ�.", ��ȣ,�̸�,����);
		        		JOptionPane.showMessageDialog(myFrame, �޼���, "���� Ŭ��", JOptionPane.OK_OPTION  );

		        	}

		        		

		            




		        }

		    }

		});

		

	}

}

//��Ŭ���� �⺻�� �� ���� ���°� �Ǵ� ���� �������� 

//DefaultTableModeld�� isCellEditable�� �������� (false��)

class NonEditableTableModel extends DefaultTableModel{

	@Override

    public boolean isCellEditable(int row, int column){

        return false;

    }

}

//==================================================================



