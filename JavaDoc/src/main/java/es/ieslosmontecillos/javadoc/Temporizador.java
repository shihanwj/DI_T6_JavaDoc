/*
 * 2º DAM DI
 * Tema 3: Creación de componentes visuales
 * @author Shihan
 * 4.7. Implementación Componente intermedio
 * Componente: Temporizador
 * Proyecto Componentes_WeiShihan
 */

package es.ieslosmontecillos.javadoc;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Temporizador is a component to countdown time.
 * It shows the countdown of the time passed as parameter and sends an alert the moment it reaches the end.
 * @author Shihan Wei
 * @version 1.0
 */
public class Temporizador extends VBox {

    /**
     * Label which shows the seconds to be timed
     */
    @FXML
    private Label tiempoTotal;

    /**
     * Label which shows the time left in seconds
     */
    @FXML
    private Label tiempoRestante;

    /**
     * Integer property tiempo
     */
    private IntegerProperty tiempo = new SimpleIntegerProperty();

    /**
     * Timeline used to keep in track the time changes
     */
    private final Timeline cuentaAtras = new Timeline();

    /**
     * Represents the action of the component when the countdown ends
     */
    public ObjectProperty<EventHandler<ActionEvent>> onFinished = new SimpleObjectProperty<>();

    /**
     * Creates a temporizador with a default time of 60 seconds.
     * @since 1.0
     * @throws RuntimeException if an error has occurred during FXML loading
     */
    public Temporizador() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        /* Establecemos un tiempo por defecto (1 minuto) */
        this.setTiempo(60);

        /* Establecemos un evento por defecto para el setOnFinished */
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Fin del temporizador. Mensaje por defecto.");
            }
        });
    }


    /**
     * Gets the value of the property onFinished
     * @since 1.0
     * @return property onFinished
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {
        return onFinished;
    }

    /**
     * Sets the value of the property onFinished
     * @since 1.0
     * @param onFinished EventHandler for property onFinished
     */
    public final void setOnFinished(EventHandler<ActionEvent> onFinished) {
        this.onFinishedProperty().set(onFinished);
    }

    /**
     * Gets the value of the event defined at property onFinished
     * @since 1.0
     * @return onFinished
     */
    public final EventHandler<ActionEvent> getOnFinished() {
        return onFinished.get();
    }

    /**
     * Gets the value of the property tiempo
     * @since 1.0
     * @return IntegerProperty
     */
    public IntegerProperty tiempoProperty() {
        return tiempo;
    }

    /**
     * Sets the value of the property tiempo
     * @since 1.0
     * @param tiempo Time(seconds) to be counted
     */
    public void setTiempo(int tiempo) {
        tiempoProperty().set(tiempo);
    }

    /**
     * Gets the int value of the property tiempo
     * @since 1.0
     * @return Established time in seconds for the temporizador
     */
    public int getTiempo() {
        return tiempoProperty().get();
    }

    /**
     * Starts the temporizador.
     * @since 1.0
     */
    public void play()
    {
        /* Definimos los argumentos necesarios para el Timeline */
        KeyValue kv = new KeyValue(tiempo, 0);
        KeyFrame kf = new KeyFrame(Duration.seconds(tiempo.get()), onFinished.get(), kv);
        cuentaAtras.getKeyFrames().add(kf);

        /* Determinamos el valor de las etiquetas  */
        tiempoTotal.setText("Cuenta atrás: " + getTiempo() + " segundos");
        tiempoRestante.setText(String.valueOf(getTiempo()));

        /* Ejecutamos el Timeline */
        cuentaAtras.play();

        /* Actualizamos la etiqueta del tiempo restante conforme que se realiza la cuenta atrás
        * Lo podemos hacer tanto con bind o con un listener*/
        tiempoRestante.textProperty().bind(tiempo.asString());
    }

}
