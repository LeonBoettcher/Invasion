package invmod.entity.ai;

import invmod.entity.EntityIMLiving;
import net.minecraft.world.level.entity.Entity;
import net.minecraft.world.level.entity.LivingEntity;

public class EntityAIFollowEntity<T extends LivingEntity> extends EntityAIMoveToEntity<T> {
	private float followDistanceSq;

	public EntityAIFollowEntity(EntityIMLiving entity, float followDistance) {
		this(entity, (Class<? extends T>) LivingEntity.class, followDistance);
	}

	public EntityAIFollowEntity(EntityIMLiving entity, Class<? extends T> target, float followDistance) {
		super(entity, target);
		this.followDistanceSq = (followDistance * followDistance);
	}

	@Override
	public void startExecuting() {
		this.getEntity().onFollowingEntity(this.getTarget());
		super.startExecuting();
	}

	@Override
	public void resetTask() {
		this.getEntity().onFollowingEntity(null);
		super.resetTask();
	}

	@Override
	public void updateTask() {
		super.updateTask();
		Entity entity = this.getTarget();
		if (this.getEntity().getDistanceSq(entity.posX, entity.getEntityBoundingBox().minY,
				entity.posZ) < this.followDistanceSq)
			this.getEntity().getNavigatorNew().haltForTick();
	}
}