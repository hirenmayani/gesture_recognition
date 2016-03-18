package gesture.testing.jpanel_dispay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Face_Panel extends JPanel
{
	JFrame frame;
	Face_Panel face;
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	int count = 0;
	public double width ;
	public double height;
	
	public Face_Panel()
	{
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
	}
	
	
	public void setFace(BufferedImage img)
	{
		image = img;
//		totalEyesDetected=InitConfig.getDetectedEyes().size();
//		totalFacesDetected=InitConfig.getDetectedFaces().toArray().length;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(this.image == null)
		{
			return;
		}
		g.drawImage(this.image , 0	 ,0 , this.image.getWidth() , this.image.getHeight() , null);
		//g.setColor(Color.WHITE);
		//g.drawString("Frame number : " + (count++) ,10 , 20);
		//g.drawLine(0, 525, 680, 525);
	}
	
	public void initialize()
    {
    	frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize((int)width,(int) height);
    	face = new Face_Panel();
    	frame.setContentPane(face);
    	frame.setVisible(true);
    }
	
    public void display(BufferedImage image)
    {
		face.setFace(image);
		face.repaint();
    }
}