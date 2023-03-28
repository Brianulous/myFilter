package tw.brian.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tw.brian.exception.LaborSQLException;
import tw.brian.util.LocalDateutil;

/**
 * @author 88693 增刪改查個別方法(非客戶端)
 */

public class GenderLawSqlServerDao implements GenderLawDao {
	private final Connection conn;

	public GenderLawSqlServerDao(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 新增性平法案件
	 */
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

	/**
	 * 新增性平法案件
	 */
	@Override
	public void addGenderLaw(Date punishDate, String docno, String enterprise, String statement, String content)
			throws SQLException {
		addGenderLaw(new GenderLawCase(punishDate, docno, enterprise, statement, content));
	}

	/**
	 * 以企業名稱查詢最久案件
	 */
	@Override
	public GenderLawCase getOldestCaseByEnterprise(String enterprise) throws SQLException {
		String sql = "select * from GenderCase " + "where enterprise = ? and punish_date = "
				+ "(select min(punish_date) from GenderCase where enterprise =?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, enterprise);
		preState.setString(2, enterprise);
		ResultSet rs = preState.executeQuery();
		if (!rs.next()) {
			throw new SQLException("查無相關性平法案件資料");
		}
		GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
														rs.getDate("punish_date"),
														rs.getString("docno"),
														rs.getString("enterprise"),
														rs.getString("statement"),
														rs.getString("content"));
		preState.close();
		return genderLawCase;
	}

