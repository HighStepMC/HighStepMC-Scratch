package com.highstepmc.scratch.Util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {

    private Map<UUID, Long> map = new HashMap<>();

    public Cooldown(){

    }

    public void put(Player player, int cooldown){
        map.put(player.getUniqueId(), System.currentTimeMillis() + cooldown);
    }

    public void remove(Player player){
        map.remove(player.getUniqueId());
    }

    public void check(Player player){
        if(map.containsKey(player.getUniqueId())
                && map.get(player.getUniqueId()) < System.currentTimeMillis()){
            map.remove(player.getUniqueId());
        }
    }

    public boolean contains(Player player){
        if(map.containsKey(player.getUniqueId())
                && map.get(player.getUniqueId()) > System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public long getTimeLeft(Player player){
        if(!map.containsKey(player.getUniqueId()))
            return 0;
        return (map.get(player.getUniqueId()) - System.currentTimeMillis());
    }

}
