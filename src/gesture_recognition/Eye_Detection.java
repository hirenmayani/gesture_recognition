package gesture_recognition	;

/*
 * Author: Harit Maniyar
 * Module Name: EyeDetection
 * External Sources required are XML file 
 */


import org.opencv.objdetect.*;
import org.opencv.highgui.*;
import org.opencv.core.*;

public class Eye_Detection
{

	
	public static void main(String[] args)
	{ 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning EyeDetection");
		Eye_Detection.eyeDetector();
	}
	
	//Eye Detection Method
	
	public static void eyeDetector()
	{
		// XML Files needed for Detection
		
		CascadeClassifier face_cascade = new CascadeClassifier("other/lbpcascade_frontalface.xml");
		CascadeClassifier eye_cascade= new CascadeClassifier("other/haarcascade_eye_tree_eyeglasses.xml");
		
		VideoCapture camera = new VideoCapture(0);
		
		Mat image=new Mat();
		
		
		MatOfRect facedetections=new MatOfRect();
		MatOfRect eyedetections=new MatOfRect();
		
		//Face Detection
		if(camera.isOpened())
		{
			if(camera.read(image))
			{
				face_cascade.detectMultiScale(image, facedetections);
				System.out.println("Face Detected");
			}
		}
	    
		Rect[] facesArray = facedetections.toArray();
		
		  
		for (Rect rect : facedetections.toArray())
	    {
	      	 Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
	    }
		
		for (int i = 0; i < facesArray.length; i++)
	    {     
	         Mat faceROI = image.submat(facesArray[i]);

	         //-- In each face, detect eyes
	    
	         eye_cascade.detectMultiScale(faceROI, eyedetections);

	         Rect[] eyesArray = eyedetections.toArray();
	         
	         System.out.println("Eyes Detected:" + eyesArray.length);
	         
	         for (int j = 0; j < eyesArray.length; j++)
	         {
	        	System.out.println("for loop");

	            Point center1 = new Point(facesArray[i].x + eyesArray[i].x + eyesArray[i].width * 0.5, facesArray[i].y + eyesArray[i].y + eyesArray[i].height * 0.5);
	            int radius = (int) Math.round((eyesArray[i].width + eyesArray[i].height) * 0.25);
	            Core.circle(image, center1, radius, new Scalar(255, 0, 0), 4, 8, 0); 
	         }
	       }
	    
		   //-- Show what you got
		   String window_name="op.png";
	       Highgui.imwrite(window_name, image);
	    
	}
}

