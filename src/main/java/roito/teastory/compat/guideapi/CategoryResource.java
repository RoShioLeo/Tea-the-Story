package roito.teastory.compat.guideapi;

import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageJsonRecipe;
import amerifrance.guideapi.page.PageText;
import amerifrance.guideapi.page.PageTextImage;
import net.minecraft.util.ResourceLocation;
import roito.teastory.TeaStory;
import roito.teastory.item.ItemRegister;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CategoryResource
{
    public static Map<ResourceLocation, EntryAbstract> build()
    {
        Map<ResourceLocation, EntryAbstract> resourceEntries = new LinkedHashMap<>();

        List<IPage> teaPage = new ArrayList<>();
        teaPage.add(new PageText("book.teastory.resource.tea.desc"));
        teaPage.add(new PageJsonRecipe(new ResourceLocation(TeaStory.MODID, "tea_leaf")));

        List<IPage> teaSeedPage = new ArrayList<>();
        teaSeedPage.add(new PageText("book.teastory.resource.tea_seed.desc"));
        teaSeedPage.add(new PageTextImage("book.teastory.resource.tea_seed.pic_desc",
                new ResourceLocation(TeaStory.MODID, "picture/tea_crop"), false));

        List<IPage> ricePage = new ArrayList<>();
        ricePage.add(new PageText("book.teastory.resource.rice.desc"));

        List<IPage> lemonPage = new ArrayList<>();
        lemonPage.add(new PageText("book.teastory.resource.lemon.desc"));

        resourceEntries.put(new ResourceLocation("teastory:tea"),
                new EntryItemStack(teaPage, "book.teastory.resource.tea.title", ItemRegister.tea_leaf.getDefaultInstance()));
        resourceEntries.put(new ResourceLocation("teastory:tea_seed"),
                new EntryItemStack(teaSeedPage, "book.teastory.resource.tea_seed.title", ItemRegister.tea_seeds.getDefaultInstance()));
        resourceEntries.put(new ResourceLocation("teastory:rice"),
                new EntryItemStack(ricePage, "book.teastory.resource.rice.title", ItemRegister.xian_rice_seeds.getDefaultInstance()));
        resourceEntries.put(new ResourceLocation("teastory:lemon"),
                new EntryItemStack(lemonPage, "book.teastory.resource.lemon.title", ItemRegister.lemon.getDefaultInstance()));

        return resourceEntries;
    }
}
