// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.resources;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {

  PhotonPipelineResult result;
  PhotonCamera photonCamera;

  EstimatedRobotPose estimatedPose;
  PhotonPoseEstimator poseEstimator;

  boolean hasPose;  
  double poseAmbiguity = 0;
  
public Vision() {
    photonCamera = new PhotonCamera("limelight");

    Transform3d cam = new Transform3d(new Translation3d(-0.33, 0, 0.33), 
    new Rotation3d(Math.toRadians(-3.5), Math.toRadians(10), Math.PI));

    poseEstimator = new PhotonPoseEstimator(AprilTagFields.k2024Crescendo.loadAprilTagLayoutField(), 
      PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, photonCamera, cam);

    result = photonCamera.getLatestResult();
  }

  public boolean hasTargets() {
    return result.hasTargets();
  }

  public double getYaw() {
    return hasTargets() ? result.getBestTarget().getYaw() : 0;
  }

  public double getPitch() {
    return hasTargets() ? result.getBestTarget().getPitch() : 0;
  }

  public double getArea() {
    return hasTargets() ? result.getBestTarget().getArea() : 0;
  }

  public EstimatedRobotPose getEstimatedPosition() {
    return estimatedPose;
  }

  public boolean hasPose()
  {
    return hasPose;
  }

  @Override
  public void periodic() {
    result = photonCamera.getLatestResult();
    

    Optional<EstimatedRobotPose> pose = poseEstimator.update();
    if (hasTargets() && pose.isPresent()) {
      estimatedPose = pose.get();
      hasPose = true;

      // Reject pose if ambiguity is greater than 0.2
      if(result.getBestTarget().getPoseAmbiguity() >= 0.1) hasPose = false;
    }
    else hasPose = false;


  }

}
