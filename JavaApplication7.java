package javaapplication7;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
class demo extends JPanel{
    private int maxWidth,maxHeight;
    private int fanWidth=260,fanHeight=250;
    private int x=10,y=100;
    private int centerX,centerY;
    int angle=0;
    public int speedDelay;
    public int direction=1; //1 for clockwise 0 for anti clock wise
    Timer timer;
    public demo(int w,int h){
        speedDelay=80; 
        timer = new Timer(speedDelay,((e)->{
            repaint();
        }));
        timer.start();
        
    }
    public void setSpeed(int speed){
        if(speed<=10){
            speed=10;
        }
        timer.setDelay(speed);    
        speedDelay=speed;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;  
        g2d.setColor(Color.CYAN);
        int xPoints[]={centerX-(fanWidth/3)+140,centerX+130,centerX+(fanWidth/3)+120};
        int yPoints[]={getHeight(),centerY+125,getHeight()};
        g2d.fillPolygon(xPoints, yPoints,3);
        
        g2d.setColor(Color.yellow);
        centerX=getWidth()/2-fanWidth/2;
        centerY=getHeight()/2-fanHeight/2;
        g2d.fillArc(centerX,centerY,fanWidth,fanHeight, angle,30);
        g2d.fillArc(centerX,centerY,fanWidth,fanHeight, angle+120,30);
        g2d.fillArc(centerX,centerY,fanWidth,fanHeight,angle+240,30);
        if(direction==1){
            angle=(angle+30)%360;
        }else{
             angle=(angle-30)%360;
        }
    }
}
public class JavaApplication7 extends JFrame implements ActionListener{
    int width=600,height=600;
    Button cw=new Button("clock Wise");
    Button acw=new Button("AntiClock wise");
    Button increSpeed=new Button("Increase Speed");
    Button decreSpeed=new Button("Decrease speed");
    demo panel;
    JavaApplication7(){
        setSize(width,height);
        setTitle("fan animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         panel = new demo(width,height);
        panel.setBackground(Color.BLACK);
        
        cw.addActionListener(new ActionListener() {
          @Override
             public void actionPerformed(ActionEvent e) {
                panel.direction=1; 
        }
        });


        
        acw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.direction=0;
            }
        });
        increSpeed.addActionListener(this);
        decreSpeed.addActionListener(this);
//        cw.setBounds(50,100,200,100);
        panel.add(cw);
//        acw.setBounds(400,100,200,100);
        panel.add(acw);
        panel.add(increSpeed);panel.add(decreSpeed);
        
        add(panel);
    }
    public static void main(String[] args) {
        new JavaApplication7().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Increase Speed") {
            int x = panel.speedDelay - 10;
            panel.setSpeed(x); 
            
            System.out.println("speed increased to "+(panel.speedDelay));
        } else if (e.getSource() == decreSpeed) {
            int x = panel.speedDelay + 10;
            panel.setSpeed(x); 
              System.out.println("speed increased to "+(panel.speedDelay));
        }
    }
}
