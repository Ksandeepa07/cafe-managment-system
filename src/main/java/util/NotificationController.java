package util;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.Optional;


public class NotificationController {
   public static void ErrorMasseage(String messeage){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(messeage);

        alert.getDialogPane().setPrefSize(300, 150);
        alert.getDialogPane().setStyle("-fx-background-color: #dfa47e;");
        alert.getDialogPane().setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/error.png"));
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();

    }
    public static void animationMesseage(String image,String title,String text){
        Image img = new Image(String.valueOf(image), 96, 96, false, false);
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3));
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static boolean confirmationMasseage(String messeage){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setHeaderText(null);
        alert.setContentText(messeage);

        alert.getDialogPane().setPrefSize(300, 150);
        alert.getDialogPane().setStyle("-fx-background-color: #dfa47e;");
        alert.getDialogPane().setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/error.png"));
        ButtonType okButton = new ButtonType("ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(okButton,cancelButton);

        Optional<ButtonType> result =alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
            return false;
    }
}
