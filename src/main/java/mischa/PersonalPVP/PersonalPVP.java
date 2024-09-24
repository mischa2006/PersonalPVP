package mischa.PersonalPVP;

import mischa.PersonalPVP.Commands.*;
import mischa.PersonalPVP.Listeners.OnDamage;
import mischa.PersonalPVP.Listeners.OnKill;
import org.bukkit.plugin.java.JavaPlugin;

public final class PersonalPVP extends JavaPlugin {

    private static PersonalPVP plugin;

    public static PersonalPVP getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        PersonalPVP.getPlugin().saveDefaultConfig();
        PersonalPVP.getPlugin().saveConfig();

        this.getCommand("togglepvp").setExecutor(new TogglePVP(getConfig()));
        this.getCommand("reloadPVP").setExecutor(new ReloadConfig());
        this.getCommand("pvphelp").setExecutor(new helppvp(getConfig()));
        this.getCommand("pvp").setExecutor(new getplayerpvp(getConfig()));


        getServer().getPluginManager().registerEvents(new OnDamage(getConfig()), this);
        getServer().getPluginManager().registerEvents(new OnKill(getConfig()), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
