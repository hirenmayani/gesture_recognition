package gesture.event.eventSource;
/*
 * Author: Hiren Mayani
 * source of Eye event */

import gesture.detection.enumPojo.EyeGestureType;
import gesture.event.gestureEvents.EyeEvent;
import gesture.event.gestureEventsListener.EyeListener;

import java.util.ArrayList;

public class Eye {
	private ArrayList<EyeListener> eyeListenersList = new ArrayList<EyeListener>();

	public synchronized void addEyeListener(EyeListener listener) {
		if (!eyeListenersList.contains(listener)) {
			eyeListenersList.add(listener);
		}
	}

	public synchronized void removeEyeListener(EyeListener listener) {
		if (eyeListenersList.contains(listener)) {
			eyeListenersList.remove(listener);
		}
	}

	public void fireevent(ArrayList<EyeGestureType> gestureType)// temp method to fire
														// event
	{
		processEyeEvent(new EyeEvent(this), gestureType);
	}

	@SuppressWarnings("unchecked")
	private void processEyeEvent(EyeEvent eyeEvent, ArrayList<EyeGestureType> gestureType) {
		ArrayList<EyeListener> tempEyeListenerList;

		synchronized (this) {
			if (eyeListenersList.size() == 0)
				return;
			tempEyeListenerList = (ArrayList<EyeListener>) eyeListenersList
					.clone();
		}

		for (EyeListener listener : tempEyeListenerList) {

			// TODO by switch-case

			if (gestureType.contains(EyeGestureType.EYE_SLEEP)) {
				listener.eyeSleep(eyeEvent);
			}
			if (gestureType.contains(EyeGestureType.EYE_LEFT_CLOSED)) {
				listener.eyeLeftClosed(eyeEvent);
			}
			if (gestureType.contains(EyeGestureType.EYE_RIGHT_CLOSED)) {
				listener.eyeRightClosed(eyeEvent);
			}
			if (gestureType.contains(EyeGestureType.EYE_TWO_BLINK)) {
				listener.eyeTwoBlink(eyeEvent);
			}
			if (gestureType.contains(EyeGestureType.EYE_THREE_BLINK)) {
				listener.eyeThreeBlink(eyeEvent);
			}
			if (gestureType.contains(EyeGestureType.EYE_FOUR_BLINK)) {
				listener.eyeFourBlink(eyeEvent);
			}
		}
	}
}
