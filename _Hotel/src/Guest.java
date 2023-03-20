public class Guest {
	private int roomNum;
	private String name;
	private String phone;
	private int afterDay;
	private int stayDay;
	private int numOfGuest;
	private int Breakfast;
	private int payment;
	private int Days;
	private Admin admin = new Admin();
	
	public Guest(int roomNum, String name, String phone, int afterDay, int stayDay, int numOfGuest, int breakfast,
			int payment, Admin admin) {
		this.roomNum = roomNum;
		this.name = name;
		this.phone = phone;
		this.afterDay = afterDay;
		this.stayDay = stayDay;
		this.numOfGuest = numOfGuest;
		Breakfast = breakfast;
		this.payment = payment;
		this.admin = admin;
		this.Days = stayDay;
	}

	public Guest() {
		
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAfterDay() {
		return afterDay;
	}

	public void setAfterDay(int afterDay) {
		this.afterDay = afterDay;
	}

	public int getStayDay() {
		return stayDay;
	}

	public void setStayDay(int stayDay) {
		this.stayDay = stayDay;
	}

	public int getNumOfGuest() {
		return numOfGuest;
	}

	public void setNumOfGuest(int numOfGuest) {
		this.numOfGuest = numOfGuest;
	}

	public int getBreakfast() {
		return Breakfast;
	}

	public void setBreakfast(int breakfast) {
		Breakfast = breakfast;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	public int getDays() {
		return Days;
	}

	public void setDays(int days) {
		Days = days;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "객실번호: " + roomNum + " || " + "예약자 이름: " + name + " || " + "연락처: " + phone + " || " 
				+ "몇일 후: " + afterDay + " || " + "숙박일: " + stayDay + " || " + "담당자: " + admin.getName();
	}
}