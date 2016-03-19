package gesture.testing.subscriber;

import java.awt.Dimension;
/** @author hirenmayani.com
 * not part of api, for testing
 */
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import gesture.detection.configuration.ConfigPropReader;
import gesture.detection.gestureDetection.EyeGestureDetection;

/*
 * Author: Dhruvin Mehta
 * Detects face
 * External sources required XML file of configuration
 * */

import gesture.detection.gestureDetection.FaceGestureDetection;
import gesture.detection.imageProcessing.Convert;
import gesture.detection.objectDetection.EyeDetection;
import gesture.detection.objectDetection.FaceDetection;
import gesture.detection.objectDetection.FistDetection;
import gesture.testing.jpanel_dispay.Face_Panel;

public class HeadDetect {

	Face_Panel panel = new Face_Panel();
	int fistcount = 0;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new HeadDetect().faceDetector();
		// new Head_gestures().headGesture();
	}

	// Face Detection Method
	public void faceDetector() {
		panel.initialize();
		FaceGestureDetection head = new FaceGestureDetection();
		EyeGestureDetection eye = new EyeGestureDetection();
		Mat image = new Mat();
		BufferedImage buffimage = null;
		VideoCapture camera = new VideoCapture(
				Integer.parseInt(ConfigPropReader.getInstance().getPropValues("camera_num"))); // Opens
																								// the
																								// camera.
																								// 0
																								// for
																								// in
		// built camera

		if (camera.isOpened()) // Checks whether camera is opened or not
		{
			try {
				// Move the cursor
				Robot robot = new Robot();
				camera.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, panel.width);
				camera.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, panel.height);
				while (true) {
					// Thread.sleep(100);
					if (camera.read(image)) // Captures the image from camera
					{
						// image = new Image_enhancement().histo_equ(image);
						// CascadeClassifier FaceDetection = new
						// CascadeClassifier("other/lbpcascade_frontalface.xml");
						// MatOfRect faceDetections = new MatOfRect();
						// FaceDetection.detectMultiScale(image,
						// faceDetections);

						if (ConfigPropReader.getInstance().getPropValues("face_enabled").equalsIgnoreCase("true")) {
							MatOfRect faceDetections = (new FaceDetection()).faceDetector(image);

							for (Rect rect : faceDetections.toArray()) {
								Core.rectangle(image, new Point(rect.x, rect.y),
										new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
								int mouseX = (int) (rect.x + (rect.width / 2));
								int mouseY = (int) (rect.y + (rect.height / 2));
							//robot.mouseMove(getX(mouseX), gety(mouseY));
							}
							head.feedData(faceDetections);
							head.calculateMidPoint();

							if (ConfigPropReader.getInstance().getPropValues("eye_enabled").equalsIgnoreCase("true")) {
								// Eye detection
								if (faceDetections.toArray().length > 0) {
									MatOfRect eyeRects = (new EyeDetection()).detectEyesFromFace(image,
											faceDetections.toArray()[0]);
									Rect[] eyesArray = eyeRects.toArray();
									for (int j = 0; j < eyesArray.length; j++) {
										Point center1 = new Point(
												faceDetections.toArray()[0].x + eyesArray[j].x
														+ eyesArray[j].width * 0.5,
												faceDetections.toArray()[0].y + eyesArray[j].y
														+ eyesArray[j].height * 0.5);
										int radius = (int) Math
												.round((eyesArray[j].width + eyesArray[j].height) * 0.25);
										Core.circle(image, center1, radius, new Scalar(255, 0, 0), 4, 8, 0);
									}
									eye.feedData(faceDetections.toArray()[0], eyeRects);
									boolean ans = eye.eyeSleep();
									if (ans)
										System.out.println("sleep");
									ans = eye.eyeLeftClosed();
									if (ans)
										System.out.println("left closed");
									ans = eye.eyeRightClosed();
									if (ans)
										System.out.println("right closed");
								}
							}

							
							
							boolean ans = head.headDownMove();
							if (ans)
								System.out.println("DOWN");
							boolean ans1 = head.headRightMove();
							if (ans1)
								System.out.println("Right");
							boolean ans2 = head.headUpMove();
							if (ans2)
								System.out.println("Up");

							ans = head.headLeftMove();
							if (ans)
								System.out.println("LEFT");
						}

						if (ConfigPropReader.getInstance().getPropValues("fist_enabled").equalsIgnoreCase("true")) {
							MatOfRect fistDetections = (new FistDetection()).fistDetector(image);

							if (fistDetections.toArray().length > 0) {
								if (fistcount < 4) {
									fistcount++;
								} else if (fistcount == 4) {
									System.out.println("Gist 1 sec");
									robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
							} else {
								fistcount = 0;
							}
							for (Rect rect : fistDetections.toArray()) {
								Core.rectangle(image, new Point(rect.x, rect.y),
										new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));

							}
						}

					}

					buffimage = new Convert().matToBuff(image);
					panel.display(buffimage);
				}

			} catch (Exception e) {
				System.out.println("HeadDetect Exception " + e);
			}
		}
	}

	static int getX(int x) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int eWidth = (int) (width / 4);

		int c = 4;
		return (width / 2 + c * (x - width / 2));
		// return (2 * x - (width / 2));

		// if(x > width)
		// return (width -width*x/((width/2)+(eWidth/2)));
		// else
		// return ( width*x/((width/2)+(eWidth/2)));
	}

	static int gety(int y) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int eHeight = (int) height / 2;

		int c = 4;
		return (height / 2 + c * (y - height / 2));
		// return (2 * y - (height / 2));
		// if(y>height)
		// return (height - height*y/((height/2)+(eHeight/2)));
		// else
		// return ( height*y/((height/2)+(eHeight/2)));
	}
}
