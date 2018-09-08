package roito.teastory.compat.guideapi;

import amerifrance.guideapi.api.GuideBook;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.BookBinder;
import amerifrance.guideapi.category.CategoryItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import roito.teastory.TeaStory;
import roito.teastory.common.CreativeTabsRegister;
import roito.teastory.item.ItemRegister;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

@GuideBook(priority = EventPriority.HIGHEST)
public class GuiBook implements IGuideBook
{
    public static BookBinder teaStoryBook = new BookBinder(new ResourceLocation(TeaStory.MODID, "teastory_book"));

    @Nullable
    @Override
    public Book buildBook()
    {
        return teaStoryBook.setGuideTitle("book.teastory.tile")
                .setItemName("book.teastory.book_name")
                .setAuthor("book.teastory.author")
                .setCreativeTab(CreativeTabsRegister.tabTeaStory)
                .setColor(Color.blue)
                .build();
    }

    @Override
    public void handlePost(@Nonnull ItemStack bookStack)
    {
        teaStoryBook.addCategory(new CategoryItemStack(CategoryResource.build(), "book.teastory.resource.title", ItemRegister.tea_leaf.getDefaultInstance()));
        // TODO
    }

    @Nullable
    @Override
    public IRecipe getRecipe(@Nonnull ItemStack bookStack)
    {
        return null;
    }
}
