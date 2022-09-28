import javax.print.DocFlavor;
import javax.print.MultiDocPrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.printing.PDFPageable;


public class PrinterUtility {


    public static void main(String[] args) {
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();

        List<String> docs = new ArrayList<>();
        File folder = new File("../../Printer Utility/Documents");

        File[] allFiles = folder.listFiles();

        for (File f : allFiles) {
            //try {
                //PDDocument doc = PDDocument.load(f);
                //System.out.println(doc.getPage(0).getMediaBox());
            //} catch (IOException e) {
                //throw new RuntimeException(e);
            //}
            docs.add(f.getName());
        }



        JFrame mainFrame = new JFrame("");
        JPanel panel3 = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel defaultPrinterLabel = new JLabel();
        JButton printButton = new JButton("Drucken");


        JList normalPrintList = new JList(docs.toArray());
        JList plotList = new JList(docs.toArray());

        normalPrintList.setSelectionBackground(Color.green);
        plotList.setSelectionBackground(Color.green);

        //panel3.add(defaultPrinterLabel);
        panel3.add(panel1);
        panel3.add(panel2);

        panel1.add(normalPrintList);
        panel1.add(printButton);
        panel2.add(plotList);


        mainFrame.add(panel3);

        defaultPrinterLabel.setText(defaultPrinter.getName());


        //printButton.setIcon(new ImageIcon("assets/printer_icon.ico"));
        printButton.setSize(90,50);
        //printButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        mainFrame.setLayout(new GridLayout(2,0,4,4));

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(normalPrintList.getSelectedIndex());
                int indexToPrint = normalPrintList.getSelectedIndex();
                switch (indexToPrint) {
                    case 0:
                        try {

                            File fileToPrint = allFiles[indexToPrint];
                            /**
                            PDDocument pdDocument = PDDocument.load(fileToPrint);
                            PrinterJob job = PrinterJob.getPrinterJob();
                            job.setPageable(new PDFPageable(pdDocument));
                            job.setPrintService(defaultPrinter);
                            job.print();
                            **/
                            Desktop.getDesktop().print(fileToPrint);
                            System.out.println(allFiles[indexToPrint]);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                }
            }
        });

        panel1.setBorder(BorderFactory.createTitledBorder("A3 & A4"));
        panel2.setBorder(BorderFactory.createTitledBorder("Grösser als A3"));
        panel3.setBorder(BorderFactory.createTitledBorder(String.format("Standart Drucker: %s", defaultPrinter.getName())));

        mainFrame.setSize(new Dimension(800, 600));
        //mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // this centers the JFrame on the screen
        //mainFrame.pack();
        mainFrame.setVisible(true);


    }
}
