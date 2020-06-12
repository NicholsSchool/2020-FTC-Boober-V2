package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.RobotMap;

public class Lights extends Subsystem {

    private RevBlinkinLedDriver lights;
    public Lights()
    {
        super(RobotMap.LIGHTS_ID);
        lights = Robot.hw.get(RevBlinkinLedDriver.class, "Lights");
    }

    public void setColor(RevBlinkinLedDriver.BlinkinPattern pattern)
    {
        lights.setPattern(pattern);
    }

    public void blue()
    {
        setColor(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }

    public void red()
    {
        setColor(RevBlinkinLedDriver.BlinkinPattern.RED);
    }

    public void green()
    {
        setColor(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    @Override
    public void run() {
        if(Robot.g2.a)
            green();
        else if(Robot.g2.b)
            red();
        else if(Robot.g2.x)
            blue();
    }

    @Override
    public void stop() {

    }
}
