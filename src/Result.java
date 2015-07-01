import java.sql.Date;

class Result {

	String exam_name = null;
	Date exam_date = null;
	String userid = null;

	int marks;
	String result = null;

	public Result(String exam_name, Date exam_date, String userid, int marks,
			String result) {
		this.exam_name = exam_name;
		this.exam_date = exam_date;
		this.userid = userid;
		this.marks = marks;
		this.result = result;

	}

	public String getExamName() {
		return exam_name;
	}

	public Date getExamDate() {
		return exam_date;
	}

	public String getUserid() {
		return userid;
	}

	public int getMarks() {
		return marks;
	}

	public String getResult() {
		return result;
	}

}