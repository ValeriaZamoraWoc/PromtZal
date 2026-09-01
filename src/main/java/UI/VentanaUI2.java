/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.*;

/**
 *
 * @author cacerola
 */
public class VentanaUI2 extends javax.swing.JFrame {

    private javax.swing.JPanel panelEditor;
    private javax.swing.JPanel panelTokens;
    private javax.swing.JPanel panelErrores;

    private Ventana2 ventana = new Ventana2();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaUI2.class.getName());

    /**
     * Creates new form Ventana2
     */
    public VentanaUI2() {
        initComponents2();
                
        aplicarEstiloSoftCute();
    }


private void aplicarEstiloSoftCute() {

    java.awt.Color bgVentana =
            new java.awt.Color(0x32, 0x2E, 0x2B);

    java.awt.Color bgCampos =
            new java.awt.Color(0x40, 0x3B, 0x37);

    java.awt.Color rosa =
            new java.awt.Color(0xF0, 0xA8, 0xA9);

    java.awt.Color rosaHover =
            new java.awt.Color(0xB8, 0x79, 0x7A);

    java.awt.Color azul =
            new java.awt.Color(0x9A, 0xC4, 0xD7);

    java.awt.Color azulHover =
            new java.awt.Color(0x78, 0x9F, 0xB1);

    java.awt.Color texto =
            new java.awt.Color(0xEE, 0xE4, 0xDB);

    java.awt.Color borde =
            new java.awt.Color(0xD8, 0xC8, 0xBD);

    java.awt.Font titulo =
            new java.awt.Font(
                    "Segoe UI Round",
                    java.awt.Font.BOLD,
                    20
            );

    java.awt.Font textoEditor =
            new java.awt.Font(
                    "Consolas",
                    java.awt.Font.PLAIN,
                    13
            );

    java.awt.Font botonPrincipal =
            new java.awt.Font(
                    "Segoe UI Round",
                    java.awt.Font.BOLD,
                    15
            );

    java.awt.Font botonSecundario =
            new java.awt.Font(
                    "Segoe UI Round",
                    java.awt.Font.BOLD,
                    13
            );

    getContentPane().setBackground(bgVentana);

    jLabel1.setText("✿ Editor de Código");
    jLabel1.setFont(titulo);
    jLabel1.setForeground(rosa);

    jLabel2.setText("★ Tokens Encontrados");
    jLabel2.setFont(titulo);
    jLabel2.setForeground(azul);

    jLabel3.setText("✖ Errores Detectados");
    jLabel3.setFont(titulo);
    jLabel3.setForeground(rosa);

    txtEditor.setFont(textoEditor);
    txtEditor.setBackground(bgCampos);
    txtEditor.setForeground(texto);
    txtEditor.setCaretColor(rosa);
    txtEditor.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    12, 14, 12, 14
            )
    );

    txtTokens.setFont(textoEditor);
    txtTokens.setBackground(bgCampos);
    txtTokens.setForeground(texto);
    txtTokens.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    12, 14, 12, 14
            )
    );

    txtErrores.setFont(textoEditor);
    txtErrores.setBackground(bgCampos);
    txtErrores.setForeground(texto);
    txtErrores.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    12, 14, 12, 14
            )
    );

    jScrollPane1.setBackground(bgCampos);
    jScrollPane1.getViewport().setBackground(bgCampos);

    jScrollPane2.setBackground(bgCampos);
    jScrollPane2.getViewport().setBackground(bgCampos);

    jScrollPane3.setBackground(bgCampos);
    jScrollPane3.getViewport().setBackground(bgCampos);

    jScrollPane1.setBorder(
            new BordeRedondeado(borde, 24, 2)
    );

    jScrollPane2.setBorder(
            new BordeRedondeado(azul, 24, 2)
    );

    jScrollPane3.setBorder(
            new BordeRedondeado(rosa, 24, 2)
    );

    btnAnalizar.setFont(botonPrincipal);
    btnAnalizar.setText("✨ Analizar Texto ✨");
    btnAnalizar.setBackground(rosa);
    btnAnalizar.setForeground(bgVentana);
    btnAnalizar.setFocusPainted(false);
    btnAnalizar.setBorderPainted(false);
    btnAnalizar.setCursor(
            new java.awt.Cursor(
                    java.awt.Cursor.HAND_CURSOR
            )
    );

    btnAnalizar.putClientProperty(
            "Component.arc",
            40
    );

    btnAnalizar.putClientProperty(
            "Button.hoverBackground",
            rosaHover
    );

    btnAnalizar.putClientProperty(
            "Button.borderWidth",
            0
    );

    btnExportarTokens.setFont(botonSecundario);
    btnExportarTokens.setText(
            "🌐 Exportar Tokens a HTML"
    );
    btnExportarTokens.setBackground(azul);
    btnExportarTokens.setForeground(bgVentana);
    btnExportarTokens.setFocusPainted(false);
    btnExportarTokens.setBorderPainted(false);
    btnExportarTokens.setCursor(
            new java.awt.Cursor(
                    java.awt.Cursor.HAND_CURSOR
            )
    );

    btnExportarTokens.putClientProperty(
            "Component.arc",
            40
    );

    btnExportarTokens.putClientProperty(
            "Button.hoverBackground",
            azulHover
    );

    btnExportarTokens.putClientProperty(
            "Button.borderWidth",
            0
    );

    btnExportarErrores.setFont(botonSecundario);
    btnExportarErrores.setText(
            "🌐 Exportar Errores a HTML"
    );
    btnExportarErrores.setBackground(rosa);
    btnExportarErrores.setForeground(bgVentana);
    btnExportarErrores.setFocusPainted(false);
    btnExportarErrores.setBorderPainted(false);
    btnExportarErrores.setCursor(
            new java.awt.Cursor(
                    java.awt.Cursor.HAND_CURSOR
            )
    );

    btnExportarErrores.putClientProperty(
            "Component.arc",
            40
    );

    btnExportarErrores.putClientProperty(
            "Button.hoverBackground",
            rosaHover
    );

    btnExportarErrores.putClientProperty(
            "Button.borderWidth",
            0
    );

    jMenuBar1.setBackground(bgVentana);
    jMenuBar1.setBorder(
            javax.swing.BorderFactory.createMatteBorder(
                    0, 0, 1, 0, borde
            )
    );

    jMenu1.setText("📂 Archivo");
    jMenu1.setForeground(texto);

    jMenu2.setText("✏️ Editar");
    jMenu2.setForeground(texto);

    jMenuItem1.setText("📄 Abrir");
    jMenuItem2.setText("💾 Guardar");
}

    private void initComponents2() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtEditor = new javax.swing.JTextArea();

        jScrollPane2 = new javax.swing.JScrollPane();
        txtTokens = new javax.swing.JTextArea();

        jScrollPane3 = new javax.swing.JScrollPane();
        txtErrores = new javax.swing.JTextArea();

        btnAnalizar = new javax.swing.JButton();
        btnExportarTokens = new javax.swing.JButton();
        btnExportarErrores = new javax.swing.JButton();

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        panelEditor = new PanelCute(
                    new java.awt.Color(0x3A, 0x34, 0x31),
                    new java.awt.Color(0xD8, 0xC8, 0xBD)
            );

            panelTokens = new PanelCute(
                    new java.awt.Color(0x3A, 0x34, 0x31),
                    new java.awt.Color(0xB7, 0xD3, 0xDF)
            );

            panelErrores = new PanelCute(
                    new java.awt.Color(0x3A, 0x34, 0x31),
                    new java.awt.Color(0xE7, 0xB0, 0xB2)
            );

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();


        // =========================================================
        // CONFIGURACIÓN DE LA VENTANA
        // =========================================================

        setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE
        );


        // =========================================================
        // EDITOR
        // =========================================================

        txtEditor.setColumns(20);
        txtEditor.setRows(5);

        jScrollPane1.setViewportView(txtEditor);


        // =========================================================
        // TOKENS
        // =========================================================

        txtTokens.setEditable(false);
        txtTokens.setColumns(20);
        txtTokens.setRows(5);

        jScrollPane2.setViewportView(txtTokens);


        // =========================================================
        // ERRORES
        // =========================================================

        txtErrores.setEditable(false);
        txtErrores.setColumns(20);
        txtErrores.setRows(5);

        jScrollPane3.setViewportView(txtErrores);

    // =========================================================
    // PANEL EDITOR
    // =========================================================

    javax.swing.GroupLayout editorLayout =
            new javax.swing.GroupLayout(panelEditor);

    panelEditor.setLayout(editorLayout);

    editorLayout.setHorizontalGroup(
        editorLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING
        )
        .addComponent(
            jLabel1,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
        .addComponent(
            jScrollPane1,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
    );

    editorLayout.setVerticalGroup(
        editorLayout.createSequentialGroup()

        .addComponent(
            jLabel1,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            35,
            javax.swing.GroupLayout.PREFERRED_SIZE
        )

        .addGap(10)

        .addComponent(
            jScrollPane1,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            450,
            Short.MAX_VALUE
        )
    );
    // =========================================================
    // PANEL TOKENS
    // =========================================================

    javax.swing.GroupLayout tokensLayout =
            new javax.swing.GroupLayout(panelTokens);

    panelTokens.setLayout(tokensLayout);

    tokensLayout.setHorizontalGroup(
        tokensLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING
        )
        .addComponent(
            jLabel2,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
        .addComponent(
            jScrollPane2,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
        .addComponent(
            btnExportarTokens,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
    );

    tokensLayout.setVerticalGroup(
        tokensLayout.createSequentialGroup()

        .addComponent(
            jLabel2,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            30,
            javax.swing.GroupLayout.PREFERRED_SIZE
        )

        .addGap(8)

        .addComponent(
            jScrollPane2,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            170,
            Short.MAX_VALUE
        )

        .addGap(10)

        .addComponent(
            btnExportarTokens,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            42,
            javax.swing.GroupLayout.PREFERRED_SIZE
        )
    );
    // =========================================================
    // PANEL ERRORES
    // =========================================================

    javax.swing.GroupLayout erroresLayout =
            new javax.swing.GroupLayout(panelErrores);

    panelErrores.setLayout(erroresLayout);

    erroresLayout.setHorizontalGroup(
        erroresLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING
        )
        .addComponent(
            jLabel3,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
        .addComponent(
            jScrollPane3,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
        .addComponent(
            btnExportarErrores,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE
        )
    );

    erroresLayout.setVerticalGroup(
        erroresLayout.createSequentialGroup()

        .addComponent(
            jLabel3,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            30,
            javax.swing.GroupLayout.PREFERRED_SIZE
        )

        .addGap(8)

        .addComponent(
            jScrollPane3,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            170,
            Short.MAX_VALUE
        )

        .addGap(10)

        .addComponent(
            btnExportarErrores,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            42,
            javax.swing.GroupLayout.PREFERRED_SIZE
        )
    );
        // =========================================================
        // BOTONES
        // =========================================================

        btnAnalizar.setText(
                "Analizar Texto"
        );
        btnAnalizar.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(
                        java.awt.event.ActionEvent evt) {

                    jMenuItem1ActionPerformed(evt);
                }
            }
        );

        btnExportarTokens.setText(
                "Exportar a HTML"
        );
        btnExportarTokens.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(
                        java.awt.event.ActionEvent evt) {

                    jMenuItem1ActionPerformed(evt);
                }
            }
        );

        btnExportarErrores.setText(
                "Exportar a HTML"
        );
        btnExportarErrores.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(
                        java.awt.event.ActionEvent evt) {

                    jMenuItem1ActionPerformed(evt);
                }
            }
        );

        // =========================================================
        // TÍTULOS
        // =========================================================

        jLabel1.setText(
                "Editor de Código"
        );

        jLabel2.setText(
                "Tokens:"
        );

        jLabel3.setText(
                "Errores:"
        );


        // =========================================================
        // MENÚ ARCHIVO
        // =========================================================

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Abrir");
        jMenu1.add(jMenuItem1);
        
        jMenuItem1.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(
                        java.awt.event.ActionEvent evt) {

                    jMenuItem1ActionPerformed(evt);
                }
            }
        );

        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenu1.add(jMenuItem2);
        
        jMenuItem2.addActionListener(
            new java.awt.event.ActionListener() {
                public void actionPerformed(
                        java.awt.event.ActionEvent evt) {

                    jMenuItem1ActionPerformed(evt);
                }
            }
        );

        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);


        // =========================================================
        // MENÚ EDITAR
        // =========================================================

        jMenu2.setText("Editar");

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);


        // =========================================================
        // LAYOUT
        // =========================================================

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(
                        getContentPane()
                );

        getContentPane().setLayout(layout);


        // =========================================================
        // DISTRIBUCIÓN HORIZONTAL
        // =========================================================

        layout.setHorizontalGroup(

            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING
            )

            .addGroup(

                layout.createSequentialGroup()

                    .addGap(20, 20, 20)

                    // ---------------------------------------------
                    // COLUMNA DEL EDITOR
                    // ---------------------------------------------

                    .addGroup(

                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )

                        .addComponent(
                                jLabel1
                        )

                        .addComponent(
                                jScrollPane1,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                550,
                                Short.MAX_VALUE
                        )

                        .addComponent(
                                btnAnalizar,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                        )
                    )


                    .addGap(22, 22, 22)


                    // ---------------------------------------------
                    // COLUMNA DE RESULTADOS
                    // ---------------------------------------------

                    .addGroup(

                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )

                        .addComponent(
                                jLabel2
                        )

                        .addComponent(
                                jScrollPane2,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                380,
                                Short.MAX_VALUE
                        )

                        .addComponent(
                                btnExportarTokens,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                        )

                        .addComponent(
                                jLabel3
                        )

                        .addComponent(
                                jScrollPane3,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                380,
                                Short.MAX_VALUE
                        )

                        .addComponent(
                                btnExportarErrores,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                        )
                    )


                    .addGap(20, 20, 20)
            )
        );


        // =========================================================
        // DISTRIBUCIÓN VERTICAL
        // =========================================================

        layout.setVerticalGroup(

            layout.createParallelGroup(
                    javax.swing.GroupLayout.Alignment.LEADING
            )

            .addGroup(

                layout.createSequentialGroup()

                    .addGap(20, 20, 20)


                    // ---------------------------------------------
                    // TÍTULOS
                    // ---------------------------------------------

                    .addComponent(
                            jLabel1,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            32,
                            javax.swing.GroupLayout.PREFERRED_SIZE
                    )

                    .addPreferredGap(
                            javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                    )


                    // ---------------------------------------------
                    // CONTENIDO
                    // ---------------------------------------------

                    .addGroup(

                        layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING
                        )

                        // =========================================
                        // EDITOR
                        // =========================================

                        .addGroup(

                            layout.createSequentialGroup()

                                .addComponent(
                                        jScrollPane1,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        470,
                                        Short.MAX_VALUE
                                )

                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                                )

                                .addComponent(
                                        btnAnalizar,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        48,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                        )


                        // =========================================
                        // RESULTADOS
                        // =========================================

                        .addGroup(

                            layout.createSequentialGroup()

                                .addComponent(
                                        jLabel2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        25,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )

                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                )

                                .addComponent(
                                        jScrollPane2,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        175,
                                        Short.MAX_VALUE
                                )

                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                                )

                                .addComponent(
                                        btnExportarTokens,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )

                                .addGap(
                                        18,
                                        18,
                                        18
                                )

                                .addComponent(
                                        jLabel3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        25,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )

                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED
                                )

                                .addComponent(
                                        jScrollPane3,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        175,
                                        Short.MAX_VALUE
                                )

                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
                                )

                                .addComponent(
                                        btnExportarErrores,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                        )
                    )


                    .addGap(20, 20, 20)
            )
        );


        // =========================================================
        // FINALIZAR
        // =========================================================

        pack();

        aplicarEstiloSoftCute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtEditor = new javax.swing.JTextArea();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTokens = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtErrores = new javax.swing.JTextArea();
        btnAnalizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnExportarTokens = new javax.swing.JButton();
        btnExportarErrores = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEditor.setColumns(20);
        txtEditor.setRows(5);
        jScrollPane1.setViewportView(txtEditor);

        txtTokens.setEditable(false);
        txtTokens.setColumns(20);
        txtTokens.setRows(5);
        jScrollPane2.setViewportView(txtTokens);

        txtErrores.setEditable(false);
        txtErrores.setColumns(20);
        txtErrores.setRows(5);
        jScrollPane3.setViewportView(txtErrores);

        btnAnalizar.setText("Analizar Texto");

        jLabel1.setText("jLabel1");

        jLabel2.setText("Tokens:");

        jLabel3.setText("Errores: ");

        btnExportarTokens.setText("jButton1");

        btnExportarErrores.setText("jButton2");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                        .addComponent(jScrollPane3))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btnExportarTokens)
                    .addComponent(btnExportarErrores))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportarTokens)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportarErrores))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //abrir archivo
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ventana.procesarArchivo();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ventana.procesarArchivo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaUI2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnExportarErrores;
    private javax.swing.JButton btnExportarTokens;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtEditor;
    private javax.swing.JTextArea txtErrores;
    private javax.swing.JTextArea txtTokens;
    // End of variables declaration//GEN-END:variables


