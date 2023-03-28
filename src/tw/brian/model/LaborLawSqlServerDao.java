package tw.brian.model;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.brian.exception.LaborSQLException;
import tw.brian.util.LocalDateutil;

/**
 * @author 88693
 * 增刪改查個別功能(非客戶端)
 */
public class LaborLawSqlServerDao implements LaborLawDao {
	private final Connection conn;

	public LaborLawSqlServerDao(Connection conn) {
		this.conn = conn;
	}
/**
 * 新增資料
 */
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
		preState.setInt(6, lawCase.getFine());

		int rows = preState.executeUpdate();
		System.out.printf("改變了%d 筆資料%n", rows);

		preState.close();
	}

	@Override
	public void addLaborLaw(Date punishDate, String docno, String enterprise, String statement, String content,
			int fine) throws SQLException {
		addLaborLaw(new LaborLawCase(punishDate, docno, enterprise, statement, content, fine));

	}
/**
 * 以企業名稱查詢最新勞基案件(單筆)
 */
	@Override
	public LaborLawCase getNewestCaseByEnterprise(String enterprise) throws SQLException {
		String sql = "select * from LaborCase "+
					 "where enterprise = ? and punish_date = "+
					 "(select min(punish_date) from LaborCase where enterprise =?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, enterprise);
		preState.setString(2, enterprise);
		ResultSet rs = preState.executeQuery();
		if(!rs.next()) {
			throw new LaborSQLException("查無相關勞基法案件資料");
		}
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		preState.close();
		return laborLawCase;
	}
/**
 * 以企業名稱查詢最舊勞基案件(單筆)
 */
	@Override	
	public LaborLawCase getOldestCaseByEnterprise(String enterprise) throws SQLException {
		String sql = "select * from LaborCase "+
				 "where enterprise = ? and punish_date = "+
				 "(select max(punish_date) from LaborCase where enterprise =?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, enterprise);
		preState.setString(2, enterprise);
		ResultSet rs = preState.executeQuery();
		if(!rs.next()) {
			throw new LaborSQLException("查無企業勞基法案件資料");
		}
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		preState.close();
		return laborLawCase;
	}
	
/**
 * 以企業名稱查詢最高罰鍰金額案件(單筆)
 */
	@Override
	public LaborLawCase hightestFineCaseByEnt(String enterprise) throws SQLException {
		String sql = "select * from LaborCase where enterprise = ? and fine = (select max(fine) from LaborCase where enterprise =?);";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, enterprise);
		preState.setString(2, enterprise);
		ResultSet rs = preState.executeQuery();
		if(!rs.next()) {
			throw new LaborSQLException("查無企業勞基法案件資料");
		}
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		preState.close();
		return laborLawCase;
	}
	/**
	 * 處分文書號查詢案件(單筆)
	 */
	@Override
	public LaborLawCase getCaseByDocno(String docno) throws SQLException {
		String sql = "select * from LaborCase "+
					 "where docno = ? )";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, docno);
		ResultSet rs = preState.executeQuery();
		if(!rs.next()) {
			throw new LaborSQLException("查無相關勞基法案件資料");
		}
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		preState.close();
		return laborLawCase;
	}
	
	/**
	 * 以法條查詢
	 */
	@Override
    public List<LaborLawCase> getCaseByStatement(String statement) throws SQLException {
		String sql = "select * from LaborCase where statement = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, statement);
		ResultSet rs = preState.executeQuery();
		
		List<LaborLawCase>lawCases = new ArrayList<>();
		while(rs.next()) {
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		
		lawCases.add(laborLawCase);
		}
		if(lawCases.isEmpty()) {
			throw new LaborSQLException("查無相關日期資料");
		}
		
		return lawCases;
		
	}
	
	
	
	
	
	/**
	 * 查詢日期前案件
	 */
	@Override
    public List<LaborLawCase> getCaseBeforeDate(String dateString) throws SQLException {
		Date date = Date.valueOf(LocalDateutil.parseLocalDate(dateString).get());
		String sql = "select * from LaborCase where punish_date<?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date);
		ResultSet rs = preState.executeQuery();
		
		List<LaborLawCase>lawCases = new ArrayList<>();
		while(rs.next()) {
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		
		lawCases.add(laborLawCase);
		}
		if(lawCases.isEmpty()) {
			throw new LaborSQLException("查無相關日期資料");
		}
		
		return lawCases;
		
	}
    
    
    /**
     *  查詢日期後案件
     */
	@Override
    public List<LaborLawCase> getCaseAfterDate(String dateString) throws SQLException {
		Date date = Date.valueOf(LocalDateutil.parseLocalDate(dateString).get());
		String sql = "select * from LaborCase where punish_date>?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date);
		ResultSet rs = preState.executeQuery();
		
		List<LaborLawCase>lawCases = new ArrayList<>();
		while(rs.next()) {
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		
		lawCases.add(laborLawCase);
		}
		if(lawCases.isEmpty()) {
			throw new LaborSQLException("查無相關日期資料");
		}
		
		return lawCases;
		
	}
	
    /**
     *  查詢日期間案件
     */
	@Override
    public List<LaborLawCase> getCaseByDate(String dateString1,String dateString2) throws SQLException {
		Date date1 = Date.valueOf(LocalDateutil.parseLocalDate(dateString1).get());
		Date date2 = Date.valueOf(LocalDateutil.parseLocalDate(dateString2).get());
		
		String sql = "select * from LaborCase where punish_date between ? and ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setDate(1, date1);
		preState.setDate(2, date2);
		
		ResultSet rs = preState.executeQuery();
		
		List<LaborLawCase>lawCases = new ArrayList<>();
		while(rs.next()) {
		LaborLawCase laborLawCase = new LaborLawCase(rs.getInt("id"),
													 rs.getDate("punish_date"),
													 rs.getString("docno"),
													 rs.getString("enterprise"),
													 rs.getString("statement"), 
													 rs.getString("content"), 
													 rs.getInt("fine"));
		
		lawCases.add(laborLawCase);
		}
		if(lawCases.isEmpty()) {
			throw new LaborSQLException("查無相關日期資料");
		}
		
		return lawCases;
		
	}
	
	
	/**
	 * 用id更改資料
	 */
	@Override
	public int updateById(int fine, int id) throws SQLException {
		String sql = "update LaborCase set fine = ? where id =?;";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, fine);
		preState.setInt(2, id);
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
		String sql = "delete LaborCase swhere id =?;";
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
