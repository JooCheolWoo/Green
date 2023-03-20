public class BookList {
	private Guest[] guestList = new Guest[31];
	
	public BookList(Guest[] guestList) {
		super();
		this.guestList = guestList;
	}

	public BookList() {
		
	}

	public Guest[] getGuestList() {
		return guestList;
	}

	public void setGuestList(Guest guest) {
		for (int i = 0; i < 31; i++) {
			if (guestList[i] == null) {
				guestList[i] = guest;
				break;
			}
		}
	}
	
	public void resetGuestList(int tempK) {
		guestList[tempK] = null;
	}
}