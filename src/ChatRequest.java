import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

class ChatRequest extends JDialog {
      
      public ChatRequest(String id) {
         setSize(150,80);
         
           Container con = getContentPane();
           con.setLayout(new GridLayout(2,1));
           
           int qut = JOptionPane.showConfirmDialog(con, "1��1 ä���� �����Ͻðڽ��ϱ�?","1��1 ä�� ����",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null);
           
         //TODO �ڽ� ���̾ƿ� �̺�Ʈ ���� �ǽ�      
         ActionListener action = new ActionListener(){         
            @Override
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub   
               if (e.getActionCommand().equals("�˾�â ȣ��")) { //TODO �˾�â ȣ�� ��ư Ŭ���̺�Ʈ ó��   
                  /** TODO [�޽��� �Է� �˸�â]
                  String data = new JOptionPane().showInputDialog("�޽����� �Է����ּ���");
                  txt.setText(data);
                  */
                  
                  /** TODO [���� �˸�â]*/ 
                  if(qut == 0) {
                     new ChatRoomView(id);
                  }
                  else if(qut == 1) {
                     dispose();
                  }
                  
                  //goekd ��ư�� ����� open_btn.addActionListener(action);

            
               }         
            }
         };
         open_btn.addActionListener(action);
           
           
         setVisible(true);
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }
   }