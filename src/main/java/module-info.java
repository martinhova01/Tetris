module tetris {
    requires javafx.controls;
    requires javafx.fxml;

    opens tetris to javafx.graphics, javafx.fxml;
}
