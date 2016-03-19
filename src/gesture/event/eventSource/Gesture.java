package gesture.event.eventSource;

/** @author hirenmayani.com
 * 
 */
import gesture.detection.configuration.InitConfig;
import gesture.event.gestureEventsListener.EyeListener;
import gesture.event.gestureEventsListener.FaceListener;

public class Gesture implements Runnable{
	private boolean isInitFace = false, isInitEye = false;
//	private InitConfig configer;
	private Face face =null;
	private Eye eye=null;
	private Thread t;

	public Gesture()
	{
		t = new Thread(this, "Gesture Detection Thread");
	}
	
	public boolean addFaceListener(FaceListener listener) {
		if (!isInitFace) {
			face = new Face();
			face.addFaceListener(listener);
			return true;
		} else {
			return false;
		}
	}

	public boolean addEyeListener(EyeListener listener) {
		if (!isInitEye) {
			eye = new Eye();
			eye.addEyeListener(listener);
			return true;
		} else {
			return false;
		}
	}
	
	
	public void startDetection()
	{
		t.start();
	}

	@Override
	public void run() {
		if(face==null && eye==null)
		{
//			throw new Exception("Register at least one listener");
		}
//		configer = new InitConfig();
		InitConfig.getInitConfig().init(face,eye);
		
	}

}
