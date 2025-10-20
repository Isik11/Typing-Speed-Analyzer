package sample.typer4;


import org.apache.commons.lang3.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController implements Initializable {

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private int first = 1;
    private File saveData;
    private int wordCounter = 0;


    @FXML
    public Text seconds;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text accuracy;
    @FXML
    private Text programWord;
    @FXML
    private Text secondProgramWord;

    @FXML
    private TextField userWord;

    @FXML
    private ImageView correct;
    @FXML
    private ImageView wrong;

    @FXML
    private Button playAgain;

    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;


    ArrayList<String> words = new ArrayList<>();


    // Circle animation


    // add words to the arrayList
    public void addToList(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("wordsList"));
            String line = reader.readLine();
            while(line!= null){
                words.add(line);
                //read next Line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void toMainMenu(ActionEvent ae) throws IOException{
        Main m = new Main();
        m.changeScene("sample.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        playAgain.setVisible((false));
        playAgain.setDisable(true);
        seconds.setText("60");
        addToList();
        Collections.shuffle(words);
        programWord.setText(words.get(wordCounter));
        secondProgramWord.setText(words.get(wordCounter + 1));
        wordCounter++;


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File("src/main/java/data/" + formatter.format(date).strip() + ".txt");
        saveData.getParentFile().mkdirs(); /// creates the directory if it's missing
        try{
            if(saveData.createNewFile()){
                System.out.println("File created: " + saveData.getName());
            } else{
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private int  timer = 60;
    Runnable r = new Runnable() {

        @Override
        public void run() {
            if (timer > -1) {
                seconds.setText(String.valueOf(timer));
                timer -= 1;
            } else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");

                    try {
                        FileWriter myWriter = new FileWriter(saveData);
                        myWriter.write(countAll + ";");
                        myWriter.write(counter + ";");
                        myWriter.write(String.valueOf(countAll - counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer == -3) {
                    playAgain.setVisible(true);
                    playAgain.setDisable(false);
                    executor.shutdown();
                }
                timer -= 1;

            }
        }
    };

    Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
            correct.setOpacity(0);
            try{
                Thread.sleep(200);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try{
                Thread.sleep(200);
            } catch (InterruptedException e ){
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try{
                Thread.sleep(200);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            correct.setOpacity(0);
        }
    };

    Runnable fadeWrong  = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try{
                Thread.sleep(200);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try{
                Thread.sleep(200);
            } catch (InterruptedException e ){
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try{
                Thread.sleep(200);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };

    private int countAll = 0;
    private int counter = 0;
    public void startGame(KeyEvent ke){
        //only gets called once
        if(first == 1){
            first = 0;
            executor.scheduleAtFixedRate(r,0,1, TimeUnit.SECONDS);

        }
        if(ke.getCode().equals(KeyCode.ENTER)){
            String s = userWord.getText();
            String real = programWord.getText();
            countAll++;

// Debug: Check countAll increment



            int distance = StringUtils.getLevenshteinDistance(s,real);
            int threshold = 2;


            if (distance<= threshold){
                counter++;
                wordsPerMin.setText(String.valueOf(counter));

                // Debug: Check counter increment and distance
                // System.out.println("Counter incremented: " + counter + ", Distance: " + distance);
//        if(s.equals(real)){
//            counter++;
//            wordsPerMin.setText(String.valueOf(counter));

                Thread t = new Thread(fadeCorrect);
                t.start();
            }
            else {
                Thread t = new Thread(fadeWrong);
                t.start();
            }
            userWord.setText("");

            // Debug: Check accuracy calculation
            //double calculatedAccuracy = counter * 100.0 / countAll;
            // System.out.println("Calculated Accuracy: " + calculatedAccuracy);
            wordsPerMin.setText(String.valueOf(counter));
          double acc = ((double) counter / countAll) * 100;
          accuracy.setText(String.format("%.0f%%", acc));
//            accuracy.setText(String.valueOf (counter * 100.0 / countAll));

            //Wordcounter safety

            if (wordCounter + 1 < words.size()) {
                programWord.setText(words.get(wordCounter));
                secondProgramWord.setText(words.get(wordCounter + 1));
            } else {
                programWord.setText(words.get(wordCounter));
                secondProgramWord.setText("");
            }
            wordCounter++;
        }
    }
}



