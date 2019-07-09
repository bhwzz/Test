package student;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jiemain {	
	private  JPanel panel1=new JPanel();
	private  JFrame frame=new JFrame();
	private ImageIcon bg = new ImageIcon("img/background.jpg");
	   public WriterRead wr=new WriterRead();
	   public Information ints=new Information();
	   public Seek seek=new Seek();
	    public String[][] students=wr.getxueshengs(); 
	    public String[] interest=ints.getinterest();
		public void begin() {
		// ���ر���ͼƬ		
		 JLabel label = new JLabel(bg); 
		 //�ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ����������
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); 
		frame.setSize(bg.getIconWidth(),bg.getIconHeight());
		//���ͼƬ��frame�ĵڶ���
		frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		//��ȡframe�����ϲ����Ϊ�������䱳����ɫ��JPanel������͸���ķ����� 
		JPanel jp=(JPanel)frame.getContentPane(); 
		jp.setOpaque(false);
		//����͸��    //�����õ�JPanel 
		frame.setLocation(400,200);
		frame.setVisible(true);
		panel1.setOpaque(false);//ҲҪ����͸��  
		panel1.setLayout(null); 
		login();
		}

		private void login()
		{
			panel1.removeAll();
			JButton button1_1=new JButton("����ɾ���޸�ѧ����Ȥ");   
			button1_1.setSize(200, 50);   
			button1_1.setLocation(420, 50);   
			JButton button1_2=new JButton("��ѯ���޸�ѧ����Ϣ");   
			button1_2.setSize(200, 50);   
			button1_2.setLocation(420, 150); 
			JButton button1_3=new JButton("���ѧ����Ϣ");   
			button1_3.setSize(200, 50);   
			button1_3.setLocation(420, 250); 
			JButton button1_4=new JButton("ɾ��ѧ����Ϣ");   
			button1_4.setSize(200, 50);   
			button1_4.setLocation(420, 350); 
			JButton button1_5=new JButton("�鿴����ѧ����Ϣ");   
			button1_5.setSize(200, 50);   
			button1_5.setLocation(420, 450); 
			  button1_1.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	rewrite_insterst();
		            }
		        });
			  button1_2.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                rewrite_student();
		            }
		        });
			  button1_3.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	increase_student();
     
		            }
		        });
			  button1_4.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	delete_student();
	
		            }
		        });
			  button1_5.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	show_all_students();
		            }
		        });
			panel1.add(button1_1); 
			panel1.add(button1_2); 	
			panel1.add(button1_3); 	
			panel1.add(button1_4); 	
			panel1.add(button1_5); 
			panel1.repaint();
			frame.add(panel1);
			frame.setVisible(true);
		}
	    public void rewrite_insterst()
	    {
	    	panel1.removeAll();
	    	JButton button0=new JButton("����");
	    	button0.setSize(100, 50);   
			button0.setLocation(40, 0);
	    	JButton button2_1=new JButton("����ѧ����Ȥ");
	    	button2_1.setSize(200, 50);   
			button2_1.setLocation(420, 50);   
	    	JButton button2_2=new JButton("ɾ��ѧ����Ȥ");
	    	button2_2.setSize(200, 50);   
			button2_2.setLocation(420, 150); 
	    	JButton button2_3=new JButton("�޸�ѧ����Ȥ");
	    	button2_3.setSize(200, 50);   
			button2_3.setLocation(420, 250); 
			button0.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	login();
 
	            }
	        });
			button2_1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	increase_interest();
 
	            }
	        });
			button2_2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	delete_interest();
 
	            }
	        });
			button2_3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	change_interest();
 
	            }
	        });
			panel1.add(button0);
			panel1.add(button2_1);
			panel1.add(button2_2);
			panel1.add(button2_3);
			panel1.repaint();
			frame.setVisible(true);
	    	
	    }
	    public void increase_interest()
	    {
	    	String s=JOptionPane.showInputDialog("������:");
	    	try
	    	{
	    		ints.write(s);
	    		JOptionPane.showMessageDialog(null, "��ӳɹ�","���ѧ����Ȥ",  JOptionPane.CLOSED_OPTION);
	    	}
	    	 catch(Exception e){  
		            e.toString();  
		            JOptionPane.showMessageDialog(null, "��ӳ���","���ѧ����Ȥ",  JOptionPane.ERROR_MESSAGE);
		        }  
	    }
	    public void delete_interest()
	    {
	    	String s=JOptionPane.showInputDialog("������:");
	    	try
	    	{
	    		int k=0;
	    		for(int i=0;i<interest.length;i++)
	    		{
	    			if(interest[i].equals(s))
	    				break;
	    			k++;
	    		}
	    		int b=ints.change(k,"-");
	    		if(b==0)
	    			JOptionPane.showMessageDialog(null, "��Ȥ��û����һ��","ɾ��ѧ����Ȥ",  JOptionPane.ERROR_MESSAGE);
	    		else
	    		JOptionPane.showMessageDialog(null, "ɾ���ɹ�","ɾ��ѧ����Ȥ",  JOptionPane.CLOSED_OPTION);
	    			
	    	}
	    	 catch(Exception e){  
		            e.toString();  
		            JOptionPane.showMessageDialog(null, "ɾ������","ɾ��ѧ����Ȥ",  JOptionPane.ERROR_MESSAGE);
		        }  
	    }
	    public void change_interest()
	    {
	    	String s=JOptionPane.showInputDialog("������Ҫ�޸ĵ���Ȥ:");
	    	String t=JOptionPane.showInputDialog("������Ҫ�޸ĺ����Ȥ:");
	    	try
	    	{
	    		int k=0;
	    		for(int i=0;i<interest.length;i++)
	    		{
	    			if(interest[i].equals(s))
	    				break;
	    			k++;
	    		}
	    		int b=ints.change(k,t);
	    		if(b==0)
	    			JOptionPane.showMessageDialog(null, "��Ȥ��û����һ��","�ı�ѧ����Ȥ",  JOptionPane.ERROR_MESSAGE);
	    		else
	    		JOptionPane.showMessageDialog(null, "�ı�ɹ�","�ı�ѧ����Ȥ",  JOptionPane.CLOSED_OPTION);
	    			
	    	}
	    	 catch(Exception e){  
		            e.toString();  
		            JOptionPane.showMessageDialog(null, "ɾ������","ɾ��ѧ����Ȥ",  JOptionPane.ERROR_MESSAGE);
		        }  
	    }
	    public void rewrite_student()
	    {
	    	panel1.removeAll();
	    	JButton button0=new JButton("����");
	    	button0.setSize(100, 50);   
			button0.setLocation(40, 0);
	    	JButton button2_1=new JButton("ѧ�Ų�ѯѧ��");
	    	button2_1.setSize(200, 50);   
			button2_1.setLocation(420, 100);   
	    	JButton button2_2=new JButton("�ؼ��ֲ���ѧ��");
	    	button2_2.setSize(200, 50);   
			button2_2.setLocation(420, 250); 
	    	JButton button2_3=new JButton("����Ȥ����ѧ��");
	    	button2_3.setSize(200, 50);   
			button2_3.setLocation(420, 400); 
			JTextField textField1=new JTextField();
	    	textField1.setSize(300, 30);
	    	textField1.setLocation(420, 70);
			JTextField textField2=new JTextField();
	    	textField2.setSize(300, 30);
	    	textField2.setLocation(420, 220);
			JTextField textField3=new JTextField();
	    	textField3.setSize(300, 30);
	    	textField3.setLocation(420, 370);
			button0.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	login();
 
	            }
	        });
			button2_1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("������ѧ��:");
	            	String[][] ss=seek.findstudents(s);
	            	chaozuo(ss);
	            }
			});
			button2_2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("������ؼ���:");
	            	String[][] ss=seek.findstudents(s);
	            	chaozuo(ss);
 
	            }
	        });
			button2_3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("��������Ȥ:");
	            	String[][] ss=seek.findbyinterest(s);
	            	chaozuo(ss);
 
	            }
	        });
			panel1.add(button0);
			panel1.add(button2_1);
			panel1.add(button2_2);
			panel1.add(button2_3);
			panel1.repaint();
			frame.setVisible(true);
	    	
	    	
	    }
	    
	    @SuppressWarnings({ "null", "unchecked" })
		public void increase_student()
	    {
	    	panel1.removeAll();
	    	JLabel label1 = new JLabel();
	    	label1.setText("ѧ��ѧ��");
	    	label1.setSize(300, 30);
	    	label1.setLocation(400, 10);   	
	    	JButton button0=new JButton("����");
	    	button0.setSize(100, 50);   
			button0.setLocation(40,0);
			
	    	button0.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	login();
 
	            }
	        });
	    	JTextField textField1=new JTextField();
	    	textField1.setSize(300, 30);
	    	textField1.setLocation(400, 50);
	    	JLabel label2 = new JLabel();
	    	label2.setText("ѧ������");
	    	label2.setSize(300, 30);
	    	label2.setLocation(400, 110);
	    	JTextField textField2=new JTextField();
	    	textField2.setSize(300, 30);
	    	textField2.setLocation(400, 150);
	    	JLabel nianji = new JLabel();
	    	nianji.setText("��ѡ�����");
	    	nianji.setSize(300, 30);
	    	nianji.setLocation(400, 210);
	    	@SuppressWarnings("rawtypes")
			JComboBox comboBox=new JComboBox();  
	        comboBox.addItem("2012");  
	        comboBox.addItem("2013");  
	        comboBox.addItem("2014");
	        comboBox.addItem("2015");
	        comboBox.addItem("2016");
	        comboBox.addItem("2017");
	        comboBox.setSize(100, 40);
	        comboBox.setLocation(400, 250);
	    	JLabel label3 = new JLabel();
	    	label3.setText("ѧ��רҵ");
	    	label3.setSize(300, 30);
	    	label3.setLocation(400, 310);
	    	JTextField textField3=new JTextField();
	    	textField3.setSize(300, 30);
	    	textField3.setLocation(400, 350);
	    	JLabel label4 = new JLabel();
	    	label4.setText("��ѡ���Ա�");
	    	label4.setSize(100, 30);
	    	label4.setLocation(350, 380);
	    	JRadioButton s1=new JRadioButton("��",true);  
	        JRadioButton s2=new JRadioButton("Ů");
	        ButtonGroup bg = new ButtonGroup();  
	        bg.add(s1);  
	        bg.add(s2);
	        JPanel jp1=new JPanel();
	        jp1.setOpaque(false);
	        jp1.setSize(300, 100);
	        jp1.setLocation(450, 380);
	        jp1.add(s1);
	        jp1.add(s2);
	        JCheckBox[] its= new JCheckBox[interest.length];
	        JLabel label5=new JLabel();
	        label5.setText("��ѡ����Ȥ");
	    	label5.setSize(100, 30);
	    	label5.setLocation(350, 400);
	        JPanel jp2=new JPanel();
	        jp2.setLocation(450, 430);
	        jp2.setSize(300, 150);
	        jp2.setOpaque(false);	
	        
	        for(int i=0;i<interest.length;i++)
	        {
	        	its[i]=new JCheckBox(interest[i]);
	        	jp2.add(its[i],BorderLayout.CENTER);	
	        }
	        JButton button1=new JButton("ȷ�����");
	    	button1.setSize(100, 50);   
			button1.setLocation(400, 580);	
			button1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String id,name,sex,year,major,like="";
	            	id=textField1.getText();
	            	name=textField2.getText();
	            	major=textField3.getText();
	            	year=comboBox.getSelectedItem().toString();
	            	if(s1.isSelected())
	            	sex="��";
	            	else sex="Ů";
	            	for(int i=0;i<its.length;i++)
	            	{
	            		if(its[i].isSelected())
	            			like+=Integer.toString(i)+";";
	            	}
	            	if(id.equals("")||!id.matches("[0-9]+"))
	            		JOptionPane.showMessageDialog(null, "ѧ��Ϊ�ջ��Ǵ�����","���ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	else if(name.equals(""))
	            	JOptionPane.showMessageDialog(null, "����Ϊ��","���ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	else if(year.equals(""))
	            		JOptionPane.showMessageDialog(null, "�꼶Ϊ��","���ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	else if(major.equals(""))
	            		JOptionPane.showMessageDialog(null, "רҵΪ��", "���ѧ����Ϣ", JOptionPane.ERROR_MESSAGE);
	            	else {
	            	
	            	wr.write(id+"|"+name+"|"+sex+"|"+year+"|"+major+"|"+like);
	            	
	            	JOptionPane.showMessageDialog(null, "��ӳɹ�","���ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
	            	}
	            	
	            	
	            	

	            }
	        });
	    	panel1.add(textField1);
	    	panel1.add(textField2);
	    	panel1.add(textField3);
	    	panel1.add(jp1);
	    	panel1.add(jp2);
	    	panel1.add(button0);
	    	panel1.add(button1);
	    	panel1.add(comboBox);
	    	panel1.add(label1);
	    	panel1.add(label2);
	    	panel1.add(label3);
	    	panel1.add(label4);
	    	panel1.add(label5);
	    	panel1.add(nianji);
	    	panel1.repaint();
	    	frame.setVisible(true); 	
	    }
	    public void delete_student()
	    {
	    	panel1.removeAll();
	    	JButton button0=new JButton("����");
	    	button0.setSize(100, 50);   
			button0.setLocation(40, 0);
	    	JButton button2_1=new JButton("ѧ��ɾ��ѧ��");
	    	button2_1.setSize(200, 50);   
			button2_1.setLocation(420, 50);   
	    	JButton button2_2=new JButton("�ؼ���ɾ��ѧ��");
	    	button2_2.setSize(200, 50);   
			button2_2.setLocation(420, 150); 
	    	JButton button2_3=new JButton("����Ȥɾ��ѧ��");
	    	button2_3.setSize(200, 50);   
			button2_3.setLocation(420, 250); 
			button0.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	login();
 
	            }
	        });
			button2_1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("������ѧ��:");
	            	String[][] ss=seek.findstudents(s);
	            	if(ss[0][0]==null)
	            	{
	            		JOptionPane.showMessageDialog(null, "ѧ��Ϊ��","ɾ��ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	}
	            	else
	            	{int j=0;
            		while(ss[j][0]!=null)
            		{
            			wr.delete(Integer.parseInt(ss[j][6]));
            			j++;
            		}
	            		JOptionPane.showMessageDialog(null, "ɾ���ɹ�","�޸�ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
	            	}
 
	            }
	        });
			button2_2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("������ؼ���:");
	            	String[][] ss=seek.findstudents(s);
	            	if(ss[0][0]==null)
	            	{
	            		JOptionPane.showMessageDialog(null, "�ؼ��ֿ�","ɾ��ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	}
	            	else
	            	{
	            		int j=0;
	            		while(ss[j][0]!=null)
	            		{
	            			wr.delete(Integer.parseInt(ss[j][6]));
	            			j++;	            			
	            		}
	            		JOptionPane.showMessageDialog(null, "ɾ���ɹ�","ɾ��ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
	            	}
	            	
 
	            }
	        });
			button2_3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String s=JOptionPane.showInputDialog("��������Ȥ:");
	            	String[][] ss=seek.findbyinterest(s);
	            	if(ss[0][0]==null)
	            	{
	            		JOptionPane.showMessageDialog(null, "��ȤΪ��","ɾ��ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	            	}
	            	else
	            	{
	            		int j=0;
	            		while(ss[j][0]!=null)
	            		{
	            			wr.delete(Integer.parseInt(ss[j][6]));
	            			j++;
	            		}
	            		JOptionPane.showMessageDialog(null, "ɾ���ɹ�","�޸�ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
	            	}
 
	            }
	        });
			panel1.add(button0);
			panel1.add(button2_1);
			panel1.add(button2_2);
			panel1.add(button2_3);
			panel1.repaint();
			frame.setVisible(true);
	    	
	    }
	    public void show_all_students()
	    {
	    	JTable table;
	    	JButton bt1 = new JButton("�޸�");
	    	JButton bt2 = new JButton("ɾ��");
	    	JPanel btnPanel = new JPanel(new java.awt.GridLayout(1, 2));
	        btnPanel.add(bt1);
	        btnPanel.add(bt2);
	    	DefaultTableModel defaultTableModel;
	    	JScrollPane scrollPane; 
	        scrollPane = new JScrollPane();  
	        scrollPane.setSize(600, 400);  
	        //����һ��ֻ�б�ͷ�ı��ģ��  
	        defaultTableModel = new DefaultTableModel(new Object[][]{},
            new String[]{"ѧ��", "����", "�Ա�","�꼶","רҵ","��Ȥ"});
	        for(int i=0;students[i][0]!=null;i++)
	        {
	        	String[] in=students[i][5].split("\\;");
	        	String k=new String();
	        	try {
	        		int[] a=new int[in.length];
	        		for(int j=0;j<in.length;j++)
	        		{
	        	     a[j] = Integer.parseInt(in[j]);
	        	     if(interest[a[j]]!="-")
	        		k+=interest[a[j]]+"--";
	        		}
	        	} catch (NumberFormatException e) {
	        	    e.printStackTrace();
	        	}

	        	defaultTableModel.insertRow(i,new Object[]{
                        students[i][0], students[i][1], students[i][2],students[i][3],students[i][4],k,});	        	
	        }
	        
	        //��students�зǿ�Ԫ�ز������ 
	        table = new JTable(defaultTableModel);  
	        table.setSize(600, 400);
	        table.setLocation(500, 200); 
	        table.updateUI();
	       JScrollPane jspane=new JScrollPane(table);
	       JFrame jf=new JFrame();
	       jf.setSize(600, 500);
	       jf.setLocation(500, 200); 
	       jf.add("Center",jspane);
	       //jf.add("South",btnPanel);
	       //jf.add(bt2);
	       jf.setVisible(true);
	       

	        
	    }
	    public void chaozuo(String[][] ss)
	    {
        	JTable table;
	    	JButton bt1 = new JButton("�޸�");
	    	JButton bt2 = new JButton("ɾ��");
	    	JPanel btnPanel = new JPanel(new java.awt.GridLayout(1, 2));
	        btnPanel.add(bt1);
	        btnPanel.add(bt2);
	    	DefaultTableModel defaultTableModel;
	    	JScrollPane scrollPane; 
	        scrollPane = new JScrollPane();  
	        scrollPane.setSize(500, 400);  
	        //����һ��ֻ�б�ͷ�ı��ģ��  
	        defaultTableModel = new DefaultTableModel(new Object[][]{},
            new String[]{"ѧ��", "����", "�Ա�","�꼶","רҵ","��Ȥ"});
	        for(int i=0;ss[i][0]!=null;i++)
	        {
	        	String[] in=ss[i][5].split("\\;");
	        	String k=new String();
	        	try {
	        		int[] a=new int[in.length];
	        		for(int j=0;j<in.length;j++)
	        		{
	        	     a[j] = Integer.parseInt(in[j]);
	        	     if(interest[a[j]]!="-")
	        		k+=interest[a[j]]+"--";
	        		}
	        	} catch (NumberFormatException e1) {
	        	    e1.printStackTrace();
	        	}

	        	defaultTableModel.insertRow(i,new Object[]{
                       ss[i][0], ss[i][1], ss[i][2],ss[i][3],ss[i][4],k,});	        	
	        }
	        //��students�зǿ�Ԫ�ز������ 
	        table = new JTable(defaultTableModel);  
	        table.setSize(500, 400);
	        table.setLocation(500, 200); 
	        table.updateUI();
	       JScrollPane jspane=new JScrollPane(table);
	       JFrame jf=new JFrame();
	       jf.setSize(600, 500);
	       jf.setLocation(500, 200); 
	       jf.add("Center",jspane);
	       jf.add("South",btnPanel);
	       //jf.add(bt2);
	       jf.setVisible(true);
	       bt1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int n=table.getSelectedRow();
	            	panel1.removeAll();
	    	    	JLabel label1 = new JLabel();
	    	    	label1.setText("ѧ��ѧ��");
	    	    	label1.setSize(300, 30);
	    	    	label1.setLocation(400, 10);   	
	    	    	JButton button0=new JButton("����");
	    	    	button0.setSize(100, 50);   
	    			button0.setLocation(40,0);
	    			
	    	    	button0.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	login();
	     
	    	            }
	    	        });
	    	    	JTextField textField1=new JTextField();
	    	    	textField1.setSize(300, 30);
	    	    	textField1.setLocation(400, 50);
	    	    	JLabel label2 = new JLabel();
	    	    	label2.setText("ѧ������");
	    	    	label2.setSize(300, 30);
	    	    	label2.setLocation(400, 110);
	    	    	JTextField textField2=new JTextField();
	    	    	textField2.setSize(300, 30);
	    	    	textField2.setLocation(400, 150);
	    	    	JLabel nianji = new JLabel();
	    	    	nianji.setText("��ѡ�����");
	    	    	nianji.setSize(300, 30);
	    	    	nianji.setLocation(400, 210);
	    	    	JComboBox<String> comboBox=new JComboBox<String>();  
	    	        comboBox.addItem("2012");  
	    	        comboBox.addItem("2013");  
	    	        comboBox.addItem("2014");
	    	        comboBox.addItem("2015");
	    	        comboBox.addItem("2016");
	    	        comboBox.addItem("2017");
	    	        comboBox.setSize(100, 40);
	    	        comboBox.setLocation(400, 250);
	    	    	JLabel label3 = new JLabel();
	    	    	label3.setText("ѧ��רҵ");
	    	    	label3.setSize(300, 30);
	    	    	label3.setLocation(400, 310);
	    	    	JTextField textField3=new JTextField();
	    	    	textField3.setSize(300, 30);
	    	    	textField3.setLocation(400, 350);
	    	    	JLabel label4 = new JLabel();
	    	    	label4.setText("��ѡ���Ա�");
	    	    	label4.setSize(100, 30);
	    	    	label4.setLocation(350, 380);
	    	    	JRadioButton s1=new JRadioButton("��",true);  
	    	        JRadioButton s2=new JRadioButton("Ů");
	    	        ButtonGroup bg = new ButtonGroup();  
	    	        bg.add(s1);  
	    	        bg.add(s2);
	    	        JPanel jp1=new JPanel();
	    	        jp1.setOpaque(false);
	    	        jp1.setSize(300, 100);
	    	        jp1.setLocation(450, 380);
	    	        jp1.add(s1);
	    	        jp1.add(s2);
	    	        JCheckBox[] its= new JCheckBox[interest.length];
	    	        JLabel label5=new JLabel();
	    	        label5.setText("��ѡ����Ȥ");
	    	    	label5.setSize(100, 30);
	    	    	label5.setLocation(350, 400);
	    	        JPanel jp2=new JPanel();
	    	        jp2.setLocation(450, 430);
	    	        jp2.setSize(300, 150);
	    	        jp2.setOpaque(false);	
	    	        
	    	        for(int i=0;i<interest.length;i++)
	    	        {
	    	        	its[i]=new JCheckBox(interest[i]);
	    	        	jp2.add(its[i],BorderLayout.CENTER);	
	    	        }
	    	        JButton button1=new JButton("ȷ���޸�");
	    	    	button1.setSize(100, 50);   
	    			button1.setLocation(400, 580);	
	    			button1.addActionListener(new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) {
	    	            	String id,name,sex,year,major,like="";
	    	            	id=textField1.getText();
	    	            	name=textField2.getText();
	    	            	major=textField3.getText();
	    	            	year=comboBox.getSelectedItem().toString();
	    	            	if(s1.isSelected())
	    	            	sex="��";
	    	            	else sex="Ů";
	    	            	for(int i=0;i<its.length;i++)
	    	            	{
	    	            		if(its[i].isSelected())
	    	            			like+=Integer.toString(i)+";";
	    	            	}
	    	            	if(id.equals("")||!id.matches("[0-9]+"))
	    	            		JOptionPane.showMessageDialog(null, "ѧ��Ϊ�ջ��Ǵ�����","�޸�ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	    	            	else if(name.equals(""))
	    	            	JOptionPane.showMessageDialog(null, "����Ϊ��","�޸�ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	    	            	else if(year.equals(""))
	    	            		JOptionPane.showMessageDialog(null, "�꼶Ϊ��","�޸�ѧ����Ϣ",  JOptionPane.ERROR_MESSAGE);
	    	            	else if(major.equals(""))
	    	            		JOptionPane.showMessageDialog(null, "רҵΪ��", "�޸�ѧ����Ϣ", JOptionPane.ERROR_MESSAGE);
	    	            	else {
	    	            		String xiugai=new String();
	    	            		for(int m=0;m<5;m++)
	    	            		{
	    	            			xiugai+=ss[n][m]+"|";
	    	            		}
	    	            		xiugai+=ss[n][5];
	    	            		System.out.println(xiugai);
	    	            		wr.change(Integer.parseInt(ss[n][6]),id+"|"+name+"|"+sex+"|"+year+"|"+major+"|"+like);
	    	            	
	    	            	JOptionPane.showMessageDialog(null, "�޸ĳɹ�","�޸�ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
	    	            	}
	    	            	
	    	            	
	    	            	

	    	            }
	    	        });
	    	    	panel1.add(textField1);
	    	    	panel1.add(textField2);
	    	    	panel1.add(textField3);
	    	    	panel1.add(jp1);
	    	    	panel1.add(jp2);
	    	    	panel1.add(button0);
	    	    	panel1.add(button1);
	    	    	panel1.add(comboBox);
	    	    	panel1.add(label1);
	    	    	panel1.add(label2);
	    	    	panel1.add(label3);
	    	    	panel1.add(label4);
	    	    	panel1.add(label5);
	    	    	panel1.add(nianji);
	    	    	panel1.repaint();
	    	    	panel1.repaint();
	    	    	frame.setVisible(true); 	
	            }
	           
	            
	        });
	       bt2.addActionListener(new ActionListener() {
   	            @Override
   	            public void actionPerformed(ActionEvent e) {
   	            	int n=table.getSelectedRow();
   	            	int k=Integer.parseInt(ss[n][6]);
   	            	wr.delete(k);
   	            	JOptionPane.showMessageDialog(null, "ɾ���ɹ�","�޸�ѧ����Ϣ",  JOptionPane.CLOSED_OPTION);
  
   	            }
        	
        });
    }
	    
	
}