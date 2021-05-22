package cloud.lemonslice.teastory.helper;

import cloud.lemonslice.teastory.TeaStory;
import org.apache.logging.log4j.Level;

public final class LogHelper
{
    public static void error(String format, Object... data)
    {
        TeaStory.LOGGER.log(Level.ERROR, String.format(format, data));
    }

    public static void warn(String format, Object... data)
    {
        TeaStory.LOGGER.log(Level.WARN, String.format(format, data));
    }

    public static void info(String format, Object... data)
    {
        TeaStory.LOGGER.log(Level.INFO, String.format(format, data));
    }
}
