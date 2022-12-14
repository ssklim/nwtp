import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.NoSuchAlgorithmException;

public class SignUpView extends JFrame {
    SignUpView() {
        new RegisterName();
        
    }
    
    static String name;
    static String id;
    static String nickName;
    static String pw = "";
    static String email;
    static String address;
    static String phone;
    static String birth;
    
    // �̸� �Է�
    static class RegisterName extends JFrame {
       Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterName() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputNameDesc = new JLabel("�̸��� �Է��ϼ���");
            inputNameDesc.setHorizontalAlignment(JLabel.CENTER);
            inputNameDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            JTextField inputName = new JTextField();
            inputName.setBorder(new EmptyBorder(10,10,10,10));

            Button_Round toInputId = new Button_Round("����");

            toInputId.setColor(Color.white, Color.gray);
            toInputId.setBackground(new Color(0x371D1E));
            toInputId.setFont(new Font("���� ���", Font.BOLD, 15));
            toInputId.setForeground(Color.WHITE);
           
            toInputId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   name = inputName.getText();
                    dispose();
                    new RegisterId();
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
                 String checktext1 = inputName.getText();
                 //System.out.println(checktext1);

                 if(!checktext1.equals("���̵� �Է��ϼ���")) {
                    if(checktext1.length() <1) {
                       toInputId.setColor(Color.white, Color.gray);
                       toInputId.setEnabled(false);
                    inputPanel.repaint();
                    
                    }else if(checktext1.length() >=1) {
                       toInputId.setColor(btn_back, btn_text);
                       toInputId.setEnabled(true);
                       inputPanel.repaint();
       
                    }
                 }
                    }
                    });
                    t1.start();
            inputPanel.add(inputDesc);
            inputPanel.add(inputNameDesc);
            inputPanel.add(inputName);
            inputPanel.add(toInputId);

            JPanel margin = new JPanel();
            margin.setBackground(yellow);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    // ID �Է�
    static class RegisterId extends JFrame {
       Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterId() {
            setSize(400,600);
            setLayout(new GridLayout(1, 0));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridBagLayout());
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputIdDesc = new JLabel("���̵� �Է��ϼ���");
            inputIdDesc.setHorizontalAlignment(JLabel.CENTER);
            inputIdDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            Font gainFont = new Font("���� ���", Font.PLAIN, 14);
            Font lostFont = new Font("���� ���", Font.PLAIN, 14);
            
            JTextField inputId = new JTextField();
            inputId.setBorder(new EmptyBorder(10,10,10,10));
            inputId.setText("���̵� �Է��ϼ���");
            inputId.setForeground(Color.gray);
            inputId.setFont(lostFont);
            inputId.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputId.getText().equals("")) {
                       inputId.setText("���̵� �Է��ϼ���");
                       inputId.setFont(lostFont);
                       inputId.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputId.getText().equals("���̵� �Է��ϼ���")) {
                       inputId.setText("");
                       inputId.setFont(gainFont);
                       inputId.setForeground(Color.BLACK);
                    }
                }
            });
            
            
            //�г���
            JLabel inputNickDesc = new JLabel("�г����� �Է��ϼ���");
            inputNickDesc.setHorizontalAlignment(JLabel.CENTER);
            inputNickDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            
            
            JTextField inputNick = new JTextField();
            inputNick.setBorder(new EmptyBorder(10,10,10,10));
            inputNick.setText("�г����� �Է��ϼ���");
            inputNick.setForeground(Color.gray);
            inputNick.setFont(lostFont);
            inputNick.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputNick.getText().equals("")) {
                       inputNick.setText("�г����� �Է��ϼ���");
                       inputNick.setFont(lostFont);
                       inputNick.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputNick.getText().equals("�г����� �Է��ϼ���")) {
                       inputNick.setText("");
                       inputNick.setFont(gainFont);
                       inputNick.setForeground(Color.BLACK);
                    }
                }
            });
            //�г���
            
            Button_Round toInputPw = new Button_Round("����");
            toInputPw.setColor(Color.white, Color.gray);
            toInputPw.setBackground(new Color(0x371D1E));
            toInputPw.setFont(new Font("���� ���", Font.BOLD, 15));
            toInputPw.setForeground(Color.WHITE);
            
            toInputPw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                   //���̵� �ߺ�ó��
                   /*
                    * ���� �ߺ��� ���̵�� �Ѿ�� ����.
                    * �ߺ���ǥ��
                    * inputIdDesc.setText("�ߺ��� ���̵��Դϴ�");
                    * inputIdDesc.setBackground(Color.RED);
                    * 
                    * 
                    * else{
                    */
                   inputIdDesc.setText("���̵� �Է��ϼ���");
                   inputNickDesc.setText("�г����� �Է��ϼ���");
                   id = inputId.getText();
                   nickName = inputNick.getText();
                   if(Main.dupCheck(id, nickName).equals("Success")) {
                	   dispose();
                       new RegisterPw();
                   }
                   else if(Main.dupCheck(id, nickName).equals("Check your ID")) {
                	   inputIdDesc.setText("Check your ID");
                   }
                   else if(Main.dupCheck(id, nickName).equals("Check your NickName")) {
                	   inputNickDesc.setText("Check your NickName");
                   }
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
                 String checktext1 = inputId.getText();
                 String checktext2 = inputNick.getText();
                 //System.out.println(checktext1 + checkPw);

                 if(!checktext1.equals("���̵� �Է��ϼ���")&&!checktext2.equals("�г����� �Է��ϼ���")) {
                    if(checktext1.length() <1 || checktext2.length() <1) {
                    toInputPw.setColor(Color.white, Color.gray);
                    toInputPw.setEnabled(false);
                    inputPanel.repaint();
                    
                    }else if(checktext1.length() >=1 && checktext2.length() >=1) {
                       toInputPw.setColor(btn_back, btn_text);
                       toInputPw.setEnabled(true);
                       inputPanel.repaint();
       
                    }
                 }
                    }
                    });
                    t1.start();
                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.weightx = 1;
                    gbc.weighty = 1;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    
            inputPanel.add(inputDesc, gbc);
            gbc.gridy = 1;
            inputPanel.add(inputIdDesc, gbc);
            
            gbc.gridy = 2;
            inputPanel.add(inputId,gbc);
            //�߰�
            gbc.gridy = 3;
            inputPanel.add(inputNickDesc,gbc);
            
            gbc.gridy = 4;
            inputPanel.add(inputNick, gbc);
            //�߰�
            
            gbc.weighty = 2;
            gbc.gridy = 5;
            JLabel margin = new JLabel(" ");
            margin.setBackground(yellow);

            inputPanel.add(margin, gbc);

            gbc.gridy = 6;
            inputPanel.add(toInputPw, gbc);

            gbc.weighty = 10;
            gbc.gridy = 7;

            inputPanel.add(new JLabel(" "), gbc);
            
            
            add(inputPanel);
            //add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    // ��й�ȣ �Է�
    static class RegisterPw extends JFrame {
       Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterPw() {
            setSize(400,600);
            setLayout(new GridLayout(2, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(5,1, 10, 10));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputPwDesc = new JLabel("��й�ȣ�� �Է��ϼ���");
            inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
            inputPwDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            Font gainFont = new Font("���� ���", Font.PLAIN, 14);
            Font lostFont = new Font("���� ���", Font.PLAIN, 14);
            
            JPasswordField inputPw = new JPasswordField();
            inputPw.setBorder(new EmptyBorder(10,10,10,10));
           inputPw.setEchoChar('*');
            inputPw.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputPw.getText().equals("")) {
                       inputPw.setEchoChar((char)0);
                       inputPw.setText("��й�ȣ�� �Է��ϼ���");
                       inputPw.setFont(lostFont);
                       inputPw.setForeground(Color.GRAY);
                    }else {
                       inputPw.setEchoChar('*');
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputPw.getText().equals("��й�ȣ�� �Է��ϼ���")) {
                       inputPw.setText("");
                       inputPw.setEchoChar('*');
                       inputPw.setFont(gainFont);
                       inputPw.setForeground(Color.BLACK);
                    }else {
                       //inputPw.setEchoChar('*');

                    }
                }
            });
            
            Button_Round toinputInfo = new Button_Round("ȸ������");
            toinputInfo.setColor(Color.white, Color.gray);
            toinputInfo.setBackground(new Color(0x371D1E));
            toinputInfo.setFont(new Font("���� ���", Font.BOLD, 15));
            toinputInfo.setForeground(Color.WHITE);
            
            // ---------------------- ȸ������ ----------------------------
            toinputInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   for(char pwd : inputPw.getPassword()) {
                      pw += pwd;
                   }
                   SHA256 sha = new SHA256();
                   try {
					pw = sha.encrypt(pw);
                   } catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
                   }
                   dispose();
                   new RegisterInfo(); 
                }
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
                 char[] checktext2 = inputPw.getPassword();
                 String checkPw = String.valueOf(checktext2);

                 if(!checkPw.equals("��й�ȣ�� �Է��ϼ���")) {
                      if(checkPw.length()<3) {
                         toinputInfo.setColor(Color.white, Color.gray);
                         toinputInfo.setEnabled(false);
                         inputPanel.repaint();
                    
                    }else if(checkPw.length() >= 3) {
                       toinputInfo.setColor(btn_back, btn_text);
                       toinputInfo.setEnabled(true);
                       inputPanel.repaint();
       
                    }
                 }
                    }
                    });
                    t1.start();
            
            // ---------------------- ȸ������ ----------------------------
            
            inputPanel.add(inputDesc);
            inputPanel.add(inputPwDesc);
            inputPanel.add(inputPw);
            inputPanel.add(toinputInfo);

            JPanel margin = new JPanel();
            margin.setBackground(yellow);

            add(inputPanel);
            add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
    static class RegisterInfo extends JFrame {
       Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);

        RegisterInfo() {
            setSize(400,600);
            setLayout(new GridLayout(1, 1));
            setBackground(yellow);
            setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridBagLayout());
            inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
            inputPanel.setBackground(yellow);

            JLabel inputDesc = new JLabel("ȸ������");
            inputDesc.setHorizontalAlignment(JLabel.CENTER);
            inputDesc.setFont(new Font("���� ���", Font.BOLD, 24));

            JLabel inputInfoDesc = new JLabel("����� ������ �Է��ϼ���");
            inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
            inputInfoDesc.setFont(new Font("���� ���", Font.PLAIN, 12));

            Font gainFont = new Font("���� ���", Font.PLAIN, 14);
            Font lostFont = new Font("���� ���", Font.PLAIN, 14);
            Font descFont = new Font("���� ���", Font.BOLD, 15);

            //�� �׽�Ʈ ��ü
            JLabel inputEmailDesc = new JLabel("�̸���: ");
            inputEmailDesc.setFont(gainFont);

            JTextField inputEmail = new JTextField(13);
            inputEmail.setBorder(new EmptyBorder(10,10,10,10));
            inputEmail.setText("�̸����� �Է��ϼ���");
           inputEmail.setFont(lostFont);
           inputEmail.setForeground(Color.GRAY);
           inputEmailDesc.setFont(descFont);
            JPanel inputEmailPanel = new JPanel();
            inputEmailPanel.add(inputEmailDesc);
            inputEmailPanel.add(inputEmail);
            inputEmail.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputEmail.getText().equals("")) {
                       inputEmail.setText("�̸����� �Է��ϼ���");
                       inputEmail.setFont(lostFont);
                       inputEmail.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputEmail.getText().equals("�̸����� �Է��ϼ���")) {
                       inputEmail.setText("");
                       inputEmail.setFont(gainFont);
                       inputEmail.setForeground(Color.BLACK);
                    }
                }
            });
            
            
            JLabel inputAddressDesc = new JLabel("�ּ�: ");
            inputAddressDesc.setFont(descFont);

            JTextField inputAddress = new JTextField(13);
            inputAddress.setBorder(new EmptyBorder(10,10,10,10));
            inputAddress.setText("�ּҸ� �Է��ϼ���");
           inputAddress.setFont(lostFont);
           inputAddress.setForeground(Color.GRAY);

            JPanel inputAddressPanel = new JPanel();
            inputAddressPanel.add(inputAddressDesc);
            inputAddressPanel.add(inputAddress);
            inputAddress.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputAddress.getText().equals("")) {
                       inputAddress.setText("�ּҸ� �Է��ϼ���");
                       inputAddress.setFont(lostFont);
                       inputAddress.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputAddress.getText().equals("�ּҸ� �Է��ϼ���")) {
                       inputAddress.setText("");
                       inputAddress.setFont(gainFont);
                       inputAddress.setForeground(Color.BLACK);
                    }
                }
            });
            
            JTextField inputPhone = new JTextField(13);
            inputPhone.setBorder(new EmptyBorder(10,10,10,10));
            JLabel inputPhoneDesc = new JLabel("��ȭ��ȣ: ");
            inputPhoneDesc.setFont(descFont);
            inputPhone.setText("��ȭ��ȣ�� �Է��ϼ���");
           inputPhone.setFont(lostFont);
           inputPhone.setForeground(Color.GRAY);

            JPanel inputPhonePanel = new JPanel();
            inputPhonePanel.add(inputPhoneDesc);
            inputPhonePanel.add(inputPhone);
            inputPhone.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputPhone.getText().equals("")) {
                       inputPhone.setText("��ȭ��ȣ�� �Է��ϼ���");
                       inputPhone.setFont(lostFont);
                       inputPhone.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputPhone.getText().equals("��ȭ��ȣ�� �Է��ϼ���")) {
                       inputPhone.setText("");
                       inputPhone.setFont(gainFont);
                       inputPhone.setForeground(Color.BLACK);
                    }
                }
            });
            
            JTextField inputBirth = new JTextField(13);
            inputBirth.setBorder(new EmptyBorder(10,10,10,10));
            JLabel inputBirthDesc = new JLabel("�������: ");
            inputBirthDesc.setFont(descFont);
           inputBirth.setForeground(Color.GRAY);

            inputBirth.setText("��������� �Է��ϼ���");
           inputBirth.setFont(lostFont);
           
            JPanel inputBirthPanel = new JPanel();
            inputBirthPanel.add(inputBirthDesc);
            inputBirthPanel.add(inputBirth);
            inputBirth.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

                @Override
                public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                    if (inputBirth.getText().equals("")) {
                       inputBirth.setText("��������� �Է��ϼ���");
                       inputBirth.setFont(lostFont);
                       inputBirth.setForeground(Color.GRAY);
                    }
                }

                @Override
                public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                    if (inputBirth.getText().equals("��������� �Է��ϼ���")) {
                       inputBirth.setText("");
                       inputBirth.setFont(gainFont);
                       inputBirth.setForeground(Color.BLACK);
                    }
                }
            });
            

            Button_Round registerEnd = new Button_Round("����");
            registerEnd.setColor(Color.white, Color.gray);
            registerEnd.setBackground(new Color(0x371D1E));
            registerEnd.setFont(new Font("���� ���", Font.BOLD, 15));
            registerEnd.setForeground(Color.WHITE);
            
            registerEnd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                   birth = inputBirth.getText();
                   phone = inputPhone.getText();
                   email = inputEmail.getText();
                   address = inputAddress.getText();
                   
                   User user = new User(id);
                   user.ip = null;
                   user.port = -1;
                   user.pw = pw;
                   user.name = name;
                   user.nickName = nickName;
                   user.email = email;
                   user.birth = birth;
                   user.phone = phone;
                   user.address = address;
                   user.todayM = "";
                   user.state = "off";
                   user.outTime = "";
                   user.count = 0;
                   
                   Main.signUp(user);
                   dispose();
                   //new LoginWindow();                      
                   }              
            });
            Timer t1=new Timer(100,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                    {
                 String checktext1 = inputEmail.getText();
                 String checktext2 = inputAddress.getText();
                 String checktext3 = inputBirth.getText();
                 String checktext4 = inputPhone.getText();

                 //System.out.println(checktext1 + checkPw);

                 if(!checktext1.equals("�̸����� �Է��ϼ���")&&!checktext2.equals("�ּҸ� �Է��ϼ���")&&!checktext3.equals("��������� �Է��ϼ���")&&!checktext4.equals("��ȭ��ȣ�� �Է��ϼ���")) {
                    if(checktext1.length() <1 || checktext2.length() <1 || checktext3.length() <6 || checktext4.length() <1) {
                       registerEnd.setColor(Color.white, Color.gray);
                       registerEnd.setEnabled(false);
                       inputPanel.repaint();
                    
                    }else if(checktext1.length() >=1 && checktext2.length() >=1 && checktext3.length() >=6 && checktext4.length() >=1) {
                       registerEnd.setColor(btn_back, btn_text);
                       registerEnd.setEnabled(true);
                       inputPanel.repaint();
       
                    }
                 }
                    }
                    });
                    t1.start();
            
            // ---------------------- ȸ������ ----------------------------
            
