package mischa.PersonalPVP.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class getplayerpvp implements CommandExecutor {

    private final FileConfiguration config;
    public getplayerpvp(FileConfiguration config){
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 1){
            if(commandSender instanceof Player player){
                Player targetPlayer = player.getServer().getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage("Player not found!");
                    return false;
                }
                boolean disbl = false;
                if(config.get(targetPlayer.getUniqueId() + ".pvpdisabled") != null){
                    if(!config.getBoolean(targetPlayer.getUniqueId() + ".pvpdisabled")){
                        disbl = true;
                    }
                }
                if(disbl){
                    player.sendMessage(ChatColor.YELLOW + targetPlayer.getDisplayName() + " has pvp on " + ChatColor.GREEN + "ENABLED");
                }else{
                    player.sendMessage(ChatColor.YELLOW + targetPlayer.getDisplayName() + " has pvp on " + ChatColor.RED + "DISABLED");
                }
            }
        }else{
            return false;
        }
        return true;
    }
}
