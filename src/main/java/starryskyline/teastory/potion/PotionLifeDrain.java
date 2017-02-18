package starryskyline.teastory.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import starryskyline.teastory.TeaStory;

public class PotionLifeDrain extends Potion
{
	private static final ResourceLocation res = new ResourceLocation(TeaStory.MODID + ":" + "textures/gui/potion.png");

    public PotionLifeDrain()
    {
        super(new ResourceLocation(TeaStory.MODID + ":" + "life_drain"), false, 0x7F0000);
        this.setPotionName("potion.life_drain");
    }
    
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(PotionLifeDrain.res);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 36, 0, 18, 18);
    }
}
