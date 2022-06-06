package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJDoor extends SuperObject {
    public OBJDoor(){
        name="Door";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/res/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}
