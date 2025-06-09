package invmod.util.spawneggs;

import net.minecraft.nbt.CompoundTag;

public class SpawnEggInfo {

	public final short eggID;
	public final String mobID;
	public final String displayName;
	public final CompoundTag spawnData;
	public final int primaryColor;
	public final int secondaryColor;

	public SpawnEggInfo(short eggID, String mobID, String displayName, CompoundTag spawnData, int primaryColor,
			int secondaryColor) {
		this.eggID = eggID;
		this.mobID = mobID;
		this.displayName = displayName;
		this.spawnData = spawnData;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	public SpawnEggInfo(short eggID, String mobID, CompoundTag compound, int primaryColor, int secondaryColor) {
		this(eggID, mobID, null, compound, primaryColor, secondaryColor);
	}

}