	/**
	 * 以企業名稱查詢最新案件
	 */
	@Override
	public GenderLawCase getNewestCaseByEnterprise(String enterprise) throws SQLException {
		String sql = "select * from GenderCase " + "where enterprise = ? and punish_date = "
				+ "(select min(punish_date) from GenderCase where enterprise =?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, enterprise);
		preState.setString(2, enterprise);
		ResultSet rs = preState.executeQuery();
		if (!rs.next()) {
			throw new SQLException("查無相關性平法案件資料");
		}
		GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
														rs.getDate("punish_date"),
														rs.getString("docno"),
														rs.getString("enterprise"),
														rs.getString("statement"),
														rs.getString("content"));
		preState.close();
		return genderLawCase;

	}

	/**
	 * 處分文書查詢
	 */
	@Override
	public GenderLawCase getCaseByDocno(String docno) throws SQLException {
		String sql = "select * from GenderCase " + "where docno = ? )";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, docno);
		ResultSet rs = preState.executeQuery();
		if (!rs.next()) {
			throw new LaborSQLException("查無相關勞基法案件資料");
		}
		GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
														rs.getDate("punish_date"),
														rs.getString("docno"),
														rs.getString("enterprise"),
														rs.getString("statement"),
														rs.getString("content"));
		preState.close();
		return genderLawCase;
	}

	/**
	 * 以法條查詢
	 */
	@Override
	public List<GenderLawCase> getCaseByStatement(String statement) throws SQLException {
		String sql = "select * from GenderCase where statement = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, statement);
		ResultSet rs = preState.executeQuery();

		List<GenderLawCase> lawCases = new ArrayList<>();
		while (rs.next()) {
			GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
															rs.getDate("punish_date"),
															rs.getString("docno"),
															rs.getString("enterprise"),
															rs.getString("statement"),
															rs.getString("content"));

			lawCases.add(genderLawCase);
		}
		if (lawCases.isEmpty()) {
			throw new LaborSQLException("查無相關日期資料");
		}
		return lawCases;
	}

	/**
	 * 日期前查詢
	 */
	@Override
	public List<GenderLawCase> getCaseBeforeDate(String dateString) throws SQLException {
		Date date = Date.valueOf(LocalDateutil.parseLocalDate(dateString).get());
		String sql = "select * from GenderCase where punish_date<?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date);
		ResultSet rs = preState.executeQuery();

		List<GenderLawCase> lawCases = new ArrayList<>();
		while (rs.next()) {
			GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
															rs.getDate("punish_date"),
															rs.getString("docno"),
															rs.getString("enterprise"),
															rs.getString("statement"),
															rs.getString("content"));

			lawCases.add(genderLawCase);
		}
		if (lawCases.isEmpty()) {
			throw new SQLException("查無相關日期資料");
		}

		return lawCases;
	}

	/**
	 * 日期後查詢
	 */
	@Override
	public List<GenderLawCase> getCaseAfterDate(String dateString) throws SQLException {
		Date date = Date.valueOf(LocalDateutil.parseLocalDate(dateString).get());
		String sql = "select * from GenderCase where punish_date>?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date);
		ResultSet rs = preState.executeQuery();

		List<GenderLawCase> lawCases = new ArrayList<>();
		while (rs.next()) {
			GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
															rs.getDate("punish_date"),
															rs.getString("docno"),
															rs.getString("enterprise"),
															rs.getString("statement"),
															rs.getString("content"));

			lawCases.add(genderLawCase);
		}
		if (lawCases.isEmpty()) {
			throw new SQLException("查無相關日期資料");
		}

		return lawCases;
	}

	/**
	 * 日期間查詢
	 */
	@Override
	public List<GenderLawCase> getCaseByDate(String dateString1, String dateString2) throws SQLException {
		Date date1 = Date.valueOf(LocalDateutil.parseLocalDate(dateString1).get());
		Date date2 = Date.valueOf(LocalDateutil.parseLocalDate(dateString2).get());
		String sql = "select * from GenderCase where punish_date between ? and ? ";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date1);
		preState.setDate(2, date2);
		ResultSet rs = preState.executeQuery();

		List<GenderLawCase> lawCases = new ArrayList<>();
		while (rs.next()) {
			GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
															rs.getDate("punish_date"),
															rs.getString("docno"),
															rs.getString("enterprise"),
															rs.getString("statement"),
															rs.getString("content"));

			lawCases.add(genderLawCase);
		}
		if (lawCases.isEmpty()) {
			throw new SQLException("查無相關日期資料");
		}

		return lawCases;
	}

	/**
	 * 查詢全部資料
	 */
	@Override
	public List<GenderLawCase> getAllGenderLawCases() throws SQLException {
		String sql = "select * from GenderCase;";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);

		List<GenderLawCase> lawCases = new ArrayList<>();
		while (rs.next()) {
			GenderLawCase genderLawCase = new GenderLawCase(rs.getInt("id"),
															rs.getDate("punish_date"),
															rs.getString("docno"),
															rs.getString("enterprise"), 
															rs.getString("statement"),
															rs.getString("content"));

			lawCases.add(genderLawCase);
		}
		if (lawCases.isEmpty()) {
			throw new SQLException("查無相關日期資料");
		}

		return lawCases;
	}

	/**
	 * 用id更改資料
	 */
	@Override
	public int updateById(String dString, int id) throws SQLException {
		String sql = "update GenderCase set punish_date = ? where id =?;";
		Date date = Date.valueOf(LocalDateutil.parseLocalDate(dString).get());
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date);
		preState.setInt(2, id);
		int rows = preState.executeUpdate();
		if (rows == 0) {
			throw new LaborSQLException("未更改成功");
		} else {
			return rows;
		}

	}

	/**
	 * 用docno更改content
	 */
	@Override
	public int updateContentByDocno(String content, String docno) throws SQLException {
		String sql = "update GenderCase set content = ? where docno =?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, content);
		preState.setString(2, docno);
		int rows = preState.executeUpdate();
		if (rows == 0) {
			throw new LaborSQLException("未更改成功");
		} else {
			return rows;
		}

	}

	/**
	 * 用id刪除資料
	 */
	@Override
	public int deleteById(int id) throws SQLException {
		String sql = "delete GenderCase where id =?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		int rows = preState.executeUpdate();
		if (rows == 0) {
			throw new LaborSQLException("未刪除成功");
		} else {
			return rows;
		}

	}

}
