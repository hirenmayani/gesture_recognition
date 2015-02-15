/*
 * Author: Hiren Mayani
 * This class contains various eye gesture detection methods.
 */

package gesture.detection.gestureDetection;

import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public class EyeGestureDetection {
	private int leftFrame, rightFrame;
	private int count2 = 0, count3 = 0, count4 = 0; // counts no of blinks
	private int iframe; // initial frame no when eye is closed
	private boolean open2 = true, open3 = true, open4 = true;
	private int twoFrame, threeFrame, fourFrame;
	private boolean flag = true, leftFlag = true, rightFlag = true; // just to
																	// check
																	// that
																	// closedEye
																	// method is
	// called
	// for first time
	// FaceGestureDetection fgd = new FaceGestureDetection();
	private MatOfRect eyes;
	private Rect face;
	private static int frame = 0;

	// private int numOfBlink;

	public void feedData(Rect face, MatOfRect detectedEyes) {
		this.face = face;
		this.eyes = detectedEyes;
		frame++;
		/*
		 * if (fgd.faces.toArray().length > 0) System.out.println(fgd.frame +
		 * " " + fgd.faces.toArray()[0].x + " " + fgd.faces.toArray()[0].x);
		 */
	}

	/*
	 * public boolean eyeBlink(int n) // eyes = detected eyes // n = no of
	 * blinks to detect e.g. 2, 3 , 4 { // if eyes are are not detected then
	 * they are closed if (eyes.empty()) { // set open flag to false so eyes are
	 * closed if (open) open = false; } // if eyes are detected and they are
	 * closed else if (!open) { count++; // increment blink count if (count ==
	 * n) // n blinks detected or not { // reset variables and return true open
	 * = true; count = 0; return true; } open = true; // if n blinks are not
	 * detected then eyes are open } return false; }
	 */

	//
	
	// checks left eye is closed or not
	public boolean eyeLeftClosed() {
		// number of eyes detected
		Rect[] eyesArray = eyes.toArray();

		for (int j = 0; j < eyesArray.length; j++) {
			// center of the eye
			Point center1 = new Point(face.x + eyesArray[j].x
					+ eyesArray[j].width * 0.5, face.y + eyesArray[j].y
					+ eyesArray[j].height * 0.5);

			// checking for right eye open
			if (center1.x < FaceGestureDetection.intial_x_point
					&& eyesArray.length == 1) {
				if (leftFlag) {
					leftFrame = frame;
					leftFlag = false;
				} else {
					if ((frame - leftFrame) > 15) {
						leftFlag = true;
						return true;
					}
				}
			} else {
				leftFlag = true;
			}
		}
		return false;
	}

	// checks left eye is closed or not
	public boolean eyeRightClosed() {
		// number of eyes detected
		Rect[] eyesArray = eyes.toArray();

		for (int j = 0; j < eyesArray.length; j++) {
			// center of the eye
			Point center1 = new Point(face.x + eyesArray[j].x
					+ eyesArray[j].width * 0.5, face.y + eyesArray[j].y
					+ eyesArray[j].height * 0.5);

			// checking for left eye open
			if (center1.x > FaceGestureDetection.intial_x_point
					&& eyesArray.length == 1) {
				if (rightFlag) {
					rightFrame = frame;
					rightFlag = false;
				} else {
					if ((frame - rightFrame) > 15) {
						rightFlag = true;
						return true;
					}
				}
			} else {
				rightFlag = true;
			}
		}
		return false;
	}

	public boolean eyeTwoBlink() {
		if (eyes.empty()) {
			// set open flag to false so eyes are closed
			if (open2)
				open2 = false;
		}
		// if eyes are detected and they are closed
		else if (!open2) {
			if (count2 == 0)
				twoFrame = frame;
			count2++; // increment blink count
			if (count2 == 2) // n blinks detected or not
			{
				// reset variables and return true
				open2 = true;
				count2 = 0;
				if (!eyeSleep() && (frame - twoFrame) < 30 && eyes.toArray().length == 2) {
					count3 = 0;
					count4 = 0;
					return true;
				}
			}
			open2 = true; // if n blinks are not detected then eyes are open
		}
		return false;
	}

	public boolean eyeThreeBlink() {
		if (eyes.empty()) {
			// set open flag to false so eyes are closed
			if (open3)
				open3 = false;
		}
		// if eyes are detected and they are closed
		else if (!open3) {
			if (count3 == 0)
				threeFrame = frame;
			count3++; // increment blink count
			if (count3 == 3) // n blinks detected or not
			{
				// reset variables and return true
				open3 = true;
				count3 = 0;
				if (!eyeSleep() && (frame - threeFrame) < 30 && eyes.toArray().length == 2) {
					count2 = 0;
					count4 = 0;
					return true;
				}
			}
			open3 = true; // if n blinks are not detected then eyes are open
		}
		return false;
	}

	public boolean eyeFourBlink() {
		if (eyes.empty()) {
			// set open flag to false so eyes are closed
			if (open4)
				open4 = false;
		}
		// if eyes are detected and they are closed
		else if (!open4) {
			if (count4 == 0)
				fourFrame = frame;
			count4++; // increment blink count
			if (count4 == 4) // n blinks detected or not
			{
				// reset variables and return true
				open4 = true;
				count4 = 0;
				if (!eyeSleep() && (frame - fourFrame) < 50 && eyes.toArray().length == 2) {
					count2 = 0;
					count3 = 0;
					return true;
				}
			}
			open4 = true; // if n blinks are not detected then eyes are open
		}
		return false;
	}

	public boolean eyeSleep() {
		if (eyes.empty()) // if eyes are not detected they are closed
		{
			if (flag) // if it is called first time
			{
				flag = false;
				iframe = frame; // set the initial frame no
			} else {
				// if difference of current frame and intial frame > 25 then eye
				// is closed
				if ((frame - iframe) > 15) {
					// reset the variables and return true
					flag = true;
					return true;
				}
			}
		} else // if eyes are detected so they are open
		{
			iframe = frame; // reset the iframe
			flag = true; // reset flag
		}
		return false;
	}
}