package roito.teastory.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;

public class Record extends ItemRecord
{
    private final ResourceLocation res;

    protected Record(String name, String name2, ResourceLocation res)
    {
        super(name, new SoundEvent(res));
        this.res = res;
        this.setTranslationKey(name2);
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, name2));
        this.setCreativeTab(CreativeTabsRegister.tabRecords);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        EnumActionResult result = super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        if (worldIn.isRemote && result == EnumActionResult.SUCCESS)
        {
            if (Minecraft.getMinecraft().getSoundHandler().getAccessor(res) == null)
            {
                player.sendMessage(new TextComponentTranslation("teastory.message.record"));
            }
        }
        return result;
    }
}
