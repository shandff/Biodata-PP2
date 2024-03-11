package StudiKasus;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel7 extends AbstractTableModel {

    
	private static final long serialVersionUID = 1L;

	// Array untuk menyimpan nama-nama kolom pada tabel
    private String[] columnNames = {"Nama", "Jenis Kelamin", "Nomor HP", "WNA"};

    // ArrayList untuk menyimpan data pada tabel
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    // Mendefinisikan jumlah kolom dalam tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mendefinisikan jumlah baris dalam tabel
    public int getRowCount() {
        return data.size();
    }

    // Mendapatkan nama kolom pada indeks tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mendapatkan nilai pada sel yang ditentukan oleh baris dan kolom
    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }

    // Mendefinisikan apakah sel dalam tabel dapat diedit
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Menambahkan baris data ke dalam tabel
    public void add(ArrayList<String> value) {
        data.add(value);
        // Memberi tahu tabel bahwa baris telah ditambahkan
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Menghapus baris pada indeks tertentu
    public void remove(int rowIndex) {
        data.remove(rowIndex);
        // Memberi tahu tabel bahwa baris telah dihapus
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    // Mengambil data pada baris tertentu
    public ArrayList<String> getDataAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }

    // Memperbarui data pada baris tertentu
    public void update(int rowIndex, ArrayList<String> updatedData) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.set(rowIndex, updatedData);
            // Memberi tahu tabel bahwa baris telah diperbarui
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    // Menghapus semua data dalam model tabel
    public void clearData() {
        int size = data.size();
        data.clear();
        // Memberi tahu tabel bahwa semua baris telah dihapus
        fireTableRowsDeleted(0, size - 1);
    }
}
