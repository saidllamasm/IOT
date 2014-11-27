package mx.cinvestav.gdl.iot.cloudclient;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UpdateDataRequest_type")
public class UpdateDataRequest
{
	private String controllerId;
	private String smartThingId;
	private SensorData[] sensorData;

	public String getControllerId()
	{
		return controllerId;
	}

	public void setControllerId(String controllerId)
	{
		this.controllerId = controllerId;
	}

	public SensorData[] getSensorData()
	{
		return sensorData;
	}

	public void setSensorData(SensorData[] sensorData)
	{
		this.sensorData = sensorData;
	}

	public String getSmartThingId()
	{
		return smartThingId;
	}

	public void setSmartThingId(String smartThingId)
	{
		this.smartThingId = smartThingId;
	}

}
