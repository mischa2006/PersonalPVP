package mischa.PersonalPVP.Commands;

import mischa.PersonalPVP.PersonalPVP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        PersonalPVP.getPlugin().saveDefaultConfig();
        PersonalPVP.getPlugin().saveConfig();
        sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
        return true;
    }
}
