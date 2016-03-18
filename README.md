 Abstract
 
	The ‘API for Gesture Recognition’ is an API developed to interact with computer using gesture of face and eyes. Generally computer application takes input from keyboards & mouse to control. But using this API application can get controlling input using camera. End users of this API may be software developer or game developer. They can include this library in their code and can use provided methods to generate event when any facial gesture occurs.

1.	INRODUCTION

	TThe goal of technology is to make human lives easy.  It is observed that moving head and eyes are more convenient than moving hands and other parts of body. So, goal of this API is to control computer applications using possible gesture of face and eyes. More importantly this API can be very useful to disabled person. Disabled person who can’t control computer using hands then they can control computer using head.
	This API is designed to interact with computer using gesture. Gestures are detected from continuous input taken from camera. Based on continuous sequence of frame pattern can be emerged. This pattern decides which gesture occured. When any gesture occurs corresponding event will fire. End user of this API is technical person like software developer or game developer can include this library on their code to use this API.

Tools/Technologies used

	•Technology: Java, OpenCV
	•Tool: Eclipse, eGit

2.	Library modules specifications

	R1 Detecting Object
	It detects parts of the body like eye, face etc. whose event-listener are added.
		R1.1 Face Detection
			Input: Request to detect face.
			Output: returns array of faces which are currently in frame.
			Process: Using OpenCV library and provided XML system detect faces.

		R1.2 Eye Detection
			Input: Request to detect eyes.
			Output: returns array of eyes which are currently in frame.
			Process: Using OpenCV library and provided XML system detect eyes.
			
		R1.3 Detect eyes of face having largest area
			Input: Request to detect eyes of face having highest area.
			Output: returns eyes in current frame whose corresponding face is having largest area.
			Process: from detected eyes returns eyes matching given criteria.

	R2 Detecting Gesture
	This module is to detect gestures like head nod, eye blink etc.
		R2.1 Detect facial gesture
		From detected face sequence detect gesture.
			R2.1.1 Face left Move
				Input: Sequence of detected faces
				Output: If head has moved left then fire this event.
			R2.1.2 Face Right Move
				Input: Sequence of detected faces
				Output: If head has moved right then fire this event.
			R2.1.3 Face up Move
				Input: Sequence of detected faces
				Output: If head has moved up then fire this event.
			R2.1.4 Face down Move
				Input: Sequence of detected faces
				Output: If head has moved down then fire this event.
			R2.1.5 Vertical head nod
				Input: Sequence of detected faces
				Output: If person has nodded vertically then fire event.
			R2.1.6 Horizontal head nod
				Input: Sequence of detected faces
				Output: If person has nodded horizontally then fire event.
	
		R2.2 detect eyes gesture
		From detected eye sequence detect gesture.
			R2.2.1 Two eye blink
				Input: Sequence of detected eyes
				Output: If person has blinked eyes twice then fire this event.
			R2.2.2 Three eye blink
				Input: Sequence of detected eyes
				Output: If person has blinked eyes thrice then fire this event.
	 		R2.2.3 Four eye blink
				Input: Sequence of detected eyes
				Output: If person blinks eyes four times then fire this event.
			 R2.2.4 Sleep
				Input: Sequence of detected faces
				Output: If person is sleeping then fire event.
			R2.2.5 Left eye closed
				Input: Sequence of detected faces
				Output: If person closed only left eye then fire event.
			R2.2.5 Right eye closed
				Input: Sequence of detected faces
				Output: If person closed only right eye then fire event.

	R3 Event Handling
	With the use of Event Delegation model handle event
		Input: status of detected gesture
		Output: if gesture detected fire corresponding event
		Process:  Using Event Source, Event Listener and Event class handle and process event
