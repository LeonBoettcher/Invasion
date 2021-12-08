package invmod.inventory.container;

import invmod.inventory.slot.SlotOutput;
import invmod.tileentity.TileEntityNexus;
import invmod.util.ModLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class ContainerNexus extends Container
{

	private TileEntityNexus nexus;
	private int activationTimer;
	private int currentWave;
	private int nexusLevel;
	private int nexusKills;
	private int spawnRadius;
	private int generation;
	private int powerLevel;
	private int cookTime;
	private int mode;
	
	private IItemHandler handler;

	public ContainerNexus(InventoryPlayer inventoryplayer, TileEntityNexus tileEntityNexus)
	{
		this.mode = 0;
		this.activationTimer = 0;
		this.currentWave = 0;
		this.nexusLevel = 0;
		this.nexusKills = 0;
		this.spawnRadius = 0;
		this.generation = 0;
		this.powerLevel = 0;
		this.cookTime = 0;
		this.nexus = tileEntityNexus;
		this.handler = nexus.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		
		
		IItemHandler handler = tileEntityNexus.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 32, 33) {
			public boolean isItemValid (ItemStack stack) {
				return true;
			}
		});
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 102, 33) {
			public boolean isItemValid (ItemStack stack) {
				return false;
			}
		});
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				this.addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for (int j = 0; j < 9; j++)
		{
			this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
		}
		
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		//for (int i = 0; i < this.crafters.size(); i++) {
		for (int i = 0; i < this.listeners.size(); i++)
		{
			//ICrafting icrafting = (ICrafting) this.crafters.get(i);
			if (this.activationTimer != this.nexus.getActivationTimer())
			{
				//icrafting.sendProgressBarUpdate(this, 0, this.nexus.getActivationTimer());
				this.listeners.get(i).sendWindowProperty(this, 0, this.nexus.getActivationTimer());
			}
			if (this.mode != this.nexus.getMode())
			{
				//icrafting.sendProgressBarUpdate(this, 1, this.nexus.getMode());
				this.listeners.get(i).sendWindowProperty(this, 1, this.nexus.getMode());
			}
			if (this.currentWave != this.nexus.getCurrentWave())
			{
				//icrafting.sendProgressBarUpdate(this, 2, this.nexus.getCurrentWave());
				this.listeners.get(i).sendWindowProperty(this, 2, this.nexus.getCurrentWave());
			}
			if (this.nexusLevel != this.nexus.getNexusLevel())
			{
				//icrafting.sendProgressBarUpdate(this, 3, this.nexus.getNexusLevel());
				this.listeners.get(i).sendWindowProperty(this, 3, this.nexus.getNexusLevel());
			}
			if (this.nexusKills != this.nexus.getNexusKills())
			{
				//icrafting.sendProgressBarUpdate(this, 4, this.nexus.getNexusKills());
				this.listeners.get(i).sendWindowProperty(this, 4, this.nexus.getNexusKills());
			}
			if (this.spawnRadius != this.nexus.getSpawnRadius())
			{
				//icrafting.sendProgressBarUpdate(this, 5, this.nexus.getSpawnRadius());
				this.listeners.get(i).sendWindowProperty(this, 4, this.nexus.getSpawnRadius());
			}
			if (this.generation != this.nexus.getGeneration())
			{
				//icrafting.sendProgressBarUpdate(this, 6, this.nexus.getGeneration());
				this.listeners.get(i).sendWindowProperty(this, 4, this.nexus.getGeneration());
			}
			if (this.generation != this.nexus.getNexusPowerLevel())
			{
				//icrafting.sendProgressBarUpdate(this, 7, this.nexus.getNexusPowerLevel());
				this.listeners.get(i).sendWindowProperty(this, 4, this.nexus.getPowerLevel());
			}
			if (this.generation != this.nexus.getCookTime())
			{
				//icrafting.sendProgressBarUpdate(this, 9, this.nexus.getCookTime());
				this.listeners.get(i).sendWindowProperty(this, 4, this.nexus.getCookTime());
			}
		}

		this.activationTimer = this.nexus.getActivationTimer();
		this.mode = this.nexus.getMode();
		this.currentWave = this.nexus.getCurrentWave();
		this.nexusLevel = this.nexus.getNexusLevel();
		this.nexusKills = this.nexus.getNexusKills();
		this.spawnRadius = this.nexus.getSpawnRadius();
		this.generation = this.nexus.getGeneration();
		this.powerLevel = this.nexus.getNexusPowerLevel();
		this.cookTime = this.nexus.getCookTime();
	}

	@Override
	public void updateProgressBar(int i, int j)
	{
		if (i == 0)
		{
			this.nexus.setActivationTimer(j);
		}
		else if (i == 1)
		{
			this.nexus.setMode(j);
		}
		else if (i == 2)
		{
			this.nexus.setWave(j);
		}
		else if (i == 3)
		{
			this.nexus.setNexusLevel(j);
		}
		else if (i == 4)
		{
			this.nexus.setNexusKills(j);
		}
		else if (i == 5)
		{
			this.nexus.setSpawnRadius(j);
		}
		else if (i == 6)
		{
			this.nexus.setGeneration(j);
		}
		else if (i == 7)
		{
			this.nexus.setNexusPowerLevel(j);
		}
		else if (i == 8)
		{
			this.nexus.setCookTime(j);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(i);
		if ((slot != null) && (slot.getHasStack()))
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 1)
			{
				if (!this.mergeItemStack(itemstack1, 2, 38, true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if ((i >= 2) && (i < 29))
			{
				if (!this.mergeItemStack(itemstack1, 29, 38, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if ((i >= 29) && (i < 38))
			{
				if (!this.mergeItemStack(itemstack1, 2, 29, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 2, 38, false))
			{
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
			{
				ModLogger.logInfo("TODO");
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.getCount() != itemstack.getCount())
			{
				slot.onTake(player, itemstack1);
			}
			else
			{
				return ItemStack.EMPTY;
			}
		}
		return itemstack;
	}
}