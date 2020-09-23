package com.mrphd.mtools.client.itemgroup;

import com.mrphd.mtools.MoreTools;
import com.mrphd.mtools.client.item.Items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroups extends ItemGroup {
	
	public static final ItemGroup INSTANCE = new ItemGroups();
	
	public ItemGroups() {
		super(MoreTools.MOD_ID);
	}
	
	@Override
	public ItemStack createIcon() {
		return Items.wooden_hammer.getDefaultInstance();
	}

}
