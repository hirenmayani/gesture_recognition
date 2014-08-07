package gesture_recognition;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
 
public class Test {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m  = Mat.eye(7, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());
        
    }
}