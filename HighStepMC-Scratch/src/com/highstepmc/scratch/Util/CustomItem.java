package com.highstepmc.scratch.Util;

import com.highstepmc.scratch.Util.Enums.CustomItemType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem {

	public abstract void use(Player user, Entity target);

	public abstract int getID();

	public abstract ItemStack getItem();

	public abstract CustomItemType getType();

	public ItemStack setAmount(CustomItem customItem, int amount){
		getItem().setAmount(amount);
		return getItem();
	}
	
}
