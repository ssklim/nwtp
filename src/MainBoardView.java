import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class MainBoardView extends JFrame{
	private static User user;
	static boolean change;
	
	static String myId;
	private Main mymain;
	private Colors colors;
	private static MyMouseListener mouse = new MyMouseListener();
	
	private JPanel topUserInfoPanel = new JPanel();
	
	private JPanel leftListPanel = new JPanel();
	private JPanel leftListPanel1 = new JPanel();
	private JPanel leftListPanel2 = new JPanel();
	
	private JPanel rightListPanel = new JPanel();
	private JPanel rightListPanel1 = new JPanel();
	private JPanel rightListPanel2 = new JPanel();
	
	private JPanel BottomSidePanel = new JPanel();

	private JPanel centerChatPanel = new JPanel();
	private JPanel centerChatPanel1 = new JPanel();

	private JScrollPane leftListScroll = new JScrollPane();
	private JScrollPane leftListScrollOff = new JScrollPane();
	
	private JScrollPane rightListScroll = new JScrollPane();
	
	private JScrollPane centerChatScroll = new JScrollPane();

	Button_Round loginButton = new Button_Round("로그인");
	Button_Round registerButton = new Button_Round("회원가입");
    private CardLayout card;

    
	void setMain(Main main) {
		this.mymain = main;
	}
	
	public MainBoardView(User user) {
		this.user= user;
		//TcpFileServer filesever = new TcpFileServer();
		//filesever.main(null); // 파일 받는 소켓 미리 열기
		
        Container con = getContentPane();

        setLocationRelativeTo(null);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        con.setBackground(colors.background);
        setLayout(new BorderLayout());
        
        Timer t1=new Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent e)
                {
             if(change == true) {
            	 setTopSide();
                 setLeftSide();
                 setCenterSide();
                 setRightSide("");
                 change = false;
                 System.out.println("changed");
             }
                }
                });
                t1.start();
                
        setTopSide();
        setLeftSide();
        setCenterSide();
        setBottomSide();
        setRightSide("");
        
        JPanel mergeCenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mergeCenterPanel.add(leftListPanel,gbc);
                
        JPanel test = new JPanel(new GridLayout(2,1));
        //test.add(centerChatScroll);
        //test.add(rightListScroll);
        
        gbc.weightx = 0.4;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mergeCenterPanel.add(centerChatPanel,gbc);
        
        
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        mergeCenterPanel.add(rightListPanel,gbc);
        
        add(topUserInfoPanel,BorderLayout.NORTH);
