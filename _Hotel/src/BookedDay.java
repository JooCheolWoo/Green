public class BookedDay {
	boolean[][] bookedDay = new boolean[2][31];

	public void bookOn(int afterDay, int stayDay) {
		if (afterDay + stayDay > 30) {
			for (int i = afterDay; i < 31; i++) {
				bookedDay[0][i] = true;
			}
			for (int i = 0; i < afterDay + stayDay - 31; i++) {
				bookedDay[1][i]	= true;		
			}
		} else {
			for (int i = afterDay; i < afterDay + stayDay; i++) {
				bookedDay[0][i] = true;
			}
		}
	}
	
	public void bookOff(int afterDay, int stayDay) {
		if (afterDay + stayDay > 30) {
			for (int i = afterDay; i < 31; i++) {
				bookedDay[0][i] = false;
			}
			for (int i = 0; i < afterDay + stayDay - 31; i++) {
				bookedDay[1][i]	= false;		
			}
		} else {
			for (int i = afterDay; i < afterDay + stayDay; i++) {
				bookedDay[0][i] = false;
			}
		}
	}
	
	public boolean checkDay(int afterDay, int stayDay) {
		if (afterDay + stayDay > 30) {
			for (int i = afterDay; i < 31; i++) {
				if (bookedDay[0][i]) {
					return false;
				}
			}
			for (int i = 0; i < afterDay + stayDay - 31; i++) {
				if (bookedDay[1][i]) {
					return false;
				}
			}
		} else {
			for (int i = afterDay; i < afterDay + stayDay; i++) {
				if (bookedDay[0][i]) {
					return false;
				}
			}
		}		
		return true;
	}
	
	public void dayPass() {
		boolean[][] temp = new boolean[1][1];
		temp[0][0] = bookedDay[1][0];
		for (int i = 1; i >= 0; i--) {
			for (int j = 29; j >=0; j--) {
				bookedDay[i][j] = bookedDay[i][j+1];
			}
		}
		bookedDay[1][30] = false;
		bookedDay[0][30] = temp[0][0];
	}
}