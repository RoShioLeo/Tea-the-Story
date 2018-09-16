package roito.teastory.compat.theoneprobe;


import com.google.common.base.Function;
import mcjty.theoneprobe.api.ITheOneProbe;

import javax.annotation.Nullable;

public class TOPCompatRegistry implements Function<ITheOneProbe, Void>
{
    @Override
	@Nullable
    public Void apply(ITheOneProbe theOneProbe)
    {
        theOneProbe.registerProvider(new TOPBarrel());
        theOneProbe.registerProvider(new TOPFullKettle());
        theOneProbe.registerProvider(new TOPTeapan());
        theOneProbe.registerProvider(new TOPTeaPlant());
        return null;
    }
}
