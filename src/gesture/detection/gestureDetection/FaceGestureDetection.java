/*
 * Author: Dhruvin Mehta
 * This class contains various face gesture detection methods.
 */

package gesture.detection.gestureDetection;

import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;

public class FaceGestureDetection {
	private int NO = 10;
	private int NO2 = 20;
	static Rect rect;

	// difference of mid points of initial and current frames
	private int difference_mid_x, difference_mid_y;

	// initial mid point of x and y axis
	static int intial_x_point, initial_y_point;

	private int horizontal_count = 1, vertical_count = 1;

	// position of current frame respect to previous frame
	private int current_frame_right = 0, current_frame_left = 0,
			current_frame_up = 0, current_frame_down = 0;
	private int current_frame_right1 = 0, current_frame_left1 = 0,
			current_frame_up1 = 0, current_frame_down1 = 0;

	// movement of face
	private boolean right = false, left = false, up = false, down = false;
	private boolean right1 = false, left1 = false, up1 = false, down1 = false;

	// horizontal or vertical movement
	private boolean vertical_node = false, horizontal_node = false;

	// flags for left right up down movement
	private boolean left_after_right = false, right_after_left = false,
			up_after_down = false, down_after_up = false;
	private boolean left_after_right1 = false, right_after_left1 = false,
			up_after_down1 = false, down_after_up1 = false;

	MatOfRect faces;
	int frame;

	public void feedData(MatOfRect detectedFaces) {
		this.faces = detectedFaces;
		this.frame++;
		/*
		 * if (this.faces.toArray().length > 0) System.out.println(this.frame +
		 * " " + this.faces.toArray()[0].x + " " + this.faces.toArray()[0].x);
		 */
	}

	public boolean horizontalNod() {
		// if horizontal movement is starting then count is 1
		// so first movement
		if (horizontal_count == 1) {
			// calculateMidPoint();
			if (difference_mid_x > -25 && difference_mid_x < 25) {
				if (right1 && left_after_right1) {
					left_after_right1 = false;
					right1 = false;
					horizontal_node = true; // horizontal movement has done
				}
			} else if (difference_mid_x > 25) {
				current_frame_right1 = frame;
				right1 = true;
				left_after_right1 = false;
			} else {
				if ((frame - current_frame_right1) < NO2)
					left_after_right1 = true;
				else
					left_after_right1 = false;
			}

			if (horizontal_node)
				horizontal_count++; // so we can determine the next movement

		} else if (horizontal_count == 2 && horizontal_node) {
			// calculateMidPoint();
			if (difference_mid_x > -25 && difference_mid_x < 25) {
				if (left1 && right_after_left1) {
					right_after_left1 = false;
					left1 = false;

					// reset variables and return true
					horizontal_node = false;
					horizontal_count = 1;
					return true;
				}
			} else if (difference_mid_x < -25) {
				current_frame_left1 = frame;
				left1 = true;
				right_after_left1 = false;
			} else {
				if ((frame - current_frame_left1) < NO2)
					right_after_left1 = true;
				else
					right_after_left1 = false;
			}
		}
		return false;
	}

	public boolean verticalNod() {
		if (vertical_count == 1) {
			// calculateMidPoint();
			if (difference_mid_y > -25 && difference_mid_y < 25) {
				if (up1 && down_after_up1) {
					down_after_up1 = false;
					up1 = false;
					vertical_node = true;
				}
			} else if (difference_mid_y > 25) {
				current_frame_up1 = frame;
				up1 = true;
				down_after_up1 = false;
			} else {
				if ((frame - current_frame_up1) < NO2)
					down_after_up1 = true;
				else
					down_after_up1 = false;
			}
			if (vertical_node)
				vertical_count++;
		} else if (vertical_count == 2 && vertical_node) {
			// faces = detected faces
			// frame = current frame no
			// calculateMidPoint(); //calculate mid points
			if (difference_mid_y > -25 && difference_mid_y < 25) {
				// to complete move face must be ideal for more than 10 frames
				// it must have gone down so "down" must be true
				// it must have gone up after being down
				if (down1 && up_after_down1) {
					// reset the variables used and return true;
					up_after_down1 = false;
					down1 = false;
					vertical_count = 1;
					vertical_node = false;
					return true;
				}
			} else if (difference_mid_y < -25) {
				// face is down respect to previous frame
				current_frame_down1 = frame; // save current frame
				down1 = true; // face is down
				up_after_down1 = false; // face is not up
			} else {
				// face is up respect to previous frame
				if ((frame - current_frame_down1) < NO2)
					up_after_down1 = true; // face is again up after down
				else
					up_after_down1 = false; // movement is taking longer time so
											// set to false
			}
		}
		return false;
	}

