import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;


public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Graph searching");
        frame.setPreferredSize(new Dimension(540, 1000));

        JButton btnWczytaj = new JButton("Wczytaj");
        btnWczytaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser txtInput = new JFileChooser();
                txtInput.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
                if (txtInput.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = txtInput.getSelectedFile().toString();
                    GraphProduction gp;
                    try {
                        gp = new GraphProduction(selectedFile);
                        DirectedSparseMultigraph g = gp.getGraph();
                        MyNode[] n = gp.getNodes();
                        MyNode start = gp.getStart(), end = gp.getGoal();


                        // VISUALISATION GENERATE
                        Layout<MyNode, MyEdge> layout = new KKLayout(g);
                        layout.setSize(new Dimension(300,300));
                        VisualizationViewer<MyNode, MyEdge> vv = new VisualizationViewer<>(layout);
                        vv.setPreferredSize(new Dimension(500,500));

                        Transformer<MyNode,Paint> nodePaint = i -> {
                            if (i.equals(start)) return Color.BLUE;
                            else {
                                if (i.equals(end)) return Color.RED;
                                else return Color.GRAY;
                            }
                        };

                        PluggableGraphMouse gm = new PluggableGraphMouse();
                        gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON1_MASK));
                        gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));
                       vv.setGraphMouse(gm);
                        vv.getRenderContext().setVertexFillPaintTransformer(nodePaint);
                        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
                        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
                        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

                        // DIJKSTRY ALGORITHM EXECUTE
                        Transformer<MyEdge, Double> transformer = e -> e.getWeight();
                        DijkstraShortestPath<MyNode, MyEdge> alg = new DijkstraShortestPath( g , transformer);
                        Number distance = alg.getDistance(start, end);
                        List<MyEdge> list = alg.getPath(start, end);

                        // A* ALGORITHM
                        Astar astar = new Astar(gp, start, end);
                        double astarDist = astar.getCost();

                        JTextArea text = new JTextArea();

                        //text.setWrapStyleWord(true);
                        text.setLineWrap(true);
                        text.setEditable(false);
                        text.setPreferredSize(new Dimension(500, 400));
                        JScrollPane scrollPane = new JScrollPane(text);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        FlowLayout l = new FlowLayout();
                        frame.getContentPane().setLayout(l);
                        frame.getContentPane().add(vv);
                        frame.getContentPane().add(scrollPane);
                        text.append("Shortest path from node " +
                                start.toString()+ " to node "+ end.toString() + "\n\nUsing Dijkstry algorithm : \n" + list.toString() +
                                "\nTotal cost: "+ distance + "\n\nUsing A* algorithm : \n"+ astar.getPath().toString()+
                                "\nTotal cost: " + astarDist + "\n\n"+g.toString());


                    } catch (FileNotFoundException fnfe) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Wrong file");
                    }
                    catch (InputMismatchException ime) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Wrong data");
                    }
                    catch (NoSuchElementException nsee) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Wrong data");
                    }
                    catch (IllegalArgumentException iae) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Wrong data");
                    }
                    catch (AstarException ae) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Path doesn't exist.");
                    }

                    catch(Exception e )
                    {
                        JOptionPane.showMessageDialog(null,
                                "Error");
                    }
                    frame.getContentPane().validate();
                    frame.getContentPane().repaint();
                }
            }
        });
        btnWczytaj.setBounds(466, 36, 89, 23);
        frame.getContentPane().add(btnWczytaj);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}