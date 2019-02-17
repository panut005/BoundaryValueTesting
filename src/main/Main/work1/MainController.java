package work1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private int type = 1;
    String textArea = "";
    private static final String fileName = "data.txt";
    @FXML TextField xMaxValue, xMinValue, yMaxValue, yMinValue;
    @FXML TextArea xyValueTextArea;
    @FXML Button calculateButton, clearButton;
    @FXML ChoiceBox typeChoiceBox;
    @FXML Circle a1Circle, b1Circle, c1Circle, d1Circle, e1Circle, f1Circle, g1Circle, h1Circle, i1Circle,
         a3Circle, b3Circle, c3Circle, d3Circle, e3Circle, f3Circle, g3Circle, h3Circle, i3Circle,
         a7Circle, b7Circle, c7Circle, d7Circle, e7Circle, f7Circle, g7Circle, h7Circle, i7Circle,
         a9Circle, b9Circle, c9Circle, d9Circle, e9Circle, f9Circle, g9Circle, h9Circle, i9Circle,
         a2Circle, b2Circle, c2Circle,
         a4Circle, b4Circle, c4Circle,
         a6Circle, b6Circle, c6Circle,
         a8Circle, b8Circle, c8Circle;
    @FXML Text a1Text, b1Text, c1Text, d1Text, e1Text, f1Text, g1Text, h1Text, i1Text,
         a3Text, b3Text, c3Text, d3Text, e3Text, f3Text, g3Text, h3Text, i3Text,
         a7Text, b7Text, c7Text, d7Text, e7Text, f7Text, g7Text, h7Text, i7Text,
         a9Text, b9Text, c9Text, d9Text, e9Text, f9Text, g9Text, h9Text, i9Text,
         a2Text, b2Text, c2Text,
         a4Text, b4Text, c4Text,
         a6Text, b6Text, c6Text,
         a8Text, b8Text, c8Text;


    public void initialize() {
        calculateButton.setDisable(false);
        clearButton.setDisable(true);
        setDrawingTypeOne();
        setTypesChoiceBox();
    }


    private void setTypesChoiceBox() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Boundary Value testing of n inputs: 4n+1");
        types.add("Robustness testing of n inputs: 6n+1");
        types.add("Worst case for boundary value: 5^n");
        types.add("Worst case for robustness: 7^n");
        typeChoiceBox.setStyle("-fx-font-size:20");
        typeChoiceBox.setItems(types);
        typeChoiceBox.getSelectionModel().selectFirst();
        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue typeObservable, Object typeOldValue, Object typeNewValue) {
                if (typeObservable.getValue().toString().equals("Boundary Value testing of n inputs: 4n+1")) {
                    type = 1;
                    setDrawingTypeOne();
                } else if (typeObservable.getValue().toString().equals("Robustness testing of n inputs: 6n+1")) {
                    type = 2;
                    setDrawingTypeTwo();
                } else if (typeObservable.getValue().toString().equals("Worst case for boundary value: 5^n")) {
                    type = 3;
                    setDrawingTypeThree();
                } else if (typeObservable.getValue().toString().equals("Worst case for robustness: 7^n")) {
                    type = 4;
                    setDrawingTypeFour();
                }
            }
        });
    }

    public void calculateOnAction() {
        if (!xMaxValue.getText().equals("") && !xMinValue.getText().equals("") && !yMaxValue.getText().equals("") && !yMinValue.getText().equals("")) {
            if (Float.parseFloat(xMaxValue.getText()) <= Float.parseFloat(xMinValue.getText()) ||
                    Float.parseFloat(yMaxValue.getText()) <= Float.parseFloat(yMinValue.getText())){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR,"Max is less than or equals min");
                errorAlert.setTitle("ERROR!");
                errorAlert.setHeaderText("");
                errorAlert.showAndWait();
            }else {
                if (type == 1) {
                    textArea = "Boundary Value testing of n inputs: 4n+1\n";
                    textArea = textArea + "Min X: " + xMinValue.getText() + "," + " Max X: " + xMaxValue.getText() +"\n";
                    textArea = textArea + "Min Y: " + yMinValue.getText() + "," + " Max Y: " + yMaxValue.getText() +"\n";
                    setValueTopCenter();
                    setValueMiddleLeft();
                    setValueMiddleCenter();
                    setValueMiddleRight();
                    setValueBottomCenter();
                } else if (type == 2) {
                    textArea = "Robustness testing of n inputs: 6n+1\n";
                    textArea = textArea + "Min X: " + xMinValue.getText() + "," + " Max X: " + xMaxValue.getText() +"\n";
                    textArea = textArea + "Min Y: " + yMinValue.getText() + "," + " Max Y: " + yMaxValue.getText() +"\n";
                    setValueTopCenter();
                    setValueMiddleLeft();
                    setValueMiddleCenter();
                    setValueMiddleRight();
                    setValueBottomCenter();
                } else if (type == 3) {
                    textArea = "Worst case for boundary value: 5^n\n";
                    textArea = textArea + "Min X: " + xMinValue.getText() + "," + "Max X: " + xMaxValue.getText() +"\n";
                    textArea = textArea + "Min Y: " + yMinValue.getText() + "," + "Max Y: " + yMaxValue.getText() +"\n";
                    setValueTopLeft();
                    setValueTopCenter();
                    setValueTopRight();
                    setValueMiddleLeft();
                    setValueMiddleCenter();
                    setValueMiddleRight();
                    setValueBottomLeft();
                    setValueBottomCenter();
                    setValueBottomRight();
                } else if (type == 4) {
                    textArea = "Worst case for robustness: 7^n\n";
                    textArea = textArea + "Min X : " + xMinValue.getText() + "," + " Max X : " + xMaxValue.getText() +"\n";
                    textArea = textArea + "Min Y : " + yMinValue.getText() + "," + " Max Y : " + yMaxValue.getText() +"\n";
                    setValueTopLeft();
                    setValueTopCenter();
                    setValueTopRight();
                    setValueMiddleLeft();
                    setValueMiddleCenter();
                    setValueMiddleRight();
                    setValueBottomLeft();
                    setValueBottomCenter();
                    setValueBottomRight();
                }
                clkSave();
                typeChoiceBox.setDisable(true);
                calculateButton.setDisable(true);
                clearButton.setDisable(false);

            }

        }
    }

    public void clearOnAction() {
        xMinValue.setText("");
        xMaxValue.setText("");
        yMinValue.setText("");
        yMaxValue.setText("");
        xyValueTextArea.clear();
        textArea ="";
        typeChoiceBox.setDisable(false);
        calculateButton.setDisable(false);
        clearButton.setDisable(true);
    }

    private void setDrawingTypeOne() { //Type 1
        a1Circle.setVisible(false);a1Text.setVisible(false);
        b1Circle.setVisible(false);b1Text.setVisible(false);
        c1Circle.setVisible(false);c1Text.setVisible(false);
        d1Circle.setVisible(false);d1Text.setVisible(false);
        e1Circle.setVisible(false);e1Text.setVisible(false);
        f1Circle.setVisible(false);f1Text.setVisible(false);
        g1Circle.setVisible(false);g1Text.setVisible(false);
        h1Circle.setVisible(false);h1Text.setVisible(false);
        i1Circle.setVisible(false);i1Text.setVisible(false);

        a3Circle.setVisible(false);a3Text.setVisible(false);
        b3Circle.setVisible(false);b3Text.setVisible(false);
        c3Circle.setVisible(false);c3Text.setVisible(false);
        d3Circle.setVisible(false);d3Text.setVisible(false);
        e3Circle.setVisible(false);e3Text.setVisible(false);
        f3Circle.setVisible(false);f3Text.setVisible(false);
        g3Circle.setVisible(false);g3Text.setVisible(false);
        h3Circle.setVisible(false);h3Text.setVisible(false);
        i3Circle.setVisible(false);i3Text.setVisible(false);

        a7Circle.setVisible(false);a7Text.setVisible(false);
        b7Circle.setVisible(false);b7Text.setVisible(false);
        c7Circle.setVisible(false);c7Text.setVisible(false);
        d7Circle.setVisible(false);d7Text.setVisible(false);
        e7Circle.setVisible(false);e7Text.setVisible(false);
        f7Circle.setVisible(false);f7Text.setVisible(false);
        g7Circle.setVisible(false);g7Text.setVisible(false);
        h7Circle.setVisible(false);h7Text.setVisible(false);
        i7Circle.setVisible(false);i7Text.setVisible(false);

        a9Circle.setVisible(false);a9Text.setVisible(false);
        b9Circle.setVisible(false);b9Text.setVisible(false);
        c9Circle.setVisible(false);c9Text.setVisible(false);
        d9Circle.setVisible(false);d9Text.setVisible(false);
        e9Circle.setVisible(false);e9Text.setVisible(false);
        f9Circle.setVisible(false);f9Text.setVisible(false);
        g9Circle.setVisible(false);g9Text.setVisible(false);
        h9Circle.setVisible(false);h9Text.setVisible(false);
        i9Circle.setVisible(false);i9Text.setVisible(false);

        a2Circle.setVisible(false);a2Text.setVisible(false);
        b2Circle.setVisible(true);b2Text.setVisible(true);
        c2Circle.setVisible(true);c2Text.setVisible(true);

        a4Circle.setVisible(false);a4Text.setVisible(false);
        b4Circle.setVisible(true);b4Text.setVisible(true);
        c4Circle.setVisible(true);c4Text.setVisible(true);

        a6Circle.setVisible(true);a6Text.setVisible(true);
        b6Circle.setVisible(true);b6Text.setVisible(true);
        c6Circle.setVisible(false);c6Text.setVisible(false);

        a8Circle.setVisible(true);a8Text.setVisible(true);
        b8Circle.setVisible(true);b8Text.setVisible(true);
        c8Circle.setVisible(false);c8Text.setVisible(false);
    }

    private void setDrawingTypeTwo() { //Type 2
        a1Circle.setVisible(false);a1Text.setVisible(false);
        b1Circle.setVisible(false);b1Text.setVisible(false);
        c1Circle.setVisible(false);c1Text.setVisible(false);
        d1Circle.setVisible(false);d1Text.setVisible(false);
        e1Circle.setVisible(false);e1Text.setVisible(false);
        f1Circle.setVisible(false);f1Text.setVisible(false);
        g1Circle.setVisible(false);g1Text.setVisible(false);
        h1Circle.setVisible(false);h1Text.setVisible(false);
        i1Circle.setVisible(false);i1Text.setVisible(false);

        a3Circle.setVisible(false);a3Text.setVisible(false);
        b3Circle.setVisible(false);b3Text.setVisible(false);
        c3Circle.setVisible(false);c3Text.setVisible(false);
        d3Circle.setVisible(false);d3Text.setVisible(false);
        e3Circle.setVisible(false);e3Text.setVisible(false);
        f3Circle.setVisible(false);f3Text.setVisible(false);
        g3Circle.setVisible(false);g3Text.setVisible(false);
        h3Circle.setVisible(false);h3Text.setVisible(false);
        i3Circle.setVisible(false);i3Text.setVisible(false);

        a7Circle.setVisible(false);a7Text.setVisible(false);
        b7Circle.setVisible(false);b7Text.setVisible(false);
        c7Circle.setVisible(false);c7Text.setVisible(false);
        d7Circle.setVisible(false);d7Text.setVisible(false);
        e7Circle.setVisible(false);e7Text.setVisible(false);
        f7Circle.setVisible(false);f7Text.setVisible(false);
        g7Circle.setVisible(false);g7Text.setVisible(false);
        h7Circle.setVisible(false);h7Text.setVisible(false);
        i7Circle.setVisible(false);i7Text.setVisible(false);

        a9Circle.setVisible(false);a9Text.setVisible(false);
        b9Circle.setVisible(false);b9Text.setVisible(false);
        c9Circle.setVisible(false);c9Text.setVisible(false);
        d9Circle.setVisible(false);d9Text.setVisible(false);
        e9Circle.setVisible(false);e9Text.setVisible(false);
        f9Circle.setVisible(false);f9Text.setVisible(false);
        g9Circle.setVisible(false);g9Text.setVisible(false);
        h9Circle.setVisible(false);h9Text.setVisible(false);
        i9Circle.setVisible(false);i9Text.setVisible(false);

        a2Circle.setVisible(true);a2Text.setVisible(true);
        b2Circle.setVisible(true);b2Text.setVisible(true);
        c2Circle.setVisible(true);c2Text.setVisible(true);

        a4Circle.setVisible(true);a4Text.setVisible(true);
        b4Circle.setVisible(true);b4Text.setVisible(true);
        c4Circle.setVisible(true);c4Text.setVisible(true);

        a6Circle.setVisible(true);a6Text.setVisible(true);
        b6Circle.setVisible(true);b6Text.setVisible(true);
        c6Circle.setVisible(true);c6Text.setVisible(true);

        a8Circle.setVisible(true);a8Text.setVisible(true);
        b8Circle.setVisible(true);b8Text.setVisible(true);
        c8Circle.setVisible(true);c8Text.setVisible(true);
    }

    private void setDrawingTypeThree() { //Type 3
        a1Circle.setVisible(false);a1Text.setVisible(false);
        b1Circle.setVisible(false);b1Text.setVisible(false);
        c1Circle.setVisible(false);c1Text.setVisible(false);
        d1Circle.setVisible(false);d1Text.setVisible(false);
        e1Circle.setVisible(true);e1Text.setVisible(true);
        f1Circle.setVisible(true);f1Text.setVisible(true);
        g1Circle.setVisible(false);g1Text.setVisible(false);
        h1Circle.setVisible(true);h1Text.setVisible(true);
        i1Circle.setVisible(true);i1Text.setVisible(true);

        a3Circle.setVisible(false);a3Text.setVisible(false);
        b3Circle.setVisible(false);b3Text.setVisible(false);
        c3Circle.setVisible(false);c3Text.setVisible(false);
        d3Circle.setVisible(true);d3Text.setVisible(true);
        e3Circle.setVisible(true);e3Text.setVisible(true);
        f3Circle.setVisible(false);f3Text.setVisible(false);
        g3Circle.setVisible(true);g3Text.setVisible(true);
        h3Circle.setVisible(true);h3Text.setVisible(true);
        i3Circle.setVisible(false);i3Text.setVisible(false);

        a7Circle.setVisible(false);a7Text.setVisible(false);
        b7Circle.setVisible(true);b7Text.setVisible(true);
        c7Circle.setVisible(true);c7Text.setVisible(true);
        d7Circle.setVisible(false);d7Text.setVisible(false);
        e7Circle.setVisible(true);e7Text.setVisible(true);
        f7Circle.setVisible(true);f7Text.setVisible(true);
        g7Circle.setVisible(false);g7Text.setVisible(false);
        h7Circle.setVisible(false);h7Text.setVisible(false);
        i7Circle.setVisible(false);i7Text.setVisible(false);

        a9Circle.setVisible(true);a9Text.setVisible(true);
        b9Circle.setVisible(true);b9Text.setVisible(true);
        c9Circle.setVisible(false);c9Text.setVisible(false);
        d9Circle.setVisible(true);d9Text.setVisible(true);
        e9Circle.setVisible(true);e9Text.setVisible(true);
        f9Circle.setVisible(false);f9Text.setVisible(false);
        g9Circle.setVisible(false);g9Text.setVisible(false);
        h9Circle.setVisible(false);h9Text.setVisible(false);
        i9Circle.setVisible(false);i9Text.setVisible(false);

        a2Circle.setVisible(false);a2Text.setVisible(false);
        b2Circle.setVisible(true);b2Text.setVisible(true);
        c2Circle.setVisible(true);c2Text.setVisible(true);

        a4Circle.setVisible(false);a4Text.setVisible(false);
        b4Circle.setVisible(true);b4Text.setVisible(true);
        c4Circle.setVisible(true);c4Text.setVisible(true);

        a6Circle.setVisible(true);a6Text.setVisible(true);
        b6Circle.setVisible(true);b6Text.setVisible(true);
        c6Circle.setVisible(false);c6Text.setVisible(false);

        a8Circle.setVisible(true);a8Text.setVisible(true);
        b8Circle.setVisible(true);b8Text.setVisible(true);
        c8Circle.setVisible(false);c8Text.setVisible(false);
    }

    private void setDrawingTypeFour() { //Type 4
        a1Circle.setVisible(true);a1Text.setVisible(true);
        b1Circle.setVisible(true);b1Text.setVisible(true);
        c1Circle.setVisible(true);c1Text.setVisible(true);
        d1Circle.setVisible(true);d1Text.setVisible(true);
        e1Circle.setVisible(true);e1Text.setVisible(true);
        f1Circle.setVisible(true);f1Text.setVisible(true);
        g1Circle.setVisible(true);g1Text.setVisible(true);
        h1Circle.setVisible(true);h1Text.setVisible(true);
        i1Circle.setVisible(true);i1Text.setVisible(true);

        a3Circle.setVisible(true);a3Text.setVisible(true);
        b3Circle.setVisible(true);b3Text.setVisible(true);
        c3Circle.setVisible(true);c3Text.setVisible(true);
        d3Circle.setVisible(true);d3Text.setVisible(true);
        e3Circle.setVisible(true);e3Text.setVisible(true);
        f3Circle.setVisible(true);f3Text.setVisible(true);
        g3Circle.setVisible(true);g3Text.setVisible(true);
        h3Circle.setVisible(true);h3Text.setVisible(true);
        i3Circle.setVisible(true);i3Text.setVisible(true);

        a7Circle.setVisible(true);a7Text.setVisible(true);
        b7Circle.setVisible(true);b7Text.setVisible(true);
        c7Circle.setVisible(true);c7Text.setVisible(true);
        d7Circle.setVisible(true);d7Text.setVisible(true);
        e7Circle.setVisible(true);e7Text.setVisible(true);
        f7Circle.setVisible(true);f7Text.setVisible(true);
        g7Circle.setVisible(true);g7Text.setVisible(true);
        h7Circle.setVisible(true);h7Text.setVisible(true);
        i7Circle.setVisible(true);i7Text.setVisible(true);

        a9Circle.setVisible(true);a9Text.setVisible(true);
        b9Circle.setVisible(true);b9Text.setVisible(true);
        c9Circle.setVisible(true);c9Text.setVisible(true);
        d9Circle.setVisible(true);d9Text.setVisible(true);
        e9Circle.setVisible(true);e9Text.setVisible(true);
        f9Circle.setVisible(true);f9Text.setVisible(true);
        g9Circle.setVisible(true);g9Text.setVisible(true);
        h9Circle.setVisible(true);h9Text.setVisible(true);
        i9Circle.setVisible(true);i9Text.setVisible(true);

        a2Circle.setVisible(true);a2Text.setVisible(true);
        b2Circle.setVisible(true);b2Text.setVisible(true);
        c2Circle.setVisible(true);c2Text.setVisible(true);

        a4Circle.setVisible(true);a4Text.setVisible(true);
        b4Circle.setVisible(true);b4Text.setVisible(true);
        c4Circle.setVisible(true);c4Text.setVisible(true);

        a6Circle.setVisible(true);a6Text.setVisible(true);
        b6Circle.setVisible(true);b6Text.setVisible(true);
        c6Circle.setVisible(true);c6Text.setVisible(true);

        a8Circle.setVisible(true);a8Text.setVisible(true);
        b8Circle.setVisible(true);b8Text.setVisible(true);
        c8Circle.setVisible(true);c8Text.setVisible(true);
    }


    private void setValueTopLeft() { //1.TopLeft
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        if (type == 4) {
            alphabetLists.add("1");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;

            alphabetLists.add("2");
            xLists.add(Float.parseFloat(xMinValue.getText()));
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;

            alphabetLists.add("3");
            xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;

            alphabetLists.add("4");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()));
            count++;
        }
        alphabetLists.add("5");
        xLists.add(Float.parseFloat(xMinValue.getText()));
        yLists.add(Float.parseFloat(yMaxValue.getText()));
        count++;

        alphabetLists.add("6");
        xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
        yLists.add(Float.parseFloat(yMaxValue.getText()));
        count++;

        if (type == 4) {
            alphabetLists.add("7");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
            count++;
        }
        alphabetLists.add("8");
        xLists.add(Float.parseFloat(xMinValue.getText()));
        yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
        count++;

        alphabetLists.add("9");
        xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
        yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
        count++;
        textArea = textArea + "Box 1\n";
        xyValueTextArea.appendText("1\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));

        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueTopCenter() { //2.TopCenter
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        if (type == 2 || type == 4) {
            alphabetLists.add("1");
            xLists.add((Float.parseFloat(xMaxValue.getText()) / Float.parseFloat(xMinValue.getText()))/2);
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;
        }
        alphabetLists.add("2");
        xLists.add((Float.parseFloat(xMaxValue.getText()) + Float.parseFloat(xMinValue.getText()))/2);
        yLists.add(Float.parseFloat(yMaxValue.getText()));
        count++;

        alphabetLists.add("3");
        xLists.add((Float.parseFloat(xMaxValue.getText()) + Float.parseFloat(xMinValue.getText()))/2);
        yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
        count++;
        xyValueTextArea.appendText("Box 2\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n", alphabetLists.get(i), checkFloatPrecision(xLists.get(i)), checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueTopRight() { //3.TopRight
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        if (type == 4) {
            alphabetLists.add("1");
            xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;

            alphabetLists.add("2");
            xLists.add(Float.parseFloat(xMaxValue.getText()));
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;

            alphabetLists.add("3");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) + 1);
            count++;
        }
        alphabetLists.add("4");
        xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
        yLists.add(Float.parseFloat(yMaxValue.getText()));
        count++;

        alphabetLists.add("5");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add(Float.parseFloat(yMaxValue.getText()));
        count++;

        if (type == 4) {
            alphabetLists.add("6");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()));
            count++;
        }

        alphabetLists.add("7");
        xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
        yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
        count++;

        alphabetLists.add("8");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
        count++;

        if (type == 4) {
            alphabetLists.add("9");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMaxValue.getText()) - 1);
            count++;
        }

        xyValueTextArea.appendText("Box 3\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueMiddleLeft() { //4.MiddleLeft
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        if (type == 2 || type == 4) {
            alphabetLists.add("1");
            xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
            yLists.add((Float.parseFloat(yMaxValue.getText()) + Float.parseFloat(yMinValue.getText()))/2);
            count++;
        }
        alphabetLists.add("2");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add((Float.parseFloat(yMaxValue.getText())+Float.parseFloat(yMinValue.getText()))/2);
        count++;

        alphabetLists.add("3");
        xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
        yLists.add((Float.parseFloat(yMaxValue.getText())+Float.parseFloat(yMinValue.getText()))/2);
        count++;
        xyValueTextArea.appendText("Box 4\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueMiddleCenter() { //5.MiddleCenter
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();

        alphabetLists.add("1");
        xLists.add((Float.parseFloat(xMaxValue.getText())+Float.parseFloat(xMinValue.getText()))/2);
        yLists.add((Float.parseFloat(yMaxValue.getText())+Float.parseFloat(yMinValue.getText()))/2);

        xyValueTextArea.appendText("Box 5\n");
        for (int i = 0; i < 1; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueMiddleRight() { //6.MiddleRight
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        alphabetLists.add("1");
        xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
        yLists.add((Float.parseFloat(yMaxValue.getText()) + Float.parseFloat(yMinValue.getText()))/2);
        count++;

        alphabetLists.add("2");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add((Float.parseFloat(yMaxValue.getText())+Float.parseFloat(yMinValue.getText()))/2);
        count++;

        if (type == 2 || type == 4) {
            alphabetLists.add("3");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add((Float.parseFloat(yMaxValue.getText()) / Float.parseFloat(yMinValue.getText()))/2);
            count++;
        }
        xyValueTextArea.appendText("Box 6\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueBottomLeft() { //7.BottomLeft
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        if (type == 4) {
            alphabetLists.add("1");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
            count++;
        }

        alphabetLists.add("2");
        xLists.add(Float.parseFloat(xMinValue.getText()));
        yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
        count++;

        alphabetLists.add("3");
        xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
        yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
        count++;

        if (type == 4) {
            alphabetLists.add("4");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMinValue.getText()));
            count++;
        }

        alphabetLists.add("5");
        xLists.add(Float.parseFloat(xMinValue.getText()));
        yLists.add(Float.parseFloat(yMinValue.getText()));
        count++;

        alphabetLists.add("6");
        xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
        yLists.add(Float.parseFloat(yMinValue.getText()));
        count++;

        if (type == 4) {
            alphabetLists.add("7");
            xLists.add(Float.parseFloat(xMinValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;

            alphabetLists.add("8");
            xLists.add(Float.parseFloat(xMinValue.getText()));
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;

            alphabetLists.add("9");
            xLists.add(Float.parseFloat(xMinValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;

        }

        xyValueTextArea.appendText("Box 7\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueBottomCenter() { //8.BottomCenter
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        alphabetLists.add("1");
        xLists.add((Float.parseFloat(xMaxValue.getText()) + Float.parseFloat(xMinValue.getText()))/2);
        yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
        count++;

        alphabetLists.add("2");
        xLists.add((Float.parseFloat(xMaxValue.getText())+Float.parseFloat(xMinValue.getText()))/2);
        yLists.add(Float.parseFloat(yMinValue.getText()));
        count++;

        if (type == 2 || type == 4) {
            alphabetLists.add("3");
            xLists.add((Float.parseFloat(xMaxValue.getText()) / Float.parseFloat(xMinValue.getText()))/2);
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;
        }
        xyValueTextArea.appendText("Box 8\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea =  textArea + xyValueTextArea.getText();
    }

    private void setValueBottomRight() { //9.BottomRight
        ArrayList<String> alphabetLists = new ArrayList<String>();
        ArrayList<Float> xLists = new ArrayList<Float>();
        ArrayList<Float> yLists = new ArrayList<Float>();
        int count = 0;

        alphabetLists.add("1");
        xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
        yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
        count++;

        alphabetLists.add("2");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
        count++;

        if (type == 4) {
            alphabetLists.add("3");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) + 1);
            count++;
        }

        alphabetLists.add("4");
        xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
        yLists.add(Float.parseFloat(yMinValue.getText()));
        count++;

        alphabetLists.add("5");
        xLists.add(Float.parseFloat(xMaxValue.getText()));
        yLists.add(Float.parseFloat(yMinValue.getText()));
        count++;

        if (type == 4) {

            alphabetLists.add("6");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMinValue.getText()));
            count++;

            alphabetLists.add("7");
            xLists.add(Float.parseFloat(xMaxValue.getText()) - 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;

            alphabetLists.add("8");
            xLists.add(Float.parseFloat(xMaxValue.getText()));
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;

            alphabetLists.add("9");
            xLists.add(Float.parseFloat(xMaxValue.getText()) + 1);
            yLists.add(Float.parseFloat(yMinValue.getText()) - 1);
            count++;
        }

        xyValueTextArea.appendText("Box 9\n");
        for (int i = 0; i < count; i++) {
            xyValueTextArea.appendText(String.format("%s: (%s,%s)\n",alphabetLists.get(i),checkFloatPrecision(xLists.get(i)),checkFloatPrecision(yLists.get(i))));
        }
        textArea = textArea + xyValueTextArea.getText();
    }

    private String checkFloatPrecision(Float value) {
        String string = value.toString();
        String[] split = string.split("\\.");
        if (split[1].equals("0")) {
            return split[0];
        }
        return String.format("%.2f", value);
    }

    public void clkSave(){
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textArea);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
