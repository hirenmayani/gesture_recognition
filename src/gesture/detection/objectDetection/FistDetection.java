package gesture.detection.objectDetection;

import java.util.ArrayList;
import java.util.List;

/** @author: hirenmayani.com
 * Detects fist
 * External sources required XML file of configuration
 * */

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import gesture.detection.configuration.ConfigPropReader;

public class FistDetection {
	int c = 0;

	// Face Detection Method
	public MatOfRect fistDetector(Mat currentImage) {

		CascadeClassifier fistDetector = new CascadeClassifier(
				ConfigPropReader.getInstance().getPropValues("fist_trainer"));
		MatOfRect fistDetections = new MatOfRect();

		// This Method detects fists
		fistDetector.detectMultiScale(currentImage, fistDetections);

		Rect[] fists = fistDetections.toArray();
		List<Rect> actualFists = new ArrayList<Rect>();

		if (ConfigPropReader.getInstance().getPropValues("multi_fist_detection").equalsIgnoreCase("true")) {
			int maxIndex = 0;
			int maxIndex1 = 0;
			String maxFists = ConfigPropReader.getInstance().getPropValues("max_fists");
			if (fists.length > 1 && (maxFists.equals("1") || maxFists.equals("2") )) {
				for (int i = 1; i < fists.length; i++) {
					if ((fists[i].height + fists[i].width) > (fists[maxIndex].height + fists[maxIndex].width)) {
						maxIndex = i;
						//System.out.println("FistDetection : max index set to " + i);
					}
				}
				actualFists.add(fists[maxIndex]);
				if (maxFists.equals("2")) {
					for (int i = 1; i < fists.length; i++) {
						if ((fists[i].height + fists[i].width) > (fists[maxIndex1].height + fists[maxIndex1].width)
								&& i != maxIndex) {
							maxIndex1 = i;
							//System.out.println("FistDetection : second max index set to " + i);
						}
					}
					actualFists.add(fists[maxIndex1]);
				}
				fistDetections = new MatOfRect();
				fistDetections.fromList(actualFists);
				return fistDetections;
			}
		}

		for (int i = 0; i < fists.length; i++) {
			if ((fists[i].height + fists[i].width) > Integer
					.parseInt(ConfigPropReader.getInstance().getPropValues("min_fist_size"))) {
				actualFists.add(fists[i]);
			}
			else
			{
				//System.out.println("ignore fist with size "+(fists[i].height + fists[i].width));
			}
		}
		
		fistDetections = new MatOfRect();
		fistDetections.fromList(actualFists);

		/*
		 * if (!faceDetections.empty())
		 * System.out.println(faceDetections.toArray()[0].x + " " +
		 * faceDetections.toArray()[0].y + " " +
		 * faceDetections.toArray()[0].height + " " +
		 * faceDetections.toArray()[0].width);
		 */
		return fistDetections;
	}
}