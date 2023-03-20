package main.store;

public interface StoreEvent {
	
	void inputSkillInfo(int index, SkillPanel skill);
	void updateLevelToDB();
	void payCiga(SkillPanel skillPanel);
}
