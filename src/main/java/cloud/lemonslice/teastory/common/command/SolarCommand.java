package cloud.lemonslice.teastory.common.command;

import cloud.lemonslice.teastory.common.capability.CapabilitySolarTermTime;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class SolarCommand
{
    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("solar").requires((source) -> source.hasPermissionLevel(2))
                .then(Commands.literal("set").then(Commands.argument("day", IntegerArgumentType.integer()).executes(commandContext -> setDay(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "day")))))
                .then(Commands.literal("add").then(Commands.argument("day", IntegerArgumentType.integer()).executes(commandContext -> addDay(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "day"))))));
    }

    private static int getDay(ServerWorld worldIn)
    {
        return worldIn.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).map(CapabilitySolarTermTime.Data::getSolarTermsDay).orElse(0);
    }

    public static int setDay(CommandSource source, int day)
    {
        for (ServerWorld serverworld : source.getServer().getWorlds())
        {
            serverworld.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(data ->
            {
                data.setSolarTermsDay(day);
                data.sendUpdateMessage(serverworld);
            });
        }

        source.sendFeedback(new TranslationTextComponent("commands.teastory.solar.set", day), true);
        return getDay(source.getWorld());
    }

    public static int addDay(CommandSource source, int add)
    {
        for (ServerWorld serverworld : source.getServer().getWorlds())
        {
            serverworld.getCapability(CapabilitySolarTermTime.WORLD_SOLAR_TIME).ifPresent(data ->
            {
                data.setSolarTermsDay(data.getSolarTermsDay() + add);
                data.sendUpdateMessage(serverworld);
                source.sendFeedback(new TranslationTextComponent("commands.teastory.solar.set", data.getSolarTermsDay()), true);
            });
        }
        return getDay(source.getWorld());
    }
}
