module com.example.projekt_uge41_majken_og_emma {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.smartcardio;


    opens com.example.projekt_uge41_majken_og_emma to javafx.fxml;
    exports com.example.projekt_uge41_majken_og_emma;
}