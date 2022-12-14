import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class LoginWindow extends JFrame {
   
   static String myId;
   private Main mymain;
   
   private JPanel logoPanel;
   private JPanel enterPanel;
   private JPanel optionPanel;
   Button_Round loginButton = new Button_Round("�α���");
   Button_Round registerButton = new Button_Round("ȸ������");
    private CardLayout card;
    
   void setMain(Main main) {
      this.mymain = main;
   }
   Main getMain() {
      return this.mymain;
   }
   
   LoginWindow(){
        Container con = getContentPane();

        setLayout(card = new CardLayout());
        Color btn_back = new Color(0x371D1E); //���� ����
        Color btn_text = new Color(0xffffff); //���ڻ� ����
        Color yellow = new Color(0xFAE100);
        
        setSize(360, 600);
        setLocationRelativeTo(null);
        con.setBackground(yellow);
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        
        logoPanel = new JPanel(new BorderLayout());
        enterPanel = new JPanel(new GridLayout(0,1, 0, 1));
        enterPanel.setBorder(BorderFactory.createEmptyBorder(30,45,0,45));
        
        optionPanel = new JPanel(new BorderLayout());
        optionPanel.setBorder(BorderFactory.createEmptyBorder(20,45,30,45));

        logoPanel.setBackground(yellow);
        enterPanel.setBackground(yellow);
        optionPanel.setBackground(yellow);

         //��� �ΰ� �̹��� �߰�
        Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/kakaotalkmain.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        
        JLabel notionLabel = new JLabel();
        notionLabel.setForeground(Color.red);
        Font notionFont = new Font("���� ���", Font.PLAIN, 11);
        notionLabel.setFont(notionFont);
        
        JPanel notionP = new JPanel();
        notionP.add(notionLabel);
        notionP.setBackground(yellow);
        logoPanel.add(notionP,BorderLayout.SOUTH);
        logoPanel.add(new JLabel(new ImageIcon(logoIcon)), BorderLayout.CENTER);

        JLabel inputText = new JLabel();
        JTextField idField = new JTextField(20);
        idField.setBorder(new EmptyBorder(10,10,10,10));

        JPasswordField pwField = new JPasswordField(20);
        pwField.setBorder(new EmptyBorder(10,10,10,10));


        // �ؽ�Ʈ�ʵ� ��Ʈ
        Font gainFont = new Font("���� ���", Font.PLAIN, 14);
        Font lostFont = new Font("���� ���", Font.PLAIN, 14);
        
        idField.setText("���̵� �Է��ϼ���");
        idField.setFont(lostFont);
        idField.setForeground(Color.GRAY);
        idField.setMargin(new Insets(10,10,10,10));
      
        idField.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

            @Override
            public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                if (idField.getText().equals("")) {
                    idField.setText("���̵� �Է��ϼ���");
                    idField.setFont(lostFont);
                    idField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                if (idField.getText().equals("���̵� �Է��ϼ���")) {
                    idField.setText("");
                    idField.setFont(gainFont);
                    idField.setForeground(Color.BLACK);
                }
            }
        });
      loginButton.setEnabled(false);

        Timer t1=new Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent e)
                {
             String checktext1 = idField.getText();
             char[] checktext2 = pwField.getPassword();
             String checkPw = String.valueOf(checktext2);

             if(!checktext1.equals("���̵� �Է��ϼ���") && !checkPw.equals("��й�ȣ�� �Է��ϼ���")) {
                  if(checktext1.length()<1 || checkPw.length()<1) {
                   loginButton.setColor(Color.white, Color.gray);
                   loginButton.setEnabled(false);
                   optionPanel.repaint();
                
                }else if(checktext1.length() >=1 && checkPw.length() >=1 ) {
                   loginButton.setColor(btn_back, btn_text);
                   loginButton.setEnabled(true);
                   optionPanel.repaint();
   
                }
             }
                }
                });
                t1.start();

        
        
        pwField.setEchoChar((char)0);
       pwField.setText("��й�ȣ�� �Է��ϼ���");
       pwField.setFont(lostFont);
       pwField.setForeground(Color.GRAY);
       
        pwField.addFocusListener(new FocusListener() {   // �ؽ�Ʈ �ʵ� ��Ŀ�� �� �̺�Ʈ

            @Override
            public void focusLost(FocusEvent e) {   // ��Ŀ���� �Ҿ��� ��,
                if (pwField.getText().equals("")) {
                   pwField.setEchoChar((char)0);
                   pwField.setText("��й�ȣ�� �Է��ϼ���");
                   pwField.setFont(lostFont);
                   pwField.setForeground(Color.GRAY);
                }else {
                   pwField.setEchoChar('*');
                }
            }

            @Override
            public void focusGained(FocusEvent e) {   // ��Ŀ���� ����� ��,
                if (pwField.getText().equals("��й�ȣ�� �Է��ϼ���")) {
                   pwField.setText("");
                   pwField.setEchoChar('*');
                   pwField.setFont(gainFont);
                   pwField.setForeground(Color.BLACK);
                }
            }
        });
        
        
