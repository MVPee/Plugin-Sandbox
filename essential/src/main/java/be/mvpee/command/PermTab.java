package be.mvpee.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PermTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("set");
        }
        else if (args.length == 2) {
            for (Player target : Bukkit.getOnlinePlayers()) {
                results.add(target.getName());
            }
        }
        else if (args.length == 3) {
            results.add("Guest");
            results.add("Helper");
            results.add("Moderator");
            results.add("Admin");
            results.add("Owner");
        }
        return results;
    }

}
