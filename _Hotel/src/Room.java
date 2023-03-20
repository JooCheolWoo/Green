public class Room {
	private int roomNum;
	private String type;
	private int capacity;
	private int price;
	private BookList booklist = new BookList();
	private BookedDay bookedDay = new BookedDay();
	private Guest guest = new Guest();
	private Admin admin = new Admin();
	
	public Room(int roomNum, String type, int capacity, int price) {
		super();
		this.roomNum = roomNum;
		this.type = type;
		this.capacity = capacity;
		this.price = price;
	}
	
	public Room() {
		
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public BookedDay getBookedDay() {
		return bookedDay;
	}

	public void setBookedDay(BookedDay bookedDay) {
		this.bookedDay = bookedDay;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public BookList getBooklist() {
		return booklist;
	}

	public void setBooklist(BookList booklist) {
		this.booklist = booklist;
	}

	@Override
	public String toString() {
		return "객실번호: " + roomNum + " || " + "타입: " + type + " || " + "투숙객: " + guest.getName() +  " || " 
				+ "입실인원: " + guest.getNumOfGuest() +  " || " + "남은 숙박일: " + guest.getStayDay() +  " || " + "담당자: " + admin.getName();
	}
}