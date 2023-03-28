package tw.brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//MysqlDao實做GenderLawDao
public class GenderLawMysqlDao implements GenderLawDao {

	private final Connection conn;

	public GenderLawMysqlDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void addGenderLaw(GenderLawCase lawCase) throws SQLException {
		String sql = "insert into GenderCase(punish_date,docno,enterprise,statement,content)" + "values(?,?,?,?,?);";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, lawCase.getPunishDate());
		preState.setString(2, lawCase.getDocno());
		preState.setString(3, lawCase.getEnterprise());
		preState.setString(4, lawCase.getStatement());
		preState.setString(5, lawCase.getContent());

		int rows = preState.executeUpdate();
		System.out.printf("改變了%d 筆資料%n", rows);

		preState.close();
	}

	@Override
	public void addGenderLaw(Date punishDate, String docno, String enterprise, String statement, String content)
			throws SQLException {
		addGenderLaw(punishDate, docno, enterprise, statement, content);

	}

	@Override
	public GenderLawCase getOldestCaseByEnterprise(String enterprise) {
		return null;
	}

	@Override
	public GenderLawCase getNewestCaseByEnterprise(String enterprise) {
		return null;
	}

	@Override
	public List<GenderLawCase> getCaseBeforeDate(String dateString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenderLawCase> getCaseAfterDate(String dateString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenderLawCase> getCaseByDate(String dateString1, String dateString2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(String dateStr, int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GenderLawCase> getAllGenderLawCases() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateContentByDocno(String content, String docno) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GenderLawCase getCaseByDocno(String docno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GenderLawCase> getCaseByStatement(String statement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
