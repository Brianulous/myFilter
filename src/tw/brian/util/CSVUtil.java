package tw.brian.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVUtil {

	public static List<CSVRecord> getCSVGender(String p) {
		String path = p;
		List<CSVRecord> list = new LinkedList<>();

		try (Reader in = new InputStreamReader(new FileInputStream(path), "Big5")) {

			// define Header
			Iterable<CSVRecord> records = CSVFormat.Builder.create()
					.setHeader("事業單位名稱", "違反法令條", "違反法規內容", "處分書文號", "處分日期").build().parse(in);
			// jump over first row
			records.iterator().next();
			for (CSVRecord record : records) {
				list.add(record);
				

			}
			return list;

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static List<CSVRecord> getCSVLabor(String p) {
		String path = p;
		List<CSVRecord> list = new LinkedList<>();

		try (Reader in = new InputStreamReader(new FileInputStream(path))) {

			// define Header
			Iterable<CSVRecord> records = CSVFormat.Builder.create()
					.setHeader("事業單位名稱", "違反法令條", "違反法規內容", "處分書文號", "處分日期","罰鍰金額").build().parse(in);
			// jump over first row
			records.iterator().next();
			for (CSVRecord record : records) {
				list.add(record);
				

			}
			return list;

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	 
}