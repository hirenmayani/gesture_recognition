package gesture.testing.subscriber;

import gesture.event.eventSource.Face;
import gesture.event.gestureEvents.FaceEvent;
import gesture.event.gestureEventsListener.FaceListener;

public class Subscriber_console2 implements FaceListener{

	public static void main(String[] args)
	{
	}
	
	public void init()
	{
		Face face=new Face();
		face.addFaceListener(this);
	}

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
	public void headDownMove(FaceEvent fe) {
		// TODO Auto-generated method stub
		System.out.println("head down move");
	}

	@Override
	public void headUpMove(FaceEvent fe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verticleNod(FaceEvent fe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void horizontalNod(FaceEvent fe) {
		// TODO Auto-generated method stub
		
	}	
}
