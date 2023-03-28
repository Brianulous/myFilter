package tw.brian.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import tw.brian.exception.RecordListNullException;

/**
 * 客戶端呼叫
 * 
 * @author 88693
 *
 */
public class LaborLawService {
	private final LaborLawDao laborLawDao;

	public LaborLawService(LaborLawDao laborLawDao) {
		this.laborLawDao = laborLawDao;

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
			LaborLawCase lawCase = new LaborLawCase(record);
			laborLawDao.addLaborLaw(lawCase);
		}

	}

	/**
	 * 輸入企業名稱:查詢最新、最舊與罰金最高的勞基法案件
	 * 
	 * @param entriprise
	 * @return
	 * @throws SQLException
	 */
	public List<LaborLawCase> queryByEnterprise(String enterprise) throws SQLException {
		List<LaborLawCase> result = new ArrayList<>();
		result.add(laborLawDao.getNewestCaseByEnterprise(enterprise));
		result.add(laborLawDao.getOldestCaseByEnterprise(enterprise));
		result.add(laborLawDao.hightestFineCaseByEnt(enterprise));
		return result;
	}

	/**
	 * 輸入法條，得到案件
	 * 
	 * @param statement
	 * @return
	 * @throws SQLException
	 */
	public List<LaborLawCase> queryByStatement(String statement) throws SQLException {
		return laborLawDao.getCaseByStatement(statement);
	}

	/**
	 * 輸入處分書文號得案件(單件)
	 * 
	 * @param docno
	 * @return
	 * @throws SQLException
	 */
	public LaborLawCase queryByDocno(String docno) throws SQLException {
		return laborLawDao.getCaseByDocno(docno);

	}

	/**
	 * 日期前案件
	 * 
	 * @param dateStr1
	 * @param dateStrig
	 * @return
	 * @throws SQLException
	 */

	public List<LaborLawCase> queryBeforeDate(String dateSring) throws SQLException {

		return laborLawDao.getCaseBeforeDate(dateSring);
	}

	/**
	 * 日期後案件
	 * 
	 * @param dateSring
	 * @return
	 * @throws SQLException
	 */
	public List<LaborLawCase> queryAfterDate(String dateSring) throws SQLException {

		return laborLawDao.getCaseAfterDate(dateSring);
	}

	/**
	 * 日期間案件
	 * 
	 * @param dateSring1
	 * @param dateSring2
	 * @return
	 * @throws SQLException
	 */
	public List<LaborLawCase> queryByDate(String dateSring1, String dateSring2) throws SQLException {

		return laborLawDao.getCaseByDate(dateSring1, dateSring2);

	}

	/**
	 * 更改資料
	 * 
	 * @param fine
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int updateFineById(int fine, int id) throws SQLException {
		return laborLawDao.updateById(fine, id);

	}

	/**
	 * 刪除資料
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int deletedataById(int id) throws SQLException {
		return laborLawDao.deleteById(id);
	}

}
