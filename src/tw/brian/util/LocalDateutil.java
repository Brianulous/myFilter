package tw.brian.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateutil {

	private static final Pattern AD_PATTERN = Pattern.compile("(\\d{4})[-/年](\\d{1,2})[-/月](\\d{1,2})");
	private static final Pattern MING_GUO_PATTERN = Pattern.compile("(\\d{1,3})[-/年](\\d{1,2})[-/月](\\d{1,2})");
	private static final Pattern SEVEN_WORDS_PATTERN = Pattern.compile("(\\d{7})");
	private static final Pattern EIGHT_WORDS_PATTERN = Pattern.compile("(\\d{8})");

//@Test
//	public static void main(String[] args) {
//		assert parseLocalDate("20231212").get().equals(LocalDate.of(2023, 12, 12));
//		assert !parseLocalDate("abc/sss/sss").isPresent();
//		assert parseLocalDate("abc/sss/sss").isPresent();
//	}
	/**
	 * format all form of dateString to Optional<LocalDate>
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Optional<LocalDate> parseLocalDate(String dateStr) {
		Matcher m = AD_PATTERN.matcher(dateStr);
		if (m.find()) {
			int year = Integer.parseInt(m.group(1));
			int month = Integer.parseInt(m.group(2));
			int day = Integer.parseInt(m.group(3));
			return Optional.of(LocalDate.of(year, month, day));
		}
		return parseLocalDateMinGuo(dateStr);
	}

	private static Optional<LocalDate> parseLocalDateMinGuo(String dateStr) {
		Matcher m = MING_GUO_PATTERN.matcher(dateStr);
		if (m.find()) {
			int year = Integer.parseInt(m.group(1)) + 1911;
			int month = Integer.parseInt(m.group(2));
			int day = Integer.parseInt(m.group(3));
			return Optional.of(LocalDate.of(year, month, day));
		}
		return parseLocalDateEight(dateStr);
	}

	private static Optional<LocalDate> parseLocalDateEight(String dateStr) {
		Matcher m = EIGHT_WORDS_PATTERN.matcher(dateStr);
		if (m.find()) {
			int year = Integer.parseInt(dateStr.substring(0, 4));
			int month = Integer.parseInt(dateStr.substring(4, 6));
			int day = Integer.parseInt(dateStr.substring(6));
			return Optional.of(LocalDate.of(year, month, day));

		}
		return parseLocalDateSeven(dateStr);

	}

	private static Optional<LocalDate> parseLocalDateSeven(String dateStr) {
		Matcher m = SEVEN_WORDS_PATTERN.matcher(dateStr);
		if (m.find()) {
			int year = Integer.parseInt(dateStr.substring(0, 3)) + 1911;
			int month = Integer.parseInt(dateStr.substring(3, 5));
			int day = Integer.parseInt(dateStr.substring(5));
			return Optional.of(LocalDate.of(year, month, day));

		}
		return Optional.empty();
	}

	// ******************被自己淘汰的方法(非官方api)，為方便複習故保留***************************************

	/**
	 * @deprecated regex作為條件篩選後分別帶入不同方法(淘汰)
	 * 
	 * @param date
	 * @return
	 */

	public LocalDate formatDateData(String dateStr) {
		if (dateStr == null) {
			throw new NullPointerException();
		}
		dateStr = dateStr.replaceAll("\\s+", "").replaceAll("\\D", "/");

		LocalDate lcd = null;

		if (dateStr.matches("(\\d{4})[年/-](\\d{1,2})[月/-](\\d{1,2})(日?)")) {
			if (dateStr.endsWith("/")) {
				dateStr = dateStr.substring(0, dateStr.length() - 1);
			}
			lcd = LocalDateutil.adFormat(dateStr);
			return lcd;

		} else if (dateStr.matches("(\\d{2,3})[年/-](\\d{1,2})[月/-](\\d{1,2})(日?)")) {
			if (dateStr.endsWith("/")) {
				dateStr = dateStr.substring(0, dateStr.length() - 1);
			}
			lcd = LocalDateutil.minguoFormat(dateStr);

			return lcd;
		}
		throw new IllegalArgumentException("can't match string: " + dateStr);
	}

	/**
	 * 將已replace過字串format(淘汰)
	 * 
	 * @deprecated
	 * @param dateStr
	 * @return
	 */
	public static LocalDate adFormat(String dateStr) {
		LocalDate lcd = null;

		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy/MM/d");
		DateTimeFormatter dateFormat3 = DateTimeFormatter.ofPattern("yyyy/M/dd");
		DateTimeFormatter dateFormat4 = DateTimeFormatter.ofPattern("yyyy/M/d");

		if (dateStr.matches("\\d{4}/\\d{2}/\\d{2}")) {
			lcd = LocalDate.parse(dateStr, dateFormat1);
		} else if (dateStr.matches("\\d{4}/\\d{2}/\\d{1}")) {
			lcd = LocalDate.parse(dateStr, dateFormat2);

		} else if (dateStr.matches("\\d{4}/\\d{1}/\\d{2}")) {
			lcd = LocalDate.parse(dateStr, dateFormat3);
		} else if (dateStr.matches("\\d{4}/\\d{1}/\\d{1}")) {
			lcd = LocalDate.parse(dateStr, dateFormat4);
		}

		return lcd;

	}

	/**
	 * 將已replace過字串format(淘汰)
	 * 
	 * @deprecated
	 * @param dateStr
	 * @return
	 */
	public static LocalDate minguoFormat(String dateStr) {

		String yyy = dateStr.substring(0, 3);
		int temp = Integer.parseInt(yyy) + 1911;
		String rs = temp + dateStr.substring(3);

		LocalDate lcd = null;

		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy/MM/d");
		DateTimeFormatter dateFormat3 = DateTimeFormatter.ofPattern("yyyy/M/d");
		DateTimeFormatter dateFormat4 = DateTimeFormatter.ofPattern("yyyy/M/dd");

		if (rs.matches("\\d{4}/\\d{2}/\\d{2}")) {
			lcd = LocalDate.parse(rs, dateFormat1);
		} else if (rs.matches("\\d{4}/\\d{2}/\\d{1}")) {
			lcd = LocalDate.parse(rs, dateFormat2);

		} else if (rs.matches("\\d{4}/\\d{1}/\\d{2}")) {
			lcd = LocalDate.parse(rs, dateFormat4);
		} else if (rs.matches("\\d{4}/\\d{1}/\\d{1}")) {
			lcd = LocalDate.parse(rs, dateFormat3);
		}

		return lcd;

	}

}
