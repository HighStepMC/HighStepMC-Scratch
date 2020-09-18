package com.highstepmc.scratch.CustomItems;

import com.highstepmc.scratch.Util.Cooldown;
import com.highstepmc.scratch.Util.CustomItem;
import com.highstepmc.scratch.Util.Enums.CustomItemType;
import com.highstepmc.scratch.main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Reaper extends CustomItem {

    private Cooldown cooldown;

    private ItemStack itemStack;
    private CustomItemType customItemType;
    private int id;

    public Reaper(){
        this.itemStack = new ItemStack(Material.DIAMOND_HOE);{
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
            itemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Reaper Scythe");
            itemStack.setItemMeta(itemMeta);
        }
        this.customItemType = CustomItemType.RIGHT_CLICK;
        this.cooldown = new Cooldown();
        this.id = 3;
    }

    @Override
    public void use(Player user, Entity target) {
        cooldown.check(user);
        if(cooldown.contains(user))
            return;
        World world = user.getWorld();
        WitherSkull witherSkull = (WitherSkull) user.launchProjectile(WitherSkull.class);
        witherSkull.setVelocity(user.getEyeLocation().getDirection().multiply(1.7));
        witherSkull.setYield(3);
        cooldown.put(user, 560);
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
