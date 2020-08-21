package swsk33.RaW.Exception;

/**
 * 行数越界异常，当读取文件指定的行数超过其本有的行数时抛出此异常
 * 
 * @author swsk33
 *
 */
public class RowsOutOfBoundsException extends Exception {
	/**
	 * 行数越界异常，当读取文件指定的行数超过其本有的行数时抛出此异常
	 * 
	 * @param msg
	 */
	public RowsOutOfBoundsException(String msg) {
		super(msg);
	}
}
