package gesture.event.gestureEventsListener;

import gesture.event.gestureEvents.EyeEvent;

/*
 * Author: Hiren Mayani
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
