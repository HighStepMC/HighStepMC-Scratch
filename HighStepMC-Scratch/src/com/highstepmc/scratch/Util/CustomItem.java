package com.highstepmc.scratch.Util;

import com.highstepmc.scratch.Util.Enums.CustomItemType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

public abstract class CustomItem {

	public abstract void use(Player user, Entity target, Location location);

	public abstract int getID();

	public abstract ItemStack getItem();

	public abstract CustomItemType getType();
	
}
