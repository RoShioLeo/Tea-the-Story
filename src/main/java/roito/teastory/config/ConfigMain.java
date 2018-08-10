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
		
		@Config.Comment("Set it to false not to use tea residues as bone meal.")
		@Config.LangKey("teastory.config.general.residues")
		@Config.Name("UseTeaResidueAsBoneMeal")
		public boolean useTeaResidueAsBoneMeal = true;
	}
	
	public static final class Drink
	{
		@Config.Comment("The ticks of the effect of green tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.greentea")
		@Config.Name("GreenTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int greenTeaDrink_Time = 1800;
		
		@Config.Comment("The ticks of the effect of matcha. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.matchadrink")
		@Config.Name("MatchaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int matchaDrink_Time = 2400;
		
		@Config.Comment("The ticks of the effect of black tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.blacktea")
		@Config.Name("BlackTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int blackTeaDrink_Time = 1800;
		
		@Config.Comment("The ticks of the effect of milk tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.milktea")
		@Config.Name("MilkTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int milkTeaDrink_Time = 480;
		
		@Config.Comment("The ticks of the effect of lemon tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.lemontea")
		@Config.Name("LemonTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int lemonTeaDrink_Time = 480;
		
		@Config.Comment("The ticks of the effect of yellow tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.yellowtea")
		@Config.Name("YellowTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int yellowTeaDrink_Time = 300;
		
		@Config.Comment("The ticks of the effect of white tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.whitetea")
		@Config.Name("WhiteTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int whiteTeaDrink_Time = 1800;
		
		@Config.Comment("The ticks of the effect of oolong tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.oolongtea")
		@Config.Name("OolongTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int oolongTeaDrink_Time = 1800;
		
		@Config.Comment("The ticks of the effect of pu'er tea. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.drink.puertea")
		@Config.Name("PuerTeaDrinksEffectTime")
		@Config.RangeInt(min = 0, max = 12000)
		public int puerTeaDrink_Time = 1800;
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
		public static int dryingBasicTime = 800;
		
		@Config.Comment("The ticks of fermentation per leaf. (1 second = 20 ticks)")
		@Config.LangKey("teastory.config.teamaking.fermentationbasictime")
		@Config.Name("FermentationBasicTime")
		@Config.RangeInt(min = 0, max = 12000)
		public static int fermentationBasicTime = 1200;
	}
	
	public static final class Others
	{
		@Config.Comment("Give you nausea effect when eating lemons.")
		@Config.LangKey("teastory.config.others.lemon")
		@Config.Name("HaveNauseaEatingLemon")
		public boolean lemon = true;
	}
}