//        // �ȳ� �ؽ�Ʈ ����
//        inputText.setText("���̵�� ��й�ȣ�� �Է��ϼ���");
//        inputText.setFont(new Font("���� ���", Font.PLAIN, 12));
//        inputText.setHorizontalAlignment(JLabel.CENTER);

        enterPanel.add(idField);
        enterPanel.add(pwField);

        // ---------------------- �α��� ----------------------------
      loginButton.setColor(Color.white, Color.gray);
        loginButton.setBackground(new Color(0x371D1E));
        loginButton.setFont(new Font("���� ���", Font.BOLD, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = "";
                

                for(char pwd : pwField.getPassword()) {
                   pw += pwd;
                }
                SHA256 sha = new SHA256();
                try {
					pw = sha.encrypt(pw);
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
                
                // �α��� ���� ����
                Object user = mymain.logIn(id, pw);
                if(user instanceof User) {
                   notionLabel.setText("");
                   mymain.showMainBoardView((User)user);
                   dispose();
                }
                else {
                   notionLabel.setText((String)user);
                }
                   
                logoPanel.repaint();
            }
        });


        registerButton = new Button_Round("ȸ������");
        registerButton.setColor(btn_back, btn_text);
        registerButton.setFont(new Font("���� ���", Font.BOLD, 14));
        registerButton.setBackground(btn_back);
        //registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpView();
            }
        });

        JLabel margin = new JLabel("");

        Button_Round pwFinderButton = new Button_Round("��й�ȣ ã��");
        pwFinderButton.setSize(50,20);
        pwFinderButton.setColor(yellow,Color.gray);
        pwFinderButton.show = false;
        pwFinderButton.setFont(new Font("���� ���", Font.PLAIN, 12));
        pwFinderButton.setBackground(Color.yellow);
        pwFinderButton.setBorderPainted(false);
        pwFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PwFinder();
            }
        });
        Button_Round idFinderButton = new Button_Round("ID ã��");
        idFinderButton.setSize(50,20);
        idFinderButton.setColor(yellow,Color.gray);
        idFinderButton.show = false;
        idFinderButton.setFont(new Font("���� ���", Font.PLAIN, 12));
        idFinderButton.setBackground(Color.yellow);
        idFinderButton.setBorderPainted(false);
        idFinderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IdFinder(); //ID ã��
            }
        });
        
        //optionPanel.add(loginButton);
        //optionPanel.add(registerButton);
        //optionPanel.add(margin);
        JPanel finderPanel = new JPanel();
        finderPanel.setLayout(new FlowLayout(1,0,0));
        finderPanel.setBackground(yellow);
        finderPanel.setFont(new Font("���� ���", Font.PLAIN, 12));

        finderPanel.add(idFinderButton);
        
        
        JLabel partitionLabel = new JLabel("|");
        partitionLabel.setSize(10,20);
        partitionLabel.setBackground(yellow);
        partitionLabel.setForeground(Color.gray);
        
        finderPanel.add(partitionLabel);
        finderPanel.add(pwFinderButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3,1,10,10));
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(registerButton);
        bottomPanel.add(finderPanel);
        bottomPanel.setBackground(yellow);
        

        optionPanel.add(loginButton,BorderLayout.NORTH);

        optionPanel.add(bottomPanel,BorderLayout.SOUTH);
        
        //enterPanel.add(loginButton);
        JPanel marginLabel = new JPanel();
        marginLabel.setBackground(yellow);
        add(marginLabel);
        add(logoPanel);
        add(enterPanel);
        add(optionPanel);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}