package Donor;

public class donor {
    private String fname;
   private  String lname;
  private   String bloodGroup;
   private String gender;
    private String phoneNumber;
   private String dateofbirth;
  private  String dist;
    private String address;
    private String email_id;
    private String phoneno;
   private String username;
   private String pass;

    public donor()
    {

    }

    //CREATE_USER
    public donor(String username_, String pass_) {
        this.username = username_;
        this.pass = pass_;
    }
    //ALL_INFO
    public donor( String fname_,String lname_, String gender_, String bloodGroup_, String dateofbirth_, String dist_, String address_, String email_id_, String phoneno_,String username_,String pass_) {
        this.fname = fname_;
        this.lname = lname_;
        this.gender = gender_;
        this.bloodGroup=bloodGroup_;
        this.dateofbirth = dateofbirth_;
        this.dist = dist_;
        this.address = address_;
        this.email_id = email_id_;
        this.phoneno = phoneno_;
        this.username=username_;
        this.pass=pass_;
    }
    public donor( String fname_,String lname_, String gender_, String bloodGroup_, String dateofbirth_, String dist_, String address_, String email_id_, String phoneno_,String username_) {
        this.fname = fname_;
        this.lname = lname_;
        this.gender = gender_;
        this.bloodGroup=bloodGroup_;
        this.dateofbirth = dateofbirth_;
        this.dist = dist_;
        this.address = address_;
        this.email_id = email_id_;
        this.phoneno = phoneno_;
        this.username=username_;
    }



    public String getFirstname()
    {
        return this.fname ;
    }
    public void setFirstname(String fname_)
    {
        this.fname=fname_;
    }

    public String getLastname()
    {
        return this.lname ;
    }
    public void setlastname(String lname_)
    {
        this.lname=lname_;
    }
    public  String getGender()
    {
        return this.gender ;
    }
    public void setGender(String gender_)
    {
        this.gender=gender_;
    }
    public String getBloodGroup()
    {
        return  this.bloodGroup;
    }
    public  void setBloodGroup(String bloodGroup_)
    {
        this.bloodGroup= bloodGroup_;
    }
    public String getDateofbirth()
    {
        return  this.dateofbirth;
    }
    public void setDateofbirth(String dateofbirth_)
    {
        this.dateofbirth=dateofbirth_;
    }
    public  String  getDist()
    {
        return this.dist;
    }
    public  void setDist(String dist_)
    {
        this.dist=dist_;
    }
    public  String getAddress()
    {
        return this.address;
    }
    public void setAddress(String address_)
    {
        this.address=address_;
    }
    public String getEmail_id()
    {
        return this.email_id;
    }
    public  void setEmail_id(String email_id_)
    {
        this.email_id=email_id_;
    }
    public  String getPhoneno()
    {
        return this.phoneno;
    }
    public void setPhoneno(String phoneno_)
    {
        this.phoneno=phoneno_;
    }
    public String getUsername() {
        return this.username;
    }

    public  void setUsername(String  username_)
    {
        this.username=username_;
    }
    public String getPass()
    {
        return this.pass;
    }
    public  void setPass(String pass_)
    {
        this.pass=pass_;
    }
}
