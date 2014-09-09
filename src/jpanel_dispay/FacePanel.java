package jpanel_dispay;

import gesture_recognition.FaceDetection;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FacePanel extends JPanel
{
	JFrame frame;
	FacePanel face;
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	int count = 0;
	private static int x1 ,y1;
	private static int eyecenter , eyecenter1;
	
	public FacePanel()
	{
		super();
	}
	
	public void setFace(BufferedImage img)
	{
		image = img;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(this.image == null)
		{
			return;
		}
		g.drawImage(this.image , 10 ,10 , this.image.getWidth() , this.image.getHeight() , null);
		//g.setColor(Color.WHITE);
		g.drawString("Frame no " + (count++) + " Face detected at "+ FacePanel.getX1() + " "+ FacePanel.getY1(), 50 , 50);
		g.drawString("Eye detected at " + x1 +" "+ y1 ,50,100);
	}
	
	public void initialize()
    {
    	frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500, 500);
    	face = new FacePanel();
    	frame.setContentPane(face);
    	frame.setVisible(true);
    }
	
    public void display(BufferedImage image , int x , int y , int eyec , int eyec1)
    {
    	setX1(x);
    	setY1(y);
    	setEyecenter(eyec);
    	setEyecenter1(eyec1);
		face.setFace(image);
		face.repaint();
    }

	public static int getY1() {
		return y1;
	}

	public static void setY1(int y1) {
		FacePanel.y1 = y1;
	}

	public static int getX1() {
		return x1;
	}

	public static void setX1(int x1) {
		FacePanel.x1 = x1;
	}
	
	public static int getEyecenter() {
		return eyecenter;
	}

	public static void setEyecenter(int y1) {
		FacePanel.eyecenter = y1;
	}
	
	public static int getEyecenter1() {
		return eyecenter1;
	}

	public static void setEyecenter1(int y1) {
		FacePanel.eyecenter1 = y1;
	}
}

