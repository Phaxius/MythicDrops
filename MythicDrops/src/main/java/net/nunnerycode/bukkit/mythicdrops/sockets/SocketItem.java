package net.nunnerycode.bukkit.mythicdrops.sockets;

import net.nunnerycode.bukkit.mythicdrops.api.items.MythicItemStack;
import org.bukkit.Material;

public class SocketItem extends MythicItemStack {

	public SocketItem(Material type) {
		super(type);
	}

//	public SocketItem(MaterialData materialData, SocketGem socketGem) {
//		super(materialData.getItemType(), 1, (short) 0, ChatColor.GOLD + MythicDropsSockets.getInstance().replaceArgs
//				(MythicDropsSockets.getInstance().getSocketGemName(), new String[][]{{"%socketgem%",
//						socketGem.getName()}}) + ChatColor.GOLD, MythicDropsSockets.getInstance().replaceArgs
//				(MythicDropsSockets.getInstance().getSocketGemLore(), new String[][]{{"%type%",
//						socketGem.getPresentableType()}}));
//	}

}
