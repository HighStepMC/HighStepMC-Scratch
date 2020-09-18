package com.highstepmc.scratch.Util;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItemManager {

	private Map<Integer, CustomItem> customItems = new HashMap();
	
	public CustomItemManager() {
		
	}

	public void register(CustomItem customItem){
		customItems.put(customItem.getID(), customItem);
	}

	public boolean isEqualTo(ItemStack itemStack){
		for(int i : customItems.keySet()){
			if(itemStack.getType() == customItems.get(i).getItem().getType()
					&& itemStack.getItemMeta().equals(customItems.get(i).getItem().getItemMeta()))
				return true;
		}
		return false;
	}

	public CustomItem getFromItemStack(ItemStack itemStack){
		for(int i : customItems.keySet()){
			if(customItems.get(i).getItem().getType() == itemStack.getType()
					&& customItems.get(i).getItem().getItemMeta().equals(itemStack.getItemMeta()))
				return customItems.get(i);
		}
		return null;
	}

	public CustomItem getByID(int id){
		return customItems.get(id);
	}

	public List<CustomItem> getCustomItems() {
		List<CustomItem> list = new ArrayList<>();
		for(int i : customItems.keySet()){
			list.add(customItems.get(i));
		}
		return list;
	}
}
