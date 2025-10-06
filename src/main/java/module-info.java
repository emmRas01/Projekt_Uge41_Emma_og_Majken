module com.example.projekt_uge41_majken_og_emma {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.projekt_uge41_majken_og_emma to javafx.fxml;
    exports com.example.projekt_uge41_majken_og_emma;
}