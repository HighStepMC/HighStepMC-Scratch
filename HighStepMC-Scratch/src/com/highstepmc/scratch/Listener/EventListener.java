package com.highstepmc.scratch.Listener;

import com.highstepmc.scratch.Util.CustomItem;
import com.highstepmc.scratch.Util.Enums.CustomItemType;
import com.highstepmc.scratch.main;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener {

	private com.highstepmc.scratch.main main;

	public EventListener(main main){
		this.main = main;
	}

	@EventHandler
	public void onjoin(PlayerJoinEvent event){
		for(CustomItem customItem : main.getCustomItemManager().getCustomItems()){
			event.getPlayer().getInventory().addItem(customItem.getItem());
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(!main.getCustomItemManager().isEqualTo(player.getItemInHand()))
			return;
		CustomItem customItem = main.getCustomItemManager().getFromItemStack(player.getItemInHand());
		if(customItem.getType().equals(CustomItemType.LEFT_CLICK)
				&& !(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)))
			return;
		if(customItem.getType().equals(CustomItemType.RIGHT_CLICK)
				&& !(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;
		customItem.use(player, null, null);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractAtEntityEvent event){
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		if(!main.getCustomItemManager().isEqualTo(player.getItemInHand()))
			return;
		if(!(main.getCustomItemManager().getFromItemStack(player.getItemInHand()).getType() == CustomItemType.INTERACT_ENTITY))
			return;
		main.getCustomItemManager().getFromItemStack(player.getItemInHand()).use(player, entity, null);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		if(!main.getCustomItemManager().isEqualTo(player.getItemInHand()))
			return;
		if(!(main.getCustomItemManager().getFromItemStack(player.getItemInHand()).getType() == CustomItemType.PLACE))
			return;
		main.getCustomItemManager().getFromItemStack(player.getItemInHand()).use(player, null, event.getBlock().getLocation());
	}

}
