package edu.pdx.cs410J.chingwei.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Command;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PhoneBillGwt implements EntryPoint {
  private final Alerter alerter;
  private final PhoneBillServiceAsync phoneBillService;
  private final Logger logger;
  

  @VisibleForTesting
  Button showPhoneBillButton;

  @VisibleForTesting
  Button showUndeclaredExceptionButton;

  @VisibleForTesting
  Button showDeclaredExceptionButton;

  @VisibleForTesting
  Button showClientSideExceptionButton;

  Button createNewPhoneBill;

  Button showAllthephonebills;

  Button AddPhonecall;

  Button searchPhonecalls;
  MenuBar help;

    /**
     * The GWT server
     */
  public PhoneBillGwt() {
    this(new Alerter() {
      @Override
      public void alert(String message) {
        Window.alert(message);
      }
    });
  }

    /**
     * GWT server
     * @param alerter alerter
     */
  @VisibleForTesting
  PhoneBillGwt(Alerter alerter) {
    this.alerter = alerter;
    this.phoneBillService = GWT.create(PhoneBillService.class);
    this.logger = Logger.getLogger("phoneBill");
    Logger.getLogger("").setLevel(Level.INFO);  // Quiet down the default logging
  }

  private void alertOnException(Throwable throwable) {
    Throwable unwrapped = unwrapUmbrellaException(throwable);
    StringBuilder sb = new StringBuilder();
    sb.append(unwrapped.toString());
    sb.append('\n');

    for (StackTraceElement element : unwrapped.getStackTrace()) {
      sb.append("  at ");
      sb.append(element.toString());
      sb.append('\n');
    }

    this.alerter.alert(sb.toString());
  }

  private Throwable unwrapUmbrellaException(Throwable throwable) {
    if (throwable instanceof UmbrellaException) {
      UmbrellaException umbrella = (UmbrellaException) throwable;
      if (umbrella.getCauses().size() == 1) {
        return unwrapUmbrellaException(umbrella.getCauses().iterator().next());
      }

    }

    return throwable;
  }

    /**
     * add the user interface
     * @param panel
     */
  private void addWidgets(VerticalPanel panel) {
    TextBox Billname = new TextBox();
    TextBox NewBill = new TextBox();
    TextBox NewCall = new TextBox();
    TextBox search = new TextBox();
    TextBox from = new TextBox();
    TextBox to = new TextBox();

    searchPhonecalls = new Button("Search phonecalls in specific phonebill.");
    searchPhonecalls.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        searchphonecalls(search.getText(), from.getText(), to.getText());
      }
    });

    NewCall.setVisibleLength(100);
    TextBox Billtoadd = new TextBox();
    showPhoneBillButton = new Button("Show a specific phonebill");
    showPhoneBillButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        showPhoneBill(Billname.getText());
      }
    });
    createNewPhoneBill = new Button("Create a new PhoneBill");
    createNewPhoneBill.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
       createPhoneBill(NewBill.getText());
      }
    });

    showAllthephonebills = new Button(("Show all the phonebills"));
    showAllthephonebills.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        showAllphonebills();
      }
    });

    AddPhonecall = new Button("Add a phonecall");
    AddPhonecall.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        addPhonecall(Billtoadd.getText(), NewCall.getText());
      }
    });

    showUndeclaredExceptionButton = new Button("Show Undeclared exception");
    showUndeclaredExceptionButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        showUndeclaredException();
      }
    });

    showDeclaredExceptionButton = new Button("Show declared exception");
    showDeclaredExceptionButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        showDeclaredException();
      }
    });

    showClientSideExceptionButton= new Button("Show client-side exception");
    showClientSideExceptionButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        throwClientSideException();
      }
    });

    Command cmd = new Command(){
      public void execute(){
        alerter.alert("CS510j Project 5 \n" +
                "Ching-Wei Lin \n" +
                "This project used GWT to build a web application. User can interact the server through the web \n"
                + "user interface, which is able to add phonebill, add phonecall, search phonecalls and pretty print. \n"
                + "The data user enter will be tested by the application. If there is invalid enter, error message will \n"
                + "pop out."); }
    };

    help = new MenuBar(true);
    help.addItem("README", cmd);

    panel.add(showPhoneBillButton);
    panel.add((new Label("Phonebill name")));
    panel.add(Billname);
    panel.add(createNewPhoneBill);
    panel.add((new Label("Phonebill name")));
    panel.add(NewBill);
    panel.add(AddPhonecall);
    panel.add(new Label("Phonebill to be added "));
    panel.add(Billtoadd);
    panel.add(new Label("Phonecall to add"));
    panel.add(NewCall);
    panel.add(searchPhonecalls);
    panel.add(new Label("Phonebill to search"));
    panel.add(search);
    panel.add(new Label("start time"));
    panel.add(from);
    panel.add(new Label("end time"));
    panel.add(to);
    panel.add(showAllthephonebills);
    panel.add(help);
    //panel.add(showUndeclaredExceptionButton);
    //panel.add(showDeclaredExceptionButton);
    //panel.add(showClientSideExceptionButton);
  }

    /**
     * search for phonecaslls
     * @param name bill name
     * @param from start yime
     * @param to end time
     */
  private void searchphonecalls(String name, String from, String to) {
   Date SD = todate(from);
   Date ED = todate(to);
   if(SD.after(ED)){
     alerter.alert("Start time should not be before than end time.");
     return;
   }
   logger.info("Searching for phonecalls");
   phoneBillService.searchforphonecalls(name, SD, ED, new AsyncCallback<PhoneBill>() {
     @Override
     public void onFailure(Throwable throwable) {
       alerter.alert("Invalid input");
     }

     @Override
     public void onSuccess(PhoneBill phoneBill) {
       StringBuilder sb = new StringBuilder();
       Collection<PhoneCall> calls = phoneBill.getPhoneCalls();
       sb.append("\n");
       for (PhoneCall call : calls) {
         sb.append(call);
         sb.append("\n");
       }
       alerter.alert(sb.toString());
     }
   });
  }

    /**
     * change string to date
     * @param tochange string to change
     * @return
     */
  private Date todate(String tochange){
    int FirstSign = tochange.indexOf(" ");
    int SecondSign = tochange.indexOf(" ", FirstSign+1 );
    String date = tochange.substring(0, FirstSign);
    String time = tochange.substring(FirstSign+1, SecondSign);
    String noon = tochange.substring(SecondSign+1, tochange.length());

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
    int hour = 0;
    int min = 0;
    if(noon.equals("AM") || noon.equals("am")){
      if(Integer.parseInt(firstt) == 12){
        hour = 0;
      }
    }
    else if(noon.equals("PM") || noon.equals("pm")){
      if(Integer.parseInt(firstt) != 12){
      hour = Integer.parseInt(firstt)+12;
      }
    }
    else
      hour = Integer.parseInt(firstt);
    min = Integer.parseInt(secondt);

    Date tar = new Date(year-1900, month, day, hour, min);
    return tar;
  }


  private void throwClientSideException() {
    logger.info("About to throw a client-side exception");
    throw new IllegalStateException("Expected exception on the client side");
  }

  private void showUndeclaredException() {
    logger.info("Calling throwUndeclaredException");
    phoneBillService.throwUndeclaredException(new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable ex) {
        alertOnException(ex);
      }

      @Override
      public void onSuccess(Void aVoid) {
        alerter.alert("This shouldn't happen");
      }
    });
  }

  private void showDeclaredException() {
    logger.info("Calling throwDeclaredException");
    phoneBillService.throwDeclaredException(new AsyncCallback<Void>() {
      @Override
      public void onFailure(Throwable ex) {
        alertOnException(ex);
      }

      @Override
      public void onSuccess(Void aVoid) {
        alerter.alert("This shouldn't happen");
      }
    });
  }

    /**
     * add a phonecall to the phonebill
     * @param billtoadd bill to add
     * @param newCall phonecall to be added
     */
  private void addPhonecall(String billtoadd, String newCall) {
    String name = "";
    String caller = "";
    String callee = "";
    String startd = "";
    String startt = "";
    String snoon = "";
    String endd = "";
    String endt = "";
    int first;
    int second;
    int third;
    int forth;
    int fifth;
    int sixth;
    int seven;
    first = newCall.indexOf(" ");
    second = newCall.indexOf(" ", first + 1);
    third = newCall.indexOf(" ", second + 1);
    forth = newCall.indexOf(" ", third + 1);
    fifth = newCall.indexOf(" ", forth + 1);
    sixth = newCall.indexOf(" ", fifth + 1);
    seven = newCall.indexOf(" ", sixth +1);
    name = newCall.substring(0, first);
    caller = newCall.substring(first + 1, second);
    callee = newCall.substring(second + 1, third);
    startd = newCall.substring(third + 1, forth);
    startt = newCall.substring(forth + 1, fifth);
    snoon = newCall.substring(fifth +1, sixth);
    endd = newCall.substring(sixth + 1, seven);
    endt = newCall.substring(seven + 1, newCall.length());
    PhoneCall call = new PhoneCall(name, caller, callee, startd, startt, snoon, endd, endt);
    logger.info("Adding a phonecall");
    phoneBillService.addPhonecall(call, billtoadd, new AsyncCallback<String>() {
      @Override
      public void onFailure(Throwable throwable) {
        alerter.alert("Invalid input");
      }

      @Override
      public void onSuccess(String s) {
        alerter.alert(s);
      }
    });
  }

    /**
     * create a new phonebill
     * @param name customer name
     */
  private void createPhoneBill(String name) {
    logger.info("Creating a new Phonebill");
    phoneBillService.addPhonebill(name, new AsyncCallback<String>() {
      @Override
      public void onFailure(Throwable throwable) {
        alertOnException(throwable);
      }

      @Override
      public void onSuccess(String s) {
        alerter.alert(s);
      }
    });

  }

    /**
     * show all the phone bill
     */
  private void showAllphonebills() {
    logger.info("Call Phonebills");
    phoneBillService.getallBills(new AsyncCallback<List<PhoneBill>>(){

      @Override
      public void onFailure(Throwable throwable) {
        alertOnException(throwable);
      }

      @Override
      public void onSuccess(List<PhoneBill> phoneBills) {
        StringBuilder sb = new StringBuilder();
        for(PhoneBill i:phoneBills){
          sb.append(i.getCustomer()+":"+"\n");
          Collection<PhoneCall> calls = i.getPhoneCalls();
          for(PhoneCall call:calls){
            sb.append(call);
            sb.append("\n");
          }
        }
        if(sb.toString().equals("")){
          alerter.alert("Empty.");
        }
        else
          alerter.alert(sb.toString());
      }
    });
  }

    /**
     * show specific phone bill
     * @param name customer
     */
  private void showPhoneBill(String name) {
    logger.info("Calling getPhoneBill");
    phoneBillService.getPhoneBill(name, new AsyncCallback<PhoneBill>() {

      @Override
      public void onFailure(Throwable ex) {
        alerter.alert("Invalid input.");
      }

      @Override
      public void onSuccess(PhoneBill phoneBill) {
        StringBuilder sb = new StringBuilder(phoneBill.toString());
        Collection<PhoneCall> calls = phoneBill.getPhoneCalls();
        sb.append("\n");
        for (PhoneCall call : calls) {
          sb.append(call);
          sb.append("\n");
        }
        alerter.alert(sb.toString());
      }
    });
  }

    /**
     * set up
     */
  @Override
  public void onModuleLoad() {
    setUpUncaughtExceptionHandler();

    // The UncaughtExceptionHandler won't catch exceptions during module load
    // So, you have to set up the UI after module load...
    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {
        setupUI();
      }
    });
  }

    /**
     * user interface
     */
  private void setupUI() {
    RootPanel rootPanel = RootPanel.get();
    VerticalPanel panel = new VerticalPanel();
    rootPanel.add(panel);

    addWidgets(panel);
  }

  private void setUpUncaughtExceptionHandler() {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable throwable) {
          alerter.alert("invalid input");
       // alertOnException(throwable);
      }
    });
  }

  @VisibleForTesting
  interface Alerter {
    void alert(String message);
  }

}
