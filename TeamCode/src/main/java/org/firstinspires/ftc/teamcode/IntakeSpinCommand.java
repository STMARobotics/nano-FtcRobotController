package org.firstinspires.ftc.teamcode;

public class IntakeSpinCommand implements Command{
    private final IntakeSubsystem intakeSystem;
    private final int timeout;
    private final Direction direction;

    public enum Direction {
        FORWARD,
        BACKWARD
    }

    public IntakeSpinCommand(IntakeSubsystem intakeSubsystem, Direction direction, int timeout) {
        this.intakeSystem = intakeSubsystem;
        this.direction = direction;
        this.timeout = timeout;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void logMessage() {
        intakeSystem.addTelemetry();
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
        if (direction == Direction.FORWARD){
            intakeSystem.spinForward();
        } else {
            intakeSystem.spinBackward();
        }
    }

    @Override
    public void init(){
    }
}
