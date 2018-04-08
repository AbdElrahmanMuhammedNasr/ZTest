package ztest;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField EmpFirstName;

    @FXML
    private TextField EmpLastName;

    @FXML
    private DatePicker EmpDate;

    @FXML
    private RadioButton m;

    @FXML
    private RadioButton f;

    @FXML
    ComboBox<?> Con;

    @FXML
    private TextField Empemail;

    @FXML
    private PasswordField Emppassword;

    ToggleGroup radioGroup;

    public void SaveData() {

        Session s = NewHibernateUtil.getSessionFactory().openSession();

        try {
            String ff = EmpFirstName.getText();
            String l = EmpLastName.getText();
            LocalDate da1 = EmpDate.getValue();
            String da = String.valueOf(da1);

            /* هاااااااااااااااام*/
            RadioButton g = (RadioButton) radioGroup.getSelectedToggle();
            String gg = g.getText();
            /*.......................................*/
            String r = Con.getSelectionModel().getSelectedItem().toString();
            String e = Empemail.getText();
            String p = Emppassword.getText();

            System.out.println(f + l + da + gg + r + e + p);

            DB d = new DB();

            s.beginTransaction();
            d.setFname(ff);
            d.setLname(l);
            d.setDate(da);
            d.setGender(gg);
            d.setRole(r);
            d.setEmail(e);
            d.setPossword(p);
            s.save(d);

            s.getTransaction().commit();

            /* Select again*/
            getDateToTable();

            EmpFirstName.setText("");
            EmpLastName.setText("");

            m.setSelected(false);
            f.setSelected(false);

            Con.setPromptText("Select Role");
            Empemail.setText("");
            Emppassword.setText("");

        } catch (Exception f) {
            JOptionPane.showMessageDialog(null,"ERROR");
                
        } finally {
            s.close();
        }

    }

    @FXML
    public TableView<DB> t;

    @FXML
    public TableColumn<DB, Integer> tid;

    @FXML
    public TableColumn<DB, String> tfname;

    @FXML
    public TableColumn<DB, String> tlname;

    @FXML
    public TableColumn<DB, String> tdate;

    @FXML
    public TableColumn<DB, String> tgender;

    @FXML
    public TableColumn<DB, String> trole;

    @FXML
    public TableColumn<DB, String> temail;

    @FXML
    public void GetDate(MouseEvent event) {

        t.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent bb) {

                DB g = t.getItems().get(t.getSelectionModel().getSelectedIndex());

                EmpFirstName.setText(g.getFname());
                EmpLastName.setText(g.getLname());

                LocalDate l = LocalDate.parse(g.getDate());
                EmpDate.setValue(l);

                // RadioButton g1 = (RadioButton) radioGroup.getSelectedToggle();
                String kind = g.getGender();
                System.out.println(kind);
                if ("Male".equals(kind)) {
                    m.setSelected(true);
                    f.setSelected(false);

                } else {
                    f.setSelected(true);

                }

                Con.setPromptText(g.getRole());

                Empemail.setText(g.getEmail());

            }

        });

    }

    void getDateToTable() {
        Session s = NewHibernateUtil.getSessionFactory().openSession();

        try {

            Criteria p = s.createCriteria(DB.class);
            List<DB> info = p.list();

            ObservableList<DB> list = FXCollections.observableList(info);

            //   for (DB db : list) {
            tid.setCellValueFactory(new PropertyValueFactory("P_Number"));
            tfname.setCellValueFactory(new PropertyValueFactory("FirstName"));
            tlname.setCellValueFactory(new PropertyValueFactory("LastName"));
            tdate.setCellValueFactory(new PropertyValueFactory("DateOfBirth"));
            tgender.setCellValueFactory(new PropertyValueFactory("Gender"));
            trole.setCellValueFactory(new PropertyValueFactory("E_Role"));
            temail.setCellValueFactory(new PropertyValueFactory("Email"));

            //   }
            t.setItems(list);

        } catch (Exception r) {
            r.printStackTrace();

        } finally {
            s.close();
        }

    }

    @FXML
    void DeleteUser(ActionEvent event) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();

        try {

            s.beginTransaction();
            
            Query q = s.createQuery("delete from DB where FirstName = :FN and LastName =:LN and Email =:EM" );
             q.setParameter("FN",EmpFirstName.getText());
             q.setParameter("LN",EmpLastName.getText());
             q.setParameter("EM",Empemail.getText());
         
             q.executeUpdate();
             
             s.getTransaction().commit();
            
            EmpFirstName.setText("");
            EmpLastName.setText("");

            m.setSelected(false);
            f.setSelected(false);

            Con.setPromptText("Select Role");
            Empemail.setText("");
            Emppassword.setText("");

            getDateToTable();

        } catch (Exception f) {
            f.printStackTrace();

        } finally {
            s.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getDateToTable();

        radioGroup = new ToggleGroup();

        m.setToggleGroup(radioGroup);
        f.setToggleGroup(radioGroup);

        ObservableList o = FXCollections.observableArrayList("Admin", "User");
        Con.setItems(o);

    }
}
