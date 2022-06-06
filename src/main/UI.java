package main;

import object.OBJKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40,arial_80B;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinish=false;
    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");
    public UI(GamePanel  gp){
        this.gp=gp;
        arial_80B=new Font("Arial",Font.BOLD,80);
        arial_40=new Font("Arial",Font.PLAIN,40);
        OBJKey key=new OBJKey();
        keyImage=key.image;
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;

    }
    public void draw(Graphics2D g2){

        if(gameFinish==true){
            String text;
            int textLength;
            g2.setFont(arial_40);
            g2.setColor(Color.ORANGE);

            text="You found the treasure! ";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            int x=gp.screenWidth/2-textLength/2;
            int y=gp.screenHeight/2-gp.tileSize*3;
            g2.drawString(text,x,y);

            text="Your time is "+dFormat.format(playTime)+" !";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+gp.tileSize*4;
            g2.drawString(text,x,y);

            text="Congratulation! ";
            g2.setFont(arial_80B);
            g2.setColor(Color.ORANGE);
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+gp.tileSize*2;
            g2.drawString(text,x,y);

            gp.gameThread=null;

        }else{
        g2.setFont(arial_40);
        g2.setColor(Color.ORANGE);
        g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.hasKey,74,60);

        //TIME
            playTime+=(double) 1/60;
            g2.drawString("Time :" +dFormat.format(playTime),gp.tileSize*11,65);
        //MESSAGE
        if(messageOn==true){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,gp.tileSize,gp.tileSize*5);

            messageCounter++;

            if(messageCounter>120){
                messageCounter=0;
                messageOn=false;
            }
        }
    }
    }
}
