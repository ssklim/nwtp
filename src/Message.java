import java.io.*;
import java.time.LocalDateTime;

public class Message implements Serializable{
    public String sender;
    public String roomId;
    public String content;
    public String now;

    Message(String sender, String roomId, String content, String now){
        this.sender = sender;
        this.roomId = roomId;
        this.content = content;
        this.now = now;
    }
}