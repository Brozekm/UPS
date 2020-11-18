package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;

public abstract class MainController {
    @FXML
    private void exitApp() {
        Platform.exit();
    }
}
