
package qrearded.GamemodeUI;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.form.element.ElementButtonImageData;

public class main extends PluginBase implements Listener{

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);

    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
  
        final FormWindowSimple gmui = new FormWindowSimple("GamemodeUI" , "Choose your game mode");
        gmui.addButton(new ElementButton("Survival" , new ElementButtonImageData("path", "textures/blocks/cobblestone")));
        gmui.addButton(new ElementButton("Adventure" , new ElementButtonImageData("path", "textures/blocks/brick")));
        gmui.addButton(new ElementButton("Creative" , new ElementButtonImageData("path", "textures/blocks/bedrock")));
        gmui.addButton(new ElementButton("Spectator" , new ElementButtonImageData("path", "textures/blocks/noteblock")));
      final Player p = (Player)sender;  
        final String lowerCase;
        switch (lowerCase = command.getName().toLowerCase()) {
            case "gmui": {
                if (!(p instanceof Player)) {
                    p.sendMessage("§8» §fUse the command in-game.");
                    return false;
                }
                if (!p.hasPermission("gmui.cmd")) {
                    p.sendMessage("§8» §cYou do not have sufficient privileges to use this command.");
                    return false;
                }
                p.showFormWindow((FormWindow)gmui);
                break;
            }
            default:
                break;
        }
        return true;
    }

    @EventHandler
    public void onResponse(final PlayerFormRespondedEvent event) {
        final Player player = event.getPlayer();
        final FormWindowSimple fw = (FormWindowSimple)event.getWindow();
        if (fw.getTitle().equals("GamemodeUI")) {
            if (fw.getResponse().getClickedButton().getText().equals("Survival")){
              player.setGamemode(0);
              player.sendPopup("§8» §fYour game mode has been changed to §6Survival§f.");
            }
            if (fw.getResponse().getClickedButton().getText().equals("Adventure")){
              player.setGamemode(2);
              player.sendPopup("§8» §fYour game mode has been changed to §6Adventure§f.");
            }
            if (fw.getResponse().getClickedButton().getText().equals("Creative")){
              player.setGamemode(1);
              player.sendPopup("§8» §fYour game mode has been changed to §6Creative§f.");
            }
            if (fw.getResponse().getClickedButton().getText().equals("Spectator")){
              player.setGamemode(3);
              player.sendPopup("§8» §fYour game mode has been changed to §6Spectator§f.");
            }
        }
    }
}
