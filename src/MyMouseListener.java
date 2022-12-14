import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ColorModel;

import javax.swing.JPanel;

public class MyMouseListener implements MouseListener, MouseMotionListener{
		String a;
		
		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(Color.white);			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());				
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			
			p.setBackground(color.darker());			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			JPanel p = (JPanel)e.getSource();
			Color color = p.getBackground();
			p.setBackground(Color.white);		
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
