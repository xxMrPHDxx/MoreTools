package com.mrphd.mtools.client.item;

import static net.minecraft.item.Items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mrphd.mtools.client.item.tool.ToolHammer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;

public class Items {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();

	private static final Set<Item> WOODS = ImmutableSet.<Item>of(
		ACACIA_PLANKS, BIRCH_PLANKS, DARK_OAK_PLANKS, JUNGLE_PLANKS, OAK_PLANKS, SPRUCE_PLANKS
	);
	private static final Set<Item> STONES = ImmutableSet.<Item>of(
		STONE, COBBLESTONE, SMOOTH_STONE
	);

	public static final Item wooden_hammer = register(new ToolHammer("wooden_hammer", 0.2f, ItemTier.WOOD, WOODS));
	public static final Item stone_hammer = register(new ToolHammer("stone_hammer", 0.3f, ItemTier.STONE, STONES));
	public static final Item iron_hammer = register(new ToolHammer("iron_hammer", 0.5f, ItemTier.IRON, ImmutableSet.<Item>of(IRON_INGOT)));
	public static final Item golden_hammer = register(new ToolHammer("golden_hammer", 1.4f, ItemTier.GOLD, ImmutableSet.<Item>of(GOLD_INGOT)));
	public static final Item diamond_hammer = register(new ToolHammer("diamond_hammer", 0.7f, ItemTier.DIAMOND, ImmutableSet.<Item>of(DIAMOND)));
	public static final Item emerald_hammer = register(new ToolHammer("emerald_hammer", 1.0f, com.mrphd.mtools.client.item.ItemTier.EMERALD, ImmutableSet.<Item>of(EMERALD)));
	public static final Item obsidian_hammer = register(new ToolHammer("obsidian_hammer", 1.5f, com.mrphd.mtools.client.item.ItemTier.OBSIDIAN, ImmutableSet.<Item>of(OBSIDIAN)));
	
	private static final Item register(final Item item) {
		ITEMS.add(item);
		return item;
	}

}
