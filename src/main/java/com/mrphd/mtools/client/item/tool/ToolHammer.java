package com.mrphd.mtools.client.item.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ToolHammer extends ToolPickaxe {

	private static final Set<Direction> X_AXIS = ImmutableSet.<Direction>of(Direction.EAST, Direction.WEST);
	private static final Set<Direction> Y_AXIS = ImmutableSet.<Direction>of(Direction.UP, Direction.DOWN);

	public ToolHammer(String name, float spd, IItemTier tier, Set<Item> repairItem) {
		super(name, spd, tier, repairItem);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if (stack.getItem() != this)
			return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
		if (!worldIn.isRemote && entityLiving instanceof PlayerEntity
				&& (EFFECTIVE_ON.contains(state.getBlock()) || canHarvestBlock(state))) {
			final PlayerEntity player = (PlayerEntity) entityLiving;
			final Vec3d dir = player.getLookVec().inverse();
			final Direction face = Direction.getFacingFromVector(dir.getX(), dir.getY(), dir.getZ());
			final boolean x = X_AXIS.contains(face), y = Y_AXIS.contains(face);

			final List<BlockPos> toDestroy = new ArrayList<BlockPos>();
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0)
						continue;
					final BlockPos off = y ? pos.add(i, 0, j) : x ? pos.add(0, i, j) : pos.add(i, j, 0);
					final BlockState block = worldIn.getBlockState(off);
					if (EFFECTIVE_ON.contains(block.getBlock()) || canHarvestBlock(stack, block)) {
						toDestroy.add(off);
					}
				}
			}
			for (int i = 0; i < Math.min(toDestroy.size(), stack.getMaxDamage()); i++) {
				worldIn.destroyBlock(toDestroy.get(i), true);
				stack.damageItem(1, entityLiving, (final LivingEntity entity) -> {});
			}
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

}
