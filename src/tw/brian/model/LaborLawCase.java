package tw.brian.model;

import java.sql.Date;

import org.apache.commons.csv.CSVRecord;

import tw.brian.exception.RecordListNullException;
import tw.brian.util.LocalDateutil;
import tw.brian.util.NumberUtil;

/**
 * 勞基法案件javaBean
 * 
 * @author 88693
 *
 */
public class LaborLawCase {
	private Integer id;
	private Date punishDate;
	private String docno;
	private String enterprise;
	private String statement;
	private String content;
	private int fine;

	public LaborLawCase() {

	}

	public LaborLawCase(Date punishDate, String docno, String enterprise, String statement, String content, int fine) {
		this(null, punishDate, docno, enterprise, statement, content, fine);
	}

	public LaborLawCase(Integer id, Date punishDate, String docno, String enterprise, String statement, String content,
			int fine) {
		this.id = id;
		this.punishDate = punishDate;
		this.docno = docno;
		this.enterprise = enterprise;
		this.statement = statement;
		this.content = content;
		this.fine = fine;
	}

	public LaborLawCase(CSVRecord record) throws RecordListNullException {
		if (record != null) {
			this.punishDate = Date.valueOf(LocalDateutil.parseLocalDate(record.get("處分日期")).get());
			this.docno = record.get("處分書文號");
			this.enterprise = record.get("事業單位名稱");
			this.statement = record.get("違反法令條");
			this.content = record.get("違反法規內容");
			this.fine = (NumberUtil.parseFine(record.get("罰鍰金額")).get());
		} else {
			throw new RecordListNullException("List<CSVRecord>是null !");
		}
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public Integer getid() {
		return id;
	}

	public Date getPunishDate() {
		return punishDate;
	}

	public void setPunishDate(Date punishDate) {
		this.punishDate = punishDate;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=");
		builder.append(id);
		builder.append(", punishDate=");
		builder.append(punishDate);
		builder.append(", docno=");
		builder.append(docno);
		builder.append(", enterprise=");
		builder.append(enterprise);
		builder.append(", statement=");
		builder.append(statement);
		builder.append(", content=");
		builder.append(content);
		builder.append(", fine=");
		builder.append(fine);
		return builder.toString();
	}

}
