package image_converter;
/*
 * Author: Dhruvin Mehta
 * Convert Mat to BufferedImage
 * */ 
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Convert {
	public BufferedImage matToBuff(Mat image)
    {	
		int type = BufferedImage.TYPE_BYTE_GRAY;
	   	if ( image.channels() > 1 ) 
	   	{
	   		Mat m2 = new Mat();
	   		Imgproc.cvtColor(image,m2,Imgproc.COLOR_BGR2RGB);
    		type = BufferedImage.TYPE_3BYTE_BGR;
    		image = m2;
	    }
	   	byte [] b = new byte[image.channels()*image.cols()*image.rows()];
	   	image.get(0,0,b); // get all the pixels
	   	BufferedImage buffimage = new BufferedImage(image.cols(),image.rows(), type);
	   	buffimage.getRaster().setDataElements(0, 0, image.cols(),image.rows(), b);
    	return buffimage;
    }
}
