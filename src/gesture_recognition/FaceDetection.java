package gesture_recognition;
/*
 * Author: Dhruvin Mehta
 * Detects face
 * External sources required XML file of configuration
 * */ 

import jpanel_dispay.FacePanel;
import image_converter.Convert;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.*;
import org.opencv.objdetect.CascadeClassifier;
 
public class FaceDetection
{
	public static void main(String[] args)
	{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       	new FaceDetection().faceDetector();   
	}
     
    //Face Detection Method
    void faceDetector()
    {
    	int frameno = 0;
    	FacePanel panel = new FacePanel();
    	panel.initialize();
    	VideoCapture camera = new VideoCapture(0); //Opens the camera. 0 for in built camera
        Mat image = new Mat(); //creates matrix for image
        BufferedImage buffimage = null;
        
        //Checks whether camera is opened or not
        if(camera.isOpened())
        {
        	while(true)
            {
        		//Captures the image from camera
        		if(camera.read(image))
        		{
        			frameno++;
        			if((frameno % 5) == 0)
        			{
        				//Class for detecting face. Provided by OpenCV
        				CascadeClassifier FaceDetection = new CascadeClassifier("other/lbpcascade_frontalface.xml");
    
        				MatOfRect faceDetections = new MatOfRect();
        			
        				//This Method detects faces
        				FaceDetection.detectMultiScale(image, faceDetections);
     
        				System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
     
        				//Draw a green rectangle around face
        				for (Rect rect : faceDetections.toArray())
        				{
        					Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
        							new Scalar(0, 255, 0));
        				}
        				
        				//convert to bufferedimage
        				buffimage = new Convert().matToBuff(image);
        				
        				//display to Jpanel
        				panel.display(buffimage); 
        			}
        		}
            }
        }
    }
}