package mx.cinvestav.gdl.iot.webpage.client;

import java.util.ArrayList;
import java.util.Collection;


import mx.cinvestav.gdl.iot.dashboard.client.ClientConstants;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorPropertyDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpSensor implements EntryPoint {

	private TextBox tbOperationS = new TextBox();

	private DialogBox dialogBox = new DialogBox();
	private Button btClose = new Button("Close");
	private Button btError = new Button("Close");

	private VerticalPanel dialogPanel = new VerticalPanel();
	private Label lbDialogBox = new Label();

	private ListBox listNameProperty = new ListBox(true);
	private ListBox listValueProperty = new ListBox(true);
	private ListBox listActiveProperty = new ListBox(true);

	private TextBox name = new TextBox();
	private TextBox value = new TextBox();
	private CheckBox active = new CheckBox();

	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();

	private Button btSaveSensor = new Button("Save Sensor");
	private Button btCancelSensor = new Button("Cancel");
	private HorizontalPanel buttonsPanel = new HorizontalPanel();

	private FlexTable tableFields = new FlexTable();

	private TextBox tbId = new TextBox();
	private TextBox tbName = new TextBox();
	private TextBox tbDescription = new TextBox();
	private CheckBox chActive = new CheckBox();
	private TextBox tbAltitude = new TextBox();
	private TextBox tbLatitude = new TextBox();
	private TextBox tbLongitude = new TextBox();
	private TextBox tbType = new TextBox();
	private TextBox tbUnit = new TextBox();
	private TextBox tbUnitMeasure = new TextBox();

	private VerticalPanel propertyPanel = new VerticalPanel();
	private Label lbProperty = new Label();
	private FlexTable tableProperty = new FlexTable();
	private Button btAddProperty = new Button("Add");

	private VerticalPanel smartThingPanel = new VerticalPanel();
	private Label lbSmartThing = new Label();
	private ListBox cbSmartThing = new ListBox();

	private ArrayList<String> property = new ArrayList<String>();

	private static final EntityStoreServiceAsync entityService = GWT
			.create(EntityStoreService.class);

	@Override
	public void onModuleLoad() {
		tableFields.setText(0, 0, "Id: ");
		tableFields.setWidget(0, 1, tbId);

		tableFields.setText(1, 0, "Name: ");
		tableFields.setWidget(1, 1, tbName);

		tableFields.setText(2, 0, "Description: ");
		tableFields.setWidget(2, 1, tbDescription);

		tableFields.setText(3, 0, "Status: ");
		tableFields.setWidget(3, 1, chActive);

		tableFields.setText(4, 0, "Altitude: ");
		tableFields.setWidget(4, 1, tbAltitude);

		tableFields.setText(5, 0, "Latitude: ");
		tableFields.setWidget(5, 1, tbLatitude);

		tableFields.setText(6, 0, "Longitude: ");
		tableFields.setWidget(6, 1, tbLongitude);

		tableFields.setText(7, 0, "Type: ");
		tableFields.setWidget(7, 1, tbType);

		tableFields.setText(8, 0, "Unit measure: ");
		tableFields.setWidget(8, 1, tbUnitMeasure);

		tableFields.setText(9, 0, "Unit: ");
		tableFields.setWidget(9, 1, tbUnit);

		tbId.setName("tbIdS");
		tbId.setEnabled(false);

		tbName.setName(ClientConstants.NAME);
		tbDescription.setName(ClientConstants.DESCRIPTION);
		tbAltitude.setName(ClientConstants.ALTITUDE);
		tbLongitude.setName(ClientConstants.LONGITUDE);
		tbLatitude.setName(ClientConstants.LONGITUDE);
		tbType.setName(ClientConstants.SENSOR_TYPE);
		tbUnit.setName(ClientConstants.UNIT);
		// UNIT MEASURE ////////
		chActive.setName(ClientConstants.ACTIVE);

		listNameProperty.setName(ClientConstants.PROP_NAMES);
		listNameProperty.setVisible(false);
		listValueProperty.setName(ClientConstants.PROP_VALUES);
		listValueProperty.setVisible(false);
		listActiveProperty.setName(ClientConstants.PROP_ACTIVE);
		listActiveProperty.setVisible(false);

		tbOperationS.setName(ClientConstants.OPERATION);
		tbOperationS.setText(ClientConstants.SENSOR);
		tbOperationS.setVisible(false);

		tableProperty.setText(0, 0, "Name");
		tableProperty.setText(0, 1, "Value");
		tableProperty.setText(0, 2, "Active");
		tableProperty.setText(0, 3, "       ");

		tableProperty.getCellFormatter().addStyleName(0, 0,
				"headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 1,
				"headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 2,
				"headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 3,
				"headerTableProperty");

		tableProperty.addStyleName("tableProperty");
		tableProperty.setCellPadding(3);

		lbProperty.setText("Property");
		lbProperty.setStyleName("lbProperty");

		propertyPanel.add(lbProperty);
		propertyPanel.add(btAddProperty);
		propertyPanel.add(tableProperty);

		// Add name to the combo that has the smartthings that belows to that
		// sensor
		lbSmartThing.setText("SmartThing:");
		lbSmartThing.addStyleName("lbProperty");
		cbSmartThing.setName("cbSmartThingsS");
		smartThingPanel.add(lbSmartThing);
		smartThingPanel.add(cbSmartThing);

		formPanel.add(tableFields);
		formPanel.add(propertyPanel);
		formPanel.add(smartThingPanel);

		btSaveSensor.addStyleName("btSave");
		btCancelSensor.addStyleName("btSave");

		buttonsPanel.add(btSaveSensor);
		buttonsPanel.add(btCancelSensor);

		formPanel.add(buttonsPanel);
		formPanel.add(tbOperationS);
		formPanel.add(listNameProperty);
		formPanel.add(listValueProperty);
		formPanel.add(listActiveProperty);

		form.setWidget(formPanel);
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(form);

		RootPanel.get("formContainer").add(decoratorPanel);

		dialogBox.add(btClose);

		btClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		btError.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		btAddProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(false);
				addProperty();
			}
		});

		btSaveSensor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				SensorDTO s = new SensorDTO();
				s.setName(tbName.getText());
				s.setDescription(tbDescription.getText());
				s.setActive(chActive.getValue());
				
				// TODO:VALIDATE!
