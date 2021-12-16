/**
 * Sample Skeleton for 'infoforprintfx.fxml' Controller Class
 */

package com.example.threefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrintfController implements Initializable {


    @FXML // fx:id="printNow"
    private Button printNow; // Value injected by FXMLLoader

    @FXML // fx:id="sumbtn"
    private Button sumbtn; // Value injected by FXMLLoader

    @FXML // fx:id="temail"
    private TextField temail; // Value injected by FXMLLoader


    @FXML // fx:id="btnclear"
    private Button btnclear; // Value injected by FXMLLoader

    @FXML // fx:id="tprice"
    private TextField tprice; // Value injected by FXMLLoader

    @FXML // fx:id="tquantity"
    private TextField tquantity; // Value injected by FXMLLoader

    @FXML // fx:id="ttotal"
    private TextField ttotal; // Value injected by FXMLLoader

    @FXML // fx:id="tusername"
    private TextField tusername; // Value injected by FXMLLoader



    String billNo="";
    int totalAmount=0;
    int cash=0;
    int balance=0;
    double bheight=0;

    ArrayList<String>username_name=new ArrayList<>();
    ArrayList<String>email_email=new ArrayList<>();
    ArrayList<String>price_price=new ArrayList<>();
    ArrayList<String>quantity_quantity=new ArrayList<>();
    ArrayList<String>total_amount=new ArrayList<>();



    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void totalup(ActionEvent event) {
        username_name.add(tusername.getText());
        email_email.add(temail.getText());
        quantity_quantity.add(tquantity.getText());
        price_price.add(tprice.getText());
        quantity_quantity.add(tquantity.getText());
        price_price.add(tprice.getText());
        totalAmount=(Integer.valueOf(tquantity.getText())*Integer.valueOf(tprice.getText()));
//        totalAmount=totalAmount+Integer.valueOf(ttotal.getText());
        ttotal.setText(total_amount+"");
        clear();

    }

    private void clear() {
        tusername.setText("");
        temail.setText("");
        tquantity.setText("");
        tprice.setText("");
        ttotal.setText("");
    }

    public PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf= pj.defaultPage();
        Paper paper=pf.getPaper();

        double bodyHeight=bheight;
        double headerHeight=5.0;
        double footerHeight=5.0;
        double width=cm_to_pp(8);
        double height=cm_to_pp((int) (headerHeight+bodyHeight+footerHeight));
        paper.setSize(width,height);
        paper.setImageableArea(0,10,width,height-cm_to_pp(1));
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }

    protected double cm_to_pp(double cm) {
        return toPPI(cm*0.399360037);
    }

    private double toPPI(double inch) {
        return inch*72d;
    }

    @FXML
    void update(ActionEvent event) {

    }
    public class BillPrintable implements Printable{

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException  {
            int r=username_name.size();
            ImageIcon icon=new ImageIcon("arkham-knight-background (10).jpg");
            int result=NO_SUCH_PAGE;
            if(pageIndex==0){
                Graphics g2d=(Graphics2D) graphics;
                double width=pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());


                try{
                    int y=20;
                    int yShift=10;
                    int headerRectHeight=15;


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    Component rootPane = null;
                    g2d.drawImage(icon.getImage(), 50,20,90,30,SwingUtilities.getRootPane(rootPane));y+=yShift+30;
                    g2d.drawString("----------------------------------------------",12,y);y+=yShift;
                    g2d.drawString("               GROUP B                        ",12,y);y+=yShift;
                    g2d.drawString("            NO 00000 ADRESS LIST ONE          ",12,y);y+=yShift;
                    g2d.drawString("         ADRESS LINE 11 KISII KENYA           ",12,y);y+=yShift;
                    g2d.drawString("           WWW.KISII.UNIVERSITY.AC.KE         ",12,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",12,y);y+=yShift;

                    g2d.drawString("ITEM NAME                            PRICE     ",10,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;

                    for (int s=0;s<r ;s++) {
                        g2d.drawString(" " + username_name.get(s) + "                    ", 10, y);
                        y += yShift;
                        g2d.drawString("     " + quantity_quantity.get(s) + "  + " + price_price.get(s), 10, y);
                        g2d.drawString(total_amount.get(s), 160, y);
                        y += yShift;
                    }

                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" TOTAL AMOUNT                    "+ttotal.getText()+"  ",10,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" CASH                             "+tprice.getText() +"  ",10,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;
//                    g2d.drawString(" BALANCE                           "+price.getText().toString()+"  ",10,y,10,y);y+=yShift;

                    g2d.drawString("ITEM NAME                            PRICE     ",10,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;

                    g2d.drawString("              THANK YOU                       ",10,y);y+=yShift;
                    g2d.drawString("----------------------------------------------",10,y);y+=yShift;
                    g2d.drawString("            SOFTWARE BY GROUP G               ",10,y);y+=yShift;
                    g2d.drawString("          CONTACT: 070000000                  ",10,y);y+=yShift;

//                    g2d.drawString("         ADRESS LINE 11 KISII KENYA           ",10,y);y+=yShift;

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"invalid operation");


                }
            }
            return 0;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