//        add(leftListPanel,BorderLayout.WEST);
//        add(centerChatScroll,BorderLayout.CENTER);
//        add(rightListScroll,BorderLayout.EAST);
        add(mergeCenterPanel,BorderLayout.CENTER);
        add(BottomSidePanel,BorderLayout.SOUTH);

        //add(BottomSidePanel,BorderLayout.EAST);
        
        setVisible(true);
    }
	
	public void setTopSide() {
		topUserInfoPanel.removeAll();
		//서버에게 db요청해서 내 정보를 받아옴 - 객체로
		topUserInfoPanel.setLayout(new GridBagLayout());
		topUserInfoPanel.setBackground(colors.chat_back);
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.ipady = 10;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

		JPanel topUserInfoLeftPanel = new JPanel(new FlowLayout(0));
        topUserInfoPanel.add(topUserInfoLeftPanel, gbc);
        topUserInfoLeftPanel.setBackground(colors.chat_back);
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;		
        
		JPanel topUserInfoCenterPanel = new JPanel(new FlowLayout(2));
		topUserInfoPanel.add(topUserInfoCenterPanel, gbc);
		topUserInfoCenterPanel.setBackground(colors.chat_back);

		gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;		
        
		JPanel topUserInfoRightPanel = new JPanel(new FlowLayout(2));
		topUserInfoPanel.add(topUserInfoRightPanel ,gbc);
		topUserInfoRightPanel.setBackground(colors.chat_back);

		Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/user.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        
        JPanel infoPanel = new JPanel(new GridLayout(1,0));
        infoPanel.setBackground(colors.chat_back);
        
        JLabel nameDesc = new JLabel("이름: ");
        nameDesc.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        nameDesc.setForeground(Color.black); 
        JLabel userName = new JLabel(user.name);
        userName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        userName.setForeground(Color.black);
        
        JLabel nickDesc = new JLabel("닉네임: ");
        nickDesc.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        nickDesc.setForeground(Color.black);
        JLabel userNick = new JLabel(user.nickName);
        userNick.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        userNick.setForeground(Color.black);
        
        JLabel idDesc = new JLabel("아이디: ");
        idDesc.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        idDesc.setForeground(Color.black); 
        JLabel userId = new JLabel(user.id);
        userId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        userId.setForeground(Color.black);

        JLabel countDesc = new JLabel("로그인 횟수: ");
        countDesc.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        countDesc.setForeground(Color.black);
        JLabel loginCount = new JLabel(user.count + "");
        loginCount.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        loginCount.setForeground(Color.black);
        
        JLabel introDesc = new JLabel("오늘의 한마디: ");
        introDesc.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        introDesc.setForeground(Color.black);
        JLabel userIntro = new JLabel(user.todayM);
        userIntro.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        userIntro.setForeground(Color.black);
        
        JPanel topinfo = new JPanel(new GridLayout(3,4));
        topinfo.setBackground(colors.chat_back);
        topinfo.add(nameDesc);
        topinfo.add(userName);
        topinfo.add(nickDesc);
        topinfo.add(userNick);
        topinfo.add(idDesc);
        topinfo.add(userId);
        topinfo.add(countDesc);
        topinfo.add(loginCount);
        topinfo.add(countDesc);
        topinfo.add(loginCount);
        topinfo.add(introDesc);
        topinfo.add(userIntro);
        
        JPanel bottominfo = new JPanel();
        //bottominfo.add(userIntro);
               
        infoPanel.add(topinfo);
        //infoPanel.add(bottominfo);
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        Font gainFont = new Font("맑은 고딕", Font.PLAIN, 13);
        Font lostFont = new Font("맑은 고딕", Font.PLAIN, 13);

        //중간부분
		Button_Round changeInfoButton = new Button_Round("정보변경");
		changeInfoButton.setFont(gainFont);
		changeInfoButton.setRound(20,20);
		changeInfoButton.setColor(colors.btn_back, colors.btn_text);
		changeInfoButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        
		changeInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ModifyInfo myinfo = new ModifyInfo(user);
                 // id에 아이디, newPw에 바꿀 비밀번호가 들어감
                 // 아이디를 통해 접근해서 비밀번호를 newPw로 바꿔주면 됩니다
            }
        });
        
		//중간부분
		//로그아웃
				Button_Round logoutButton = new Button_Round("로그아웃");
				logoutButton.setFont(gainFont);
				logoutButton.setRound(20,20);
				logoutButton.setColor(colors.btn_back, colors.btn_text);
				logoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		        
				logoutButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	Main.logOut(user);		          		            	
		            	dispose();
		            }
		        });
				
				//회원탈퇴
				Button_Round outButton = new Button_Round("회원탈퇴");
				outButton.setFont(gainFont);
				outButton.setRound(20,20);
				outButton.setColor(colors.btn_back, colors.btn_text);
				outButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		        
				outButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	
		            	Main.removeUser(user);		          		            	
		            	dispose();
		            }
		        });
        
        JTextField searchField = new JTextField(19);
        searchField.setBorder(new EmptyBorder(10,10,10,10));
        searchField.setMargin(new Insets(10,10,10,10));
        
        searchField.setText("아이디 또는 닉네임을 검색하세요");
        searchField.setFont(lostFont);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {	// 텍스트 필드 포커스 시 이벤트

            @Override
            public void focusLost(FocusEvent e) {	// 포커스를 잃었을 때,
                if (searchField.getText().equals("")) {
                	searchField.setText("아이디 또는 닉네임을 검색하세요");
                	searchField.setFont(lostFont);
                	searchField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {	// 포커스를 얻었을 때,
                if (searchField.getText().equals("아이디 또는 닉네임을 검색하세요")) {
                	searchField.setText("");
                	searchField.setFont(gainFont);
                	searchField.setForeground(Color.BLACK);
                }
            }
        });
        //검색버튼 누를 때
        Button_Round searchButton = new Button_Round("검색");
        searchButton.setRound(20,20);
        searchButton.setFont(gainFont);
        searchButton.setColor(colors.btn_back, colors.btn_text);
        searchButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String wantSearch = searchField.getText();
            	rightListPanel.removeAll();
            	rightListPanel1.removeAll();
            	setRightSide(wantSearch);
                 // id에 아이디, newPw에 바꿀 비밀번호가 들어감
                 // 아이디를 통해 접근해서 비밀번호를 newPw로 바꿔주면 됩니다
            }
        });
        
        
        
        
        topUserInfoLeftPanel.add(new JLabel(new ImageIcon(logoIcon)));
        topUserInfoLeftPanel.add(infoPanel);
        
        //topUserInfoCenterPanel.add(changeInfoButton);
        
        topUserInfoRightPanel.add(changeInfoButton);
        topUserInfoRightPanel.add(logoutButton);
        topUserInfoRightPanel.add(outButton);
		topUserInfoRightPanel.add(searchField);
		topUserInfoRightPanel.add(searchButton);
		searchButton.setAlignmentX(CENTER_ALIGNMENT);
		searchField.setAlignmentX(CENTER_ALIGNMENT);
		searchButton.setAlignmentY(CENTER_ALIGNMENT);
		searchField.setAlignmentY(CENTER_ALIGNMENT);

		//topUserInfoPanel.add(topUserInfoLeftPanel);
		//topUserInfoPanel.add(topUserInfoCenterPanel);
		//topUserInfoPanel.add(topUserInfoRightPanel);

	}
	public void setLeftSide() {
		leftListPanel.removeAll();
		leftListPanel1.removeAll();
		leftListPanel2.removeAll();

		leftListScroll.getViewport().setBackground(colors.light_gray);
		leftListScroll.getVerticalScrollBar().setUnitIncrement(20);

		leftListScrollOff.getViewport().setBackground(colors.chat_other); //offline
		leftListScrollOff.getVerticalScrollBar().setUnitIncrement(20);

		//현재 아이디와 친구인 다른 사용자들의 리스트들을 띄움
		JPanel allList = new JPanel();
		leftListPanel.setLayout(new GridLayout(2,1));
		
		JPanel onlinePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        
		JPanel ButtonPanel = new JPanel(new GridLayout(0,2,10,0));

        gbc.weightx = 0.4;
        gbc.weighty = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Button_Round onlineButton = new Button_Round();
        onlineButton.setColor(colors.btn_back, colors.btn_text);
        onlineButton.setRound(20, 20);
        onlineButton.setText("온라인");
        onlineButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));

        ButtonPanel.add(onlineButton);
        
        gbc.weightx = 0.4;
        gbc.weighty = 0.2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        Button_Round offlineButton = new Button_Round();
        offlineButton.setColor(colors.btn_back, colors.btn_text);
        offlineButton.setRound(20, 20);
        offlineButton.setText("오프라인");
        offlineButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));

        ButtonPanel.add(offlineButton);
        
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        //onlinePanel.add(ButtonPanel,gbc);//버튼이 둘 다 위에 있을 때
        
        onlinePanel.add(onlineButton,gbc); //따로 있을 때

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        onlinePanel.add(leftListScroll,gbc); 

		leftListPanel.add(onlinePanel);
		
		JPanel offlinePanel = new JPanel(new GridBagLayout());
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        offlinePanel.add(offlineButton,gbc);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        offlinePanel.add(leftListScrollOff,gbc); 

		leftListPanel.add(offlinePanel);
		
        
		//leftListPanel.add(leftListScrollOff);
		
		leftListPanel1.setLayout(new BoxLayout(leftListPanel1,BoxLayout.Y_AXIS));
		leftListPanel1.setBackground(colors.chat_other);
		leftListPanel1.setOpaque(true);
		
		leftListPanel2.setLayout(new BoxLayout(leftListPanel2,BoxLayout.Y_AXIS));
		leftListPanel2.setBackground(colors.chat_other);
		leftListPanel2.setOpaque(true);
		
		
		JPanel onlineList = new JPanel();
		onlineList.setLayout(new BoxLayout(onlineList, BoxLayout.Y_AXIS));        
		onlineList.setBackground(colors.chat_back);
		
		
		JPanel offlineList = new JPanel();
		offlineList.setLayout(new BoxLayout(offlineList, BoxLayout.Y_AXIS));        
		offlineList.setBackground(colors.chat_back);
		
        //leftListScroll = new JScrollPane(leftListPanel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //leftListScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
        //채팅 메시지 표현---------------------------------------------------------------
        
		ArrayList<User> onUser = Main.online(user);
	
		for(int i = 0; i < onUser.size(); i++) {
	        
			users oneUser = new users(onUser.get(i).nickName,onUser.get(i).id,onUser.get(i).todayM);
			
			leftListPanel1.add(oneUser);
		}
        leftListPanel1.repaint();
        
        ArrayList<User> offUser = Main.offline(user);
    	
		for(int i = 0; i < offUser.size(); i++) {
	        
			users oneUser = new users(offUser.get(i).nickName,offUser.get(i).id,offUser.get(i).todayM);
			
			leftListPanel2.add(oneUser);
		}
        leftListPanel2.repaint();
        
        
        leftListScroll.setViewportView(leftListPanel1);
        leftListScroll.setBorder(new EmptyBorder(0,0,2,0));
        
        leftListScrollOff.setViewportView(leftListPanel2);
        leftListScrollOff.setBorder(new EmptyBorder(2,0,10,0));
		
	}
	public void setRightSide(String wantSearch) {
		rightListPanel1.removeAll();
		rightListPanel.removeAll();

		rightListScroll.getViewport().setBackground(colors.chat_other);
		rightListScroll.getVerticalScrollBar().setUnitIncrement(20);

		JPanel searchPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        Button_Round searchResultButton = new Button_Round();
        searchResultButton.setColor(colors.btn_back, colors.btn_text);
        searchResultButton.setRound(20, 20);
        searchResultButton.setText("검색 결과");
        searchResultButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        searchPanel.add(searchResultButton,gbc);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 9;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        searchPanel.add(rightListScroll,gbc); 

        rightListPanel.setLayout(new GridLayout(1,0));
        rightListPanel.add(searchPanel);

        rightListPanel1.setLayout(new BoxLayout(rightListPanel1,BoxLayout.Y_AXIS));

		rightListPanel1.setBackground(colors.chat_other);
		rightListPanel1.setOpaque(true);
			
        
        //채팅 메시지 표현---------------------------------------------------------------
        //여기서 wantSearch를 이용해 결과물 찾기 
		
		ArrayList<User> searchUser = Main.searching(wantSearch);
		
		for(int i = 0; i < searchUser.size(); i++) {
	        
			users oneUser = new users(searchUser.get(i).nickName,searchUser.get(i).id,searchUser.get(i).todayM);
			
			rightListPanel1.add(oneUser);
		}
		rightListPanel1.repaint();
  
        setVisible(true);
        rightListScroll.setViewportView(rightListPanel1);
        rightListScroll.setBorder(new EmptyBorder(0,0,10,0));
        		
	}
	
	public void setCenterSide() {
		centerChatPanel.removeAll();
		centerChatPanel1.removeAll();

		centerChatScroll.getViewport().setBackground(colors.chat_other);
		centerChatScroll.getVerticalScrollBar().setUnitIncrement(20);
		centerChatScroll = new JScrollPane(centerChatPanel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		//현재 아이디와 친구인 다른 사용자들의 리스트들을 띄움
	
		JPanel chatPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        Button_Round chatButton = new Button_Round();
        chatButton.setColor(colors.btn_back, colors.btn_text);
        chatButton.setRound(20, 20);
        chatButton.setText("채팅방 목록");
        chatButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        
        
        
        
        
        
        chatPanel.add(chatButton,gbc);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 9;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        chatPanel.add(centerChatScroll,gbc); 

        centerChatPanel.setLayout(new GridLayout(1,0));
		centerChatPanel.add(chatPanel);
		
		centerChatPanel1.setLayout(new BoxLayout(centerChatPanel1,BoxLayout.Y_AXIS));
		centerChatPanel1.setBackground(colors.chat_other);
		centerChatPanel1.setOpaque(true);
		
		
		
		
		//채팅 룸들을 가져오다
		for(int i = 0; i < 15; i++) {
	        String[] users = new String[2];
	        users[0] = "승민";
	        users[1] = "한슬";
			chatRooms oneRoom = new chatRooms(user.id,users,"하 힘들다 그냥");
			if(i%2!=0) {
				//oneRoom.setBackground(colors.light_gray);
			}
			oneRoom.addMouseListener(new chatListener());
			centerChatPanel1.add(oneRoom);
		}
		centerChatPanel1.repaint();
		
        setVisible(true);
        centerChatScroll.setViewportView(centerChatPanel1);
        centerChatScroll.setBorder(new EmptyBorder(0,0,10,0));
	}
	
	static class users extends JPanel {		
		Colors colors;
		
		users(String name, String id, String intro) {
			

			setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			
			setLayout(new GridBagLayout()); //좌로 정렬
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.BOTH;
	       
			setBackground(colors.chat_other);
			//setBorder(new LineBorder(Color.gray,1));
			Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/user.png"))).getImage();
	        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	        
			JLabel nameLabel = new JLabel();
			nameLabel.setText(name);
			nameLabel.setBackground(colors.chat_other);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
			nameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
			idLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(intro);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.gray);
			introLabel.setOpaque(false);
			introLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			
			JPanel nameIdPanel = new JPanel(new GridLayout(2,1));
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(nameLabel);
			nameIdPanel.add(idLabel);
			
			 gbc.weightx = 2;
		        gbc.weighty = 1;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		       
		        
	        add(new JLabel(new ImageIcon(logoIcon)),gbc);
	        
	        gbc.weightx = 2;
	        gbc.weighty = 1;
	        gbc.gridx = 1;
	        gbc.gridy = 0;
	       
	        
			add(nameIdPanel,gbc);
			
			 gbc.weightx = 6;
		        gbc.weighty = 1;
		        gbc.gridx = 2;
		        gbc.gridy = 0;
		       
		        
			add(introLabel,gbc);
			
			addMouseListener(mouse);
			
			//우클릭 이벤트
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem detail = new JMenuItem("상세정보 보기");
			detail.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			
			detail.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println("상세정보");
	            	new UserInfo(id); //나중에 객체로 넘기면 됨
	            }
	        });

			JMenuItem plus = new JMenuItem("친구 등록하기");
			plus.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			plus.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Main.plusFriend(user,id);
	            	change = true;
	            }
	        });
			
			JMenuItem chat = new JMenuItem("1:1 채팅하기");
			chat.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			chat.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	String roomId = user.id;
	            	
	            	new ChatRoomView(roomId);
	            	
	            	
	            	//new ChatRequest(id);	           
	            }
	        });

			JMenuItem file = new JMenuItem("파일 전송하기");
			file.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			file.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	TcpFileClient newFile = new TcpFileClient();
	            	newFile.main(null); 
	            }
	        });


			//JLabel detailsLabel = new JLabel("상세정보 보기");
			//menus.add(detailsLabel);
			
		      popupMenu.add(detail);
		      popupMenu.add(plus);
		      popupMenu.add(chat);
		      popupMenu.add(file);

		      setComponentPopupMenu(popupMenu);
		      
		}
		
		
	}
	static class chatRooms extends JPanel {		
		Colors colors;
		
		chatRooms(String roomId, String[] id, String lastMessage) {
			setLayout(new GridBagLayout()); //좌로 정렬
			GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill = GridBagConstraints.BOTH;
	        setName(roomId);
	       
			setBackground(colors.chat_other);
			//setBorder(new LineBorder(Color.gray,1));
			Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/chat.png"))).getImage();
	        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
	        
	        
	        
			JLabel nameLabel = new JLabel();
			nameLabel.setText(roomId);
			nameLabel.setBackground(colors.chat_other);
			nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 2, 10));
			nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			nameLabel.setOpaque(false);

			JLabel idLabel = new JLabel();
			idLabel.setText(id[0]+id[1]);
			idLabel.setBackground(colors.chat_other);
			idLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));
			idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			idLabel.setOpaque(false);

			JLabel introLabel = new JLabel();
			introLabel.setText(lastMessage);
			introLabel.setBackground(colors.chat_other);
			introLabel.setForeground(Color.black);
			introLabel.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 10));

			introLabel.setOpaque(false);
			introLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			
			JPanel nameIdPanel = new JPanel(new GridBagLayout());
			gbc.fill = GridBagConstraints.BOTH;
	        
	        gbc.weightx = 1;
	        gbc.weighty = 1;
	        gbc.gridx = 0;
	        gbc.gridy = 0;
			
			nameIdPanel.setBackground(colors.transparent);
			nameIdPanel.add(idLabel,gbc);
			
			gbc.weightx = 1;
	        gbc.weighty = 0.8;
	        gbc.gridx = 0;
	        gbc.gridy = 1;
			nameIdPanel.add(introLabel,gbc);
			
			 gbc.weightx = 0.5;
		        gbc.weighty = 2;
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        add(new JLabel(new ImageIcon(logoIcon)), gbc);
		        
		    	gbc.weightx = 8;
		        gbc.weighty = 1;
		        gbc.gridx = 1;
		        gbc.gridy = 0;
				add(nameIdPanel,gbc);
							
		}

		
		
	}
	
	
	public void setBottomSide() {
        BottomSidePanel.setLayout(new GridLayout(1,5));
        BottomSidePanel.setBorder(new EmptyBorder(10,10,10,10));
        
        LocalDateTime now = LocalDateTime.now();
        
        String formatedbaseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
       
        String formatedbaseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
        
        String baseDate = formatedbaseDate;
        String baseTime = "0000"; //formatedbaseTime;
        
        Map<String, String> weatherMap = WeatherTest.getWeatherList(baseDate, baseTime);
        
        String windV=weatherMap.get("WSD");
        String temp=weatherMap.get("T1H");
        String most=weatherMap.get("REH");
        String windD=weatherMap.get("VEC");
        String rain=weatherMap.get("RN1");
        
        JPanel windVPanel = new JPanel();
        JPanel tempPanel = new JPanel();
        JPanel mostPanel = new JPanel();
        JPanel windDPanel = new JPanel();
        JPanel rainPanel = new JPanel();
        
        JLabel windVDesc = new JLabel("풍속");
        windVDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        JLabel tempDesc = new JLabel("기온");
        tempDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel mostDesc = new JLabel("습도");
        mostDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        
        JLabel windDDesc = new JLabel("풍향");
        windDDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel rainDesc = new JLabel("강수량");
        rainDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));


        JLabel windVLabel = new JLabel(windV+"m/s");
        windVLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel tempLabel = new JLabel(temp+"도");
        tempLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel mostLabel = new JLabel(most+"%");
        mostLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel windDLabel = new JLabel(windD);
        windDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        JLabel rainLabel = new JLabel("현재 1시간" +rain+"mm");
        rainLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        
        windVPanel.setLayout(new GridLayout(2,1));
        windVPanel.add(windVDesc);
        windVPanel.add(windVLabel);
        
        tempPanel.setLayout(new GridLayout(2,1));
        tempPanel.add(tempDesc);
        tempPanel.add(tempLabel);
        
        mostPanel.setLayout(new GridLayout(2,1));
        mostPanel.add(mostDesc);
        mostPanel.add(mostLabel);
        
        windDPanel.setLayout(new GridLayout(2,1));
        windDPanel.add(windDDesc);
        windDPanel.add(windDLabel);
        
        rainPanel.setLayout(new GridLayout(2,1));
        rainPanel.add(rainDesc);
        rainPanel.add(rainLabel);
        
        JLabel test = new JLabel();
        test.setBackground(colors.background);
        test.setOpaque(true);
        
        BottomSidePanel.add(windVPanel);
        BottomSidePanel.add(tempPanel);
        BottomSidePanel.add(mostPanel);
        BottomSidePanel.add(windDPanel);
        BottomSidePanel.add(rainPanel);


        
     }

	
	class chatListener implements MouseListener, MouseMotionListener{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(color.brighter());			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			mymain.showChatRoomView(p.getName());
			
			p.setBackground(color.darker());				
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(color.brighter().brighter().brighter());		
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

