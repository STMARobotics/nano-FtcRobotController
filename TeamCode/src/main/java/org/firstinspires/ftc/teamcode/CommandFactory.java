package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.IntakeSpinCommand.Direction;

public class CommandFactory {
    private static CommandFactory commandFactory;
    private final DriveSubsystem driveSubsystem;
    private final DistanceSensorSubsystem distanceSensor;
    private final ArmSubsystem arm;
    private final SlideSubsystem slideSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final WristSubsystem wristSubsystem;

    private CommandFactory(DriveSubsystem driveSubsystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem, IntakeSubsystem intakeSubsystem, WristSubsystem wristSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.distanceSensor = distanceSensor;
        this.arm = arm;
        this.slideSubsystem = slideSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.wristSubsystem = wristSubsystem;
    }

    public static void InitFactory(DriveSubsystem driveSubSystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem, IntakeSubsystem intakeSubsystem, WristSubsystem wristSubsystem) {
        commandFactory = new CommandFactory(driveSubSystem, distanceSensor, arm, slideSubsystem, intakeSubsystem, wristSubsystem);
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

    public static Command MoveArmUpTicks(int ticksUp, int timeout){
        return new MoveArmUpTicksCommand(commandFactory.arm, ticksUp, timeout);
    }
    public static Command MoveArmDownTicks(int ticksDown, int timeout){
        return new MoveArmUpTicksCommand(commandFactory.arm, -ticksDown, timeout);
    }

    public static Command ResetArmPosition(){
        return new ResetArmCommand(commandFactory.arm);
    }

    public static Command MoveSlideToPosition(int position, int timeout){
        return new SlideToPositionCommand(commandFactory.slideSubsystem, position, timeout);
    }

    public static Command EjectSample(int timeout){
        return new IntakeSpinCommand(commandFactory.intakeSubsystem, Direction.FORWARD, timeout);
    }

    public static Command LoadSample(int timeout){
        return new IntakeSpinCommand(commandFactory.intakeSubsystem,Direction.BACKWARD,timeout);
    }
    public static Command WristPickUp(int timeout){
        return new MoveWristToPositionCommand(commandFactory.wristSubsystem, WristSubsystem.PICKUP_POSITION,timeout);
    }
    public static Command WristDropOff(int position, int timeout){
        return new MoveWristToPositionCommand(commandFactory.wristSubsystem,  WristSubsystem.DROP_OFF_POSITION,timeout);
    }
}
