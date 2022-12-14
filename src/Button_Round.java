import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

//버튼 디자인
//https://leirbag.tistory.com/15

   public class Button_Round extends JButton {
	   Color back;
	   Color text;
	   
	   boolean show = true;
	   boolean dark = false;
	   
	   int R1 = 0;
	   int R2 = 0;
	   
      public Button_Round() { super(); decorate(); } 
      public Button_Round(String text) { super(text); decorate(); } 
      public Button_Round(Action action) { super(action); decorate(); } 
      public Button_Round(Icon icon) { super(icon); decorate(); } 
      public Button_Round(String text, Icon icon) { super(text, icon); decorate(); } 
      public void setColor(Color back, Color text) {
    	  this.back = back;
    	  this.text = text;
      }
      public void setShow(boolean show) {
    	  this.show = show;
      }
      public void setDark(boolean dark) {
    	  this.dark = dark;
      }
      public void setRound(int r1, int r2) {
    	  this.R1 = r1;
    	  this.R2 = r2;
      }
      protected void decorate() { setBorderPainted(false); setOpaque(false); }
      @Override 
      protected void paintComponent(Graphics g) {
         Color c,o;
         //c = new Color(255,247,242); //배경색 결정
         //o = new Color(247,99,12); //글자색 결정
         
         
         c = new Color(0x371D1E); //배경색 결정
         o = new Color(0xffffff); //글자색 결정
         c = this.back;
         o = this.text;
         
         int width = getWidth(); 
         int height = getHeight(); 
         Graphics2D graphics = (Graphics2D) g; 
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
         if (getModel().isArmed()&&show) { graphics.setColor(c.darker()); if(dark)graphics.setColor(c.brighter()); } 
         else if (getModel().isRollover()&&show) { graphics.setColor(c.brighter());if(dark)graphics.setColor(c.darker()); } 
         else { graphics.setColor(c); } 
         //graphics.fillRoundRect(0, 0, width, height, 10, 10);
         graphics.fillRoundRect(0, 0, width, height, R1, R2); 
         FontMetrics fontMetrics = graphics.getFontMetrics(); 
         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
         int textX = (width - stringBounds.width) / 2; 
         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
         graphics.setColor(o); 
         graphics.setFont(getFont()); 
         graphics.drawString(getText(), textX, textY); 
         graphics.dispose(); 
         super.paintComponent(g); 
         }
      }

