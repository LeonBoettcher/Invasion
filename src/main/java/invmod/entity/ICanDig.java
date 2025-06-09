package invmod.entity;

import net.minecraft.world.level.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.IBlockAccess;

public interface ICanDig {

	public BlockPos[] getBlockRemovalOrder(BlockPos pos);

	public float getBlockRemovalCost(BlockPos pos);

	public boolean canClearBlock(BlockPos pos);

	public void onBlockRemoved(BlockPos pos, BlockState state);

	public IBlockAccess getTerrain();

}