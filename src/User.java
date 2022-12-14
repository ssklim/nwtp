import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class User implements Serializable{   
	InetAddress ip;
	int port;
	String id;
	String pw;
	String name;
	String nickName;
	String email;
	String birth;
	String phone;
	String address;
	String todayM;
	String state;
	String outTime;
	int count;

    User(String id){
        this.id = id;
    }
}