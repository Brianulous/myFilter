package tw.brian.util;

import java.util.Optional;

public class NumberUtil {

	public static Optional<Integer> parseFine(String fineStr) {
		if (!fineStr.isEmpty()) {
			fineStr = fineStr.replaceAll("[^\\d-]", "");
			return Optional.of(Integer.parseInt(fineStr));
		} else if (fineStr.isEmpty()) {
			return Optional.of(0);
		}
		return Optional.empty();
	}

}