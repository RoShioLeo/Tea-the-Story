package cloud.lemonslice.teastory.common.tileentity;

import cloud.lemonslice.teastory.common.block.BlockRegistry;
import cloud.lemonslice.teastory.registry.RegistryModule;
import net.minecraft.tileentity.TileEntityType;

public final class TileEntityTypeRegistry extends RegistryModule
{
    public static final TileEntityType<StoveTileEntity> STOVE_TILE = (TileEntityType<StoveTileEntity>) TileEntityType.Builder.create(StoveTileEntity::new, BlockRegistry.DIRT_STOVE, BlockRegistry.STONE_STOVE).build(null).setRegistryName("stove");
    public static final TileEntityType<BambooTrayTileEntity> BAMBOO_TRAY = (TileEntityType<BambooTrayTileEntity>) TileEntityType.Builder.create(BambooTrayTileEntity::new, BlockRegistry.BAMBOO_TRAY, BlockRegistry.STONE_CATAPULT_BOARD_WITH_TRAY).build(null).setRegistryName("bamboo_tray");
    public static final TileEntityType<DrinkMakerTileEntity> DRINK_MAKER = (TileEntityType<DrinkMakerTileEntity>) TileEntityType.Builder.create(DrinkMakerTileEntity::new, BlockRegistry.DRINK_MAKER).build(null).setRegistryName("drink_maker");
    public static final TileEntityType<TeapotTileEntity> TEAPOT = (TileEntityType<TeapotTileEntity>) TileEntityType.Builder.create(() -> new TeapotTileEntity(1000), BlockRegistry.TEAPOT).build(null).setRegistryName("teapot");
    public static final TileEntityType<TeapotTileEntity> IRON_KETTLE = (TileEntityType<TeapotTileEntity>) TileEntityType.Builder.create(() -> new TeapotTileEntity(2000), BlockRegistry.IRON_KETTLE).build(null).setRegistryName("iron_kettle");
    public static final TileEntityType<TeaCupTileEntity> PORCELAIN_CUP = (TileEntityType<TeaCupTileEntity>) TileEntityType.Builder.create(() -> new TeaCupTileEntity(250), BlockRegistry.WOODEN_TRAY).build(null).setRegistryName("porcelain_cup");
    public static final TileEntityType<WoodenBarrelTileEntity> WOODEN_BARREL = (TileEntityType<WoodenBarrelTileEntity>) TileEntityType.Builder.create(() -> new WoodenBarrelTileEntity(), BlockRegistry.WOODEN_BARREL).build(null).setRegistryName("wooden_barrel");
    public static final TileEntityType<StoneMillTileEntity> STONE_MILL = (TileEntityType<StoneMillTileEntity>) TileEntityType.Builder.create(() -> new StoneMillTileEntity(), BlockRegistry.STONE_MILL).build(null).setRegistryName("stone_mill");
    public static final TileEntityType<StoneRollerTileEntity> STONE_ROLLER = (TileEntityType<StoneRollerTileEntity>) TileEntityType.Builder.create(() -> new StoneRollerTileEntity(), BlockRegistry.STONE_ROLLER).build(null).setRegistryName("stone_roller");
}
