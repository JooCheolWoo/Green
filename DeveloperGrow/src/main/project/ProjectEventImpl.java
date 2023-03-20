package main.project;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import database.dblist.UserProject;
import main.DeathDialog;
import main.MainFrame;

public class ProjectEventImpl implements ProjectEvent {
	
	private MainFrame mainFrame;
	private int minutes;
	private int hours;
	private double rate;
	private Timer projectTimer = null;
	private int deadLine;
	
	public ProjectEventImpl(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public int searchNowProject(List<UserProject> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isProceeding() && !list.get(i).isComplete()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void projectTimeControll(int time, int index) {
		if (projectTimer != null) {
			projectTimer.cancel();
		}
		minutes = Integer.valueOf(mainFrame.getProjectMinute().getText());
		hours = Integer.valueOf(mainFrame.getProjectHour().getText());
		rate = Double.valueOf(mainFrame.getNowRatinglbl().getText());
		deadLine = mainFrame.getUserProjectList().get(index).getDeadLine();
		mainFrame.getProjectDeadLine().setText(String.valueOf(deadLine));
		projectTimer = new Timer();
	    TimerTask projcetTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo() && mainFrame.isProjectGo()) {
					if (hours == 0 && minutes == 0) {
						projectTimer.cancel();
					} else if (minutes == 0) {
						hours--;
						minutes = 59;
					} else {
						minutes--;
					}
					rate = 100 - ((double)(hours * 60 + minutes) / (time * 60)) * 100;
					updateTime(minutes, hours, rate);
				}
				try {
					if (mainFrame.isTimeGo()) {
						deadLine--;
						mainFrame.getProjectDeadLine().setText(String.valueOf(deadLine));
						mainFrame.getUserProjectList().get(index).setDeadLine(deadLine);
						mainFrame.revalidate();
						mainFrame.repaint();
						if (deadLine <= 0) {
							DeathDialog deathDialog = new DeathDialog(mainFrame.getX(), mainFrame.getY(), mainFrame);
							deathDialog.showGUI();
						}
					}
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(50 / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    };
		projectTimer.scheduleAtFixedRate(projcetTask, 0, 1);
	}
	
	private void updateTime(int minutes, int hours, double rate) {
		mainFrame.getProjectHour().setText(String.format("%02d", hours));
		mainFrame.getProjectMinute().setText(String.format("%02d", minutes));
		if (rate >= 100.0) {
			mainFrame.getNowRatinglbl().setText("완료");
			projectTimer.cancel();
			int index = mainFrame.getNowProjectId();
			if (index == mainFrame.getProjectList().size() - 1) {
				mainFrame.getTaskbtn().setEnabled(false);
			}
			mainFrame.getUserProjectList().get(index).setComplete(true);
			mainFrame.getNowRatinglbl().setText("완료!!");
			expProgressBar(mainFrame.getProjectList().get(index).getRewardExp());
			mainFrame.revalidate();
			mainFrame.repaint();
		} else {
			mainFrame.getNowRatinglbl().setText(String.format("%.02f", rate));
		}
		mainFrame.revalidate();
		mainFrame.repaint();		
    }
	
	public void expProgressBar(int addExp) {
		int levelExp = mainFrame.getUserInfo().getLevel() * 100;
		int charExp = mainFrame.getExpbar().getValue();
		int input = (int) (charExp + (((double) addExp / (double) levelExp) * 100));
		if (input >= 100) {
			int level = Integer.valueOf(mainFrame.getLevellbl().getText());
			mainFrame.getLevellbl().setText(String.valueOf(level + 1));
			mainFrame.getUserInfo().setLevel(level + 1);
			mainFrame.getUserInfo().setCiga(mainFrame.getUserInfo().getCiga() + 10);
			mainFrame.getNumOfcigalbl().setText(String.valueOf(mainFrame.getUserInfo().getCiga()));
			int hp = mainFrame.getHpbar().getValue() + 30;
			int stress = mainFrame.getStessbar().getValue() - 30;
			int health = mainFrame.getHealthbar().getValue() + 30;
			if (hp >= 100) {
				mainFrame.getHpbar().setValue(100);
			} else {
				mainFrame.getHpbar().setValue(hp);
			}
			if (stress <= 100) {
				mainFrame.getStressbar().setValue(0);
			} else {
				mainFrame.getStressbar().setValue(stress);
			}
			if (health >= 100) {
				mainFrame.getHealthbar().setValue(100);
			} else {
				mainFrame.getHealthbar().setValue(health);
			}
			
			double a = ((double) level / (double) (level + 1));
			input = (int) ((input - 100) * a);
			mainFrame.getExpbar().setValue(input);
		} else {
			mainFrame.getExpbar().setValue(input);
		}
	}

	public Timer getProjectTimer() {
		return projectTimer;
	}
}
