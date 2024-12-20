package org.firstinspires.ftc.teamcode;

public class MoveWristToPositionCommand implements Command {
    private final WristSubsystem wristSubsystem;
    private final int timeout;
    private final double position;


    public MoveWristToPositionCommand(WristSubsystem wristSubsystem, double position, int timeout) {
        this.wristSubsystem = wristSubsystem;
        this.position = position;
        this.timeout = timeout;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void logMessage() {
        wristSubsystem.addTelemetry();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void execute() {
        wristSubsystem.moveToPosition(position);
    }

    @Override
    public void init(){
    }
}
