package king.bird.marrykotlin.util;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author
 * @return Log工具类
 */
public final class LogUtils {
	// 混淆打包 置为false

	private LogUtils() {
	}

	/**
	 *
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg) {
		Log.e(tag, msg);
	}
	/**
	 *
	 * @param msg
	 */
	public static void e(String msg) {
		Log.e("LogUtils", msg);
	}

	/**
	 * 打印log到文件中
	 * 
	 * @param logPath
	 *            日志文件路径
	 * @param logData
	 *            日志内容
	 * @param override
	 *            是否覆盖文件内容
	 */
	public static void f(String logPath, String logData, boolean override) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(logPath, !override));
			if (!override) {
				writer.append(logData);
			} else {
				writer.write(logData);
			}
			writer.newLine();
		} catch (FileNotFoundException e) {
//			e(new LogUtil(), e.toString());
		} catch (IOException e) {
//			e(new LogUtil(), e.toString());
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (Exception e2) {
//				e(new LogUtil(), e2.toString());
			}
		}
	}
}
