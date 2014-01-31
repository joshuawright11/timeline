/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 * @author Andrew
 */
public class EditWindow extends javax.swing.JFrame {

    /**
     * Creates new form Window2
     */
    public EditWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        toolbar = new javax.swing.JPanel();
        toolbarLabel = new javax.swing.JLabel();
        toolbarSeparator1 = new javax.swing.JSeparator();
        eventsEditLabel = new javax.swing.JLabel();
        addEventButton = new javax.swing.JButton();
        deleteEventButton = new javax.swing.JButton();
        editEventButton = new javax.swing.JButton();
        toolbarSeparator2 = new javax.swing.JSeparator();
        timelinesEditLabel = new javax.swing.JLabel();
        timelinesPane = new javax.swing.JScrollPane();
        timelines = new javax.swing.JList();
        addTimelineButton = new javax.swing.JButton();
        deleteTimelineButton = new javax.swing.JButton();
        editTimelineButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newTimelineMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveTimelineMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        editMenuSeparator1 = new javax.swing.JPopupMenu.Separator();
        deleteMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        editViewMenuItem = new javax.swing.JMenuItem();
        previewMenuItem = new javax.swing.JMenuItem();
        insertMenu = new javax.swing.JMenu();
        newEventMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Timelord - Create, edit, and view timelines!");

        mainSplitPane.setDividerLocation(120);

        toolbarLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        toolbarLabel.setText("Toolbar");

        eventsEditLabel.setText("Events");

        addEventButton.setText("Add Event");

        deleteEventButton.setText("Delete Event");
        deleteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventButtonActionPerformed(evt);
            }
        });

        editEventButton.setText("Edit Event");
        editEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEventButtonActionPerformed(evt);
            }
        });

        timelinesEditLabel.setText("Timelines");

        timelinesPane.setViewportView(timelines);

        addTimelineButton.setText("Add Timeline");

        deleteTimelineButton.setText("Delete Timeline");

        editTimelineButton.setText("Edit Timeline");
        editTimelineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTimelineButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toolbarLayout = new javax.swing.GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
            toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolbarSeparator1)
            .addComponent(eventsEditLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(addEventButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deleteEventButton, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addComponent(editEventButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolbarSeparator2)
            .addComponent(timelinesEditLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(toolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timelinesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(editTimelineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(addTimelineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deleteTimelineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        toolbarLayout.setVerticalGroup(
            toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarLayout.createSequentialGroup()
                .addComponent(toolbarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbarSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eventsEditLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addEventButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteEventButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editEventButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolbarSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timelinesEditLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timelinesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTimelineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteTimelineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editTimelineButton))
        );

        mainSplitPane.setLeftComponent(toolbar);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainSplitPane.setRightComponent(jScrollPane1);

        fileMenu.setText("File");

        newTimelineMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newTimelineMenuItem.setText("New");
        newTimelineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTimelineMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newTimelineMenuItem);
        fileMenu.add(fileMenuSeparator1);

        saveTimelineMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveTimelineMenuItem.setText("Save");
        saveTimelineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTimelineMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveTimelineMenuItem);
        fileMenu.add(fileMenuSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setText("Undo");
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setText("Redo");
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(redoMenuItem);
        editMenu.add(editMenuSeparator1);

        deleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        viewMenu.setText("View");

        editViewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        editViewMenuItem.setText("Edit View");
        viewMenu.add(editViewMenuItem);

        previewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        previewMenuItem.setText("Preview");
        viewMenu.add(previewMenuItem);

        menuBar.add(viewMenu);

        insertMenu.setText("Insert");

        newEventMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        newEventMenuItem.setText("New Event");
        newEventMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEventMenuItemActionPerformed(evt);
            }
        });
        insertMenu.add(newEventMenuItem);

        menuBar.add(insertMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newTimelineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTimelineMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newTimelineMenuItemActionPerformed

    private void saveTimelineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTimelineMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveTimelineMenuItemActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void newEventMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newEventMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newEventMenuItemActionPerformed

    private void deleteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEventButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteEventButtonActionPerformed

    private void editEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEventButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editEventButtonActionPerformed

    private void editTimelineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTimelineButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editTimelineButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEventButton;
    private javax.swing.JButton addTimelineButton;
    private javax.swing.JButton deleteEventButton;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JButton deleteTimelineButton;
    private javax.swing.JButton editEventButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JPopupMenu.Separator editMenuSeparator1;
    private javax.swing.JButton editTimelineButton;
    private javax.swing.JMenuItem editViewMenuItem;
    private javax.swing.JLabel eventsEditLabel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator1;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator2;
    private javax.swing.JMenu insertMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newEventMenuItem;
    private javax.swing.JMenuItem newTimelineMenuItem;
    private javax.swing.JMenuItem previewMenuItem;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JMenuItem saveTimelineMenuItem;
    private javax.swing.JList timelines;
    private javax.swing.JLabel timelinesEditLabel;
    private javax.swing.JScrollPane timelinesPane;
    private javax.swing.JPanel toolbar;
    private javax.swing.JLabel toolbarLabel;
    private javax.swing.JSeparator toolbarSeparator1;
    private javax.swing.JSeparator toolbarSeparator2;
    private javax.swing.JMenuItem undoMenuItem;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
