package edu.pdx.cs410J.chingwei.client;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private Collection<PhoneCall> calls = new ArrayList<>();
  private String customer;

  /**
   * In order for GWT to serialize this class (so that it can be sent between
   * the client and the server), it must have a zero-argument constructor.
   */
  public PhoneBill() {
  }

  /**
   * Copy constructor
   * @param name customer name
   */
  public PhoneBill(String name){
    this.customer = name;
  }

  /**
   * get customer name
   * @return
   */
  @Override
  public String getCustomer() {
    return this.customer;
  }

  /**
   * add a phonecall
   * @param call
   */
  @Override
  public void addPhoneCall(PhoneCall call) {
    this.calls.add(call);
  }

  /**
   * return phonecalls
   * @return
   */
  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.calls;
  }
}
