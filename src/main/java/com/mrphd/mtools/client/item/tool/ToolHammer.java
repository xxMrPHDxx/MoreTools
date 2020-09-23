package com.mrphd.mtools.client.item.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;
import com.mrphd.mtools.MoreTools;
import com.mrphd.mtools.client.itemgroup.ItemGroups;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ToolHammer extends ToolItem {

	private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
			Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL,
			Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK,
			Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE,
			Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE,
			Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE,
			Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
			Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB,
			Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB,
			Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ,
			Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON,
			Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB,
			Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB,
			Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB,
			Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB,
			Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX,
			Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX,
			Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX,
			Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX,
			Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX);
	private static final Set<Direction> X_AXIS = ImmutableSet.<Direction>of(Direction.EAST, Direction.WEST);
	private static final Set<Direction> Y_AXIS = ImmutableSet.<Direction>of(Direction.UP, Direction.DOWN);

	public ToolHammer(String name, float spd, IItemTier tier, Set<Item> repairItem) {
		super(tier.getAttackDamage() * 0.7f, spd * 0.6f, tier, EFFECTIVE_ON,
				new Properties().addToolType(ToolType.PICKAXE, tier.getHarvestLevel()).group(ItemGroups.INSTANCE));
		this.setRegistryName(MoreTools.MOD_ID, name);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
		return super.onBlockStartBreak(itemstack, pos, player);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if (stack.getItem() != this)
			return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
		if (entityLiving instanceof PlayerEntity) {
			final PlayerEntity player = (PlayerEntity) entityLiving;
			final Vec3d dir = player.getLookVec().inverse();
			final Direction face = Direction.getFacingFromVector(dir.getX(), dir.getY(), dir.getZ());
			final boolean x = X_AXIS.contains(face), y = Y_AXIS.contains(face);
			
			final List<BlockPos> toDestroy = new ArrayList<BlockPos>();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					final BlockPos off = y ? pos.add(i, 0, j) : x ? pos.add(0, i, j) : pos.add(i, j, 0);
					final Block block = worldIn.getBlockState(off).getBlock();
					if(EFFECTIVE_ON.contains(block)) {
						toDestroy.add(off);
					}
				}
			}
			for(int i=0; i<Math.min(toDestroy.size(), stack.getDamage()); i++) {
				worldIn.destroyBlock(toDestroy.get(i), true);
				stack.damageItem(1, entityLiving, new Consumer<LivingEntity>() {
					public void accept(final LivingEntity entity) {
						
					};
				});
			}
		}
		return false;
	}

}
