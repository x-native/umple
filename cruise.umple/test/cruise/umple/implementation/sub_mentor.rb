# PLEASE DO NOT EDIT THIS CODE
# This code was generated using the UMPLE @UMPLE_VERSION@ modeling language!

module Example

class SubMentor < Mentor


  #------------------------
  # CONSTRUCTOR
  #------------------------

  def initialize(a_name)
    super(a_name)
    @initialized = false
    @deleted = false
    @initialized = true
  end

  #------------------------
  # INTERFACE
  #------------------------

  def delete
    @deleted = true
    super.delete
  end

end
end