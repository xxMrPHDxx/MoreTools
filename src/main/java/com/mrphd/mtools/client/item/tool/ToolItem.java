package com.mrphd.mtools.client.item.tool;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;

public class ToolItem extends net.minecraft.item.ToolItem {

	public ToolItem(float atkDmg, float atkSpd, IItemTier tier, Set<Block> effectiveBlocks, Properties properties) {
		super(atkDmg, atkSpd, tier, effectiveBlocks, properties);
	}

}
