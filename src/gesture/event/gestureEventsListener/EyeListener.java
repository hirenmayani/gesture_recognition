package gesture.event.gestureEventsListener;

import gesture.event.gestureEvents.EyeEvent;

/** @author hirenmayani.com
 * listener for Eye gestures
 *to add new gesture entry is required here.
 * */
public interface EyeListener extends java.util.EventListener{
	void eyeSleep(EyeEvent ee);
	void eyeLeftClosed(EyeEvent ee);
	void eyeRightClosed(EyeEvent ee);
	void eyeTwoBlink(EyeEvent ee);
	void eyeThreeBlink(EyeEvent ee);
	void eyeFourBlink(EyeEvent ee);
}
