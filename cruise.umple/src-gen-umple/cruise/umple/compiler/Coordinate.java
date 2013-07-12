/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.17.0.2716 modeling language!*/

package cruise.umple.compiler;

/**
 * A Coordinate is used in  the layout of classes
 * It represents the shape of a class box in a diagram
 * @umplesource Umple.ump 630
 * @umplesource Umple_Code.ump 1761
 */
// line 630 "../../../../src/Umple.ump"
// line 1761 "../../../../src/Umple_Code.ump"
public class Coordinate
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Coordinate Attributes
  private int x;
  private int y;
  private int width;
  private int height;

  //Coordinate State Machines
  enum Status { Explicit, Undefined, Defaulted }
  private Status status;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetX;
  private boolean canSetY;
  private boolean canSetWidth;
  private boolean canSetHeight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  @umplesourcefile(line={638},file={"Umple.ump"},javaline={58},length={1})
  public Coordinate(int aX, int aY, int aWidth, int aHeight)
  {
    cachedHashCode = -1;
    canSetX = true;
    canSetY = true;
    canSetWidth = true;
    canSetHeight = true;
    x = aX;
    y = aY;
    width = aWidth;
    height = aHeight;
    setStatus(Status.Explicit);
    // line 638 "../../../../src/Umple.ump"
    updateStatus();
  }

  //------------------------
  // INTERFACE
  //------------------------

  @umplesourcefile(line={639},file={"Umple.ump"},javaline={73},length={1})
  public boolean setX(int aX)
  {
    boolean wasSet = false;
    if (!canSetX) { return false; }
    x = aX;
    wasSet = true;
    // line 639 "../../../../src/Umple.ump"
    if(wasSet) updateStatus();
    return wasSet;
  }

  @umplesourcefile(line={640},file={"Umple.ump"},javaline={84},length={1})
  public boolean setY(int aY)
  {
    boolean wasSet = false;
    if (!canSetY) { return false; }
    y = aY;
    wasSet = true;
    // line 640 "../../../../src/Umple.ump"
    if(wasSet) updateStatus();
    return wasSet;
  }

  @umplesourcefile(line={641},file={"Umple.ump"},javaline={95},length={1})
  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    if (!canSetWidth) { return false; }
    width = aWidth;
    wasSet = true;
    // line 641 "../../../../src/Umple.ump"
    if(wasSet) updateStatus();
    return wasSet;
  }

  @umplesourcefile(line={642},file={"Umple.ump"},javaline={106},length={1})
  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    if (!canSetHeight) { return false; }
    height = aHeight;
    wasSet = true;
    // line 642 "../../../../src/Umple.ump"
    if(wasSet) updateStatus();
    return wasSet;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean setStatus(Status aStatus)
  {
    status = aStatus;
    return true;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Coordinate compareTo = (Coordinate)obj;
  
    if (x != compareTo.x)
    {
      return false;
    }

    if (y != compareTo.y)
    {
      return false;
    }

    if (width != compareTo.width)
    {
      return false;
    }

    if (height != compareTo.height)
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    cachedHashCode = cachedHashCode * 23 + x;

    cachedHashCode = cachedHashCode * 23 + y;

    cachedHashCode = cachedHashCode * 23 + width;

    cachedHashCode = cachedHashCode * 23 + height;

    canSetX = false;
    canSetY = false;
    canSetWidth = false;
    canSetHeight = false;
    return cachedHashCode;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  //  @umplesourcefile(line={1763},file={"Umple_Code.ump"},javaline={209},length={91})
  @umplesourcefile(line={1764},file={"Umple_Code.ump"},javaline={210},length={4})
  public Point getTopLeft()
  {
    return new Point(x,y);
  }

  @umplesourcefile(line={1769},file={"Umple_Code.ump"},javaline={216},length={4})
  public Point getTopRight()
  {
    return new Point(x+width,y);
  }

  @umplesourcefile(line={1774},file={"Umple_Code.ump"},javaline={222},length={4})
  public Point getBottomLeft()
  {
    return new Point(x,y+height);
  }

  @umplesourcefile(line={1779},file={"Umple_Code.ump"},javaline={228},length={4})
  public Point getBottomRight()
  {
    return new Point(x+width,y+height);
  }

  @umplesourcefile(line={1784},file={"Umple_Code.ump"},javaline={234},length={4})
  public Point getTopMiddle()
  {
    return new Point(x+width/2,y);
  }

  @umplesourcefile(line={1789},file={"Umple_Code.ump"},javaline={240},length={4})
  public Point getBottomMiddle()
  {
    return new Point(x+width/2,y+height);
  }

  @umplesourcefile(line={1794},file={"Umple_Code.ump"},javaline={246},length={4})
  public Point getRightMiddle()
  {
    return new Point(x+width,y+height/2);
  }

  @umplesourcefile(line={1799},file={"Umple_Code.ump"},javaline={252},length={4})
  public Point getLeftMiddle()
  {
    return new Point(x,y+height/2);
  }

  @umplesourcefile(line={1804},file={"Umple_Code.ump"},javaline={258},length={25})
  public String whereIs(Coordinate neighbour)
  {
    int topDeltaY = getTopMiddle().getY() - neighbour.getBottomMiddle().getY();
    int topDeltaX = getTopMiddle().getX() - neighbour.getBottomMiddle().getX();

    int bottomDeltaY = neighbour.getTopMiddle().getY() - getBottomMiddle().getY();
    int bottomDeltaX = getBottomMiddle().getX() - neighbour.getTopMiddle().getX(); 

    if (topDeltaY > 0 && Math.abs(topDeltaY) > Math.abs(topDeltaX))
    {
      return "top";
    }
    else if (bottomDeltaY > 0 && Math.abs(bottomDeltaY) > Math.abs(bottomDeltaX))
    {
      return "bottom";
    }
    else if (neighbour.getX() < getX())
    {
      return "left";
    }
    else
    {
      return "right";
    }
  }

  @umplesourcefile(line={1830},file={"Umple_Code.ump"},javaline={285},length={7})
  public int distanceTo(Coordinate other)
  {
    int a = other.getX() - getX();
    int b = other.getY() - getY();
    int c = (int) Math.round(Math.sqrt(Math.pow(a,2) + Math.pow(b,2)));
    return c; 
  }

  @umplesourcefile(line={1838},file={"Umple_Code.ump"},javaline={294},length={4})
  public String toString()
  {
    return "(" + getX() + "," + getY() + "," + getWidth() + "," + getHeight() + ")";
  }

  @umplesourcefile(line={1843},file={"Umple_Code.ump"},javaline={300},length={11})
  private void updateStatus()
  {
    if (x == -1 && y == -1 && width == -1 && height == -1)
    {
      setStatus(Status.Undefined);
    }
    else 
    {
      setStatus(Status.Explicit);
    }
  }

}