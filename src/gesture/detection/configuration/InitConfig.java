/*
 * Author: Hiren Mayani
 * this class is controller to detect gesture.
 * all methods are call from here and if gesture detects then fire corresponding event. */

package gesture.detection.configuration;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.VideoCapture;

import gesture.detection.enumPojo.EyeGestureType;
import gesture.detection.enumPojo.FaceGestureType;
import gesture.detection.gestureDetection.EyeGestureDetection;
import gesture.detection.gestureDetection.FaceGestureDetection;
import gesture.detection.objectDetection.EyeDetection;
import gesture.detection.objectDetection.FaceDetection;
import gesture.event.eventSource.Eye;
import gesture.event.eventSource.Face;

public class InitConfig {
	private static InitConfig initConfig = null;
	private static Object mutex = new Object();

	protected InitConfig() {
	}

	public static InitConfig getInitConfig() {
		if (initConfig == null) {
			synchronized (mutex) {
				if (initConfig == null) {
					initConfig = new InitConfig();
				}
			}
		}
		return initConfig;
	}

	private static Mat currentImage;
	private static FaceDetection faceDetector;
	private static EyeDetection eyeDetector;
	private static MatOfRect detectedFaces;
	private static ArrayList<Rect[]> detectedEyes;
	private static MatOfRect largestEye;
	private static boolean destroyer = true;
	private static VideoCapture camera;
	private static Face face;
	private static Eye eye;
	private static FaceGestureDetection faceGestureDetector;
	private static EyeGestureDetection eyeGestureDetector;
	private static boolean isFaceRunning = false;
	private static boolean isEyeRunning = false;

	public void init(Face face1, Eye eye1) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		camera = new VideoCapture(0); // Opens the camera. 0 for in built camera
		// TODO read 0 from config file
		currentImage = new Mat();

		if (face != null) {
			face = face1;
			isFaceRunning = true;
			faceGestureDetector = new FaceGestureDetection();
		}
		faceDetector = new FaceDetection();// this will also required in eye

		if (eye != null) {
			eye = eye1;
			isEyeRunning = true;
			eyeDetector = new EyeDetection();
			eyeGestureDetector = new EyeGestureDetection();
		}

		if (camera.isOpened()) // Checks whether camera is opened or not
		{
			while (destroyer) {
				if (camera.read(currentImage)) // Captures the image from camera
				{
					detectedFaces = faceDetector.faceDetector(currentImage);
					if (isFaceRunning) {
						detectFaceGesture();
					}
					if (isEyeRunning) {
						detectedEyes = eyeDetector.eyeDetector(currentImage);
						largestEye = eyeDetector
								.largestEyeDetector(currentImage);
						detectEyeGesture();
					}
				}
			}
			System.out.println("destroyed...");
		}
	}

	public void stop() {
		destroyer = false;
		// System.out.println("check destroyed");
	}

	public MatOfRect getDetectedFaces() {
		return detectedFaces;
	}

	public ArrayList<Rect[]> getDetectedEyes() {
		return detectedEyes;
	}

	private void detectFaceGesture() {
		// TODO Auto-generated method stub

		faceGestureDetector.feedData(detectedFaces);
		faceGestureDetector.calculateMidPoint();
		ArrayList<FaceGestureType> eventToFire = new ArrayList<FaceGestureType>();

		if (faceGestureDetector.headRightMove()) {
			eventToFire.add(FaceGestureType.FACE_RIGHT_MOVE);
		}
		if (faceGestureDetector.headLeftMove()) {
			eventToFire.add(FaceGestureType.FACE_LEFT_MOVE);
		}
		if (faceGestureDetector.headUpMove()) {
			eventToFire.add(FaceGestureType.FACE_UP_MOVE);
		}
		if (faceGestureDetector.headDownMove()) {
			eventToFire.add(FaceGestureType.FACE_DOWN_MOVE);
		}

		faceGestureDetector.feedData(detectedFaces);
		faceGestureDetector.calculateMidPoint();
		if (faceGestureDetector.horizontalNod()) {
			eventToFire.add(FaceGestureType.FACE_HORIZONTAL_NOD);
		}
		if (faceGestureDetector.verticalNod()) {
			eventToFire.add(FaceGestureType.FACEC_VERTICLE_NOD);
		}
		face.fireevent(eventToFire);
		eventToFire.clear();
	}

	private static void detectEyeGesture() {

		eyeGestureDetector.feedData(EyeDetection.rect, largestEye);
		ArrayList<EyeGestureType> eventToFire = new ArrayList<EyeGestureType>();

		if (eyeGestureDetector.eyeFourBlink()) {
			eventToFire.add(EyeGestureType.EYE_FOUR_BLINK);
		}
		if (eyeGestureDetector.eyeThreeBlink()) {
			eventToFire.add(EyeGestureType.EYE_THREE_BLINK);
		}
		if (eyeGestureDetector.eyeTwoBlink()) {
			eventToFire.add(EyeGestureType.EYE_TWO_BLINK);
		}

		if (eyeGestureDetector.eyeLeftClosed()) {
			eventToFire.add(EyeGestureType.EYE_LEFT_CLOSED);
		}
		if (eyeGestureDetector.eyeRightClosed()) {
			eventToFire.add(EyeGestureType.EYE_RIGHT_CLOSED);
		}

		if (eyeGestureDetector.eyeSleep()) {
			eventToFire.add(EyeGestureType.EYE_SLEEP);
		}
		eye.fireevent(eventToFire);

	}

	public Mat getCurrentImage() {
		return currentImage;
	}

	public Mat getCurrentDetectedFaces() {
		return detectedFaces;
	}

	public ArrayList<Rect[]> getCurrentDetectedEyes() {
		return detectedEyes;
	}
}