	public boolean headRightMove() {
		// calculateMidPoint();
		if (difference_mid_x > -25 && difference_mid_x < 25) {
			if ((frame - current_frame_right) > NO && right && left_after_right) {
				left_after_right = false;
				right = false;
				return true;
			}
		} else if (difference_mid_x > 25) {
			current_frame_right = frame;
			right = true;
			left_after_right = false;
		} else {
			if ((frame - current_frame_right) < NO2)
				left_after_right = true;
			else
				left_after_right = false;
		}
		return false;
	}

	public boolean headLeftMove() {
		// calculateMidPoint();
		if (difference_mid_x > -25 && difference_mid_x < 25) {
			if ((frame - current_frame_left) > NO && left && right_after_left) {
				right_after_left = false;
				left = false;
				return true;
			}
		} else if (difference_mid_x < -25) {
			current_frame_left = frame;
			left = true;
			right_after_left = false;
		} else {
			if ((frame - current_frame_left) < NO2)
				right_after_left = true;
			else
				right_after_left = false;
		}
		return false;
	}

	public boolean headUpMove() {
		// calculateMidPoint();
		if (difference_mid_y > -25 && difference_mid_y < 25) {
			if ((frame - current_frame_up) > NO && up && down_after_up) {
				down_after_up = false;
				up = false;
				return true;
			}
		} else if (difference_mid_y > 25) {
			current_frame_up = frame;
			up = true;
			down_after_up = false;
		} else {
			if ((frame - current_frame_up) < NO2)
				down_after_up = true;
			else
				down_after_up = false;
		}
		return false;
	}

	public boolean headDownMove() {
		// faces = detected faces
		// frame = current frame no
		// calculateMidPoint(); //calculate mid points
		if (difference_mid_y > -25 && difference_mid_y < 25) {
			// to complete move face must be ideal for more than 10 frames
			// it must have gone down so "down" must be true
			// it must have gone up after being down
			if ((frame - current_frame_down) > NO && down && up_after_down) {
				// reset the variables used and return true;
				up_after_down = false;
				down = false;
				return true;
			}
		} else if (difference_mid_y < -25) {
			// face is down respect to previous frame
			current_frame_down = frame; // save current frame
			down = true; // face is down
			up_after_down = false; // face is not up
		} else {
			// face is up respect to previous frame
			if ((frame - current_frame_down) < NO2)
				up_after_down = true; // face is again up after down
			else
				up_after_down = false; // movement is taking longer time so set
										// to false
		}
		return false;
	}

	public void calculateMidPoint() {
		// width of recognized face and
		// current frame's x axis and y axis mid point
		int width = 0, current_x_point, current_y_point;
		rect = new Rect();

		// detect the biggest face detected
		for (Rect rect1 : faces.toArray()) {
			if (rect1.width > width)
				rect = rect1;
		}

		// for the first frame calculate initial points
		if (frame == 0) {
			intial_x_point = rect.x + rect.width / 2;
			initial_y_point = rect.y + rect.height / 2;
		}
		// if not first then for every 10th frame calculate difference of mid
		// points
		else if ((frame % 10) == 0) {
			current_x_point = rect.x + rect.width / 2; // Set mid point of
														// current x axis of
														// face
			current_y_point = rect.y + rect.height / 2; // Set mid point of
														// current y axis of
														// face

			// difference of mid points
			difference_mid_x = intial_x_point - current_x_point;
			difference_mid_y = initial_y_point - current_y_point;

			// set current points as initial points
			intial_x_point = current_x_point;
			initial_y_point = current_y_point;
		}
	}
}
