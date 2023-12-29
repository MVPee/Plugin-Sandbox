package be.mvpee.Command;

import be.mvpee.manager.MoneyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class MoneyCommand implements CommandExecutor {

    private MoneyManager moneyManager;

    public MoneyCommand(MoneyManager moneyManager) {
        this.moneyManager = moneyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int money = moneyManager.getMoney(player.getName());
            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "Your balance: " + ChatColor.WHITE + money);
        }
        return false;
    }

}
