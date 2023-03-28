package tw.brian.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import tw.brian.exception.RecordListNullException;

public class GenderLawService {
	private final GenderLawDao genderLawDao;

	/**
	 * 客戶端service
	 * 
	 * @param genderLawDao
	 */
	public GenderLawService(GenderLawDao genderLawDao) {
		this.genderLawDao = genderLawDao;
	}

	/**
	 * 增加資料至LaborCase table
	 * 
	 * @param list
	 * @throws SQLException
	 * @throws RecordListNullException
	 */
	public void insertData(List<CSVRecord> list) throws SQLException, RecordListNullException {
		for (CSVRecord record : list) {
			GenderLawCase lawCase = new GenderLawCase(record);
			genderLawDao.addGenderLaw(lawCase);
		}

	}

	/**
	 * 輸入企業得到最新、最舊案件
	 * 
	 * @param entriprise
	 * @return
	 * @throws SQLException
	 */
	public List<GenderLawCase> queryByEnterprise(String entriprise) throws SQLException {
		List<GenderLawCase> result = new ArrayList<>();
		result.add(genderLawDao.getNewestCaseByEnterprise(entriprise));
		result.add(genderLawDao.getOldestCaseByEnterprise(entriprise));
		return result;
	}

	/**
	 * 輸入日期得到日期前案件
	 * 
	 * @param dateSring
	 * @return
	 * @throws SQLException
	 */
	public List<GenderLawCase> queryBeforeDate(String dateSring) throws SQLException {

		return genderLawDao.getCaseBeforeDate(dateSring);
	}

	/**
	 * 輸入日期得到日期後案件
	 * 
	 * @param dateSring
	 * @return
	 * @throws SQLException
	 */
	public List<GenderLawCase> queryAfterDate(String dateSring) throws SQLException {

		return genderLawDao.getCaseAfterDate(dateSring);

	}

	/**
	 * 輸入處分書文號得案件(單件)
	 * 
	 * @param docno
	 * @return
	 * @throws SQLException
	 */
	public GenderLawCase queryByDocno(String docno) throws SQLException {
		return genderLawDao.getCaseByDocno(docno);

	}

	/**
	 * 輸入日期得到日期間案件
	 * 
	 * @param dateSring
	 * @return
	 * @throws SQLException
	 */
	public List<GenderLawCase> queryByDate(String dateSring1, String dateSring2) throws SQLException {

		return genderLawDao.getCaseByDate(dateSring1, dateSring2);

	}

	/**
	 * 條文
	 * 
	 * @param statement
	 * @return
	 * @throws SQLException
	 */
	public List<GenderLawCase> queryByStatement(String statement) throws SQLException {

		return genderLawDao.getCaseByStatement(statement);

	}

	/**
	 * 全部資料
	 * 
	 * @return
	 * @throws SQLException
	 */

	public List<GenderLawCase> queryAllData() throws SQLException {

		return genderLawDao.getAllGenderLawCases();

	}

	/**
	 * 更改資料
	 * 
	 * @param fine
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateDateById(String daString, int id) throws SQLException {
		return genderLawDao.updateById(daString, id);

	}

	/**
	 * 由處分文書號碼更改內容
	 * 
	 * @param fine
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateContentByDocno(String content, String docno) throws SQLException {
		return genderLawDao.updateContentByDocno(content, docno);

	}

	/**
	 * 刪除資料
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deletedataById(int id) throws SQLException {
		return genderLawDao.deleteById(id);
	}

}
