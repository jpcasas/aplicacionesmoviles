/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asms;

import javax.microedition.io.Connector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import org.netbeans.microedition.lcdui.SimpleTableModel;
import org.netbeans.microedition.lcdui.SplashScreen;
import org.netbeans.microedition.lcdui.TableItem;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.lcdui.wma.SMSComposer;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author RAIDEN
 */
public class ASMS extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List menu;
    private SMSComposer smsComposer;
    private SplashScreen splashScreen;
    private Form conversationForm;
    private TableItem tableItem;
    private List conversationsMenu;
    private Alert messageReceivedAlert;
    private WaitScreen waitScreen;
    private Alert messageSentAlert;
    private Alert messageNotSentAlert;
    private Command menuExitCommand;
    private Command menuOkCommand;
    private Command smsComposerCancelCommand;
    private Command conversationsMenuBackCommand;
    private Command conversationsMenuOkCommand;
    private Command conversationFormBackCommand;
    private Command messageReceivedOkCommand;
    private Command messageReceivedCancelCommand;
    private Command errorAlertExitCommand;
    private Image asmsImage;
    private Image createMessageImage;
    private Font menuFont;
    private Image conversationsImage;
    private Font conversationsMenuFont;
    private Image conversationImage;
    private SimpleTableModel conversationTableModel;
    private Font conversationFont;
    private SimpleCancellableTask sendMessageTask;
    private Image sendingMessageImage;
    //</editor-fold>//GEN-END:|fields|0|
    private RecordStore rs;
    static final String REC_STORE = "asms_rms";
    private boolean done;
    private MessageConnection smsConnection;
    private String senderAddress;
    private String[][] conversation;

    /**
     * The ASMS constructor.
     */
    public ASMS() {
        openRecordStore();
    }

    private void openRecordStore() {
        try {
            rs = RecordStore.openRecordStore(REC_STORE, true);
        } catch (Exception e) {
            db(e);
        }
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
            connectSMSServer(getAppProperty("serverPort"));
            done = false;
            new Thread(new SMSServer()).start();
        }
        midletPaused = false;
    }

    /* Make a connection */
    public void connectSMSServer(String port) {
        try {
            smsConnection = (MessageConnection) Connector.open("sms://:" + port);
        } catch (Exception e) {
            db(e);
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == conversationForm) {//GEN-BEGIN:|7-commandAction|1|54-preAction
            if (command == conversationFormBackCommand) {//GEN-END:|7-commandAction|1|54-preAction
                // write pre-action user code here
                switchDisplayable(null, getConversationsMenu());//GEN-LINE:|7-commandAction|2|54-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|44-preAction
        } else if (displayable == conversationsMenu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|3|44-preAction
                // write pre-action user code here
                conversationsMenuAction();//GEN-LINE:|7-commandAction|4|44-postAction
                // write post-action user code here
            } else if (command == conversationsMenuBackCommand) {//GEN-LINE:|7-commandAction|5|48-preAction
                // write pre-action user code here
                switchDisplayable(null, getMenu());//GEN-LINE:|7-commandAction|6|48-postAction
                // write post-action user code here
            } else if (command == conversationsMenuOkCommand) {//GEN-LINE:|7-commandAction|7|51-preAction
                // write pre-action user code here
                switchDisplayable(null, getConversationForm());//GEN-LINE:|7-commandAction|8|51-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|16-preAction
        } else if (displayable == menu) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|16-preAction
                // write pre-action user code here
                menuAction();//GEN-LINE:|7-commandAction|10|16-postAction
                // write post-action user code here
            } else if (command == menuExitCommand) {//GEN-LINE:|7-commandAction|11|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|12|19-postAction
                // write post-action user code here
            } else if (command == menuOkCommand) {//GEN-LINE:|7-commandAction|13|21-preAction
                // write pre-action user code here
                menuAction();//GEN-LINE:|7-commandAction|14|21-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|74-preAction
        } else if (displayable == messageReceivedAlert) {
            if (command == messageReceivedCancelCommand) {//GEN-END:|7-commandAction|15|74-preAction
                // write pre-action user code here
                switchDisplayable(null, getMenu());//GEN-LINE:|7-commandAction|16|74-postAction
                // write post-action user code here
            } else if (command == messageReceivedOkCommand) {//GEN-LINE:|7-commandAction|17|71-preAction
                // write pre-action user code here
                switchDisplayable(null, getConversationForm());//GEN-LINE:|7-commandAction|18|71-postAction
                // write post-action user code here
                conversationTableModel.setValues(conversation);
                conversationTableModel.fireTableModelChanged();
            }//GEN-BEGIN:|7-commandAction|19|26-preAction
        } else if (displayable == smsComposer) {
            if (command == SMSComposer.SEND_COMMAND) {//GEN-END:|7-commandAction|19|26-preAction
                // write pre-action user code here
                switchDisplayable(null, getWaitScreen());//GEN-LINE:|7-commandAction|20|26-postAction
                // write post-action user code here
            } else if (command == smsComposerCancelCommand) {//GEN-LINE:|7-commandAction|21|39-preAction
                // write pre-action user code here
                smsComposer.setPhoneNumber("");
                smsComposer.setMessage("");
                switchDisplayable(null, getMenu());//GEN-LINE:|7-commandAction|22|39-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|29-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|23|29-preAction
                // write pre-action user code here
                switchDisplayable(null, getMenu());//GEN-LINE:|7-commandAction|24|29-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|25|89-preAction
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|25|89-preAction
                // write pre-action user code here
                switchDisplayable(getMessageNotSentAlert(), getMenu());//GEN-LINE:|7-commandAction|26|89-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|27|88-preAction
                // write pre-action user code here
                smsComposer.setPhoneNumber("");
                smsComposer.setMessage("");
                switchDisplayable(getMessageSentAlert(), getMenu());//GEN-LINE:|7-commandAction|28|88-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|29|7-postCommandAction
        }//GEN-END:|7-commandAction|29|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|30|
    //</editor-fold>//GEN-END:|7-commandAction|30|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: menu ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of menu component.
     * @return the initialized component instance
     */
    public List getMenu() {
        if (menu == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            menu = new List("ASMS", Choice.IMPLICIT);//GEN-BEGIN:|14-getter|1|14-postInit
            menu.append("New message", getCreateMessageImage());
            menu.append("My conversations", getConversationsImage());
            menu.addCommand(getMenuExitCommand());
            menu.addCommand(getMenuOkCommand());
            menu.setCommandListener(this);
            menu.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            menu.setSelectCommand(getMenuOkCommand());
            menu.setSelectedFlags(new boolean[] { false, false });
            menu.setFont(0, getMenuFont());
            menu.setFont(1, getMenuFont());//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return menu;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: menuAction ">//GEN-BEGIN:|14-action|0|14-preAction
    /**
     * Performs an action assigned to the selected list element in the menu component.
     */
    public void menuAction() {//GEN-END:|14-action|0|14-preAction
        // enter pre-action user code here
        String __selectedString = getMenu().getString(getMenu().getSelectedIndex());//GEN-BEGIN:|14-action|1|32-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("New message")) {//GEN-END:|14-action|1|32-preAction
                // write pre-action user code here
                switchDisplayable(null, getSmsComposer());//GEN-LINE:|14-action|2|32-postAction
                // write post-action user code here
            } else if (__selectedString.equals("My conversations")) {//GEN-LINE:|14-action|3|35-preAction
                // write pre-action user code here
                switchDisplayable(null, getConversationsMenu());//GEN-LINE:|14-action|4|35-postAction
                // write post-action user code here
            }//GEN-BEGIN:|14-action|5|14-postAction
        }//GEN-END:|14-action|5|14-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|14-action|6|
    //</editor-fold>//GEN-END:|14-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: smsComposer ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of smsComposer component.
     * @return the initialized component instance
     */
    public SMSComposer getSmsComposer() {
        if (smsComposer == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            smsComposer = new SMSComposer(getDisplay());//GEN-BEGIN:|24-getter|1|24-postInit
            smsComposer.setTitle("New text message");
            smsComposer.addCommand(SMSComposer.SEND_COMMAND);
            smsComposer.addCommand(getSmsComposerCancelCommand());
            smsComposer.setCommandListener(this);
            smsComposer.setBGColor(-5197578);
            smsComposer.setFGColor(-16777216);
            smsComposer.setPort(50000);
            smsComposer.setSendAutomatically(false);
            smsComposer.setPhoneNumberLabel("To:");
            smsComposer.setMessageLabel("Message:");//GEN-END:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return smsComposer;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|27-getter|1|27-postInit
            splashScreen.setTitle("ASMS");
            splashScreen.setCommandListener(this);
            splashScreen.setFullScreenMode(true);
            splashScreen.setImage(getAsmsImage());
            splashScreen.setText("Advanced SMS");
            splashScreen.setTimeout(5000);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: menuExitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of menuExitCommand component.
     * @return the initialized component instance
     */
    public Command getMenuExitCommand() {
        if (menuExitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            menuExitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return menuExitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: menuOkCommand ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of menuOkCommand component.
     * @return the initialized component instance
     */
    public Command getMenuOkCommand() {
        if (menuOkCommand == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            menuOkCommand = new Command("Select", Command.OK, 0);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return menuOkCommand;
    }
    //</editor-fold>//GEN-END:|20-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: asmsImage ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initiliazed instance of asmsImage component.
     * @return the initialized component instance
     */
    public Image getAsmsImage() {
        if (asmsImage == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|30-getter|1|30-@java.io.IOException
                asmsImage = Image.createImage("/resources/ASMS.png");
            } catch (java.io.IOException e) {//GEN-END:|30-getter|1|30-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|30-getter|2|30-postInit
            // write post-init user code here
        }//GEN-BEGIN:|30-getter|3|
        return asmsImage;
    }
    //</editor-fold>//GEN-END:|30-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: menuFont ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of menuFont component.
     * @return the initialized component instance
     */
    public Font getMenuFont() {
        if (menuFont == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            menuFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return menuFont;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: createMessageImage ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of createMessageImage component.
     * @return the initialized component instance
     */
    public Image getCreateMessageImage() {
        if (createMessageImage == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|34-getter|1|34-@java.io.IOException
                createMessageImage = Image.createImage("/resources/Create message.png");
            } catch (java.io.IOException e) {//GEN-END:|34-getter|1|34-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|34-getter|2|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|3|
        return createMessageImage;
    }
    //</editor-fold>//GEN-END:|34-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationsImage ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of conversationsImage component.
     * @return the initialized component instance
     */
    public Image getConversationsImage() {
        if (conversationsImage == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|36-getter|1|36-@java.io.IOException
                conversationsImage = Image.createImage("/resources/Conversations.png");
            } catch (java.io.IOException e) {//GEN-END:|36-getter|1|36-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|36-getter|2|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|3|
        return conversationsImage;
    }
    //</editor-fold>//GEN-END:|36-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: smsComposerCancelCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of smsComposerCancelCommand component.
     * @return the initialized component instance
     */
    public Command getSmsComposerCancelCommand() {
        if (smsComposerCancelCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            smsComposerCancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return smsComposerCancelCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationForm ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initiliazed instance of conversationForm component.
     * @return the initialized component instance
     */
    public Form getConversationForm() {
        if (conversationForm == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            conversationForm = new Form("Conversation", new Item[] { getTableItem() });//GEN-BEGIN:|42-getter|1|42-postInit
            conversationForm.addCommand(getConversationFormBackCommand());
            conversationForm.setCommandListener(this);//GEN-END:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return conversationForm;
    }
    //</editor-fold>//GEN-END:|42-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationsMenu ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of conversationsMenu component.
     * @return the initialized component instance
     */
    public List getConversationsMenu() {
        if (conversationsMenu == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            conversationsMenu = new List("My conversations", Choice.IMPLICIT);//GEN-BEGIN:|43-getter|1|43-postInit
            conversationsMenu.append("", getConversationImage());
            conversationsMenu.addCommand(getConversationsMenuBackCommand());
            conversationsMenu.addCommand(getConversationsMenuOkCommand());
            conversationsMenu.setCommandListener(this);
            conversationsMenu.setSelectCommand(getConversationsMenuOkCommand());
            conversationsMenu.setSelectedFlags(new boolean[] { false });
            conversationsMenu.setFont(0, getConversationsMenuFont());//GEN-END:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return conversationsMenu;
    }
    //</editor-fold>//GEN-END:|43-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: conversationsMenuAction ">//GEN-BEGIN:|43-action|0|43-preAction
    /**
     * Performs an action assigned to the selected list element in the conversationsMenu component.
     */
    public void conversationsMenuAction() {//GEN-END:|43-action|0|43-preAction
        // enter pre-action user code here
        String __selectedString = getConversationsMenu().getString(getConversationsMenu().getSelectedIndex());//GEN-BEGIN:|43-action|1|56-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("")) {//GEN-END:|43-action|1|56-preAction
                // write pre-action user code here
//GEN-LINE:|43-action|2|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|43-action|3|43-postAction
        }//GEN-END:|43-action|3|43-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|43-action|4|
    //</editor-fold>//GEN-END:|43-action|4|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationsMenuBackCommand ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of conversationsMenuBackCommand component.
     * @return the initialized component instance
     */
    public Command getConversationsMenuBackCommand() {
        if (conversationsMenuBackCommand == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            conversationsMenuBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return conversationsMenuBackCommand;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationsMenuOkCommand ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of conversationsMenuOkCommand component.
     * @return the initialized component instance
     */
    public Command getConversationsMenuOkCommand() {
        if (conversationsMenuOkCommand == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            conversationsMenuOkCommand = new Command("Open", Command.OK, 0);//GEN-LINE:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return conversationsMenuOkCommand;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationFormBackCommand ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of conversationFormBackCommand component.
     * @return the initialized component instance
     */
    public Command getConversationFormBackCommand() {
        if (conversationFormBackCommand == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            conversationFormBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return conversationFormBackCommand;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationImage ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of conversationImage component.
     * @return the initialized component instance
     */
    public Image getConversationImage() {
        if (conversationImage == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|57-getter|1|57-@java.io.IOException
                conversationImage = Image.createImage("/resources/Conversation.png");
            } catch (java.io.IOException e) {//GEN-END:|57-getter|1|57-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|57-getter|2|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|3|
        return conversationImage;
    }
    //</editor-fold>//GEN-END:|57-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationsMenuFont ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of conversationsMenuFont component.
     * @return the initialized component instance
     */
    public Font getConversationsMenuFont() {
        if (conversationsMenuFont == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            conversationsMenuFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return conversationsMenuFont;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tableItem ">//GEN-BEGIN:|63-getter|0|63-preInit
    /**
     * Returns an initiliazed instance of tableItem component.
     * @return the initialized component instance
     */
    public TableItem getTableItem() {
        if (tableItem == null) {//GEN-END:|63-getter|0|63-preInit
            // write pre-init user code here
            tableItem = new TableItem(getDisplay(), "");//GEN-BEGIN:|63-getter|1|63-postInit
            tableItem.setLayout(ImageItem.LAYOUT_LEFT | Item.LAYOUT_TOP | Item.LAYOUT_VCENTER | ImageItem.LAYOUT_NEWLINE_BEFORE | Item.LAYOUT_EXPAND | Item.LAYOUT_VEXPAND);
            tableItem.setModel(getConversationTableModel());
            tableItem.setValuesFont(getConversationFont());//GEN-END:|63-getter|1|63-postInit
            // write post-init user code here
        }//GEN-BEGIN:|63-getter|2|
        return tableItem;
    }
    //</editor-fold>//GEN-END:|63-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationTableModel ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of conversationTableModel component.
     * @return the initialized component instance
     */
    public SimpleTableModel getConversationTableModel() {
        if (conversationTableModel == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            conversationTableModel = new SimpleTableModel(new java.lang.String[][] {//GEN-BEGIN:|64-getter|1|64-postInit
                new java.lang.String[] { "" }}, null);//GEN-END:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return conversationTableModel;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: conversationFont ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initiliazed instance of conversationFont component.
     * @return the initialized component instance
     */
    public Font getConversationFont() {
        if (conversationFont == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            conversationFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);//GEN-LINE:|65-getter|1|65-postInit
            // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return conversationFont;
    }
    //</editor-fold>//GEN-END:|65-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageReceivedAlert ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of messageReceivedAlert component.
     * @return the initialized component instance
     */
    public Alert getMessageReceivedAlert() {
        if (messageReceivedAlert == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            messageReceivedAlert = new Alert("Message received", "1 new message", null, AlertType.ALARM);//GEN-BEGIN:|66-getter|1|66-postInit
            messageReceivedAlert.addCommand(getMessageReceivedCancelCommand());
            messageReceivedAlert.addCommand(getMessageReceivedOkCommand());
            messageReceivedAlert.setCommandListener(this);
            messageReceivedAlert.setTimeout(Alert.FOREVER);//GEN-END:|66-getter|1|66-postInit
            // write post-init user code here
        }//GEN-BEGIN:|66-getter|2|
        return messageReceivedAlert;
    }
    //</editor-fold>//GEN-END:|66-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageReceivedOkCommand ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of messageReceivedOkCommand component.
     * @return the initialized component instance
     */
    public Command getMessageReceivedOkCommand() {
        if (messageReceivedOkCommand == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            messageReceivedOkCommand = new Command("Show", Command.OK, 0);//GEN-LINE:|70-getter|1|70-postInit
            // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return messageReceivedOkCommand;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageReceivedCancelCommand ">//GEN-BEGIN:|73-getter|0|73-preInit
    /**
     * Returns an initiliazed instance of messageReceivedCancelCommand component.
     * @return the initialized component instance
     */
    public Command getMessageReceivedCancelCommand() {
        if (messageReceivedCancelCommand == null) {//GEN-END:|73-getter|0|73-preInit
            // write pre-init user code here
            messageReceivedCancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|73-getter|1|73-postInit
            // write post-init user code here
        }//GEN-BEGIN:|73-getter|2|
        return messageReceivedCancelCommand;
    }
    //</editor-fold>//GEN-END:|73-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitScreen ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initiliazed instance of waitScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (waitScreen == null) {//GEN-END:|85-getter|0|85-preInit
            // write pre-init user code here
            waitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|85-getter|1|85-postInit
            waitScreen.setTitle("");
            waitScreen.setCommandListener(this);
            waitScreen.setFullScreenMode(true);
            waitScreen.setImage(getSendingMessageImage());
            waitScreen.setText("Sending message...");
            waitScreen.setTask(getSendMessageTask());//GEN-END:|85-getter|1|85-postInit
            // write post-init user code here
        }//GEN-BEGIN:|85-getter|2|
        return waitScreen;
    }
    //</editor-fold>//GEN-END:|85-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageSentAlert ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initiliazed instance of messageSentAlert component.
     * @return the initialized component instance
     */
    public Alert getMessageSentAlert() {
        if (messageSentAlert == null) {//GEN-END:|91-getter|0|91-preInit
            // write pre-init user code here
            messageSentAlert = new Alert("", "Message sent", null, AlertType.CONFIRMATION);//GEN-BEGIN:|91-getter|1|91-postInit
            messageSentAlert.setTimeout(2000);//GEN-END:|91-getter|1|91-postInit
            // write post-init user code here
        }//GEN-BEGIN:|91-getter|2|
        return messageSentAlert;
    }
    //</editor-fold>//GEN-END:|91-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendMessageTask ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of sendMessageTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getSendMessageTask() {
        if (sendMessageTask == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            sendMessageTask = new SimpleCancellableTask();//GEN-BEGIN:|90-getter|1|90-execute
            sendMessageTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|90-getter|1|90-execute
                    // write task-execution user code here
                    smsComposer.sendSMS();
                }//GEN-BEGIN:|90-getter|2|90-postInit
            });//GEN-END:|90-getter|2|90-postInit
            // write post-init user code here
        }//GEN-BEGIN:|90-getter|3|
        return sendMessageTask;
    }
    //</editor-fold>//GEN-END:|90-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageNotSentAlert ">//GEN-BEGIN:|97-getter|0|97-preInit
    /**
     * Returns an initiliazed instance of messageNotSentAlert component.
     * @return the initialized component instance
     */
    public Alert getMessageNotSentAlert() {
        if (messageNotSentAlert == null) {//GEN-END:|97-getter|0|97-preInit
            // write pre-init user code here
            messageNotSentAlert = new Alert("", "Message not sent", null, AlertType.ERROR);//GEN-BEGIN:|97-getter|1|97-postInit
            messageNotSentAlert.setTimeout(2000);//GEN-END:|97-getter|1|97-postInit
            // write post-init user code here
        }//GEN-BEGIN:|97-getter|2|
        return messageNotSentAlert;
    }
    //</editor-fold>//GEN-END:|97-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendingMessageImage ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initiliazed instance of sendingMessageImage component.
     * @return the initialized component instance
     */
    public Image getSendingMessageImage() {
        if (sendingMessageImage == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|100-getter|1|100-@java.io.IOException
                sendingMessageImage = Image.createImage("/resources/Sending message.png");
            } catch (java.io.IOException e) {//GEN-END:|100-getter|1|100-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|100-getter|2|100-postInit
            // write post-init user code here
        }//GEN-BEGIN:|100-getter|3|
        return sendingMessageImage;
    }
    //</editor-fold>//GEN-END:|100-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: errorAlertExitCommand ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of errorAlertExitCommand component.
     * @return the initialized component instance
     */
    public Command getErrorAlertExitCommand() {
        if (errorAlertExitCommand == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            errorAlertExitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|102-getter|1|102-postInit
            // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return errorAlertExitCommand;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    class SMSServer implements Runnable {

        public void run() {
            try {
                while (!done) {
                    Message message = smsConnection.receive();
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        senderAddress = textMessage.getAddress().substring(6);

                        conversation = new String[2][1];
                        conversation[0][0] = senderAddress + ":";
                        conversation[1][0] = textMessage.getPayloadText();

                        switchDisplayable(null, getMessageReceivedAlert());
                    } else {
                        throw new Exception("Received is not a text message");
                    }
                }
            } catch (Exception e) {
                db(e);
            }
        }
    }

    public void writeRecord(String string) {
        byte[] record = string.getBytes();
        try {
            rs.addRecord(record, 0, record.length);
        } catch (Exception e) {
            db(e);
        }
    }

    public void readRecords() {
        try {
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            if (re.numRecords() > 0) {
                while (re.hasNextElement()) {
                    String string = new String(re.nextRecord());
                    // Show records in the form 'ConversationsMenu'
                }
            }
            re.destroy();
        } catch (Exception e) {
            db(e);
        }
    }

    private void searchRecordStore(String tfFind) {
        try {
            if (rs.getNumRecords() > 0) {
                SearchFilter searchFilter = new SearchFilter(tfFind);
                RecordEnumeration re = rs.enumerateRecords(searchFilter, null, false);
                if (re.numRecords() > 0) {
                    while (re.hasNextElement()) {
                        // Show match in the form 'ConversationForm'
                    }
                }
                re.destroy();
            }
        } catch (Exception e) {
            db(e);
        }
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        done = true;
        closeConnection();
        closeRecordStore();
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /* Close Connection */
    public void closeConnection() {
        if (smsConnection != null) {
            try {
                smsConnection.setMessageListener(null);
                smsConnection.close();
            } catch (Exception e) {
                db(e);
            }
        }
    }

    private void closeRecordStore() {
        try {
            rs.closeRecordStore();
        } catch (Exception e) {
            db(e);
        }
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    private void db(Exception e) {
        e.printStackTrace();
    }
}
