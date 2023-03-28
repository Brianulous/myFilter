package tw.brian.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import tw.brian.exception.RecordListNullException;
import tw.brian.model.GenderLawCase;
import tw.brian.model.GenderLawService;
import tw.brian.model.GenderLawSqlServerDao;
import tw.brian.model.LaborLawCase;
import tw.brian.model.LaborLawService;
import tw.brian.model.LaborLawSqlServerDao;
import tw.brian.util.CSVUtil;
import tw.brian.util.ConnectionFactory;
import tw.brian.util.TSUtility;
/**
 * 操作介面 main於最底下
 * @author 88693
 *
 */
public class FilterView {
	private Connection conn;
	private final GenderLawService genderLawService;
	private final LaborLawService laborLawService;

	public FilterView(ConnectionFactory factory) {
		this.conn = factory.createSQLServerConnection();
		this.genderLawService = new GenderLawService(new GenderLawSqlServerDao(conn));
		this.laborLawService = new LaborLawService(new LaborLawSqlServerDao(conn));
	}

	public void enterMainMenu() {
		boolean flag = true;
		while (flag) {
			System.out.println("\n\n1-性平法專區 2-勞基法專區 3-退出Filter");
			char menu = TSUtility.readMenuSelection3();
			switch (menu) {
			case '1':
				System.out.println("------歡迎進入性平專區!-----\n");
				genderOptionSelect();
				break;
			case '2':
				System.out.println("-------歡迎進入勞基法專區!-----\n");
				laborOptionSelect();

				break;
			case '3':
				System.out.println("是否退出Filter(Y/N)");
				char isExit = TSUtility.readConfirmSelection();
				if (isExit == 'Y') {
					flag = false;
					System.out.println("下次見~!");

				}
				break;
			}
		}
	}

	private void genderOptionSelect() {
		System.out.println("1-新增性平法案件 2-刪除性平法案件 3-修改性平法案件 4-查詢性平法案件 5-退出");
		char option = TSUtility.readMenuSelection5();
		switch (option) {
		case '1':
			genderAdd();
			break;
		case '2':
			genderDelete();
			break;
		case '3':
			genderUpdate();
			break;
		case '4':
			genderQuery();
			break;
		case '5':
			System.out.println("返回主選單");
			break;

		default:
			break;
		}
	}

	private void laborOptionSelect() {
		System.out.println("1-新增勞基法案件 2-刪除勞基平法案件 3-修改勞基法案件 4-查詢勞基法案件 5-退出");
		char option = TSUtility.readMenuSelection5();
		switch (option) {
		case '1':
			laborAdd();
			break;
		case '2':
			laborDelete();
			break;
		case '3':
			laborUpdate();
			break;
		case '4':
			laborQuery();
			break;
		case '5':
			System.out.println("返回主選單");
			break;

		default:
			break;
		}
	}

