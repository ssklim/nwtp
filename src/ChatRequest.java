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
           
           int qut = JOptionPane.showConfirmDialog(con, "1대1 채팅을 수락하시겠습니까?","1대1 채팅 수락",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null);
           
         //TODO 자식 레이아웃 이벤트 정의 실시      
         ActionListener action = new ActionListener(){         
            @Override
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub   
               if (e.getActionCommand().equals("팝업창 호출")) { //TODO 팝업창 호출 버튼 클릭이벤트 처리   
                  /** TODO [메시지 입력 알림창]
                  String data = new JOptionPane().showInputDialog("메시지를 입력해주세요");
                  txt.setText(data);
                  */
                  
                  /** TODO [질문 알림창]*/ 
                  if(qut == 0) {
                     new ChatRoomView(id);
                  }
                  else if(qut == 1) {
                     dispose();
                  }
                  
                  //goekd 버튼에 써야함 open_btn.addActionListener(action);

            
               }         
            }
         };
         open_btn.addActionListener(action);
           
           
         setVisible(true);
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }
   }