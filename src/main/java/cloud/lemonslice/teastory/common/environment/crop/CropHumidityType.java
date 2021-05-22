package cloud.lemonslice.teastory.common.environment.crop;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import net.minecraft.util.ResourceLocation;

public enum CropHumidityType
{
    ARID(new CropHumidityInfo(Humidity.ARID), new ResourceLocation("teastory:crops/arid_arid")),
    ARID_DRY(new CropHumidityInfo(Humidity.ARID, Humidity.DRY), new ResourceLocation("teastory:crops/arid_dry")),
    ARID_AVERAGE(new CropHumidityInfo(Humidity.ARID, Humidity.AVERAGE), new ResourceLocation("teastory:crops/arid_average")),
    ARID_MOIST(new CropHumidityInfo(Humidity.ARID, Humidity.MOIST), new ResourceLocation("teastory:crops/arid_moist")),
    ARID_HUMID(new CropHumidityInfo(Humidity.ARID, Humidity.HUMID), new ResourceLocation("teastory:crops/arid_humid")),
    DRY(new CropHumidityInfo(Humidity.DRY), new ResourceLocation("teastory:crops/dry_dry")),
    DRY_AVERAGE(new CropHumidityInfo(Humidity.DRY, Humidity.AVERAGE), new ResourceLocation("teastory:crops/dry_average")),
    DRY_MOIST(new CropHumidityInfo(Humidity.DRY, Humidity.MOIST), new ResourceLocation("teastory:crops/dry_moist")),
    DRY_HUMID(new CropHumidityInfo(Humidity.DRY, Humidity.HUMID), new ResourceLocation("teastory:crops/dry_humid")),
    AVERAGE(new CropHumidityInfo(Humidity.AVERAGE), new ResourceLocation("teastory:crops/average_average")),
    AVERAGE_MOIST(new CropHumidityInfo(Humidity.AVERAGE, Humidity.MOIST), new ResourceLocation("teastory:crops/average_moist")),
    AVERAGE_HUMID(new CropHumidityInfo(Humidity.AVERAGE, Humidity.HUMID), new ResourceLocation("teastory:crops/average_humid")),
    MOIST(new CropHumidityInfo(Humidity.MOIST), new ResourceLocation("teastory:crops/moist_moist")),
    MOIST_HUMID(new CropHumidityInfo(Humidity.MOIST, Humidity.HUMID), new ResourceLocation("teastory:crops/moist_humid")),
    HUMID(new CropHumidityInfo(Humidity.HUMID), new ResourceLocation("teastory:crops/humid_humid"));

    private final CropHumidityInfo info;
    private final ResourceLocation res;

    CropHumidityType(CropHumidityInfo info, ResourceLocation res)
    {
        this.info = info;
        this.res = res;
    }

    public CropHumidityInfo getInfo()
    {
        return info;
    }

    public ResourceLocation getRes()
    {
        return res;
    }
}
