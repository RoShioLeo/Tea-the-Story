package cloud.lemonslice.teastory.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig
{
    protected ServerConfig(ForgeConfigSpec.Builder builder)
    {
        Block.load(builder);
        Agriculture.load(builder);
        Temperature.load(builder);
        Season.load(builder);
        Others.load(builder);
    }

    public static class Block
    {
        public static ForgeConfigSpec.IntValue woodenBarrelCapacity;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.push("Block");
            woodenBarrelCapacity = builder.comment("The capacity of wooden barrel. (mB)")
                    .defineInRange("WoodenBarrelCapacity", 4000, 100, Integer.MAX_VALUE);
            builder.pop();
        }
    }

    public static class Temperature
    {
        public static ForgeConfigSpec.BooleanValue iceMelt;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.push("Temperature");
            iceMelt = builder.comment("Ice or snow layer will melt in warm place..")
                    .define("IceAndSnowMelt", true);
            builder.pop();
        }
    }

    public static class Season
    {
        public static ForgeConfigSpec.BooleanValue enable;
        public static ForgeConfigSpec.IntValue lastingDaysOfEachTerm;
        public static ForgeConfigSpec.IntValue initialSolarTermIndex;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.push("Season");
            enable = builder.comment("Enable solar term season system.")
                    .define("EnableSeason", true);
            lastingDaysOfEachTerm = builder.comment("The lasting days of each term (24 in total).")
                    .defineInRange("LastingDaysOfEachTerm", 7, 1, 30);
            initialSolarTermIndex = builder.comment("The index of the initial solar term.")
                    .defineInRange("InitialSolarTermIndex", 1, 1, 24);
            builder.pop();
        }
    }

    public static class Agriculture
    {
        public static ForgeConfigSpec.BooleanValue canUseBoneMeal;
        public static ForgeConfigSpec.BooleanValue useAshAsBoneMeal;
        public static ForgeConfigSpec.BooleanValue dropRiceGrains;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.push("Agriculture");
            canUseBoneMeal = builder.comment("Can bone meal be used to grow crops?")
                    .define("BoneMeal", true);
            useAshAsBoneMeal = builder.comment("Can ash be used as bone meal?")
                    .define("Ash", true);
            dropRiceGrains = builder.comment("Can grass drop rice grains?")
                    .define("DropRiceGrains", true);
            builder.pop();
        }
    }

    public static class Others
    {
        public static ForgeConfigSpec.BooleanValue woodDropsAshWhenBurning;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.push("Others");
            woodDropsAshWhenBurning = builder.comment("Wooden blocks will drop ashes when burning.")
                    .define("WoodDropsAshWhenBurning", true);
            builder.pop();
        }
    }
}