//            inputPanel.add(inputDesc);
//            inputPanel.add(inputInfoDesc);
//            inputPanel.add(inputBirthPanel);
//            inputPanel.add(inputEmailPanel);
//            inputPanel.add(inputPhonePanel);
//            inputPanel.add(inputAddressPanel);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            inputPanel.add(inputDesc,gbc);
            
            gbc.gridy = 1;
            inputPanel.add(inputInfoDesc,gbc);
            
            gbc.gridy = 2;
            inputPanel.add(inputBirthDesc,gbc);
            
            gbc.gridy = 3;
            inputPanel.add(inputBirth,gbc);
            
            gbc.gridy = 4;
            inputPanel.add(inputPhoneDesc,gbc);
            
            gbc.gridy = 5;
            inputPanel.add(inputPhone,gbc);
 
            gbc.gridy = 6;
            inputPanel.add(inputEmailDesc,gbc);
            
            gbc.gridy = 7;
            inputPanel.add(inputEmail,gbc);
            
            gbc.gridy = 8;
            inputPanel.add(inputAddressDesc,gbc);
            
            gbc.gridy = 9;
            inputPanel.add(inputAddress,gbc);
            
            JLabel margin = new JLabel("");
            margin.setBackground(yellow);
            
            gbc.gridy = 10;
            inputPanel.add(new JLabel(""),gbc);

            gbc.weighty = 2;
            gbc.gridy = 11;
            inputPanel.add(registerEnd,gbc);
            
            gbc.gridy = 12;
            inputPanel.add(margin,gbc);



            add(inputPanel);
            //add(margin);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
    
    
}