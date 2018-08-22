package game_abhi;

// This class is only used to create print the logs:
// which class was called by what class and at what time

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {

	protected static String getCallerCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		String callerClassName = null;
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Debug.class.getName())
					&& ste.getClassName().indexOf("java.lang.Thread") != 0) {
				if (callerClassName == null) {
					callerClassName = ste.getClassName();
				} else if (!callerClassName.equals(ste.getClassName())) {
					return "called by : " + ste.getClassName();
				}
			}
		}
		return null;
	}

	protected void getTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(getClass().getName() + " : " + dateFormat.format(date)); // 2018/11/16 02:08:43
	}
}
