package invmod.util.spawneggs;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public class CustomTags {

	public static CompoundTag poweredCreeper() {
		CompoundTag tag = new CompoundTag();
		tag.setByte("powered", (byte) 1);
		return tag;
	}

	public static CompoundTag IMZombie_T1() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 0);
		tag.setInteger("tier", 1);
		return tag;
	}

	public static CompoundTag IMZombie_T2() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 0);
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMZombie_T2_tar() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 2);
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMZombie_T3() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 0);
		tag.setInteger("tier", 3);
		return tag;
	}

	public static CompoundTag IMSpider_T1_baby() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 1);
		tag.setInteger("tier", 1);
		return tag;
	}

	public static CompoundTag IMSpider_T2() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 0);
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMSpider_T2_mother() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 1);
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMThrower_T2() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMZombiePigman_T1() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 1);
		tag.setInteger("tier", 1);
		return tag;
	}

	public static CompoundTag IMZombiePigman_T2() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 1);
		tag.setInteger("tier", 2);
		return tag;
	}

	public static CompoundTag IMZombiePigman_T3() {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("flavour", 1);
		tag.setInteger("tier", 3);
		return tag;
	}

	public static CompoundTag witherSkeleton() {
		CompoundTag tag = new CompoundTag();
		tag.setByte("SkeletonType", (byte) 1);
		ListTag list = new ListTag();
		CompoundTag swordItem = createItemTag((byte) 1, (short) 0, (short) 272);
		list.appendTag(swordItem);
		for (int i = 0; i < 4; ++i)
			list.appendTag(new CompoundTag());
		tag.setTag("Equipment", list);
		return tag;
	}

	public static CompoundTag villagerZombie() {
		CompoundTag tag = new CompoundTag();
		tag.setByte("IsVillager", (byte) 1);
		return tag;
	}

	public static CompoundTag babyZombie() {
		CompoundTag tag = new CompoundTag();
		tag.setByte("IsBaby", (byte) 1);
		return tag;
	}

	public static CompoundTag horseType(int type) {
		CompoundTag tag = new CompoundTag();
		tag.setInteger("Type", type);
		return tag;
	}

	public static CompoundTag createItemTag(byte count, short damage, short id) {
		CompoundTag item = new CompoundTag();
		item.setByte("Count", count);
		item.setShort("Damage", damage);
		item.setShort("id", id);
		return item;
	}

	public static CompoundTag getEntityTag(String entityID) {
		CompoundTag tag = new CompoundTag();
		tag.setString("id", entityID);
		return tag;
	}

	public static CompoundTag ridingTag(CompoundTag ridden) {
		CompoundTag tag = new CompoundTag();
		tag.setTag("Riding", ridden);
		return tag;
	}

	public static CompoundTag spiderJockey(boolean wither) {
		CompoundTag skele = (wither) ? witherSkeleton() : new CompoundTag();
		skele.setTag("Riding", getEntityTag("Spider"));
		return skele;
	}

	public static CompoundTag chickenJockey(boolean villager) {
		CompoundTag zomb = babyZombie();
		if (villager)
			zomb.setByte("IsVillager", (byte) 1);
		zomb.setTag("Riding", getEntityTag("Chicken"));
		return zomb;
	}

}