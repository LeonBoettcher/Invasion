package invmod.entity;

import net.minecraft.world.level.level.block.Block;
import net.minecraft.world.level.level.block.state.BlockState;
import net.minecraft.core.BlockPos;

public class ModifyBlockEntry
/* implements IPosition */ {
	private BlockPos pos;
	private BlockState oldBlock;
	private BlockState newBlock;
	// private BlockState newBlockMeta;
	private int cost;

	public ModifyBlockEntry(BlockPos pos, Block newBlock) {
		this(pos, newBlock.getDefaultState(), 0, null);
	}

	public ModifyBlockEntry(BlockPos pos, BlockState newBlock) {
		this(pos, newBlock, 0, null);
	}

	public ModifyBlockEntry(BlockPos pos, Block newBlock, int cost) {
		this(pos, newBlock != null ? newBlock.getDefaultState() : null, cost, null);
	}

	public ModifyBlockEntry(BlockPos pos, BlockState newBlock, int cost) {
		this(pos, newBlock, cost, null);
	}

	public ModifyBlockEntry(BlockPos pos, Block newBlock, int cost, Block oldBlock) {
		this(pos, newBlock.getDefaultState(), cost, oldBlock != null ? oldBlock.getDefaultState() : null);
	}

	public ModifyBlockEntry(BlockPos pos, BlockState newBlock, int cost,
			/* BlockState newBlockMeta, */ BlockState oldBlock) {
		this.pos = pos;
		this.newBlock = newBlock;
		this.cost = cost;
		// this.newBlockMeta = newBlockMeta;
		this.oldBlock = oldBlock;
	}

	public BlockPos getPos() {
		return this.pos;
	}

	/*
	 * @Override public int getXCoord(){ return this.xCoord; }
	 * 
	 * @Override public int getYCoord(){ return this.yCoord; }
	 * 
	 * @Override public int getZCoord(){ return this.zCoord; }
	 */

	public BlockState getNewBlock() {
		return this.newBlock;
	}

	/*
	 * public BlockState getNewBlockMeta() { return this.newBlockMeta; }
	 */

	public int getCost() {
		return this.cost;
	}

	public BlockState getOldBlock() {
		return this.oldBlock;
	}

	public void setOldBlock(BlockState state) {
		this.oldBlock = state;
	}

}