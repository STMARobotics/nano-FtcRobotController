package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.CommandFactory.Forward;

public class MoveToPickupCommand implements Command{
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
}