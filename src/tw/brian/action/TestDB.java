package tw.brian.action;

import java.sql.Connection;
import java.sql.SQLException;

import tw.brian.model.GenderLawCase;
import tw.brian.model.GenderLawService;
import tw.brian.model.GenderLawSqlServerDao;
import tw.brian.model.LaborLawCase;
import tw.brian.model.LaborLawService;
import tw.brian.model.LaborLawSqlServerDao;
import tw.brian.util.ConnectionFactory;

public class TestDB {
	public static void main(String[] args)  {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.createSQLServerConnection();

//		LaborLawService service = new LaborLawService(new LaborLawSqlServerDao(conn));
		GenderLawService service = new GenderLawService(new GenderLawSqlServerDao(conn));
		
		try {
//			service.insertData(CSVUtil.getCSVLabor("C:\\JDBC\\JavaWorkspace\\filter\\data\\labor\\tp_labor111.csv"));
			
			int count = service.queryAllData().size();
			for(GenderLawCase lawCase:service.queryAllData()){
				System.out.println(lawCase);
			}
			System.out.println("共"+count+"筆資料");
			
//			System.out.println(service.updateContentByDocno("職場性騷擾", "府勞條字第1060058540號函"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.closeConnection(conn);
		}

	}
}