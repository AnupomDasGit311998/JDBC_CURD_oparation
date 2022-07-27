import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employee {
    private JTextField employeeNameTextField;
    private JTextField mobileNumberTextField;
    private JTextField officeLocationTextField;
    private JTextField salaryTextField;
    private JTable table1;
    private JButton saveButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField textSearch;
    private JPanel main;
    private JScrollPane Display;
    private Object DoUtils;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee");
        frame.setContentPane(new Employee().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con = Connector.getCon();


    void table_load(){
        try {
            PreparedStatement ps = con.prepareStatement("select * from employee_details");
            ResultSet rs = ps.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception ex){

            ex.printStackTrace();
            System.out.println("Success...display");
        }
    }


    public Employee() {

        table_load();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Name, OfficeLocation, Mobile, Salary;
                Name = employeeNameTextField.getText();
                Mobile = mobileNumberTextField.getText();
                OfficeLocation = officeLocationTextField.getText();
                Salary = salaryTextField.getText();

                String qry = "insert into employee_details(Name, Mobile, OfficeLocation, Salary) values(?,?,?,?)";
                try {
                    PreparedStatement ps = con.prepareStatement(qry);
                    ps.setString(1,Name);
                    ps.setString(2,Mobile);
                    ps.setString(3,OfficeLocation);
                    ps.setString(4,Salary);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data inserted Sucessfl");
                    table_load();
                    employeeNameTextField.setText("");
                    mobileNumberTextField.setText("");
                    officeLocationTextField.setText("");
                    salaryTextField.setText("");
                    employeeNameTextField.requestFocus();
                }catch (Exception ex){

                    System.out.println("Data not inserted..!!!!");
                }



            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String id, Name, OfficeLocation, Mobile, Salary;

                Name = employeeNameTextField.getText();
                Mobile = mobileNumberTextField.getText();
                OfficeLocation = officeLocationTextField.getText();
                Salary = salaryTextField.getText();
                id = textSearch.getText();

//                String qry = ;
                try {
                    PreparedStatement ps = con.prepareStatement("update employee_details set Name=?, Mobile=?, OfficeLocation=?, Salary=? where id=?");
                    ps.setString(1,Name);
                    ps.setString(2,Mobile);
                    ps.setString(3,OfficeLocation);
                    ps.setString(4,Salary);
                    ps.setString(5,id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Updated Sucessfl");
                    table_load();
                    employeeNameTextField.setText("");
                    mobileNumberTextField.setText("");
                    officeLocationTextField.setText("");
                    salaryTextField.setText("");
                    employeeNameTextField.requestFocus();
                }catch (Exception ex){

                    System.out.println("Data not inserted..!!!!");
                }

            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = textSearch.getText();

                    PreparedStatement ps = con.prepareStatement("select Name, Mobile, OfficeLocation, Salary from employee_details where id=?");
                    ps.setString(1,id);
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()==true){

                        String Name = rs.getString(1);
                        String Mobile = rs.getString(2);
                        String OfficeLocation = rs.getString(3);
                        String Salary = rs.getString(4);

                        employeeNameTextField.setText(Name);
                        mobileNumberTextField.setText(Mobile);
                        officeLocationTextField.setText(OfficeLocation);
                        salaryTextField.setText(Salary);

                    }else{
                        employeeNameTextField.setText("");
                        mobileNumberTextField.setText("");
                        officeLocationTextField.setText("");
                        salaryTextField.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Employ id");
                    }


                }catch (Exception ex){

                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = textSearch.getText();

                try {
                    PreparedStatement ps = con.prepareStatement("delete from employee_details where id=?");
                    ps.setString(1,id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Deleted Successfully...");
                    table_load();
                    employeeNameTextField.setText("");
                    mobileNumberTextField.setText("");
                    officeLocationTextField.setText("");
                    salaryTextField.setText("");
                    employeeNameTextField.requestFocus();
                }catch (Exception ex){

                }

            }
        });
    }
}