//				s.setAltitude(Double.parseDouble(tbAltitude.getText()));
//				s.setLatitude(Double.parseDouble(tbLatitude.getText()));
//				s.setLongitude(Double.parseDouble(tbLongitude.getText()));
				
				s.setUnit(tbUnit.getText());
				s.setSensor_type(tbType.getText());
				
				//TODO: set parent smart thing
				s.setIdthing(0);

				// TODO: s.setMeasures(measures);

				Collection<IoTPropertyDTO> props = new ArrayList<>();
				for (int i = 0; i < listNameProperty.getItemCount(); i++) 
				{
					IoTPropertyDTO prop = new SensorPropertyDTO();
					prop.setName(listNameProperty.getItemText(i));
					prop.setValue(listValueProperty.getItemText(i));
					prop.setActive(Boolean.valueOf(listActiveProperty
							.getValue(i)));
					props.add(prop);
				}

				entityService.storeEntity(s, props, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						dialogBox.setAnimationEnabled(true);
						dialogBox.setGlassEnabled(true);
						dialogBox.center();

						dialogBox.setText("Information");

						lbDialogBox.setText("Sensor succesfully stored");
						dialogPanel.add(lbDialogBox);
						dialogPanel.setCellHorizontalAlignment(lbDialogBox,
								HasHorizontalAlignment.ALIGN_CENTER);

						dialogPanel.add(btClose);
						dialogPanel.setCellHorizontalAlignment(btClose,
								HasHorizontalAlignment.ALIGN_CENTER);

						dialogBox.add(dialogPanel);

						dialogBox.show();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});

			}
		});

		btCancelSensor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.Location.replace("wpSensors.jsp");
			}
		});

	}

	private void addProperty() {
		int row = tableProperty.getRowCount();

		tableProperty.setWidget(row, 0, name);
		tableProperty.setWidget(row, 1, value);
		tableProperty.setWidget(row, 2, active);

		// Add a button to remove this stock from the table.
		Button saveProperty = new Button("Save");
		saveProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(true);
				saveProperty();
			}
		});

		Button cancelProperty = new Button("Cancel");
		cancelProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(true);

			}
		});

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.add(saveProperty);
		buttonsPanel.add(cancelProperty);

		tableProperty.setWidget(row, 3, buttonsPanel);

	}

	private void saveProperty() {
		tableProperty.removeRow(tableProperty.getRowCount() - 1);

		final String symboln = name.getText();
		final String symbolv = value.getText();
		CheckBox symbola = new CheckBox();

		property.add(symboln);

		if (active.getValue() == true) {
			symbola.setValue(true);
		} else {
			symbola.setValue(false);
		}

		if (symboln.length() > 45) {
			dialogBox.setAnimationEnabled(true);
			dialogBox.setGlassEnabled(true);
			dialogBox.center();

			dialogBox.setText("Error");

			lbDialogBox.setText("The name must have less than 45 characters");
			dialogPanel.add(lbDialogBox);
			dialogPanel.setCellHorizontalAlignment(lbDialogBox,
					HasHorizontalAlignment.ALIGN_CENTER);

			dialogPanel.add(btError);
			dialogPanel.setCellHorizontalAlignment(btError,
					HasHorizontalAlignment.ALIGN_CENTER);

			dialogBox.add(dialogPanel);

			dialogBox.show();

			return;
		}

		if (symbolv.length() > 45) {
			dialogBox.setAnimationEnabled(true);
			dialogBox.setGlassEnabled(true);
			dialogBox.center();

			dialogBox.setText("Error");

			lbDialogBox.setText("The value must have less than 45 characters");
			dialogPanel.add(lbDialogBox);
			dialogPanel.setCellHorizontalAlignment(lbDialogBox,
					HasHorizontalAlignment.ALIGN_CENTER);

			dialogPanel.add(btError);
			dialogPanel.setCellHorizontalAlignment(btError,
					HasHorizontalAlignment.ALIGN_CENTER);

			dialogBox.add(dialogPanel);

			dialogBox.show();

			return;
		}
		name.setText("");
		value.setText("");
		active.setValue(false);

		int row = tableProperty.getRowCount();
		tableProperty.setText(row, 0, symboln);
		tableProperty.setText(row, 1, symbolv);
		tableProperty.setWidget(row, 2, symbola);

		listNameProperty.addItem(symboln);
		listValueProperty.addItem(symbolv);
		listActiveProperty.addItem(symbola.getValue() + "");

		Button removeProperty = new Button("Remove");
		removeProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int removedIndex = property.indexOf(symboln);
				property.remove(removedIndex);
				tableProperty.removeRow(removedIndex + 1);

				listNameProperty.removeItem(removedIndex);
				listValueProperty.removeItem(removedIndex);
				listActiveProperty.removeItem(removedIndex);
			}
		});

		tableProperty.setWidget(row, 3, removeProperty);
	}

}