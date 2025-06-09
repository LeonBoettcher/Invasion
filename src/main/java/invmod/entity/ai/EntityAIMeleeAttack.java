package invmod.entity.ai;

import invmod.entity.Goal;
import invmod.entity.monster.EntityIMMob;
import net.minecraft.world.level.entity.LivingEntity;
import net.minecraft.world.level.entity.ai.EntityAIBase;

public class EntityAIMeleeAttack<T extends LivingEntity> extends EntityAIBase {
	private EntityIMMob theEntity;
	private Class<? extends T> targetClass;
	private float attackRange;
	private int attackDelay;
	private int nextAttack;

	public EntityAIMeleeAttack(EntityIMMob entity, Class<? extends T> targetClass, int attackDelay) {
		this.theEntity = entity;
		this.targetClass = targetClass;
		this.attackDelay = attackDelay;
		this.attackRange = 0.6F;
		this.nextAttack = 0;
	}

	@Override
	public boolean shouldExecute() {
		LivingEntity target = this.theEntity.getAttackTarget();
		return (target != null)
				&& (this.theEntity.getAIGoal() == Goal.MELEE_TARGET) && (this.theEntity
						.getDistance(target) < (this.attackRange + this.theEntity.width + target.width) * 4.0F)
				&& (target.getClass().isAssignableFrom(this.targetClass));
	}

	@Override
	public void updateTask() {
		LivingEntity target = this.theEntity.getAttackTarget();
		if (this.canAttackEntity(target)) {
			this.attackEntity(target);
		}
		this.setAttackTime(this.getAttackTime() - 1);
	}

	public Class<? extends T> getTargetClass() {
		return this.targetClass;
	}

	protected void attackEntity(LivingEntity target) {
		this.theEntity.attackEntityAsMob(target);
		this.setAttackTime(this.getAttackDelay());
	}

	protected boolean canAttackEntity(LivingEntity target) {
		if (this.getAttackTime() <= 0) {
			double d = this.theEntity.width + this.attackRange;
			return this.theEntity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ) < d * d;
		}
		return false;
	}

	protected int getAttackTime() {
		return this.nextAttack;
	}

	protected void setAttackTime(int time) {
		this.nextAttack = time;
	}

	protected int getAttackDelay() {
		return this.attackDelay;
	}

	protected void setAttackDelay(int time) {
		this.attackDelay = time;
	}
}