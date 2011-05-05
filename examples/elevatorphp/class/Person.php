<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.6.0.1712 modeling language!*/

class Person
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private $name;

  //Autounique Attributes
  private $number;

  //Person Associations
  private $desiredFloor;
  private $floor;
  private $elevator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->number = self::$nextNumber++;
  }
  
  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $this->name = $aName;
    return true;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getNumber()
  {
    return $this->number;
  }

  public function getDesiredFloor()
  {
    return $this->desiredFloor;
  }

  public function getFloor()
  {
    return $this->floor;
  }

  public function getElevator()
  {
    return $this->elevator;
  }

  public function setDesiredFloor($aDesiredFloor)
  {
    $existingDesiredFloor = $this->desiredFloor;
    $this->desiredFloor = $aDesiredFloor;
    if ($existingDesiredFloor != null && $existingDesiredFloor != $aDesiredFloor)
    {
      $existingDesiredFloor->removeExitAtPerson($this);
    }
    if ($aDesiredFloor != null)
    {
      $aDesiredFloor->addExitAtPerson($this);  
    }
  }

  public function setFloor($aFloor)
  {
    $existingFloor = $this->floor;
    $this->floor = $aFloor;
    if ($existingFloor != null && $existingFloor != $aFloor)
    {
      $existingFloor->removeWaitingPerson($this);
    }
    if ($aFloor != null)
    {
      $aFloor->addWaitingPerson($this);  
    }
  }

  public function setElevator($aElevator)
  {
    $existingElevator = $this->elevator;
    $this->elevator = $aElevator;
    if ($existingElevator != null && $existingElevator != $aElevator)
    {
      $existingElevator->removeRidingPerson($this);
    }
    if ($aElevator != null)
    {
      $aElevator->addRidingPerson($this);  
    }
  }

  public function delete()
  {
    if ($this->desiredFloor != null)
    {
      $this->desiredFloor->removeExitAtPerson($this);
    }
    if ($this->floor != null)
    {
      $this->floor->removeWaitingPerson($this);
    }
    if ($this->elevator != null)
    {
      $this->elevator->removeRidingPerson($this);
    }
  }

  public function pressButton($desiredFloor)
  {
    $this->setDesiredFloor($desiredFloor);
    if ($this->getFloor()->getNumber() > $desiredFloor->getNumber())
    {
      $this->getFloor()->getDownRequest()->setIsCalled(true);
    }
    else if ($this->getFloor()->getNumber() < $desiredFloor->getNumber())
    {
      $this->getFloor()->getUpRequest()->setIsCalled(true);
    }
    else
    {
     $this->setDesiredFloor(null);
    }
  }
}
?>