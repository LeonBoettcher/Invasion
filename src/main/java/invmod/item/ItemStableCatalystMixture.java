package invmod.item;

import net.minecraft.item.Item;

public class ItemStableCatalystMixture extends Item {

	private final String name = "stableCatalystMixture";

	public ItemStableCatalystMixture() {
		// this.setRegistryName(this.name);
		// GameRegistry.register(this);
		// this.setUnlocalizedName(Reference.MODID + "_" + this.name);
		this.setMaxStackSize(1);
		// this.setCreativeTab(mod_Invasion.tabInvmod);
	}

	public String getName() {
		return this.name;
	}

}
