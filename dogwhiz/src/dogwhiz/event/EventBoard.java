package dogwhiz.event;

import java.time.LocalDate;

import dogwhiz.model.Board;

public class EventBoard extends Board {
	private LocalDate startDate;
	private LocalDate endDate;
	
	
    public EventBoard() {
        this.category = "이벤트";
        this.writer = "관리자";
    }
	
	public EventBoard(String title, String content, LocalDate startDate, LocalDate endDate) {
		super(title, content, "관리자");
		this.category = "이벤트";
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
