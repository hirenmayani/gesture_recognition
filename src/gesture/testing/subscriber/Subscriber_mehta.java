package gesture.testing.subscriber;


/*
 * Author: Dhruvin Mehta
 * Detects face
 * External sources required XML file of configuration
 * */

import gesture.detection.gestureDetection.FaceGestureDetection;
import gesture.detection.imageProcessing.Convert;
import gesture.testing.jpanel_dispay.Face_Panel;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

public class Subscriber_mehta {

	    Face_Panel panel = new Face_Panel();

	    public static void main(String[] args) {
	        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	        new Subscriber_mehta().faceDetector();
	        // new Head_gestures().headGesture();
	    }

	    // Face Detection Method
	    public void faceDetector() {
	        panel.initialize();
	        FaceGestureDetection head = new FaceGestureDetection();
	        Mat image = new Mat();
	        BufferedImage buffimage = null;
	        VideoCapture camera = new VideoCapture(0); // Opens the camera. 0 for in
	                                                    // built camera

	        if (camera.isOpened()) // Checks whether camera is opened or not
	        {
	            while (true) {
	                if (camera.read(image)) // Captures the image from camera
	                {
	                    // image = new Image_enhancement().histo_equ(image);
	                    CascadeClassifier FaceDetection = new CascadeClassifier(
	                            "other/lbpcascade_frontalface.xml");
	                    MatOfRect
	                    faceDetections = new MatOfRect();

	                    FaceDetection.detectMultiScale(image, faceDetections);

	                    for (Rect rect : faceDetections.toArray()) {
	                        Core.rectangle(image, new Point(rect.x, rect.y),
	                                new Point(rect.x + rect.width, rect.y
	                                        + rect.height), new Scalar(0, 255, 0));
	                    }
	             
						head.feedData(faceDetections);
	                    boolean ans = head.headDownMove();
	                    if(ans)
	                        System.out.println("DOWN");
	                    boolean ans1 = head.headRightMove();
	                    if(ans1)
	                        System.out.println("Right");
	                    boolean ans2 = head.headUpMove();
	                    if(ans2)
	                        System.out.println("Up");
	                    
	                    ans = head.headLeftMove();
	                    if(ans)
	                        System.out.println("LEFT");
	                }

	                buffimage = new Convert().matToBuff(image);
	                panel.display(buffimage);
	            }
	        }
	    }
	}
