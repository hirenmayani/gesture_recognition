package gesture.detection.objectDetection;

/*
 * Author: Harit Maniyar
 * Module Name: EyeDetection
 * External Sources required are XML file 
 * modified and reviewed by: hiren mayani 
 * to make it geniric 
 */

import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import gesture.detection.configuration.ConfigPropReader;

public class EyeDetection {
	public static Rect rect;
	
	public ArrayList<Rect[]> eyeDetector(Mat currentImage) {
		FaceDetection faceDetector = new FaceDetection();
		MatOfRect detectedFaces = faceDetector.faceDetector(currentImage);
		Rect[] facesArray = detectedFaces.toArray();
		ArrayList<Rect[]> allEyes = new ArrayList<Rect[]>();

		CascadeClassifier eye_cascade = new CascadeClassifier(ConfigPropReader.getInstance().getPropValues("eye_trainer"));
		MatOfRect eyedetections = new MatOfRect();

		for (int i = 0; i < facesArray.length; i++) {
			Mat faceROI = currentImage.submat(facesArray[i]);

			// -- In each face, detect eyes
			eye_cascade.detectMultiScale(faceROI, eyedetections);
			Rect[] eyesArray = eyedetections.toArray();
			allEyes.add(eyesArray);

			/*
			 * for (int j = 0; j < eyesArray.length; j++) { Point center1 = new
			 * Point(facesArray[i].x + eyesArray[j].x + eyesArray[j].width *
			 * 0.5, facesArray[i].y + eyesArray[j].y + eyesArray[j].height *
			 * 0.5); int radius = (int) Math .round((eyesArray[j].width +
			 * eyesArray[j].height) * 0.25); Core.circle(currentImage, center1,
			 * radius, new Scalar(255, 0, 0), 4, 8, 0);
			 * 
			 * if (blink == 0) { count++; if (count % 3 == 0)
			 * System.out.println("3 Blinks Detected"); } blink = 1; }
			 * 
			 * if (eyedetections.empty() && blink == 1) { blink = 0; }
			 */
		}
		return allEyes;
	}

	public MatOfRect largestEyeDetector(Mat currentImage) {
		int width = 0;
		FaceDetection faceDetector = new FaceDetection();
		MatOfRect detectedFaces = faceDetector.faceDetector(currentImage);
		rect = new Rect();

		for (Rect rect1 : detectedFaces.toArray()) {
			if (rect1.width > width)
				rect = rect1;
		}

		CascadeClassifier eye_cascade = new CascadeClassifier(
				"other/haarcascade_eye_tree_eyeglasses.xml");
		MatOfRect eyedetections = new MatOfRect();

		Mat faceROI = currentImage.submat(rect);

		// -- In each face, detect eyes
		eye_cascade.detectMultiScale(faceROI, eyedetections);
		return eyedetections;
	}
}