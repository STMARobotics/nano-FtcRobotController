package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SlideSubsystem {

    public static final int SLIDE_VELOCITY = 4000;
    public static final int ROTATIONS_PER_SECOND = 4000;
    private DcMotor slideMotor;
    public static final String SLIDE_MOTOR="slideMotor";

    public static final int LIFT_TICKS_PER_INCH = 120;

    public static final int LIFT_COLLAPSED = 0;
    public static final int LIFT_SCORING_IN_LOW_BASKET = 12 * LIFT_TICKS_PER_INCH;
    public static final int LIFT_SCORING_IN_HIGH_BASKET = 20 * LIFT_TICKS_PER_INCH;
    public static final int LIFT_SHORT_REACH = 6 * LIFT_TICKS_PER_INCH;

    public static final int MAX_SLIDE_POSITIONS = 2950;
    public static final int MIN_SLIDE_POSITION = -250;

    double liftPosition = LIFT_COLLAPSED;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public SlideSubsystem (HardwareMap hm, Telemetry telemetry){
        this.hardwareMap = hm;
        this.telemetry = telemetry;

        slideMotor = hardwareMap.get(DcMotor.class, SLIDE_MOTOR);

        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setTargetPosition(0);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void changePosition(float positionDelta){
        int currentPosition = slideMotor.getCurrentPosition();

        float targetPosition = currentPosition + positionDelta;
        setPosition(targetPosition);
    }

    public void setPosition(float liftPosition) {
        if (liftPosition > MAX_SLIDE_POSITIONS){
            liftPosition = MAX_SLIDE_POSITIONS;
        }

        if (liftPosition < MIN_SLIDE_POSITION){
            liftPosition = MIN_SLIDE_POSITION;
        }

        slideMotor.setTargetPosition((int) (liftPosition));
        ((DcMotorEx) slideMotor).setVelocity(SLIDE_VELOCITY);
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
        setPosition(currentPosition);
    }
}