private static class BordeRedondeado extends javax.swing.border.AbstractBorder {

    private final java.awt.Color color;
    private final int radio;
    private final int grosor;

    public BordeRedondeado(java.awt.Color color, int radio, int grosor) {
        this.color = color;
        this.radio = radio;
        this.grosor = grosor;
    }

    @Override
    public void paintBorder(
            java.awt.Component c,
            java.awt.Graphics g,
            int x,
            int y,
            int width,
            int height) {

        java.awt.Graphics2D g2 =
                (java.awt.Graphics2D) g.create();

        g2.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(color);

        g2.setStroke(
                new java.awt.BasicStroke(grosor)
        );

        g2.drawRoundRect(
                x + grosor / 2,
                y + grosor / 2,
                width - grosor,
                height - grosor,
                radio,
                radio
        );

        g2.dispose();
    }

    @Override
    public java.awt.Insets getBorderInsets(
            java.awt.Component c) {

        return new java.awt.Insets(
                8,
                8,
                8,
                8
        );
    }
}

private static class PanelCute extends javax.swing.JPanel {

    private final java.awt.Color fondo;
    private final java.awt.Color borde;

    public PanelCute(
            java.awt.Color fondo,
            java.awt.Color borde) {

        this.fondo = fondo;
        this.borde = borde;

        setOpaque(false);

        setBorder(
                javax.swing.BorderFactory.createCompoundBorder(
                        new BordeRedondeado(
                                borde,
                                28,
                                2
                        ),
                        javax.swing.BorderFactory.createEmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );
    }

    @Override
    protected void paintComponent(
            java.awt.Graphics g) {

        java.awt.Graphics2D g2 =
                (java.awt.Graphics2D) g.create();

        g2.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Fondo redondeado
        g2.setColor(fondo);

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                28,
                28
        );

        g2.dispose();

        super.paintComponent(g);
    }
}

}
