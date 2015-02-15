package gesture.event.gestureEventsListener;
/*
 * Author: Hiren Mayani
 * listener for face gestures
 *to add new gesture entry is required here.
 * */


import gesture.event.gestureEvents.FaceEvent;

public interface FaceListener extends java.util.EventListener {
	void headRightMove(FaceEvent fe);
	void headLeftMove(FaceEvent fe);
	void headUpMove(FaceEvent fe);
	void headDownMove(FaceEvent fe);
	void verticleNod(FaceEvent fe);
	void horizontalNod(FaceEvent fe);
}
