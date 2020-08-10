package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.RobotMap;
import org.firstinspires.ftc.teamcode.util.record.Recordable;

import java.util.List;

public class DriveTrain extends Subsystem implements Recordable {

    //Counts per rev untested
    private static final double
            COUNTS_PER_REV  = 515, INCHES_PER_REV = 4 * Math.PI, COUNTS_PER_INCH = COUNTS_PER_REV/INCHES_PER_REV;

    private DcMotorEx lfDrive, lmDrive,lbDrive, rfDrive, rmDrive, rbDrive;
    private List<LynxModule> allHubs;
    private DcMotorEx[] motors;
    private boolean isArcadeDrive;
    private boolean replayRunning;
    public DriveTrain()
    {
        super("DriveTrain");
        lfDrive = Robot.hw.get(DcMotorEx.class, RobotMap.LEFT_FRONT_DRIVE_ID);
        lmDrive = Robot.hw.get(DcMotorEx.class, RobotMap.LEFT_MIDDLE_DRIVE_ID);
        lbDrive = Robot.hw.get(DcMotorEx.class, RobotMap.LEFT_BACK_DRIVE_ID);
        rfDrive = Robot.hw.get(DcMotorEx.class, RobotMap.RIGHT_FRONT_DRIVE_ID);
        rmDrive = Robot.hw.get(DcMotorEx.class, RobotMap.RIGHT_MIDDLE_DRIVE_ID);
        rbDrive = Robot.hw.get(DcMotorEx.class, RobotMap.RIGHT_BACK_DRIVE_ID);

        allHubs = Robot.hw.getAll(LynxModule.class);
        for (LynxModule module : allHubs)
            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        lfDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        lmDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        lbDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        rfDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rmDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rbDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        motors = new DcMotorEx[6];

        motors[0] = lfDrive;
        motors[1] = lmDrive;
        motors[2] = lbDrive;
        motors[3] = rfDrive;
        motors[4] = rmDrive;
        motors[5] = rbDrive;
   //     lfDrive.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, );
     //   setPIDF(0,0,0, 1);

        isArcadeDrive = false;
        replayRunning = false;
    }

    private void setPIDF(double p, double i, double d, double f)
    {
        PIDFCoefficients coefficients = new PIDFCoefficients(p, i, d, f);
        for(DcMotorEx m : motors)
            m.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, coefficients);
    }

    public void setBrakeMode(boolean brakeModeOn)
    {
        for(DcMotorEx motor : motors)
            motor.setZeroPowerBehavior(brakeModeOn ? DcMotor.ZeroPowerBehavior.BRAKE : DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void move(double lSpeed, double rSpeed)
    {
        move(lSpeed, rSpeed, true);
    }

    public void move(double lSpeed, double rSpeed, boolean squareMovement)
    {
        lSpeed = Range.clip(lSpeed, -1, 1);
        rSpeed = Range.clip(rSpeed, -1, 1);

        // The x^2 movement Dillan wanted.
        if(squareMovement) {
            lSpeed *= Math.abs(lSpeed);
            rSpeed *= Math.abs(rSpeed);
        }

//        lSpeed *= Constants.DRIVE_BUFFER;
//        rSpeed *= Constants.DRIVE_BUFFER;


        for(int i = 0; i < motors.length; i++)
            if(i < motors.length/2)
                motors[i].setPower(lSpeed);
            else
                motors[i].setPower(rSpeed);
    }

    public void tankDrive()
    {
        move(-Robot.g1.left_stick_y, -Robot.g1.right_stick_y);
    }

    public void arcadeDrive()
    {
        move( -(Robot.g1.right_stick_y - Robot.g1.left_stick_x), -(Robot.g1.right_stick_y + Robot.g1.left_stick_x));
    }
    @Override
    public void run() {
        if(isArcadeDrive)
            arcadeDrive();
        else
            tankDrive();

//        if(Robot.g1.dpad_up)
//            isArcadeDrive = false;
//        else if(Robot.g1.dpad_down)
//            isArcadeDrive = true;
        if(Robot.g1.dpad_right)
            resetEncoders();

        for(int i = 0; i < motors.length; i ++) {
            Robot.telemetry.addData("Motor " + i + " Position PID", motors[i].getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION));
            Robot.telemetry.addData("Motor " + i + " Velocity PID", motors[i].getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));

        }
        printEncoderValues();

    }

    private void printEncoderValues()
    {
        for(int i = 0; i < motors.length; i ++)
            Robot.telemetry.addData("Motor " + i , motors[i].getCurrentPosition());
    }

    private void resetEncoders()
    {

        for(DcMotorEx motor : motors) {
            DcMotor.RunMode currentMode = motor.getMode();
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(currentMode);
        }
    }

    public void test() {
        rmDrive.setPower(0.2);
    }

    @Override
    public void stop() {
        move(0,0);
    }

    @Override
    public double[] getValues() {
        for (LynxModule module : allHubs)
            module.clearBulkCache();
        double[] values = new double[motors.length * 2];
        for(int i = 0; i < motors.length; i ++) {
            values[i] = motors[i].getCurrentPosition();
            values[i + motors.length] = motors[i].getPower();
        }
        return values;
    }

    @Override
    public void setValues(double[] vals) {
        if(!replayRunning)
            resetEncoders();

        for(int i = 0; i < motors.length; i++) {
            motors[i].setTargetPosition((int) vals[i]);
            motors[i].setPower(vals[i + motors.length]);
        }
        if(!replayRunning)
        {

            for(int i = 0; i < motors.length; i++)
                motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            replayRunning = true;
        }
    }
}
