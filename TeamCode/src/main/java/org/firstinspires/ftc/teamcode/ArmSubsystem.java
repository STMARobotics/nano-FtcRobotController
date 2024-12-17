package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class ArmSubsystem {
    public static final int ARM_SPEED = 2100;
    public static final int ROTATIONS_PER_SECOND = 2800;
    private DcMotor armMotor;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public static final String ARM_MOTOR = "armMotor";

    public static final int ARM_TICKS_PER_DEGREE = 18;

    public static final int ARM_TOP = 90 * ARM_TICKS_PER_DEGREE;
    public static final int ARM_BOTTOM = 0 * ARM_TICKS_PER_DEGREE;
    public static final int ARM_PARALLEL = 40 * ARM_TICKS_PER_DEGREE;
    public static final int ARM_PICKUP = 15* ARM_TICKS_PER_DEGREE;
    public static final int MAX_POSITION = 90 * ARM_TICKS_PER_DEGREE;
    public static final int MIN_POSITION = -10 * ARM_TICKS_PER_DEGREE;
    public static int FUDGE_FACTOR_DEGREES = 15;
    public static int ARM_SCORE_TOP_BASKET = 1120;
    public static int ARM_PICK_UP_BLOCK = 220;

    public ArmSubsystem(HardwareMap hm, Telemetry telemetry)    {
        this.hardwareMap = hm;
        this.telemetry = telemetry;

        armMotor=hardwareMap.get(DcMotor.class, ARM_MOTOR);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ((DcMotorEx)armMotor).setCurrentAlert(5, CurrentUnit.AMPS);
        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private double getAlterationDegrees(double alteration) {
        return FUDGE_FACTOR_DEGREES * ARM_TICKS_PER_DEGREE * alteration;
    }


    public int getPosition(){
        return armMotor.getCurrentPosition();
    }

    public int getTargetPosition(){
        System.out.println("************************  MODE " + armMotor.getMode());
        return armMotor.getTargetPosition();
    }

    private void setArmPosition(double position) {
        moveToPosition((int)position);
    }

    public void moveToPosition(int position) {
        armMotor.setTargetPosition(position);
        ((DcMotorEx) armMotor).setVelocity(ARM_SPEED);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetArmEncoder(){
    armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void addTelemetry(){
        telemetry.addData("arm position",armMotor.getCurrentPosition());
        telemetry.addData("arm busy", armMotor.isBusy());
    }

    public boolean isMoving(){
        return armMotor.isBusy();
    }

    public void holdPosition(){
        int currentPosition = armMotor.getCurrentPosition();
        armMotor.setTargetPosition(currentPosition);
        ((DcMotorEx) armMotor).setVelocity(ARM_SPEED);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveUpTicks(double amount) {
       moveTicks((int)amount);
    }

    public void moveDownTicks(double amount) {
        moveTicks(-(int)amount);
    }

    private void moveTicks(int amount) {
        int currentPosition = armMotor.getCurrentPosition();
        int targetPosition = currentPosition + amount;

        if (targetPosition > MAX_POSITION){
            targetPosition = MAX_POSITION;
        }

        if (targetPosition < MIN_POSITION){
            targetPosition = MIN_POSITION;
        }

        armMotor.setTargetPosition(targetPosition);
        ((DcMotorEx) armMotor).setVelocity(ARM_SPEED);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
