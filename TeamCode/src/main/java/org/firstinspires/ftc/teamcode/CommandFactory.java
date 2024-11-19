package org.firstinspires.ftc.teamcode;

public class CommandFactory {
    private static CommandFactory commandFactory;
    private final DriveSubsystem driveSubsystem;
    private final DistanceSensorSubsystem distanceSensor;
    private final ArmSubsystem arm;
    private final SlideSubsystem slideSubsystem;

    private CommandFactory(DriveSubsystem driveSubsystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.distanceSensor = distanceSensor;
        this.arm = arm;
        this.slideSubsystem = slideSubsystem;
    }

    public static void InitFactory(DriveSubsystem driveSubSystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem) {
        commandFactory = new CommandFactory(driveSubSystem, distanceSensor, arm, slideSubsystem);
    }


    public static Command Forward(double inchesForward, double speed, int timeout) {
        return new ForwardCommand(commandFactory.driveSubsystem, inchesForward, speed, timeout);
    }

    public static Command Backward(double inchesBackward, double speed, int timeout) {
        return new ForwardCommand(commandFactory.driveSubsystem, -inchesBackward, speed, timeout);
    }

    public static Command StrafeLeft(double inchesLeft, double speed, int timeout) {
        return new StrafeLeftCommand(commandFactory.driveSubsystem, inchesLeft, speed, timeout);
    }

    public static Command StrafeRight(double inchesRight, double speed, int timeout) {
        return new StrafeLeftCommand(commandFactory.driveSubsystem, -inchesRight, speed, timeout);
    }

    public static Command TurnLeft(double degrees, int timeout) {
        return new TurnLeftCommand(commandFactory.driveSubsystem, degrees, timeout);
    }

    public static Command TurnRight(double degrees, int timeout) {
        return new TurnLeftCommand(commandFactory.driveSubsystem, -degrees, timeout);
    }

    public static Command Pause(double seconds) {
        return new PauseCommand(seconds);
    }

    public static Command MoveToPickup() {
        return new MoveToPickupCommand(commandFactory.distanceSensor);
    }

    public static Command MoveArmToPosition(int position, int timeout){
        return new MoveArmToPositionCommand(commandFactory.arm, position, timeout);
    }

    public static Command ResetArmPosition(){
        return new ResetArmCommand(commandFactory.arm);
    }

    public static Command MoveSlideToPosition(int position, int timeout){
        return new SlideToPositionCommand(commandFactory.slideSubsystem, position, timeout);
    }
}
