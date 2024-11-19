package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSubsystem {
    private final DistanceSensor distanceSensor;
    private final Telemetry telemetry;

    public static final String DISTANCE_SENSOR = "distance";

    public DistanceSensorSubsystem(HardwareMap hm, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.distanceSensor = hm.get(DistanceSensor.class, DISTANCE_SENSOR);
    }


    public double getDistanceCentimeters(){
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

    public double getDistanceInches(){
        return distanceSensor.getDistance(DistanceUnit.INCH);
    }

    public void addTelemetry() {
        telemetry.addData("InchesInFront", distanceSensor.getDistance(DistanceUnit.INCH));
    }
}
