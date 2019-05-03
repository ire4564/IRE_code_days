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

public class 상품창   extends JFrame{

    JTextField	tfd번호;
    JTextField	tfd이름;
    JTextField	tfd가격;

	JButton btn목록지움;
	JButton btn행추가;
	JButton btn행삭제;

	JTable 상품목록표;
	
	상품창 myFrame;

	public 상품창( ){

		myFrame = this;

		//

		tfd번호=new JTextField();
		tfd번호.setBounds(10, 50, 80, 30);
		this.add(tfd번호);

		tfd이름=new JTextField();
		tfd이름.setBounds(105, 50, 80, 30);
		this.add(tfd이름);

		tfd가격=new JTextField();
		tfd가격.setBounds(200, 50, 80, 30);
		this.add(tfd가격);

		//
		btn목록지움=new JButton("목록지움");
		btn목록지움.setBounds(10, 10, 90, 30);
		this.add(btn목록지움);

		btn행추가=new JButton("행추가");
		btn행추가.setBounds(105, 10, 80, 30);
		this.add(btn행추가);
		
		btn행삭제=new JButton("행삭제");
		btn행삭제.setBounds(200, 10, 80, 30);
		this.add(btn행삭제);		

		//JTable

		//======표 모델 설정-테이블의 구조 설정을 위해===

		//DefaultTableModel 기본표모델=new DefaultTableModel();

		DefaultTableModel 기본표모델=new NonEditableTableModel();

		

		//열 추가 3개

		기본표모델.addColumn("상품번호");
		기본표모델.addColumn("상품명");
		기본표모델.addColumn("가격");

		//======보이는 테이블 속성 설정==============

		상품목록표=new JTable(기본표모델);

		//표시할 행이 없어도 상위 컨테이너 높이 만큼으로 자동 높이 확장

		상품목록표.setFillsViewportHeight(true);

		//한행씩만 선택되도록

		상품목록표.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);




		//===================================

		JScrollPane scrollPane = new JScrollPane(상품목록표);

	    scrollPane.setBounds(10,120,450,300);    

	    this.add(scrollPane);




	    //=======Frame 자체 속성 설정=============

		this.setTitle("상품 목록");

		this.setLayout(null);

		this.setSize(500, 500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		//===이벤트들============================

		btn목록지움.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel 기본표모델=(DefaultTableModel) 상품목록표.getModel();

				기본표모델.setRowCount(0);

			}

		});

		

		btn행추가.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {

				//0.입력값 읽기

				String 번호값=tfd번호.getText();

				String 이름값=tfd이름.getText();

				String 가격값_문자열=tfd가격.getText();

				int 가격값=Integer.valueOf(가격값_문자열);

				

				//==1.행추가=============

				//테이블에서 모델 얻기

				DefaultTableModel 기본표모델=(DefaultTableModel) 상품목록표.getModel();

				Object[] 행={번호값,이름값,new Integer(가격값)};

				//모델에 넣기(이것은 테이블의 시각적 변환를 주게됨.)

				기본표모델.addRow(행);

			}

		});	

		

		btn행삭제.addActionListener(new ActionListener(){				

			public void actionPerformed(ActionEvent arg0) {					

				int 선택된행=myFrame.상품목록표.getSelectedRow();

				if(선택된행==-1){

					JOptionPane.showMessageDialog(myFrame, "선택된 행이 없어요", "바보!", JOptionPane.OK_OPTION  );
					return;

				}					

				DefaultTableModel 기본표모델=(DefaultTableModel) myFrame.상품목록표.getModel();

				기본표모델.removeRow(선택된행);				

			}

		});	

		

		상품목록표.addMouseListener(new MouseAdapter() {

		    @Override

		    public void mouseClicked(MouseEvent 이벤트) {

		        int 행 = 상품목록표.rowAtPoint(이벤트.getPoint());
		        int 열 = 상품목록표.columnAtPoint(이벤트.getPoint());

		        if (행 >= 0 && 열 >= 0) {

		        	JTable 표=(JTable)이벤트.getSource();

		        	if(이벤트.getClickCount()==1){

		        		DefaultTableModel 기본표모델=(DefaultTableModel) 표.getModel();

		        		//테이블 행읽기

		        		String 번호=(String)기본표모델.getValueAt(행, 0);
		        		String 이름=(String)기본표모델.getValueAt(행, 1);
		        		Integer 가격=(Integer)기본표모델.getValueAt(행, 2);

		        		//쓰기

		        		myFrame.tfd번호.setText(번호);
		        		myFrame.tfd이름.setText(이름);
		        		myFrame.tfd가격.setText(가격.toString());

		        	}

		        	else if(이벤트.getClickCount()==2){

		        		DefaultTableModel 기본표모델=(DefaultTableModel) 표.getModel();

		        		//

		        		String 번호=(String)기본표모델.getValueAt(행, 0);
		        		String 이름=(String)기본표모델.getValueAt(행, 1);
		        		Integer 가격=(Integer)기본표모델.getValueAt(행, 2);

		        		

		        		String 메세지=String.format("번호:%s, 이름:%s, 가격:%d 입니다.", 번호,이름,가격);
		        		JOptionPane.showMessageDialog(myFrame, 메세지, "더블 클릭", JOptionPane.OK_OPTION  );

		        	}

		        		

		            




		        }

		    }

		});

		

	}

}

//셀클릭시 기본은 셀 편집 상태가 되는 것을 막기위해 

//DefaultTableModeld의 isCellEditable를 재정의함 (false로)

class NonEditableTableModel extends DefaultTableModel{

	@Override

    public boolean isCellEditable(int row, int column){

        return false;

    }

}

//==================================================================



