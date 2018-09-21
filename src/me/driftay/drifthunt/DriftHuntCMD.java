package me.driftay.drifthunt;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
/*
    Created by Driftay
        9/12

 */

    public class DriftHuntCMD implements CommandExecutor {
        private Main main;

        public DriftHuntCMD(Main main) {
            this.main = main;
        }


        private void ConsoleLabHuntChest(World world, int x, int y, int z, ConsoleCommandSender p) {
            world.getBlockAt(x, y + 1, z).setType(Material.CHEST);
            world.getBlockAt(x, y + 2, z).setType(Material.AIR);
            world.getBlockAt(x, y + 3, z).setType(Material.AIR);
            List<String> bcast1 = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Broadcast-Start"));
            this.main.getConfig().set("Drift-Hunt.chest.x", x);
            this.main.getConfig().set("Drift-Hunt.chest.y", y);
            this.main.getConfig().set("Drift-Hunt.chest.z", z);
            this.main.saveConfig();
        }

        private void PlayerLabHuntChest(World world, int x, int y, int z, Player p) {
            world.getBlockAt(x, y + 1, z).setType(Material.CHEST);
            world.getBlockAt(x, y + 2, z).setType(Material.AIR);
            world.getBlockAt(x, y + 3, z).setType(Material.AIR);
            List<String> bcast1 = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Broadcast-Start"));
            this.main.getConfig().set("Drift-Hunt.chest.x", x);
            this.main.getConfig().set("Drift-Hunt.chest.y", y);
            this.main.getConfig().set("Drift-Hunt.chest.z", z);
            this.main.saveConfig();
        }

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            int x;
            int y;
            int z;
            Random randomChest;
            int isNegative;
            int x1;
            int y1;
            int z1;
            World w;
            Block chest;
            if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender p = (ConsoleCommandSender)sender;
                if (cmd.getName().equalsIgnoreCase("dhunt")) {
                    if (args.length >= 1 && args.length <= 1) {
                        if (args.length == 1) {
                            if (args[0].equalsIgnoreCase("start")) {
                                if (p.hasPermission("drifthunt.use")) {
                                    x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                    y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                    z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                    if (x != 0 && y != 0 && z != 0) {
                                        List<String> alreadystarted = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Already-Started").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                    } else {
                                        randomChest = new Random();
                                        isNegative = randomChest.nextInt(2);
                                        x1 = randomChest.nextInt(this.main.getConfig().getInt("Drift-Hunt.WorldBorderSize"));
                                        if (isNegative == 1) {
                                            x1 *= -1;
                                        }

                                        y1 = randomChest.nextInt(150);
                                        isNegative = randomChest.nextInt(2);
                                        z1 = randomChest.nextInt(this.main.getConfig().getInt("Drift-Hunt.WorldBorderSize"));
                                        if (isNegative == 1) {
                                            z1 *= -1;
                                        }

                                        this.ConsoleLabHuntChest(Bukkit.getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld")), x1, y1, z1, p);
                                    }
                                } else {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.nopermission")));
                                }
                            }

                            if (args[0].equalsIgnoreCase("info")) {
                                x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                if (x != 0 && y != 0 && z != 0) {
                                    List<String> info = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Player-Info").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                } else {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.notactive")));
                                }
                            }

                            if (args[0].equalsIgnoreCase("teleport")) {
                                p.sendMessage("You must be a player to teleport to the Hunt");
                            }

                            if (args[0].equalsIgnoreCase("stop")) {
                                if (p.hasPermission("drifthunt.use")) {
                                    x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                    y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                    z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                    w = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
                                    chest = w.getBlockAt(x, y + 1, z);
                                    if (x != 0 && y != 0 && z != 0) {
                                        chest.setType(Material.AIR);
                                        this.main.getConfig().set("Drift-Hunt.chest.x", 0);
                                        this.main.getConfig().set("Drift-Hunt.chest.y", 0);
                                        this.main.getConfig().set("Drift-Hunt.chest.z", 0);
                                        this.main.saveConfig();
                                        List<String> huntstopped = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Hunt-Stopped").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                    } else {
                                        List<String> notactive = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Not-Active").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                    }
                                } else {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.nopermission")));
                                }
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.usage")));
                    }
                }
            } else if (sender instanceof Player) {
                Player p = (Player)sender;
                if (args.length >= 1 && args.length <= 1) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("start")) {
                            if (p.hasPermission("drifthunt.use")) {
                                x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                if (x != 0 && y != 0 && z != 0) {
                                    List<String> alreadyon = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Already-Active").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                } else {
                                    randomChest = new Random();
                                    isNegative = randomChest.nextInt(2);
                                    x1 = randomChest.nextInt(this.main.getConfig().getInt("Drift-Hunt.WorldBorderSize"));
                                    if (isNegative == 1) {
                                        x1 *= -1;
                                    }

                                    y1 = randomChest.nextInt(150);
                                    isNegative = randomChest.nextInt(2);
                                    z1 = randomChest.nextInt(this.main.getConfig().getInt("Drift-Hunt.WorldBorderSize"));
                                    if (isNegative == 1) {
                                        z1 *= -1;
                                    }

                                    this.PlayerLabHuntChest(Bukkit.getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld")), x1, y1, z1, p);
                                }
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.nopermission")));
                            }
                        }

                        if (args[0].equalsIgnoreCase("teleport")) {
                            if (p.hasPermission("drifthunt.use")) {
                                x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                w = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
                                Location loc = new Location(w, (double)x, (double)(y + 2), (double)z);
                                if (x != 0 && y != 0 && z != 0) {
                                    p.teleport(loc);
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.chesttp")));
                                } else {
                                    List<String> notactive = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Not-Active").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                }
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.nopermission")));
                            }
                        }

                        if (args[0].equalsIgnoreCase("info")) {
                            x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                            y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                            z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                            if (x != 0 && y != 0 && z != 0) {
                                List<String> info = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Player-Info").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                            } else {
                                List<String> notactive = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Not-Active").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                            }
                        }

                        if (args[0].equalsIgnoreCase("stop")) {
                            if (p.hasPermission("drifthunt.use")) {
                                x = (Integer)this.main.getConfig().get("Drift-Hunt.chest.x");
                                y = (Integer)this.main.getConfig().get("Drift-Hunt.chest.y");
                                z = (Integer)this.main.getConfig().get("Drift-Hunt.chest.z");
                                w = Bukkit.getServer().getWorld(this.main.getConfig().getString("Drift-Hunt.DefaultWorld"));
                                chest = w.getBlockAt(x, y + 1, z);
                                if (x != 0 && y != 0 && z != 0) {
                                    chest.setType(Material.AIR);
                                    this.main.getConfig().set("Drift-Hunt.chest.x", 0);
                                    this.main.getConfig().set("Drift-Hunt.chest.y", 0);
                                    this.main.getConfig().set("Drift-Hunt.chest.z", 0);
                                    this.main.saveConfig();
                                    List<String> huntstopped = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Hunt-Stopped").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                } else {
                                    List<String> notactive = this.main.getLang().getStringList(ChatColor.translateAlternateColorCodes('&', "Not-Active").replace("%x%", String.valueOf(x).replace("%y%", String.valueOf(y).replace("%z%", String.valueOf(z)))));
                                }
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.nopermission")));
                            }
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Drift-Hunt.messages.usage")));
                }
            }

            return true;
        }
    }


