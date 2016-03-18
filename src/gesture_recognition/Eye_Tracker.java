package gesture_recognition;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
/** @author hirenmayani.com
 * Module Name: Eye_Tracker
 *  
 */
public class Eye_Tracker 
{
	public void tracker(Mat eye)
	{
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		List<MatOfPoint> contours;
		contours = new ArrayList<MatOfPoint>(200); 
		double area;
		Mat src = new Mat();
		src = eye;
		Mat threshold = new Mat(eye.rows(),eye.cols(),eye.type());
		
		contours = new ArrayList<MatOfPoint>(200); 
        
        Core.bitwise_not(src , src);	
		Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
		
		threshold = src;
	       Imgproc.threshold(src,threshold,220,250,Imgproc.THRESH_BINARY);
		
	       Imgproc.findContours(src, contours, new Mat(), Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_NONE);
	       Imgproc.drawContours(src, contours, -1, new Scalar(255,255,255), -1);
		
		for (int i = 0; i < contours.size(); i++)
		{
		    area = Imgproc.contourArea(contours.get(i));    // Blob area
		    
		    //Bounding box
		    Rect rect = Imgproc.boundingRect(contours.get(i)); 
		    
		    // Approximate radius
		    int radius = rect.width/2;

		    // Look for round shaped blob
		    if (area >= 30 && 
		        Math.abs(1 - ((double)rect.width / (double)rect.height)) <= 0.2 &&
		        Math.abs(1 - (area / (Math.PI * Math.pow(radius, 2)))) <= 0.2)    
		    {
		        Core.circle(eye, new Point(rect.x + radius, rect.y + radius), radius, new Scalar(255,0,0), 2);
		    }
		}
	}
}
