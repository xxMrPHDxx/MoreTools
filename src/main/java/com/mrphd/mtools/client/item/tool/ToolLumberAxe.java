package com.mrphd.mtools.client.item.tool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mrphd.mtools.util.Maths;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolLumberAxe extends ToolAxe {

	private static final int MAX_BLOCKS_TO_DESTROY = 100;

	public ToolLumberAxe(String name, float spd, IItemTier tier, Set<Item> repairItem) {
		super(name, spd, tier, repairItem);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if (!worldIn.isRemote && entityLiving instanceof PlayerEntity
				&& (EFFECTIVE_ON.contains(state.getBlock()) || canHarvestBlock(state))) {
			List<BlockPos> toDestroy = getAllConnectedBlocks(worldIn, state, pos);
			final int n = Maths.min(toDestroy.size(), stack.getMaxDamage(), MAX_BLOCKS_TO_DESTROY);
			System.out.println(String.format("%s, %s, %s, %s", toDestroy.size(), stack.getMaxDamage(), MAX_BLOCKS_TO_DESTROY, n));
			for (int i = 0; i < n; i++) {
				worldIn.destroyBlock(toDestroy.get(i), true);
				stack.damageItem(1, entityLiving, (final LivingEntity entity) -> {
				});
			}
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	private static List<BlockPos> getAllConnectedBlocks(World world, BlockState startBlock, BlockPos startPos) {
		final Predicate<BlockState> isSimilarBlock = (final BlockState state) -> {
			return state.getBlock() == startBlock.getBlock();
		};
		final Set<BlockPos> ret = new HashSet<BlockPos>();
		ret.add(startPos);
		final List<BlockPos> visited = Lists.newArrayList(startPos);
		List<BlockPos> candidate = getBlockAround(world, startBlock, startPos, isSimilarBlock);
		do {
//			List<BlockPos> newCandidate = candidate.stream().map((BlockPos pos) -> getBlockAround(world, world.getBlockState(pos), pos, isSimilarBlock)).collect(Collectors.reducing(new ArrayList<BlockPos>(), new BinaryOperator<List<BlockPos>>() {
//				@Override
//				public List<BlockPos> apply(List<BlockPos> a, List<BlockPos> b) {
//					final List<BlockPos> ret = new ArrayList<BlockPos>();
//					ret.addAll(a);
//					ret.addAll(b);
//					return ret; 
//				}
//			}));
			List<BlockPos> newCandidate = new ArrayList<BlockPos>();
			for(final BlockPos pos : candidate) {
				if(visited.contains(pos)) continue;
				visited.add(pos);
				newCandidate.addAll(getBlockAround(world, world.getBlockState(pos), pos, isSimilarBlock));
			}
			System.out.printf("Block around: %s\n", newCandidate.size());
			if (newCandidate.size() == 0)
				break;
			ret.addAll(newCandidate);
			candidate = newCandidate;
		} while(candidate.size() != 0);
		return ImmutableList.copyOf(ret);
	}

	private static List<BlockPos> getBlockAround(World world, BlockState state, BlockPos pos,
			Predicate<BlockState> condition) {
		final List<BlockPos> ret = new ArrayList<BlockPos>();
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					final BlockPos off = pos.add(x, y, z);
					final BlockState block = world.getBlockState(off);
					if (condition.apply(block)) {
						ret.add(off);
					}
				}
			}
		}
		return ImmutableList.copyOf(ret);
	}

}


/*
 * new Collector<List<BlockPos>, List<BlockPos>, List<BlockPos>>() {
				@Override
				public Supplier<List<BlockPos>> supplier() {	
					return () -> new ArrayList<BlockPos>();
				}
				@Override
				public BiConsumer<List<BlockPos>, List<BlockPos>> accumulator() {	
					return (l1, l2) -> l1.addAll(l2);
				}
				@Override
				public BinaryOperator<List<BlockPos>> combiner() {	
					return (a, b) -> {
						final List<BlockPos> ret = new ArrayList<BlockPos>();
						ret.addAll(a);
						ret.addAll(b);
						return ret;
					};
				}
				@Override
				public Function<List<BlockPos>, List<BlockPos>> finisher() {	
					return (l) -> l;
				}
				@Override
				public Set<Characteristics> characteristics() {	
					return ImmutableSet.of(Characteristics.UNORDERED);
				}
			}
 * */