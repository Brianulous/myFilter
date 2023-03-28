package tw.brian.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface LaborLawDao {

	// 增1

	void addLaborLaw(LaborLawCase lawCase) throws SQLException;

	// 增2
	void addLaborLaw(Date punishDate, String docno, String enterprise, String statement, String content, int fine)
			throws SQLException;

	// 查1
	LaborLawCase getNewestCaseByEnterprise(String enterprise) throws SQLException;

	// 查2
	LaborLawCase getOldestCaseByEnterprise(String enterprise) throws SQLException;

	// 查3
	LaborLawCase hightestFineCaseByEnt(String enterprise) throws SQLException;

	// 查4
	List<LaborLawCase> getCaseBeforeDate(String dateString) throws SQLException;

	// 查5
	List<LaborLawCase> getCaseAfterDate(String dateString) throws SQLException;

	// 查6
	List<LaborLawCase> getCaseByDate(String dateString1, String daString2) throws SQLException;

	// 改
	int updateById(int fine, int id) throws SQLException;

	// 刪
	int deleteById(int id) throws SQLException;

	// 查
	LaborLawCase getCaseByDocno(String docno) throws SQLException;

	// 查
	List<LaborLawCase> getCaseByStatement(String statement) throws SQLException;
}
