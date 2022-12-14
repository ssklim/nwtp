import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.NoSuchAlgorithmException;

public class PwFinder extends JFrame {
	PwFinder() {
		new InputInfoPw();
	}

	static String newPw = "";
	static Colors colors;

	static class InputInfoPw extends JFrame {
		InputInfoPw() {
			setSize(400, 600);
			setLayout(new GridLayout(2, 1));
			setBackground(colors.background);
			setLocationRelativeTo(null);

			JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
			inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
			inputPanel.setBackground(colors.background);

			JLabel inputDesc = new JLabel("비밀번호 찾기");
			inputDesc.setHorizontalAlignment(JLabel.CENTER);
			inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

			JLabel inputInfoDesc = new JLabel("아이디를 입력하세요");
			inputInfoDesc.setHorizontalAlignment(JLabel.CENTER);
			inputInfoDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

			JTextField inputId = new JTextField();

			Font gainFont = new Font("맑은 고딕", Font.PLAIN, 15);
			Font lostFont = new Font("맑은 고딕", Font.PLAIN, 15);
			inputId.setBorder(new EmptyBorder(10, 10, 10, 10));
			inputId.setMargin(new Insets(10, 10, 10, 10));
			inputId.setText("아이디를 입력하세요");
			inputId.setFont(lostFont);
			inputId.setForeground(Color.GRAY);
			inputId.addFocusListener(new FocusListener() { // 텍스트 필드 포커스 시 이벤트

				@Override
				public void focusLost(FocusEvent e) { // 포커스를 잃었을 때,
					if (inputId.getText().equals("")) {
						inputId.setText("아이디를 입력하세요");
						inputId.setFont(lostFont);
						inputId.setForeground(Color.GRAY);
					}
				}

				@Override
				public void focusGained(FocusEvent e) { // 포커스를 얻었을 때,
					if (inputId.getText().equals("아이디를 입력하세요")) {
						inputId.setText("");
						inputId.setFont(gainFont);
						inputId.setForeground(Color.BLACK);
					}
				}
			});

			JPasswordField inputPw = new JPasswordField(20);
			inputPw.setBorder(new EmptyBorder(10, 10, 10, 10));
			inputPw.setEchoChar((char) 0);

			inputPw.setMargin(new Insets(10, 10, 10, 10));

			inputPw.setText("현재 비밀번호를 입력하세요");
			inputPw.setFont(lostFont);
			inputPw.setForeground(Color.GRAY);
			inputPw.addFocusListener(new FocusListener() { // 텍스트 필드 포커스 시 이벤트

				@Override
				public void focusLost(FocusEvent e) { // 포커스를 잃었을 때,
					if (inputPw.getText().equals("")) {
						inputPw.setEchoChar((char) 0);
						inputPw.setText("현재 비밀번호를 입력하세요");
						inputPw.setFont(lostFont);
						inputPw.setForeground(Color.GRAY);
					} else {
						inputPw.setEchoChar('*');
					}
				}

				@Override
				public void focusGained(FocusEvent e) { // 포커스를 얻었을 때,
					if (inputPw.getText().equals("현재 비밀번호를 입력하세요")) {
						inputPw.setText("");
						inputPw.setEchoChar('*');
						inputPw.setFont(gainFont);
						inputPw.setForeground(Color.BLACK);
					}
				}
			});

			Button_Round toChangePw = new Button_Round("다음");
			toChangePw.setColor(Color.white, Color.gray);
			toChangePw.setBackground(new Color(0x371D1E));
			toChangePw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			toChangePw.setForeground(Color.WHITE);

			toChangePw.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = inputId.getText();
					String pw = "";

					for (char pwd : inputPw.getPassword()) {
						pw += pwd;
					}

					SHA256 sha = new SHA256();
					try {
						pw = sha.encrypt(pw);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}

					String checkPw = Main.findPw(id, pw);
					if (!checkPw.equals("continue"))
						inputInfoDesc.setText(checkPw);
					else {
						dispose();
						new ChangePw();
					}
				}
			});
			Timer t1 = new Timer(100, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String checktext1 = inputId.getText();
					char[] checktext2 = inputPw.getPassword();
					String checkPw = String.valueOf(checktext2);

					if (!checktext1.equals("아이디를 입력하세요") && !checkPw.equals("비밀번호를 입력하세요")) {
						if (checktext1.length() < 1 || checkPw.length() < 1) {
							toChangePw.setColor(Color.white, Color.gray);
							toChangePw.setEnabled(false);
							inputPanel.repaint();

						} else if (checktext1.length() >= 1 && checkPw.length() >= 1) {
							toChangePw.setColor(colors.btn_back, colors.btn_text);
							toChangePw.setEnabled(true);
							inputPanel.repaint();

						}
					}
				}
			});
			t1.start();

			inputPanel.add(inputDesc);
			inputPanel.add(inputInfoDesc);
			inputPanel.add(inputId);
			inputPanel.add(inputPw);
			inputPanel.add(toChangePw);

			JPanel margin = new JPanel();
			margin.setBackground(colors.background);

			add(inputPanel);
			add(margin);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}

	static class ChangePw extends JFrame {
		Colors colors;

		ChangePw() {
			setSize(400, 600);
			setLayout(new GridLayout(2, 1));
			setBackground(colors.background);
			setLocationRelativeTo(null);

			JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
			inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
			inputPanel.setBackground(colors.background);

			JLabel inputDesc = new JLabel("비밀번호 변경");
			inputDesc.setHorizontalAlignment(JLabel.CENTER);
			inputDesc.setFont(new Font("맑은 고딕", Font.BOLD, 24));

			JLabel inputPwDesc = new JLabel("새 비밀번호를 입력하세요");
			inputPwDesc.setHorizontalAlignment(JLabel.CENTER);
			inputPwDesc.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

			JPasswordField inputPw = new JPasswordField();
			Font gainFont = new Font("맑은 고딕", Font.PLAIN, 14);
			Font lostFont = new Font("맑은 고딕", Font.PLAIN, 14);

			inputPw.setBorder(new EmptyBorder(10, 10, 10, 10));
			inputPw.setMargin(new Insets(10, 10, 10, 10));
			inputPw.setEchoChar((char) 0);
			inputPw.setText("비밀번호를 입력하세요");
			inputPw.setFont(lostFont);
			inputPw.setForeground(Color.GRAY);

			Button_Round toLogin = new Button_Round("변경 완료");
			toLogin.setColor(Color.white, Color.gray);
			toLogin.setBackground(new Color(0x371D1E));
			toLogin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			toLogin.setForeground(Color.WHITE);
			// ---------------------- 비밀번호 변경 ----------------------------
			toLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (char pwd : inputPw.getPassword()) {
						newPw += pwd;
					}
					
					SHA256 sha = new SHA256();
					try {
						newPw = sha.encrypt(newPw);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}

					String change = Main.changePw(newPw);
					if (change.equals("Password change success")) {
						dispose();
						// LoginWindow news = new LoginWindow();
						// news.setMain(myMain); // 로그인창에게 메인 클래스보내기

					} else
						inputPwDesc.setText(change);
					// id에 아이디, newPw에 바꿀 비밀번호가 들어감
					// 아이디를 통해 접근해서 비밀번호를 newPw로 바꿔주면 됩니다
				}
			});

			inputPw.addFocusListener(new FocusListener() { // 텍스트 필드 포커스 시 이벤트

				@Override
				public void focusLost(FocusEvent e) { // 포커스를 잃었을 때,
					if (inputPw.getText().equals("")) {
						inputPw.setEchoChar((char) 0);
						inputPw.setText("비밀번호를 입력하세요");
						inputPw.setFont(lostFont);
						inputPw.setForeground(Color.GRAY);
					} else {
						inputPw.setEchoChar('*');
					}
				}

				@Override
				public void focusGained(FocusEvent e) { // 포커스를 얻었을 때,
					if (inputPw.getText().equals("비밀번호를 입력하세요")) {
						inputPw.setText("");
						inputPw.setEchoChar('*');
						inputPw.setFont(gainFont);
						inputPw.setForeground(Color.BLACK);
					}
				}
			});

			Timer t1 = new Timer(100, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String checktext1 = inputPw.getText();
					// System.out.println(checktext1 + checkPw);

					if (!checktext1.equals("새 비밀번호를 입력하세요")) {
						if (checktext1.length() < 1) {
							toLogin.setColor(Color.white, Color.gray);
							toLogin.setEnabled(false);
							inputPanel.repaint();

						} else if (checktext1.length() >= 1) {
							toLogin.setColor(colors.btn_back, colors.btn_text);
							toLogin.setEnabled(true);
							inputPanel.repaint();

						}
					}
				}
			});
			t1.start();
			// ---------------------- 비밀번호 변경 ----------------------------

			inputPanel.add(inputDesc);
			inputPanel.add(inputPwDesc);
			inputPanel.add(inputPw);
			inputPanel.add(toLogin);

			JPanel margin = new JPanel();
			margin.setBackground(colors.background);

			add(inputPanel);
			add(margin);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}
}