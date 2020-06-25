package PDF;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class pdf {

    public static void Main() {
        String filePath;
        JFileChooser fc = new JFileChooser("c:\\Alcolizer Reader\\");
        fc.setFileFilter(new FileNameExtensionFilter("PDF File", "pdf"));
        fc.setAcceptAllFileFilterUsed(false);
        int nome = fc.showOpenDialog(null);
        if (nome == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();
            filePath = arquivo.getAbsolutePath();
            SwingController controller = new SwingController();

            SwingViewBuilder factory = new SwingViewBuilder(controller);

            JPanel viewerComponentPanel = factory.buildViewerPanel();

            controller.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            controller.getDocumentViewController()));

            JFrame applicationFrame = new JFrame();
            applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            applicationFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            applicationFrame.getContentPane().add(viewerComponentPanel);
            applicationFrame.add(viewerComponentPanel);

            try {
                // controller.openFile();
            } catch (NoSuchMethodError e) {
                System.out.println(e);
            }
            try {
                controller.openDocument(filePath);
            } catch (NoSuchMethodError e) {
                System.out.println(e);
            }

            applicationFrame.pack();
            applicationFrame.setVisible(true);
        }
    }

    public static void Abrir(String filePath) {
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applicationFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);
        try {
            controller.openDocument(filePath);
        } catch (NoSuchMethodError e) {
            JOptionPane.showMessageDialog(null, "application startup error", "", JOptionPane.ERROR);
            System.exit(0);
        }

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

}
