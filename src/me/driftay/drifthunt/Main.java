package me.driftay.drifthunt;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Main extends JavaPlugin {
    ConsoleCommandSender Console = this.getServer().getConsoleSender();
    public File lang = new File(this.getDataFolder() + "/lang.yml");
    public FileConfiguration langcfg;

    public Main() {
        this.langcfg = YamlConfiguration.loadConfiguration(this.lang);
    }

    public void onEnable() {
        this.Console.sendMessage("§8§l§m====================================================");
        this.Console.sendMessage("                       §b§lDrift§cHunt+");
        this.Console.sendMessage(" ");
        this.Console.sendMessage("        §7§lStatus: §a§lEnabled");
        this.Console.sendMessage("        §7§lAuthor: §a§lDriftay");
        this.Console.sendMessage("        §7§lVersion: §a§l1.0");
        this.Console.sendMessage(" ");
        this.Console.sendMessage("§8§l§m====================================================");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.saveDefaultLang();
        this.getCommand("dhunt").setExecutor(new DriftHuntCMD(this));
        this.getServer().getPluginManager().registerEvents(new DriftHuntListener(this), this);

    }


    public void onDisable(){
        this.Console.sendMessage("§8§l§m====================================================");
        this.Console.sendMessage("                       §b§lDrift§cHunt+");
        this.Console.sendMessage(" ");
        this.Console.sendMessage("        §7§lStatus: §4§lDisabled");
        this.Console.sendMessage("        §7§lAuthor: §4§lDriftay");
        this.Console.sendMessage("        §7§lVersion: §4§l1.0");
        this.Console.sendMessage(" ");
        this.Console.sendMessage("§8§l§m====================================================");

    }





    /*
    For Lang.yml Setup
     */

    public FileConfiguration getLang() {
        if (this.langcfg == null) {
            this.reloadLang();
        }

        return this.langcfg;
    }

    public void resetLang() {
        if (this.lang == null) {
            this.lang = new File(this.getDataFolder(), "/lang.yml");
        }

        this.langcfg = YamlConfiguration.loadConfiguration(this.lang);
        this.lang.delete();
        this.saveDefaultLang();
        this.reloadLang();
    }

    public void savelang() {
        if (this.langcfg != null && this.lang != null) {
            try {
                this.getLang().save(this.lang);
            } catch (IOException oof) {

            }

        }
    }

    public void reloadLang() {
        if (this.lang == null) {
            this.lang = new File(this.getDataFolder(), "/lang.yml");
        }

        this.langcfg = YamlConfiguration.loadConfiguration(this.lang);

        try {
            InputStreamReader defConfigStream = new InputStreamReader(this.getResource("lang.yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.langcfg.setDefaults(defConfig);
            }
        } catch (UnsupportedEncodingException nope) {
            nope.printStackTrace();
        }

    }

    public void saveDefaultLang() {
        if (this.lang == null) {
            this.lang = new File(this.getDataFolder(), "/lang.yml");
        }

        if (!this.lang.exists()) {
            this.saveResource("lang.yml", false);
        }

    }


}