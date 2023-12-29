package be.mvpee.Command.admin;

import be.mvpee.manager.MoneyManager;
import be.mvpee.manager.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class MoneyGiveCommand implements CommandExecutor {

    private final MoneyManager moneyManager;
    private PermissionsManager permissionsManager;

    public MoneyGiveCommand(MoneyManager moneyManager, PermissionsManager permissionsManager) {

        this.moneyManager = moneyManager;
        this.permissionsManager = permissionsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || permissionsManager.getPermissions(player.getName()) >= 3) {
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null && target.isOnline()) {
                        moneyManager.setMoney(args[0], Integer.parseInt(args[1]));
                        player.sendMessage(ChatColor.GREEN + "You give " + args[1] + "$ to " + args[0] + ".");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "This player is offline.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /m <pseudo> <amount>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You don't have the permission for this command.");
            }
        }
        return false;
    }

}
