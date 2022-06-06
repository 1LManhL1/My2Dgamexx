package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManeger {
    GamePanel gp;
    public Tile [] tile;
    public int[][] mapTileNum;

    public TileManeger(GamePanel gp){
        this.gp=gp;
        tile=new Tile[10];
        getTileImage();
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/res/world01.txt");
    }
    public void loadMap(String filePath){
        try{
            InputStream is =getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line =br.readLine();

                while(col< gp.maxWorldCol){
                    String number[]=line.split(" ");
                    int num=Integer.parseInt(number[col]);
                    mapTileNum[row][col]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }
    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/res/grass.png"));

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/res/wall.png"));
            tile[1].collison=true;


            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/res/water.png"));
            tile[2].collison=true
            ;
            tile[3]=new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/res/earth.png"));

            tile[4]=new Tile();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/res/tree.png"));
            tile[4].collison=true;

            tile[5]=new Tile();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/res/sand.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){

            int tileNum=mapTileNum[worldRow][worldCol];

            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;

            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;
            if(     worldX>gp.player.worldX-gp.player.screenX-1*gp.tileSize &&
                    worldX<gp.player.worldX+gp.player.screenX+1*gp.tileSize &&
                    worldY>gp.player.worldY-gp.player.screenY-1*gp.tileSize &&
                    worldY<gp.player.worldY+gp.player.screenY+1*gp.tileSize
            ) {
                g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            }

            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
