package roito.teastory.config;

import net.minecraftforge.common.config.Config;
import roito.teastory.TeaStory;

@Config(modid = TeaStory.MODID)
@Config.LangKey("teastory.config")
public final class ConfigMain
{
    @Config.Name("General")
    @Config.LangKey("teastory.config.general")
    public static final General general = new General();

    @Config.Name("Drink")
    @Config.LangKey("teastory.config.drink")
    public static final Drink drink = new Drink();

    @Config.Name("Tea Making")
    @Config.LangKey("teastory.config.teamaking")
    public static final TeaMaking teaMaking = new TeaMaking();

    @Config.Name("Others")
    @Config.LangKey("teastory.config.others")
    public static final Others others = new Others();

    public static final class General
    {
        @Config.Comment("Set it to false to let it not show information when players log in.")
        @Config.LangKey("teastory.config.general.enableinfo")
        @Config.Name("EnableInfo")
        public boolean info = true;

        @Config.Comment("The dropping chance of tea seeds. (‰)")
        @Config.LangKey("teastory.config.general.teaseedchance")
        @Config.Name("TeaSeedsDropChance")
        @Config.RangeInt(min = 0, max = 1000)
        public int teaSeedsDropChance = 8;

        @Config.Comment("The dropping chance of lemons. (‰)")
        @Config.LangKey("teastory.config.general.lemonchance")
        @Config.Name("LemonDropChance")
        @Config.RangeInt(min = 0, max = 1000)
        public int lemonDropChance = 8;

        @Config.RequiresMcRestart
        @Config.Comment("The relative probability of the seeds, where wheat seeds are 10.")
        @Config.LangKey("teastory.config.general.riceweight")
        @Config.Name("RiceSeedsDropWeight")
        @Config.RangeInt(min = 0, max = 1000)
        public int riceSeedsDropWeight = 5;

        @Config.Comment("Set it to false not to use tea residues as bone meal.")
        @Config.LangKey("teastory.config.general.residues")
        @Config.Name("UseTeaResidueAsBoneMeal")
        public boolean useTeaResidueAsBoneMeal = true;

        @Config.Comment("Set it to false not to use ore dictionary when washing rice.")
        @Config.LangKey("teastory.config.general.washrice")
        @Config.Name("UseOreDictionaryWhenWashingRice")
        public boolean useOreDictionaryWhenWashingRice = true;

        @Config.Comment("List of hoes which can't be identified.")
        @Config.LangKey("teastory.config.general.hoelist")
        @Config.Name("CustomHoeList")
        public String[] hoeList = new String[]{};

        @Config.Comment("List of spades which can't be identified.")
        @Config.LangKey("teastory.config.general.spadelist")
        @Config.Name("CustomSpadeList")
        public String[] spadeList = new String[]{};

        @Config.Comment("Set it to false to let it not show tooltips.")
        @Config.LangKey("teastory.config.general.tooltips")
        @Config.Name("EnableTooltips")
        public boolean tooltips = true;

        @Config.Comment("Set it to false to let it not show messages.")
        @Config.LangKey("teastory.config.general.blockMessage")
        @Config.Name("EnableBlockMessage")
        public boolean blockMessage = true;

        @Config.Comment("Set it to false to let it not show messages.")
        @Config.LangKey("teastory.config.general.guiMessage")
        @Config.Name("EnableGUIMessage")
        public boolean guiMessage = true;
    }

