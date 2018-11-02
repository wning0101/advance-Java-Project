package edu.pdx.cs410J.chingwei.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * A GWT remote service that returns a dummy Phone Bill
 */
@RemoteServiceRelativePath("phoneBill")
public interface PhoneBillService extends RemoteService {

    /**
     * bill list
     */
  public List<PhoneBill> blist = new ArrayList<PhoneBill>();

  /**
   * Returns the a dummy Phone Bill
   */
  public PhoneBill getPhoneBill(String name);

    /**
     * get all the bills
     * @return
     */
  public List<PhoneBill> getallBills();

  /**
   * add a phone call
   * @param toadd call to be added
   * @param bill bill to add
   * @return
   */

  public String addPhonecall(PhoneCall toadd, String bill);

  /**
   * add a phone bill
   * @param name bill name
   * @return
   */
  public String addPhonebill(String name);

  /**
   * search for phone calls
   * @param name bill name
   * @param SD start date
   * @param ED end date
   * @return
   */

  public PhoneBill searchforphonecalls(String name, Date SD, Date ED);

  /**
   * Always throws an undeclared exception so that we can see GWT handles it.
   */
  void throwUndeclaredException();

  /**
   * Always throws a declared exception so that we can see GWT handles it.
   */
  void throwDeclaredException() throws IllegalStateException;

}
