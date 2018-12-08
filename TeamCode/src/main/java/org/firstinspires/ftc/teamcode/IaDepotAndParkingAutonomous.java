package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Depot Parking", group="group")
//@Disabled
public class IaDepotAndParkingAutonomous extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor intake;
    private DcMotor lift;
    private DcMotor arm;

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
        arm = hardwareMap.get(DcMotor.class, "ARM");


        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        lift.setPower(-.1);
        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            //This might collide with our teammate's robot

            lift.setPower(0.3);
            sleep(1250);
            lift.setPower(0);
            turnLeft(0.5,1000);
            sleep(1000);
            turnLeft(-.5, 580);
            //Goes to depot
            arm.setPower(0.8);
            sleep(500);
            arm.setPower(0);
            straight(0.5, 1420);
            straight(1,200);
            straight(-1,200);
            sleep(1000);
            intake.setPower(.6);
            sleep(1000);
            intake.setPower(0);
            sleep(1000);
            straight(-0.5,680);
            sleep(1000);
            //going to crater
            turnRight(-0.5,700);
            sleep(1000);
            straight(0.6, 850);
            sleep(1000);
            turnRight(-0.6, 150);
            straight(0.8,2000);

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
