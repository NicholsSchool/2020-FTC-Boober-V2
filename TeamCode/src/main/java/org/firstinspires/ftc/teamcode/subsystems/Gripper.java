package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.RobotMap;
import org.firstinspires.ftc.teamcode.util.record.Recordable;

public class Gripper extends Subsystem implements Recordable {

    private DcMotorEx lGripper, rGripper;

    public Gripper()
    {
        super("Gripper");
        lGripper = Robot.hw.get(DcMotorEx.class, RobotMap.LEFT_INTAKE_ID);
        rGripper = Robot.hw.get(DcMotorEx.class, RobotMap.RIGHT_INTAKE_ID);

        lGripper.setDirection(DcMotorSimple.Direction.REVERSE);
        rGripper.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake()
    {
        move(1.0);
    }

    public void outtake()
    {
        move(-1.0);
    }

    private void move(double speed)
    {
        lGripper.setPower(speed);
        rGripper.setPower(speed);
    }

    @Override
    public void run() {
        if(Robot.g2.right_trigger > 0.5)
            intake();
        else if(Robot.g2.left_trigger > 0.5)
            outtake();
        else
            stop();
    }

    @Override
    public void stop() {
        move(0);
    }

    @Override
    public double[] getValues() {
        return new double[]{lGripper.getPower(), rGripper.getPower()};
    }

    @Override
    public void setValues(double[] vals) {
        lGripper.setPower(vals[0]);
        rGripper.setPower(vals[1]);
    }
}
