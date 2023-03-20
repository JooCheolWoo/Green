package main.ranking;

import java.util.List;

import database.controllDB.SelectDB;
import database.dblist.Rank;
import database.dblist.RankerInfo;
import main.MainFrame;

public class RankEventImpl implements RankEvent{
	
	private MainFrame mainFrame;
	private List<Rank> userRankList;
	
	
	public RankEventImpl(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		userRankList = mainFrame.getUserRankList();
	}
	
	@Override
	public void inputRankData(int index, RankPanel rankPanel) {
		
		SelectDB selectDB = new SelectDB();
		RankerInfo rankerInfo;
		
		try {
			int userInfoId = userRankList.get(index).getUserinfoId();
			String nickName = userRankList.get(index).getNickname();
			int score = userRankList.get(index).getScore();
			
			rankerInfo = selectDB.searchRankerInfo(userInfoId, nickName);
			int date = rankerInfo.getDate();
			int time = rankerInfo.getTime();
			int hour = time / 60;
			int minutes = time % 60;
			int usedCiga = rankerInfo.getUsedCiga();
			
			rankPanel.getNickNameLabel().setText(nickName);
			rankPanel.getScoreLabel().setText(score + "점");
			rankPanel.getDateLabel().setText(date + "일차");
			rankPanel.getTimeLabel().setText(String.format("%02d", hour) + " : " + String.format("%02d", minutes));
			rankPanel.getUsedCigaLabel().setText(usedCiga + "개");
		} catch (IndexOutOfBoundsException e) {
			rankPanel.getNickNameLabel().setText("없음");
			rankPanel.getScoreLabel().setText("0점");
			rankPanel.getDateLabel().setText("없음");
			rankPanel.getTimeLabel().setText("00 : 00");
			rankPanel.getUsedCigaLabel().setText("0개");
		}
		
	}
}
