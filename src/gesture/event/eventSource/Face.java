package gesture.event.eventSource;
/*
 * Author: Hiren Mayani
 * source of Face event */


import gesture.detection.enumPojo.FaceGestureType;
import gesture.event.gestureEvents.FaceEvent;
import gesture.event.gestureEventsListener.FaceListener;

import java.util.ArrayList;

public class Face {
	private ArrayList<FaceListener> faceListenersList = new ArrayList<FaceListener>();

	public synchronized void addFaceListener(FaceListener listener) {
		if (!faceListenersList.contains(listener)) {
			faceListenersList.add(listener);
		}
	}

	public synchronized void removeFaceListener(FaceListener listener) {
		if (faceListenersList.contains(listener)) {
			faceListenersList.remove(listener);
		}
	}

	public void fireevent(ArrayList<FaceGestureType> gestureType)// temp method to fire
														// event
	{
		processFaceEvent(new FaceEvent(this), gestureType);
	}

	@SuppressWarnings("unchecked")
	private void processFaceEvent(FaceEvent faceEvent,
			ArrayList<FaceGestureType> gestureType) {
		ArrayList<FaceListener> tempFaceListenerList;

		synchronized (this) {
			if (faceListenersList.size() == 0)
				return;
			tempFaceListenerList = (ArrayList<FaceListener>) faceListenersList
					.clone();
		}

		for (FaceListener listener : tempFaceListenerList) {
			
			if (gestureType.contains(FaceGestureType.FACE_RIGHT_MOVE)) {
				listener.headRightMove(faceEvent);
			}
			if (gestureType.contains(FaceGestureType.FACE_LEFT_MOVE)) {
				listener.headLeftMove(faceEvent);
			}
			if (gestureType.contains(FaceGestureType.FACE_UP_MOVE)) {
				listener.headUpMove(faceEvent);
			}
			if (gestureType.contains(FaceGestureType.FACE_DOWN_MOVE)) {
				listener.headDownMove(faceEvent);
			}
			if (gestureType.contains(FaceGestureType.FACE_HORIZONTAL_NOD)) {
				listener.horizontalNod(faceEvent);
			}
			if (gestureType.contains(FaceGestureType.FACEC_VERTICLE_NOD)) {
				listener.verticleNod(faceEvent);
			}
		}
	}

}