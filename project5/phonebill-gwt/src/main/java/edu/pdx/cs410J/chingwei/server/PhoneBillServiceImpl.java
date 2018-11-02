package edu.pdx.cs410J.chingwei.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.chingwei.client.PhoneBill;
import edu.pdx.cs410J.chingwei.client.PhoneCall;
import edu.pdx.cs410J.chingwei.client.PhoneBillService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PhoneBillServiceImpl extends RemoteServiceServlet implements PhoneBillService
{
  @Override
  public PhoneBill getPhoneBill(String name) {
    PhoneBill phonebill = new PhoneBill();
    phonebill.addPhoneCall(new PhoneCall());
    if(this.blist.isEmpty()){
      return phonebill;
    }
    for(PhoneBill i:this.blist){
      if(i.getCustomer().equals(name)){
        return i;
      }
    }
    return phonebill;
  }

    /**
     * get all bills
     * @return
     */

  @Override
  public List<PhoneBill> getallBills() {
    return this.blist;
  }

    /**
     * add a phonecall
     * @param toadd call to be added
     * @param bill bill to add
     * @return
     */
  @Override
  public String addPhonecall(PhoneCall toadd, String bill) {
    for(PhoneBill i:this.blist){
      if(i.getCustomer().equals(bill)){
        i.addPhoneCall(toadd);
        return "Successfully add a phonecall to " + bill + "'s bill";
      }
    }
    return "fail to add phonecall";
  }

    /**
     * add a phone bill
     * @param name bill name
     * @return
     */
  @Override
  public String addPhonebill(String name) {
    if(name.equals("")){
      return "fail to add phonebill";
    }
    for(PhoneBill i:this.blist){
        if(i.getCustomer().equals(name)){
          return "This phonebill already exists.";
        }
    }
    PhoneBill toadd = new PhoneBill(name);
    this.blist.add(toadd);

    return "successfully add a phonebill";
  }

    /**
     * search for phonecalls
     * @param name bill name
     * @param SD start date
     * @param ED end date
     * @return
     */
  @Override
  public PhoneBill searchforphonecalls(String name, Date SD, Date ED) {
    for(PhoneBill i:this.blist){
      if(i.getCustomer().equals(name)){
        Collection<PhoneCall> calls = i.getPhoneCalls();
        PhoneBill result = new PhoneBill(i.getCustomer());
        for (PhoneCall call : calls) {

          if(call.start.after(SD)){
            if(call.start.before(ED)) {
              result.addPhoneCall(call);
            }
          }

        }
        return result;
      }
    }
    return null;
  }


  @Override
  public void throwUndeclaredException() {
    throw new IllegalStateException("Expected undeclared exception");
  }

  @Override
  public void throwDeclaredException() throws IllegalStateException {
    throw new IllegalStateException("Expected declared exception");
  }

  /**
   * Log unhandled exceptions to standard error
   *
   * @param unhandled
   *        The exception that wasn't handled
   */
  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }

}
