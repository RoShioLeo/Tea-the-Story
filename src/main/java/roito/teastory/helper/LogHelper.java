package roito.teastory.helper;

import org.apache.logging.log4j.Level;
import roito.teastory.TeaStory;

public class LogHelper
{
    public static void error(String format, Object... data)
    {
        TeaStory.logger.log(Level.ERROR, String.format(format, data));
    }

    public static void warn(String format, Object... data)
    {
        TeaStory.logger.log(Level.WARN, String.format(format, data));
    }

    public static void info(String format, Object... data)
    {
        TeaStory.logger.log(Level.INFO, String.format(format, data));
    }
}
