package gesture.detection.objectDetection;

/*
 * Author: Dhruvin Mehta
 * Detects face
 * External sources required XML file of configuration
 * */

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import gesture.detection.configuration.ConfigPropReader;

public class FaceDetection {
	int c = 0;

	// Face Detection Method
	public MatOfRect faceDetector(Mat currentImage) {
		CascadeClassifier FaceDetection = new CascadeClassifier(
				ConfigPropReader.getPropValues("face_trainer"));
		MatOfRect faceDetections = new MatOfRect();

		// This Method detects faces
		FaceDetection.detectMultiScale(currentImage, faceDetections);
		/*
		 * if (!faceDetections.empty())
		 * System.out.println(faceDetections.toArray()[0].x + " " +
		 * faceDetections.toArray()[0].y + " " +
		 * faceDetections.toArray()[0].height + " " +
		 * faceDetections.toArray()[0].width);
		 */
		return faceDetections;
	}
}