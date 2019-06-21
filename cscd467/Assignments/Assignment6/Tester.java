import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

//import javax.swing.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.*;
import java.io.*;

import java.util.List;

public class Tester extends JFrame {

    private DefaultListModel model = new DefaultListModel();
    public static final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    private int count = 0;
    private JTree tree;
    private JLabel label;
    private JButton download;
    private DefaultTreeModel treeModel;
    private TreePath namesPath;
    private JPanel wrap;
    private TreePath downloadPath = null;

    private static DefaultTreeModel getDefaultTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("All My Buckets");
        DefaultMutableTreeNode parent;
        
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b: buckets)
        {
        	parent = new DefaultMutableTreeNode(b.getName());
        	root.add(parent);
        	ListObjectsV2Result result = s3.listObjectsV2(b.getName());
        	List<S3ObjectSummary> objects = result.getObjectSummaries();
        	for (S3ObjectSummary sum: objects)
        		parent.add(new DefaultMutableTreeNode(sum.getKey()));
        }
        
        return new DefaultTreeModel(root);
    }

    public Tester() {
        super("Drag and Drop File Transfers in Cloud");

        treeModel = getDefaultTreeModel();
        
        tree = new JTree(treeModel);
        tree.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        tree.setDropMode(DropMode.ON);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        namesPath = tree.getPathForRow(2);
        tree.expandRow(2);
        tree.expandRow(1);
        tree.setRowHeight(0);

        //Handles the tree node selection event that triggered by user selection
        //Identify which tree node(file name) has been selected, for downloading.
        //For more info, see TreeSelectionListener Class in Java
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                //DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                   //tree.getLastSelectedPathComponent();

                /* if nothing is selected */ 
                //if (node == null) return;

                /* retrieve the node that was selected */ 
                //Object nodeInfo = node.getUserObject();
                //System.out.println("Node selected is:" + nodeInfo.toString());
                /* React to the node selection. */
                downloadPath = e.getNewLeadSelectionPath();
            }
        });
        
        tree.setTransferHandler(new TransferHandler() {

            public boolean canImport(TransferHandler.TransferSupport info) {
                // we'll only support drops (not clip-board paste)
                if (!info.isDrop()) {
                    return false;
                }
                info.setDropAction(COPY); //Tony added
                info.setShowDropLocation(true);
                // we import Strings and files
                if (!info.isDataFlavorSupported(DataFlavor.stringFlavor) &&
                		!info.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    return false;
                }

                // fetch the drop location
                JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();
                TreePath path = dl.getPath();

                // we don't support invalid paths or descendants of the names folder
                if (path == null || namesPath.isDescendant(path)) {
                    return false;
                }
                return true;
            }

            public boolean importData(TransferHandler.TransferSupport info) {            	
            		// if we can't handle the import, say so
                if (!canImport(info)) {
                    return false;
                }
                // fetch the drop location
                JTree.DropLocation dl = (JTree.DropLocation)info.getDropLocation();
                
                // fetch the path and child index from the drop location
                TreePath path = dl.getPath();
                int childIndex = dl.getChildIndex();

                // fetch the data and bail if this fails
                String uploadName = "";
                
                Transferable t = info.getTransferable();
                try {
                    java.util.List<File> l = (java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File f: l)
                    {
                    	try { s3.putObject(path.getLastPathComponent().toString(), f.getName(), f); }
                    	catch (AmazonServiceException e)
                    	{
                    		System.err.println(e.getErrorMessage());
                    		break;
                    	}
                    	uploadName = f.getName();
                    	String copyName = "./copy-" + f.getName();
                    	File destFile = new File(copyName);
                    	copyFile(f, destFile);
                        break;//We process only one dropped file.
                    }
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                }
                
                // if child index is -1, the drop was on top of the path, so we'll
                // treat it as inserting at the end of that path's list of children
                if (childIndex == -1) {
                    childIndex = tree.getModel().getChildCount(path.getLastPathComponent());
                }

                // create a new node to represent the data and insert it into the model
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(uploadName);
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)path.getLastPathComponent();
                treeModel.insertNodeInto(newNode, parentNode, childIndex);

                // make the new node visible and scroll so that it's visible
                tree.makeVisible(path.pathByAddingChild(newNode));
                tree.scrollRectToVisible(tree.getPathBounds(path.pathByAddingChild(newNode)));
				
                //Display uploading status
                label.setText("UpLoaded **" + uploadName + "** successfully!");

                return true;
            }
            
        });

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        this.wrap = new JPanel();
        this.label = new JLabel("Status Bar...");
        wrap.add(this.label);
        p.add(Box.createHorizontalStrut(4));
        p.add(Box.createGlue());
        p.add(wrap);
        p.add(Box.createGlue());
        p.add(Box.createHorizontalStrut(4));
        getContentPane().add(p, BorderLayout.NORTH);

        getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
        download = new JButton("Download");
        download.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    try
        	    {
        	    	S3Object o = s3.getObject(downloadPath.getParentPath().getLastPathComponent().toString(), downloadPath.getLastPathComponent().toString());
        	    	S3ObjectInputStream s3is = o.getObjectContent();
        	    	File f = new File("./copy-" + downloadPath.getLastPathComponent().toString());
        	    	FileOutputStream fos = new FileOutputStream(f);
        	    	byte[] read_buf = new byte[1024];
        	    	int read_len = 0;
        	    	while ((read_len = s3is.read(read_buf)) > 0)
        	    		fos.write(read_buf, 0, read_len);
        	    	s3is.close();
        	    	fos.close();
        	    	
        	    	System.out.println(f.getAbsolutePath());
        	    	
        	    	label.setText("Downloaded *" + downloadPath.getLastPathComponent().toString() + "* successfully!");
        	    }
        	    catch (AmazonServiceException er)
        	    {
        	    	System.err.println(er.getErrorMessage());
        	    	label.setText("Error downloading *" + downloadPath.getLastPathComponent().toString() + "*:" + er.getErrorMessage());
        	    }
        	    catch (IOException er)
        	    {
        	    	System.err.println(er.getMessage());
        	    	label.setText("Error donwloading *" + downloadPath.getLastPathComponent().toString() + "*:" + er.getMessage());
        	    }
        	    if(downloadPath != null) {
        	    		JOptionPane.showMessageDialog(null, "You like to downloand a file from cloud from buckets:" + 
        	    				downloadPath.toString());
        	    }
        	  } 
        	} );

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        wrap = new JPanel();
        //wrap.add(new JLabel("Show drop location:"));
        wrap.add(download);
        p.add(Box.createHorizontalStrut(4));
        p.add(Box.createGlue());
        p.add(wrap);
        p.add(Box.createGlue());
        p.add(Box.createHorizontalStrut(4));
        getContentPane().add(p, BorderLayout.SOUTH);

        getContentPane().setPreferredSize(new Dimension(400, 450));
    }

    private static void increaseFont(String type) {
        Font font = UIManager.getFont(type);
        font = font.deriveFont(font.getSize() + 4f);
        UIManager.put(type, font);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        Tester test = new Tester();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Display the window.
        test.pack();
        test.setVisible(true);
    }
    
    
    private void copyFile(File source, File dest)
    		throws IOException {
	    	InputStream input = null;
	    	OutputStream output = null;
	    	try {
	    		input = new FileInputStream(source);
	    		output = new FileOutputStream(dest);
	    		byte[] buf = new byte[1024];
	    		int bytesRead;
	    		while ((bytesRead = input.read(buf)) > 0) {
	    			output.write(buf, 0, bytesRead);
	    		}
	    	} finally {
	    		input.close();
	    		output.close();
	    	}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    increaseFont("Tree.font");
                    increaseFont("Label.font");
                    increaseFont("ComboBox.font");
                    increaseFont("List.font");
                } catch (Exception e) {}

                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}