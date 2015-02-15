/* Author : Dhruvin Mehta
 * Contains method to perform Histogram Equilization
 */

package gesture.detection.imageProcessing;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Histogram_equilization {
	public Mat histo_equ(Mat image) {
		Mat equilized_image = new Mat();
		Imgproc.cvtColor(image, equilized_image, Imgproc.COLOR_RGB2YCrCb);
		List<Mat> channels = new ArrayList<Mat>();
		Core.split(equilized_image, channels);
		Imgproc.equalizeHist(channels.get(0), channels.get(0));
		Core.merge(channels, equilized_image);
		Imgproc.cvtColor(equilized_image, equilized_image, Imgproc.COLOR_YCrCb2RGB);
		return equilized_image;

	}
}
