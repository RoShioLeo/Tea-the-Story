package cloud.lemonslice.teastory.helper;

public final class ColorHelper
{
    public static int getRed(int color)
    {
        return color >> 16 & 255;
    }

    public static int getGreen(int color)
    {
        return color >> 8 & 255;
    }

    public static int getBlue(int color)
    {
        return color & 255;
    }

    public static int getAlpha(int color)
    {
        return color >> 24 & 255;
    }

    public static float getRedF(int color)
    {
        return getRed(color) / 255.0F;
    }

    public static float getGreenF(int color)
    {
        return getGreen(color) / 255.0F;
    }

    public static float getBlueF(int color)
    {
        return getBlue(color) / 255.0F;
    }

    public static float getAlphaF(int color)
    {
        return getAlpha(color) / 255.0F;
    }

    public static int simplyMixColor(int color1, float alpha1, int color2, float alpha2)
    {
        int red = (int) (getRed(color1) * alpha1 + getRed(color2) * alpha2);
        int green = (int) (getGreen(color1) * alpha1 + getGreen(color2) * alpha2);
        int blue = (int) (getBlue(color1) * alpha1 + getBlue(color2) * alpha2);
        int alpha = (int) (getAlpha(color1) * alpha1 + getAlpha(color2) * alpha2);
        return alpha << 24 | red << 16 | green << 8 | blue;
    }
}
