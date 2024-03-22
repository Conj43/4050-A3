module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment.group19_cs4050_7050_assignment3 to javafx.fxml;
    exports assignment.group19_cs4050_7050_assignment3;
}