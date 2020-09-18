package com.highstepmc.scratch.CustomItems;

import com.highstepmc.scratch.Util.Cooldown;
import com.highstepmc.scratch.Util.CustomItem;
import com.highstepmc.scratch.Util.Enums.CustomItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;

public class Buster extends CustomItem {

    private Cooldown cooldown;

    private ItemStack itemStack;
    private CustomItemType customItemType;
    private int id;

    public Buster(){
        this.itemStack = new ItemStack(Material.BLAZE_ROD);{
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            itemMeta.setDisplayName(ChatColor.RED + "BUSTER");
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemStack.setItemMeta(itemMeta);
        }
        this.customItemType = CustomItemType.CLICK;
        this.id = 1;
        this.cooldown = new Cooldown();
    }

    @Override
    public void use(Player user, Entity target) {
        cooldown.check(user);
        if(cooldown.contains(user)) {
            user.sendMessage(ChatColor.GREEN + "Time left: " + cooldown.getTimeLeft(user) / 1000);
            return;
        }
        user.getWorld().createExplosion(user.getTargetBlock((HashSet<Byte>) null, 0).getLocation(), 10);
        cooldown.put(user, 1500);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public CustomItemType getType() {
        return customItemType;
    }
}
