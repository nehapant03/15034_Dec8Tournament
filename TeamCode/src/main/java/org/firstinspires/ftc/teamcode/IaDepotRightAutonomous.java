package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Depot Right", group="group")
//@Disabled
public class IaDepotRightAutonomous extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor intake;
    private DcMotor lift;
    private Servo intakeDeployer;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFront = hardwareMap.get(DcMotor.class, "LF");
        leftBack = hardwareMap.get(DcMotor.class, "LB");
        rightFront = hardwareMap.get(DcMotor.class, "RF");
        rightBack = hardwareMap.get(DcMotor.class, "RB");

        lift = hardwareMap.get(DcMotor.class, "LIFT");
        intake = hardwareMap.get(DcMotor.class, "INTAKE");
        intakeDeployer = hardwareMap.get(Servo.class, "SERVO");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        lift.setPower(-.1);
        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            //This might collide with our teammate's robot

            lift.setPower(0.3);
            sleep(1000);
            lift.setPower(0);
            turnLeft(0.5,1000);
            sleep(1000);
            turnLeft(-.5, 500);
            //sample right
            turnRight(0.5,500);
            sleep(1000);
            straight(0.5,600);
            sleep(1000);
            straight(-0.5,600);
            sleep(1000);
            turnLeft(0.5,500);
            sleep(1000);
            //Goes to depot
            intakeDeployer.setPosition(-.5);
            straight(0.5, 1300);
            intake.setPower(.6);
            sleep(1000);
            intake.setPower(0);
            sleep(1000);
            straight(-0.5,500);
            sleep(1000);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFront.getPower(), rightFront.getPower());
            telemetry.update();
            break;

        }
    }

    public void straight(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

    public void turnRight(double power, int time) {
        leftFront.setPower(-power);
        leftBack.setPower(-power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

    public void turnLeft(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

}
