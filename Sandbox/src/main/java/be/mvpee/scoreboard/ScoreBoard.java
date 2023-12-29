package be.mvpee.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoard implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("Testboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "  MVPee");

        Score website = obj.getScore(ChatColor.GOLD + "play.mvpee.be");
        website.setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Score name = obj.getScore(ChatColor.RED.toString() + ChatColor.RED + "Name: " + player.getName());
        name.setScore(3);

        Score space2 = obj.getScore("  ");
        space2.setScore(4);

        player.setScoreboard(board);
    }
}