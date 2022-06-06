package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJBoosts extends SuperObject {
    public OBJBoosts(){
        name="Boots";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/res/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
