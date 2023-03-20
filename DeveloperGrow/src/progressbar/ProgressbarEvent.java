package progressbar;

import java.util.Timer;
import java.util.TimerTask;

import main.DeathDialog;
import main.MainFrame;

public class ProgressbarEvent {
	
	MainFrame mainFrame;
	Timer timeHp = null;
	Timer timeStress = null;
	Timer timeHealth = null;
	
	private int gameSpeed;
	private int hp;
	private int stress;
	private int health;
	private TimerTask hpTask;

	public ProgressbarEvent(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public void controllPB_default() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
					
		int speedHp = 3000 + (level * 20) + (energydrink * 50) + (mac * 50);
		int speedStress = 1000 + (level * 20) + (mac * 50);
		int speedHealth = 2000 + (level * 20) + (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarDecreas(speedHp);
		stressbarIncrease(speedStress);
		healthbarDecreas(speedHealth);
	}
	
	public void controllPB_ciga() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
		
		int speedHp = 2500 + (level * 20) + (energydrink * 50) + (mac * 50);
		int speedStress = 500 - (level * 20) - (mac * 50) - (elecciga * 10);
		int speedHealth = 1300 + (level * 20) + (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarDecreas(speedHp);
		stressbarDecreas(speedStress);
		healthbarDecreas(speedHealth);
	}
	
	public void controllPB_coupang() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
								
		int speedHp = 2000 + (level * 20) + (energydrink * 50) + (mac * 50);
		int speedStress = 670 + (level * 20) + (mac * 50);
		int speedHealth = 1340 + (level * 20) + (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarDecreas(speedHp);
		stressbarIncrease(speedStress);
		healthbarDecreas(speedHealth);
	}
	
	public void controllPB_game() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
					
		int speedHp = 2300 + (level * 20) + (energydrink * 50) + (mac * 50);
		int speedStress = 2000 - (level * 20) - (mac * 50) - (computer * 50);
		int speedHealth = 1500 + (level * 20) + (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarDecreas(speedHp);
		stressbarDecreas(speedStress);
		healthbarDecreas(speedHealth);
	}
	
	public void controllPB_sleep() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
					
		int speedHp = 2500 - (level * 20) - (energydrink * 50) - (mac * 50) - (bed * 20);
		int speedStress = 1500 - (level * 20) - (mac * 50) - (bed * 20);
		int speedHealth = 2000 - (level * 20) - (mac * 50) - (bed * 20);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarIncrease(speedHp);
		stressbarDecreas(speedStress);
		healthbarIncrease(speedHealth);
	}
	
	public void controllPB_eat() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
					
		int speedHp = 2500 - (level * 20) - (energydrink * 50) - (mac * 50);
		int speedStress = 2500 - (level * 20) - (mac * 50);
		int speedHealth = 1500 - (level * 20) - (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarIncrease(speedHp);
		stressbarDecreas(speedStress);
		healthbarIncrease(speedHealth);
	}
	
	public void controllPB_resume() {
		int level = mainFrame.getUserInfo().getLevel();
		int bed = mainFrame.getUserSkillList().get(0).getSkillLevel();
		int energydrink = mainFrame.getUserSkillList().get(1).getSkillLevel();
		int computer = mainFrame.getUserSkillList().get(2).getSkillLevel();
		int elecciga = mainFrame.getUserSkillList().get(3).getSkillLevel();
		int mac = mainFrame.getUserSkillList().get(4).getSkillLevel();
						
		int speedHp = 2500 + (level * 20) + (energydrink * 50) + (mac * 50);
		int speedStress = 700 + (level * 20) + (mac * 50);
		int speedHealth = 1300 + (level * 20) + (mac * 50);
		
		if (speedHp < 10) {
			speedHp = 10;
		}
		if (speedStress < 10) {
			speedStress = 10;
		}
		if (speedHealth < 10) {
			speedHealth = 10;
		}
		
		hpbarDecreas(speedHp);
		stressbarIncrease(speedStress);
		healthbarDecreas(speedHealth);
	}
	

