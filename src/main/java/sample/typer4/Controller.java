package sample.typer4;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private javafx.scene.control.Label timeLabel;
    @FXML
    private javafx.scene.control.Label displayUserName;
    @FXML
    private Text total;
    @FXML
    private Text wpm;
    @FXML
    private Text invalid;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;


    public void setUpCircleAnimations(){

        //Circle 1
        ScaleTransition st1 = new ScaleTransition(Duration.seconds(1),circle1);
        st1.setFromX(1.0);
        st1.setToX(1.2);
        st1.setFromY(1.0);
        st1.setToY(1.2);
        st1.setCycleCount(Animation.INDEFINITE);
        st1.setAutoReverse(true);
        st1.play();

        //Circle2
        ScaleTransition st2 = new ScaleTransition(Duration.seconds(1.5),circle2);
        st2.setFromX(1.0);
        st2.setToX(1.3);
        st2.setFromY(1.0);
        st2.setToY(1.3);
        st2.setCycleCount(Animation.INDEFINITE);
        st2.setAutoReverse(true);
        st2.play();

        // Cirlce3
        ScaleTransition st3 = new ScaleTransition(Duration.seconds(2),circle3);
        st3.setFromX(1.0);
        st3.setToX(1.4);
        st3.setFromY(1.0);
        st3.setToY(1.4);
        st3.setCycleCount(Animation.INDEFINITE);
        st3.setAutoReverse(true);
        st3.play();



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpCircleAnimations();
        File newFile = new File("username.txt");
        if( newFile.exists() && newFile.length()!=0){
            Scanner reader = null;
            try{
                reader = new Scanner(newFile);

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            String data = reader.nextLine();
            displayUserName.setText("Welcome " +data);
        }

        // Setting the day
        Date date = new Date();
        Locale locale = new Locale("en");
        DateFormat formatter = new SimpleDateFormat("EEEE",locale);
        String strDay = formatter.format(date);
        timeLabel.setText("Today is " +strDay);

        int[] data = FileHandling.sumUpNumbers("src/main/java/data/");
        total.setText(String.valueOf(data[0]));
        wpm.setText(String.valueOf(Math.round(data[1] * 1.0 / data[3])));
        invalid.setText(String.valueOf(data[2]));
    }

    public void playGame (ActionEvent event) throws IOException {
        Main m = new Main();
        File newFile  = new File("username.txt");
        if(newFile.length()==0){
            try{
                m.changeScene("/sample/typer4/popup.fxml");

            } catch(IOException e){
                e.printStackTrace();
            }
        }
        else {
            m.changeScene("/sample/typer4/game.fxml");
        }
    }
}


