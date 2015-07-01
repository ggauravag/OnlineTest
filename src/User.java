class User {

	String userid = null;
	String branch = null;
	String fname = null;

	String lname = null;
	String uname = null;
	String mobile = null;

	String email = null;
	String address = null;
	String status_user = null;

	String status_admin = null;

	public User(String userid, String branch, String fname, String lname,
			String uname, String mobile, String email, String address,
			String status_user, String status_admin) {
		this.userid = userid;
		this.branch = branch;
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.status_user = status_user;
		this.status_admin = status_admin;
	}

	public String getUserid() {
		return userid;
	}

	public String getBranch() {
		return branch;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getUname() {
		return uname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getStatusUser() {
		return status_user;
	}

	public String getStatusAdmin() {
		return status_admin;
	}

}