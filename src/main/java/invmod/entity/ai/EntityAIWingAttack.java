package invmod.entity.ai;

import invmod.entity.monster.EntityIMBird;
import net.minecraft.world.level.entity.LivingEntity;

public class EntityAIWingAttack extends EntityAIMeleeAttack {
	private EntityIMBird theEntity;

	public EntityAIWingAttack(EntityIMBird entity, Class<? extends LivingEntity> targetClass, int attackDelay) {
		super(entity, targetClass, attackDelay);
		this.theEntity = entity;
	}

	@Override
	public void updateTask() {
		if (this.getAttackTime() == 0) {
			this.theEntity.setAttackingWithWings(this.isInStartMeleeRange());
		}
		super.updateTask();
	}

	@Override
	public void resetTask() {
		this.theEntity.setAttackingWithWings(false);
	}

	protected boolean isInStartMeleeRange() {
		LivingEntity target = this.theEntity.getAttackTarget();
		if (target == null) {
			return false;
		}
		double d = this.theEntity.width + this.theEntity.getAttackRange() + 3.0D;
		return this.theEntity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ) < d * d;
	}
}