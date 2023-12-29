package be.mvpee.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager implements Listener {

    private final MoneyManager moneyManager;
    private final PermissionsManager permissionsManager;
    private final PlayerManager playerManager;
    private final JavaPlugin plugin;

    public ScoreboardManager(JavaPlugin plugin, PlayerManager playerManager, MoneyManager moneyManager, PermissionsManager permissionsManager) {
        this.plugin = plugin;
        this.playerManager = playerManager;
        this.moneyManager = moneyManager;
        this.permissionsManager = permissionsManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            setupScoreboard(e.getPlayer());
        }, 0, 60);
    }

    public void setupScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Testboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "  MVPee");

        Score website = obj.getScore(ChatColor.GOLD + "play.mvpee.be");
        website.setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Score money = obj.getScore(ChatColor.DARK_GRAY + "Balance: " + ChatColor.WHITE + moneyManager.getMoney(player.getName()));
        money.setScore(3);

        Score space2 = obj.getScore("  ");
        space2.setScore(4);

        int code = permissionsManager.getPermissions(player.getName());
        ChatColor codeColor = playerManager.getColorWithCode(code);

        Score perm = obj.getScore(ChatColor.GRAY.toString() + ChatColor.BOLD + "Rank: " + codeColor + playerManager.getNameWithCode(permissionsManager.getPermissions(player.getName())));
        perm.setScore(5);

        Score name = obj.getScore(ChatColor.RED.toString() + ChatColor.BOLD + "Name: " + player.getName());
        name.setScore(6);

        Score space3 = obj.getScore("   ");
        space3.setScore(7);

        player.setScoreboard(board);
    }
}