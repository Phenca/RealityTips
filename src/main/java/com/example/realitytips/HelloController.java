package com.example.realitytips;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Text per_person_tip;

    @FXML
    private Text per_person_total;

    @FXML
    private TextField bill_amount;

    @FXML
    private TextField tip_amount;

    @FXML
    private TextField people_number;

    @FXML
    private TextField date_input;

    @FXML
    private Button calculate;

    @FXML
    private Label error_label;

    @FXML
    public void on_calculate_clicked(ActionEvent event) throws IllegalArgumentException {
        try {
            try {
                check_input(bill_amount, "Bill");
                check_input(tip_amount, "Tip");
                check_input(people_number, "Nb people");

                Tip tip = new Tip(
                        Double.parseDouble(bill_amount.getText()),
                        Integer.parseInt(tip_amount.getText()),
                        Integer.parseInt(people_number.getText())
                );

                double _tip = tip.get_tip(tip);
                double _total = tip.get_total(tip, _tip);

                check_date(date_input.getText());
                error_label.setText("");
                per_person_tip.setText(String.valueOf(_tip));
                per_person_total.setText(String.valueOf(_total));

                historize(
                    date_input.getText(),
                    bill_amount.getText(),
                    tip_amount.getText(),
                    people_number.getText()
                );
            } catch (NumberFormatException err) {
                error_label.setText(err.getMessage());
            } catch (DateTimeException err) {error_label.setText(err.getMessage());}

        } catch (IllegalArgumentException err) {error_label.setText(err.getMessage());}
    }

    public void check_input(TextField input, String input_name) throws IllegalArgumentException {
        if (input.getText().isEmpty()) {
            throw new IllegalArgumentException("Le champ " + input_name + " ne doit pas être vide");
        }
        if (!input.getText().matches("[0-9]*")) {
            throw new IllegalArgumentException("Le champ '" + input_name + "' ne doit contenir que des nombres positifs");
        }
    }

    public void check_date(String date) throws DateTimeException {
        if (!date.matches("[0-3][0-9]/[0-1][0-9]/[0-9]{4}")) {
            throw new DateTimeException("Le format de date renseigné est invalide");
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate _date = LocalDate.parse(date, format);
        LocalDate d = LocalDate.now();
        if (_date.isAfter(d)) {
            throw new DateTimeException("La date donnée est dans le futur");
        }
    }

    public void historize(String date, String bill, String tip, String people_number) {
        ArrayList<String> history = new ArrayList<>();
        File history_file = new File("src/main/resources/history/history.txt");

        try {
            if (history_file.exists()) {
                // Read data from history.txt file and fill the ArrayList.
                Scanner scanner = new Scanner(history_file);
                while (scanner.hasNextLine()) {history.add(scanner.nextLine());}
                scanner.close();
            }
            boolean is_date_already_in_history_file = false;
            // Change line data if the date is already in the ArrayList.
            for (int line = 0; line < history.size(); line++) {
                String[] parts = history.get(line).split(";");
                if (parts.length > 0 && parts[0].equals(date)) {
                    history.set(line, date + ";" + bill + ";" + tip + ";" + people_number);
                    is_date_already_in_history_file = true;
                    break;
                }
            }
            if (!is_date_already_in_history_file) {
                history.add(date + ";" + bill + ";" + tip + ";" + people_number);
            }
            // Re-write data in history.txt.
            FileWriter writer = new FileWriter(history_file, false);
            for (String line : history) {writer.write(line + "\n");}
            writer.close();
        } catch (IOException err) {
            System.err.println(err.getMessage());
        }
    }
}
