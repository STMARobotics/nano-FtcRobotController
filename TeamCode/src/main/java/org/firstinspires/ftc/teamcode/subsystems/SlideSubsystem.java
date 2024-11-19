package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SlideSubsystem {

    private DcMotor slideMotor;
    public static final String SLIDE_MOTOR="slideMotor";

    public static final double LIFT_TICKS_PER_INCH = 120;

    public static final double LIFT_COLLAPSED = 0 * LIFT_TICKS_PER_INCH;
    public static final double LIFT_SCORING_IN_LOW_BASKET = 12 * LIFT_TICKS_PER_INCH;
    public static final double LIFT_SCORING_IN_HIGH_BASKET = 20 * LIFT_TICKS_PER_INCH;
    public static final double LIFT_SHORT_REACH = 6 * LIFT_TICKS_PER_INCH;

    double liftPosition = LIFT_COLLAPSED;

    private final Telemetry telemetry;

    public SlideSubsystem (HardwareMap hm, Telemetry telemetry){
        this.telemetry = telemetry;

        slideMotor = hm.get(DcMotor.class, SLIDE_MOTOR);

        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setTargetPosition(0);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void changePosition(int positionDelta){
        this.liftPosition = this.liftPosition + positionDelta;
        setPosition(this.liftPosition);
    }

    public void setPosition(double liftPosition) {
        slideMotor.setTargetPosition((int) (liftPosition));

        ((DcMotorEx) slideMotor).setVelocity(2100);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void addTelemetry(){
        telemetry.addData("slide position",slideMotor.getCurrentPosition());
        telemetry.addData("slide target position", slideMotor.getTargetPosition());
        telemetry.addData("slide busy", slideMotor.isBusy());
    }

    public boolean isMoving() {
        return slideMotor.isBusy();
    }

    public void holdPosition(){
        int currentPosition = slideMotor.getCurrentPosition();
        slideMotor.setTargetPosition(currentPosition);
        ((DcMotorEx) slideMotor).setVelocity(2100);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void hardReset(){
        slideMotor.setPower(0);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
