package tw.brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LaborLawMySqlDao implements LaborLawDao {
	private final Connection conn;

	public LaborLawMySqlDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void addLaborLaw(LaborLawCase lawCase) throws SQLException {
		String sql = "insert into LaborCase(punish_date,docno,enterprise,statement,content,fine)"
				+ "values(?,?,?,?,?,?);";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, lawCase.getPunishDate());
		preState.setString(2, lawCase.getDocno());
		preState.setString(3, lawCase.getEnterprise());
		preState.setString(4, lawCase.getStatement());
		preState.setString(5, lawCase.getContent());
		preState.setInt(5, lawCase.getFine());

	}

	@Override
	public void addLaborLaw(Date punishDate, String docno, String enterprise, String statement, String content,
			int fine) throws SQLException {
		addLaborLaw(new LaborLawCase(punishDate, docno, enterprise, statement, content, fine));
	}

	@Override
	public LaborLawCase getNewestCaseByEnterprise(String enterprise) {
		return null;
	}

	@Override
	public LaborLawCase getOldestCaseByEnterprise(String enterprise) {
		return null;
	}

	@Override
	public LaborLawCase hightestFineCaseByEnt(String enterprise) throws SQLException {
		return null;
	}

	@Override
	public List<LaborLawCase> getCaseBeforeDate(String dateString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LaborLawCase> getCaseAfterDate(String dateString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LaborLawCase> getCaseByDate(String dateString1, String daString2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(int fine, int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LaborLawCase getCaseByDocno(String docno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LaborLawCase> getCaseByStatement(String statement) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




}
