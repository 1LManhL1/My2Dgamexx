package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJKey extends SuperObject {
    public OBJKey(){
        name="Key";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/res/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
