

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	enum DialogChoice { OPEN, SAVE } 
	
	private void serverStart(){
		//Socket으로 읽어서 file로 쓴다.
		
		//String file =showDialog(DialogChoice.SAVE);
		
		File saveDir = showDialog(DialogChoice.SAVE);
		
		if(!saveDir.exists()){ //저장할 폴더가 없으면 만들어준다.
			saveDir.mkdirs();
			
		}
		
		try {
			ServerSocket server = new ServerSocket(7777);//포트번호
			System.out.println("서버가 준비중입니다...");
			
			Socket socket = server.accept(); // 클라이언트의 요청을 기다린다.
			
			System.out.println("파일 저장 시작...");
			
			//소켓용 입력 스트림 객체 생성
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			BufferedInputStream bis = new BufferedInputStream(dis);
			
			//클라이언트가 접속되었을 때 첫번째로 보내온 파일이름을 받는다.
			String fileName = dis.readUTF();
			
			//File saveFile = new File(saveDir, "멍멍.jpg");
			File saveFile = new File(saveDir, fileName);
			
			/*
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			BufferedInputStream bis = new BufferedInputStream(dis);
			*/
			
		
			//파일 출력용 스트림 객체 생성
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(saveFile)
					);
			
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			while((length = bis.read(temp)) > 0){
				bos.write(temp , 0, length);
				
			}
			bos.flush();
			
			System.out.println("파일 저장 완료...");
			
			
			//스트림과 소켓 닫기
			bos.close();
			bis.close();
			socket.close();
			server.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 저장 실패!!" + e.getMessage());
		}
		
	}
	
	public File showDialog(DialogChoice choice){
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		/*
		FileNameExtensionFilter doc = 
				new FileNameExtensionFilter("Word File", "docx", "doc");
		FileNameExtensionFilter img =
				new FileNameExtensionFilter("Image File", new String[]{"png", "jpg", "gif"});
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("text파일(.txt)", "txt");
		
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(txt);
		
		// 확장자 목록 중 기본적으로 선택될 확장자 지정
		chooser.setFileFilter(txt); 
		*/
		
		// 모든 파일 목록 표시 여부 설정(true: 설정, false: 해제)
		//chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAcceptAllFileFilterUsed(true);
		
		// Dialog창에 나타날 기본 경로 설정하기
		chooser.setCurrentDirectory(new File("C:\\Users\\"));
		int result = -1;
		if(choice==DialogChoice.OPEN){
			
			// 열기 창
			result = chooser.showOpenDialog(new Panel());//부모창
		}else if(choice==DialogChoice.SAVE){
			// 저장 창
			result = chooser.showSaveDialog(new Panel());
		}
		// '열기 창' 또는 '저장 창'에서 선택한 파일 정보 가져오기
		
		
		File selectedFile = null;
		// '열기' 또는 '저장'버튼을 눌렀을 경우에는...
		if(result == JFileChooser.APPROVE_OPTION){ 
			// 선택한 파일 정보 구하기
			selectedFile = chooser.getSelectedFile();
			//System.out.println("선택 파일 : " + selectedFile.getAbsolutePath());
		}
		return selectedFile;
	}
	
	public static void main(String[] args) {
       new TcpFileServer().serverStart();
		
		
	}

}