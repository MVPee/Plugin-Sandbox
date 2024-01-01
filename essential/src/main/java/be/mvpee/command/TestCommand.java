package be.mvpee.command;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private PermissionsManager perm;

    public TestCommand(PermissionsManager perm) {
        this.perm = perm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (perm.hasPerm(player, 3)) {
            player.sendMessage("test");
        }
        return false;
    }
}
