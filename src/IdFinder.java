import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IdFinder extends JFrame {
    IdFinder() {
        new InputInfo();
    }
    
    static Colors colors;
    
    static class InputInfo extends JFrame {
        InputInfo() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(colors.background);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(colors.background);

            JLabel inputDesc = new JLabel("���̵� ã��");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("�̸��� �Է��ϼ���");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("���� ���", Font.PLAIN, 12));
            
            JTextField inputName = new JTextField();
            
            Font gainFont = new Font("���� ���", Font.PLAIN, 15);
            Font lostFont = new Font("���� ���", Font.PLAIN, 15);
            inputName.setBorder(new EmptyBorder(10,10,10,10));
            inputName.setMargin(new Insets(10,10,10,10));
            inputName.setText("�̸��� �Է��ϼ���");
            inputName.setFont(lostFont);
            inputName.setForeground(Color.GRAY);
            inputName.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                    if (inputName.getText().equals("")) {
                    	inputName.setText("�̸��� �Է��ϼ���");
                    	inputName.setFont(lostFont);
                    	inputName.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                    if (inputName.getText().equals("�̸��� �Է��ϼ���")) {
                    	inputName.setText("");
                    	inputName.setFont(gainFont);
                    	inputName.setForeground(Color.BLACK);
                    }
                }
            });
            
            JTextField inputEmail = new JTextField(20);
            inputEmail.setBorder(new EmptyBorder(10,10,10,10));
            inputEmail.setMargin(new Insets(10,10,10,10));
            
            inputEmail.setText("�̸����� �Է��ϼ���");
            inputEmail.setFont(lostFont);
            inputEmail.setForeground(Color.GRAY);
            inputEmail.addFocusListener(new FocusListener() {	// �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {	// ��Ŀ���� �Ҿ��� ��,
                    if (inputEmail.getText().equals("")) {
                    	inputEmail.setText("�̸����� �Է��ϼ���");
                    	inputEmail.setFont(lostFont);
                    	inputEmail.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {	// ��Ŀ���� ����� ��,
                    if (inputEmail.getText().equals("�̸����� �Է��ϼ���")) {
                    	inputEmail.setText("");
                    	inputEmail.setFont(gainFont);
                    	inputEmail.setForeground(Color.BLACK);
                    }
                }
            });
            
            
            
            Button_Round checkId = new Button_Round("Ȯ��");
            checkId.setColor(Color.white, Color.gray);
            checkId.setBackground(new Color(0x371D1E)); 
            checkId.setFont(new Font("���� ���", Font.BOLD, 15));
            checkId.setForeground(Color.WHITE);
            
            checkId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	String name = inputName.getText();
                    String email = inputEmail.getText();
                    String id = Main.findId(name, email);
                    inputInfoDesc.setText(id);
    
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
        			String checktext1 = inputName.getText();
        			String checktext2 = inputEmail.getText();
        			//System.out.println(checktext1);

        			if(!checktext1.equals("�̸��� �Է��ϼ���") && !checktext2.equals("�̸����� �Է��ϼ���")) {
        				if(checktext1.length() <1 || checktext2.length() <1) {
        					checkId.setColor(Color.white, Color.gray);
        					checkId.setEnabled(false);
        					inputPanel.repaint();
    	    			
    	    			}else if(checktext1.length() >=1 && checktext2.length() >= 1) {
    	    				checkId.setColor(colors.btn_back, colors.btn_text);
    	    				checkId.setEnabled(true);
    	    				inputPanel.repaint();
    	
    	    			}
        			}
                    }
                    });
                    t1.start();
                    
            inputPanel.add(inputDesc);
            inputPanel.add(inputInfoDesc);
            inputPanel.add(inputName);
            inputPanel.add(inputEmail);
            inputPanel.add(checkId);

            JPanel margin = new JPanel();
            margin.setBackground(colors.background);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
