package com.mortuusterra.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class CustomScoreboards {

	private Scoreboard pkTeamsBoard;

	public CustomScoreboards() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();

		pkTeamsBoard = manager.getNewScoreboard();

		pkTeamsBoard.registerNewTeam("Neutral");
		pkTeamsBoard.registerNewTeam("Orange");
		pkTeamsBoard.registerNewTeam("Red");

		pkTeamsBoard.getTeam("Neutral").setPrefix(PKStates.NEUTRAL.getColor() + "");
		pkTeamsBoard.getTeam("Orange").setPrefix(PKStates.ORANGE.getColor() + "");
		pkTeamsBoard.getTeam("Red").setPrefix(PKStates.RED.getColor() + "");

		for (Team team : pkTeamsBoard.getTeams()) {
			team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		}
	}
	
	public boolean isHostile(Player p) {
		if (pkTeamsBoard.getTeam("Orange").hasEntry(p.getName()) ||
			pkTeamsBoard.getTeam("Red").hasEntry(p.getName())) 
			return true;
		return false;
	}

	public void switchTeam(Player p, String newTeam) {
		for (Team team : pkTeamsBoard.getTeams()) {
			if (team.hasEntry(p.getName()))
				team.removeEntry(p.getName());
		}
		addPlayer(p, newTeam);
	}

	public void addPlayer(Player p, String team) {
		if (!pkTeamsBoard.getTeam(team).hasEntry(p.getName())) {
			pkTeamsBoard.getTeam(team).addEntry(p.getName());
			p.setScoreboard(pkTeamsBoard);
		}
	}

	public void removePlayer(Player p, String team) {
		if (!pkTeamsBoard.getTeam(team).hasEntry(p.getName())) {
			pkTeamsBoard.getTeam(team).removeEntry(p.getName());
			p.setScoreboard(pkTeamsBoard);
		}
	}

	public Scoreboard getPkTeamsBoard() {
		return pkTeamsBoard;
	}

}
