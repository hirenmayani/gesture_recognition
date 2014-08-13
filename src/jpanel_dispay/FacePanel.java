package jpanel_dispay;
/*
 * Author: Dhruvin Mehta
 * Creates Jframe and initialize it
 * */
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
		g.drawString("Frame no " + (count++),50,50);
		
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
	
    public void display(BufferedImage image)
    {
    	
		face.setFace(image);
		face.repaint();
    }
}

