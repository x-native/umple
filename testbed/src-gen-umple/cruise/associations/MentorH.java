/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.associations;

// line 115 "../../../src/TestHarnessAssociations.ump"
public class MentorH
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorH Attributes
  private String name;

  //MentorH Associations
  private MentorH superMentor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MentorH(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public MentorH getSuperMentor()
  {
    return superMentor;
  }

  public boolean setSuperMentor(MentorH aNewSuperMentor)
  {
    boolean wasSet = false;
    if (aNewSuperMentor == null)
    {
      MentorH existingSuperMentor = superMentor;
      superMentor = null;
      
      if (existingSuperMentor != null && existingSuperMentor.getSuperMentor() != null)
      {
        existingSuperMentor.setSuperMentor(null);
      }
      wasSet = true;
      return wasSet;
    }

    MentorH currentSuperMentor = getSuperMentor();
    if (currentSuperMentor != null && !currentSuperMentor.equals(aNewSuperMentor))
    {
      currentSuperMentor.setSuperMentor(null);
    }

    superMentor = aNewSuperMentor;
    MentorH existingSuperMentor = aNewSuperMentor.getSuperMentor();

    if (!equals(existingSuperMentor))
    {
      aNewSuperMentor.setSuperMentor(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (superMentor != null)
    {
      superMentor.setSuperMentor(null);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "superMentor = "+(getSuperMentor()!=null?Integer.toHexString(System.identityHashCode(getSuperMentor())):"null")
     + outputString;
  }
}