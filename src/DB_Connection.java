import java.io.*;
import java.net.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DB_Connection {
	public Connection con;
	
	public DB_Connection() {
		String port = "3306";
		String dbName = "network";
		
		String url = "jdbc:mysql://localhost:3306/network";
		String userid = "network";
		String pwd = "1234"; 
		
		try {
			con = DriverManager.getConnection(url,userid,pwd);
			System.out.println("Connection Success!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			}
	}
	
	public User setUser(User user) {
		String query = "SELECT * FROM user WHERE id = " +  "\"" + user.id + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {				
				user.pw = rs.getString(4);
				user.name = rs.getString(5);
				user.nickName = rs.getString(6);
				user.email = rs.getString(7);
				user.birth = rs.getString(8);
				user.phone = rs.getString(9);
				user.address = rs.getString(10);
				user.todayM = rs.getString(11);
				user.state = rs.getString(12);
				user.outTime = rs.getString(13);
				user.count = rs.getInt(14);
			}	
			query = "UPDATE user SET count = count + 1, state = \"on\" WHERE id = " + "\"" + user.id + "\""; 
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public String logIn(String id, String pw) {
		String query = "SELECT pw FROM user WHERE id = " + "\"" + id + "\"";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				if(rs.getString(1).equals(pw)) {
					System.out.println(rs.getString(1));
					return "Success";
				}
				else
					return "Wrong Password";
			}
			else
				return "Wrong ID";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void logOut(User me) {
		String myId = me.id;
		String outTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String query= "UPDATE user SET state = \"off\", outTime = " + "\"" + outTime + "\"" + " WHERE id = " + "\"" + myId + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String dupCheck(String id, String nickName) {
		String query = "SELECT id FROM user where id = " + "\"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) 
				return "Check your ID";		
			else {
				query = "SELECT nickName FROM user where nickName = " + "\"" + nickName + "\"";
				rs = stmt.executeQuery(query);
				if (rs.next()) 
					return "Check your NickName";
				else
					return "Success";
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	public void signUp(User user) {
		String query = "INSERT INTO user VALUES (\"" + user.ip + "\", " + user.port + ", \"" + user.id + "\", \"" + 
						user.pw + "\", \"" + user.name + "\", \"" + user.nickName + "\", \"" + user.email + "\", \"" 
						+ user.birth + "\", \"" + user.phone + "\", \"" + user.address + "\", \"" + user.todayM + 
						"\", \"" + user.state + "\", \"" + user.outTime + "\", " + user.count + ")"; 
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String findId(String name, String email) {
		String query = "SELECT id FROM user WHERE name = " + "\"" + name + "\" and email = " + "\"" + email + "\""; ;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Wrong name or email";
	}
	
	public String changePw(String id, String npw) {
		String query = "UPDATE user SET pw = " + "\"" + npw + "\" WHERE id = " + "\"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);
			if (rs == 1) {
				return "Password change success";
			}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void changeInfo(String id, String name, String nickName, String email,
								  String phone, String address, String todayM) {
		String query = "UPDATE user SET name = \"" + name + "\", nickName = \"" + nickName + "\", email = \"" 
						+ email + "\", phone = \"" + phone + "\", address = \"" + address + "\", todayM = \""
						+ todayM + "\" WHERE id = \"" + id + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User makeUser(String id) {
		String query = "SELECT * FROM user WHERE id = " +  "\"" + id + "\"";
		User user = new User(id);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				user.ip = InetAddress.getByName(rs.getString(1));
				user.port = rs.getInt(2);
				user.id = rs.getString(3);
				user.pw = rs.getString(4);
				user.name = rs.getString(5);
				user.nickName = rs.getString(6);
				user.email = rs.getString(7);
				user.birth = rs.getString(8);
				user.phone = rs.getString(9);
				user.address = rs.getString(10);
				user.todayM = rs.getString(11);
				user.state = rs.getString(12);
				user.outTime = rs.getString(13);
				user.count = rs.getInt(14);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<User> online(User user) {

		String query = "SELECT Id from user WHERE state = \"on\" and id in (select toid from friend where fromid= \"" + user.id + "\")";
		ArrayList<User> onUsers = new ArrayList<User>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(query);

			int count = 1;
			while (rs.next()) {
				System.out.println(rs.getString(1)+"sdeeeee");

				User onUser = makeUser(rs.getString(1));
				onUsers.add(onUser);
				count++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return onUsers;
	}
	
	public ArrayList<User> offline(User user) {
		String query = "SELECT Id from user WHERE state = \"off\" and id in (select toid from friend where fromid= \"" + user.id + "\")";
		ArrayList<User> offUsers = new ArrayList<User>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int count = 1;
			while (rs.next()) {
				System.out.println(rs.getString(1)+"sd");
				User offUser = makeUser(rs.getString(1));
				offUsers.add(offUser);
				count++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return offUsers;
	}
	public ArrayList<User> searching(String searchThing) {
        
        String users="SELECT id FROM user where id like \'%" + searchThing + "%\'" + "OR nickName like \'%" + searchThing + "%\'";
        
		ArrayList<User> searchUsers = new ArrayList<User>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(users);
			while (rs.next()) {
				User searchUser = makeUser(rs.getString(1));
				searchUsers.add(searchUser);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return searchUsers;
	}
	public void removeUser(User me) {
		String myId = me.id;
		String query= "DELETE FROM user WHERE id = " + "\"" + myId + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		String query2= "DELETE FROM friend WHERE id = " + "\"" + myId + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query2);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		String query3= "DELETE FROM room WHERE id = " + "\"" + myId + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query3);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void plusFriend(User me, String toId) {
		String myId = me.id;
		String query= "INSERT INTO friend VALUES(\"" + myId + "\" ,\""+toId+"\")";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Message> readTalk(String roomId) {
		ArrayList<Message> messages = new ArrayList<Message>();
		String query = "SELECT * FROM message WHERE roomId = \"" + roomId + "\"";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String sender = rs.getString(1);
				String receiver = rs.getString(2);
				String content = rs.getString(3);
				String now = rs.getInt(4)+rs.getInt(5)+rs.getInt(6)+rs.getInt(7)+rs.getInt(8)+"";
				messages.add(new Message(sender, receiver, content, now));			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public void writeTalk(Message talk) { //데이터베이스에 채팅내용 저장하기
		String sender = talk.sender;
		String roomId = talk.roomId;
		String content = talk.content;
//		int year = talk.now.getYear();  // 연도
//		int monthValue = talk.now.getMonthValue();  // 월(숫자)
//		int dayOfMonth = talk.now.getDayOfMonth();  // 일(월 기준)
//		int hour = talk.now.getHour();
//		int minute = talk.now.getMinute();
//		int second = talk.now.getSecond();
		
	  //	String query="INSERT INTO Room VALUES(" + "\"" +  sender + "\", "  + roomId + ", " + "\"" + content + "\"," + year + ", " + monthValue + ", " + dayOfMonth + ", " + hour + ", " + minute + ", " + second + ")"; 
//	  	System.out.println(query);
//	  	try { 
//	  	  	Statement stmt=con.createStatement();
//	  	  	stmt.execute(query);
//
//	  	} catch(SQLException e) {
//	  	  	e.printStackTrace();
//	  	}
	}
}