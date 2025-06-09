package invmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.item.Item;

public class ModItem extends Item {

	public final String name;

	public ModItem(String name) {
		this.name = name;
		/*
		 * this.setRegistryName(name); this.setUnlocalizedName(name);
		 * this.setCreativeTab(mod_Invasion.tabInvmod);
		 */
		// GameRegistry.register(this);
	}

	@Override
	public ModItem setCreativeTab(CreativeModeTab tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public ModItem setMaxStackSize(int maxStackSize) {
		super.setMaxStackSize(maxStackSize);
		return this;
	}

	@Override
	public ModItem setMaxDamage(int maxDamageIn) {
		super.setMaxDamage(maxDamageIn);
		return this;
	}

	@Override
	public ModItem setFull3D() {
		super.setFull3D();
		return this;
	}

}
