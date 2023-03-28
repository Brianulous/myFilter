package tw.brian.exception;

/**
 * 自建Exception增加可讀
 * 
 * @author 88693
 *
 */
public class RecordListNullException extends Exception {
	static final long serialVersionUID = -3387516993124229948L;

	public RecordListNullException() {
	}

	public RecordListNullException(String msg) {
		super(msg);
	}

}
