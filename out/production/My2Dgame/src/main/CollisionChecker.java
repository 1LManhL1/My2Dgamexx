package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public  CollisionChecker(GamePanel gp){
        this.gp=gp;
    }
    public void CheckTile(Entity entity){
        int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;

        int entityTopWorldY= entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.TileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2=gp.TileM.mapTileNum[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collison==true || gp.TileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.TileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2=gp.TileM.mapTileNum[entityBottomRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collison==true || gp.TileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.TileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2=gp.TileM.mapTileNum[entityTopRow][entityLeftCol];
                if(gp.TileM.tile[tileNum1].collison==true || gp.TileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.TileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2=gp.TileM.mapTileNum[entityTopRow][entityRightCol];
                if(gp.TileM.tile[tileNum1].collison==true || gp.TileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;

        }
    }
    public int checkObj(Entity entity, boolean player){
        int index=999;

        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //Get entity's solid area position
                entity.solidArea.x= entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //Get the object's area position
                gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision=true) entity.collisionOn=true;
                            if(player==true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision=true) entity.collisionOn=true;
                            if(player==true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision=true) entity.collisionOn=true;
                            if(player==true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision=true) entity.collisionOn=true;
                            if(player==true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].getSolidAreaDefaultY;
            }
        }
        return index;
    }
}
