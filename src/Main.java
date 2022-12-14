import java.io.*; //import io
import java.net.*; //import socket
import java.util.*;
import javax.swing.JFrame;

public class Main {
	LoginWindow loginView;
	SignUpView signupView;
	ChatRoomView chatroomview;
	MainBoardView mainboardView;

	static String serverIp = "127.0.0.1"; // ���� ip
	static int serverPort = 9800; // ���� ��Ʈ
	public static Socket clientSocket = null; // Ŭ�� ����
	public static ObjectInputStream in = null;
	public static ObjectOutputStream out = null;

	public static void main(String[] args) {      
	      try {
	         ObjectInputStream inp = new ObjectInputStream(new FileInputStream("server_info.dat"));//read server_info
	         
	         for(int i = 0; i < 2; i++)
	            if(i == 0)
	            	serverIp = inp.readUTF();
	            else 
	            	serverPort =inp.readInt();
	         
	      } catch(FileNotFoundException e) {     
	         System.out.println("no file exist");
	         System.out.println("default server_ip: localhost");
	         System.out.println("default server_port: 9800");
	      }catch(IOException e) {
	         System.out.println(e);
	      }

		// ����Ŭ���� ����
		Main main = new Main();
		try {
			clientSocket = new Socket(serverIp, serverPort); // Ŭ����� ����
			out = new ObjectOutputStream(clientSocket.getOutputStream()); // ���� ��� ��Ʈ�� ����
			in = new ObjectInputStream(clientSocket.getInputStream()); // ���� �Է� ��Ʈ�� ����
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		main.loginView = new LoginWindow(); // �α���â ���̱�
		main.loginView.setMain(main); // �α���â���� ���� Ŭ����������

		// main.chatroomview = new ChatRoomView();
		// main.chatroomview.setMain(main);

		// main.mainboardView = new MainBoardView("�¹�");
		// main.mainboardView.setMain(main);
	}

	public Object logIn(String id, String pw) {
		Object user = null;
		try {
			out.writeObject("logIn");
			out.writeObject(id);
			out.writeObject(pw);
			user = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void logOut(User user) {
		try {
			out.writeObject("logOut");
			out.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String findId(String name, String email) {
		String id = "";
		try {
			out.writeObject("findId");
			out.writeObject(name);
			out.writeObject(email);
			id = (String) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static String findPw(String id, String pw) {
		String check = "";
		try {
			out.writeObject("changePw");
			out.writeObject(id);
			out.writeObject(pw);
			check = (String) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}

	public static String changePw(String newPw) {
		String check = "";
		try {
			out.writeObject(newPw);
			check = (String) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}

	public static User setUser(User user) {
		try {
			out.writeObject("setUser");
			out.writeObject(user);
			user = (User) in.readObject();
			user.ip = clientSocket.getLocalAddress();
			user.port = clientSocket.getLocalPort();
			user.count++;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static String dupCheck(String id, String nickName) {
		String check = "";
		try {
			out.writeObject("dupCheck");
			out.writeObject(id);
			out.writeObject(nickName);
			check = (String) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return check;
	}

	public static void signUp(User user) {
		try {
			out.writeObject("signUp");
			out.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeInfo(String id, String name, String nickName, String email, 
								  String phone, String address, String todayM) {
		try {
			out.writeObject("changeInfo");
			out.writeObject(id);
			out.writeObject(name);
			out.writeObject(nickName);
			out.writeObject(email);
			out.writeObject(phone);
			out.writeObject(address);
			out.writeObject(todayM);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> online(User user){
		ArrayList<User> online = null;
		try {
			out.writeObject("online");
			out.writeObject(user);	
			online = (ArrayList<User>)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return online;
	}
	
	public static ArrayList<User> offline(User user){
		ArrayList<User> offline = null;
		try {
			out.writeObject("offline");
			out.writeObject(user);	
			offline = (ArrayList<User>)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return offline;
	}
	
	public static ArrayList<User> searching(String searchThing){
		ArrayList<User> searching = null;
		try {
			out.writeObject("searching");
			out.writeObject(searchThing);	
			searching = (ArrayList<User>)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return searching;
	}
	
	public static User making(String id) {
		User user = null;
		try {
			out.writeObject("making");
			out.writeObject(id);	
			user = (User)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	public static void removeUser(User user) {
		try {
			out.writeObject("removeUser");
			out.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void plusFriend(User user, String id) {
		try {
			out.writeObject("plusFriend");
			out.writeObject(user);
			out.writeObject(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Message> readTalk(String roomId) {
		ArrayList<Message> message = null;
		
		try {
			out.writeObject("readTalk");
			out.writeObject(roomId);
			message = (ArrayList<Message>)in.readObject();
		}catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
	}
	

	// ���κ���â
	public void showMainBoardView(User user) {
		loginView.dispose(); // �α���â�ݱ�
		user = this.setUser(user);
		this.mainboardView = new MainBoardView(user); // �׽�Ʈ������ ����
		this.mainboardView.setMain(loginView.getMain());
	}

	// ȸ������ â
	public void showSignUpView() {
		this.signupView = new SignUpView(); // �׽�Ʈ������ ����
	}

	// �� ä��â
	public void showChatRoomView(String id) {
		this.chatroomview = new ChatRoomView(id); // �׽�Ʈ������ ����
	}
}
