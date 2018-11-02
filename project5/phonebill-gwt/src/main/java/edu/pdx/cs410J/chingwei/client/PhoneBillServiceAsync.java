package edu.pdx.cs410J.chingwei.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;

import java.util.Date;
import java.util.List;

/**
 * The client-side interface to the phone bill service
 */
public interface PhoneBillServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void getPhoneBill(String name, AsyncCallback<PhoneBill> async);

    /**
     * add a phone call
     * @param toadd phonecall to be added
     * @param bill the bill to add
     * @param result feedback
     */

  void addPhonecall(PhoneCall toadd, String bill, AsyncCallback<String> result);

  /**
   * search for phonecalls
   * @param name bill name
   * @param SD   start date
   * @param ED   end date
   * @param result  feedback
   */
  void searchforphonecalls(String name, Date SD, Date ED, AsyncCallback<PhoneBill> result);

  /**
   * get all the bills
   * @param toshow
   */

  void getallBills(AsyncCallback<List<PhoneBill>> toshow);
  /**
   * Always throws an exception so that we can see how to handle uncaught
   * exceptions in GWT.
   */
  void throwUndeclaredException(AsyncCallback<Void> async);

  /**
   * Always throws a declared exception so that we can see GWT handles it.
   */
  void throwDeclaredException(AsyncCallback<Void> async);

  /**
   * add a phone bill
   * @param name bill name
   * @param toadd feedback
   */
  void addPhonebill(String name, AsyncCallback<String> toadd);
}
