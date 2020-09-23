package com.mrphd.mtools.client.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public enum ItemTier implements IItemTier {
	
	EMERALD(2342, 12, 4, 8, Ingredient.fromStacks(net.minecraft.item.Items.EMERALD.getDefaultInstance()), 3),
	OBSIDIAN(3746, 16, 5, 6, Ingredient.fromTag(Tags.Items.OBSIDIAN), 4.5f);
	
	private final int maxUses, eff, hvstLvl, enct;
	private final Ingredient repItem;
	private final float atkDmg;
	
	private ItemTier(int maxUses, int eff, int hvstLvl, int enct, Ingredient replItem, float atkDmg) {
		this.maxUses = maxUses;
		this.eff = eff;
		this.hvstLvl = hvstLvl;
		this.enct = enct;
		this.repItem = replItem;
		this.atkDmg = atkDmg;
	}
	
	@Override
	public int getMaxUses() {
		return maxUses;
	}
	
	@Override
	public float getEfficiency() {
		return eff;
	}
	
	@Override
	public int getHarvestLevel() {
		return hvstLvl;
	}
	
	@Override
	public int getEnchantability() {
		return enct;
	}
	
	@Override
	public Ingredient getRepairMaterial() {		
		return this.repItem;
	}
	
	@Override
	public float getAttackDamage() {
		return atkDmg;
	}
	
}
