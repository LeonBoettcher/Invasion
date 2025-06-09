import os
import re

# Package mapping from 1.12.2 to 1.19.2
PACKAGE_MAPPING = {
    'net.minecraft.block': 'net.minecraft.world.level.block',
    'net.minecraft.block.material': 'net.minecraft.world.level.material',
    'net.minecraft.block.properties': 'net.minecraft.world.level.block.state.properties',
    'net.minecraft.block.state': 'net.minecraft.world.level.block.state',
    'net.minecraft.entity': 'net.minecraft.world.entity',
    'net.minecraft.entity.player': 'net.minecraft.world.entity.player',
    'net.minecraft.item': 'net.minecraft.world.item',
    'net.minecraft.tileentity': 'net.minecraft.world.level.block.entity',
    'net.minecraft.util.math': 'net.minecraft.core',
    'net.minecraft.world': 'net.minecraft.world.level',
    'net.minecraftforge.fml.relauncher': 'net.minecraftforge.api.distmarker',
    'net.minecraft.client.gui': 'net.minecraft.client.gui.screens',
    'net.minecraft.inventory': 'net.minecraft.world.inventory',
    'net.minecraft.nbt': 'net.minecraft.nbt',
    'net.minecraft.util': 'net.minecraft.core',
    'net.minecraft.util.text': 'net.minecraft.network.chat',
    'net.minecraft.init': 'net.minecraft.world.level.block',
    'net.minecraft.creativetab': 'net.minecraft.world.item',
    'net.minecraft.client.renderer': 'net.minecraft.client.renderer',
    'net.minecraft.client.resources': 'net.minecraft.client.resources',
    'net.minecraft.network': 'net.minecraft.network',
    'net.minecraft.stats': 'net.minecraft.stats',
    'net.minecraft.enchantment': 'net.minecraft.world.item.enchantment',
    'net.minecraft.potion': 'net.minecraft.world.effect',
}

# Class name mapping from 1.12.2 to 1.19.2
CLASS_MAPPING = {
    'IBlockState': 'BlockState',
    'PropertyBool': 'BooleanProperty',
    'ItemBlock': 'BlockItem',
    'TileEntity': 'BlockEntity',
    'EnumFacing': 'Direction',
    'EnumHand': 'InteractionHand',
    'EnumParticleTypes': 'ParticleTypes',
    'World': 'Level',
    'EntityPlayer': 'Player',
    'EnumBlockRenderType': 'RenderShape',
    'BlockStateContainer': 'StateDefinition',
    'Side': 'Dist',
    'SideOnly': 'OnlyIn',
    'GuiContainer': 'AbstractContainerScreen',
    'GuiScreen': 'Screen',
    'ITextComponent': 'Component',
    'TextComponentString': 'TextComponent',
    'NBTTagCompound': 'CompoundTag',
    'NBTTagList': 'ListTag',
    'Item.Properties': 'Item.Properties',
    'CreativeTabs': 'CreativeModeTab',
    'SoundType': 'SoundType',
    'Material': 'Material',
    'EntityLiving': 'LivingEntity',
    'EntityLivingBase': 'LivingEntity',
    'DamageSource': 'DamageSource',
    'SoundEvent': 'SoundEvent',
    'IInventory': 'Container',
    'Container': 'AbstractContainerMenu',
    'Slot': 'Slot',
}

def update_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Update package imports
    for old_pkg, new_pkg in PACKAGE_MAPPING.items():
        content = content.replace(f'import {old_pkg}.', f'import {new_pkg}.')
        content = content.replace(f'import static {old_pkg}.', f'import static {new_pkg}.')

    # Update class names
    for old_class, new_class in CLASS_MAPPING.items():
        content = re.sub(r'\b' + old_class + r'\b', new_class, content)

    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(content)

def process_directory(directory):
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                print(f'Processing {file_path}')
                update_file(file_path)

if __name__ == '__main__':
    process_directory('src/main/java/invmod') 