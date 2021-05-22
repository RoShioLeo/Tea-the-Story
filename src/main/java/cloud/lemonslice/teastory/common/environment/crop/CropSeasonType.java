package cloud.lemonslice.teastory.common.environment.crop;

import net.minecraft.util.ResourceLocation;

public enum CropSeasonType
{
    SPRING(new CropSeasonInfo(1), new ResourceLocation("teastory:crops/spring")),
    SUMMER(new CropSeasonInfo(2), new ResourceLocation("teastory:crops/summer")),
    AUTUMN(new CropSeasonInfo(4), new ResourceLocation("teastory:crops/autumn")),
    WINTER(new CropSeasonInfo(8), new ResourceLocation("teastory:crops/winter")),
    SP_SU(new CropSeasonInfo(3), new ResourceLocation("teastory:crops/spring_summer")),
    SP_AU(new CropSeasonInfo(5), new ResourceLocation("teastory:crops/spring_autumn")),
    SP_WI(new CropSeasonInfo(9), new ResourceLocation("teastory:crops/spring_winter")),
    SU_AU(new CropSeasonInfo(6), new ResourceLocation("teastory:crops/summer_autumn")),
    SU_WI(new CropSeasonInfo(10), new ResourceLocation("teastory:crops/summer_winter")),
    AU_WI(new CropSeasonInfo(12), new ResourceLocation("teastory:crops/autumn_winter")),
    SP_SU_AU(new CropSeasonInfo(7), new ResourceLocation("teastory:crops/spring_summer_autumn")),
    SP_SU_WI(new CropSeasonInfo(11), new ResourceLocation("teastory:crops/spring_summer_winter")),
    SP_AU_WI(new CropSeasonInfo(13), new ResourceLocation("teastory:crops/spring_autumn_winter")),
    SU_AU_WI(new CropSeasonInfo(14), new ResourceLocation("teastory:crops/summer_autumn_winter")),
    ALL(new CropSeasonInfo(15), new ResourceLocation("teastory:crops/all_seasons"));

    private final CropSeasonInfo info;
    private final ResourceLocation res;

    CropSeasonType(CropSeasonInfo info, ResourceLocation res)
    {
        this.info = info;
        this.res = res;
    }

    public CropSeasonInfo getInfo()
    {
        return info;
    }

    public ResourceLocation getRes()
    {
        return res;
    }
}