    public static final class Drink
    {
        @Config.Comment("The ticks of the effect of green tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.greentea.time")
        @Config.Name("GreenTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int greenTeaDrink_Time = 7200;

        @Config.Comment("The effect of green tea.")
        @Config.LangKey("teastory.config.drink.greentea.effect")
        @Config.Name("GreenTeaDrinksEffect")
        public String[] greenTeaDrink_Effect = {"teastory:agility"};

        @Config.Comment("The ticks of the effect of matcha. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.matchadrink.time")
        @Config.Name("MatchaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int matchaDrink_Time = 4800;

        @Config.Comment("The effect of matcha.")
        @Config.LangKey("teastory.config.drink.matchadrink.effect")
        @Config.Name("MatchaDrinksEffect")
        public String[] matchaDrink_Effect = {"minecraft:absorption"};

        @Config.Comment("The ticks of the effect of black tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.blacktea.time")
        @Config.Name("BlackTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int blackTeaDrink_Time = 7200;

        @Config.Comment("The effect of black tea.")
        @Config.LangKey("teastory.config.drink.blacktea.effect")
        @Config.Name("BlackTeaDrinksEffect")
        public String[] blackTeaDrink_Effect = {"minecraft:health_boost"};

        @Config.Comment("The ticks of the effect of milk tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.milktea.time")
        @Config.Name("MilkTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int milkTeaDrink_Time = 600;

        @Config.Comment("The effect of milk tea.")
        @Config.LangKey("teastory.config.drink.milktea.effect")
        @Config.Name("MilkTeaDrinksEffect")
        public String[] milkTeaDrink_Effect = {"minecraft:regeneration"};

        @Config.Comment("The ticks of the effect of lemon tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.lemontea.time")
        @Config.Name("LemonTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int lemonTeaDrink_Time = 1200;

        @Config.Comment("The effect of lemon tea.")
        @Config.LangKey("teastory.config.drink.lemontea.effect")
        @Config.Name("LemonTeaDrinksEffect")
        public String[] lemonTeaDrink_Effect = {"minecraft:regeneration"};

        @Config.Comment("The ticks of the effect of yellow tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.yellowtea.time")
        @Config.Name("YellowTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int yellowTeaDrink_Time = 600;

        @Config.Comment("The effect of yellow tea.")
        @Config.LangKey("teastory.config.drink.yellowtea.effect")
        @Config.Name("YellowTeaDrinksEffect")
        public String[] yellowTeaDrink_Effect = {"teastory:defence"};

        @Config.Comment("The ticks of the effect of white tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.whitetea.time")
        @Config.Name("WhiteTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int whiteTeaDrink_Time = 3600;

        @Config.Comment("The effect of white tea.")
        @Config.LangKey("teastory.config.drink.whitetea.effect")
        @Config.Name("WhiteTeaDrinksEffect")
        public String[] whiteTeaDrink_Effect = {"minecraft:haste"};

        @Config.Comment("The ticks of the effect of oolong tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.oolongtea.time")
        @Config.Name("OolongTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int oolongTeaDrink_Time = 7200;

        @Config.Comment("The effect of oolong tea.")
        @Config.LangKey("teastory.config.drink.oolongtea.effect")
        @Config.Name("OolongTeaDrinksEffect")
        public String[] oolongTeaDrink_Effect = {"teastory:photosynthesis"};

        @Config.Comment("The ticks of the effect of pu'er tea. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.drink.puertea.time")
        @Config.Name("PuerTeaDrinksEffectTime")
        @Config.RangeInt(min = 0, max = 72000)
        public int puerTeaDrink_Time = 3600;

        @Config.Comment("The effect of pu'er tea.")
        @Config.LangKey("teastory.config.drink.puertea.effect")
        @Config.Name("PuerTeaDrinksEffect")
        public String[] puerTeaDrink_Effect = {"teastory:life_drain"};
    }

    public static final class TeaMaking
    {
        @Config.Comment("Set it to false to disable the limitation of drying tea.")
        @Config.LangKey("teastory.config.teamaking.limited.drying")
        @Config.Name("IsDryingLimited")
        public boolean isDryingLimited = true;

        @Config.Comment("Set it to false to disable the limitation of fermentation.")
        @Config.LangKey("teastory.config.teamaking.limited.fermentation")
        @Config.Name("IsFermentationLimited")
        public boolean isFermentationLimited = true;

        @Config.Comment("Set it to false to disable the limitation of growing tea plant.")
        @Config.LangKey("teastory.config.teamaking.limited.teaplant")
        @Config.Name("IsTeaPlantLimited")
        public boolean isTeaPlantLimited = true;

        @Config.Comment("Set it to false to disable the limitation of growing rice crop.")
        @Config.LangKey("teastory.config.teamaking.limited.rice")
        @Config.Name("IsRiceLimited")
        public boolean isRiceLimited = true;

        @Config.Comment("The ticks of drying tea per leaf. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.teamaking.dryingbasictime")
        @Config.Name("DryingBasicTime")
        @Config.RangeInt(min = 0, max = 12000)
        public int dryingBasicTime = 800;

        @Config.Comment("The ticks of fermentation per leaf. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.teamaking.fermentationbasictime")
        @Config.Name("FermentationBasicTime")
        @Config.RangeInt(min = 0, max = 12000)
        public int fermentationBasicTime = 1200;

        @Config.Comment("The ticks of steaming per leaf. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.teamaking.steamingbasictime")
        @Config.Name("SteamingBasicTime")
        @Config.RangeInt(min = 0, max = 12000)
        public int steamingBasicTime = 1200;
    }

    public static final class Others
    {
        @Config.Comment("Give you nausea effect when eating lemons.")
        @Config.LangKey("teastory.config.others.lemon")
        @Config.Name("HaveNauseaEatingLemon")
        public boolean lemon = true;

        @Config.Comment("The ticks of cooking rice. (1 second = 20 ticks)")
        @Config.LangKey("teastory.config.others.ricecookingbasictime")
        @Config.Name("RiceCookingBasicTime")
        @Config.RangeInt(min = 0, max = 12000)
        public int riceCookingBasicTime = 1200;

        @Config.Comment("The number of rice balls you get each time you cook rice.")
        @Config.LangKey("teastory.config.others.cookriceballeachtime")
        @Config.Name("CookRiceBallEachTime")
        @Config.RangeInt(min = 1, max = 8)
        public int cookRiceBallEachTime = 2;
    }
}