	private void hpbarDecreas(int speed) {
		if (timeHp != null) {
			timeHp.cancel();
		}
		timeHp = new Timer();
		hp = mainFrame.getHpbar().getValue();
		hpTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo()) {
					if (hp <= 0) {
						DeathDialog deathDialog = new DeathDialog(mainFrame.getX(), mainFrame.getY(), mainFrame);
						deathDialog.showGUI();
					} else if (hp > 0 && mainFrame.getHealthbar().getValue() >= 20) {
						hp--;
					} else if (hp > 0 && mainFrame.getHealthbar().getValue() < 20) {
						hp--;
						hp--;
					}
					mainFrame.getHpbar().setValue(hp);
				}
				try {
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(speed / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeHp.scheduleAtFixedRate(hpTask, 0, 1);		
	}
	
	private void hpbarIncrease(int speed) {
		if (timeHp != null) {
			timeHp.cancel();
		}
		timeHp = new Timer();
		hp = mainFrame.getHpbar().getValue();
		TimerTask hpTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo()) {
					if (hp == 0) {
						timeHp.cancel();
						DeathDialog deathDialog = new DeathDialog(mainFrame.getX(), mainFrame.getY(), mainFrame);
						deathDialog.showGUI();
					}
					if (hp < 100 && mainFrame.getHealthbar().getValue() >= 80){
						hp++;
						hp++;
					} else if (hp < 100 && mainFrame.getHealthbar().getValue() < 80){
						hp++;
					}
					mainFrame.getHpbar().setValue(hp);
				}
				try {
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(speed / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeHp.scheduleAtFixedRate(hpTask, 0, 1);		
	}	
	
	private void stressbarDecreas(int speed) {
		if (timeStress != null) {
			timeStress.cancel();
		}
		timeStress = new Timer();
		stress = mainFrame.getStessbar().getValue();
		TimerTask stressTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo()) {
					if (stress == 0) {
						timeStress.cancel();
					} else if (stress > 0) {
						stress--;
					}
					mainFrame.getStessbar().setValue(stress);
				}
				try {
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(speed / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeStress.scheduleAtFixedRate(stressTask, 0, 1);		
	}
	
	private void stressbarIncrease(int speed) {
		if (timeStress != null) {
			timeStress.cancel();
		}
		timeStress = new Timer();
		stress = mainFrame.getStessbar().getValue();
		TimerTask stressTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo()) {
					if (stress < 100) {
						stress++;
					}
					mainFrame.getStessbar().setValue(stress);
				}
				try {
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(speed / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeStress.scheduleAtFixedRate(stressTask, 0, 1);		
	}		
	
	private void healthbarDecreas(int speed) {
		if (timeHealth != null) {
			timeHealth.cancel();
		}
		timeHealth = new Timer();
		health = mainFrame.getHealthbar().getValue();
		TimerTask healthTask = new TimerTask() {
			@Override
			public void run() {
				if (mainFrame.isTimeGo()) {
					if (health == 0) {
						timeHealth.cancel();
					} else if (health > 0 && mainFrame.getStressbar().getValue() >= 80) {
						health--;
						health--;
					} else if (health > 0 && mainFrame.getStressbar().getValue() < 80) {
						health--;
					}
					mainFrame.getHealthbar().setValue(health);
				}
				try {
					int gameSpeed = mainFrame.getGameSpeed();
					Thread.sleep(speed / gameSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeHealth.scheduleAtFixedRate(healthTask, 0, 1);		
	}
	
	private void healthbarIncrease(int speed) {
		if (timeHealth != null) {
			timeHealth.cancel();
		}
		timeHealth = new Timer();
		health = mainFrame.getHealthbar().getValue();
		TimerTask healthTask = new TimerTask() {
			@Override
			public void run() {
					if (mainFrame.isTimeGo()) {
						if (health < 100 && mainFrame.getStressbar().getValue() <= 20) {
							health++;
							health++;
						} else if (health < 100) {
							health++;
						}
						mainFrame.getHealthbar().setValue(health);
					}
					try {
						int gameSpeed = mainFrame.getGameSpeed();
						Thread.sleep(speed / gameSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		};
		timeHealth.scheduleAtFixedRate(healthTask, 0, 1);		
	}

	public Timer getTimeHp() {
		return timeHp;
	}

	public Timer getTimeStress() {
		return timeStress;
	}

	public Timer getTimeHealth() {
		return timeHealth;
	}	
	
	
}