package roito.teastory.item;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class ItemEmptyPot extends TSItem
{
    String cold_water;

    public ItemEmptyPot(String name)
    {
        super(name, 64, CreativeTabsRegister.tabDrink);
        this.cold_water = TeaStory.MODID + ":cold_water_" + name;
    }

    public Item getColdWater()
    {
        return Item.getByNameOrId(cold_water);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, playerIn.getHeldItem(handIn), raytraceresult);
        if (ret != null)
        {
            return ret;
        }

        if (raytraceresult == null)
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }
        else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
        {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        }
        else
        {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (!worldIn.isBlockModifiable(playerIn, blockpos))
            {
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
            }
            else
            {
                if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, playerIn.getHeldItem(handIn)))
                {
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                }
                else
                {
                    IBlockState iblockstate = worldIn.getBlockState(blockpos);
                    Material material = iblockstate.getMaterial();

                    if (material == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL).intValue() == 0)
                    {
                        playerIn.addStat(StatList.getObjectUseStats(this));
                        playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, this.turnPotIntoItem(playerIn.getHeldItem(handIn), playerIn, new ItemStack(Item.getByNameOrId(cold_water), 1, playerIn.getHeldItem(handIn).getItemDamage())));
                    }
                    else
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
                    }
                }
            }
        }
    }

    protected ItemStack turnPotIntoItem(ItemStack stackIn, EntityPlayer player, ItemStack stack)
    {
        if (!player.capabilities.isCreativeMode)
        {
            stackIn.shrink(1);
        }

        if (stackIn.getCount() <= 0)
        {
            return stack;
        }
        else
        {
            ItemHandlerHelper.giveItemToPlayer(player, stack);

            return stackIn;
        }
    }
}
