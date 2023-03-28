package tw.brian.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface GenderLawDao {

	// 增1

	void addGenderLaw(GenderLawCase lawCase) throws SQLException;

	// 增2
	void addGenderLaw(Date punishDate, String docno, String enterprise, String statement, String content)
			throws SQLException;

	// 查1
	GenderLawCase getNewestCaseByEnterprise(String enterprise) throws SQLException;

	// 查2
	GenderLawCase getOldestCaseByEnterprise(String enterprise) throws SQLException;

	// 查3
	List<GenderLawCase> getCaseBeforeDate(String dateString) throws SQLException;

	// 查4
	List<GenderLawCase> getCaseAfterDate(String dateString) throws SQLException;

	// 查5
	List<GenderLawCase> getCaseByDate(String dateString1, String dateString2) throws SQLException;

	// 查6
	List<GenderLawCase> getAllGenderLawCases() throws SQLException;

	// 改1
	int updateById(String dateStr, int id) throws SQLException;

	// 改2
	int updateContentByDocno(String content, String docno) throws SQLException;

	// 刪1
	int deleteById(int id) throws SQLException;

	// 查
	GenderLawCase getCaseByDocno(String docno) throws SQLException;

	// 查
	List<GenderLawCase> getCaseByStatement(String statement) throws SQLException;

}