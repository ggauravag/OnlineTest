class Question {

	int ques_no;
	String question = null;
	String op1 = null;

	String op2 = null;
	String op3 = null;
	String op4 = null;

	String correct = null;

	public Question(int ques_no, String question, String op1, String op2,
			String op3, String op4, String correct) {
		this.ques_no = ques_no;
		this.question = question;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.op4 = op4;
		this.correct = correct;

	}

	public int getQuesno() {
		return ques_no;
	}

	public String getQuestion() {
		return question;
	}

	public String getOp1() {
		return op1;
	}

	public String getOp2() {
		return op2;
	}

	public String getOp3() {
		return op3;
	}

	public String getOp4() {
		return op4;
	}

	public String getCorrect() {
		return correct;
	}

}