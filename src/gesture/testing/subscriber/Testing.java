package gesture.testing.subscriber;

import gesture.detection.configuration.InitConfig;
import gesture.detection.imageProcessing.Convert;
import gesture.event.eventSource.Gesture;
import gesture.event.gestureEvents.EyeEvent;
import gesture.event.gestureEvents.FaceEvent;
import gesture.event.gestureEventsListener.EyeListener;
import gesture.event.gestureEventsListener.FaceListener;
import gesture.testing.jpanel_dispay.Face_Panel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class Testing {
	private static Rect[] detectedFacesArray;
	private static Mat currentImage;
	private static ArrayList<Rect[]> allEyesArray;

	public static void main(String args[]) throws InterruptedException {
		Face_Panel panel = new Face_Panel();
		panel.initialize();
		BufferedImage buffimage = null;
		MatOfRect detectedFaces;
		Gesture gesture = new Gesture();
		FaceListener facelistener = new MyFaceListener();
		EyeListener eyelistener = new MyEyeListener();
		gesture.addFaceListener(facelistener);
		gesture.addEyeListener(eyelistener);
		gesture.startDetection();
		while (true) {
			currentImage = InitConfig.getInitConfig().getCurrentImage();
			allEyesArray = InitConfig.getInitConfig().getDetectedEyes();
			detectedFaces = InitConfig.getInitConfig().getDetectedFaces();
			if (detectedFaces == null) {
				detectedFacesArray = null;
			} else {
				detectedFacesArray = detectedFaces.toArray();
				drawFaces();
				if (allEyesArray != null) {
					drawEyes();
				}
			}
			while(currentImage == null)
			{
				System.out.println("sleeping");
				Thread.sleep(2000);
			}
			buffimage = new Convert().matToBuff(currentImage);
			panel.display(buffimage); // redraw new frame on panel
		}
	}

	private static class MyFaceListener implements FaceListener {

		@Override
		public void headRightMove(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("head right move");
		}

		@Override
		public void headLeftMove(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("head left move");
		}

		@Override
		public void headUpMove(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("head top move");
		}

		@Override
		public void headDownMove(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("head down move");
		}

		@Override
		public void verticleNod(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("verticle nod");
		}

		@Override
		public void horizontalNod(FaceEvent fe) {
			// TODO Auto-generated method stub
			System.out.println("horizontal nod");
		}
	}

	private static class MyEyeListener implements EyeListener {

		@Override
		public void eyeSleep(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("eye sleep");
		}

		@Override
		public void eyeLeftClosed(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("eye left closed");
		}

		@Override
		public void eyeRightClosed(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("eye right closed");
		}

		@Override
		public void eyeTwoBlink(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("two eye blink");
		}

		@Override
		public void eyeThreeBlink(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("three eye blink");
		}

		@Override
		public void eyeFourBlink(EyeEvent ee) {
			// TODO Auto-generated method stub
			System.out.println("four eye blink");
		}
	}

	static void drawFaces() {
		// Draw a green rectangle around face
		for (Rect rect : detectedFacesArray) {
			Core.rectangle(currentImage, new Point(rect.x, rect.y), new Point(
					rect.x + rect.width, rect.y + rect.height), new Scalar(0,
					255, 0));
		}
	}

	static void drawEyes() {
		// code to draw red circle around eyes
		for (int i = 0; i < allEyesArray.size(); i++) {
			Rect[] eyesArray = allEyesArray.get(i);

			for (int j = 0; j < eyesArray.length; j++) {
				Point center1 = new Point(detectedFacesArray[i].x
						+ eyesArray[j].x + eyesArray[j].width * 0.5,
						detectedFacesArray[i].y + eyesArray[j].y
								+ eyesArray[j].height * 0.5);
				int radius = (int) Math
						.round((eyesArray[j].width + eyesArray[j].height) * 0.25);
				Core.circle(currentImage, center1, radius,
						new Scalar(255, 0, 0), 4, 8, 0);

				// if (blink == 0) {
				// count++;
				// if (count % 3 == 0)
				// System.out.println("3 Blinks Detected");
				// }
				// blink = 1;
			}

			// if (eyedetections.empty() && blink == 1) {
			// blink = 0;
			// }
		}
	}
}