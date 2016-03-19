package gesture.detection.objectDetection;

/*
 * Author: Dhruvin Mehta
 * Detects face
 * External sources required XML file of configuration
 * */

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import gesture.detection.configuration.ConfigPropReader;

public class FaceDetection {
	int c = 0;

	// Face Detection Method
	public MatOfRect faceDetector(Mat currentImage) {

		CascadeClassifier FaceDetection = new CascadeClassifier(
				ConfigPropReader.getInstance().getPropValues("face_trainer"));
		MatOfRect faceDetections = new MatOfRect();

		// This Method detects faces
		FaceDetection.detectMultiScale(currentImage, faceDetections);

		Rect[] faces = faceDetections.toArray();

		if (ConfigPropReader.getInstance().getPropValues("multi_face_detection").equalsIgnoreCase("true")) {
			int maxIndex = 0;
			if (faces.length > 1) {
				for (int i = 1; i < faces.length; i++) {
					if ((faces[i].height + faces[i].width) > (faces[maxIndex].height + faces[maxIndex].width)) {
						maxIndex = i;
						System.out.println("FaceDetection : max index set to " + i);
					}
				}
				faceDetections = null;
				return new MatOfRect(faces[maxIndex]);
			}
		}

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