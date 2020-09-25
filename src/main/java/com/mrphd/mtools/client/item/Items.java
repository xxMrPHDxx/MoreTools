package com.mrphd.mtools.client.item;

import static net.minecraft.item.Items.ACACIA_PLANKS;
import static net.minecraft.item.Items.BIRCH_PLANKS;
import static net.minecraft.item.Items.COBBLESTONE;
import static net.minecraft.item.Items.DARK_OAK_PLANKS;
import static net.minecraft.item.Items.DIAMOND;
import static net.minecraft.item.Items.EMERALD;
import static net.minecraft.item.Items.GOLD_INGOT;
import static net.minecraft.item.Items.IRON_INGOT;
import static net.minecraft.item.Items.JUNGLE_PLANKS;
import static net.minecraft.item.Items.OAK_PLANKS;
import static net.minecraft.item.Items.OBSIDIAN;
import static net.minecraft.item.Items.SMOOTH_STONE;
import static net.minecraft.item.Items.SPRUCE_PLANKS;
import static net.minecraft.item.Items.STONE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mrphd.mtools.client.item.tool.ToolAxe;
import com.mrphd.mtools.client.item.tool.ToolHammer;
import com.mrphd.mtools.client.item.tool.ToolLumberAxe;
import com.mrphd.mtools.client.item.tool.ToolPickaxe;

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
	private static final Set<Item> IRONS = ImmutableSet.<Item>of(IRON_INGOT);
	private static final Set<Item> GOLDS = ImmutableSet.<Item>of(GOLD_INGOT);
	private static final Set<Item> DIAMONDS = ImmutableSet.<Item>of(DIAMOND);
	private static final Set<Item> EMERALDS = ImmutableSet.<Item>of(EMERALD);
	private static final Set<Item> OBSIDIANS = ImmutableSet.<Item>of(OBSIDIAN);

	public static final Item emerald_axe = register(new ToolAxe("emerald_axe", 1.0f, com.mrphd.mtools.client.item.ItemTier.EMERALD, EMERALDS));
	public static final Item obsidian_axe = register(new ToolAxe("obsidian_axe", 1.5f, com.mrphd.mtools.client.item.ItemTier.OBSIDIAN, OBSIDIANS));
	
	public static final Item emerald_pickaxe = register(new ToolPickaxe("emerald_pickaxe", 1.0f, com.mrphd.mtools.client.item.ItemTier.EMERALD, EMERALDS));
	public static final Item obsidian_pickaxe = register(new ToolPickaxe("obsidian_pickaxe", 1.5f, com.mrphd.mtools.client.item.ItemTier.OBSIDIAN, OBSIDIANS));

	public static final Item wooden_lumberaxe = register(new ToolLumberAxe("wooden_lumberaxe", 0.2f, ItemTier.WOOD, WOODS));
	public static final Item stone_lumberaxe = register(new ToolLumberAxe("stone_lumberaxe", 0.2f, ItemTier.STONE, STONES));
	public static final Item iron_lumberaxe = register(new ToolLumberAxe("iron_lumberaxe", 0.2f, ItemTier.IRON, IRONS));
	public static final Item golden_lumberaxe = register(new ToolLumberAxe("golden_lumberaxe", 0.2f, ItemTier.GOLD, GOLDS));
	public static final Item diamond_lumberaxe = register(new ToolLumberAxe("diamond_lumberaxe", 0.2f, ItemTier.DIAMOND, DIAMONDS));
	public static final Item emerald_lumberaxe = register(new ToolLumberAxe("emerald_lumberaxe", 0.2f, com.mrphd.mtools.client.item.ItemTier.EMERALD, EMERALDS));
	public static final Item obsidian_lumberaxe = register(new ToolLumberAxe("obsidian_lumberaxe", 0.2f, com.mrphd.mtools.client.item.ItemTier.OBSIDIAN, OBSIDIANS));
	
	public static final Item wooden_hammer = register(new ToolHammer("wooden_hammer", 0.2f, ItemTier.WOOD, WOODS));
	public static final Item stone_hammer = register(new ToolHammer("stone_hammer", 0.3f, ItemTier.STONE, STONES));
	public static final Item iron_hammer = register(new ToolHammer("iron_hammer", 0.5f, ItemTier.IRON, IRONS));
	public static final Item golden_hammer = register(new ToolHammer("golden_hammer", 1.4f, ItemTier.GOLD, GOLDS));
	public static final Item diamond_hammer = register(new ToolHammer("diamond_hammer", 0.7f, ItemTier.DIAMOND, DIAMONDS));
	public static final Item emerald_hammer = register(new ToolHammer("emerald_hammer", 1.0f, com.mrphd.mtools.client.item.ItemTier.EMERALD, EMERALDS));
	public static final Item obsidian_hammer = register(new ToolHammer("obsidian_hammer", 1.5f, com.mrphd.mtools.client.item.ItemTier.OBSIDIAN, OBSIDIANS));
	
	/* Miscellaneous stuff */
	public static final Item tough_stick = register(new com.mrphd.mtools.client.item.Item("tough_stick", new Item.Properties()));
	
	private static final Item register(final Item item) {
		ITEMS.add(item);
		return item;
	}

}
