package invmod.entity.ai;

//NOOB HAUS: Done

import invmod.entity.Goal;
import invmod.entity.INavigationFlying;
import invmod.entity.MoveState;
import invmod.entity.monster.EntityIMFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFlyingTackle extends EntityAIBase {
	private EntityIMFlying theEntity;
	private int time;

	public EntityAIFlyingTackle(EntityIMFlying entity) {
		this.theEntity = entity;
		this.time = 0;
	}

	@Override
	public boolean shouldExecute() {
		return this.theEntity.getAIGoal() == Goal.TACKLE_TARGET;
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase target = this.theEntity.getAttackTarget();
		if ((target == null) || (target.isDead)) {
			this.theEntity.transitionAIGoal(Goal.NONE);
			return false;
		}

		if (this.theEntity.getAIGoal() != Goal.TACKLE_TARGET) {
			return false;
		}
		return true;
	}

	@Override
	public void startExecuting() {
		this.time = 0;
		EntityLivingBase target = this.theEntity.getAttackTarget();
		if (target != null) {
			this.theEntity.getNavigatorNew().setMovementType(INavigationFlying.MoveType.PREFER_WALKING);
		}
	}

	@Override
	public void updateTask() {
		if (this.theEntity.getMoveState() != MoveState.FLYING) {
			this.theEntity.transitionAIGoal(Goal.MELEE_TARGET);
		}
	}
}