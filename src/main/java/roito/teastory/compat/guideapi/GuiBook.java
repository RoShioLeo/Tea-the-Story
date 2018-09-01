package roito.teastory.compat.guideapi;

import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageJsonRecipe;
import amerifrance.guideapi.page.PageText;
import amerifrance.guideapi.page.PageTextImage;
import net.minecraft.util.ResourceLocation;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.item.ItemRegister;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// TODO
@GuideBook
public class GuiBook implements IGuideBook
{
    public static BookBinder teaStoryBook;

    @Nullable
    @Override
    public Book buildBook()
    {
        // 设置基本信息
        teaStoryBook.setGuideTitle("book.teastory.tile");
        teaStoryBook.setItemName("book.teastory.book_name");
        teaStoryBook.setAuthor("book.teastory.author");
        teaStoryBook.setCreativeTab(CreativeTabsRegister.tabTeaStory);

        // 设置手册背景
        teaStoryBook.setColor(Color.blue);

        // 绘制分目录
        Map<ResourceLocation, EntryAbstract> resourceEntries = new LinkedHashMap<>();
        Map<ResourceLocation, EntryAbstract> teaEntries = new LinkedHashMap<>();
        Map<ResourceLocation, EntryAbstract> riceEntries = new LinkedHashMap<>();
        Map<ResourceLocation, EntryAbstract> otherEntries = new LinkedHashMap<>();

        // 资源篇章
        List<IPage> teaPage = new ArrayList<>();
        teaPage.add(new PageText("book.teastory.resource.tea.desc"));
        teaPage.add(new PageJsonRecipe(new ResourceLocation("teastory:tea_leaf")));

        List<IPage> teaSeedPage = new ArrayList<>();
        teaPage.add(new PageText("book.teastory.resource.tea_seed.desc"));
        teaPage.add(new PageTextImage("book.teastory.resource.tea_seed.pic_desc", new ResourceLocation("teastory:picture/tea_crop"), false));

        resourceEntries.put(new ResourceLocation("teastory:tea"), new EntryItemStack(teaPage, "book.teastory.resource.tea.title", ItemRegister.tea_leaf.getDefaultInstance()));
        resourceEntries.put(new ResourceLocation("teastory:tea_seed"), new EntryItemStack(teaSeedPage, "book.teastory.resource.tea_seed.title", ItemRegister.tea_seeds.getDefaultInstance()));

        return teaStoryBook.build();
    }
}
