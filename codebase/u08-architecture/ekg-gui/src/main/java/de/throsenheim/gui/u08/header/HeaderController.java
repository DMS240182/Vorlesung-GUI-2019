package de.throsenheim.gui.u08.header;


import de.throsenheim.gui.u08.eventbus.SimpleEventBus;
import de.throsenheim.gui.u08.eventbus.StatusEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Presenter fuer die Header-Maske
 * 
 * @author dominik.haas
 */
public class HeaderController implements Initializable {
    
    private static final Logger LOGGER = Logger.getLogger(HeaderController.class.getName());
    
    @FXML
    private ComboBox<String> seriesInput;
    @FXML
    private ComboBox<String> measurementInput;
    @FXML
    private ComboBox<String> hostInput;
    @FXML
    private ComboBox<String> processInput;
    @FXML
    private ComboBox<String> typeInput;
    @FXML
    private ComboBox<String> metricInput;
    @FXML
    private TextField excludeInput;
    @FXML
    private ComboBox<String> samplingInput;
    @FXML
    private ComboBox<String> aggregationInput;
    @FXML
    private ComboBox<String> graphInput;
    @FXML
    private DatePicker fromInput;
    @FXML
    private DatePicker untilInput;
    @FXML
    private CheckBox expertModeInput;
    @FXML
    private Button generateGraphButton;
    @FXML
    private Button createBookmarkButton;
    @FXML
    private SplitMenuButton addToGraphButton;
    @FXML
    private Pane rootPane;
    
    private final HeaderSettingsModel settingsModel = new HeaderSettingsModel();
    private final HeaderSettingsFilterModel filterModel = new HeaderSettingsFilterModel();
    
    
    private HeaderActionListener actionListener;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeBindings();
        generateGraphButton.addEventHandler(ActionEvent.ANY, (e) -> {});
        generateGraphButton.setOnAction(this::onGenerateGraph);
        createBookmarkButton.setOnAction((event) -> {
            SimpleEventBus.getBus().publish(new StatusEvent(this, "Creating bookmark ..."));
        });
        addToGraphButton.setOnAction((event) -> {
            SimpleEventBus.getBus().publish(new StatusEvent(this, "Adding to graph ..."));
        });


    }

    private void initializeBindings() {
        initializeSettingsModelBindings();
        initializeFilterModelBindings();
    }

    private void initializeSettingsModelBindings() {
        seriesInput.valueProperty().bindBidirectional(settingsModel.seriesProperty());
        measurementInput.valueProperty().bindBidirectional(settingsModel.measurementProperty());
        hostInput.valueProperty().bindBidirectional(settingsModel.hostProperty());
        processInput.valueProperty().bindBidirectional(settingsModel.processProperty());
        typeInput.valueProperty().bindBidirectional(settingsModel.typeProperty());
        metricInput.valueProperty().bindBidirectional(settingsModel.metricProperty());
        excludeInput.textProperty().bindBidirectional(settingsModel.excludeProperty());
        expertModeInput.selectedProperty().bindBidirectional(settingsModel.expertModeProperty());
        
        samplingInput.valueProperty().bindBidirectional(settingsModel.samplingProperty());
        aggregationInput.valueProperty().bindBidirectional(settingsModel.aggregationProperty());
        graphInput.valueProperty().bindBidirectional(settingsModel.graphProperty());
        
        fromInput.valueProperty().bindBidirectional(settingsModel.fromProperty());
        untilInput.valueProperty().bindBidirectional(settingsModel.untilProperty());
    }    
    
    private void initializeFilterModelBindings() {
        samplingInput.itemsProperty().bind(filterModel.samplingsProperty());
        aggregationInput.itemsProperty().bind(filterModel.aggregationsProperty());
        graphInput.itemsProperty().bind(filterModel.graphsProperty());
    }


    
    @FXML
    private void onGenerateGraph(ActionEvent e) {
        LOGGER.log(Level.INFO, "on generate graph: {0}", settingsModel);
        if(this.actionListener != null) {
            this.actionListener.onGenerateGraph();
        }
        SimpleEventBus.getBus().publish(new StatusEvent(this, "Generating graph ..."));
    }

    public HeaderSettingsModel getSettingsModel() {
        return settingsModel;
    }

    public void setActionListener(HeaderActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    
}
