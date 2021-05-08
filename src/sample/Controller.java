package sample;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    ComboBox mode_combo,diff_combo,marker_combo;//three ComboBoxes for user selection before starting game
    @FXML
    VBox vbox;//for enabling and disabling TIC TAC TOE BOARD
    @FXML
    AnchorPane anchorpane;//for adding another pane for printing ending animation
    @FXML
    Pane pane1,pane2,pane3,pane4,pane5,pane6,pane7,pane8,pane9;//9 panes for 9 places of 'X' and 'O'

//variables for switching marker, counting player moves, select player marker, random computer move (if there is no logic in 'resistive difficulty')
    //and marker selector for computer in digital respectively
int p=1,counter=0,selector=0,enemyrandom=0,AI=0;

//logic for determining wether AI mode is activated or not
boolean logic=false;

        //dynamic list for tracking game stats
    ArrayList<String> markerlist=new ArrayList<String>();

    // Method for choosing game mode
    public void setMode_combo(){
        if(mode_combo.getValue().equals("vs Player"))
            diff_combo.setDisable(true);//disabling and enabling difficulty box if user has selected 'P vs P' or 'P vs AI' mode
        else
            diff_combo.setDisable(false);
    }

    //method for choosing marker
    public void setMarker_combo() {
      //this function is called once however ,restricted this only one time for keeping the value of selector constant
        if(counter==0){
            if(marker_combo.getValue().equals("X")){p=0;selector=0;AI=1;}//setting different variables on the basis of user input
            else if(marker_combo.getValue().equals("O")){p=1;selector=1;AI=0;}
        }

    }
    //Method for staring the game
    public void start(){

            //setting logic after detecting that user has selected game mode
        if(mode_combo.getValue().equals("vs Computer"))logic=true;
        else logic=false;

        //disabling all user selection settings for not updatable after game has started
        mode_combo.setDisable(true);
        diff_combo.setDisable(true);
        marker_combo.setDisable(true);

        //activating playing board (at default it is disabled)
        vbox.setDisable(false);
        vbox.setOpacity(1);

        //calling marker method for selecting user marker after starting the game
        setMarker_combo();

        //Default initialize dynamic list with numbers 1 to 9 to reserve 9 spaces in the list for keep comparing values in between game
        for(int i=1;i<10;i++){
            markerlist.add(String.valueOf(i));
        }
        //initializing variable for every fresh start of the game
        AI=0;


    }

    //Method for resetting game
    public void reset(){
        //clearing every pane for next game
        pane1.getChildren().clear();
        pane2.getChildren().clear();
        pane3.getChildren().clear();
        pane4.getChildren().clear();
        pane5.getChildren().clear();
        pane6.getChildren().clear();
        pane7.getChildren().clear();
        pane8.getChildren().clear();
        pane9.getChildren().clear();

        //enabling every user selection tools
        mode_combo.setDisable(false);
        //but also keep checking what the game mode is
        if(mode_combo.getValue().equals("vs Player"))
        diff_combo.setDisable(true);
        else
            diff_combo.setDisable(false);
        marker_combo.setDisable(false);

        //disabling playing board when user reset the game
        vbox.setDisable(true);
        vbox.setOpacity(.5);
        //resetting variable for fresh start
        counter=0;
        markerlist.clear();
        AI=0;

        //removing additional pane for ending animation after user reset the game
            while(anchorpane.getChildren().size()>11)//removing every children in ending animation
            {//removing 11th space because every time a child removed, next one occupy its space
                anchorpane.getChildren().remove(11);
            }



    }


    /**-----------------------------------------METHODS FOR MOUSE HOVER ANIMATION ON EVERY 9 PANES------------------------------------------------**/
    public void hoverpane1() {pane1.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane2() {pane2.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane3() {pane3.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane4() {pane4.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane5() {pane5.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane6() {pane6.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane7() {pane7.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane8() {pane8.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}
    public void hoverpane9() {pane9.setBackground(new Background(new BackgroundFill(Paint.valueOf("b3ccff"), new CornerRadii(10), Insets.EMPTY)));}

/**----------------------------------------------- HERE THE PRINTING OF MARKERS TAKES PLACE--------------------------------------------------------**/

/**---------------------------------MethodS for printing marker on pane 1 and all other methods are identical---------------------------------------**/
    public void setpane1(){

        //marker is printed only if the pane is empty i.e. there is no marker printed here before
         if(pane1.getChildren().isEmpty()) {
             //adding the marker the pane of desired attributes by calling 'printMarker' method
            pane1.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));

            //p is used to switch marker (its value jumps between 0 and 1)
             p=1-p;counter++;//increasing counter for counting markers

             //Switching value of marker by using index (i.e. switching between 0 and 1 using 'p')
            marker_combo.setValue(marker_combo.getItems().get(p));

            //adding marker symbol to the 0 index of the dynamic list by extracting 'C' and 'L' by a long text line
             markerlist.set(0,pane1.getChildren().get(0).toString().substring(0,1));

             //calling this method for checking if combination of 3 is matched or not and end this game right now
             WIN();

             if(logic==true)logic();//if user selected AI mode then next move is played by computer

        }

    }
    public void setpane2(){
        if(pane2.getChildren().isEmpty()) {// System.out.println(p);
             pane2.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(1,pane2.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();
        }

    }
    public void setpane3(){
        if(pane3.getChildren().isEmpty()) { //System.out.println(p);
            pane3.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(2,pane3.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();
        }
    }
    public void setpane4(){
        if(pane4.getChildren().isEmpty()) {// System.out.println(p);
            pane4.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(3,pane4.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();
        }
    }
    public void setpane5(){
        if(pane5.getChildren().isEmpty()) { //System.out.println(p);
            pane5.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(4,pane5.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();
        }

    }
    public void setpane6(){
        if(pane6.getChildren().isEmpty()){ //System.out.println(p);
            pane6.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(5,pane6.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();
        }
    }
    public void setpane7(){
        if(pane7.getChildren().isEmpty()) { //System.out.println(p);
            pane7.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,200,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

            markerlist.set(6,pane7.getChildren().get(0).toString().substring(0,1));

            WIN();

            if(logic==true)logic();


        }
    }
    public void setpane8(){
            if(pane8.getChildren().isEmpty()) {// System.out.println(p);
            pane8.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
                p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));

                markerlist.set(7,pane8.getChildren().get(0).toString().substring(0,1));

                WIN();

                if(logic==true)logic();
        }

    }
    public void setpane9(){
        if(pane9.getChildren().isEmpty()) { //System.out.println(p);
            pane9.getChildren().setAll(printMarker(40,30,70,60,55,45,15,1.5,0,1));
            p=1-p;counter++;
            marker_combo.setValue(marker_combo.getItems().get(p));;

            markerlist.set(8,pane9.getChildren().get(0).toString().substring(0,1));
            WIN();
            if(logic==true)logic();

    }

    }

    /**----------------------------METHODS FOR REMOVING HOVERING ANIMATIONS AFTER MOUSE IS MOVED OUT TOF THE PANE-----------------------------------**/
    public void removePane1(){ pane1.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane2(){ pane2.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane3(){ pane3.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane4(){ pane4.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane5(){ pane5.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane6(){ pane6.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane7(){ pane7.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane8(){ pane8.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }
    public void removePane9(){ pane9.setBackground(new Background(new BackgroundFill(Paint.valueOf("ffffff"), CornerRadii.EMPTY, Insets.EMPTY))); }

    //Method for Generating LINE OR CIRCLE based on what pane want
    public ArrayList printMarker(int linestartX,int linestartY,int lineendX,int lineendY,int circleX,int circleY,int circleradius
                                     ,double scale, int delay,int strokewidth){
        //creating dynamic list only for returning group of objects whether  its 2 lines (X) or Circle(O)
        ArrayList linelist = new ArrayList<>();

        //CREATING a new line and setting different properties to it from method attributes provided by CALLER
        Line line1=new Line();
        line1.setStartX(linestartX);line1.setStartY(linestartY);//starting coordinate
        line1.setEndX(lineendX);line1.setEndY(lineendY);//ending coordinate
        line1.setStrokeWidth(strokewidth);
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line1.setStroke(Paint.valueOf("663366"));

        //ANOTHER line for creating 'X'
        Line line2=new Line();
        line2.setStartX(lineendX);line2.setStartY(linestartY);
        line2.setEndX(linestartX);line2.setEndY(lineendY);
        line2.setStrokeWidth(strokewidth);
        line2.setStrokeLineCap(StrokeLineCap.ROUND);
        line2.setStroke(Paint.valueOf("663366"));

        //CREATING a Circle and setting different properties to it from method attributes provided by CALLER
        Circle circle=new Circle();
        circle.setCenterX(circleX);circle.setCenterY(circleY);//center coordinate
        circle.setRadius(circleradius);//radius
        circle.setStrokeWidth(strokewidth);
        circle.setFill(Color.TRANSPARENT);//Setting Transparent color inside circle
        circle.setStroke(Paint.valueOf("663366"));

        //Scaling transitions for Stretching the Lines and Circle for creating animation
        ScaleTransition animation1=new ScaleTransition();
        animation1.setNode(line1);
        animation1.setDuration(Duration.millis(200));
        animation1.setByX(scale);animation1.setByY(scale);//scaling factor provided by CALLER
        animation1.setDelay(Duration.millis(delay));
        animation1.play();

        ScaleTransition animation2=new ScaleTransition();
        animation2.setNode(line2);
        animation2.setDuration(Duration.millis(200));
        animation2.setByX(scale);animation2.setByY(scale);
        animation2.setDelay(Duration.millis(delay));
        animation2.play();

        ScaleTransition animation3=new ScaleTransition();
        animation3.setNode(circle);
        animation3.setDuration(Duration.millis(200));
        animation3.setByX(scale);animation3.setByY(scale);
        animation3.setDelay(Duration.millis(delay));
        animation3.play();

        //Adding lines or circle to the dynamic list on the basis of current marker
        if(marker_combo.getValue().equals("X")){ linelist.add(line1);linelist.add(line2); }
        else if(marker_combo.getValue().equals("O")) linelist.add(circle);

        //returning this list to CALLER so that marker is printed present in the list
        return linelist;

    }

    //HERE IS THE LOGIC OF THE COMPUTER CONTROLLED
    public void logic(){
        //Conditions on the basis what user has selected the difficulty
        if(diff_combo.getValue().equals("KID")) {
            //Easiest difficulty, in which AI place marker randomly
            randommarker(0);//0 for completely random
        }
        else if(diff_combo.getValue().equals("ENEMY")){
            //Second difficulty, in which AI resist the 3 matches of the player
             resistivedifficulty();
        }
        else  if(diff_combo.getValue().equals("GOD")) {
            //Impossible difficulty, in which u cannot WIN because it uses MIN_MAX algorithm

                    //checking if the middle space is empty after user has entered on another space
                    if (pane5.getChildren().isEmpty() && AI==0){
                        setpane5();AI=1;//increasing this variable for allowing this execute only once
                    }
                //checking if user has entered a cross corner move after placing marker other than middle so that AI plays on the edge
                    // to make its 3 match combination ready in PLUS symbol
                else if((markerlist.get(0).equals(markerlist.get(8)) || markerlist.get(2).equals(markerlist.get(6))) && AI==1){
                    randommarker(1);//1 for making random moves in PLUS symbol
                    AI=2;
                }
                //checking if user has entered in middle so that AI will play on corners
                else if(!pane5.getChildren().isEmpty() && AI==0){
                    randommarker(2);//2 for making random moves in corners
                    AI=1;
                }
                //checking if user has entered at corner after placing in middle
                else if((markerlist.get(0).equals(markerlist.get(4)) || markerlist.get(2).equals(markerlist.get(4)) ||
                            markerlist.get(6).equals(markerlist.get(4)) ||  markerlist.get(8).equals(markerlist.get(4))) && AI==1){
                    randommarker(2);
                    AI=2;
                }


                else {resistivedifficulty();}//if above conditions doesn't follow then AI plays a completely random mode
        }

    }

    //Method for generating resistive mode for user
    public void resistivedifficulty(){

        //each condition is for placing marker in such a way that user should not complete its match 3 combination
        //here indexing is used in this way only to identify pane correctly (index=pane number - 1)
                if (markerlist.get(2 - (1)).equals(markerlist.get(3 - (1))) && pane1.getChildren().isEmpty() && p!=selector) {//1st horizontal line
                setpane1();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(2 - (1))) && pane3.getChildren().isEmpty() && p!=selector) {
                setpane3();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(3 - (1))) && pane2.getChildren().isEmpty() && p!=selector) {
                setpane2();enemyrandom = 1; }
            else if (markerlist.get(5 - (1)).equals(markerlist.get(6 - (1))) && pane4.getChildren().isEmpty() && p!=selector) {//2nd horizontal line
                setpane4();enemyrandom = 1; }
            else if (markerlist.get(4 - (1)).equals(markerlist.get(5 - (1))) && pane6.getChildren().isEmpty() && p!=selector) {
                setpane6();enemyrandom = 1; }
            else if (markerlist.get(4 - (1)).equals(markerlist.get(6 - (1))) && pane5.getChildren().isEmpty() && p!=selector) {
                setpane5();enemyrandom = 1; }
            else if (markerlist.get(8 - (1)).equals(markerlist.get(9 - (1))) && pane7.getChildren().isEmpty() && p!=selector) {//3rd horizontal line
                setpane7();enemyrandom = 1; }
            else if (markerlist.get(7 - (1)).equals(markerlist.get(8 - (1))) && pane9.getChildren().isEmpty() && p!=selector) {
                setpane9();enemyrandom = 1; }
            else if (markerlist.get(7 - (1)).equals(markerlist.get(9 - (1))) && pane8.getChildren().isEmpty() && p!=selector) {
                setpane8();enemyrandom = 1; }
            else if (markerlist.get(4 - (1)).equals(markerlist.get(7 - (1))) && pane1.getChildren().isEmpty() && p!=selector) {//1st vertical line
                setpane1();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(7 - (1))) && pane4.getChildren().isEmpty() && p!=selector) {
                setpane4();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(4 - (1))) && pane7.getChildren().isEmpty() && p!=selector) {
                setpane7();enemyrandom = 1; }
            else if (markerlist.get(5 - (1)).equals(markerlist.get(8 - (1))) && pane2.getChildren().isEmpty() && p!=selector) {//2nd vertical line
                setpane2();enemyrandom = 1; }
            else if (markerlist.get(2 - (1)).equals(markerlist.get(8 - (1))) && pane5.getChildren().isEmpty() && p!=selector) {
                setpane5();enemyrandom = 1; }
            else if (markerlist.get(2 - (1)).equals(markerlist.get(5 - (1))) && pane8.getChildren().isEmpty() && p!=selector) {
                setpane8();enemyrandom = 1; }
            else if (markerlist.get(6 - (1)).equals(markerlist.get(9 - (1))) && pane3.getChildren().isEmpty() && p!=selector) {//3rd vertical line
                setpane3();enemyrandom = 1; }
            else if (markerlist.get(3 - (1)).equals(markerlist.get(9 - (1))) && pane6.getChildren().isEmpty() && p!=selector) {
                setpane6();enemyrandom = 1; }
            else if (markerlist.get(3 - (1)).equals(markerlist.get(6 - (1))) && pane9.getChildren().isEmpty() && p!=selector) {
                setpane9();enemyrandom = 1; }
            else if (markerlist.get(5 - (1)).equals(markerlist.get(9 - (1))) && pane1.getChildren().isEmpty() && p!=selector) {// '\' diagonal
                setpane1();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(9 - (1))) && pane5.getChildren().isEmpty() && p!=selector) {
                setpane5();enemyrandom = 1; }
            else if (markerlist.get(1 - (1)).equals(markerlist.get(5 - (1))) && pane9.getChildren().isEmpty() && p!=selector) {
                setpane9();enemyrandom = 1; }
            else if (markerlist.get(5 - (1)).equals(markerlist.get(7 - (1))) && pane3.getChildren().isEmpty() && p!=selector) {// '/' diagonal
                setpane3();enemyrandom = 1; }
            else if (markerlist.get(3 - (1)).equals(markerlist.get(7 - (1))) && pane5.getChildren().isEmpty() && p!=selector) {
                setpane5();enemyrandom = 1; }
            else if (markerlist.get(3 - (1)).equals(markerlist.get(5 - (1))) && pane7.getChildren().isEmpty() && p!=selector) {
                setpane7();enemyrandom = 1; }
        //if above conditions are true then random condition must not run run that's why enemyrandom  variable is set to 1

        //if above conditions are not run then AI plays a completely random move
        if(enemyrandom==0) randommarker(0);
        enemyrandom=0;

    }

    //Method for Generating random moves
    public void randommarker(int selectiverandom){
        int random=0;
        Random rand=new Random();//generating random number

        //loop because ,maybe a pane is generated randomly which already has a marker on it then this process should continue until a successful
        //execution of AI move must happen
        while(counter<9) {

            if(selectiverandom==0)random=1+rand.nextInt(9);//generating numbers between 1 and 10
        else if(selectiverandom==1)random=2+2*rand.nextInt(5);// generating even numbers between 1 to 10
        else if(selectiverandom==2)random=1+2*rand.nextInt(5);//generating odd numbers between 1 to 10

            //when AI plays its move then value of 'p' is also switched so we break out this loop
            // otherwise it does not change its value until AI plays its move so this loop will continue
            if(p==selector)break;

            switch(random){
                case 1:setpane1();break;
                case 2:setpane2();break;
                case 3:setpane3();break;
                case 4:setpane4();break;
                case 5:setpane5();break;
                case 6:setpane6();break;
                case 7:setpane7();break;
                case 8:setpane8();break;
                case 9:setpane9();break;

            }

        }
    }

    public void WIN(){

        //Creating a line for a completed match 3 combination to display when game ends
        Line line=new Line();
        line.setStroke(Color.GREEN);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setStrokeWidth(2);

        //Creating a Scaling animation for line
        ScaleTransition transition=new ScaleTransition();
        transition.setNode(line);
        transition.setDuration(Duration.millis(200));
        transition.setByX(3);transition.setByY(3);

        /**--HERE THE PROCESS OF CHECKING IS INITIATED , i.e. IN WHICH WAY A MATCH 3 COMBINATION IS COMPLETED HORIZONTALLY,VERTICALLY OR DIAGONALLY--**/
       if(markerlist.get(0).equals(markerlist.get(1)) && markerlist.get(1).equals(markerlist.get(2))){//checking 1st horizontal line

           line.setStartX(345);line.setStartY(145);
           line.setEndX(425);line.setEndY(145);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();

       }
       else if(markerlist.get(3).equals(markerlist.get(4)) && markerlist.get(4).equals(markerlist.get(5))){//checking 2nd horizontal line

           line.setStartX(345);line.setStartY(244);
           line.setEndX(425);line.setEndY(244);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();

       }
       else if(markerlist.get(6).equals(markerlist.get(7)) && markerlist.get(7).equals(markerlist.get(8))){//checking 3rd horizontal line

           line.setStartX(345);line.setStartY(341);
           line.setEndX(425);line.setEndY(341);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();
       }
       else if(markerlist.get(0).equals(markerlist.get(3)) && markerlist.get(3).equals(markerlist.get(6))){//checking 1st vertical line

           line.setStartX(275);line.setStartY(210);
           line.setEndX(275);line.setEndY(280);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();

       }
       else if(markerlist.get(1).equals(markerlist.get(4)) && markerlist.get(4).equals(markerlist.get(7))){//checking 2nd vertical line

           line.setStartX(388);line.setStartY(210);
           line.setEndX(388);line.setEndY(280);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();
       }
       else if(markerlist.get(2).equals(markerlist.get(5)) && markerlist.get(5).equals(markerlist.get(8))){//checking 3rd vertical line

           line.setStartX(502);line.setStartY(210);
           line.setEndX(502);line.setEndY(280);
           anchorpane.getChildren().add(line);
           transition.play();
           endanimation();
       }
       else if(markerlist.get(0).equals(markerlist.get(4)) && markerlist.get(4).equals(markerlist.get(8))){//checking '\' diagonal line

           line.setStartX(348);line.setStartY(208);
           line.setEndX(422);line.setEndY(274);
           anchorpane.getChildren().add(line);

           transition.play();
           endanimation();
       }
       else if(markerlist.get(2).equals(markerlist.get(4)) && markerlist.get(4).equals(markerlist.get(6))){//checking '/' diagonal line

           line.setStartX(424);line.setStartY(208);
           line.setEndX(356);line.setEndY(274);
           anchorpane.getChildren().add(line);
           transition.play();

            //calling final method i.e. that plays ending animation
           endanimation();



       }



    }

    public void endanimation(){
                //when game has detected a match 3 combination has completed and AI has turn so it should not play its move
                counter=10;
                p=1-p;//for getting which marker is winner otherwise the marker is switched
                marker_combo.setValue(marker_combo.getItems().get(p));

                //when game is completed it should disable playing board to stop further moves
                vbox.setDisable(true);
                vbox.setOpacity(.5);


                //new pane is created to that play ending animation on it
                Pane newpane=new Pane();

        newpane.setLayoutX(vbox.getLayoutX());newpane.setLayoutY(vbox.getLayoutY());//x coordinate same as VBOX
        newpane.setPrefWidth(vbox.getWidth());newpane.setPrefHeight(vbox.getHeight());//y coordinate same as VBOX
        newpane.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));//setting BG color to white

        //Creating a label to display WIN message
        Label label=new Label("WIN");
        label.setScaleX(3); label.setScaleY(3);
        label.setTextFill(Color.PURPLE);
        label.setLayoutX(150);label.setLayoutY(235);

        //newly created pane is requesting to print and animate which marker has won
        newpane.getChildren().addAll(printMarker(152,120,178,146,165,125,12,
                7,500,1));//and this time it gives values in such a way that 'X' and 'O' is printed BIGGER

        newpane.getChildren().addAll(label);//adding label to the newly created pane


        anchorpane.getChildren().add(newpane);//adding this pane the main ANCHOR PANE (main window)

        //fade transition for new pane so that previous match 3 animation should also be shown
        FadeTransition f=new FadeTransition(Duration.millis(500),newpane);
        f.setFromValue(.1);//fading IN
        f.setToValue(1);
        f.play();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //added different options of game modes and selected player by default
        mode_combo.getItems().addAll("vs Player","vs Computer");mode_combo.setValue("vs Player");

        //added different options of Difficulties and selected KID by default
        diff_combo.getItems().addAll("KID","ENEMY","GOD");diff_combo.setValue("KID");

        //added different options of markers and selected X by default
        marker_combo.getItems().addAll("X","O");marker_combo.setValue("X");


        setMarker_combo();//for selecting default marker
        diff_combo.setDisable(true);//by default difficulty option should be disabled because game mode is set to P vs P
        vbox.setDisable(true);//by default playing board is disabled
        vbox.setOpacity(.5);
    }
}
