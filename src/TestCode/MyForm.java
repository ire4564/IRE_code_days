package TestCode;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyForm extends JFrame{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyForm frame = new MyForm();
				frame.setVisible(true);
			}
		});
	}
	public MyForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 242);
		setTitle("표 체크박스 Test");
		getContentPane().setLayout(null);//필욧없
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		getContentPane().add(scrollPane);
		
		final JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel() {
			
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
					//return String.class;
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		table.setModel(model);
		
		model.addColumn("주식명");
		model.addColumn("가격");
		model.addColumn("금일 변동");
		model.addColumn("최근 변동");
		model.addColumn("선택");
		
		 
		for(int i = 0; i <= 10; i++) {
			model.addRow(new Object[0]);
			model.setValueAt("Data Col0", i, 0);
			model.setValueAt("Row" + (i+1), i, 1);
			model.setValueAt("Data Col2", i, 2);
			model.setValueAt("Data Col3", i, 3);
		
			
		}
		JButton btnGetRowSelected = new JButton("선택");
		btnGetRowSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String dataCol1 = table.getValueAt(i, 1).toString();
					if(chked) {
						JOptionPane.showMessageDialog(null,dataCol1);
					}
				}
			}
		});
		btnGetRowSelected.setBounds(224, 149, 131, 23);
		getContentPane().add(btnGetRowSelected);
		
	}

}
