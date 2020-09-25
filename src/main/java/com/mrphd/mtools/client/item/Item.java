package com.mrphd.mtools.client.item;

import com.mrphd.mtools.MoreTools;

public class Item extends net.minecraft.item.Item {

	public Item(String name, Properties properties) {
		super(properties);
		setRegistryName(MoreTools.MOD_ID, name);
	}
	
}
