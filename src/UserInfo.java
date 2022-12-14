import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfo extends JFrame{
	
	
	Colors colors;
	
	public UserInfo(String id) {
		Container con = getContentPane();
        //setUndecorated(true);
		
		User user = Main.making(id);
        
        setLocationRelativeTo(null);
        setSize(500, 300);
        
        con.setBackground(colors.light_gray);
        con.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
		JPanel infoPanel = new JPanel(new GridLayout(1,0));
        infoPanel.setBackground(colors.transparent);
        
        JLabel nameDesc = new JLabel("ÀÌ¸§: ");
        nameDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        nameDesc.setForeground(Color.black); 
        JLabel userName = new JLabel(user.name);
        userName.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userName.setForeground(Color.black);
        JPanel nameP = new JPanel();
        nameP.add(nameDesc);
        nameP.add(userName);

        JLabel nickDesc = new JLabel("´Ð³×ÀÓ: ");
        nickDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        nickDesc.setForeground(Color.black);
        JLabel userNick = new JLabel(user.nickName);
        userNick.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userNick.setForeground(Color.black);
        JPanel nickP = new JPanel();
        nickP.add(nickDesc);
        nickP.add(userNick);
        
        JLabel idDesc = new JLabel("¾ÆÀÌµð: ");
        idDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        idDesc.setForeground(Color.black); 
        JLabel userId = new JLabel(id);
        userId.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userId.setForeground(Color.black);
        JPanel idP = new JPanel();
        idP.add(idDesc);
        idP.add(userId);
        
        JLabel countDesc = new JLabel("·Î±×ÀÎ È½¼ö: ");
        countDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        countDesc.setForeground(Color.black);
        JLabel loginCount = new JLabel(user.count+"");
        loginCount.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        loginCount.setForeground(Color.black);
        JPanel countP = new JPanel();
        countP.add(countDesc);
        countP.add(loginCount);
        
        JLabel introDesc = new JLabel("¿À´ÃÀÇ ÇÑ¸¶µð: ");
        introDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        introDesc.setForeground(Color.black);
        JLabel userIntro = new JLabel(user.todayM);
        userIntro.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userIntro.setForeground(Color.black);
        JPanel introP = new JPanel();
        introP.add(introDesc);
        introP.add(userIntro);
        
        JLabel emailDesc = new JLabel("Email: ");
        emailDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        emailDesc.setForeground(Color.black);
        JLabel userEmail = new JLabel(user.email);
        userEmail.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userEmail.setForeground(Color.black);
        JPanel emailP = new JPanel();
        emailP.add(emailDesc);
        emailP.add(userEmail);
        
        JLabel birthDesc = new JLabel("»ýÀÏ: ");
        birthDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        birthDesc.setForeground(Color.black);
        JLabel userBirth = new JLabel(user.birth);
        userBirth.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userBirth.setForeground(Color.black);
        JPanel birthP = new JPanel();
        birthP.add(birthDesc);
        birthP.add(userBirth);
        
        JLabel phoneDesc = new JLabel("ÀüÈ­¹øÈ£: ");
        phoneDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        phoneDesc.setForeground(Color.black);
        JLabel userPhone = new JLabel(user.phone);
        userPhone.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userPhone.setForeground(Color.black);
        JPanel phoneP = new JPanel();
        phoneP.add(phoneDesc);
        phoneP.add(userPhone);
        
        JLabel addressDesc = new JLabel("ÁÖ¼Ò: ");
        addressDesc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
        addressDesc.setForeground(Color.black);
        JLabel userAddress = new JLabel(user.address);
        userAddress.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
        userAddress.setForeground(Color.black);
        JPanel addressP = new JPanel();
        addressP.add(addressDesc);
        addressP.add(userAddress);
        
        JPanel topinfo = new JPanel(new GridLayout(5,2));
        topinfo.setBackground(colors.transparent);
        
        topinfo.add(nameP);
        topinfo.add(nickP);
        topinfo.add(idP);
        topinfo.add(emailP);
        topinfo.add(birthP);
        topinfo.add(phoneP);
        topinfo.add(addressP);
        topinfo.add(countP);
        topinfo.add(introP);


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
		
        infoPanel.add(topinfo);
        add(infoPanel,gbc);
        setVisible(true);

	}
}