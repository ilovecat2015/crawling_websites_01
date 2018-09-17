package gui;

import gui.*;
import core.config.RecordConfig;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CaptureAreaSelector_h265 {
    // the local record config reference object
    private RecordConfig recConfig = null;
    public CaptureAreaSelector_h265(RecordConfig rc) {  
        // set the record config reference
        this.recConfig = rc;
        EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
              /*
              
              try {
                  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
              } 
              catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
              {
              }
              */
              
              JFrame frame = new JFrame();
              frame.setUndecorated(true);
              // This works differently under Java 6
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setLayout(new BorderLayout());
              frame.add(new SnipItPane());
              frame.setVisible(true);
              frame.setBackground(new Color(0, 0, 0, 0));
              frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
          }
      });
  }

  public class SnipItPane extends JPanel {
      private Point mouseAnchor;
      private Point dragPoint;
      private SelectionPane selectionPane;

      public SnipItPane() {
          setOpaque(false);
          setLayout(null);
          selectionPane = new SelectionPane();
          add(selectionPane);
          MouseAdapter adapter = new MouseAdapter() {

              @Override
              public void mousePressed(MouseEvent e) {
                  mouseAnchor = e.getPoint();
                  dragPoint = null;
                  selectionPane.setLocation(mouseAnchor);
                  selectionPane.setSize(0, 0);
              }

              @Override
              public void mouseDragged(MouseEvent e) {
                  dragPoint = e.getPoint();
                  int width = dragPoint.x - mouseAnchor.x;
                  int height = dragPoint.y - mouseAnchor.y;
                  int x = mouseAnchor.x;
                  int y = mouseAnchor.y;

                  if (width < 0) {
                      x = dragPoint.x;
                      width *= -1;
                  }
                  if (height < 0) {
                      y = dragPoint.y;
                      height *= -1;
                  }
                  selectionPane.setBounds(x, y, width, height);
                  selectionPane.revalidate();
                  repaint();              
              }               
          };
          addMouseListener(adapter);
          addMouseMotionListener(adapter);
      } 
   
      @Override
      protected void paintComponent(Graphics g) {
      	
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g.create();
          Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
          Area area = new Area(bounds);
          area.subtract(new Area(selectionPane.getBounds()));
          g2d.setColor(new Color(192, 192, 192, 64));
          g2d.fill(area);
      }
  }
  
  public class SelectionPane extends JPanel {
      private JButton button;
      private JLabel label;

      public SelectionPane() {
          button = new JButton("Close");
          setOpaque(false);
          label = new JLabel("Rectangle");
          label.setOpaque(true);
          label.setBorder(new EmptyBorder(4, 4, 4, 4));
          label.setBackground(Color.GRAY);
          label.setForeground(Color.WHITE);
          
          setLayout(new GridBagLayout());
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.gridx = 0;
          gbc.gridy = 0;
          add(label, gbc);
          gbc.gridy++;
          add(button, gbc);
          button.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
                  int a=getX();
                  int b=getY();
                  int c=getWidth();
                  int d=getHeight();
                  
                  //change it to even number
                  if (a%2==0)
                      a=a;
                  else
                      a=a+1;
                  if (b%2==0)
                      b=b;
                  else
                      b=b+1;
                  if (c%2==0)
                      c=c;
                  else
                      c=c+1;
                  if (d%2==0)
                      d=d;
                  else
                      d=d+1;

                  Rectangle bounds1 = new Rectangle(a, b, c, d);
                  // if the full screen flag is set then set the capture area dimension as that of the screen
                  //recConfig.setFrameDimension1(new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize()));
                    // else set the capture area bounds same as the trans panel bounds
                   recConfig.setFrameDimension_h265(bounds1);
                   int x=(int) (recConfig.getFrameDimension_h265().getX()+(recConfig.getFrameDimension_h265().getWidth()/2));
                   int y=(int) (recConfig.getFrameDimension_h265().getY()+(recConfig.getFrameDimension_h265().getHeight()/2));
                   //System.out.println("location x: "+x +" ("+ recConfig.getFrameDimension_filehoot().getMinX()+"-"+recConfig.getFrameDimension_filehoot().getMaxX()+") ");
                   //System.out.println("location y: "+y +" ("+ recConfig.getFrameDimension_filehoot().getMinY()+"-"+recConfig.getFrameDimension_filehoot().getMaxY()+") ");
                   
              }
          }
          );
          addComponentListener(new ComponentAdapter() {
              @Override
              public void componentResized(ComponentEvent e) {
                  label.setText("Rectangle " + getX() + "x" + getY() + "x" + getWidth() + "x" + getHeight());
              }
          }
          );
      }

      @Override
      protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g.create();
          float dash1[] = {20.0f};
          BasicStroke dashed = new BasicStroke(10.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
          g2d.setColor(Color.cyan);
          g2d.setStroke(dashed);
          g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
          g2d.dispose();
      }
  }

  public static Rectangle getVirtualBounds() {
      Rectangle bounds = new Rectangle(0, 0, 0, 0);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice lstGDs[] = ge.getScreenDevices();
      for (GraphicsDevice gd : lstGDs) {
          bounds.add(gd.getDefaultConfiguration().getBounds());
      }
      return bounds;
  }
}