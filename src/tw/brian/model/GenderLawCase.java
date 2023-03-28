package tw.brian.model;

import java.sql.Date;

import org.apache.commons.csv.CSVRecord;

import tw.brian.util.LocalDateutil;

/**
 * 性平法javaBean
 * @author 88693
 *
 */
public class GenderLawCase {
	private Integer id;
	private Date punishDate;
	private String docno;
	private String enterprise;
	private String statement;
	private String content;

	public GenderLawCase() {
	}
	
//overload能直接呼叫
	public GenderLawCase(Date punishDate, String docno, String enterprise, String statement, String content) {
		this(null, punishDate, docno, enterprise, statement, content);
	}
	
	public GenderLawCase(Integer id,Date punishDate, String docno, String enterprise, String statement, String content) {
		this.id = id;
		this.punishDate = punishDate;
		this.docno = docno;
		this.enterprise = enterprise;
		this.statement = statement;
		this.content = content;
	}


//CSVRecord -> GenderLawCase
	public GenderLawCase(CSVRecord record) {

		this.punishDate = Date.valueOf(LocalDateutil.parseLocalDate(record.get("處分日期")).get());
		this.docno = record.get("處分書文號");
		this.enterprise = record.get("事業單位名稱");
		this.statement = record.get("違反法令條");
		this.content = record.get("違反法規內容");
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
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return builder.toString();
	}



}
