package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.commands.CommandFactory.Forward;

import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSubsystem;

import java.util.Collections;
import java.util.Map;

public class MoveToPickupCommand implements ConfigurableCommand {
    public final DistanceSensorSubsystem sensor;

    public static final double EXPECTED_DISTANCE = 10;

    public MoveToPickupCommand(DistanceSensorSubsystem sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        double distanceInches = sensor.getDistanceInches();

        // calculate difference from expected
        double distanceDifference = distanceInches - EXPECTED_DISTANCE;

        // move to expected
        Forward(distanceDifference, .5, 3);

    }

    @Override
    public void init() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void logMessage() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public String getName() {
        return "Distance Command";
    }

    @Override
    public Map<String, String> getValues() {
        return Collections.emptyMap();
    }

    @Override
    public void increment() {

    }

    @Override
    public void decrement() {

    }
}