package edu.pdx.cs410J.chingwei.client;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {
  private String caller;
  private String callee;
  private String stime;
  private String etime;
  private String snoon;
  private String enoon;
  public Date start;
  public Date end;
  public int caler;
  private int duration;
  /**
   * In order for GWT to serialize this class (so that it can be sent between
   * the client and the server), it must have a zero-argument constructor.
   */
  public PhoneCall() {
    this.caller = null;
    this.callee = null;
    this.stime = null;
    this.etime = null;
    this.start = null;
    this.end = null;
    this.snoon = null;
    this.enoon = null;
    this.caler = 0;
    this.duration = 0;

  }
  /***
   * The PhoneCall class constructor which asks information from the user and build up a phonecall.
   */
  public PhoneCall(String caller, String callee, String start, String startt, String snoon, String end, String endt, String enoon){
    this.caller = Notnumber(caller, "caller");
    this.callee = Notnumber(callee, "callee");

    this.stime = Notdate(start, "start") +" "+ Nottime(startt, "start", snoon);
    this.etime = Notdate(end, "end") +" "+ Nottime(endt, "end", enoon);
    this.start = todate(this.stime);
    this.end = todate(this.etime);
    this.caler = tonumber(this.caller);
    this.duration = countduration(this.end, this.start);
    this.snoon = snoon;
    this.enoon = enoon;
    if(this.start.after(this.end)){
      System.err.print("Start time should never be after end time.");
    }

  }

  /***
   * Check if the time is valid.
   * @param totest the tie to be tested
   * @return
   */

  private String Nottime(String totest, String check, String check_noon){
    try {
      int FirstSign = totest.indexOf(":");
      String newtime;
      if (FirstSign == -1) {
        throw new UnsupportedOperationException("Wrong format for time.");
      }
      String first = totest.substring(0, FirstSign);
      String second = totest.substring(FirstSign + 1, totest.length());
      int hour, min;
      hour = Integer.parseInt(first);
      min = Integer.parseInt(second);
      if (hour < 0 || hour > 12 || min < 0 || min > 60) {
        throw new UnsupportedOperationException("Wrong format for time. \n");
      } else {
        if(check_noon.equals("am") || check_noon.equals("AM")){
          return totest;
        }
        else if(check_noon.equals("pm") || check_noon.equals("PM")){
          if(hour == 12){
            return totest;
          }
          newtime = Integer.toString(hour+12) + ":" + Integer.toString(min);
          return newtime;
        }
        else
          throw new UnsupportedOperationException();
      }
    }
    catch (UnsupportedOperationException | NumberFormatException a){
      System.err.print("Wrong format for time. \n");
      return totest;
    }
  }


  /***
   * Check if the phone number is valid.
   * @param totest the number to be tested
   * @return
   */

  private String Notnumber(String totest, String check) {
    try {

      int FirstSign = totest.indexOf("-");
      int LastSign = totest.lastIndexOf("-");
      if (FirstSign == LastSign || FirstSign == -1 || LastSign == -1) {
        throw new UnsupportedOperationException("Wrong format for number.");
      }
      String first = totest.substring(0, FirstSign);
      String second = totest.substring(FirstSign + 1, LastSign);
      String third = totest.substring(LastSign + 1, totest.length());
      int month, day, year;
      month = Integer.parseInt(first);
      day = Integer.parseInt(second);
      year = Integer.parseInt(third);
      if (first.length() != 3 || second.length() != 3 || third.length() != 4) {
        throw new UnsupportedOperationException("Wrong format for number. \n");
      } else {
        return totest;
      }
    }
    catch(UnsupportedOperationException | NumberFormatException e){
      System.err.print("Wrong format for phone number. \n");
      return totest;
    }
  }


  /***
   * Check if the date is valid.
   * @param totest the date to be tested
   * @return
   */
  private String Notdate(String totest, String check){
    try {
      int FirstSign = totest.indexOf("/");
      int LastSign = totest.lastIndexOf("/");
      if (FirstSign == LastSign || FirstSign == -1 || LastSign == -1) {
        throw new UnsupportedOperationException("Wrong format for time.");
      }
      String first = totest.substring(0, FirstSign);
      String second = totest.substring(FirstSign + 1, LastSign);
      String third = totest.substring(LastSign + 1, totest.length());
      int month, day, year;
      month = Integer.parseInt(first);
      day = Integer.parseInt(second);
      year = Integer.parseInt(third);
      if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1000 || year > 9999) {
        throw new UnsupportedOperationException("Wrong range for time. \n");
      } else {
        return totest;
      }
    }
    catch (UnsupportedOperationException | NumberFormatException a)
    {
      System.err.print("Wrong format for date. \n");
      return totest;
    }
  }

  /***
   * Transfer the phone number to integer to compare
   * @param tochange the phone number to be transfer
   * @return
   */
  private int tonumber(String tochange){
    int FirstSign = tochange.indexOf("-");
    int LastSign = tochange.lastIndexOf("-");
    if (FirstSign == LastSign || FirstSign == -1 || LastSign == -1) {
      throw new UnsupportedOperationException("Wrong format for number.");
    }
    String first = tochange.substring(0, FirstSign);
    String second = tochange.substring(FirstSign + 1, LastSign);
    String third = tochange.substring(LastSign + 1, tochange.length());
    String com = first + second;

    int num;
    num = Integer.parseInt(com);
    return num;
  }

  /***
   * Transfer the String date to Date format
   * @param tochange the String to be changed
   * @return
   */
  private Date todate(String tochange){
    int FirstSign = tochange.indexOf(" ");
    String date = tochange.substring(0, FirstSign);
    String time = tochange.substring(FirstSign+1, tochange.length());

    int FirstSignd = date.indexOf("/");
    int LastSignd = date.lastIndexOf("/");
    String first = date.substring(0, FirstSignd);
    String second = date.substring(FirstSignd + 1, LastSignd);
    String third = date.substring(LastSignd + 1, date.length());
    int month, day, year;
    month = Integer.parseInt(first);
    day = Integer.parseInt(second);
    year = Integer.parseInt(third);

    int FirstSignt = time.indexOf(":");
    String firstt = time.substring(0, FirstSignt);
    String secondt = time.substring(FirstSignt + 1, time.length());
    int hour, min;
    hour = Integer.parseInt(firstt);
    min = Integer.parseInt(secondt);
    if (month == 12){
      Date tar = new Date(year-1900, 12, day, hour, min);
      return tar;
    }
    Date tar = new Date(year-1900, month, day, hour, min);
    return tar;
  }

  /***
   * Calculate to phone call duration
   * @param a start time
   * @param b end time
   * @return
   */

  public int countduration(Date a, Date b){
    return (a.getHours()-b.getHours())* 60 + a.getMinutes() - b.getMinutes();
  }

  /***
   * Return am/pm for start time
   * @return
   */
  public String getSnoon(){
    return this.snoon;
  }

  /***
   * Return am/pm for end time
   * @return
   */
  public String getEnoon(){
    return this.enoon;
  }

  /***
   * Return the caller
   * @return
   */

  @Override
  public String getCaller() {
    return this.caller;
  }

  @Override
  public Date getStartTime() {
    return this.start;
  }

  @Override
  public String getStartTimeString() {

    StringBuilder sb = new StringBuilder();
    int year = this.start.getYear() + 1900;
    int month = this.start.getMonth();
    if(month == 0){
      month = 12;
    }
    int day = this.start.getDate();
    sb.append(month + "/"+ day +"/" + year);
    sb.append(" "+this.start.getHours()+":"+this.start.getMinutes()+" "+this.snoon);
    return sb.toString();
  }

  @Override
  public String getCallee() {
    return this.callee;
  }

  @Override
  public Date getEndTime() {
    return this.end;
  }

  @Override
  public String getEndTimeString() {
    StringBuilder sb = new StringBuilder();
    int year = this.end.getYear() + 1900;
    int month = this.end.getMonth();
    if(month == 0){
      month = 12;
    }
    sb.append(month+ "/"+ this.end.getDate() +"/" + year);
    sb.append(" "+this.end.getHours()+":"+this.end.getMinutes()+" "+this.enoon);
    return sb.toString();

  }

}
