package gesture_recognition	;

/*
 * Author: Harit Maniyar
 * Module Name: EyeDetection
 * External Sources required are XML file 
 */

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.objdetect.CascadeClassifier;

public class Eye_Detection
{
	public Mat eyeDetector(Mat image , MatOfRect facedetections)
	{
		Mat face;
		Mat crop=null;
		Mat eye;
		// XML Files needed for Detection
		
		CascadeClassifier eye_cascade= new CascadeClassifier("other/haarcascade_eye_tree_eyeglasses.xml");
		
		MatOfRect eyedetections=new MatOfRect();
			    
		Rect[] facesArray = facedetections.toArray();
		
		for (int i = 0; i < facesArray.length; i++)
	    {     
	         Mat faceROI = image.submat(facesArray[i]);

	         //-- In each face, detect eyes
	         face = image.submat(facedetections.toArray()[i]);
	         //crop = face.submat(4, (2*face.width())/3, 0, face.height());
	         eye_cascade.detectMultiScale(faceROI, eyedetections);

	         Rect[] eyesArray = eyedetections.toArray();
	         
	         for (int j = 0; j < eyesArray.length; j++)
	         {
	        	//System.out.println("for loop");

	            Point center1 = new Point(facesArray[i].x + eyesArray[j].x + eyesArray[j].width * 0.5, facesArray[i].y + eyesArray[j].y + eyesArray[j].height * 0.5);
	            int radius = (int) Math.round((eyesArray[j].width + eyesArray[j].height) * 0.25);
	            Core.circle(image, center1, radius, new Scalar(255, 0, 0), 4, 8, 0); 
	            
	            //eye = crop.submat(eyedetections.toArray()[j]);
	                   
	            //new Eye_Tracker().tracker(eye);
	         }
	         if (eyedetections.empty())
	         {
	        	 System.out.println("Closed Eye");
	         }
	       }
	    return image;
	}
}