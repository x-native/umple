/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.11.1.3376 modeling language!*/

package cruise.umple.compiler;

public class PrimitiveType extends Type
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PrimitiveType Attributes
  private String typeName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PrimitiveType(String aTypeName)
  {
    super();
    typeName = aTypeName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTypeName(String aTypeName)
  {
    boolean wasSet = false;
    typeName = aTypeName;
    wasSet = true;
    return wasSet;
  }

  public String getTypeName()
  {
    return typeName;
  }

  public void delete()
  {
    super.delete();
  }

}