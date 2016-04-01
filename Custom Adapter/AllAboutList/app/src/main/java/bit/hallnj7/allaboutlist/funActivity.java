package bit.hallnj7.allaboutlist;

import android.graphics.drawable.Drawable;

/**
 * Created by Nikaela on 1/04/2016.
 */
public class funActivity
{
    Drawable funImage;
    String activityName;

    public funActivity(String activityName, Drawable funImage)
    {
        this.activityName = activityName;
        this.funImage = funImage;
    }

    @Override
    public String toString()
    {
        return activityName;
    }
}
