package gesture.testing.subscriber;

/** @author hirenmayani.com
 * this class is not part of API.
 * this is created for testing purpose.
 * inner class is used here.
 *  */

import gesture.event.eventSource.Gesture;
import gesture.event.gestureEvents.EyeEvent;
import gesture.event.gestureEvents.FaceEvent;
import gesture.event.gestureEventsListener.EyeListener;
import gesture.event.gestureEventsListener.FaceListener;

public class Subscriber_console {
	public static void main(String[] args) {
		Gesture gesture=new Gesture();
		FaceListener facelistener = new MyFaceListener();
		EyeListener eyelistener = new MyEyeListener();
		gesture.addFaceListener(facelistener);
		gesture.addEyeListener(eyelistener);
		
		gesture.startDetection();
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
}