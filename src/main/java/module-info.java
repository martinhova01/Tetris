module tetris {
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
    opens model to javafx.graphics, javafx.fxml;
}
