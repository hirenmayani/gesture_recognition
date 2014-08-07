package gesture_recognization;
/*
 * auother: dhruvin mehta
 * detect face
 * external sources required xml file of configuration
 * */ 
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.*;
import org.opencv.objdetect.CascadeClassifier;
 
public class FaceDetection {
     
	public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning FaceDetection");
        new FaceDetection().faceDetector();
    }
     
    //Face Detection Method
    void faceDetector() {
    	VideoCapture camera = new VideoCapture(0); //Opens the camera. 0 for in built camera
        Mat image = new Mat(); //creates matrix for image
        
        //Checks whether camera is opened or not
        if(camera.isOpened()) {
        	//Captures the image from camera
        	if(camera.read(image)) {
        		//Class for detecting face. Provided by OpenCV
        		CascadeClassifier FaceDetection = new CascadeClassifier("other/lbpcascade_frontalface.xml");
 
        		MatOfRect faceDetections = new MatOfRect();
        		
        		//This Method detects faces
        		FaceDetection.detectMultiScale(image, faceDetections);
 
        		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        		//Draw a green rectangle around face
        		for (Rect rect : faceDetections.toArray()) {
        			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        		}
 
        		String filename = "ouput.png";// give path of destination image
        		System.out.println(String.format("Writing %s", filename));
        		Highgui.imwrite(filename, image);
        	}
        }
    }
}