import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import java.util.ArrayList;
import java.util.Objects;

public class ChatRoomView extends JFrame {
	
	static String myId;
	static String roomId;
	
	private Main mymain;
	private Colors colors;
	
	private JPanel infoPanel = new JPanel();
	private JPanel notionPanel = new JPanel();
	private JPanel chatPanel = new JPanel();
	private JPanel enterPanel = new JPanel();
	
	Button_Round loginButton = new Button_Round("로그인");
	Button_Round registerButton = new Button_Round("회원가입");
    private CardLayout card;

    
	void setMain(Main main) {
		this.mymain = main;
	}
	
	ChatRoomView(String roomId){
		this.roomId = roomId;
		
        Container con = getContentPane();

        setLayout(card = new CardLayout());
        //setUndecorated(true);
        
        //setLocationRelativeTo(null);

        setSize(400, 700);
        
        con.setBackground(colors.chat_back);
        //con.setLayout(new FlowLayout());
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        
        infoPanel = new JPanel(new FlowLayout()); //채팅창 위의 채팅창 정보
        infoPanel.setBackground(colors.chat_back);
        // -----------------------------채팅방 정보 -------------------------
        Image logo = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/image/kakaotalkmain.png"))).getImage();
        Image logoIcon = logo.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        
        JPanel roomNameCountPanel = new JPanel(new GridLayout(2,1,5,5));
        roomNameCountPanel.setBackground(colors.chat_back);
        JLabel roomName = new JLabel(roomId+"님이 개설한 방");
        JLabel roomCount = new JLabel("2");
        roomNameCountPanel.add(roomName);
        roomNameCountPanel.add(roomCount);
        
        JPanel functionLogoPanel = new JPanel(new FlowLayout(2));
        functionLogoPanel.setBackground(colors.chat_back);
        Button_Round function1 = new Button_Round(" ");
        function1.setColor(colors.btn_back, colors.btn_text);
        
        Button_Round function2 = new Button_Round(" ");
        function2.setColor(colors.btn_back, colors.btn_text);

        Button_Round function3 = new Button_Round(" ");
        function3.setColor(colors.btn_back, colors.btn_text);

        functionLogoPanel.add(function1);
        functionLogoPanel.add(function2);
        functionLogoPanel.add(function3); //기능들 집합 끝
        
        infoPanel.add(new JLabel(new ImageIcon(logoIcon)));
        infoPanel.add(roomNameCountPanel);
        infoPanel.add(functionLogoPanel);
        infoPanel.setBorder(new EmptyBorder(10,10,10,10));

        
        // -----------------------------채팅방 정보 -------------------------       
        
        
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));        
        chatPanel.setBackground(colors.chat_back);
        JScrollPane chatScroll = new JScrollPane(chatPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScroll.getVerticalScrollBar().setUnitIncrement(20);
        //chatPanel.setMinimumSize(new Dimension(400,400));
        
        
        ArrayList<Message> message = addChatMessage(this.roomId); // 채팅 내용 불러오기
        
        
        
        //채팅 메시지 표현---------------------------------------------------------------
        for(int i = 0; i < message.size(); i++) {
        	String id = message.get(i).sender;
			messages new_msg = new messages(id,message.get(i).content,message.get(i).now);
			chatPanel.add(new_msg);

			chatPanel.add(new JLabel(""));
		}
        setVisible(true);
        chatScroll.setViewportView(chatPanel);
        chatScroll.setBorder(new EmptyBorder(0,0,0,0));
        chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());

        //채팅 메시지 표현-------------------------------------------------------------------
        
        enterPanel = new JPanel();
        enterPanel.setLayout(new BoxLayout(enterPanel, BoxLayout.Y_AXIS));
        enterPanel.setBackground(Color.white);
        enterPanel.setBorder(BorderFactory.createEmptyBorder(30,45,0,45));
        
        JTextField inputField = new JTextField(50);
        inputField.setBorder(new EmptyBorder(10,10,10,10));
       
        Font gainFont = new Font("맑은 고딕", Font.PLAIN, 14);
        Font lostFont = new Font("맑은 고딕", Font.PLAIN, 14);
        inputField.setText("");
        inputField.setFont(gainFont);
        inputField.setForeground(Color.BLACK);
        inputField.setMargin(new Insets(0,0,0,0));
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        Button_Round chatButton = new Button_Round("전송");
        chatButton.setColor(colors.light_gray,Color.gray);
        chatButton.setDark(true);
        
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String msg = inputField.getText();
            	//myId
            	//roomId
            	
            	
            	
            }
        });
        
        enterPanel.add(inputField);
        enterPanel.add(bottomPanel);
        enterPanel.setBorder(new EmptyBorder(0,0,0,0));

        
        Timer t1=new Timer(100,new ActionListener(){
            public void actionPerformed(ActionEvent e)
                {
    			String checktext1 = inputField.getText();
    			//System.out.println(checktext1);

    			if(!checktext1.equals("시험용")) {
    				if(checktext1.length() <1) {
    					chatButton.setColor(colors.light_gray, Color.gray);
    					chatButton.setEnabled(false);
    					enterPanel.repaint();
	    			
	    			}else if(checktext1.length() >=1) {
	    				chatButton.setColor(colors.background, colors.btn_text);
	    				chatButton.setEnabled(true);
	    				enterPanel.repaint();
	
	    			}

    				
    			}
                }
                });
                t1.start();
       
        bottomPanel.add(chatButton);
        bottomPanel.setBackground(colors.chat_other);
        
        add(infoPanel);
        add(chatScroll);
        add(enterPanel);
        
        
        
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
	
	public ArrayList<Message> addChatMessage(String roomID) {
		 return Main.readTalk(roomID);
	}
	
	static class messages extends JPanel {
		Colors colors;
		
		messages(String id, String msg, String time) {
			
    		if(id.equals(myId)) { //자기 아이디일 때
    			setLayout(new FlowLayout(2)); //우로 정렬
    			setBackground(colors.chat_back);
    			this.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    			
    			Button_Round message = new Button_Round();
    			message.setColor(colors.chat_me, Color.black);
    			message.setShow(false);
    			message.setRound(20, 20);
    			message.setText(msg);
    			
    			message.setBackground(colors.chat_me);
    			message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    			message.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
    			message.setOpaque(true);

    			JLabel timeLabel = new JLabel();
    			timeLabel.setText("\n"+time);
    			timeLabel.setBackground(colors.chat_back);
    			timeLabel.setForeground(colors.chat_time);
    			timeLabel.setOpaque(true);
    			timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
    			timeLabel.setVerticalAlignment(JLabel.BOTTOM);

    			JPanel forTime = new JPanel();
    			forTime.setBackground(colors.transparent);
    			forTime.setLayout(new GridLayout(2,1));
    			forTime.add(new JLabel());
    			forTime.add(timeLabel);
    			add(forTime);
    			add(message);
    		}else { //타인의 아이디일 때
    			setLayout(new FlowLayout(0)); //좌로 정렬
    			setBackground(colors.chat_back);
    			
    			JLabel name = new JLabel();
    			name.setText(id);
    			name.setBackground(colors.chat_back);
    			name.setFont(new Font("맑은 고딕", Font.BOLD, 12));

    			
    			Button_Round message = new Button_Round();
    			message.setColor(colors.chat_other, Color.black);
    			message.setShow(false);
    			message.setRound(20, 20);
    			message.setText(msg);    			message.setText(msg);
    			message.setBackground(colors.chat_other);
    			message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    			message.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
    			message.setOpaque(true);

    			JLabel timeLabel = new JLabel();
    			timeLabel.setText(time);
    			timeLabel.setBackground(colors.chat_back);
    			timeLabel.setForeground(colors.chat_time);
    			timeLabel.setOpaque(true);
    			timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
    			
    			JPanel forTime = new JPanel();
    			forTime.setBackground(colors.transparent);
    			forTime.setLayout(new GridLayout(2,1));
    			forTime.add(new JLabel());
    			forTime.add(timeLabel);
    			
    			JPanel forMessage = new JPanel();
    			forMessage.setLayout(new BorderLayout());
    			//forMessage.setAlignmentX(RIGHT_ALIGNMENT);
    			forMessage.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    			forMessage.setBackground(colors.chat_back);

    			JPanel forMessageT = new JPanel();
    			forMessageT.setLayout(new FlowLayout());
    			forMessageT.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    			forMessageT.setBackground(colors.transparent);
    			
    			JPanel forName = new JPanel();
    			forName.setLayout(new FlowLayout(0));
    			forName.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    			forName.setBackground(colors.transparent);
    			forName.add(name);
    			
    			forMessage.add(forName,BorderLayout.NORTH);
    			forMessage.add(forMessageT,BorderLayout.WEST);

    			forMessageT.add(message);
    			forMessageT.add(forTime);
    			
    			add(forMessage);    			
    		}
    		
            
    	}
    }
}