	private void genderQuery() {
		System.out.println("1-日期查詢 2-企業名稱查詢 3-處分書查詢 4-法律查詢 5-退出");
		char option = TSUtility.readMenuSelection5();
		switch (option) {
		case '1':
			try {
				System.out.println("請輸入起始日期(YYYYMMDD)");
				String datestr1 = TSUtility.readDate();
				System.out.println("請輸入結束日期(YYYYMMDD)");
				String datestr2 = TSUtility.readDate();

				for (GenderLawCase lawcase : genderLawService.queryByDate(datestr1, datestr2)) {
					System.out.println(lawcase);
				}
			} catch (SQLException e) {
				e.getMessage();
			}
			break;
		case '2':
			System.out.println("請輸入企業名稱");
			String enterprise = TSUtility.readLine();
			try {
				System.out.println(genderLawService.queryByEnterprise(enterprise));
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}

			break;
		case '3':
			System.out.println("請輸入處分書文號");
			String docno = TSUtility.readLine();
			try {
				for (GenderLawCase lawCase : genderLawService.queryByEnterprise(docno)) {
					System.out.println(lawCase);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

			break;
		case '4':
			System.out.println("請輸入法規");
			String statement = TSUtility.readLine();
			try {
				for (GenderLawCase genderLawCase : genderLawService.queryByStatement(statement)) {
					System.out.println(genderLawCase);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			break;
		case '5':
			System.out.println("返回主選單");
			break;
		}

	}

	private void genderAdd() {
		System.out.println("請輸入欲添加檔案路徑");
		List<CSVRecord> list = new ArrayList<>();

		try {
			String path = TSUtility.readPath();
			list = CSVUtil.getCSVGender(path);
			genderLawService.insertData(list);
			System.out.println("成功添加性平法案件資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (RecordListNullException e) {
			System.out.println(e.getMessage());
		} 
	

	}


	private void genderDelete() {
		System.out.println("WARNING!資料一去不復返，請謹慎操作!");
		System.out.println("請輸入案件id");
		int id = TSUtility.readInt();
		try {
			int row = genderLawService.deletedataById(id);
			System.out.println("成功刪除" + row + "筆資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void genderUpdate() {
		System.out.println("WARNING!下列操作可能使資料異動!\n");
		System.out.println("請輸入欲更改日期[YYYYMMDD]");
		String dateStr = TSUtility.readDate();
		System.out.println("請輸入案件id");
		int id = TSUtility.readInt();

		try {
			int row = genderLawService.updateDateById(dateStr, id);
			System.out.println("已更改" + row + "筆資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
//**************************************************************************************************

	private void laborQuery() {
		System.out.println("1-日期查詢 2-企業名稱查詢 3-處分書查詢 4-法律查詢 5-退出");
		char option = TSUtility.readMenuSelection5();
		switch (option) {
		case '1':
			System.out.println("請輸入起始日期");
			String date1 = TSUtility.readDate();
			System.out.println("請輸入結束日期");
			String date2 = TSUtility.readDate();

			try {
				for (LaborLawCase lawCase : laborLawService.queryByDate(date1, date2)) {
					System.out.println(lawCase);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			break;
		case '2':
			System.out.println("請輸入企業名稱");
			String enterprise = TSUtility.readLine();
			try {
				for (LaborLawCase laborLawCase : laborLawService.queryByEnterprise(enterprise)) {
					System.out.println(laborLawCase);
				}
			} catch (SQLException e) {
				e.getMessage();
			}
			break;
		case '3':
			System.out.println("請輸入處分書文號");
			String docno = TSUtility.readLine();
			try {
				System.out.println(laborLawService.queryByDocno(docno));
			} catch (SQLException e) {
				e.getMessage();
			}
			break;
		case '4':
			System.out.println("請輸入法規");
			String statement = TSUtility.readLine();
			try {
				for (LaborLawCase lawCase : laborLawService.queryByStatement(statement)) {
					System.out.println(lawCase);
				}
			} catch (SQLException e) {
				e.getMessage();
			}
			break;
		case '5':
			System.out.println("返回主選單");
			break;

		}
	}

	private void laborAdd() {
		System.out.println("請輸入欲添加檔案路徑");
		String path = TSUtility.readPath();
		List<CSVRecord> list = new ArrayList<>();

		list = CSVUtil.getCSVGender(path);
		try {
			laborLawService.insertData(list);
			System.out.println("成功添加勞基法案件資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (RecordListNullException e) {
			System.out.println(e.getMessage());
		}
	}

	private void laborDelete() {
		System.out.println("WARNING!資料一去不復返，請謹慎操作!");
		System.out.println("請輸入案件id");
		int id = TSUtility.readInt();
		try {
			int row = laborLawService.deletedataById(id);
			System.out.println("成功刪除" + row + "筆資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void laborUpdate() {
		System.out.println("WARNING!下列操作可能使資料異動!\n");
		System.out.println("請輸入欲更改罰鍰金額");
		int newFine = TSUtility.readInt();
		System.out.println("請輸入案件id");
		int id = TSUtility.readInt();

		try {
			int row = laborLawService.updateFineById(newFine, id);
			System.out.println("已更改" + row + "筆資料");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void greeting() {
		System.out.println("\t\t\t\tWelcome on board!\t\t\t\t\n");
		System.out.println(
				"--------------------------For man is man and master of his fate--------------------------------\n");

	}

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		FilterView view = new FilterView(factory);

		view.greeting();
		view.enterMainMenu();
		

	}

}
