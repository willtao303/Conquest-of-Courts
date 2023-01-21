package GameAssets;

import java.awt.Toolkit;

public class ScreenConsts {

    private final static int WINDOWEDWIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.95);//1600;
    private final static int WINDOWEDHEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.95);//1000;
    private final static int SCREENWIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final static int SCREENHEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static int WINDOWWIDTH = SCREENWIDTH;
    public static int WINDOWHEIGHT = SCREENHEIGHT;

    public final static int PIXELRARIO = 5;

    public static void FULLSCREEN_DIMS(){
        WINDOWWIDTH = SCREENWIDTH;
        WINDOWHEIGHT = SCREENHEIGHT;
    }
    public static void WINDOWED_DIMS(){
        WINDOWWIDTH = WINDOWEDWIDTH;
        WINDOWHEIGHT = WINDOWEDHEIGHT;
    }
}
