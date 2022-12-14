import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModifyInfo extends JFrame{
   
   String id;
   String pw;
   String name;
   String nickName;
   String email;
   String phone;
   String address;
   String word;
   Colors colors;
   
   MainBoardView myboard;
   
   void setBoard(MainBoardView myboard) {
	   this.myboard = myboard;
	}
   
   public ModifyInfo(User me) {  

      id = me.id;
      name = me.name;
      nickName = me.nickName;
      email = me.email;
      phone = me.phone;
      address = me.address;
      word = me.todayM;    
      
      Container con = getContentPane();
        //setUndecorated(true);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700, 400);
        
        con.setBackground(colors.chat_back);
        con.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
      JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(colors.transparent);
        
        JLabel nameDesc = new JLabel("ÀÌ¸§: ");
        nameDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        nameDesc.setForeground(Color.black); 
        JTextField userName = new JTextField(13);
        userName.setText(this.name);
        userName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userName.setForeground(Color.black);
        userName.setBorder(new EmptyBorder(10,10,10,10));

        JPanel nameP = new JPanel();
        nameP.add(nameDesc);
        nameP.add(userName);
        nameP.setBackground(colors.chat_back);

        JLabel nickDesc = new JLabel("´Ð³×ÀÓ: ");
        nickDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        nickDesc.setForeground(Color.black);
        JTextField userNick = new JTextField(13);
        userNick.setText(this.nickName);
        userNick.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userNick.setForeground(Color.black);
        userNick.setBorder(new EmptyBorder(10,10,10,10));

        JPanel nickP = new JPanel();
        nickP.add(nickDesc);
        nickP.add(userNick);
        nickP.setBackground(colors.chat_back);

        JLabel idDesc = new JLabel("¾ÆÀÌµð: ");
        idDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        idDesc.setForeground(Color.black); 
        JTextField userId = new JTextField(13);
        userId.setText(this.id);
        userId.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userId.setForeground(Color.black);
        userId.setBorder(new EmptyBorder(10,10,10,10));

        JPanel idP = new JPanel();
        idP.add(idDesc);
        idP.add(userId);
        idP.setBackground(colors.chat_back);

        JLabel countDesc = new JLabel("·Î±×ÀÎ È½¼ö: ");
        countDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        countDesc.setForeground(Color.black);
        JTextField loginCount = new JTextField(13);
        //loginCount.setText(this.count);
        loginCount.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        loginCount.setForeground(Color.black);
        loginCount.setBorder(new EmptyBorder(10,10,10,10));

        JPanel countP = new JPanel();
        countP.add(countDesc);
        countP.add(loginCount);
        countP.setBackground(colors.chat_back);

        JLabel introDesc = new JLabel("¿À´ÃÀÇ ÇÑ¸¶µð: ");
        introDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        introDesc.setForeground(Color.black);
        JTextField userIntro = new JTextField(13);
        userIntro.setText(this.word);
        userIntro.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userIntro.setForeground(Color.black);
        userIntro.setBorder(new EmptyBorder(10,10,10,10));

        JPanel introP = new JPanel();
        introP.add(introDesc);
        introP.add(userIntro);
        introP.setBackground(colors.chat_back);

        JLabel emailDesc = new JLabel("Email: ");
        emailDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        emailDesc.setForeground(Color.black);
        JTextField userEmail = new JTextField(13);
        userEmail.setText(this.email);        
        userEmail.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userEmail.setForeground(Color.black);
        userEmail.setBorder(new EmptyBorder(10,10,10,10));

        JPanel emailP = new JPanel();
        emailP.add(emailDesc);
        emailP.add(userEmail);
        emailP.setBackground(colors.chat_back);

        JLabel birthDesc = new JLabel("»ýÀÏ: ");
        birthDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        birthDesc.setForeground(Color.black);
        JTextField userBirth = new JTextField(13);
        //userBirth.setText(this.birth);
        userBirth.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userBirth.setForeground(Color.black);
        userBirth.setBorder(new EmptyBorder(10,10,10,10));

        JPanel birthP = new JPanel();
        birthP.add(birthDesc);
        birthP.add(userBirth);
        birthP.setBackground(colors.chat_back);

        JLabel phoneDesc = new JLabel("ÀüÈ­¹øÈ£: ");
        phoneDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        phoneDesc.setForeground(Color.black);
        JTextField userPhone = new JTextField(13);
        userPhone.setText(this.phone);
        userPhone.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userPhone.setForeground(Color.black);
        userPhone.setBorder(new EmptyBorder(10,10,10,10));

        JPanel phoneP = new JPanel();
        phoneP.add(phoneDesc);
        phoneP.add(userPhone);
        phoneP.setBackground(colors.chat_back);

        JLabel addressDesc = new JLabel("ÁÖ¼Ò: ");
        addressDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        addressDesc.setForeground(Color.black);
        JTextField userAddress = new JTextField(13);
        userAddress.setText(this.address);
        userAddress.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userAddress.setForeground(Color.black);
        userAddress.setBorder(new EmptyBorder(10,10,10,10));

        JPanel addressP = new JPanel();
        addressP.add(addressDesc);
        addressP.add(userAddress);
        addressP.setBackground(colors.chat_back);

        JPanel topinfo = new JPanel(new GridBagLayout());
        topinfo.setBackground(colors.transparent);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        topinfo.add(nameP,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        topinfo.add(nickP,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        topinfo.add(idP,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        topinfo.add(emailP,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        //topinfo.add(birthP,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        topinfo.add(phoneP,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        topinfo.add(addressP,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        //topinfo.add(countP,gbc);
        topinfo.add(introP,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        //topinfo.add(introP,gbc);

        gbc.fill = GridBagConstraints.NONE;

        Button_Round modifyButton = new Button_Round("Á¤º¸ º¯°æ");
        modifyButton.setColor(colors.btn_back,colors.btn_text);
        modifyButton.setRound(20, 20);
        modifyButton.setBorder(new EmptyBorder(0,10,10,10));
        modifyButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
              name = userName.getText();
              nickName = userNick.getText();
              email = userEmail.getText();
              phone = userPhone.getText();
              address = userAddress.getText();
              word = userIntro.getText();
              
             Main.changeInfo(id, name, nickName, email, phone, address, word);
             
            }
        });
        topinfo.add(modifyButton,gbc);

//        gbc.gridx = 1;
//        gbc.gridy = 4;

//        topinfo.add(nameDesc);
//        topinfo.add(userName);
//        
//        topinfo.add(nickDesc);
//        topinfo.add(userNick);
//        
//        topinfo.add(idDesc);
//        topinfo.add(userId);
//        
//        topinfo.add(emailDesc);
//        topinfo.add(userEmail);
//        
//        topinfo.add(birthDesc);
//        topinfo.add(userBirth);
//        
//        topinfo.add(phoneDesc);
//        topinfo.add(userPhone);
//        
//        topinfo.add(addressDesc);
//        topinfo.add(userAddress);
//        
//        topinfo.add(countDesc);
//        topinfo.add(loginCount);
//        
//        topinfo.add(introDesc);
//        topinfo.add(userIntro);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topinfo.setBackground(colors.chat_back);
        infoPanel.setBackground(colors.chat_back);
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));

        infoPanel.add(topinfo,gbc);
        add(infoPanel,gbc);
      
   }
}