

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
		//Socket���� �о file�� ����.
		
		//String file =showDialog(DialogChoice.SAVE);
		
		File saveDir = showDialog(DialogChoice.SAVE);
		
		if(!saveDir.exists()){ //������ ������ ������ ������ش�.
			saveDir.mkdirs();
			
		}
		
		try {
			ServerSocket server = new ServerSocket(7777);//��Ʈ��ȣ
			System.out.println("������ �غ����Դϴ�...");
			
			Socket socket = server.accept(); // Ŭ���̾�Ʈ�� ��û�� ��ٸ���.
			
			System.out.println("���� ���� ����...");
			
			//���Ͽ� �Է� ��Ʈ�� ��ü ����
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			BufferedInputStream bis = new BufferedInputStream(dis);
			
			//Ŭ���̾�Ʈ�� ���ӵǾ��� �� ù��°�� ������ �����̸��� �޴´�.
			String fileName = dis.readUTF();
			
			//File saveFile = new File(saveDir, "�۸�.jpg");
			File saveFile = new File(saveDir, fileName);
			
			/*
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			BufferedInputStream bis = new BufferedInputStream(dis);
			*/
			
		
			//���� ��¿� ��Ʈ�� ��ü ����
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(saveFile)
					);
			
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			while((length = bis.read(temp)) > 0){
				bos.write(temp , 0, length);
				
			}
			bos.flush();
			
			System.out.println("���� ���� �Ϸ�...");
			
			
			//��Ʈ���� ���� �ݱ�
			bos.close();
			bis.close();
			socket.close();
			server.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ���� ����!!" + e.getMessage());
		}
		
	}
	
	public File showDialog(DialogChoice choice){
		// SWING�� ���� ����, ���� â ����
		JFileChooser chooser = new JFileChooser();
		
		// ������ ������ Ȯ���� ����
		/*
		FileNameExtensionFilter doc = 
				new FileNameExtensionFilter("Word File", "docx", "doc");
		FileNameExtensionFilter img =
				new FileNameExtensionFilter("Image File", new String[]{"png", "jpg", "gif"});
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("text����(.txt)", "txt");
		
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(txt);
		
		// Ȯ���� ��� �� �⺻������ ���õ� Ȯ���� ����
		chooser.setFileFilter(txt); 
		*/
		
		// ��� ���� ��� ǥ�� ���� ����(true: ����, false: ����)
		//chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAcceptAllFileFilterUsed(true);
		
		// Dialogâ�� ��Ÿ�� �⺻ ��� �����ϱ�
		chooser.setCurrentDirectory(new File("C:\\Users\\"));
		int result = -1;
		if(choice==DialogChoice.OPEN){
			
			// ���� â
			result = chooser.showOpenDialog(new Panel());//�θ�â
		}else if(choice==DialogChoice.SAVE){
			// ���� â
			result = chooser.showSaveDialog(new Panel());
		}
		// '���� â' �Ǵ� '���� â'���� ������ ���� ���� ��������
		
		
		File selectedFile = null;
		// '����' �Ǵ� '����'��ư�� ������ ��쿡��...
		if(result == JFileChooser.APPROVE_OPTION){ 
			// ������ ���� ���� ���ϱ�
			selectedFile = chooser.getSelectedFile();
			//System.out.println("���� ���� : " + selectedFile.getAbsolutePath());
		}
		return selectedFile;
	}
	
	public static void main(String[] args) {
       new TcpFileServer().serverStart();
		
		
	}

}