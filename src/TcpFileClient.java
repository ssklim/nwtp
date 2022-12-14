

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * ������ Ŭ���̾�Ʈ�� ������ �Ϸ�Ǹ�
 * Ŭ���̾�Ʈ�� ('�����ּ�')or ������ ������ ������ �����Ѵ�.
 * ������ Ŭ���̾�Ʈ�� ������ ������ �޾Ƽ� 'C:\Users\�Ӽ���\Desktop\new' ������
 * ���� �̸����� ����ǵ��� �Ѵ�.
 * 
 */

public class TcpFileClient {
	enum DialogChoice { OPEN, SAVE } 
	
	private void clientStart(){
		//������ �о Socket���� ����: ����
		
		//File file = new File("�����ּ�");
		File file =showDialog(DialogChoice.OPEN);
		
		if(file == null){
			System.out.println("������ ������ �������� �ʾҽ��ϴ�.");
			System.out.println("�۾��� �ߴ��մϴ�...");
			return;
		}
		
		String fileName = file.getName();
		if(!file.exists()){
			System.out.println(fileName + "������ �����ϴ�.");
			System.out.println("�۾��� �ߴ� �մϴ�...");
			return;
		}
		
		try {
			Socket socket = new Socket("localhost" , 7777);//ip�ּ�, ��Ʈ��ȣ
			System.out.println("���� ���� ����...");
			
			
			//Socket�� OutputStream ��ü ����
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedOutputStream bos = new BufferedOutputStream(dos);
			
			// ������ �����ϸ� ù��°�� ������ ������ ���ϸ��� �����Ѵ�.
			dos.writeUTF(fileName);
			
			
			//���� �Է¿� InputStream ��ü ����
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file)
			);
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			// ���� ������ �о�� �������� �����ϱ�
			while((length = bis.read(temp)) > 0){
				//�о�� ������ ������ 0������ ������ ���
				bos.write(temp, 0, length);
			}
			bos.flush();//���� ���ۿ� ����Ǿ� �ִ� ������ Ŭ���̾�Ʈ�� �����ϰ� ���۸� ����.

			System.out.println("���� ���� �Ϸ�..");
			
			//��Ʈ���� ���� �ݱ�
			bis.close();
			bos.close();
			socket.close();
			
			
		} catch (Exception e) {
			System.out.println("���� ���� ���� : " + e.getMessage());
		}
		
	}
	

	public File showDialog(DialogChoice choice){
		// SWING�� ���� ����, ���� â ����
		JFileChooser chooser = new JFileChooser();
		
		// ������ ������ Ȯ���� ����
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
       new TcpFileClient().clientStart();
		
	}

}