package StudiKasus; // Ini adalah deklarasi paket (package) untuk proyek Java. Paket digunakan untuk mengelompokkan kelas-kelas terkait dalam satu direktori.

import java.awt.event.*; // Mengimpor kelas dan antarmuka yang diperlukan dari paket java.awt.event.
import javax.swing.*; // Mengimpor kelas dan antarmuka yang diperlukan dari paket javax.swing.
import javax.swing.table.*; // Mengimpor kelas yang diperlukan untuk bekerja dengan tabel dalam GUI Swing.
import java.util.*; // Mengimpor kelas utilitas yang diperlukan, seperti ArrayList.
import java.io.FileWriter; // Mengimpor kelas FileWriter dari paket java.io untuk menulis ke file.
import java.io.File; // Mengimpor kelas File dari paket java.io untuk mengelola file.
import java.io.IOException; // Mengimpor kelas IOException dari paket java.io untuk menangani kesalahan IO.
import javax.swing.JOptionPane; // Mengimpor kelas JOptionPane dari paket javax.swing untuk menampilkan pesan dialog.



@SuppressWarnings("unused")

public class BiodataApp extends JFrame {

	
	private static final long serialVersionUID = 1L;



	private boolean isNumeric(String str) {
	    return str.matches("\\d+");
	}

    public BiodataApp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelForm = new JLabel("Form Biodata");
        labelForm.setBounds(150, 10, 100, 20);
        this.add(labelForm);

        JLabel labelInput = new JLabel("Nama : ");
        labelInput.setBounds(15, 20, 100, 20);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 40, 200, 20);

        JLabel labelTelepon = new JLabel("Nomor HP : ");
        labelTelepon.setBounds(15, 60, 100, 20);

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(15, 80, 200, 20);

        JLabel labelRadio = new JLabel("Alamat : ");
        labelRadio.setBounds(15, 100, 300, 20);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 120, 100, 20);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 140, 100, 20);

        JLabel labelAlamat = new JLabel("Alamat : ");
        labelAlamat.setBounds(15, 160, 100, 20);

        JTextField textFieldAlamat = new JTextField();
        textFieldAlamat.setBounds(10, 180, 370, 70);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JButton button = new JButton("Simpan");
        button.setBounds(10, 250, 100, 40);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(100, 250, 80, 40);

        JButton deleteButton = new JButton("Hapus");
        deleteButton.setBounds(180, 250, 80, 40);

        this.add(editButton);
        this.add(deleteButton);

        JButton saveToFileButton = new JButton("Simpan ke File");
        saveToFileButton.setBounds(260, 250, 120, 40);
        this.add(saveToFileButton);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(10, 300, 370, 150);

        MyTableModel7 tableModel7 = new MyTableModel7();
        table.setModel(tableModel7);

               
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Jeniskelamin = "";
                if (radioButton1.isSelected()) {
                    Jeniskelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    Jeniskelamin = radioButton2.getText();
                }

                String nama = textFieldNama.getText();
                String nomorHp = textFieldTelepon.getText();
                String alamat = textFieldAlamat.getText();

                if (nama.isEmpty() || nomorHp.isEmpty() || alamat.isEmpty() || Jeniskelamin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua kolom.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(nomorHp)) {
                    JOptionPane.showMessageDialog(null, "Nomor Telepon harus berisi angka.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    ArrayList<String> rowData = new ArrayList<>();
                    rowData.add(nama);
                    rowData.add(Jeniskelamin);
                    rowData.add(nomorHp);
                    rowData.add(alamat);

                    tableModel7.add(rowData);

                    textFieldNama.setText("");
                    textFieldTelepon.setText("");
                    textFieldAlamat.setText(alamat);

                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });




        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String nama = (String) tableModel7.getValueAt(selectedRow, 0);
                    String jenisKelamin = (String) tableModel7.getValueAt(selectedRow, 1);
                    String nomorHp = (String) tableModel7.getValueAt(selectedRow, 2);
                    String alamat = (String) tableModel7.getValueAt(selectedRow, 3);

                    textFieldNama.setText(nama);
                    if (jenisKelamin.equals("Laki-Laki")) {
                        radioButton1.setSelected(true);
                    } else {
                        radioButton2.setSelected(true);
                    }
                    textFieldTelepon.setText(nomorHp);
                    textFieldAlamat.setText(alamat);

                    // Hapus data setelah memastikan data telah disalin
                    tableModel7.remove(selectedRow);
                    
                    // Notifikasi setelah data berhasil disalin
                    JOptionPane.showMessageDialog(null, "Data berhasil disalin ke formulir. Klik Simpan jika sudah selesai mengedit.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Notifikasi jika tidak ada data yang dipilih
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin Anda edit terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel7.remove(selectedRow); // Notifikasi setelah data dihapus
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Notifikasi jika tidak ada data yang dipilih
                    JOptionPane.showMessageDialog(null, "Pilih data yang ingin Anda hapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String filePath = "C:\\Users\\shila\\eclipse-workspace\\pp2\\src\\StudiKasus\\file.txt"; // Lokasi file yang ditentukan
                    
                    File file = new File(filePath);

                    // Buat FileWriter untuk menulis data ke file
                    FileWriter writer = new FileWriter(file);

                    // Tulis data dari tabel ke file
                    for (int i = 0; i < tableModel7.getRowCount(); i++) {
                        for (int j = 0; j < tableModel7.getColumnCount(); j++) {
                            writer.write(tableModel7.getValueAt(i, j).toString());
                            writer.write("\t"); // Tab sebagai pemisah data
                        }
                        writer.write("\n"); // Baris baru untuk setiap baris data
                    }

                    // Tutup FileWriter
                    writer.close();

                    // Notifikasi setelah data disimpan ke file
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file", "Info", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    // Tangani kesalahan jika ada masalah dengan penulisan ke file
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan ke file.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Keluar dari aplikasi jika pengguna memilih "Ya"
                }
                // Jangan melakukan apa pun jika pengguna memilih "Tidak"
            }
        });

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Tidak melakukan apa pun saat tombol close window ditekan

        
        

        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textFieldAlamat);
        this.add(button);
        this.add(scrollableTable);
        this.setSize(400, 500);
        this.setLayout(null);
    }
    
    

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	BiodataApp h = new BiodataApp();
                h.setVisible(true);
            }
        });
    }
}
